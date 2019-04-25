package PersonnelManager;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLHelper {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public Connection connectToSQL() {
        try {
            connection = DriverManager.getConnection("jdbc:derby:NaxSQL;create=true");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLHelper().connectToSQL() " + e);
        }
        return connection;
    }

    public void disconnectSQL() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            //Should throw a XJ015 exception, which is normal.
        }
    }

    public void firstTimeSetupSQL() {
        try {
            connection = connectToSQL();//, properties); //Jar freezes when it reaches this point
            ps = connection.prepareStatement("CREATE TABLE user_registry ("
                    + "user_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 0, INCREMENT BY 1),"
                    + "first_name VARCHAR(25) NOT NULL,"
                    + "last_name VARCHAR(25) NOT NULL,"
                    + "job_title VARCHAR(50),"
                    + "start_date DATE,"
                    + "end_date DATE,"
                    + "bio_sex VARCHAR(15)"
                    + ")");
            ps.execute();

            ps = connection.prepareStatement("CREATE TABLE task_registry ("
                    + "task_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 0, INCREMENT BY 1),"
                    + "task_details VARCHAR(300),"
                    + "task_startdate DATE,"
                    + "task_deadline DATE,"
                    + "status VARCHAR(50),"
                    + "user_id INTEGER,"
                    + "CONSTRAINT task_registry_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_registry(user_id)"
                    + ")");
            ps.execute();

            ps = connection.prepareStatement("CREATE TABLE user_pictures ("
                    + "picture BLOB,"
                    + "user_id INTEGER UNIQUE NOT NULL,"
                    + "CONSTRAINT user_pictures_user_id_fkey FOREIGN KEY(user_id) REFERENCES user_registry(user_id) ON DELETE CASCADE"
                    + ")");
            ps.execute();
            ps.close();
            connection.close();
        } catch (HeadlessException | SQLException e) {
        }
    }

    public ArrayList<Object[]> selectUsers(String queryString) {
        Integer userid = null;
        ArrayList<Object[]> arr = new ArrayList();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM user_registry WHERE UPPER(CAST(user_id AS CHAR(10)) || first_name || last_name || job_title || start_date || end_date || bio_sex) LIKE UPPER(?)");
            ps.setString(1, "%" + queryString + "%");
            rs = ps.executeQuery();
            Object[] obj;
            while (rs.next()) {
                obj = new Object[8];
                obj[0] = rs.getInt(1);
                userid = rs.getInt(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(7);
                obj[4] = rs.getString(4);
                obj[5] = rs.getDate(5);
                obj[6] = rs.getDate(6);
                //No need for an image to be stored here. This is only for populating the table and for the search bar. 
                arr.add(obj);
            }
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
        if (arr.size() == 1) {
            displayPicture(selectUserPicture(userid));
        }
        return arr;
    }

    public Object[] selectUsers(int userid) {
        Object[] obj = null;
        ImageIcon imageIcon = selectUserPicture(userid);
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM user_registry WHERE user_id = ?");
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                obj = new Object[8];
                obj[0] = rs.getInt(1); //userid
                obj[1] = rs.getString(2); //firstname
                obj[2] = rs.getString(3); //lastname
                obj[3] = rs.getString(7); //sex
                obj[4] = rs.getString(4); //jobtitle
                obj[5] = rs.getDate(5); //startdate
                obj[6] = rs.getDate(6); //enddate
                obj[7] = imageIcon;
            }
            ps.close();
            connection.close();
            return obj;
        } catch (SQLException e) {
        }
        return null;
    }

    public void insertUser(String firstname, String lastname, String jobtitle, String startdate, String enddate, String biosex, String path) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO user_registry(first_name, last_name, job_title, start_date, end_date, bio_sex) "
                    + "VALUES(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, jobtitle);
            ps.setDate(4, Date.valueOf(startdate));
            ps.setDate(5, Date.valueOf(enddate));
            ps.setString(6, biosex);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                int userid = rs.getInt(1);
                if (path != null) {
                    insertUserPicture(path, userid);
                }
            }
            ps.close();
            connection.close();
            populateTable(selectUsers(""), PersonnelManagerGUI.tbl_userregistry);
        } catch (SQLException e) {
        }
    }

    public void updateUser(int userid, String firstname, String lastname, String jobtitle, String startdate, String enddate, String biosex, String path) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("UPDATE user_registry SET first_name = ?, last_name = ?, job_title = ?, start_date = ?, end_date = ?, bio_sex = ? WHERE user_id = ?");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, jobtitle);
            ps.setDate(4, Date.valueOf(startdate));
            ps.setDate(5, Date.valueOf(enddate));
            ps.setString(6, biosex);
            ps.setInt(7, userid);
            ps.execute();
            ps.close();
            connection.close();
            if (path != null) {
                insertUserPicture(path, userid);
            }
            populateTable(selectUsers(""), PersonnelManagerGUI.tbl_userregistry);
            JOptionPane.showMessageDialog(null, "User has been updated successfully.");
        } catch (SQLException e) {
        }
    }

    public void deleteUser(int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DELETE FROM user_registry WHERE user_id = ?");
            ps.setInt(1, userid);
            ps.execute();
            ps.close();
            connection.close();
            populateTable(selectUsers(""), PersonnelManagerGUI.tbl_userregistry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The user you have selected cannot be deleted as there are tasks assigned to this user."
                    + "\nYour options are:"
                    + "\n1. Delete all of the tasks assigned to this user."
                    + "\n2. Reassign all of this user's tasks to another user."
                    + "\nAfter that is done, the user can be removed from the database.");
        }
    }

    public ArrayList<Object[]> selectTasks(String queryString) {
        ArrayList<Object[]> arr = new ArrayList();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM task_registry WHERE UPPER(CAST(task_id AS CHAR(10)) || task_details || task_startdate || task_deadline || status || CAST(user_id AS CHAR(10))) LIKE ?");
            ps.setString(1, "%" + queryString + "%");
            rs = ps.executeQuery();
            Object[] obj;
            while (rs.next()) {
                obj = new Object[6];
                obj[0] = rs.getInt("task_id");
                obj[1] = rs.getString("task_details");
                obj[2] = rs.getDate("task_startdate");
                obj[3] = rs.getDate("task_deadline");
                obj[4] = rs.getString("status");
                obj[5] = rs.getInt("user_id");
                arr.add(obj);
            }
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    public ArrayList<Object[]> selectTasks(int taskid) {
        ArrayList<Object[]> arr = new ArrayList();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM task_registry WHERE task_id = ?");
            ps.setInt(1, taskid);
            rs = ps.executeQuery();
            Object[] obj;
            while (rs.next()) {
                obj = new Object[6];
                obj[0] = rs.getInt("task_id");
                obj[1] = rs.getString("task_details");
                obj[2] = rs.getDate("task_startdate");
                obj[3] = rs.getDate("task_deadline");
                obj[4] = rs.getString("status");
                obj[5] = rs.getInt("user_id");
                arr.add(obj);
            }
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
        return arr;
    }

    public void insertTask(String taskdetails, String taskstartdate, String taskdeadline, String status, int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO task_registry (task_details, task_startdate, task_deadline, status, user_id) VALUES (?,?,?,?,?)");
            ps.setString(1, taskdetails);
            ps.setDate(2, Date.valueOf(taskstartdate));
            ps.setDate(3, Date.valueOf(taskdeadline));
            ps.setString(4, status);
            ps.setInt(5, userid);
            ps.execute();
            ps.close();
            connection.close();
            populateTable(selectTasks(""), PersonnelManagerGUI.tbl_taskregistry);
        } catch (SQLException e) {
        }
    }

    public void updateTask(int taskid, String taskdetails, String taskstartdate, String taskdeadline, String status, int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("UPDATE task_registry SET task_details = ?, task_startdate = ?, task_deadline = ?, status = ?, user_id = ? WHERE task_id = ?");
            ps.setString(1, taskdetails);
            ps.setDate(2, Date.valueOf(taskstartdate));
            ps.setDate(3, Date.valueOf(taskdeadline));
            ps.setString(4, status);
            ps.setInt(5, userid);
            ps.setInt(6, taskid);
            ps.execute();
            ps.close();
            connection.close();
            populateTable(selectTasks(""), PersonnelManagerGUI.tbl_taskregistry);
        } catch (SQLException e) {
        }
    }

    public void deleteTask(int taskid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DELETE FROM task_registry WHERE task_id = ?");
            ps.setInt(1, taskid);
            ps.execute();
            ps.close();
            connection.close();
            populateTable(selectTasks(""), PersonnelManagerGUI.tbl_taskregistry);
        } catch (SQLException e) {
        }
    }

    public ImageIcon selectUserPicture(int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT * FROM user_pictures WHERE user_id = ?");
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                InputStream inputStream = rs.getBinaryStream(1);
                ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
                int num = inputStream.read();
                while (num >= 0) {
                    baoStream.write((char) num);
                    num = inputStream.read();
                }
                Image image = Toolkit.getDefaultToolkit().createImage(baoStream.toByteArray());
                ImageIcon imageIcon = new ImageIcon(image);
                ps.close();
                connection.close();
                return imageIcon;
            } else {
                ps.close();
                connection.close();
                return null;
            }
        } catch (IOException | SQLException e) {
        }
        return null;
    }

    public void insertUserPicture(String path, int userid) {
        if (path != null) {
            try {
                connection = connectToSQL();
                ps = connection.prepareStatement("INSERT INTO user_pictures (picture, user_id) VALUES (?,?)");
                FileInputStream fis = new FileInputStream(new File(path));
                ps.setBinaryStream(1, fis);
                ps.setInt(2, userid);
                ps.execute();
                ps.close();
                connection.close();
            } catch (FileNotFoundException | SQLException e) {
            }
        }
    }

    public void updateUserPicture(String path, int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("UPDATE user_pictures SET picture = ?, user_id = ?");
            FileInputStream fis = new FileInputStream(new File(path));
            ps.setBinaryStream(1, fis);
            ps.setInt(2, userid);
            ps.execute();
            ps.close();
            connection.close();
        } catch (FileNotFoundException | SQLException e) {
        }
    }

    public void deleteUserPicture(int userid) {
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("DELETE FROM user_pictures WHERE user_id = ?");
            ps.setInt(1, userid);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
    }

    public void populateTable(ArrayList<Object[]> arr, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (arr != null) {
            Iterator<Object[]> iter = arr.iterator();
            while (iter.hasNext()) {
                model.addRow(iter.next());
            }
        }
    }

    public void displayPicture(ImageIcon imageIcon) {
        if (imageIcon != null) {
            Image image = imageIcon.getImage();
            JLabel imageFrame = PersonnelManagerGUI.lbl_image;
            if (image.getWidth(null) > image.getHeight(null)) {
                image = image.getScaledInstance(imageFrame.getWidth(), image.getHeight(null) * imageFrame.getWidth() / image.getWidth(null), Image.SCALE_SMOOTH); //Calculate for new height
            } else {
                image = image.getScaledInstance(imageFrame.getHeight() * image.getWidth(null) / image.getHeight(null), imageFrame.getHeight(), Image.SCALE_SMOOTH); //Calculate for new width
            }
            imageIcon = new ImageIcon(image);
            PersonnelManagerGUI.lbl_image.setHorizontalAlignment(0);
            PersonnelManagerGUI.lbl_image.setIcon(imageIcon);
        }
    }

    /*
    =====================================================================
    THE CODE BELOW IS FOR TESTING PURPOSES. MAY OR MAY NOT BE INCLUDED IN THE FINAL PRODUCT.
    =====================================================================
     */
    public static void main(String[] args) {
        SQLHelper sql = new SQLHelper();
        sql.customSQLStatement();
    }

    public void customSQLStatement() {
        String statement = "CREATE TABLE user_pictures ("
                + "picture BLOB,"
                + "user_id INTEGER UNIQUE NOT NULL,"
                + "CONSTRAINT user_pictures_user_id_fkey FOREIGN KEY(user_id) REFERENCES user_registry(user_id) ON DELETE CASCADE"
                + ")";
//        String statement2 = "DROP TABLE user_pictures";
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement(statement);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
    }

    public void insertTestUser() {
        int userid;
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO user_registry(first_name, last_name, job_title, start_date, end_date, bio_sex) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, randomString());
            ps.setString(2, randomString());
            ps.setString(3, randomJob());
            ps.setDate(4, randomDate());
            ps.setDate(5, randomDate());
            ps.setString(6, randomSex());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userid = rs.getInt(1);
            } else {
            }
            populateTable(selectUsers(""), PersonnelManagerGUI.tbl_userregistry);
        } catch (SQLException e) {
//            System.out.println("insertTestUser() " + e);
        }
    }

    public void insertTestTask() {
        try {
            int randomid = randomUserid();
            connection = connectToSQL();
            ps = connection.prepareStatement("INSERT INTO task_registry(task_details, task_startdate, task_deadline, status, user_id) VALUES (?,?,?,?,?)");
            ps.setString(1, randomString());
            ps.setDate(2, randomDate());
            ps.setDate(3, randomDate());
            ps.setString(4, randomStatus());
            ps.setInt(5, randomid);
            ps.execute();
            populateTable(selectTasks(""), PersonnelManagerGUI.tbl_taskregistry);
        } catch (SQLException e) {
//            System.out.println("insertTestTask() " + e);
        }
    }

    public String randomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomString = "";
        for (int i = 0; i < 3; i++) {
            randomString = randomString + alphabet.charAt(new Random().nextInt(alphabet.length()));
        }
        return randomString;
    }

    public String randomJob() {
        String[] arr = {"Programmer", "Manager", "Professor", "Physician", "Lawyer", "Electrical Engineer", "Mechanical Engineer", "Janitor", "Secretary", "CEO"};
        return arr[new Random().nextInt(arr.length)];
    }

    public String randomPhoneNumber() {
        String numbers = "0123456789";
        String randomPhoneNumber = "";
        for (int i = 0; i < 10; i++) {
            randomPhoneNumber += numbers.charAt(new Random().nextInt(numbers.length()));
        }
        return randomPhoneNumber;
    }

    public String randomSex() {
        String[] arr = {"Male", "Female"};
        return arr[new Random().nextInt(arr.length)];
    }

    public Date randomDate() {
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = minDay + new Random().nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return Date.valueOf(randomBirthDate);
    }

    public String randomStatus() {
        String[] arr = {"In-Progress", "Completed", "Cancelled", "Postponed", "Other"};
        return arr[new Random().nextInt(arr.length)];
    }

    public int randomUserid() {
        ArrayList<Integer> arr = new ArrayList();
        try {
            connection = connectToSQL();
            ps = connection.prepareStatement("SELECT user_id FROM user_registry");
            rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
//            System.out.println("SQLHelper.randomUserid() " + e);
        }
        return arr.get(new Random().nextInt(arr.size()));
    }
}
