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
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by User on 2015/8/30.
 */
public class ColorPickerDialog extends DialogFragment {

    private SeekBar slideR, slideB, slideG;
    private ImageView colorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_color_picker, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        colorView = (ImageView) view.findViewById(R.id.colorView);

        slideR = (SeekBar) view.findViewById(R.id.slideR);
        slideG = (SeekBar) view.findViewById(R.id.slideG);
        slideB = (SeekBar) view.findViewById(R.id.slideB);

        slideR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        slideG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        slideB.setOnSeekBarChangeListener(onSeekBarChangeListener);

    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.slideR:
                    colorView.setBackgroundColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideG:
                    colorView.setBackgroundColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
                    break;
                case R.id.slideB:
                    colorView.setBackgroundColor(Color.rgb(slideR.getProgress(), slideG.getProgress(), slideB.getProgress()));
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
