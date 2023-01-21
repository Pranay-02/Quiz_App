package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question("What is the value of Pi ?", "A", "3.14", "1.71","2.71", "5.98"));
                add(new Question("What is the capital of India?", "B", "Mumbai", "Delhi","Kolkata", "Lucknow"));
                add(new Question("Who is the first prime minister?", "D", "M Gandhi", "SC Bose","Lal B. Shastri", "Jawaharlal Nehru"));
                add(new Question("Who is the biggest music popstar?", "B", "Whindersson Nunes", "Michael Jackson", "Kanye West", "Billie Eilish"));
                add(new Question("Who is regarded as father of engineering?", "A", "M. Visvesvaraya", "CV Raman", "Vikram Sarabai", "Abdul Kalam"));
                add(new Question("Which of the following is not applicable for graph?", "D", "Breadth First Search", "Level Order Traversal", "Depth First Search", "Binary Search"));
                add(new Question("What is similar to  8 bits?", "C", "1 Bit", "16 Bytes", "1 Byte", "1 Mega Byte"));
                add(new Question("Which technology cryptocurrency is based on?", "B", "Hadoop", "BlockChain", "Machine Learning", "Cloud Computing"));
                add(new Question("Founder of Bitcoin?", "B", "Margaret Hamilton", "Satoshi Nakamoto", "Alan Turing", "Gustavo Guanabara"));
                add(new Question("Who founded apple company?", "A", "Steve Jobs", "Mark Zuckerberg", "Alan Turing", "Elon Musk"));
            }
        };
        Collections.shuffle(questions);
        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }
}
