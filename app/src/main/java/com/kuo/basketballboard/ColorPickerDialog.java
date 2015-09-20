package com.kuo.basketballboard;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by User on 2015/8/30.
 */
public class ColorPickerDialog extends DialogFragment {

    private SeekBar slideR, slideB, slideG, lineWidth;
    private LineView colorView;
    private Button enterButton, cancelButton;
    private OnCallBackLineData onCallBackLineData;
    private TextView text_r, text_g, text_b;

    public interface OnCallBackLineData {
        void getWidth(int i);
        void getColor(int r, int g, int b);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_color_picker, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        colorView = (LineView) view.findViewById(R.id.colorView);

        enterButton = (Button) view.findViewById(R.id.enterButton);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);

        text_r = (TextView) view.findViewById(R.id.text_r);
        text_g = (TextView) view.findViewById(R.id.text_g);
        text_b = (TextView) view.findViewById(R.id.text_b);


        slideR = (SeekBar) view.findViewById(R.id.slideR);
        slideG = (SeekBar) view.findViewById(R.id.slideG);
        slideB = (SeekBar) view.findViewById(R.id.slideB);
        lineWidth = (SeekBar) view.findViewById(R.id.lineWidth);

        slideR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        slideG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        slideB.setOnSeekBarChangeListener(onSeekBarChangeListener);
        lineWidth.setOnSeekBarChangeListener(onSeekBarChangeListener);

        enterButton.setOnClickListener(onButtonClickListener);
        cancelButton.setOnClickListener(onButtonClickListener);

        text_r.setVisibility(View.INVISIBLE);
        text_g.setVisibility(View.INVISIBLE);
        text_b.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", 0);
        colorView.setLineWidth(sharedPreferences.getInt("lineWidth", 20));
        lineWidth.setProgress(sharedPreferences.getInt("lineWidth", 20));

    }

    public void setLineWidth(int i) {
        colorView.setLineWidth(i);
    }

    public void setOnCallBackLineData(OnCallBackLineData onCallBackLineData) {
        this.onCallBackLineData = onCallBackLineData;
    }

    private Button.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.enterButton:
                    if(onCallBackLineData != null){
                        onCallBackLineData.getWidth(lineWidth.getProgress());
                        onCallBackLineData.getColor(slideR.getProgress(), slideG.getProgress(), slideB.getProgress());
                        getDialog().dismiss();
                    }
                    break;
                case R.id.cancelButton:
                    getDialog().dismiss();
                    break;
            }

        }
    };

    private float x = 0;
    private int oldProgressR;
    private int oldProgressG;
    private int oldProgressB;

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.slideR:
                    text_r.setText(seekBar.getProgress() + "");
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressR);
                    text_r.animate().x(computtingPosition(x + text_r.getX(), seekBar.getWidth() - (text_r.getWidth()/2f), seekBar.getX())).setDuration(0).start();
                    oldProgressR = seekBar.getProgress();
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideG:
                    text_g.setText(seekBar.getProgress() + "");
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressG);
                    text_g.animate().x(computtingPosition(x + text_g.getX(), seekBar.getWidth() - (text_r.getWidth()/2f), seekBar.getX())).setDuration(0).start();
                    oldProgressG = seekBar.getProgress();
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideB:
                    text_b.setText(seekBar.getProgress() + "");
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressB);
                    text_b.animate().x(computtingPosition(x + text_b.getX(), seekBar.getWidth() - (text_r.getWidth()/2f), seekBar.getX())).setDuration(0).start();
                    oldProgressB = seekBar.getProgress();
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.lineWidth:
                    colorView.setLineWidth(lineWidth.getProgress());
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            switch (seekBar.getId()) {
                case R.id.slideR:
                    text_r.setVisibility(View.VISIBLE);
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressR);
                    text_r.animate().x(x + text_r.getX()).setDuration(0).start();
                    break;
                case R.id.slideG:
                    text_g.setVisibility(View.VISIBLE);
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressG);
                    text_g.animate().x(x + text_g.getX()).setDuration(0).start();
                    break;
                case R.id.slideB:
                    text_b.setVisibility(View.VISIBLE);
                    x = (seekBar.getWidth() / 255f) * (seekBar.getProgress() - oldProgressB);
                    text_b.animate().x(x + text_b.getX()).setDuration(0).start();
                    break;
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            switch (seekBar.getId()) {
                case R.id.slideR:
                    text_r.setVisibility(View.INVISIBLE);
                    break;
                case R.id.slideG:
                    text_g.setVisibility(View.INVISIBLE);
                    break;
                case R.id.slideB:
                    text_b.setVisibility(View.INVISIBLE);
                    break;
            }
        }

    };

    private float computtingPosition(float x, float border, float startBorder) {

        if(x >= border){
            return border;
        }else if(x <= startBorder) {
            return startBorder;
        }else {
            return x;
        }

    }

}
