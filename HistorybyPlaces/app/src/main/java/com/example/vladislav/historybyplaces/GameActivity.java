package com.example.vladislav.historybyplaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_record_text;
    TextView tv_record;
    TextView tv_hp_text;
    TextView tv_hp;
    TextView tv_points_text;
    TextView tv_points;
    ImageView iv_place;
    int imageID;
    int imageID2;
    TextView tv_question;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    private static final int INITIAL_POINTS = 0;
    private static final int INITIAL_HP = 3;
    static int points = INITIAL_POINTS;
    static int hp = INITIAL_HP;
    public static final int POINTS_MAX = 9;
    public static final int HP_MIN = 0;
    private ArrayList<Integer> id_random;
    Cursor cursor;

    SharedPreferences saves;
    public static final String PREF_NAME = "saves_points";
    public static final String SAVE_POINTS = "points";

    static Typeface cormorant_regular;
    static Typeface cormorant_light;

    private static final int KEY_ID = 0;
    private static final int KEY_QUESTION = 1;
    private static final int KEY_RIGHT = 2;
    private static final int KEY_WRONG1 = 3;
    private static final int KEY_WRONG2 = 4;
    private static final int KEY_WRONG3 = 5;
    private static final int KEY_INFO = 7;
    private static final int KEY_IMAGE = 6;
    private static final int KEY_IMAGE2 = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_points_text = (TextView) findViewById(R.id.record_text);
        tv_record = (TextView) findViewById(R.id.record);
        tv_hp_text = (TextView) findViewById(R.id.tv_hp_text);
        tv_hp = (TextView) findViewById(R.id.tv_hp);
        tv_hp.setText("" + hp);
        tv_record_text = (TextView) findViewById(R.id.points_text);
        tv_points = (TextView) findViewById(R.id.tv_points);
        tv_points.setText("" + points);
        iv_place = (ImageView) findViewById(R.id.place);
        tv_question = (TextView) findViewById(R.id.tv_quest);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        cormorant_regular = Typeface.createFromAsset(getAssets(), "fonts/CormorantInfant-Regular.ttf");
        tv_question.setTypeface(cormorant_regular);

        cormorant_light = Typeface.createFromAsset(getAssets(), "fonts/CormorantInfant-Light.ttf");
        tv_record_text.setTypeface(cormorant_light);
        tv_points_text.setTypeface(cormorant_light);
        tv_hp_text.setTypeface(cormorant_light);
        tv_hp.setTypeface(cormorant_light);
        tv_record.setTypeface(cormorant_light);
        tv_points.setTypeface(cormorant_light);
        btn1.setTypeface(cormorant_light);
        btn2.setTypeface(cormorant_light);
        btn3.setTypeface(cormorant_light);
        btn4.setTypeface(cormorant_light);

        cursor = DBHelperRealtor.prepareDB(GameActivity.this);

        id_random = new ArrayList<>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            id_random.add(i);
        }

        loadPoints();
        fillButtons();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    private void fillButtons(){
        try {
            Random random = new Random();
            int pos = random.nextInt(id_random.size());

            if (cursor.moveToPosition(id_random.get(pos))) {
                tv_question.setText(cursor.getString(KEY_QUESTION));

                String mDrawableName = cursor.getString(KEY_IMAGE);
                imageID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
                iv_place.setImageResource(imageID);

                ArrayList<Integer> arrayList = new ArrayList<Integer>(4);
                for (int i = 2; i < 6; i++) {
                    arrayList.add(i);
                }
                btn1.setText(cursor.getString(arrayList.remove(random.nextInt(arrayList.size()))));
                btn2.setText(cursor.getString(arrayList.remove(random.nextInt(arrayList.size()))));
                btn3.setText(cursor.getString(arrayList.remove(random.nextInt(arrayList.size()))));
                btn4.setText(cursor.getString(arrayList.remove(random.nextInt(arrayList.size()))));
            }
            id_random.remove(pos);
        }catch (IllegalArgumentException ex){
            ex.getMessage();
            Toast.makeText(this, "End of the questions", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String mDrawableName = cursor.getString(KEY_IMAGE2);
        imageID2 = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        Intent intent = new Intent(GameActivity.this, InfoActivity.class);
        intent.putExtra("id_image", imageID2);
        intent.putExtra("info", cursor.getString(KEY_INFO));

        if (btn.getText().toString().equals(cursor.getString(KEY_RIGHT))){
            points++;
            tv_points.setText("" + points);
            intent.putExtra("result", "Верно!");
            //Toast.makeText(this, "Верно!", Toast.LENGTH_SHORT).show();
        }else{
            hp--;
            tv_hp.setText("" + hp);
            intent.putExtra("result", "Неверно!");
            //Toast.makeText(this, "Неверно!", Toast.LENGTH_SHORT).show();
        }
        savePoints();
        startActivityForResult(intent, 0);
    }

    private void savePoints(){
        saves = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = saves.edit();
        if (points > saves.getInt(SAVE_POINTS, 0)) {
            editor.putInt(SAVE_POINTS, points);
            editor.apply();
        }
    }

    private void loadPoints(){
        saves = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        tv_record.setText("" + saves.getInt(SAVE_POINTS, 0));
    }

    @Override
    protected void onDestroy() {
        points = INITIAL_POINTS;
        hp = INITIAL_HP;
        id_random = new ArrayList<>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            id_random.add(i);
        }
        cursor.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (points == POINTS_MAX || hp == HP_MIN){
            finish();
        }
        fillButtons();
    }
}
