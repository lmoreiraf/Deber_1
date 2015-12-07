package com.lsi.android.quizapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private Button btnTrue;
    private Button btnFalse;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private TextView txtQuestion;
    private int currentIntdex=0;

    private Question[] questions={
            new Question(R.string.question_capital,false),
            new Question(R.string.question_guayaquil,true),
            new Question(R.string.question_manta,true),
            new Question(R.string.question_volcan,false)
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btnTrue=(Button)findViewById(R.id.true_button);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        btnFalse=(Button)findViewById(R.id.false_button);
        btnFalse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkAnswer (false);
        }
    });

        btnNext=(ImageButton) findViewById(R.id.next_button);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIntdex = (currentIntdex+1)%questions.length;
                updateQuestion();
            }
        });

        btnPrevious = (ImageButton) findViewById(R.id.previous_button);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIntdex = (currentIntdex-1)%questions.length;
                updateQuestion();
            }
        });

        txtQuestion = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();
    }

     private void  updateQuestion(){
         int question=questions[currentIntdex].getQuestionId();
         txtQuestion.setText(question);
     }
    private void  checkAnswer(boolean press){
        boolean isTrue=questions[currentIntdex].isTrue();
        int messageId=0;
        if (press==isTrue){
            messageId=R.string.correct_toast;
        }else{
            messageId=R.string.incorrect_toast;
        }
        Toast.makeText(this,messageId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
