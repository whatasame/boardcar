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

import java.util.ArrayList;

import Community.BoardInfo;
import Community.BoardUtil;


public class BoardUI extends Fragment implements View.OnClickListener {
        RecyclerViewBoardAdapter.OnItemClickListener listener;
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




        //게시판 열떄 제목이랑 작성자 보내서 그에 맞는애 불러오는 라인
        listener =new RecyclerViewBoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String title, String writer) {
                intent = new Intent(getContext(),OpenUI.class);
                intent.putExtra("title", title);
                intent.putExtra("writer", writer);
                startActivity(intent);
            }
        };



        return v;

    }


    @Override
    public void onClick(View view) {
        boardList=v.findViewById(R.id.BoardList);
        RecyclerViewBoardAdapter adapter = new RecyclerViewBoardAdapter(getContext(),listener);
        switch (view.getId()){
            case R.id.BoardWrite:
                Intent intent = new Intent(getActivity(), BoardEditUI.class);
                startActivity(intent);
                break;
            case R.id.BoardSort:
                //정렬은 빡세니... 맨마지막?
                break;
            case R.id.FreeBoard:
                BoardUtil freeBoardUtil = new BoardUtil(getContext());
                freeBoard.setTypeface(null, Typeface.BOLD);
                honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                carBoard.setTypeface(null, Typeface.NORMAL);
                freeBoardUtil.openPostList("자유");
                //자유게시판에 있는 글 불러오는데 각각 제목,작성자,추천수 DB에 해당하는애 for문으로 돌리기
                // BoardList(adapter,boardList,"제목","작성자",1);
                break;
            case R.id.HoneyTipBoard:
                BoardUtil honeyTipBoardUtil = new BoardUtil(getContext());
                freeBoard.setTypeface(null, Typeface.NORMAL);
                honeyTipBoard.setTypeface(null, Typeface.BOLD);
                carBoard.setTypeface(null, Typeface.NORMAL);
                ArrayList<BoardInfo> honeyTipArrayList = honeyTipBoardUtil.openPostList("꿀팁");
                for(BoardInfo item : honeyTipArrayList){
                    BoardList(adapter,boardList,item.getTITLE(), item.getMID(), item.getUPVOTE());
                }

                break;
            case R.id.CarBoard:
                freeBoard.setTypeface(null, Typeface.NORMAL);
                honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                carBoard.setTypeface(null, Typeface.BOLD);
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