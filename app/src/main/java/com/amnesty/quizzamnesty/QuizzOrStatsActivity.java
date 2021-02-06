package com.amnesty.quizzamnesty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizzOrStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_or_stats);
    }

    public void goQuizz(View view) {

        Intent intent = new Intent(this, FirstQuestionActivity.class);
        startActivity(intent);
    }

    public void goStats(View view) {

        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
}
