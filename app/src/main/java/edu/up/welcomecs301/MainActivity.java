// Carolyn Sousa

package edu.up.welcomecs301;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Face face;
    private Spinner hairStyleSpinner;
    private RadioGroup featureRadioGroup;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private Button randomFaceButton;
    private FaceView faceSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setListeners();

        face = new Face();
        updateViewsFromFace();
    }

    private void initializeViews() {
        hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        featureRadioGroup = findViewById(R.id.featureRadioGroup);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        randomFaceButton = findViewById(R.id.randomFaceButton);
        faceSurfaceView = findViewById(R.id.faceSurfaceView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Face.getHairStyles());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairStyleSpinner.setAdapter(adapter);
    }

    //onItemSelected info
    //https://stackoverflow.com/questions/20151414/how-can-i-use-onitemselected-in-android
    private void setListeners() {
        hairStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                face.setHairStyle(position);
                updateViewsFromFace();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        featureRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updateSeekBarProgress());

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        randomFaceButton.setOnClickListener(v -> {
            face.randomize();
            updateViewsFromFace();
        });
    }

    private void updateSeekBarProgress() {
        int color;
        if (featureRadioGroup.getCheckedRadioButtonId() == R.id.hairRadioButton) {
            color = face.getHairColor();
        } else if (featureRadioGroup.getCheckedRadioButtonId() == R.id.eyesRadioButton) {
            color = face.getEyeColor();
        } else {
            color = face.getSkinColor();
        }
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
    }

    private void updateViewsFromFace() {
        hairStyleSpinner.setSelection(face.getHairStyle());

        updateSeekBarProgress();
        faceSurfaceView.updateFace(face);
    }

    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int color = Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
            if (featureRadioGroup.getCheckedRadioButtonId() == R.id.hairRadioButton) {
                face.setHairColor(color);
            } else if (featureRadioGroup.getCheckedRadioButtonId() == R.id.eyesRadioButton) {
                face.setEyeColor(color);
            } else {
                face.setSkinColor(color);
            }
            updateViewsFromFace();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}

    };
}