package com.j_lappay.numberpairinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final int[] btn_idArray ={
    R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_10, R.id.btn_11, R.id.btn_12, R.id.btn_13, R.id.btn_14, R.id.btn_15, R.id.btn_16, R.id.btn_17, R.id.btn_18, R.id.btn_19, R.id.btn_20, R.id.btn_21, R.id.btn_22, R.id.btn_23, R.id.btn_24, R.id.btn_25, R.id.btn_26, R.id.btn_27, R.id.btn_28, R.id.btn_29, R.id.btn_30, R.id.btn_31, R.id.btn_32, R.id.btn_33, R.id.btn_34, R.id.btn_35, R.id.btn_36, R.id.btn_37, R.id.btn_38, R.id.btn_39, R.id.btn_40, R.id.btn_41, R.id.btn_42, R.id.btn_43, R.id.btn_44, R.id.btn_45, R.id.btn_46, R.id.btn_47, R.id.btn_48, R.id.btn_49, R.id.btn_50, R.id.btn_51, R.id.btn_52, R.id.btn_53, R.id.btn_54, R.id.btn_55, R.id.btn_56, R.id.btn_57, R.id.btn_58, R.id.btn_59, R.id.btn_60, R.id.btn_61, R.id.btn_62, R.id.btn_63, R.id.btn_64, R.id.btn_65, R.id.btn_66, R.id.btn_67, R.id.btn_68, R.id.btn_69, R.id.btn_70, R.id.btn_71, R.id.btn_72, R.id.btn_73, R.id.btn_74, R.id.btn_75, R.id.btn_76, R.id.btn_77, R.id.btn_78, R.id.btn_79, R.id.btn_80, R.id.btn_81, R.id.btn_82, R.id.btn_83, R.id.btn_84, R.id.btn_85, R.id.btn_86, R.id.btn_87, R.id.btn_88, R.id.btn_89, R.id.btn_90, R.id.btn_91, R.id.btn_92, R.id.btn_93, R.id.btn_94, R.id.btn_95, R.id.btn_96, R.id.btn_97, R.id.btn_98, R.id.btn_99, R.id.btn_100
    };

    private TextView[] btn = new TextView[btn_idArray.length];
    Boolean[] bool_btn = new Boolean[btn_idArray.length];
    ImageButton btn_restart_exit,btn_start_pause;
    TextView timerCountdown,total_score,username, label_restart_exit, label_start_pause;
    CountDownTimer count_down_timer;
    int int_random;
    int counter = 100;

    Integer[] Hidden_Number = new Integer[btn_idArray.length];

    Boolean play = false;
    long duration = TimeUnit.SECONDS.toMillis(100);
    boolean pairCheck = false;
    boolean inGame = false;
    int tempPair;
    int Pair1;
    int Pair2;
    String user_Name="";
    int score;
    int multi = 1;
    private String Tag = "MainActivity";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();

        username = (TextView) findViewById(R.id.player_name);
        label_restart_exit = (TextView) findViewById(R.id.label_exit_restart);
        label_start_pause = (TextView) findViewById(R.id.label_start_pause);
        timerCountdown = (TextView) findViewById(R.id.timer);
        total_score = (TextView) findViewById(R.id.score);
        btn_restart_exit = (ImageButton) findViewById(R.id.btn_restart_exit);
        btn_start_pause = (ImageButton) findViewById(R.id.btn_start_pause);

        generateNumbers(rand);
        setBtnDefault();

        username.setText(String.valueOf(Input_Player_Name.getUser_Name()));

        for (int i = 0; i < btn_idArray.length; i++) {
            btn[i] = (TextView) findViewById(btn_idArray[i]);
            int finalI = i;
            btn[i].setOnClickListener(view -> {
                if (!bool_btn[finalI]) {
                    btn[finalI].setText(String.valueOf(Hidden_Number[finalI]));
                    btn[finalI].setBackgroundColor(Color.parseColor("#FF03DAC5"));
                    btn[finalI].setTextColor(Color.parseColor("#FFFFFFFF"));
                    bool_btn[finalI] = true;
                    tempPair++;

                    if(tempPair == 1){
                        Pair1 = finalI;
                    }else{
                        Pair2 = finalI;
                        pairCheck = true;
                        checkIfEqual(total_score);
                    }

                }
            });
        }

        btn_disable(btn);

        btn_start_pause.setOnClickListener(view -> {
           if(play){
               pause();
               btn_disable(btn);

           }else{

               start(timerCountdown);
               btn_Enable(btn);
           }
        });

        btn_restart_exit.setOnClickListener(view -> {
            if(inGame){
                btn_restart(rand);
                inGame = false;
            }else{
                btn_exit();


            }
        });
    }

    private void start(TextView timer){
        play = true;
        inGame = true;
        count_down_timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                duration = l;
                counter--;
                timer.setText(String.valueOf(counter));
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                play = false;
                counter = 10;
                timer.setText("Game Over");
                timer.setTextColor(Color.parseColor("#FF4545"));
                for(int i=0; i< btn_idArray.length; i++){
                    btn[i].setEnabled(false);
                }
            }
        }.start();
        btn_start_pause.setImageResource(R.drawable.ic_bx_pause);
        label_start_pause.setText("Pause");
        btn_restart_exit.setImageResource(R.drawable.ic_bx_refresh);
        label_restart_exit.setText("Restart");
    }

    private void pause(){
        play = false;
        count_down_timer.cancel();
        btn_start_pause.setImageResource(R.drawable.ic_bx_play);
        label_start_pause.setText("Play");
    }

    private void generateNumbers(Random ran_number){
        List<Integer> tempNumRand = Arrays.asList(Hidden_Number);
        int iTemp = 1;
        for (int i = 0; i < 100; i++) {
            Hidden_Number[i] = iTemp;
            if (i % 2 == 1) {
                iTemp++;
            }
        }
        Collections.shuffle(tempNumRand);
        tempNumRand.toArray(Hidden_Number);

        Log.i(Tag, "arraylist rand: "+  Arrays.toString(Hidden_Number));
/*
        for(int i=0; i< btn_idArray.length; i++){
            int_random = ran_number.nextInt(50+1);
            Hidden_Number[i] = int_random;
        }*/

    }
    private void btn_disable(TextView[] btnDis){
        for(int i=0; i< btn_idArray.length; i++){
            btnDis[i].setBackgroundColor(Color.parseColor("#9D68E8"));
            btnDis[i].setEnabled(false);
        }
    }

    private void btn_Enable(TextView[] btnEn){
        for(int i=0; i< btn_idArray.length; i++){
            btnEn[i].setBackgroundColor(Color.parseColor("#FF6200EE"));
            btnEn[i].setEnabled(true);
        }
    }

    private void setBtnDefault(){
        for(int i=0; i< btn_idArray.length; i++){
            bool_btn[i] = false;
        }
    }

    private void btn_exit(){
        finish();
    }

    private void btn_restart(Random random){
        for(int i=0; i< btn_idArray.length; i++){
            btn[i].setText("?");
            btn[i].setBackgroundColor(Color.parseColor("#FF6200EE"));
        }
        timerCountdown.setText("100");
        counter = 100;
        total_score.setText("0");
        count_down_timer.cancel();
        score = 0;
        multi = 1;
        tempPair = 0;
        pairCheck = false;
        timerCountdown.setTextColor(Color.parseColor("#FF000000"));
        btn_start_pause.setImageResource(R.drawable.ic_bx_play);
        label_start_pause.setText("Start");
        btn_restart_exit.setImageResource(R.drawable.ic_bx_exit);
        label_restart_exit.setText("Exit");
        duration = TimeUnit.SECONDS.toMillis(100);
        generateNumbers(random);
        setBtnDefault();
        btn_disable(btn);
    }

    private void checkIfEqual(TextView t_score){
        if(pairCheck && tempPair == 2){
            tempPair = 0;
            pairCheck = false;
            if(Hidden_Number[Pair1].equals(Hidden_Number[Pair2])){
                score += multi * 2;
                t_score.setText(String.valueOf(score));
                    Toast.makeText(getApplicationContext(), "+2 points x " + multi + "\n +5secs", Toast.LENGTH_SHORT).show();
                duration+=5;
                counter +=5;
                count_down_timer.cancel();
                start(timerCountdown);
                multi ++;
            }else {
                bool_btn[Pair1] = false;
                bool_btn[Pair2] = false;
                multi = 1;
                btn[Pair1].setBackgroundColor(Color.parseColor("#FF4545"));
                btn[Pair2].setBackgroundColor(Color.parseColor("#FF4545"));
                final int interval = 300; // 1 Second
                Handler handler = new Handler();
                Runnable runnable = new Runnable(){
                    public void run() {
                        btn[Pair1].setText("?");
                        btn[Pair1].setBackgroundColor(Color.parseColor("#FF6200EE"));
                        btn[Pair2].setText("?");
                        btn[Pair2].setBackgroundColor(Color.parseColor("#FF6200EE"));
                    }
                };
                handler.postAtTime(runnable, System.currentTimeMillis()+interval);
                handler.postDelayed(runnable, interval);
            }
        }
    }
}