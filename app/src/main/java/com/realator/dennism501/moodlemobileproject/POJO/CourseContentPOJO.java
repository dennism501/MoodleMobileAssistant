package com.realator.dennism501.moodlemobileproject.POJO;

/**
 * Created by dennism501 on 5/22/17.
 */

public class CourseContentPOJO {

    private String id;
    private String name;
    private String visible;
    private String summary;
    private String summaryformat;
    private String section;
    private String hiddenbynumsection;
    private String modid;
    private String modurl;
    private String modModname;
    private String modinstance;
    private String moddescription;
    private String modvisible;
    private String modicon;
    private String modname;
    private String modplural;
    private String modindent;
    private Module module = new Module();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryformat() {
        return summaryformat;
    }

    public void setSummaryformat(String summaryformat) {
        this.summaryformat = summaryformat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getHiddenbynumsection() {
        return hiddenbynumsection;
    }

    public void setHiddenbynumsection(String hiddenbynumsection) {
        this.hiddenbynumsection = hiddenbynumsection;
    }

    /*
    * The following methods are used to set and get values for the module
    * composed class in this script
    * */

    public String getModid() {
        return modid;
    }

    public void setModid(String modid) {
        this.modid = modid;
    }

    public String getModurl() {
        return modurl;
    }

    public void setModurl(String modurl) {
        this.modurl = modurl;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }

    public String getModplural() {
        return modplural;
    }

    public void setModplural(String modplural) {
        this.modplural = modplural;
    }

    public String getModindent() {
        return modindent;
    }

    public void setModindent(String modindent) {
        this.modindent = modindent;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getModinstance() {
        return modinstance;
    }

    public void setModinstance(String modinstance) {
        this.modinstance = modinstance;
    }

    public String getModdescription() {
        return moddescription;
    }

    public void setModdescription(String moddescription) {
        this.moddescription = moddescription;
    }

    public String getModvisible() {
        return modvisible;
    }

    public void setModvisible(String modvisible) {
        this.modvisible = modvisible;
    }

    public String getModicon() {
        return modicon;
    }

    public void setModicon(String modicon) {
        this.modicon = modicon;
    }

    public String getModModname() {
        return modModname;
    }

    public void setModModname(String modModname) {
        this.modModname = modModname;
    }
}
