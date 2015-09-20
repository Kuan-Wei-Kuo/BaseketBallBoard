package com.kuo.basketballboard;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private ArrayList<ObjectPlayer> bluePlayers = new ArrayList<>();
    private ArrayList<ObjectPlayer> redPlayers = new ArrayList<>();
    private ArrayList<Point> blueInitPoints = new ArrayList<>();
    private ArrayList<Point> redInitPoints = new ArrayList<>();
    private Toolbar toolbar;
    private MenuItem menuItem;

    private boolean isFirstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        bluePlayers.add((ObjectPlayer) findViewById(R.id.blue_1));
        bluePlayers.add((ObjectPlayer) findViewById(R.id.blue_2));
        bluePlayers.add((ObjectPlayer) findViewById(R.id.blue_3));
        bluePlayers.add((ObjectPlayer) findViewById(R.id.blue_4));
        bluePlayers.add((ObjectPlayer) findViewById(R.id.blue_5));

        redPlayers.add((ObjectPlayer) findViewById(R.id.red_1));
        redPlayers.add((ObjectPlayer) findViewById(R.id.red_2));
        redPlayers.add((ObjectPlayer) findViewById(R.id.red_3));
        redPlayers.add((ObjectPlayer) findViewById(R.id.red_4));
        redPlayers.add((ObjectPlayer) findViewById(R.id.red_5));

        setRedPlayersColor();
        setObjectPlayersClickable(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        boardView = (BoardView) findViewById(R.id.boardView);

        SharedPreferences sharedPreferences = getSharedPreferences("setting", 0);
        boardView.setLineWidth(sharedPreferences.getInt("lineWidth", 20));

        toolbar.setTitle("籃球戰術板");
        setSupportActionBar(toolbar);

    }

    private void setRedPlayersColor() {
        for(int i = 0 ; i < redPlayers.size() ; i++) {
            redPlayers.get(i).setColor(getResources().getColor(R.color.RED_300));
        }
    }

    private void setBlueInitPoints() {

        for(int i = 0 ; i < bluePlayers.size() ; i++) {
            blueInitPoints.add(new Point((int)bluePlayers.get(i).getX(), (int)bluePlayers.get(i).getY()));
        }

    }

    private void setRedInitPoints() {

        for(int i = 0 ; i < redPlayers.size() ; i++) {
            redInitPoints.add(new Point((int)redPlayers.get(i).getX(), (int)redPlayers.get(i).getY()));
        }

    }

    private void onResetLayout() {

        for(int i = 0 ; i < bluePlayers.size() ; i++) {
            bluePlayers.get(i).setX(blueInitPoints.get(i).x);
            bluePlayers.get(i).setY(blueInitPoints.get(i).y);
            redPlayers.get(i).setX(redInitPoints.get(i).x);
            redPlayers.get(i).setY(redInitPoints.get(i).y);
        }

    }

    private void setObjectPlayersClickable(boolean i) {

        for(int j = 0 ; j < bluePlayers.size() ; j++) {
            bluePlayers.get(j).setClickable(i);
            redPlayers.get(j).setClickable(i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItem = (MenuItem) menu.findItem(R.id.action_draw_line);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_draw_line) {
            if(!boardView.getDrawLine()){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                boardView.setDrawLine(true);
                setObjectPlayersClickable(false);
                item.setIcon(R.mipmap.eraser_icon);
            }else {
                boardView.removePath();
            }
        }else if(id == R.id.action_color_picker) {
            ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
            colorPickerDialog.setOnCallBackLineData(onCallBackLineData);
            colorPickerDialog.show(getSupportFragmentManager(), "dialog");
        }else if(id == android.R.id.home) {
            setObjectPlayersClickable(true);
            boardView.setDrawLine(false);
            menuItem.setIcon(R.mipmap.line_icon);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }else if(id == R.id.action_reset) {
            onResetLayout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(isFirstRun) {
            isFirstRun = false;
            setRedInitPoints();
            setBlueInitPoints();
        }
    }

    private ColorPickerDialog.OnCallBackLineData onCallBackLineData = new ColorPickerDialog.OnCallBackLineData() {
        @Override
        public void getWidth(int i) {

            boardView.setLineWidth(i);
            SharedPreferences sharedPreferences = getSharedPreferences("setting", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("lineWidth", i);
            editor.commit();

        }

        @Override
        public void getColor(int r, int g, int b) {
            boardView.setLineColor(r, g, b);
        }
    };

}
