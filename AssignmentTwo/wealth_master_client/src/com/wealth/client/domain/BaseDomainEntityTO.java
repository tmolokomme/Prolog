package com.wealth.client.domain;

import java.io.Serializable;

public class BaseDomainEntityTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id = null;
    
    public BaseDomainEntityTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
