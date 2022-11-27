package com.example.boardcar;

public class RecyclerViewCommentDataModel {
    String name;
    String body;
    String regdate;

    public String getName() {
        return name;
    }
    public String getBody() {
        return body;
    }
    public String getRegdate() {return regdate;}

    public RecyclerViewCommentDataModel(String name , String body, String regdate) {
        this.name = name;
        this.body=body;
        this.regdate=regdate;
    }
}
