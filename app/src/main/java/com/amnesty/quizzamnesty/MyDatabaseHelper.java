package com.amnesty.quizzamnesty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.logging.Logger;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "amnesty_quizz";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getReadableDatabase();
        Log.i("DB", "dbManager");
        Log.i("DB PATH", context.getDatabasePath(DATABASE_NAME).getAbsolutePath());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DB", "dbOnCreate");
        String script = "CREATE TABLE question_armes (" +
                "id INTEGER PRIMARY KEY, " +
                "armes_legere BOOLEAN, " +
                "chars BOOLEAN, " +
                "avions BOOLEAN);";
        db.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeResultFirstQuestion(boolean isArmesLegeresSelected, boolean isCharsSelected, boolean isAvionsSelected) {

        Log.i("DB", "dbStoreResultFirstQuestion");
        Log.i("DB ARME LEGERES", String.valueOf(isArmesLegeresSelected));
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("armes_legere", isArmesLegeresSelected);
        values.put("chars", isCharsSelected);
        values.put("avions", isAvionsSelected);

        db.insert("question_armes", null, values);
        db.close();
    }

    public Integer[] getResults() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorArmesLegere = db.rawQuery("SELECT armes_legere FROM question_armes", null);
        Cursor cursorChars = db.rawQuery("SELECT chars FROM question_armes", null);
        Cursor cursorAvions = db.rawQuery("SELECT avions FROM question_armes", null);

        int countArmesLegere = 0;
        if(cursorArmesLegere != null){
            while(cursorArmesLegere.moveToNext()) {
                if (cursorArmesLegere.getString(0).equals("1")) {
                    countArmesLegere++;
                }
            }
        }

        int countChars = 0;
        if(cursorChars != null){
            while(cursorChars.moveToNext()) {
                if (cursorChars.getString(0).equals("1")) {
                    countChars++;
                }
            }
        }

        int countAvions = 0;
        if(cursorAvions != null){
            while(cursorAvions.moveToNext()) {
                if (cursorAvions.getString(0).equals("1")) {
                    countAvions++;
                }
            }
        }

        Log.i("COUNTS", String.valueOf(countArmesLegere));
        return new Integer[] {countArmesLegere, countChars, countAvions};
    }
}
