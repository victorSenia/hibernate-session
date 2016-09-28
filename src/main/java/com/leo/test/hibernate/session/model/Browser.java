package com.leo.test.hibernate.session.model;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public class Browser {
    private Integer id;

    private String browser;

    private String cssGrade;

    private String engine;

    private String engineVersion;

    private String platform;

    public Browser(Integer id, String browser, String cssGrade, String engine, String engineVersion, String platform) {
        this.id = id;
        this.browser = browser;
        this.cssGrade = cssGrade;
        this.engine = engine;
        this.engineVersion = engineVersion;
        this.platform = platform;
    }

    public Browser() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getCssGrade() {
        return cssGrade;
    }

    public void setCssGrade(String cssGrade) {
        this.cssGrade = cssGrade;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + ", \"browser\":\"" + browser + '"' + ", \"cssGrade\":\"" + cssGrade + '"' + ", \"engine\":\"" + engine + '"' + ", \"engineVersion\":\"" + engineVersion + '"' + ", \"platform\":\"" + platform + '"' + '}';
    }
}
