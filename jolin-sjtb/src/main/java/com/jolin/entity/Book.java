package com.jolin.entity;

public class Book {
    private String num;
    private String cod;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Book() {}
    public Book(String num, String cod) {
        this.num = num;
        this.cod = cod;
    }
}
