package ru.bvkuchin.intergation.dtos;


public class TextResponseTDO {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextResponseTDO() {
    }

    public TextResponseTDO(String text) {
        this.text = text;
    }
}
