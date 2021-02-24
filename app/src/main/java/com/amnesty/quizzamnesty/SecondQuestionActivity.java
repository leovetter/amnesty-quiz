package com.amnesty.quizzamnesty;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amnesty.quizzamnesty.views.PersonView;
import com.amnesty.quizzamnesty.views.SubmitArrowView;


import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class SecondQuestionActivity extends AppCompatActivity {

    private boolean validated = false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        Context  appContext = getApplicationContext();
        float maxHeight = appContext.getResources().getDimension(R.dimen.person_height);

        PersonView person1 = findViewById(R.id.person1);
        PersonView person2 = findViewById(R.id.person2);
        PersonView person3 = findViewById(R.id.person3);
        PersonView person4 = findViewById(R.id.person4);
        PersonView person5 = findViewById(R.id.person5);
        PersonView person6 = findViewById(R.id.person6);
        PersonView person7 = findViewById(R.id.person7);
        PersonView person8 = findViewById(R.id.person8);
        PersonView person9 = findViewById(R.id.person9);
        PersonView person10 = findViewById(R.id.person10);

        List<BehaviorSubject<Float>> listSubjects = Arrays.asList(person1.heightSubject, person2.heightSubject, person3.heightSubject, person4.heightSubject,
                person5.heightSubject, person6.heightSubject, person7.heightSubject, person8.heightSubject,
                person9.heightSubject, person10.heightSubject);

        Observable.combineLatest(listSubjects, args -> {
            return 10*maxHeight - ( maxHeight - (float) args[0]) - ( maxHeight - (float) args[1]) - ( maxHeight - (float) args[2]) - (maxHeight - (float) args[3]) - (maxHeight - (float) args[4]) - ( maxHeight - (float) args[5]) - ( maxHeight - (float) args[6]) - ( maxHeight - (float) args[7]) - ( maxHeight - (float) args[8]) - ( maxHeight - (float) args[9]);
        }).subscribe(allHeight -> {
            TextView percentUser1 = findViewById(R.id.percent_user1);
            Integer percent = Math.round((1 - allHeight / ( maxHeight * 10)) * 100);
            percentUser1.setText( percent + "%");
        });

        PersonView person11 = findViewById(R.id.person11);
        PersonView person12 = findViewById(R.id.person12);
        PersonView person13 = findViewById(R.id.person13);
        PersonView person14 = findViewById(R.id.person14);
        PersonView person15 = findViewById(R.id.person15);
        PersonView person16 = findViewById(R.id.person16);
        PersonView person17 = findViewById(R.id.person17);
        PersonView person18 = findViewById(R.id.person18);
        PersonView person19 = findViewById(R.id.person19);
        PersonView person20 = findViewById(R.id.person20);

        List<BehaviorSubject<Float>> listSubjects2 = Arrays.asList(person11.heightSubject, person12.heightSubject, person13.heightSubject, person14.heightSubject,
                person15.heightSubject, person16.heightSubject, person17.heightSubject, person18.heightSubject,
                person19.heightSubject, person20.heightSubject);

        Observable.combineLatest(listSubjects2, args -> {
            return  10*maxHeight - ( maxHeight - (float) args[0]) - ( maxHeight - (float) args[1]) - ( maxHeight - (float) args[2]) - (maxHeight - (float) args[3]) - (maxHeight - (float) args[4]) - ( maxHeight - (float) args[5]) - ( maxHeight - (float) args[6]) - ( maxHeight - (float) args[7]) - ( maxHeight - (float) args[8]) - ( maxHeight - (float) args[9]);
        }).subscribe(allHeight -> {
            TextView percentUser2 = findViewById(R.id.percent_user2);
            Integer percent = Math.round((1 - allHeight / ( maxHeight * 10)) * 100);
            percentUser2.setText( percent + "%");
        });
    }

    public void validate(View view) {

        SubmitArrowView submitArrowview = (SubmitArrowView) view;
        if (!this.validated) {
            submitArrowview.invalidate();
            this.validated = true;

            TextView corPercent1 = findViewById(R.id.corection_percent_user1);
            corPercent1.setText("10%");

            TextView corPercent2 = findViewById(R.id.corection_percent_user2);
            corPercent2.setText("80%");


            PersonView person1 = (PersonView) findViewById(R.id.person1);
            person1.setValidate(true);
            person1.invalidate();

            PersonView person11 = (PersonView) findViewById(R.id.person11);
            person11.setValidate(true);
            person11.invalidate();

            PersonView person12 = (PersonView) findViewById(R.id.person12);
            person12.setValidate(true);
            person12.invalidate();

            PersonView person13 = (PersonView) findViewById(R.id.person13);
            person13.setValidate(true);
            person13.invalidate();

            PersonView person14 = (PersonView) findViewById(R.id.person14);
            person14.setValidate(true);
            person14.invalidate();

            PersonView person15 = (PersonView) findViewById(R.id.person15);
            person15.setValidate(true);
            person15.invalidate();

            PersonView person16 = (PersonView) findViewById(R.id.person16);
            person16.setValidate(true);
            person16.invalidate();

            PersonView person17 = (PersonView) findViewById(R.id.person17);
            person17.setValidate(true);
            person17.invalidate();

            PersonView person18 = (PersonView) findViewById(R.id.person18);
            person18.setValidate(true);
            person18.invalidate();

        }
    }
}
