package com.example.finalproject;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.Placeholder;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_GAME_ACTIVITY = 600;

    String hint, word, letter;
    Character c;
    int countNot;
    int[] letter_index;
    int[] gui_index;
    String fragMessage, fragTitle;
    Boolean won;
    Boolean isGameDone = false;
    int finalPoint, finalScore;

    //push database
    private DatabaseReference mDatabase;


    //widgets
    TextView L1,L2,L3,L4,L5,L6,L7,L8,L9,showHint;
    TextView W1, W2, W3, W4, W5, W6;
    Button buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI, buttonJ, buttonK, buttonL, buttonM, buttonN, buttonO, buttonP, buttonQ, buttonR, buttonS, buttonT, buttonU, buttonV, buttonW, buttonX, buttonY, buttonZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countNot = 0;

        //database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //RECEIVE INFO FROM LOGIN ACTIVITY
        //create intent
        Intent intent = getIntent();
        //create vars & receive
        Bundle info = intent.getExtras();
        word = (info.getString("word")).toUpperCase();
        hint = info.getString("hint");

        //CONNECT WORD'S LETTER TEXTVIEWS
        L1 = findViewById(R.id.txt1);
        L2 = findViewById(R.id.txt2);
        L3 = findViewById(R.id.txt3);
        L4 = findViewById(R.id.txt4);
        L5 = findViewById(R.id.txt5);
        L6 = findViewById(R.id.txt6);
        L7 = findViewById(R.id.txt7);
        L8 = findViewById(R.id.txt8);
        L9 = findViewById(R.id.txt9);

        //CONNECT WONG LETTER TEXTVIEWS
        W1 = findViewById(R.id.wrong1);
        W2 = findViewById(R.id.wrong2);
        W3 = findViewById(R.id.wrong3);
        W4 = findViewById(R.id.wrong4);
        W5 = findViewById(R.id.wrong5);
        W6 = findViewById(R.id.wrong6);

        //CONNECT BUTTONS
        buttonA = findViewById(R.id.button_a);
        buttonB = findViewById(R.id.button_b);
        buttonC = findViewById(R.id.button_c);
        buttonD = findViewById(R.id.button_d);
        buttonE = findViewById(R.id.button_e);
        buttonF = findViewById(R.id.button_f);
        buttonG = findViewById(R.id.button_g);
        buttonH = findViewById(R.id.button_h);
        buttonI = findViewById(R.id.button_i);
        buttonJ = findViewById(R.id.button_j);
        buttonK = findViewById(R.id.button_k);
        buttonL = findViewById(R.id.button_l);
        buttonM = findViewById(R.id.button_m);
        buttonN = findViewById(R.id.button_n);
        buttonO = findViewById(R.id.button_o);
        buttonP = findViewById(R.id.button_p);
        buttonQ = findViewById(R.id.button_q);
        buttonR = findViewById(R.id.button_r);
        buttonS = findViewById(R.id.button_s);
        buttonT = findViewById(R.id.button_t);
        buttonU = findViewById(R.id.button_u);
        buttonV = findViewById(R.id.button_v);
        buttonW = findViewById(R.id.button_w);
        buttonX = findViewById(R.id.button_x);
        buttonY = findViewById(R.id.button_y);
        buttonZ = findViewById(R.id.button_z);

        //SET ONCLICK LISTENERS FOR BUTTONS
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonH.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonJ.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonL.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonN.setOnClickListener(this);
        buttonO.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonQ.setOnClickListener(this);
        buttonR.setOnClickListener(this);
        buttonS.setOnClickListener(this);
        buttonT.setOnClickListener(this);
        buttonU.setOnClickListener(this);
        buttonV.setOnClickListener(this);
        buttonW.setOnClickListener(this);
        buttonX.setOnClickListener(this);
        buttonY.setOnClickListener(this);
        buttonZ.setOnClickListener(this);


        //SHOW HINT
        showHint = findViewById(R.id.hintDisp);
        showHint.setText("Hint: " + hint);
        showHint.setVisibility(View.VISIBLE);

        //ONLY REVEAL LENGTH OF WORD IN TEXTVIEWS
        final int length = word.length();
        switch (length) {
            case 1:
                L1.setVisibility(View.VISIBLE);
                break;
            case 2: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                break;
            }
            case 4: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                break;
            }
            case 5: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                break;
            }
            case 6: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                break;
            }
            case 7: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                break;
            }
            case 8: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                L8.setVisibility(View.VISIBLE);
                break;
            }
            case 9: {
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                L8.setVisibility(View.VISIBLE);
                L9.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, finalScore);
        
        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public void onClick(View view) {

        letter = "";

        switch (view.getId()) {
            case R.id.button_a:
                letter = "A";
                buttonA.setEnabled(false);
                break;
            case R.id.button_b:
                letter = "B";
                buttonB.setEnabled(false);
                break;
            case R.id.button_c:
                letter = "C";
                buttonC.setEnabled(false);
                break;
            case R.id.button_d:
                letter = "D";
                buttonD.setEnabled(false);
                break;
            case R.id.button_e:
                letter = "E";
                buttonE.setEnabled(false);
                break;
            case R.id.button_f:
                letter = "F";
                buttonF.setEnabled(false);
                break;
            case R.id.button_g:
                letter = "G";
                buttonG.setEnabled(false);
                break;
            case R.id.button_h:
                letter = "H";
                buttonH.setEnabled(false);
                break;
            case R.id.button_i:
                letter = "I";
                buttonI.setEnabled(false);
                break;
            case R.id.button_j:
                letter = "J";
                buttonJ.setEnabled(false);
                break;
            case R.id.button_k:
                letter = "K";
                buttonK.setEnabled(false);
                break;
            case R.id.button_l:
                letter = "L";
                buttonL.setEnabled(false);
                break;
            case R.id.button_m:
                letter = "M";
                buttonM.setEnabled(false);
                break;
            case R.id.button_n:
                letter = "N";
                buttonN.setEnabled(false);
                break;
            case R.id.button_o:
                letter = "O";
                buttonO.setEnabled(false);
                break;
            case R.id.button_p:
                letter = "P";
                buttonP.setEnabled(false);
                break;
            case R.id.button_q:
                letter = "Q";
                buttonQ.setEnabled(false);
                break;
            case R.id.button_r:
                letter = "R";
                buttonR.setEnabled(false);
                break;
            case R.id.button_s:
                letter = "S";
                buttonS.setEnabled(false);
                break;
            case R.id.button_t:
                letter = "T";
                buttonT.setEnabled(false);
                break;
            case R.id.button_u:
                letter = "U";
                buttonU.setEnabled(false);
                break;
            case R.id.button_v:
                letter = "V";
                buttonV.setEnabled(false);
                break;
            case R.id.button_w:
                letter = "W";
                buttonW.setEnabled(false);
                break;
            case R.id.button_x:
                letter = "X";
                buttonX.setEnabled(false);
                break;
            case R.id.button_y:
                letter = "Y";
                buttonY.setEnabled(false);
                break;
            case R.id.button_z:
                letter = "Z";
                buttonZ.setEnabled(false);
                break;
            default:
                break;
        }

        c = letter.charAt(0);

        if (containsLetter(word, letter)) {

            letter_index = getIndexesArray(c, word);
            gui_index = getIndexesArray(c, word);
//            String testi="";
//            for(int i=0; i < letter_index.length; i++){
//                testi = testi + String.valueOf(letter_index[i]);
//            }
//            Toast.makeText(getApplicationContext(), testi, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < letter_index.length; i++) {

                word = replaceLetter(letter_index, word);

                if (letter_index[i] == 1) {
                    switch (i) {
                        case 0: {
                            L1.setText(letter);
                            break;
                        }
                        case 1: {
                            L2.setText(letter);
                            break;
                        }
                        case 2: {
                            L3.setText(letter);
                            break;
                        }
                        case 3: {
                            L4.setText(letter);
                            break;
                        }
                        case 4: {
                            L5.setText(letter);
                            break;
                        }
                        case 5: {
                            L6.setText(letter);
                            break;
                        }
                        case 6: {
                            L7.setText(letter);
                            break;
                        }
                        case 7: {
                            L8.setText(letter);
                            break;
                        }
                        case 8: {
                            L9.setText(letter);
                            break;
                        }
                        default:
                            break;
                    }
                }
                if (wordComplete(word)) {
                    fragTitle = "YOU ESCAPED!!!";
                    fragMessage = "Live to see another day #BLESSUP";
                    won = true;
                    isGameDone = true;
                    finalPoint = 1;
                    //launch win fragment!
                    ResultFragment dFrag = new ResultFragment();
                    Bundle info = new Bundle();
                    info.putString("title",fragTitle);
                    info.putString("message",fragMessage);
                    info.putBoolean("won",won);
                    dFrag.setArguments(info);
                    dFrag.show((GameActivity.this).getSupportFragmentManager(),"Result Dialog");

                }
            }
        } else {
            switch (countNot) {
                case 0: {
                    W1.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                case 1: {
                    W2.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                case 2: {
                    W3.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                case 3: {
                    W4.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                case 4: {
                    W5.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                case 5: {
                    W6.setText(letter);
                    countNot = countNot + 1;
                    break;
                }
                default: {
//                    launch lose fragment!
                    fragTitle = "GG R.I.P.";
                    fragMessage = "It was nice knowing you. JK, new phone who 'dis?";
                    won = false;
                    isGameDone = true;
                    finalPoint = -1;
                    //launch lose fragment!
                    ResultFragment dFrag = new ResultFragment();
                    Bundle info = new Bundle();
                    info.putString("title",fragTitle);
                    info.putString("message",fragMessage);
                    info.putBoolean("won",won);
                    dFrag.setArguments(info);
                    dFrag.show((GameActivity.this).getSupportFragmentManager(),"Result Dialog");
                    break;
                }
            }
        }
    }

    public Boolean wordComplete(String word){
        if (word.matches("[0-9]+")){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean containsLetter(String word, String letter){
        if (word.contains(letter)){
            return true;
        }
        else{
            return false;
        }
    }

    //output letters in the word with a 1 in an array and a 0 if not the letter (e.g. the a in car = 010)
    public int[] getIndexesArray(Character c, String word){
        int[] storage = new int[word.length()];

        for (int i = 0; i < word.length(); i++){
            Character cw = word.charAt(i);

            if(cw.equals(c)){
                storage[i] = 1;
            }
            else{
                storage[i] = 0;
            }
        }
        return storage;
    }


    public String replaceLetter(int[] indexList, String word){
//        Toast.makeText(getApplicationContext(), "i made it", Toast.LENGTH_SHORT).show();
        StringBuffer sbf = new StringBuffer(word);
        for(int i=0; i<indexList.length;i++){
            if(indexList[i]==0){
//                Toast.makeText(getApplicationContext(), "last letter", Toast.LENGTH_SHORT).show();
            }
            else if(indexList[i]==1){
                sbf.replace(i,i+1,"9");
                word = sbf.toString();
            }
        }
        return word;
    }

    public void clickExitGame(View view) {
        if(!isGameDone){
            Toast.makeText(getApplicationContext(), "game incomplete", Toast.LENGTH_SHORT).show();
        }
        else if (isGameDone){
            notificationDialog();
            Intent intent  = new Intent(GameActivity.this, LastActivity.class);
            startActivity(intent);

        }

    }
    private void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Tutorialspoint")
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("User Score Updated")
                .setContentText("Account: QISHUZHANG@GMAIL.COM - 13")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
    }
}
