package com.example.boardcar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
        return v;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.BoardWrite:
                Intent intent = new Intent(getActivity(), BoardEditUI.class);
                startActivity(intent);

        }


        }


}