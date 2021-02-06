package com.amnesty.quizzamnesty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        Integer[] counts = db.getResults();

        TextView armesLegeresText = (TextView) this.findViewById(R.id.result_armes_legeres);
        Log.i("COUNT", String.valueOf(counts[0]));
        armesLegeresText.setText(String.valueOf(counts[0]));

        TextView charsText = (TextView) this.findViewById(R.id.result_chars);
        charsText.setText(String.valueOf(counts[1]));

        TextView avionsText = (TextView) this.findViewById(R.id.result_avions);
        avionsText.setText(String.valueOf(counts[2]));
    }
}
