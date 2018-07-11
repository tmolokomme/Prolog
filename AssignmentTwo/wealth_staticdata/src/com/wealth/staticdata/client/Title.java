package com.wealth.staticdata.client;

import java.io.Serializable;

public class Title implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    
    public Title() {}

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
