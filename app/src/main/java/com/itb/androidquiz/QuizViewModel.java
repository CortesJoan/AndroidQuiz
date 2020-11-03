package com.itb.androidquiz;

import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    public int getNumeroPreguntaActual() {
        return numeroPreguntaActual;
    }

    public void setNumeroPreguntaActual(int numeroPreguntaActual) {
        this.numeroPreguntaActual = numeroPreguntaActual;
    }

    public QuestionModel getPreguntaActual() {
        return preguntaActual;
    }

    public void setPreguntaActual(QuestionModel preguntaActual) {
        this.preguntaActual = preguntaActual;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static QuestionModel[] getQuestionBank() {
        return questionBank;
    }

    int numeroPreguntaActual = 0;
    QuestionModel preguntaActual = questionBank[numeroPreguntaActual];
    ViewModel  viewModel;
    static final QuestionModel[] questionBank = {
            new QuestionModel(R.string.q0, false),
            new QuestionModel(R.string.q1, false),
            new QuestionModel(R.string.q2, true),
            new QuestionModel(R.string.q3, true),
            new QuestionModel(R.string.q4, true),
            new QuestionModel(R.string.q5, true),
            new QuestionModel(R.string.q6, false),
            new QuestionModel(R.string.q7, false),
            new QuestionModel(R.string.q8, true),
            new QuestionModel(R.string.q9, true),


    };

}
