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

import java.sql.SQLOutput;
import java.util.ArrayList;

import Community.BoardInfo;
import Community.BoardUtil;


public class BoardUI extends Fragment implements View.OnClickListener {
    RecyclerViewBoardAdapter.OnItemClickListener listener;
    TextView freeBoard, honeyTipBoard, carBoard;
    Button boardSort;
    ImageView boardWrite;
    View v;
    RecyclerView boardList;
    Intent intent;
    boolean isFreeList = true, isHoneyTipList = false, isCarList = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_board, container, false);
        freeBoard = v.findViewById(R.id.FreeBoard);
        honeyTipBoard = v.findViewById(R.id.HoneyTipBoard);
        carBoard = v.findViewById(R.id.CarBoard);
        boardSort = v.findViewById(R.id.Refresh);
        boardWrite = v.findViewById(R.id.BoardWrite);
        boardList = v.findViewById(R.id.BoardList);

        boardWrite.setOnClickListener(this);
        boardSort.setOnClickListener(this);
        freeBoard.setOnClickListener(this);
        honeyTipBoard.setOnClickListener(this);
        carBoard.setOnClickListener(this);


        //게시판 열떄 제목이랑 작성자 보내서 그에 맞는애 불러오는 라인
        listener = new RecyclerViewBoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String title, String writer, String pid) {
                intent = new Intent(getContext(), OpenUI.class);
                intent.putExtra("pid", pid);
                intent.putExtra("title", title);
                intent.putExtra("writer", writer);
                startActivity(intent);
            }
        };
        return v;
    }

    @Override
    public void onClick(View view) {
        boardList = v.findViewById(R.id.BoardList);
        RecyclerViewBoardAdapter adapter = new RecyclerViewBoardAdapter(getContext(), listener);
        switch (view.getId()) {
            case R.id.BoardWrite: // 얘가 글 작성 버튼 클릭
                Intent intent = new Intent(getActivity(), BoardEditUI.class);
                startActivity(intent);
                break;
            case R.id.Refresh:
                int index = getListSelected();
                BoardUtil boardUtil = new BoardUtil(getContext());
                ArrayList<BoardInfo> boardInfoArrayList;
                switch(index){
                    case 1: // FreeBoard Refresh
                        freeBoard.setTypeface(null, Typeface.BOLD);
                        honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                        carBoard.setTypeface(null, Typeface.NORMAL);
                        boardInfoArrayList = boardUtil.openPostList("자유");
                        break;
                    case 2: // HoneyBoard Refresh
                        freeBoard.setTypeface(null, Typeface.NORMAL);
                        honeyTipBoard.setTypeface(null, Typeface.BOLD);
                        carBoard.setTypeface(null, Typeface.NORMAL);
                        boardInfoArrayList = boardUtil.openPostList("꿀팁");
                        break;
                    case 3: // CarBoard Refresh
                        freeBoard.setTypeface(null, Typeface.NORMAL);
                        honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                        carBoard.setTypeface(null, Typeface.BOLD);
                        boardInfoArrayList = boardUtil.openPostList("차량");
                        break;
                    default: // 초기화. 해당 실행은 없도록 구현
                        boardInfoArrayList = new ArrayList<>();
                }
                for(BoardInfo item : boardInfoArrayList){
                    BoardList(adapter, boardList, item.getTITLE(), item.getMID(), item.getUPVOTE(), item.getPID());
                }
                break;
            case R.id.FreeBoard:
                BoardUtil freeBoardUtil = new BoardUtil(getContext());
                freeBoard.setTypeface(null, Typeface.BOLD);
                honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                carBoard.setTypeface(null, Typeface.NORMAL);
                ArrayList<BoardInfo> freeArrayList = freeBoardUtil.openPostList("자유");
                for (BoardInfo item : freeArrayList){
                    BoardList(adapter, boardList, item.getTITLE(), item.getMID(), item.getUPVOTE(), item.getPID());
                }
                setListSelected(true,false, false);
                break;
            case R.id.HoneyTipBoard:
                BoardUtil honeyTipBoardUtil = new BoardUtil(getContext());
                freeBoard.setTypeface(null, Typeface.NORMAL);
                honeyTipBoard.setTypeface(null, Typeface.BOLD);
                carBoard.setTypeface(null, Typeface.NORMAL);
                ArrayList<BoardInfo> honeyTipArrayList = honeyTipBoardUtil.openPostList("꿀팁");
                for (BoardInfo item : honeyTipArrayList) {
                    BoardList(adapter, boardList, item.getTITLE(), item.getMID(), item.getUPVOTE(), item.getPID());
                }
                setListSelected(false,true, false);
                break;
            case R.id.CarBoard:
                BoardUtil carBoardUtil = new BoardUtil(getContext());
                freeBoard.setTypeface(null, Typeface.NORMAL);
                honeyTipBoard.setTypeface(null, Typeface.NORMAL);
                carBoard.setTypeface(null, Typeface.BOLD);
                ArrayList<BoardInfo> carArrayList = carBoardUtil.openPostList("차량");
                for (BoardInfo item : carArrayList)
                    BoardList(adapter, boardList, item.getTITLE(), item.getMID(), item.getUPVOTE(), item.getPID());
                setListSelected(false,false, true);
                break;
        }


    }

    /**
     * 게시판 새로고침을 위해 어디 탭인지 표시하는 메소드
     * @param isFreeList {boolean}
     * @param isHoneyTipList {boolean}
     * @param isCarList {boolean}
     */
    private void setListSelected(boolean isFreeList, boolean isHoneyTipList, boolean isCarList){
        this.isFreeList = isFreeList;
        this.isHoneyTipList = isHoneyTipList;
        this.isCarList = isCarList;
    }

    /**
     * 선택한 리스트가 뭔지 알려준다.
     * @return index{int} 1:자유 2:꿀팁 3:차량
     */
    private int getListSelected(){
        if(isFreeList) return 1;
        else if(isHoneyTipList) return 2;
        else return 3;
    }
    //리스트에 내용 보여주는애 제목, 작성자, 추천수 받아오는게
    public void BoardList(RecyclerViewBoardAdapter adapter, RecyclerView BoardList, String title, String writer, int recommend, int pid) {
        BoardList.setVisibility(View.VISIBLE);
        adapter.addItem(new RecyclerViewBoardDataModel(writer, title, String.valueOf(recommend), String.valueOf(pid)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        BoardList.setLayoutManager(layoutManager);
        BoardList.setAdapter(adapter);
    }
}