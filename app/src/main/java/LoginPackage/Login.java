package LoginPackage;
/*
* Author : 이윤상
* resent update : 11-21
*
* note
*   수정내용
*       1. MemberDAO를 전역변수 위치에 객체 선언 (null 상태)
*       2. 전역변수로 DBUrl, DBId, DBPw를 final로 선언
*       3. Login 생성자에서 DB연결 삭제 및 MemberDAO 생성자 작성
*       4. 전역변수에서 Connection conn 삭제 (class Diagram에 반영하기)
*
*
*
*
* */
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.boardcar.LoginUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MVC.MemberDAO;
import MVC.MemberVO;

public class Login {
    String alertMessage;


    final String DBUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
    final String DBId = "scott";
    final String DBPw = "tiger";

    MemberDAO memberDAO;

    public Login(){
        memberDAO = new MemberDAO(DBUrl, DBId, DBPw);
    }

    public boolean isIdEmpty(String Id, AlertDialog.Builder Alert){
        if(Id.equals("")) {
            printAlertMessage("아이디를 입력하지 않았습니다.", Alert);
            return true;
        }
        return false;
    }

    public boolean isPwEmpty(String pw, AlertDialog.Builder Alert){

        if(pw.equals("")){
            printAlertMessage("비밀번호를 입력하지 않았습니다.", Alert);
            return true;
        }
        return false;
    }

    public void runLogin(String enterId, String enterPw, AlertDialog.Builder Alert){
        MemberVO memberVO = memberDAO.getMemberById(enterId);
        if(memberVO == null){
            //해당 ID가 DB에 없음
            System.out.println("Login Failed!!!___ No Info!");
            printAlertMessage("회원정보가 존재하지 않습니다.", Alert);
        }
        else if(enterPw.equals(memberVO.getPassword())){
            //DB에서 확인한 정보와 일치할 경우
            System.out.println("Login Successed!!!");
            printAlertMessage("로그인에 성공합니다.", Alert);
        }
        else{
            //두 칸 다
            System.out.println("Login Failed!!!___ Wrong ID or Password!");
            printAlertMessage("ID 혹은 PW가 틀렸습니다.", Alert);
        }

        /*ResultSet resultSet;
        String sql = "SELECT PASSWORD FROM MEMBER WHERE MID = ?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, enterId);
            resultSet = preparedStatement.executeQuery();

            //DB에 저장된 id는 primary Key이므로 중복이 없음. 아이디가 있다면 비밀번호는 하나일 것.
            if(enterPw.equals(resultSet.getString(1))){ //비밀번호랑 일치한다면
                System.out.println("Login Success!");
                alertMessage = "로그인에 성공하였습니다.";
                Alert.setMessage(alertMessage);
                Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                Alert.show();
            }
            else{
                System.out.println("Login Failed!");
                alertMessage = "아이디 혹은 비밀번호가 틀렸습니다.";
                Alert.setMessage(alertMessage);
                Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                Alert.show();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }*/
    }

    private void printAlertMessage(String message, AlertDialog.Builder Alert){
        Alert.setTitle("로그인 실패");
        Alert.setMessage(message);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        Alert.show();
    }
}
