package com.hxx.domain;

public class Log {

    private String src;
    private String dest;
    private String amount;
    private String date;

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Log(String src, String dest, String amount, String date){
        this.src=src;
        this.dest = dest;
        this.amount = amount;
        this.date = date;
    }

}
