package com.example.tp_baseline.soundiry;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RecordActivity extends AppCompatActivity {

    private Button btnPlay, btnRecord, btnStop, btnSave;
    private MediaRecorder myAudioRecorder;
    private String outPutFile;
    private EditText etRecordName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setTitle("Write a Soundiry");

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnSave = (Button) findViewById(R.id.btnSave);
        etRecordName = (EditText) findViewById(R.id.etRecordName);
        btnPlay.setEnabled(false);
        btnStop.setEnabled(false);
        btnSave.setEnabled(false);


        //create a folder to store record audio
        String folder_main = "Soundiry";
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }


        //assign the app to store the record in folder which already created
        outPutFile = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Soundiry/" + getTime() + ".3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outPutFile);


        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {

                } catch (IOException ise) {

                }

                btnRecord.setEnabled(false);
                btnStop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                btnRecord.setEnabled(false);
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);
                btnSave.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio record successfully", Toast.LENGTH_LONG).show();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaPlayer mediaPlayer = new MediaPlayer();

                btnRecord.setEnabled(false);

                try {
                    mediaPlayer.setDataSource(outPutFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordActivity.this, HomePageActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Save Soundiry successfully", Toast.LENGTH_LONG).show();
            }
        });

    }

    //get the current time as name of the record
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        return currentDateandTime;
    }


}
