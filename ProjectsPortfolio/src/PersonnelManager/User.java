package PersonnelManager;

import java.sql.Date;
import javax.swing.ImageIcon;

/*
The User class will not be used in the creation process of new users but instead as data storage. 
When the data is pulled from SQL, it can be stored in the variables of this class.
 */
public class User {

    protected int userid; //Automatically assigned by SQL server
    protected String firstname;
    protected String lastname;
    protected String jobtitle;
    protected Date startdate;
    protected Date enddate;
    protected String biosex;
    protected ImageIcon picture;

    public User(int userid, String firstname, String lastname, String jobtitle, Date startdate, Date enddate, String biosex, ImageIcon picture) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.jobtitle = jobtitle;
        this.startdate = startdate;
        this.enddate = enddate;
        this.biosex = biosex;
        this.picture = picture;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setBiosex(String biosex) {
        this.biosex = biosex;
    }

    public int getUserid() {
        return userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public String getBiosex() {
        return biosex;
    }
}
