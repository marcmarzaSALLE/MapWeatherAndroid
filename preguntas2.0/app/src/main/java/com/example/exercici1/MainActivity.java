package com.example.exercici1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Declaracions del TextView i del Button
    private TextView textView, txtViewTitle;
    private ArrayList<Question> questions;
    int sizeArray = 0, counterAnswersCorrect = 0, counterAnswersIncorrect = 0, counterQuestion=1;
    private Button btnNo, btnYes;

    private Random randomGenerator;
    private boolean status=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData();

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        txtViewTitle = findViewById(R.id.textViewTitle);
        randomGenerator = new Random();
        do{
            int index = randomGenerator.nextInt(questions.size());
            if(questions.get(index).isStatus()){
                textView.setText(questions.get(index).getQuestion());
                questions.get(index).setStatus(true);
                System.out.println("Random Element is :"
                        + questions.get(index).getQuestion());
                status = true;
            }


        } while (!status);


        txtViewTitle.setText(getResources().getString(R.string.titleQuestion) + " " + counterQuestion );

        btnNo = (Button) findViewById(R.id.button1);
        btnNo.setFocusable(true);
        btnNo.setFocusableInTouchMode(true);///add this line
        btnNo.requestFocus();
        btnYes = (Button) findViewById(R.id.button2);

        btnNo.setOnClickListener(v -> {
            if (!questions.get(sizeArray).isAnswer()) {
                counterAnswersCorrect++;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.trueMessage), Toast.LENGTH_SHORT).show();

            } else {
                counterAnswersIncorrect++;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.falseMessage), Toast.LENGTH_SHORT).show();

            }
            sizeArray++;
            counterQuestion++;
            status = false;
            while (!status){
                int index = (int)(Math.random() * questions.size());
                if(questions.get(index).isStatus()){
                    textView.setText(questions.get(index).getQuestion());
                    status = true;
                    questions.get(index).setStatus(true);
                }
            }

            if (sizeArray < questions.size()) {
                textView.setText(questions.get(sizeArray).getQuestion());
            }

            if (sizeArray == questions.size()) {
                Result result = new Result(getResources().getString(R.string.messageAnswerCorrect), getResources().getString(R.string.messageAnswerIncorrect), counterAnswersCorrect, counterAnswersIncorrect);
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("numberQuestion", getResources().getString(R.string.numberOfQuestion) + " " + questions.size());
                intent.putExtra("result", result);
                startActivity(intent);

            }
        });

        btnYes.setOnClickListener(v -> {
            if (questions.get(sizeArray).isAnswer()) {
                counterAnswersCorrect++;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.trueMessage), Toast.LENGTH_SHORT).show();

            } else {
                counterAnswersIncorrect++;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.falseMessage), Toast.LENGTH_SHORT).show();

            }
            sizeArray++;
            counterQuestion++;
            status = false;
            while (!status){
                int index = (int)(Math.random() * questions.size());
                if(questions.get(index).isStatus()){
                    textView.setText(questions.get(index).getQuestion());
                    status = true;
                    questions.get(index).setStatus(true);
                }
            }

            if (sizeArray < questions.size()) {
                textView.setText(questions.get(sizeArray).getQuestion());

            }

            if (sizeArray == questions.size()) {
                Result result = new Result(getResources().getString(R.string.messageAnswerCorrect), getResources().getString(R.string.messageAnswerIncorrect), counterAnswersCorrect, counterAnswersIncorrect);
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("numberQuestion", getResources().getString(R.string.numberOfQuestion) + " " + questions.size());
                intent.putExtra("result", result);
                startActivity(intent);

            }
        });


    }

    private void addData() {
        questions = new ArrayList<>();
        String[] arrayQuestion = getResources().getStringArray(R.array.question);
        String[] arrayAnswers = getResources().getStringArray(R.array.answers);
        Question question;
        for (int i = 0; i < arrayQuestion.length; i++) {
            if (arrayAnswers[i].equalsIgnoreCase("si")) {
                question = new Question(arrayQuestion[i], true);
            } else {
                question = new Question(arrayQuestion[i], false);
            }
            questions.add(question);
        }
    }

    private void settingButtons() {

    }

    /**
    private void showQuestion(){
        boolean status = false;
        while (status){
            int index = randomGenerator.nextInt(questions.size());
            if(!questions.get(index).isStatus()){
                textView.setText(questions.get(index).getQuestion());
                status = true;
                questions.get(index).setStatus(true);
            }
        }
    }*/
}