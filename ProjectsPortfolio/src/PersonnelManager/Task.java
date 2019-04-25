package PersonnelManager;

import java.sql.Date;

public class Task {

    protected int taskid;
    protected String taskdetails;
    protected Date taskstartdate;
    protected Date taskdeadline;
    protected String status;
    protected int userid;

    public Task(int taskid, String taskdetails, Date taskstartdate, Date taskdeadline, String status, int userid) {
        this.taskid = taskid;
        this.taskdetails = taskdetails;
        this.taskstartdate = taskstartdate;
        this.taskdeadline = taskdeadline;
        this.status = status;
        this.userid = userid;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTaskdetails() {
        return taskdetails;
    }

    public void setTaskdetails(String taskdetails) {
        this.taskdetails = taskdetails;
    }

    public Date getTaskstartdate() {
        return taskstartdate;
    }

    public void setTaskstartdate(Date taskstartdate) {
        this.taskstartdate = taskstartdate;
    }

    public Date getTaskdeadline() {
        return taskdeadline;
    }

    public void setTaskdeadline(Date taskdeadline) {
        this.taskdeadline = taskdeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
}
