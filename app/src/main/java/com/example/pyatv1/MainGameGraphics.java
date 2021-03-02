package com.example.pyatv1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.app.ActivityCompat;

public class MainGameGraphics extends View {
    public Paint mPaint = new Paint();
    public ComputationalFunctions сompFunct = new ComputationalFunctions();
    public SoundGames SG = new SoundGames(getContext());
    public SoundGames SGFinish = new SoundGames(getContext());

    int[][] arr = new int[4][4];
    final int X1 = 10; //отступ рамки, отсюда идет расчет остальных размеров // координата X внешнего квадрата, слева
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
    // индикатор активации меню
    boolean switchMenu = false;
    // индикатор демонстрации правильного варианта
    boolean switchDemo = false;
    // размеры экрана
    int width;
    int height;
    // текст из ресурсов
    String textFinishGame = getResources().getString(R.string.textFinishGame);
    String textNewGame = getResources().getString(R.string.textNewGame);
    String textMute = getResources().getString(R.string.textMute);
    String textExitGame = getResources().getString(R.string.textExitGame);
    String textMenu = getResources().getString(R.string.textMenu);
    String textMenuBackToGame = getResources().getString(R.string.textMenuBackToGame);
    String textMenuDemoGame = getResources().getString(R.string.textMenuDemoGame);
    // цвета из ресурсов
    int colorBackground = getResources().getColor(R.color.colorBackground);
    int colorBigFrameOut = getResources().getColor(R.color.colorBigFrameOut);
    int colorBigFrameIn = getResources().getColor(R.color.colorBigFrameIn);
    int colorSmallSquare = getResources().getColor(R.color.colorSmallSquare);
    int colorSmallSquareFrame = getResources().getColor(R.color.colorSmallSquareFrame);
    int colorTextMuteOn = getResources().getColor(R.color.colorTextMuteOn);
    int colorTextMuteOff = getResources().getColor(R.color.colorTextMuteOff);
    int colorFigureNewGame = getResources().getColor(R.color.colorFigureNewGame);
    int colorFigureExitGame = getResources().getColor(R.color.colorFigureExitGame);
    int colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOn);
    int colorFigureMuteOff = getResources().getColor(R.color.colorFigureMuteOff);
    int colorFigureMenuOn = getResources().getColor(R.color.colorFigureMenuOn);
    int colorTextMenuOn = getResources().getColor(R.color.colorTextMenuOn);
    int colorFigureMenuBackToGame = getResources().getColor(R.color.colorFigureMenuBackToGame);
    int colorTextMenuBackToGame = getResources().getColor(R.color.colorTextMenuBackToGame);
    int colorTextMenuDemo = getResources().getColor(R.color.colorTextMenuDemo);
    int colorFigureMenuDemo = getResources().getColor(R.color.colorFigureMenuDemo);

    int colorBigFrameOutForWin = getResources().getColor(R.color.colorBigFrameOutForWin);
    int colorBigFrameInForWin = getResources().getColor(R.color.colorBigFrameInForWin);
    int colorSmallSquareForWin = getResources().getColor(R.color.colorSmallSquareForWin);
    int colorSmallSquareFrameForWin = getResources().getColor(R.color.colorSmallSquareFrameForWin);

    int colorClickNewGame = getResources().getColor(R.color.colorClickNewGame);
    int colorClickMute = getResources().getColor(R.color.colorClickMute);
    int colorTextWin = getResources().getColor(R.color.colorTextWin);
    int colorTextNumeral = getResources().getColor(R.color.colorTextNumeral);
    int colorTextExitGame = getResources().getColor(R.color.colorTextExitGame);
    int colorTextNumeralForWin = getResources().getColor(R.color.colorTextNumeralForWin);

    // координаты для кнопки звука
    int muteRectX1;
    int muteRectY1;
    int muteRectX2;
    int muteRectY2;
    int muteTextX;
    int muteTextY;
    // координаты для кнопки НОВАЯ ИГРА
    int newGameRectX1;
    int newGameRectY1;
    int newGameRectX2;
    int newGameRectY2;
    int newGameTextX;
    int newGameTextY;
    // координаты для кнопки ВЫХОД
    int exitGameRectX1;
    int exitGameRectY1;
    int exitGameRectX2;
    int exitGameRectY2;
    int exitGameTextX;
    int exitGameTextY;
    // координаты для кнопки МЕНЮ
    int menuRectX1;
    int menuRectY1;
    int menuRectX2;
    int menuRectY2;
    int menuTextX;
    int menuTextY;
    // координаты пунктов МЕНЮ
    int menuNewGameRectX1;
    int menuNewGameRectY1;
    int menuNewGameRectX2;
    int menuNewGameRectY2;
    int menuNewGameTextX;
    int menuNewGameTextY;
    int menuExitGameRectX1;
    int menuExitGameRectY1;
    int menuExitGameRectX2;
    int menuExitGameRectY2;
    int menuExitGameTextX;
    int menuExitGameTextY;
    int menuDemoGameRectX1;
    int menuDemoGameRectY1;
    int menuDemoGameRectX2;
    int menuDemoGameRectY2;
    int menuDemoGameTextX;
    int menuDemoGameTextY;
    int menuBackToGameRectX1;
    int menuBackToGameRectY1;
    int menuBackToGameRectX2;
    int menuBackToGameRectY2;
    int menuBackToGameTextX;
    int menuBackToGameTextY;


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
        // Рисуем большой прямоугольник
        mPaint.setColor(colorBigFrameOut);
        canvas.drawRect(X1, Y1, x2, y2, mPaint);
        // Рисуем внутренний прямоугольник в большом, для создания рамки
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
                    canvas.drawRoundRect(xin1, yin1, xin2, yin2, 20,20, mPaint);
                    mPaint.setColor(colorTextNumeral);
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setAntiAlias(true);
                    mPaint.setTextSize(yD*2/3);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(String.valueOf(arr[i][j]), xin2 - (xin2 - xin1) / 2, yin2 - (yin2 - yin1) / 2 + yD / 4, mPaint);
                }
        }

        // кнопка МЕНЮ
        if (gameOver == false && switchMenu == false) { // отображать только вовремя игры
            menuRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD + yD / 2;
            menuRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 0)*2;
            menuRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD;
            menuTextX = menuRectX1 + (menuRectX2 - menuRectX1) / 2;
            menuTextY = menuRectY2 - (menuRectY2 - menuRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureMenuOn);
            canvas.drawRoundRect(menuRectX1, menuRectY1, menuRectX2, menuRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuOn);
            canvas.drawText(textMenu, menuTextX, menuTextY, mPaint);
        }

        // МЕНЮ
        if (gameOver == false && switchMenu == true) {
            // стиль Заливка
            mPaint.setStyle(Paint.Style.FILL);
            // закрашиваем холст
            mPaint.setColor(colorBackground);
            canvas.drawPaint(mPaint);

            menuNewGameRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuNewGameRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 0);
            menuNewGameRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuNewGameRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 0) - yD/2;
            menuNewGameTextX =  menuNewGameRectX1 + (menuNewGameRectX2 -  menuNewGameRectX1) / 2;
            menuNewGameTextY = menuNewGameRectY2 - (menuNewGameRectY2 - menuNewGameRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureNewGame);
            canvas.drawRoundRect(menuNewGameRectX1, menuNewGameRectY1, menuNewGameRectX2, menuNewGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorClickNewGame);
            canvas.drawText(textNewGame, menuNewGameTextX, menuNewGameTextY, mPaint);

            menuExitGameRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuExitGameRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 1);
            menuExitGameRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuExitGameRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 1) - yD/2;
            menuExitGameTextX =  menuExitGameRectX1 + (menuExitGameRectX2 -  menuExitGameRectX1) / 2;
            menuExitGameTextY = menuExitGameRectY2 - (menuExitGameRectY2 - menuExitGameRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureExitGame);
            canvas.drawRoundRect(menuExitGameRectX1, menuExitGameRectY1, menuExitGameRectX2, menuExitGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextExitGame);
            canvas.drawText(textExitGame, menuExitGameTextX, menuExitGameTextY, mPaint);

            menuDemoGameRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuDemoGameRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 2);
            menuDemoGameRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuDemoGameRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 2) - yD/2;
            menuDemoGameTextX =  menuDemoGameRectX1 + (menuDemoGameRectX2 -  menuDemoGameRectX1) / 2;
            menuDemoGameTextY = menuDemoGameRectY2 - (menuDemoGameRectY2 - menuDemoGameRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureMenuDemo);
            canvas.drawRoundRect(menuDemoGameRectX1, menuDemoGameRectY1, menuDemoGameRectX2, menuDemoGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuDemo);
            canvas.drawText(textMenuDemoGame, menuDemoGameTextX, menuDemoGameTextY, mPaint);

            menuBackToGameRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuBackToGameRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3);
            menuBackToGameRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuBackToGameRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) - yD/2;
            menuBackToGameTextX =  menuBackToGameRectX1 + (menuBackToGameRectX2 -  menuBackToGameRectX1) / 2;
            menuBackToGameTextY = menuBackToGameRectY2 - (menuBackToGameRectY2 - menuBackToGameRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureMenuBackToGame);
            canvas.drawRoundRect(menuBackToGameRectX1, menuBackToGameRectY1, menuBackToGameRectX2, menuBackToGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuBackToGame);
            canvas.drawText(textMenuBackToGame, menuBackToGameTextX, menuBackToGameTextY, mPaint);
        }

        // звук выкл/вкл
        if (gameOver == false && switchMenu == false && switchDemo == false) { // отображать только вовремя игры
            muteRectX1 = сompFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 3);
            muteRectY1 = сompFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD + yD / 2;
            muteRectX2 = сompFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            muteRectY2 = сompFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD;
            muteTextX = muteRectX1 + (muteRectX2 - muteRectX1) / 2;
            muteTextY = muteRectY2 - (muteRectY2 - muteRectY1) / 2 + yD / 8;
            mPaint.setColor(colorFigureMuteOn);
            canvas.drawRoundRect(muteRectX1, muteRectY1, muteRectX2, muteRectY2, 35, 35, mPaint);
            mPaint.setTextSize(yD * 1 / 3); // надо придумать множитель для разной длины текста
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMuteOn);
            canvas.drawText(textMute, muteTextX, muteTextY, mPaint);
        }

        if (сompFunct.checkWin(arr) == true && gameOver == false && switchDemo == false) {

            SGFinish.playSound(switchMusic);
            // ПОБЕДА
            // стиль Заливка
            mPaint.setStyle(Paint.Style.FILL);
            // закрашиваем холст
            //mPaint.setColor(colorBackground);
           // меняем цвет внутреннего большого квадрата на победный, при нажатии кнопки НОВАЯ ИГРА возвращаем цвета назад
           colorBigFrameIn = colorBigFrameInForWin;
           colorBigFrameOut = colorBigFrameOutForWin;
           colorSmallSquare = colorSmallSquareForWin;
           colorTextNumeral = colorTextNumeralForWin;
           gameOver = true;
         //  invalidate();
        }

        if (gameOver == true && switchDemo == false) {

            newGameRectX1 = X1;
            newGameRectY1 = y2 + 50;
            newGameRectX2 = x2;
            newGameRectY2 = y2 + yD - 50;
            newGameTextX = (x2 - X1)/2 + X1;
            newGameTextY = y2 + yD*3/4;

            exitGameRectX1 = X1;
            exitGameRectY1 = Y1 - yD + 50;
            exitGameRectX2 = x2;
            exitGameRectY2 = Y1 - 50;
            exitGameTextX = (x2 - X1)/2 + X1;
            exitGameTextY = Y1 - yD*1/4;
            ///////////////////
            //canvas.drawPaint(mPaint);
            mPaint.setColor(colorTextWin);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(yD * 2 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(textFinishGame, (x4 - x3)/2 + x3, (y4 - y3)/2 + y3, mPaint);
            // Новая игра
            mPaint.setColor(colorFigureNewGame);
            canvas.drawRect(newGameRectX1, newGameRectY1, newGameRectX2, newGameRectY2, mPaint);
            mPaint.setTextSize(yD * 2 / 3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorClickNewGame);
            canvas.drawText(textNewGame, newGameTextX, newGameTextY, mPaint);
            // Выход из игры
            mPaint.setColor(colorFigureExitGame);
            canvas.drawRect(exitGameRectX1, exitGameRectY1, exitGameRectX2 , exitGameRectY2, mPaint);
            mPaint.setColor(colorTextExitGame);
            canvas.drawText(textExitGame, exitGameTextX, exitGameTextY, mPaint);
            invalidate(); // перерисовываем

        }
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
                        сompFunct.j0 > 0 &&
                        gameOver == false &&
                        switchMenu == false &&
                        switchDemo == false) {
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
                        сompFunct.j0 < 3 &&
                        gameOver == false &&
                        switchMenu == false &&
                        switchDemo == false) {
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
                        сompFunct.i0 > 0 &&
                        gameOver == false &&
                        switchMenu == false &&
                        switchDemo == false) {
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
                        сompFunct.i0 < 3 &&
                        gameOver == false &&
                        switchMenu == false &&
                        switchDemo == false) {
                    SG.playSound(switchMusic);
                    tempArr = arr[сompFunct.i0][сompFunct.j0];
                    arr[сompFunct.i0][сompFunct.j0] = arr[сompFunct.i0+1][сompFunct.j0];
                    arr[сompFunct.i0+1][сompFunct.j0] = tempArr;
                    сompFunct.i0 = сompFunct.i0+1;
                    invalidate();
                }

                // кнопка "МЕНЮ"
                if (evX >= menuRectX1 &&
                        evX <= menuRectX2 &&
                        evY >= menuRectY1 &&
                        evY <= menuRectY2 &&
                        gameOver == false &&
                        switchMenu == false) {

                    SG.playSound(switchMusic);
                    switchMenu = true;
                    invalidate();

                }
                // кнопка "ЗВУК"
                if (evX >= muteRectX1 &&
                        evX <= muteRectX2 &&
                        evY >= muteRectY1 &&
                        evY <= muteRectY2 &&
                        gameOver == false &&
                        switchMenu == false &&
                        switchDemo == false) {
                    switchMusic = !switchMusic;

                    if (switchMusic == false) {
                        colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOff);
                    }
                    if (switchMusic == true) {
                        colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOn);
                    }
                    invalidate();
                }
                // кнопка "НОВАЯ ИГРА"
               if (evX >= newGameRectX1 &&
                        evX <= newGameRectX2 &&
                        evY >= newGameRectY1 &&
                        evY <= newGameRectY2 &&
                        gameOver == true) {
                   сompFunct.createMixedArray(arr);
                   SG.playSound(switchMusic);
                   // убираем победные цвета внутреннего большого квадрата, возвращаем обычные из ресурсов
                   colorBigFrameIn = getResources().getColor(R.color.colorBigFrameIn);
                   colorBigFrameOut = getResources().getColor(R.color.colorBigFrameOut);
                   colorSmallSquare = getResources().getColor(R.color.colorSmallSquare);
                   colorTextNumeral = getResources().getColor(R.color.colorTextNumeral);
                   gameOver = false;
                   switchDemo = false;
                   invalidate();
                   }
                // кнопка "ВЫХОД"
                if (evX >= exitGameRectX1 &&
                        evX <= exitGameRectX2 &&
                        evY >= exitGameRectY1 &&
                        evY <= exitGameRectY2 &&
                        gameOver == true) {

                    System.exit(0);
                }
                // кнопка "НОВАЯ ИГРА" в МЕНЮ
                if (evX >= menuNewGameRectX1 &&
                        evX <= menuNewGameRectX2 &&
                        evY >= menuNewGameRectY1 &&
                        evY <=menuNewGameRectY2 &&
                        gameOver == false &&
                        switchMenu == true) {
                    switchMenu = false;
                    сompFunct.createMixedArray(arr);
                    SG.playSound(switchMusic);
                    switchDemo = false;
                    invalidate();
                }
                // кнопка "ВЫХОД" в МЕНЮ
                if (evX >= menuExitGameRectX1 &&
                        evX <= menuExitGameRectX2 &&
                        evY >= menuExitGameRectY1 &&
                        evY <=menuExitGameRectY2 &&
                        gameOver == false &&
                        switchMenu == true) {
                    System.exit(0);
                }
                // кнопка "НАЗАД В ИГРУ" в МЕНЮ
                if (evX >= menuBackToGameRectX1 &&
                        evX <= menuBackToGameRectX2 &&
                        evY >= menuBackToGameRectY1 &&
                        evY <=menuBackToGameRectY2 &&
                        gameOver == false &&
                        switchMenu == true) {
                    switchMenu = false;
                    switchDemo = false;
                    SG.playSound(switchMusic);
                    invalidate();
                }
                // кнопка "ДЕМОНСТРАЦИЯ" в МЕНЮ
                if (evX >= menuDemoGameRectX1 &&
                        evX <= menuDemoGameRectX2 &&
                        evY >= menuDemoGameRectY1 &&
                        evY <=menuDemoGameRectY2 &&
                        gameOver == false &&
                        switchMenu == true) {
                    switchDemo = true;
                    switchMenu = false;
                    сompFunct.createRightArray(arr);
                    SG.playSound(switchMusic);
                    invalidate();
                }
                break;
        }
        return true;
    }




}