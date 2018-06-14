package com.realator.dennism501.moodlemobileproject.POJO;

/**
 * Created by dennism501 on 5/18/17.
 */

public class MoodleMessage {

    private String msgid;
    private String useridfrom;
    private String useridto;
    private String subject;
    private String text;
    private String fullmessage;
    private String fullmessageformat;
    private String smallmessage;
    private String contexturl;
    private String contexturlname;
    private int timecreated;
    private int timeread;
    private String usertofullname;
    private String userfromfullname;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getUseridfrom() {
        return useridfrom;
    }

    public void setUseridfrom(String useridfrom) {
        this.useridfrom = useridfrom;
    }

    public String getUseridto() {
        return useridto;
    }

    public void setUseridto(String useridto) {
        this.useridto = useridto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFullmessage() {
        return fullmessage;
    }

    public void setFullmessage(String fullmessage) {
        this.fullmessage = fullmessage;
    }

    public String getFullmessageformat() {
        return fullmessageformat;
    }

    public void setFullmessageformat(String fullmessageformat) {
        this.fullmessageformat = fullmessageformat;
    }

    public String getSmallmessage() {
        return smallmessage;
    }

    public void setSmallmessage(String smallmessage) {
        this.smallmessage = smallmessage;
    }

    public String getContexturl() {
        return contexturl;
    }

    public void setContexturl(String contexturl) {
        this.contexturl = contexturl;
    }

    public String getContexturlname() {
        return contexturlname;
    }

    public void setContexturlname(String contexturlname) {
        this.contexturlname = contexturlname;
    }

    public int getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(int timecreated) {
        this.timecreated = timecreated;
    }

    public int getTimeread() {
        return timeread;
    }

    public void setTimeread(int timeread) {
        this.timeread = timeread;
    }

    public String getUsertofullname() {
        return usertofullname;
    }

    public void setUsertofullname(String usertofullname) {
        this.usertofullname = usertofullname;
    }

    public String getUserfromfullname() {
        return userfromfullname;
    }

    public void setUserfromfullname(String userfromfullname) {
        this.userfromfullname = userfromfullname;
    }
}
