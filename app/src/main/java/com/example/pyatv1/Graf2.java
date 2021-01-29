package com.example.pyatv1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Graf2 extends View {
    public Paint mPaint = new Paint();
    Canvas canvas = new Canvas();
    public Calc mCalc = new Calc();
    int[][] arr = new int[4][4];

    int i;
    int j;
int countstart = 0;
    int x1 = 200; //отступ рамки, отсюда идет расчет остальных размеров
    int y1 = 400; //отступ рамки, отсюда идет расчет остальных размеров
    int x2; //вторая координата рамки большого квадрата
    int y2; //вторая координата рамки большого квадрата
    int drect = 10; // толщина рамки большого квадрата
    // координаты меньшего квадрата для отображения рамки
    int x3;
    int y3;
    int x4;
    int y4;
    // координаты квадратиков
    int xin1 = 0;
    int yin1 = 0;
    int xin2 = 0;
    int yin2 = 0;
    int drect2  = 30; // промежуток между квадратиками
    // размеры одного квадратика
    int xd;
    int yd;



    public Graf2(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        x2 = width - x1;
        y2 = x2 - x1 + y1;
        // рамка большого квадрата
        x3 = x1 + drect;
        y3 = y1 + drect;
        x4 = x2 - drect;
        y4 = y2 - drect;

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);
        // закрашиваем холст
        mPaint.setColor(Color.GREEN);
        canvas.drawPaint(mPaint);
        // Рисуем внешний прямоугольник
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(x1, y1, x2, y2, mPaint);
        // Рисуем внутренний прямоугольник
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(x3, y3, x4, y4, mPaint);


/////// Инициализация массива
        if (countstart == 0) {
            mCalc.initArr(arr);
    }
        ///////////////////////////////////


            for (i = 0; i < 4; i++)
                for (j = 0; j < 4; j++) {

                    if (arr[i][j] != 0) {


                        mPaint.setColor(Color.YELLOW);



                        //  xd = (x4 - x3 - drect2 * 5) / 4; // один квадратик
                        // yd = (y4 - y3 - drect2 * 5) / 4; // один квадратик

                            xd = mCalc.Fxd(x4, x3, drect2);
                            yd = mCalc.Fxd(y4, y3, drect2);
                            //  xin1 = x3 + drect2 + (xd + drect2) * j;
                            xin1 = mCalc.Sxin1(x3, xd, drect2, j);
                            // xin2 = x3 + drect2 + xd + (xd + drect2) * j;
                            xin2 = mCalc.Sxin2(x3, xd, drect2, j);
                            //yin1 = y3 + drect2 + (yd + drect2) * i;
                            yin1 = mCalc.Syin1(y3, yd, drect2, i);
                            //yin2 = y3 + drect2 + yd + (yd + drect2) * i;
                            yin2 = mCalc.Syin2(y3, yd, drect2, i);
                            //   }

                        canvas.drawRect(xin1, yin1, xin2, yin2, mPaint);

                        mPaint.setColor(Color.RED);
                        mPaint.setStyle(Paint.Style.FILL);
                        mPaint.setAntiAlias(true);
                        mPaint.setTextSize(yd * 2 / 3);
                        mPaint.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText(String.valueOf(arr[i][j]), xin2 - (xin2 - xin1) / 2, yin2 - (yin2 - yin1) / 2 + yd / 4, mPaint);
                    }


                }

            // индикатор размеров холста
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(48);
            mPaint.setTextAlign(Paint.Align.LEFT);
            //canvas.drawText(String.valueOf(width), 30, height - 32, mPaint);
            //canvas.drawText(String.valueOf(height), 30, height - 96, mPaint);

     canvas.drawText(String.valueOf(mCalc.i0), 30, height - 32, mPaint);
     canvas.drawText(String.valueOf(mCalc.j0), 30, height - 96, mPaint);

        countstart = +1;

    }


    public boolean onTouchEvent(MotionEvent event) {
        // координаты Touch-события
        float evX = event.getX();
        float evY = event.getY();

    int temparr;

        switch (event.getAction()) {
            // касание началось
            case MotionEvent.ACTION_DOWN:
                // если касание было начато в пределах квадрата

                if (evX >= mCalc.Sxin1(x3, xd, drect2, mCalc.j0-1) && evX <= mCalc.Sxin2(x3, xd, drect2, mCalc.j0-1)
                        && evY >= mCalc.Syin1(y3, yd, drect2, mCalc.i0) && evY <= mCalc.Syin2(y3, yd, drect2, mCalc.i0) && mCalc.j0 > 0)
                {
                    temparr = arr[mCalc.i0][mCalc.j0];
                    arr[mCalc.i0][mCalc.j0] = arr[mCalc.i0][mCalc.j0-1];
                    arr[mCalc.i0][mCalc.j0-1] = temparr;
                    mCalc.j0 = mCalc.j0-1;
                   invalidate();
                }

                if (evX >= mCalc.Sxin1(x3, xd, drect2, mCalc.j0+1) && evX <= mCalc.Sxin2(x3, xd, drect2, mCalc.j0+1)
                        && evY >= mCalc.Syin1(y3, yd, drect2, mCalc.i0) && evY <= mCalc.Syin2(y3, yd, drect2, mCalc.i0) && mCalc.j0 < 3)
                {
                    temparr = arr[mCalc.i0][mCalc.j0];
                    arr[mCalc.i0][mCalc.j0] = arr[mCalc.i0][mCalc.j0+1];
                    arr[mCalc.i0][mCalc.j0+1] = temparr;
                    mCalc.j0 = mCalc.j0+1;
                    invalidate();
                }

                if (evX >= mCalc.Sxin1(x3, xd, drect2, mCalc.j0) && evX <= mCalc.Sxin2(x3, xd, drect2, mCalc.j0)
                        && evY >= mCalc.Syin1(y3, yd, drect2, mCalc.i0-1) && evY <= mCalc.Syin2(y3, yd, drect2, mCalc.i0-1) && mCalc.i0 > 0)
                {
                    temparr = arr[mCalc.i0][mCalc.j0];
                    arr[mCalc.i0][mCalc.j0] = arr[mCalc.i0-1][mCalc.j0];
                    arr[mCalc.i0-1][mCalc.j0] = temparr;
                    mCalc.i0 = mCalc.i0-1;
                    invalidate();
                }

                if (evX >= mCalc.Sxin1(x3, xd, drect2, mCalc.j0) && evX <= mCalc.Sxin2(x3, xd, drect2, mCalc.j0)
                        && evY >= mCalc.Syin1(y3, yd, drect2, mCalc.i0+1) && evY <= mCalc.Syin2(y3, yd, drect2, mCalc.i0+1) && mCalc.i0 < 3)
                {
                    temparr = arr[mCalc.i0][mCalc.j0];
                    arr[mCalc.i0][mCalc.j0] = arr[mCalc.i0+1][mCalc.j0];
                    arr[mCalc.i0+1][mCalc.j0] = temparr;
                    mCalc.i0 = mCalc.i0+1;
                    invalidate();
                }


                break;



        }
        return true;
    }








}
