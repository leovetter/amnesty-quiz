package com.amnesty.quizzamnesty;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.amnesty.quizzamnesty.views.SubmitArrowView;

public class FirstQuestionActivity extends AppCompatActivity {

    Spinner spinnerRussia = null;
    Spinner spinnerUsa = null;
    Spinner spinnerFrance = null;
    private boolean validated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);

        this.spinnerRussia = findViewById(R.id.spinnerRussia);
        this.spinnerUsa = findViewById(R.id.spinnerUsa);
        this.spinnerFrance = findViewById(R.id.spinnerFrance);

        //create a list of items for the spinner.
        String[] items = new String[] {"État Unis", "Royaume-Uni", "Arabie Saoudite", "Russie", "Chine", "France", "Allemagne"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        this.spinnerRussia.setAdapter(adapter);
        this.spinnerUsa.setAdapter(adapter);
        this.spinnerFrance.setAdapter(adapter);
    }

    public void validate(View view) {

        SubmitArrowView submitArrowview = (SubmitArrowView) view;
        if(!this.validated) {
            submitArrowview.invalidate();

            if (!this.spinnerRussia.getSelectedItem().toString().equals("Russie")) {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorRussia));
                border.setStroke(4, getResources().getColor(R.color.colorRed));
                this.spinnerRussia.setBackground(border);

                TextView responseRussia = (TextView) findViewById(R.id.russiaResponse);
                responseRussia.setText("Russie");
            } else {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorRussia));
                border.setStroke(4, getResources().getColor(R.color.colorSubmit));
                this.spinnerRussia.setBackground(border);
            }

            if (!this.spinnerUsa.getSelectedItem().toString().equals("État Unis")) {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorUsa));
                border.setStroke(4, getResources().getColor(R.color.colorRed));
                this.spinnerUsa.setBackground(border);

                TextView responseUsa = (TextView) findViewById(R.id.usaResponse);
                responseUsa.setText("État Unis");
            } else {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorUsa));
                border.setStroke(4, getResources().getColor(R.color.colorSubmit));
                this.spinnerUsa.setBackground(border);
            }

            if (!this.spinnerFrance.getSelectedItem().toString().equals("France")) {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorFrance));
                border.setStroke(4, getResources().getColor(R.color.colorRed));
                this.spinnerFrance.setBackground(border);

                TextView responseFrance = (TextView) findViewById(R.id.franceResponse);
                responseFrance.setText("France");
            } else {

                GradientDrawable border = new GradientDrawable();
                border.setColor(getResources().getColor(R.color.colorFrance));
                border.setStroke(4, getResources().getColor(R.color.colorSubmit));
                this.spinnerFrance.setBackground(border);
            }
        }
    }

    public void setButtonSelected(View view) {

        if(view.isSelected()) {
            view.setSelected(false);
        } else {
            view.setSelected(true);
        }
    }

    public void submitQuestion(View view) {

        /*View armesLegeresButton = this.findViewById(R.id.button_1);
        View charsButton = this.findViewById(R.id.button_2);
        View avionsButton = this.findViewById(R.id.button_3);*/

        /*MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.storeResultFirstQuestion(armesLegeresButton.isSelected(), charsButton.isSelected(), avionsButton.isSelected());

        Intent intent = new Intent(this, QuizzOrStatsActivity.class);
        startActivity(intent);*/
    }
}
