package ch.android.mynhien.spinningwheel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

import ch.android.mynhien.spinningwheel.R;
import view.SpinningWheelView;

public class SpinningWheelActivity extends AppCompatActivity {

    private Button spinButton;
    private SpinningWheelView spinningWheelView;
    private ArrayList<String> optionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinningwheel);

        initializeControls();
    }

    private void initializeControls(){
        spinButton = findViewById(R.id.spinBtn);
        spinningWheelView = findViewById(R.id.spinningWheelView);

        optionsList = getIntent().getExtras().getStringArrayList("options"); //TODO: make sure no empty data
        spinningWheelView.setOptionsList(optionsList);
    }
}
