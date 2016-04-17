package com.zybnet.autocomplete.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AutocompleteFieldSuggestion implements Serializable {

    private Integer id;
    private String value;
    private String caption;

    public String getCaption() {
        return caption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}