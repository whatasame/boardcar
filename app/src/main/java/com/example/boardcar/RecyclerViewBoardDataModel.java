package com.example.boardcar;

public class RecyclerViewBoardDataModel {
    String name;
    String title;
    String recommend;
    String pid;

    public String getName() {
        return name;
    }
    public String getTitle() {
        return title;
    }
    public String getRecommend() {return recommend;}

    public String getPid() {
        return pid;
    }

    public RecyclerViewBoardDataModel(String name, String title, String recommend, String pid) {
        this.name = name;
        this.title = title;
        this.recommend = recommend;
        this.pid = pid;
    }
}
