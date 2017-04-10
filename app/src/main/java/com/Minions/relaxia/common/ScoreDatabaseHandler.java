package com.Minions.relaxia.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.Minions.relaxia.ui.History;

import java.util.ArrayList;

/**
 * Created by JJ on 9/4/2017.
 */

public class ScoreDatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "ScoreDatabaseHandler";

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Scores.db";


    public static final String TABLE_ALL = "TableForScores";



    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DIFF_LEVEL = "Difficulty";
    public static final String COLUMN_SCORE = "Score";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_DATETIME = "DateTime";
    public static final String COLUMN_STARS = "Stars";
    public static final String COLUMN_THEME = "Theme";


    public static final String MEMORY_GAME_NUMBERS = "Numbers";
    public static final String MEMORY_GAME_ALPHABETS = "Alphabets";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_ALL + " ("
            + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DIFF_LEVEL + " INTEGER, "
            + COLUMN_SCORE + " INTEGER, "
            + COLUMN_TIME + " INTEGER, "
            + COLUMN_DATETIME + " TEXT, "
            + COLUMN_STARS + " INTEGER, "
            + COLUMN_THEME + " TEXT "
            + ");";

    private static ScoreDatabaseHandler mInstance;

    private Context context;

    public static synchronized ScoreDatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new ScoreDatabaseHandler(context);
        }
        return mInstance;
    }

    public ScoreDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i(TAG, "onOpen database!");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate databasehandler");
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertIntoTable(String theme, int diff_level, int time, String dateTime, int stars){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DIFF_LEVEL, diff_level);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_DATETIME, dateTime);
        cv.put(COLUMN_STARS, stars);
        cv.put(COLUMN_THEME, theme);
        db.insert(TABLE_ALL, null, cv);
        Log.i(TAG, "Inserting into table. Theme: " + theme + "diff_level: "+ diff_level + "time : "+ time + "dateTime: " + dateTime + "stars: " + stars);

    }

    public ArrayList<History> tableToArrayList(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ALL + " ORDER BY " + COLUMN_ID + " ASC;";

        ArrayList<History> gameplayHistory = new ArrayList<>();

        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()) {
            c.moveToFirst();

            while(!(c.isAfterLast())) {

                History mHistory = new History();

                mHistory.setAttempt(c.getString(c.getColumnIndex(COLUMN_ID)));
                mHistory.setDateTime(c.getString(c.getColumnIndex(COLUMN_DATETIME)));
                mHistory.setTheme(c.getString(c.getColumnIndex(COLUMN_THEME)));
                mHistory.setDifficulty(c.getString(c.getColumnIndex(COLUMN_DIFF_LEVEL)));
                mHistory.setStars(c.getString(c.getColumnIndex(COLUMN_STARS)));

                c.moveToNext();
                gameplayHistory.add(mHistory);
            }
            c.close();
            db.close();
            return gameplayHistory;
        } else {
            return gameplayHistory;
        }


    }
    public int getTotalStars(int themeId){
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        int totalStars = 0;
        if(themeId ==1) {
            query = "SELECT * FROM " + TABLE_ALL + " WHERE " + COLUMN_THEME + " LIKE 'Numbers' ORDER BY " + COLUMN_ID + " ASC;";
        }
        else if (themeId ==2){
            query = "SELECT * FROM " + TABLE_ALL + " WHERE " + COLUMN_THEME + " LIKE 'Alphabets' ORDER BY " + COLUMN_ID + " ASC;";
        }
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()) {
            c.moveToFirst();
            double[] highStarsPerLevel = new double[6];        //An array to store the highest score earned for each level
            for (int i = 0; i < 6; i++) {                           //Initialise all the high scores to be 0
                highStarsPerLevel[i] = 0;
            }

            while (!(c.isAfterLast())) {
                int level = c.getInt(c.getColumnIndex(COLUMN_DIFF_LEVEL));
                int stars = c.getInt(c.getColumnIndex(COLUMN_STARS));

                if (highStarsPerLevel[level - 1] < stars) {                    //Check if cursor score is greater than previous recorded high score for that level
                    highStarsPerLevel[level - 1] = stars;

                    totalStars = 0;
                    for (int i = 0; i < 6; i++) {
                        totalStars += highStarsPerLevel[i];                 //Recalculate total high score
                    }
                }
                c.moveToNext();
            }

            c.close();
            return totalStars;
        } else {

            return totalStars;
        }
    }
    public DataPoint[] numbersThemeCoords() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ALL + " WHERE " + COLUMN_THEME + " LIKE 'Numbers' ORDER BY " + COLUMN_ID + " ASC;";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()) {
            c.moveToFirst();
            int totalAttempts = c.getCount();                   //Get the number of rows in the table, which is the number of attempts
            DataPoint[] values = new DataPoint[totalAttempts];  //Array of DataPoints, each DataPoint holds an (X,Y) coordinate
            double[] highStarsPerLevel = new double[6];        //An array to store the highest score earned for each level
            int totalStars = 0;
            int attemptNumber = 0;
            //Use for calculating total score which will be the Y coordinate
            for (int i = 0; i < 6; i++) {                           //Initialise all the high scores to be 0
                highStarsPerLevel[i] = 0;
            }

            while (!(c.isAfterLast())) {

                attemptNumber++;          //This is the X coordinate
                int level = c.getInt(c.getColumnIndex(COLUMN_DIFF_LEVEL));
                int stars = c.getInt(c.getColumnIndex(COLUMN_STARS));

                if (highStarsPerLevel[level - 1] < stars) {                    //Check if cursor score is greater than previous recorded high score for that level
                    highStarsPerLevel[level - 1] = stars;

                    totalStars = 0;
                    for (int i = 0; i < 6; i++) {
                        totalStars += highStarsPerLevel[i];                 //Recalculate total high score
                    }
                }

                DataPoint XYcoordinate = new DataPoint(attemptNumber, totalStars);  //Assign attempt number and total score to DataPoint
                values[attemptNumber - 1] = XYcoordinate;                             //Add DataPoint to the DataPoint array
                c.moveToNext();


                Log.v("DataPoint", XYcoordinate.getX() + " " + XYcoordinate.getY());

            }

            c.close();
            return values;
        } else {
            DataPoint[] values = new DataPoint[1];
            values[0] = new DataPoint(0,0);
            c.close();
            return values;
        }

    }

    public DataPoint[] alphabetsThemeCoords() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ALL + " WHERE " + COLUMN_THEME + " LIKE 'Alphabets' ORDER BY " + COLUMN_ID + " ASC;";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()) {
            c.moveToFirst();
            int totalAttempts = c.getCount();                   //Get the number of rows in the table, which is the number of attempts
            DataPoint[] values = new DataPoint[totalAttempts];  //Array of DataPoints, each DataPoint holds an (X,Y) coordinate
            double[] highStarsPerLevel = new double[6];        //An array to store the highest score earned for each level
            int totalStars = 0;
            int attemptNumber = 0;
            //Use for calculating total score which will be the Y coordinate
            for (int i = 0; i < 6; i++) {                           //Initialise all the high scores to be 0
                highStarsPerLevel[i] = 0;
            }

            while (!(c.isAfterLast())) {

                attemptNumber++;                        //This is the X coordinate
                int level = c.getInt(c.getColumnIndex(COLUMN_DIFF_LEVEL));
                int stars = c.getInt(c.getColumnIndex(COLUMN_STARS));

                if (highStarsPerLevel[level - 1] < stars) {                    //Check if cursor score is greater than previous recorded high score for that level
                    highStarsPerLevel[level - 1] = stars;

                    totalStars = 0;
                    for (int i = 0; i < 6; i++) {
                        totalStars += highStarsPerLevel[i];                 //Recalculate total high score
                    }
                }

                DataPoint XYcoordinate = new DataPoint(attemptNumber, totalStars);  //Assign attempt number and total score to DataPoint
                values[attemptNumber - 1] = XYcoordinate;                             //Add DataPoint to the DataPoint array
                c.moveToNext();



                Log.v("DataPoint", XYcoordinate.getX() + " " + XYcoordinate.getY());

            }

            c.close();
            return values;
        } else {
            DataPoint[] values = new DataPoint[1];
            values[0] = new DataPoint(0,0);
            c.close();
            return values;
        }
    }
}
