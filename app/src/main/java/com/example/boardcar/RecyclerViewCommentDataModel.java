package com.example.boardcar;

public class RecyclerViewCommentDataModel {
    String name;
    String body;

    public String getName() {
        return name;
    }
    public String getBody() {
        return body;
    }

    public RecyclerViewCommentDataModel(String name , String body) {
        this.name = name;
        this.body=body;
    }
}
