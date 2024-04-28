package com.model;

public class TextSections extends AbstractSections{
    private String content;

    public TextSections(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
