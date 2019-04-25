package PersonnelManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelCreator {

    public ExcelCreator() {

    }

    public void createExcelSheet(JTable userregistry, JTable taskregistry) {
        //Get table
        DefaultTableModel usersModel = (DefaultTableModel) userregistry.getModel();
        int userRowCount = usersModel.getRowCount();
        Vector users = usersModel.getDataVector();

        DefaultTableModel tasksModel = (DefaultTableModel) taskregistry.getModel();
        int taskRowCount = tasksModel.getRowCount();
        Vector tasks = tasksModel.getDataVector();

        //Prepare the workbook
        try {
            Workbook wb = new HSSFWorkbook();
            Row row;
            Cell cell;

            //PERSONNEL ROSTER-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //Create row for column names, set colors, and then freeze that row (row position 0)
            Sheet sheet = wb.createSheet("Personnel Roster");
            row = sheet.createRow(0);
            Font fontStyle = wb.createFont();
            fontStyle.setColor(IndexedColors.WHITE.getIndex());
            fontStyle.setBold(true);
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFont(fontStyle); //Necessary!
            cell = row.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("First name");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("Last name");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("Sex");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("Job title");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("Start date");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("End date");
            cell.setCellStyle(style);
            sheet.createFreezePane(0, 1, 0, 1);
            //Creating rows and filling them with row data (starting from row position 1)
            for (int i = 1; i <= userRowCount; i++) {
                row = sheet.createRow(i);
                String[] strArr = users.get(i - 1).toString().replace("]", "").replace("[", "").split(", "); //The -1 is to compensate for the 'column names' row (it was given the spot of row 0)
                for (int j = 0; j < strArr.length; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(strArr[j]);
                }
            }

            //TASKS LIST-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //Creating first row with column names
            sheet = wb.createSheet("Task Assignments");
            row = sheet.createRow(0);
            fontStyle = wb.createFont(); //already defined. delete later, maybe
            fontStyle.setColor(IndexedColors.WHITE.getIndex()); //already defined. delete later, maybe
            fontStyle.setBold(true); //already defined. delete later, maybe
            style = wb.createCellStyle(); //necessary? It was already created above.. 
            style.setFillBackgroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //already defined. delete later, maybe
            style.setFont(fontStyle); //Actually, many of these probably don't need to be written again... oh well.
            cell = row.createCell(0);
            cell.setCellValue("Task ID");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("Task details");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("Task start date");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("Deadline");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("Status");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("Assigned User");
            cell.setCellStyle(style);
            sheet.createFreezePane(0, 1, 0, 1);
            //Filling remaining rows with task data
            for (int i = 1; i < taskRowCount; i++) {
                row = sheet.createRow(i);
                String[] strArr = tasks.get(i - 1).toString().replace("[", "").replace("]", "").split(", ");
                for (int j = 0; j < strArr.length; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(strArr[j]);
                }
            }

            //Create folder at the same location as the jar file
            String fileDirectory = ExcelCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            if (fileDirectory.contains(".jar")) {
                String[] parts = fileDirectory.split("/");
                String jarName = parts[parts.length - 1];
                fileDirectory = fileDirectory.replace(jarName, "");
            }
            File folder = new File(fileDirectory, "Personnel Roster & Assignments");
            folder.mkdir();
            //Create excel file
            OutputStream fileOut = new FileOutputStream(new File(fileDirectory + "Personnel Roster & Assignments", LocalDate.now().toString() + ".PersonnelRoster&Tasks.xls"));
            wb.write(fileOut);
            wb.close();
        } catch (Exception e) {
            System.out.println("ERROR: ExcelCreator().createExcelSheet(JTable) " + e);
        }
        //Parse table
    }
}
