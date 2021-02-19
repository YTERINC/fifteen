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
    public SoundGames SGFinish = new SoundGames(getContext());



    int[][] arr = new int[4][4];
    final int X1 = 50; //отступ рамки, отсюда идет расчет остальных размеров // координата X внешнего квадрата, слева
    final int Y1 = 350; //отступ рамки, отсюда идет расчет остальных размеров // координата Y внешнего квадрата, слева
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
    // индикатор окончания игры
    boolean gameOver = false;
    // размеры экрана
    int width;
    int height;
    // текст берем из ресурсов
    String finishGame = getResources().getString(R.string.finishGame);
    String newGame = getResources().getString(R.string.newGame);
    String mute = getResources().getString(R.string.mute);
    // цвета геометрических фигур из ресурсов
    int colorBackground = getResources().getColor(R.color.colorBackground);
    int colorBigFrameOut = getResources().getColor(R.color.colorBigFrameOut);
    int colorBigFrameIn = getResources().getColor(R.color.colorBigFrameIn);
    int colorSmallSquare = getResources().getColor(R.color.colorSmallSquare);
    int colorSmallSquareFrame = getResources().getColor(R.color.colorSmallSquareFrame);
    int colorMute = getResources().getColor(R.color.colorMute);
    int colorFigureNewGame = getResources().getColor(R.color.colorFigureNewGame);

    // цвета текстов из ресурсов
    int colorClickNewGame = getResources().getColor(R.color.colorClickNewGame);
    int colorClickMute = getResources().getColor(R.color.colorClickMute);
    int colorTextWin = getResources().getColor(R.color.colorTextWin);
    int colorTextNumeral = getResources().getColor(R.color.colorTextNumeral);

   public MainGameGraphics(Context context) {
       super(context);
       SG.initSound("click.mp3");
       сompFunct.createMixedArray(arr);
       SGFinish.initSound("baraban.ogg");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = canvas.getWidth();
        height = canvas.getHeight();
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
        mPaint.setColor(colorBackground);
        canvas.drawPaint(mPaint);
        // Рисуем внешний прямоугольник
        mPaint.setColor(colorBigFrameOut);
        canvas.drawRect(X1, Y1, x2, y2, mPaint);
        // Рисуем внутренний прямоугольник
        mPaint.setColor(colorBigFrameIn);
        canvas.drawRect(x3, y3, x4, y4, mPaint);

         // рисуем квадратики
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != 0) {
                    mPaint.setColor(colorSmallSquare);
                    xD = сompFunct.sizeOneSquareX(x4, x3, DIST_BETWEEN_SQUARES);
                    yD = сompFunct.sizeOneSquareY(y4, y3, DIST_BETWEEN_SQUARES);
                    xin1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, j);
                    xin2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, j);
                    yin1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, i);
                    yin2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, i);
                    canvas.drawRect(xin1, yin1, xin2, yin2, mPaint);
                    mPaint.setColor(colorTextNumeral);
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setAntiAlias(true);
                    mPaint.setTextSize(yD * 2 / 3);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(String.valueOf(arr[i][j]), xin2 - (xin2 - xin1) / 2, yin2 - (yin2 - yin1) / 2 + yD / 4, mPaint);
                }
        }

        if (сompFunct.checkWin(arr) == true) {
            // ПОБЕДА
            // стиль Заливка
            mPaint.setStyle(Paint.Style.FILL);
            // закрашиваем холст
            mPaint.setColor(colorBackground);
            canvas.drawPaint(mPaint);
            mPaint.setColor(colorTextWin);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(yD * 2 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(finishGame, (x4 - x3)/2 + x3, (y4 - y3)/2 + y3, mPaint);
            SGFinish.playSound(switchMusic);
            gameOver = true;
            // Новая игра
            mPaint.setColor(colorFigureNewGame);
            canvas.drawRect(X1, y2 + 50, x2, y2 + 50 + yD, mPaint);
            mPaint.setTextSize(yD * 2 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorClickNewGame);
            canvas.drawText(newGame, (x2 - X1)/2 + X1, y2 + 50 + yD*3/4, mPaint);
        }

            // индикаторы // потом убрать
     /*   mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(48);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(String.valueOf(сompFunct.i0), 30, height - 32, mPaint);
        canvas.drawText(String.valueOf(сompFunct.j0), 30, height - 96, mPaint);
        canvas.drawText(String.valueOf(сompFunct.checkWin(arr)), 30, height - 150, mPaint);
       */////////////////

        /// звук выкл/вкл
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(width - xD - 20, height - yD*1/2 -20, width - 20, height - 20, mPaint);
        mPaint.setTextSize(yD * 2 / 5);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(colorClickMute);
        canvas.drawText(mute, xD/2 + width - xD - 20, height - 20 - yD*1/8, mPaint);

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
                        сompFunct.j0 > 0 && gameOver == false) {
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
                        сompFunct.j0 < 3 && gameOver == false) {
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
                        сompFunct.i0 > 0 && gameOver == false) {
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
                        сompFunct.i0 < 3 && gameOver == false) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0+1][сompFunct.j0];
                    arr[сompFunct.i0+1][сompFunct.j0] = tempArr;
                    сompFunct.i0 = сompFunct.i0+1;
                    invalidate();
                }
                // кнопка "НОВАЯ ИГРА"
               if (evX >= X1 &&
                        evX <= x2 &&
                        evY >= y2 + 50 &&
                        evY <= y2 + 50 + yD &&
                        gameOver == true) {
                   gameOver = false;
                   сompFunct.createMixedArray(arr);
                   SG.playSound(switchMusic);
                   invalidate();
               }
                // кнопка "ЗВУК"
                if (evX >= width - xD - 20 &&
                        evX <= width - 20 &&
                        evY >= height - yD*1/2 -20 &&
                        evY <= height - 20
                        ) {
                   switchMusic = !switchMusic;
                }
                break;
        }
        return true;
    }
}