package com.example.boardcar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.Type;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BoardUI extends Fragment implements View.OnClickListener {

        TextView freeBoard,honeyTipBoard,carBoard;
        Button  boardSort;
        ImageView boardWrite;
        View v;
        RecyclerView boardList;
        Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_board, container, false);
        freeBoard =v.findViewById(R.id.FreeBoard);
        honeyTipBoard=v.findViewById(R.id.HoneyTipBoard);
        carBoard=v.findViewById(R.id.CarBoard);
        boardSort=v.findViewById(R.id.BoardSort);
        boardWrite=v.findViewById(R.id.BoardWrite);
        boardList=v.findViewById(R.id.BoardList);

        boardWrite.setOnClickListener(this);
        boardSort.setOnClickListener(this);
        freeBoard.setOnClickListener(this);
        honeyTipBoard.setOnClickListener(this);
        carBoard.setOnClickListener(this);

        RecyclerViewBoardAdapter adapter = new RecyclerViewBoardAdapter();

        BoardList(adapter,boardList,"테스트","테스트",1);


       adapter.setOnClickListener(new RecyclerViewBoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String title, String writer) {
                intent = new Intent(getContext(),OpenUI.class);
                intent.putExtra("title", title);
                intent.putExtra("writer", writer);
                startActivity(intent);
            }
        });



        return v;

    }


    @Override
    public void onClick(View view) {
        boardList=v.findViewById(R.id.BoardList);
        RecyclerViewBoardAdapter adapter = new RecyclerViewBoardAdapter();
        switch (view.getId()){
            case R.id.BoardWrite:
                Intent intent = new Intent(getActivity(), BoardEditUI.class);
                startActivity(intent);
                break;
            case R.id.BoardSort:
                //정렬은 빡세니... 맨마지막?
                break;
            case R.id.FreeBoard:
                freeBoard.setTypeface(freeBoard.getTypeface(), Typeface.BOLD);
                honeyTipBoard.setTypeface(honeyTipBoard.getTypeface(), Typeface.NORMAL);
                carBoard.setTypeface(carBoard.getTypeface(), Typeface.NORMAL);
                //자유게시판에 있는 글 불러오는데 각각 제목,작성자,추천수 DB에 해당하는애 for문으로 돌리기
                BoardList(adapter,boardList,"제목","작성자",1);
                break;
            case R.id.HoneyTipBoard:
                freeBoard.setTypeface(freeBoard.getTypeface(), Typeface.NORMAL);
                honeyTipBoard.setTypeface(honeyTipBoard.getTypeface(), Typeface.BOLD);
                carBoard.setTypeface(carBoard.getTypeface(), Typeface.NORMAL);
                BoardList(adapter,boardList,"제목","작성자",1);
                break;
            case R.id.CarBoard:
                freeBoard.setTypeface(freeBoard.getTypeface(), Typeface.NORMAL);
                honeyTipBoard.setTypeface(honeyTipBoard.getTypeface(), Typeface.NORMAL);
                carBoard.setTypeface(carBoard.getTypeface(), Typeface.BOLD);
                //여기는 차정보도 함께 뭐어찌저찌 잘묶어서 for문돌려서 나타내야함
                BoardList(adapter,boardList,"제목","작성자",1);
                break;

        }


        }
//리스트에 내용 보여주는애 제목, 작성자, 추천수 받아오는게
    public void BoardList(RecyclerViewBoardAdapter adapter, RecyclerView BoardList ,String title ,String writer,int recommend ) {
        BoardList.setVisibility(View.VISIBLE);
        adapter.addItem(new RecyclerViewBoardDataModel(title,writer,String.valueOf(recommend)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        BoardList.setLayoutManager(layoutManager);
        BoardList.setAdapter(adapter);
    }
}