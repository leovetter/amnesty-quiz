package com.amnesty.quizzamnesty;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
    }

    public void setButtonSelected(View view) {

        if(view.isSelected()) {
            view.setSelected(false);
        } else {
            view.setSelected(true);
        }
    }

    public void submitQuestion(View view) {

        View armesLegeresButton = this.findViewById(R.id.button_1);
        View charsButton = this.findViewById(R.id.button_2);
        View avionsButton = this.findViewById(R.id.button_3);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.storeResultFirstQuestion(armesLegeresButton.isSelected(), charsButton.isSelected(), avionsButton.isSelected());

        Intent intent = new Intent(this, QuizzOrStatsActivity.class);
        startActivity(intent);
    }
}
