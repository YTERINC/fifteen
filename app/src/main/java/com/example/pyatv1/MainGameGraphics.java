package com.example.pyatv1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class MainGameGraphics extends View {
    public Paint mPaint = new Paint();
    public ComputationalFunctions сompFunct = new ComputationalFunctions();
    public SoundGames SG = new SoundGames(getContext());


    int[][] arr = new int[4][4];
    int countStart = 0; // счетчик количества вывозов onDraw
    final int X1 = 50; //отступ рамки, отсюда идет расчет остальных размеров // координата X внешнего квадрата, слева
    final int Y1 = 200; //отступ рамки, отсюда идет расчет остальных размеров // координата Y внешнего квадрата, слева
    int x2; //координата X внешнего квадрата, справа
    int y2; //координата Y внешнего квадрата, справа
    final int THICKNESS_BIG_FRAME = 10; // толщина рамки большого квадрата
    // координаты меньшего квадрата для отображения рамки
    int x3; //координата X внутреннего квадрата, слева
    int y3; //координата Y внутреннего квадрата, слева
    int x4; //координата X внутреннего квадрата, справа
    int y4; //координата Y внутреннего квадрата, справа
    // координаты квадратиков
    int xin1 = 0; // координата X маленького квадрата, слева
    int yin1 = 0; // координата Y маленького квадрата, слева
    int xin2 = 0; // координата X маленького квадрата, справа
    int yin2 = 0; // координата Y маленького квадрата, справа
    final int DIST_BETWEEN_SQUARES = 10; // промежуток между квадратиками
    // размеры одного квадратика для рассчетов
    int xD;
    int yD;
    // включатель звука
    boolean switchMusic = true;


   public MainGameGraphics(Context context) {
        super(context);
       SG.initSound("click.mp3");
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        x2 = width - X1;
        y2 = x2 - X1 + Y1;
        // рамка внутреннего большого квадрата
        x3 = X1 + THICKNESS_BIG_FRAME;
        y3 = Y1 + THICKNESS_BIG_FRAME;
        x4 = x2 - THICKNESS_BIG_FRAME;
        y4 = y2 - THICKNESS_BIG_FRAME;

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);
        // закрашиваем холст
        mPaint.setColor(Color.GREEN);

        canvas.drawPaint(mPaint);
        // Рисуем внешний прямоугольник
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(X1, Y1, x2, y2, mPaint);
        // Рисуем внутренний прямоугольник
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(x3, y3, x4, y4, mPaint);

/////// Инициализация массива
        if (countStart == 0) {
            сompFunct.createMixedArray(arr);
        }
 ///////////////////////////////////

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != 0) {
                    mPaint.setColor(Color.YELLOW);
                    xD = сompFunct.sizeOneSquareX(x4, x3, DIST_BETWEEN_SQUARES);
                    yD = сompFunct.sizeOneSquareY(y4, y3, DIST_BETWEEN_SQUARES);
                    xin1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, j);
                    xin2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, j);
                    yin1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, i);
                    yin2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, i);
                    canvas.drawRect(xin1, yin1, xin2, yin2, mPaint);
                    mPaint.setColor(Color.RED);
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setAntiAlias(true);
                    mPaint.setTextSize(yD * 2 / 3);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(String.valueOf(arr[i][j]), xin2 - (xin2 - xin1) / 2, yin2 - (yin2 - yin1) / 2 + yD / 4, mPaint);
                }
            }

            // индикаторы
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(48);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(String.valueOf(сompFunct.i0), 30, height - 32, mPaint);
        canvas.drawText(String.valueOf(сompFunct.j0), 30, height - 96, mPaint);
        canvas.drawText(String.valueOf(сompFunct.checkWin(arr)), 30, height - 150, mPaint);
        /////////////////
        countStart = +1;

    }


    public boolean onTouchEvent(MotionEvent event) {
        // координаты Touch-события
        float evX = event.getX();
        float evY = event.getY();

        int tempArr;  // для временного хранения значения с нулем

        switch (event.getAction()) {
            // касание началось
            case MotionEvent.ACTION_DOWN:
                // если касание было начато в пределах квадрата
                if (evX >= сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0-1) &&
                        evX <= сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0-1) &&
                        evY >= сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0) &&
                        evY <= сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0) &&
                        сompFunct.j0 > 0) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0][сompFunct.j0-1];
                    arr[сompFunct.i0][сompFunct.j0-1] = tempArr;
                    сompFunct.j0 = сompFunct.j0-1;
                     invalidate();
                }
                if (evX >= сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0+1) &&
                        evX <= сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0+1) &&
                        evY >= сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0) &&
                        evY <= сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0) &&
                        сompFunct.j0 < 3) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0][сompFunct.j0+1];
                    arr[сompFunct.i0][сompFunct.j0+1] = tempArr;
                    сompFunct.j0 = сompFunct.j0+1;
                    invalidate();
                }
                if (evX >= сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0) &&
                        evX <= сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0) &&
                        evY >= сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0-1) &&
                        evY <= сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0-1) &&
                        сompFunct.i0 > 0) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0-1][сompFunct.j0];
                    arr[сompFunct.i0-1][сompFunct.j0] = tempArr;
                    сompFunct.i0 = сompFunct.i0-1;
                    invalidate();
                }
                if (evX >= сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0) &&
                        evX <= сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, сompFunct.j0) &&
                        evY >= сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0+1) &&
                        evY <= сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, сompFunct.i0+1) &&
                        сompFunct.i0 < 3) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0+1][сompFunct.j0];
                    arr[сompFunct.i0+1][сompFunct.j0] = tempArr;
                    сompFunct.i0 = сompFunct.i0+1;
                    invalidate();
                }
                break;
        }
        return true;
    }




}