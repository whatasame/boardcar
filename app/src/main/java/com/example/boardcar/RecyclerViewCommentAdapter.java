package com.example.boardcar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewCommentAdapter extends RecyclerView.Adapter {
    private final List<RecyclerViewCommentDataModel> items = new ArrayList<>();

    public void addItem(RecyclerViewCommentDataModel commentDataModel) { //댓글 리스트에 종류 추가하는애
        items.add(commentDataModel);
    }
    public void clearItem(){
        items.clear();
    }

    @Override
    public int getItemCount() { //그냥 필요쓸일 없는데 오버라이딩 떄문에 강제로 있어야하는애 신경 ㄴㄴ
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentrecylcer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //애는 틀임
        ViewHolder ViewHolder = (ViewHolder)holder;
        ViewHolder.body.setText(items.get(position).getBody());
        ViewHolder.writer.setText(items.get(position).getName());
        ViewHolder.regdate.setText(items.get(position).getRegdate());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView writer,body,regdate;

        public ViewHolder(@NonNull View itemView) { //리스트 안에있는 객체들에 각각 값을 지정해줄수있는애
            super(itemView);
            body = itemView.findViewById(R.id.CommentBody);
            writer =  itemView.findViewById(R.id.CommentWriter);
            regdate=itemView.findViewById(R.id.CommentRegDate);



        }


    }

}