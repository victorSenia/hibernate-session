package com.leo.test.hibernate.session.util;

import com.leo.test.hibernate.session.model.Browser;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public class BrowserMapper {

    public static String toString(Browser browser) {
        return appendBuilder(browser, new StringBuilder()).toString();
    }

    public static String toString(Iterable<Browser> iterable) {
        StringBuilder builder = new StringBuilder();
        for (Browser browser : iterable) {
            appendBuilder(browser, builder);
        }
        return builder.toString();
    }

    private static StringBuilder appendBuilder(Browser browser, StringBuilder builder) {
        return builder.append("{").append("\"id\":").append(browser.getId()).append(", \"browser\":\"").append(browser.getBrowser()).append('"').append(", \"cssGrade\":\"").append(browser.getCssGrade()).append('"').append(", \"engine\":\"").append(browser.getEngine()).append('"').append(", \"engineVersion\":\"").append(browser.getEngineVersion()).append('"').append(", \"platform\":\"").append(browser.getPlatform()).append('"').append('}');
    }

    public static Browser toBrowser(String browser) {
        try {
            JsonObject object = Json.createReader(new StringReader(browser)).readObject();
            Browser browserObject = new Browser();
            browserObject.setEngine(object.getString("engine"));
            browserObject.setPlatform(object.getString("platform"));
            browserObject.setEngineVersion(object.getString("engineVersion"));
            browserObject.setCssGrade(object.getString("cssGrade"));
            browserObject.setBrowser(object.getString("browser"));
            if (browserObject.getBrowser() != null && browserObject.getCssGrade() != null && browserObject.getEngine() != null && browserObject.getEngineVersion() != null && browserObject.getPlatform() != null)
                return browserObject;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
