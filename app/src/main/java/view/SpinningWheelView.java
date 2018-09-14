package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ch.android.mynhien.spinningwheel.R;

public class SpinningWheelView extends View {

    private int height, width;
    private Paint paint;
    private int radius;
    private int centerX, centerY;
    private ArrayList<String> optionsList;
    private Button spinButton;

    private static final int STROKE_WIDTH = 5;
    private static final int[] SLICE_COLORS = {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.DKGRAY}; //TODO: limit?

    public SpinningWheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinningWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpinningWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initSpinningWheel() {
        height = getHeight();
        width = getWidth();
        radius = Math.min(height, width) / 2; //radius = smaller side of view divided by 2
        paint = new Paint();
        centerX = width / 2;
        centerY = height / 2;

        spinButton = findViewById(R.id.spinBtn);
    }

    @Override
    public void onDraw(Canvas canvas) {
        initSpinningWheel();

        drawCircle(canvas);
        drawSlices(canvas);
    }

    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(centerX, centerY, radius-STROKE_WIDTH, paint);
    }

    public void setOptionsList(ArrayList<String> optionsList) {
        this.optionsList = optionsList;

    }

    private void drawSlices(Canvas canvas) {
        float top = centerY - radius - STROKE_WIDTH;
        float right = centerX + radius + STROKE_WIDTH;
        float bottom = centerY + radius + STROKE_WIDTH;
        float left = centerX - radius - STROKE_WIDTH;

        RectF rectF = new RectF(left, top, right, bottom);

        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStyle(Paint.Style.FILL);

        float sweepAngle = 360f / optionsList.size();

        //TODO: you probably can optimize that...
        for (int i = 0; i < optionsList.size(); i++) {
            paint.setColor(SLICE_COLORS[i]);
            canvas.drawArc(rectF, 270 + i * sweepAngle, sweepAngle, true, paint);
        }
            canvas.rotate(sweepAngle / 2, centerX, centerY);

        for (int j = 0; j < optionsList.size(); j++) {
                paint.setColor(Color.BLACK);
                paint.setTextSize(40);
                canvas.drawText(optionsList.get(j), centerX + radius / 4, centerY, paint);
                canvas.rotate(sweepAngle, centerX, centerY);
        }

    }
}
