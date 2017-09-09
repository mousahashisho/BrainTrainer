package com.example.mousa.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btnPlayAgain;
    private TextView txtEqu;
    private TextView txtResult;
    private TextView txtPionts;
    private TextView txtTimer;
    private ArrayList<Integer> answers;
    private int locationOfCorrectAnswer;
    private int incorrectAnswer;
    private int score = 0;
    private int numberOfQuestions = 0;


    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        txtTimer.setText("30s");
        txtPionts.setText("0/0");
        txtResult.setText("");
        btnPlayAgain.setVisibility(INVISIBLE);
        btnStart.setVisibility(INVISIBLE);
        showButtons();
        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                hideButtons();
                txtResult.setText("Done , Your score : " + score + "/" + numberOfQuestions);
                txtTimer.setText("0s");
                btnPlayAgain.setVisibility(VISIBLE);


            }
        }.start();


    }

    public void generateQuestion() {
        Random random = new Random();
        answers = new ArrayList<>();

        int a = random.nextInt(31);
        int b = random.nextInt(31);
        locationOfCorrectAnswer = random.nextInt(4);

        txtEqu.setText(a + " + " + b);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(51);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(51);
                }
                answers.add(incorrectAnswer);
            }
        }

        btn0.setText(answers.get(0).toString());
        btn1.setText(answers.get(1).toString());
        btn2.setText(answers.get(2).toString());
        btn3.setText(answers.get(3).toString());
    }


    public void chooseAnswer(View view) {
        int id = Integer.parseInt(view.getTag().toString());
        if (id == locationOfCorrectAnswer) {
            score++;
            txtResult.setText("Correct");
        } else {
            txtResult.setText("Wrong");
        }
        numberOfQuestions++;
        txtPionts.setText(score + "/" + numberOfQuestions);
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnStart = (Button) findViewById(R.id.btnStart);
        btn0 = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        txtEqu = (TextView) findViewById(R.id.textView);
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtPionts = (TextView) findViewById(R.id.txtPionts);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        hideButtons();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain(view);

            }
        });

//        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playAgain();
//            }
//        });


    }

    public void hideButtons() {
        btn0.setVisibility(INVISIBLE);
        btn1.setVisibility(INVISIBLE);
        btn2.setVisibility(INVISIBLE);
        btn3.setVisibility(INVISIBLE);
    }

    public void showButtons() {
        btn0.setVisibility(VISIBLE);
        btn1.setVisibility(VISIBLE);
        btn2.setVisibility(VISIBLE);
        btn3.setVisibility(VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
