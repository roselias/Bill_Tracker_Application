package com.example.billtracker;

public class Bills {

    private String billName, dueDate;
    private int id;

    public String getBillName(){
        return billName;
    }

    public void setBillName(String name){
        this.billName = name;
    }

    public String getDueDate(){
        return dueDate;
    }

    public void setDueDate(String date){
        this.dueDate = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Bills(String name, String date){
        this.billName = name;
        this.dueDate = date;
    }
}

