package com.belajar.calistung.belajarcalistung.audio;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.belajar.calistung.belajarcalistung.LatihanHuruf;
import com.belajar.calistung.belajarcalistung.MainActivity;
import com.belajar.calistung.belajarcalistung.R;
import com.belajar.calistung.belajarcalistung.result.Result;
import com.belajar.calistung.belajarcalistung.test.Level;

public class Audio extends Activity {
    private int currentQuestion;
    private String[] questions;
    private String[] answers;
    private String[] ans1;
    private String[] ans2;
    private String[] ans3;
    private String[] ans4;
    private Button answerButton;
    private Button questionButton;
    private TextView questionView;
    private TextView answerView;
    public int correct;
    //public  String[] res;

    public static String msg = "SEND MESSAGE";
    //public static String msg2 = "outtt";


    CheckBox opt1, opt2, opt3, opt4;
    String r;
    int point = 0;
    int selanjutnya = 0;
    public Button image;
    public ImageButton stop;

    int[] songs;
    MediaPlayer mediaPlayer;
    int current_index = 0;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.audio);

        answerButton = (Button) findViewById(R.id.submit);
        questionButton = (Button) findViewById(R.id.next);
        questionView = (TextView) findViewById(R.id.ques);
        answerView = (TextView) findViewById(R.id.result);

        opt1 = (CheckBox) findViewById(R.id.checkBox1);
        opt1.setOnCheckedChangeListener(listener);

        opt2 = (CheckBox) findViewById(R.id.checkBox2);
        opt2.setOnCheckedChangeListener(listener);

        opt3 = (CheckBox) findViewById(R.id.checkBox3);
        opt3.setOnCheckedChangeListener(listener);

        opt4 = (CheckBox) findViewById(R.id.checkBox4);
        opt4.setOnCheckedChangeListener(listener);


        CheckBox checkBox1, checkBox2, checkBox3, checkBox4;

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        final float scale1 = this.getResources().getDisplayMetrics().density;
        checkBox1.setPadding(checkBox1.getPaddingLeft() + (int) (10.0f * scale1 + 0.5f),
                checkBox1.getPaddingTop(),
                checkBox1.getPaddingRight(),
                checkBox1.getPaddingBottom());
        final float scale2 = this.getResources().getDisplayMetrics().density;
        checkBox2.setPadding(checkBox2.getPaddingLeft() + (int) (10.0f * scale2 + 0.5f),
                checkBox2.getPaddingTop(),
                checkBox2.getPaddingRight(),
                checkBox2.getPaddingBottom());
        final float scale3 = this.getResources().getDisplayMetrics().density;
        checkBox3.setPadding(checkBox3.getPaddingLeft() + (int) (10.0f * scale3 + 0.5f),
                checkBox3.getPaddingTop(),
                checkBox3.getPaddingRight(),
                checkBox3.getPaddingBottom());
        final float scale4 = this.getResources().getDisplayMetrics().density;
        checkBox4.setPadding(checkBox4.getPaddingLeft() + (int) (10.0f * scale4 + 0.5f),
                checkBox4.getPaddingTop(),
                checkBox4.getPaddingRight(),
                checkBox4.getPaddingBottom());


        init();

    }


    public OnCheckedChangeListener listener = new OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
            if (isChecked) {
                switch (arg0.getId()) {
                    case R.id.checkBox1:
                        opt1.setChecked(true);
                        opt2.setChecked(false);
                        opt3.setChecked(false);
                        opt4.setChecked(false);
                        break;
                    case R.id.checkBox2:
                        opt2.setChecked(true);
                        opt1.setChecked(false);
                        opt3.setChecked(false);
                        opt4.setChecked(false);
                        break;
                    case R.id.checkBox3:
                        opt3.setChecked(true);
                        opt1.setChecked(false);
                        opt2.setChecked(false);
                        opt4.setChecked(false);
                        break;
                    case R.id.checkBox4:
                        opt4.setChecked(true);
                        opt1.setChecked(false);
                        opt2.setChecked(false);
                        opt3.setChecked(false);
                        break;
                }
            }

        }
    };


    ////////////////////Press Lower Back Button
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Audio.this);
        builder.setMessage("Yakin ingin keluar?").setCancelable(
                false).setPositiveButton("Keluar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mediaPlayer.stop();
                        Audio.this.finish();
                        Intent intent = new Intent(Audio.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


////////////////////Press Lower Back Button		


    /////////////////////set back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case (android.R.id.home):
                Intent intent = new Intent(this, Level.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                mediaPlayer.stop();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//set back button
/////////////////////set back button		 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beginner, menu);
        return true;
    }


    public void init() {
        songs = new int[]{
                R.raw.nada_a,
                R.raw.nada_b,
                R.raw.nada_c,
                R.raw.nada_d,
                R.raw.nada_e,
                R.raw.nada_f,
                R.raw.nada_g,
                R.raw.nada_h,
                R.raw.nada_i
        };


        questions = new String[]{
                "BANTUAN: ABJAD KE 1",
                "BANTUAN: ABJAD KE 2",
                "BANTUAN: ABJAD KE 3",
                "BANTUAN: ABJAD KE 4",
                "BANTUAN: ABJAD KE 5",
                "BANTUAN: ABJAD KE 6",
                "BANTUAN: ABJAD KE 7",
                "BANTUAN: ABJAD KE 8",
                "BANTUAN: ABJAD KE 9",
        };

        answers = new String[]{
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
        };

        ans1 = new String[]{"O", "B", "C", "J", "T", "R", "K", "G", "R"};
        ans2 = new String[]{"X", "F", "P", "G", "X", "Q", "H", "F", "T"};
        ans3 = new String[]{"D", "G", "O", "T", "E", "K", "E", "T", "O"};
        ans4 = new String[]{"A", "R", "H", "D", "S", "F", "G", "H", "I"};

        currentQuestion = 0;


        opt1.setText(ans1[currentQuestion]);
        opt2.setText(ans2[currentQuestion]);
        opt3.setText(ans3[currentQuestion]);
        opt4.setText(ans4[currentQuestion]);


        mediaPlayer = MediaPlayer.create(this, songs[0]);
        final Button play_button = (Button) this.findViewById(R.id.play);
        play_button.setBackgroundResource(R.drawable.play);
        play_button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // If the music is playing
                if (mediaPlayer.isPlaying() == true) {
                    // Pause the music player
                    play_button.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                }
                // If it's not playing
                else {
                    // Resume the music player
                    play_button.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer1) {
                play_button.setBackgroundResource(R.drawable.play);
                mediaPlayer.pause();
            }
        });


        questionView.setText(questions[currentQuestion]);
        answerView.setText("");


        Lable1:
        {
            answerButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (opt1.isChecked() || opt2.isChecked() || opt3.isChecked() || opt4.isChecked()) {
                        mediaPlayer.stop();
                        play_button.setBackgroundResource(R.drawable.play);

                        if (opt1.isChecked()) {

                            opt2.setChecked(false);
                            opt3.setChecked(false);
                            opt4.setChecked(false);
                            r = opt1.getText().toString();
                        }
                        if (opt2.isChecked()) {

                            opt1.setChecked(false);
                            opt3.setChecked(false);
                            opt4.setChecked(false);
                            r = opt2.getText().toString();
                        }
                        if (opt3.isChecked()) {

                            opt2.setChecked(false);
                            opt1.setChecked(false);
                            opt4.setChecked(false);
                            r = opt3.getText().toString();
                        }
                        if (opt4.isChecked()) {

                            opt2.setChecked(false);
                            opt3.setChecked(false);
                            opt1.setChecked(false);
                            r = opt4.getText().toString();
                        }
                        checkAnswer(r);
                        opt1.setChecked(false);
                        opt2.setChecked(false);
                        opt3.setChecked(false);
                        opt4.setChecked(false);
                        showQuestion();

                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Pilih Jawaban Terlebih dahulu", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mediaPlayer1) {
                                play_button.setBackgroundResource(R.drawable.play);
                                mediaPlayer.pause();
                            }
                        });


                    }


                }
            });
            break Lable1;
        }


        questionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                play_button.setBackgroundResource(R.drawable.play);
                mediaPlayer.stop();

                opt1.setChecked(false);
                opt2.setChecked(false);
                opt3.setChecked(false);
                opt4.setChecked(false);

                showQuestion();
            }
        });
    }

		
		/*
        * This method
		* 1: increment currentQuestion index
		* 2: check if it is equal to the size of the array and rest
		if necessary
		* 3: display the question at currentQuestion index in
		question view
		* 4: Empty answer view
		*/

    public void showQuestion() {
        currentQuestion++;
        if (currentQuestion == (questions.length)) {
            correct = point;
            String ms = Integer.toString(correct);
            msg = ms;
            currentQuestion = 0;
            point = 0;


            Bundle b = new Bundle();
            b.putStringArray("mes", new String[]{answers[0], answers[1]});

            Intent in = new Intent(Audio.this, Result.class);
            in.putExtra("string", msg);
            in.putExtras(b);
            startActivity(in);
            finish();
        }


        mediaPlayer = MediaPlayer.create(this, songs[currentQuestion]);
        final Button play_button = (Button) this.findViewById(R.id.play);

        play_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // If the music is playing
                if (mediaPlayer.isPlaying() == true) {
                    // Pause the music player
                    play_button.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                }
                // If it's not playing
                else {
                    // Resume the music player
                    play_button.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer1) {
                play_button.setBackgroundResource(R.drawable.play);
                mediaPlayer.pause();
            }
        });


        questionView.setText(questions[currentQuestion]);
        answerView.setText("");
        opt1.setText(ans1[currentQuestion]);
        opt2.setText(ans2[currentQuestion]);
        opt3.setText(ans3[currentQuestion]);
        opt4.setText(ans4[currentQuestion]);

    }

    /*
    * This method return true if the answer equals to correct
    answer
    * (Ignoring case)
    */
    public boolean isCorrect(String r) {
        return (r.equalsIgnoreCase(answers[currentQuestion]));
    }


    /* this method :
    * 1: Read the text ( answer) from the answerTextEdit
    * 2: call the isCorrect method
    * 3: display the appropriate message.
    */
    public void checkAnswer(String r) {

        if (isCorrect(r)) {
            point++;
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "BENAR...!", Toast.LENGTH_SHORT);

            toast2.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast2.show();

        } else {
            selanjutnya++;


            Toast toast3 = Toast.makeText(getApplicationContext(),
                    "Jawaban yang benar adalah : " + answers[currentQuestion], Toast.LENGTH_SHORT);
            toast3.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            //toast3.setView(incorrect);
            toast3.show();
        }
    }
}
