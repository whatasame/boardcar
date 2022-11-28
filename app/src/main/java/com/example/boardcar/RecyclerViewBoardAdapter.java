package com.example.boardcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewBoardAdapter extends RecyclerView.Adapter {
    private final List<RecyclerViewBoardDataModel> items = new ArrayList<>();
    public interface OnItemClickListener {
        void onItemClicked(String title, String writer);
    }
    private OnItemClickListener itemClickListener;
    private Context context;
    public RecyclerViewBoardAdapter(Context context,OnItemClickListener listener){
        this.context=context;
        this.itemClickListener=listener;
    }


    public void setOnClickListener (OnItemClickListener lister) {
        itemClickListener = lister;
    }


    public void addItem(RecyclerViewBoardDataModel boardDataModel) { //차량 리스트에 종류 추가하는애
        items.add(boardDataModel);
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.boardrecyler,parent,false);
        RecyclerViewBoardAdapter.ViewHolder viewHolder = new RecyclerViewBoardAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //클릭하면
                String title = viewHolder.getTitle().getText().toString();
                String writer = viewHolder.getWriter().getText().toString();
                itemClickListener.onItemClicked(title, writer);
            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //애는 틀임
        ViewHolder ViewHolder = (ViewHolder)holder;

        ViewHolder.title.setText(items.get(position).getTitle());
        ViewHolder.writer.setText(items.get(position).getName());
        ViewHolder.recommend.setText(items.get(position).getRecommend());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView writer, title,comment,recommend;

        public ViewHolder(@NonNull View itemView) { //리스트 안에있는 객체들에 각각 값을 지정해줄수있는애
            super(itemView);
            title = itemView.findViewById(R.id.ContentTitle);
            writer =  itemView.findViewById(R.id.ContentWriter);
            recommend=itemView.findViewById(R.id.ReCommend);



        }
        public TextView getWriter(){
            return writer;
        }
        public TextView getTitle(){
            return title;
        }


    }

}