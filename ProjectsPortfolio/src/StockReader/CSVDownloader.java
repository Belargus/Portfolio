package StockReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
//import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

public class CSVDownloader {

    /*
    1. Download the page of the stock index by supplying the URL and symbol. To prevent HTTP401, you need to 'trick' it by adding a header.
    2. Locate the crumb by breaking the downloaded page into smaller strings by the '}' character and then checking each for "CrumbStore".
    3. If located, split the string using the ':' character. Keep only the third section, as it is the crumb we're looking for. i.e. Discard "CrumbStore" and "crumb".
    4. The crumb must be unescaped before it is usable. Do this by using StringEscapeUtils.unescapeJava(crumb). 
    5. Download data (???) 
     */

    HttpClient client = HttpClientBuilder.create().build();
    HttpClientContext context = HttpClientContext.create();
    String fileDirectory = null;

    public CSVDownloader() {
        CookieStore cookieStore = new BasicCookieStore();
        client = HttpClientBuilder.create().build();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
    }

    public CSVDownloader(String[] symbols, long startDate, long endDate) {
        CookieStore cookieStore = new BasicCookieStore();
        client = HttpClientBuilder.create().build();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        for (String symbol : symbols) {
            String crumb = locateAndGetCrumb(getPage(symbol));
            if (crumb != null) {
                downloadData(symbol, startDate, endDate, crumb);
            }
        }
    }

    public CSVDownloader(String[] symbols) {
        CookieStore cookieStore = new BasicCookieStore();
        client = HttpClientBuilder.create().build();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        for (String symbol : symbols) {
            String crumb = locateAndGetCrumb(getPage(symbol));
            if (crumb != null) {
                downloadData(symbol, 0, System.currentTimeMillis(), crumb);
            }
        }
    }

    public CSVDownloader getInstance() {
        return this;
    }

    //(1) Gets page data, (2) Parses the data into smaller Strings and stores in a List, (3) Returns the List
    private List<String> getPage(String symbol) {
        String pageData = null;
        String URL = String.format("https://finance.yahoo.com/quote/%s/?p=%s", symbol, symbol);
        HttpGet request = new HttpGet(URL);
        request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
        try {
            HttpResponse response = client.execute(request, context);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer; //Same as StringBuilder, but is threadsafe
            stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            pageData = stringBuffer.toString();
            HttpClientUtils.closeQuietly(response);
        } catch (IOException | UnsupportedOperationException e) {
        }
        List<String> pageDataList; //splits the page data into smaller strings. Uses "}" to divide up the data.
        pageDataList = Arrays.asList(pageData.split("}"));
        return pageDataList;
    }

    //(1) Locate crumb if it exists, (2) Return the crumb
    private String locateAndGetCrumb(List<String> pageDataList) {
        String crumb = null;
        for (String line : pageDataList) {
            //Go through the pageDataList and check each string for "CrumbStore"
//            if (line.indexOf("CrumbStore") > -1) {
            if (line.contains("CrumbStore")) {
                crumb = line;
                break;
            }
        }
        if (crumb != null) {
            String[] splitLine = crumb.split(":");
            //the retrieved strings need to be parsed to just the crumb itself. i.e.  ,"CrumbStore":{"crumb":"cRuMbExAmPlE01" needs to be reduced to just "cRuMbExAmPlE01"
            crumb = splitLine[2];
            //This selects the third part which is the crumb. But it's still not "pure" enough. The quotations also need to be removed.
            crumb = crumb.replace("\"", "");
            //This replaces the quotation with "". One last step needed: the crumb now needs to be unescaped to be finally usable.
//            crumb = org.apache.commons.text.StringEscapeUtils.unescapeJava(crumb);
            crumb = StringEscapeUtils.unescapeJava(crumb);
            //Now the crumb is in a usable state.
        }
        if (crumb == null) {
            JOptionPane.showMessageDialog(null, "The data for the specified stock symbol could not be found.\n"
                    + "Please ensure that it is not mispelled and that you are using the symbol and not the full name of the company. If the stock symbol includes non-alphabetic characters such as ^, then it too must be included.\n"
                    + "(For example: The Dow Jones Index uses ^DJI as their stock symbol. Bank of America Corporation uses BAC as their stock symbol.)");
        }
        return crumb;
    }

    public void getCSVsFolderPath() {
        if (fileDirectory == null) {
            fileDirectory = CSVDownloader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            if (fileDirectory.contains(".jar")) {
                String[] parts = fileDirectory.split("/");
                String jarName = parts[parts.length - 1];
                fileDirectory = fileDirectory.replace(jarName, "");
            }
        }
        File folder = new File(fileDirectory, "CSVs");
        folder.mkdir();
    }

    //Last step(s): assemble the URL with the symbol and crumb
    private void downloadData(String symbol, long startDate, long endDate, String crumb) {
        getCSVsFolderPath(); //Creates the folder if it doesn't already exist
        String fileName = null;
        try {
            fileName = String.format("%s.csv", URLDecoder.decode(symbol, "UTF-8")); //names the file after the symbol
        } catch (UnsupportedEncodingException ex) {
        }
        String URL = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%s&period2=%s&interval=1d&events=history&crumb=%s", symbol, startDate, endDate, crumb);
        //https://query1.finance.yahoo.com/v7/finance/download/%5EDJI?period1=1550617840&period2=1553033440&interval=1d&events=history&crumb=LspjxmIomr8
        HttpGet request = new HttpGet(URL);
        request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
        if (fileName != null) {
            try {
                HttpResponse response = client.execute(request, context);
                int responseCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (responseCode == 404) {
                    JOptionPane.showMessageDialog(null, "Unable to get the data for the given stock symbol. \nPlease check the spelling and try again. Examples: '^DJI' , 'AAPL'"); //Possibly unreachable. If the crumb is null in the locateAndGetCrumb() method, then the program doesn't call this method.
                } else {
                    if (entity != null) {
                        BufferedOutputStream bufferedOutputStream;
                        try ( BufferedInputStream bufferedInputStream = new BufferedInputStream(entity.getContent())) {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(fileDirectory + "CSVs", fileName)));
                            int inByte;
                            while ((inByte = bufferedInputStream.read()) != -1) { //It seems that -1 signals that the end of content has been reached
                                bufferedOutputStream.write(inByte);
                            }
                        }
                        bufferedOutputStream.close();
                    }
                    JOptionPane.showMessageDialog(null, "CSV file successfully downloaded.");
                }
                HttpClientUtils.closeQuietly(response);
            } catch (IOException e) {
            }
        }
    }

    //Using a list returned from SQL Database: stock_tracker, this downloads the latest CSVs of the stocks in the list
    public String[] updateAllCSVs() {
        try {
            getCSVsFolderPath();
            FileUtils.cleanDirectory(new File(fileDirectory + "CSVs"));
            String[] urls = new SQLHelper().selectTrackerURLNames().toArray(new String[0]);
            CSVDownloader csvd = new CSVDownloader(urls);
            return urls;
        } catch (IOException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        CSVDownloader csvd = new CSVDownloader();
//        csvd.updateAllCSVs();
    }
}
