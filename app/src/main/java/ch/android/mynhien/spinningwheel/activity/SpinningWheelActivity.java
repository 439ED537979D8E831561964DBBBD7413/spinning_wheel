package ch.android.mynhien.spinningwheel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

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
        addEventHandlers();
    }

    private void initializeControls(){
        spinButton = findViewById(R.id.spinBtn);
        spinningWheelView = findViewById(R.id.spinningWheelView);

        optionsList = getIntent().getExtras().getStringArrayList("options"); //TODO: make sure no empty data
        spinningWheelView.setOptionsList(optionsList);

    }

    private void addEventHandlers(){
        spinButton.setOnTouchListener((v,e) -> {
            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    v.setPressed(true);
                    spinWheel();

                case MotionEvent.ACTION_UP:
                    v.setPressed(false);
            }

            return true;
        });
    }

    private void spinWheel(){
        final int toDegrees = new Random().nextInt(3600) + 1080;
        final Animation rotateAnimation = new RotateAnimation(0, toDegrees, spinningWheelView.getPivotX(), spinningWheelView.getPivotY());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        spinningWheelView.startAnimation(rotateAnimation);
    }
}
