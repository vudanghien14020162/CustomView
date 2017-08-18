package demo.exercise.pclap.customviewclock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

/**
 * Created by PC Lap on 8/18/2017.
 */
public class ClockCustom extends View{
    private Paint mPaintCircleCenter;
    private Paint mPaintCircleMain;
    private Paint mPaintSecond, mPaintMin, mPaintHour;
    private Paint mPaintText;

    private boolean mIsSquare;

    public ClockCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustom(context, attrs);
    }

    private void initCustom(Context context, AttributeSet attributeSet) {

        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.ClockView);

        mIsSquare = array.getBoolean(R.styleable.ClockView_is_square_width, true);

        int colorCircleMain = array.getColor(
                R.styleable.ClockView_color_circle_main, getResources().getColor(R.color.brown_500));
        int colorCenter = array.getColor(
                R.styleable.ClockView_color_circle_center, getResources().getColor(R.color.blue_50));
        int colorSecond = array.getColor(R.styleable.ClockView_color_second, getResources().getColor(R.color.red_400));
        int colorMin = array.getColor(R.styleable.ClockView_color_min, getResources().getColor(R.color.yellow_300));
        int colorHour = array.getColor(R.styleable.ClockView_color_hour, getResources().getColor(R.color.grey_500));
        int colorText = array.getColor(R.styleable.ClockView_color_text, getResources().getColor(R.color.yellow_300));
        //giai phong array
        array.recycle();

        mPaintCircleMain = new Paint();
        //bo viền ben ngoai
        mPaintCircleMain.setAntiAlias(true);
        mPaintCircleMain.setStyle(Paint.Style.FILL);
        mPaintCircleMain.setColor(colorCircleMain);

        mPaintCircleCenter = new Paint();
        mPaintCircleCenter.setAntiAlias(true);
        mPaintCircleCenter.setColor(colorCenter);
        mPaintCircleCenter.setStyle(Paint.Style.FILL);

        mPaintSecond = new Paint();
        mPaintSecond.setColor(colorSecond);
        mPaintSecond.setStrokeWidth(CONS.STROKE_WIDTH_SECOND);
        mPaintSecond.setStyle(Paint.Style.STROKE);
        mPaintSecond.setAntiAlias(true);

        mPaintMin = new Paint();
        mPaintMin.setAntiAlias(true);
        mPaintMin.setColor(colorMin);
        mPaintMin.setStyle(Paint.Style.STROKE);
        mPaintMin.setStrokeWidth(CONS.STROKE_WIDTH_MIN);

        mPaintHour = new Paint();
        mPaintHour.setAntiAlias(true);
        mPaintHour.setColor(colorHour);
        mPaintHour.setStyle(Paint.Style.STROKE);
        mPaintHour.setStrokeWidth(CONS.STOKE_WIDTH_HOUR);

        //
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        mPaintText = new Paint();
        mPaintText.setTextSize(CONS.TEXT_SIZE);
        mPaintText.setTypeface(typeface);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(colorText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(mIsSquare){
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw circle main
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawOval(rectF, mPaintCircleMain);

        RectF rectFCenter = new RectF(
                getWidth() / 2 - 50, getHeight() / 2 - 50, getWidth() / 2  + 50, getHeight() / 2 + 50);
        canvas.drawOval(rectFCenter, mPaintCircleCenter);

        //dua tam ve giua man hinh
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //draw second
        canvas.drawLine(0, 0, getWidth() / 2 - 14, 0, mPaintSecond);
        //draw min
        canvas.drawLine(0, 0, getWidth() / 3 - 40, getHeight() / 3 - 40, mPaintMin);
        //draw hour
        canvas.drawLine(0, 0, getWidth() / 4 - 24, - getHeight() / 4 - 24, mPaintHour);

        //draw number
        canvas.drawText("12", -44, - getHeight() / 2 + CONS.TEXT_SIZE, mPaintText);
        canvas.drawText("3", getWidth() / 2 -CONS.TEXT_SIZE, 0, mPaintText);
        canvas.drawText("6", 0, getHeight() / 2 - 14, mPaintText);
        canvas.drawText("9", - getWidth() / 2 + 14, 0, mPaintText);

        Date date = new Date();
        int second = date.getSeconds();
        int radius = second * 6;
        canvas.rotate(radius);

    }

    private class RunAsyTaskClock extends AsyncTask<Integer, Void, Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
            return null;
        }

    }
}
