package com.kuo.basketballboard;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
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
    private ArrayList<ObjectPlayer> objectPlayers = new ArrayList<>();

    private Toolbar toolbar;

    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        boardView = (BoardView) findViewById(R.id.boardView);

        toolbar.setTitle("籃球戰術板");
        setSupportActionBar(toolbar);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if(isFirst) {

            isFirst = false;

            float supportPoint = boardView.getWidth() * 0.093f;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)(supportPoint - (supportPoint/3)), (int)(supportPoint - (supportPoint/3)));


            for(int i = 0 ; i < 5 ; i++) {
                ObjectPlayer objectPlayer = new ObjectPlayer(this);
                objectPlayer.setLayoutParams(layoutParams);
                objectPlayer.setX(0.5f);
                objectPlayer.setY(boardView.getHeight()/2 - (i*supportPoint));
                objectPlayers.add(objectPlayer);
                boardView.addView(objectPlayers.get(i));
            }

            Bitmap bitmap = Bitmap.createBitmap(boardView.getWidth(), boardView.getHeight(), Bitmap.Config.ARGB_8888); //設置點陣圖的寬高
            boardView.onCreateDrawLineCanvas(bitmap);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_color_picker) {

            ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
            colorPickerDialog.show(getSupportFragmentManager(), "dialog");

        }

        return super.onOptionsItemSelected(item);
    }
}
