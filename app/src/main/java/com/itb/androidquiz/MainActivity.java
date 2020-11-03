package com.itb.androidquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.itb.androidquiz.QuizViewModel.questionBank;

public class MainActivity extends AppCompatActivity {
    TextView questionText;
    TextView progressText;
    Button trueButton;
    Button falseButton;
    ProgressBar progressBar;
    AlertDialog.Builder finalDialogueBuilder;
    AlertDialog finalDialogue;
 QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizViewModel= new ViewModelProvider(this).get(QuizViewModel.class);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null) quizViewModel.setNumeroPreguntaActual(savedInstanceState.getInt("numeroPreguntaActual"));

        questionText = findViewById(R.id.questionText);
        progressText = findViewById(R.id.progressText);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        progressBar = findViewById(R.id.progressBar);
        finalDialogueBuilder= new AlertDialog.Builder(MainActivity.this);
        finalDialogueBuilder.setTitle(R.string.congratulationText);
        finalDialogueBuilder.setMessage(R.string.nextText);
        finalDialogueBuilder.setNegativeButton(R.string.negativeButtonText,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               cambiarPregunta();

            }});
        finalDialogueBuilder.setPositiveButton(R.string.positiveButtonText,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();


            }});
        finalDialogue = finalDialogueBuilder.create();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprovarResultat(true, quizViewModel.getPreguntaActual());
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprovarResultat(false, quizViewModel.getPreguntaActual());
            }
        });
    }

    private void tornarApreguntaInicial() {
        quizViewModel.setNumeroPreguntaActual(0);
        progressBar.setProgress(progressBar.getMax() / quizViewModel.getQuestionBank().length);
        actualitzarPantalla();
    }

    private void comprovarResultat(boolean resposta, QuestionModel preguntaActual) {
        if (preguntaActual.isAnswer() == resposta) {
            Toast.makeText(MainActivity.this, R.string.onCorrectAnswer, Toast.LENGTH_SHORT).show();


        } else {

            Toast.makeText(MainActivity.this,  getString(R.string.onIncorrectAnswer)+" "+  preguntaActual.isAnswer(), Toast.LENGTH_SHORT).show();
        }
        if (preguntaActual== QuizViewModel.getQuestionBank()[quizViewModel.getQuestionBank().length-1]){
            finalDialogue.show();
        }else {
            cambiarPregunta();
        }
    }

    private void cambiarPregunta() {
        if (quizViewModel.getNumeroPreguntaActual() == questionBank.length - 1) {
            quizViewModel.setNumeroPreguntaActual(-1);
            progressBar.setProgress(0);
        }
        progressBar.incrementProgressBy(progressBar.getMax() / questionBank.length);
        quizViewModel.setNumeroPreguntaActual(quizViewModel.getNumeroPreguntaActual()+1);
          quizViewModel.setPreguntaActual( QuizViewModel.getQuestionBank()[quizViewModel.getNumeroPreguntaActual()]);
        actualitzarPantalla();
    }

    void actualitzarPantalla() {
        questionText.setText(quizViewModel.getPreguntaActual().getQuestionText());
        String text = (quizViewModel.getNumeroPreguntaActual() + 1) + " of " + questionBank.length;
        progressText.setText(text);
    }


}