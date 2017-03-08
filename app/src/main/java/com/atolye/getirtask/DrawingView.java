package com.atolye.getirtask;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import com.atolye.getirtask.response.BaseResponse;
import com.atolye.getirtask.response.Element;

/**
 * Created by mertn on 08-Mar-17.
 */

public class DrawingView extends View {
    private BaseResponse response;
    private Paint paint;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
    }
    private void drawRectangle(Element element, Canvas canvas) {
        Rect rect = new Rect(element.getXPosition(), element.getYPosition(), element.getXPosition()+element.getWidth(), element.getYPosition()+element.getHeight());
        paint.setColor(Color.parseColor("#"+element.getColor()));
        canvas.drawRect(rect, paint);
    }

    private void drawCircle(Element element, Canvas canvas) {
        paint.setColor(Color.parseColor("#"+element.getColor()));
        canvas.drawCircle(element.getXPosition(), element.getYPosition(), element.getR(), paint);
    }

    public void drawResponse(BaseResponse response){
        this.response = response;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(response == null || response.getElements() == null)
            return;
        for (Element element : response.getElements()){
            if (element.getType().contentEquals("circle"))
                drawCircle(element, canvas);
            else if (element.getType().contentEquals("rectangle"))
                drawRectangle(element, canvas);
        }
    }
}
