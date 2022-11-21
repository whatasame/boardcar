package com.example.boardcar;

public class RecyclerViewBoardDataModel {
    String name;
    String title;
    String comment;
    String recommend;

    public String getName() {
        return name;
    }
    public String getTitle() {
        return title;
    }
    public String getComment() {
        return comment;
    }
    public String getRecommend() {
        return recommend;
    }

    public RecyclerViewBoardDataModel(String name , String title,String comment, String recommend) {
        this.name = name;
        this.title=title;
        this.comment=comment;
        this.recommend=recommend;
    }

}
