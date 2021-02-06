package com.amnesty.quizzamnesty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view) {

        Intent intent = new Intent(this, QuizzOrStatsActivity.class);
        startActivity(intent);
    }

}
