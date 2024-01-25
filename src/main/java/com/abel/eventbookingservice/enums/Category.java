package com.abel.eventbookingservice.enums;

public enum Category {

    Concert("0"),
    Conference("1"),
    Game("2");

    public final String code;

    private Category(String code) {
        this.code = code;
    }

}
