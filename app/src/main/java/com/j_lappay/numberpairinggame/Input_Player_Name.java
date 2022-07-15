package com.j_lappay.numberpairinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Input_Player_Name extends AppCompatActivity {

    private static String user_Name;
    Button submitBtn;

    public static String getUser_Name() {
        return user_Name;
    }

    public static void setUser_Name(String user_Name) {
        Input_Player_Name.user_Name = user_Name;
    }



    EditText getName;

    Intent CallMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_player_name);

        submitBtn = (Button) findViewById(R.id.submit_btn);
        getName = (EditText) findViewById(R.id.playerName);

        submitBtn.setOnClickListener(view -> {

            setUser_Name(String.valueOf(getName.getText()));

            CallMain = new Intent(".MainActivity");

            Thread timer = new Thread(() -> {
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(CallMain);
                    finish();
                }
            });
            timer.start();
        });
    }


}