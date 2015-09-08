package com.kuo.basketballboard;

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

/**
 * Created by User on 2015/8/30.
 */
public class ColorPickerDialog extends DialogFragment {

    private SeekBar slideR, slideB, slideG, lineWidth;
    private LineView colorView;
    private Button enterButton, cancelButton;
    private OnCallBackLineData onCallBackLineData;

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

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.slideR:
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideG:
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideB:
                    colorView.setLineColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.lineWidth:
                    colorView.setLineWidth(lineWidth.getProgress());
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
