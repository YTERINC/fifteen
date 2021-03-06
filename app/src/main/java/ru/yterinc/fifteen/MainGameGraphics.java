package ru.yterinc.fifteen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.yterinc.fifteen.R;

public class MainGameGraphics extends View {
    public Paint mPaint = new Paint();
    public ComputationalFunctions compFunct = new ComputationalFunctions();
    public SoundGames SG = new SoundGames(getContext());
    public SoundGames SGFinish = new SoundGames(getContext());

    int[][] arr = new int[4][4];
    int[][] arrDemo = new int[4][4];
    int[][] arrTemp = new int[4][4];
    final int X1 = 10; //отступ рамки, отсюда идет расчет остальных размеров // координата X внешнего квадрата, слева
    int y1 = 250; //отступ рамки, отсюда идет расчет остальных размеров // координата Y внешнего квадрата, слева
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
    boolean MusicOn = true;
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
    String textLabelGame = getResources().getString(R.string.textLabelGame);
    // цвета из ресурсов
    int colorBackground = getResources().getColor(R.color.colorBackground);
    int colorBigSquareOut = getResources().getColor(R.color.colorBigSquareOut);
    int colorBigSquareIn = getResources().getColor(R.color.colorBigSquareIn);
    int colorSmallSquareIn = getResources().getColor(R.color.colorSmallSquareIn);
    int colorTextNumeral = getResources().getColor(R.color.colorTextNumeral);

    int colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOn);
    int colorFigureMuteOff = getResources().getColor(R.color.colorFigureMuteOff);
    int colorTextMuteOn = getResources().getColor(R.color.colorTextMuteOn);
    int colorTextMuteOff = getResources().getColor(R.color.colorTextMuteOff);

    int colorFigureNewGame = getResources().getColor(R.color.colorFigureNewGame);
    int colorTextNewGame = getResources().getColor(R.color.colorTextNewGame);
    int colorFigureExitGame = getResources().getColor(R.color.colorFigureExitGame);
    int colorTextExitGame = getResources().getColor(R.color.colorTextExitGame);
    int colorTextWin = getResources().getColor(R.color.colorTextWin);

    int colorFigureMenuOn = getResources().getColor(R.color.colorFigureMenuOn);
    int colorTextMenuOn = getResources().getColor(R.color.colorTextMenuOn);
    int colorFigureMenuBackToGame = getResources().getColor(R.color.colorFigureMenuBackToGame);
    int colorTextMenuBackToGame = getResources().getColor(R.color.colorTextMenuBackToGame);
    int colorFigureMenuDemo = getResources().getColor(R.color.colorFigureMenuDemo);
    int colorTextMenuDemo = getResources().getColor(R.color.colorTextMenuDemo);
    int colorFigureMenuLabel = getResources().getColor(R.color.colorFigureMenuLabel);
    int colorTextMenuLabel = getResources().getColor(R.color.colorTextMenuLabel);

    int colorBigSquareOutForWin = getResources().getColor(R.color.colorBigSquareOutForWin);
    int colorBigSquareInForWin = getResources().getColor(R.color.colorBigSquareInForWin);
    int colorSmallSquareInForWin = getResources().getColor(R.color.colorSmallSquareInForWin);
    int colorSmallSquareOutForWin = getResources().getColor(R.color.colorSmallSquareOutForWin);
    int colorTextNumeralForWin = getResources().getColor(R.color.colorTextNumeralForWin);

    int colorFigureBlink = getResources().getColor(R.color.colorFigureBlink);

    int colorLabelGame = getResources().getColor(R.color.colorLabelGame);

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
    int menuLabelRectX1;
    int menuLabelRectY1;
    int menuLabelRectX2;
    int menuLabelRectY2;
    int menuLabelTextX;
    int menuLabelTextY;
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

    // координаты текста названия игры
    int labelGameRectX1;
    int labelGameRectY1;
    int labelGameRectX2;
    int labelGameRectY2;
    int labelGameTextX;
    int labelGameTextY;


   public MainGameGraphics(Context context) {
       super(context);
       SG.initSound("click.mp3");
       compFunct.createMixedArray(arr);
       compFunct.createDemoArray(arrDemo);
       SGFinish.initSound("baraban.ogg");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        y1 = width/5;
        x2 = width - X1;
        y2 = x2 - X1 + y1;
        // рамка внутреннего большого квадрата
        x3 = X1 + THICKNESS_BIG_FRAME;
        y3 = y1 + THICKNESS_BIG_FRAME;
        x4 = x2 - THICKNESS_BIG_FRAME;
        y4 = y2 - THICKNESS_BIG_FRAME;
        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);
        // закрашиваем холст
        mPaint.setColor(colorBackground);
        canvas.drawPaint(mPaint);
        mPaint.setAntiAlias(true);

        if (!switchMenu) {
            // Рисуем большой прямоугольник
            mPaint.setColor(colorBigSquareOut);
            canvas.drawRect(X1, y1, x2, y2, mPaint);
            // Рисуем внутренний прямоугольник в большом, для создания рамки
            mPaint.setColor(colorBigSquareIn);
            canvas.drawRect(x3, y3, x4, y4, mPaint);
            // рисуем квадратики
            if (switchDemo) compFunct.copyArray(arrTemp, arrDemo);
            if (switchDemo) compFunct.copyArray(arrTemp, arrDemo);
            else compFunct.copyArray(arrTemp, arr);
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (arrTemp[i][j] != 0) {
                        mPaint.setColor(colorSmallSquareIn);
                        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                        xD = compFunct.sizeOneSquareX(x4, x3, DIST_BETWEEN_SQUARES);
                        yD = compFunct.sizeOneSquareY(y4, y3, DIST_BETWEEN_SQUARES);
                        xin1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, j);
                        xin2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, j);
                        yin1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, i);
                        yin2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, i);
                        canvas.drawRoundRect(xin1, yin1, xin2, yin2, 20, 20, mPaint);
                        mPaint.setColor(colorTextNumeral);
                        mPaint.setTextSize((float) yD*2/3);
                        mPaint.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText(String.valueOf(arrTemp[i][j]), (float) (xin2 - (xin2 - xin1)/2), (float) (yin2 - (yin2 - yin1)/2 + yD/4), mPaint);
                    }
                }
        }

        // кнопка МЕНЮ
        if (!gameOver && !switchMenu) { // отображать только вовремя игры
            menuRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD + yD/2;
            menuRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 0)*2;
            menuRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD;
            menuTextX = menuRectX1 + (menuRectX2 - menuRectX1)/2;
            menuTextY = menuRectY2 - (menuRectY2 - menuRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureMenuOn);
            canvas.drawRoundRect(menuRectX1, menuRectY1, menuRectX2, menuRectY2, 35, 35, mPaint);
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuOn);
            canvas.drawText(textMenu, menuTextX, menuTextY, mPaint);

            labelGameRectX1 = 0;
            labelGameRectY1 = 0;
            labelGameRectX2 = width;
            labelGameRectY2 = y1;
            labelGameTextX = labelGameRectX1 + (labelGameRectX2 - labelGameRectX1)/2;
            labelGameTextY = labelGameRectY2 - (labelGameRectY2 - labelGameRectY1)/2 + yD/8;
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorLabelGame);
            canvas.drawText(textLabelGame, labelGameTextX, labelGameTextY, mPaint);
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        // МЕНЮ
        if (!gameOver && switchMenu) {
            menuLabelRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuLabelRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 0) - yD*2/3;
            menuLabelRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuLabelRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 0) - yD;
            menuLabelTextX =  menuLabelRectX1 + (menuLabelRectX2 -  menuLabelRectX1)/2;
            menuLabelTextY = menuLabelRectY2 - (menuLabelRectY2 - menuLabelRectY1)/2 + yD/6;
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorLabelGame);
            canvas.drawText(textLabelGame, menuLabelTextX, menuLabelTextY, mPaint);

            menuNewGameRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuNewGameRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 2) - yD/2;
            menuNewGameRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuNewGameRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 2) - yD;
            menuNewGameTextX =  menuNewGameRectX1 + (menuNewGameRectX2 -  menuNewGameRectX1)/2;
            menuNewGameTextY = menuNewGameRectY2 - (menuNewGameRectY2 - menuNewGameRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureNewGame);
            canvas.drawRoundRect(menuNewGameRectX1, menuNewGameRectY1, menuNewGameRectX2, menuNewGameRectY2, 35, 35, mPaint);
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextNewGame);
            canvas.drawText(textNewGame, menuNewGameTextX, menuNewGameTextY, mPaint);

            menuExitGameRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuExitGameRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 4) - yD/2;
            menuExitGameRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuExitGameRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 4) - yD;
            menuExitGameTextX =  menuExitGameRectX1 + (menuExitGameRectX2 -  menuExitGameRectX1)/2;
            menuExitGameTextY = menuExitGameRectY2 - (menuExitGameRectY2 - menuExitGameRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureExitGame);
            canvas.drawRoundRect(menuExitGameRectX1, menuExitGameRectY1, menuExitGameRectX2, menuExitGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextExitGame);
            canvas.drawText(textExitGame, menuExitGameTextX, menuExitGameTextY, mPaint);

            menuDemoGameRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuDemoGameRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3) - yD/2;
            menuDemoGameRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuDemoGameRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) - yD;
            menuDemoGameTextX =  menuDemoGameRectX1 + (menuDemoGameRectX2 -  menuDemoGameRectX1)/2;
            menuDemoGameTextY = menuDemoGameRectY2 - (menuDemoGameRectY2 - menuDemoGameRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureMenuDemo);
            canvas.drawRoundRect(menuDemoGameRectX1, menuDemoGameRectY1, menuDemoGameRectX2, menuDemoGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuDemo);
            canvas.drawText(textMenuDemoGame, menuDemoGameTextX, menuDemoGameTextY, mPaint);

            menuBackToGameRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 0);
            menuBackToGameRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 1) - yD/2;
            menuBackToGameRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            menuBackToGameRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 1) - yD;
            menuBackToGameTextX =  menuBackToGameRectX1 + (menuBackToGameRectX2 -  menuBackToGameRectX1)/2;
            menuBackToGameTextY = menuBackToGameRectY2 - (menuBackToGameRectY2 - menuBackToGameRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureMenuBackToGame);
            canvas.drawRoundRect(menuBackToGameRectX1, menuBackToGameRectY1, menuBackToGameRectX2, menuBackToGameRectY2, 35, 35, mPaint);
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMenuBackToGame);
            canvas.drawText(textMenuBackToGame, menuBackToGameTextX, menuBackToGameTextY, mPaint);
        }

        // звук выкл/вкл
        if (!gameOver && !switchMenu && !switchDemo) { // отображать только вовремя игры
            muteRectX1 = compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, 3);
            muteRectY1 = compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD + yD/2;
            muteRectX2 = compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, 3);
            muteRectY2 = compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, 3) + yD;
            muteTextX = muteRectX1 + (muteRectX2 - muteRectX1)/2;
            muteTextY = muteRectY2 - (muteRectY2 - muteRectY1)/2 + yD/8;
            mPaint.setColor(colorFigureMuteOn);
            canvas.drawRoundRect(muteRectX1, muteRectY1, muteRectX2, muteRectY2, 35, 35, mPaint);
            mPaint.setTextSize((float) yD*1/3); // надо придумать множитель для разной длины текста
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextMuteOn);
            canvas.drawText(textMute, muteTextX, muteTextY, mPaint);
        }
        // ПОБЕДА
        if (compFunct.checkWin(arr) && !gameOver && !switchDemo) {
     //   if (compFunct.checkWin(arr) == true && switchDemo == false) {
           SGFinish.playSound(MusicOn);
           // меняем цвет внутреннего большого квадрата на победный, при нажатии кнопки НОВАЯ ИГРА возвращаем цвета назад
           colorBigSquareIn = colorBigSquareInForWin;
           colorBigSquareOut = colorBigSquareOutForWin;
           colorSmallSquareIn = colorSmallSquareInForWin;
           colorTextNumeral = colorTextNumeralForWin;
           gameOver = true;
         }
        if (gameOver && !switchDemo) {
            newGameRectX1 = X1;
            newGameRectY1 = y2 + yD/8;
            newGameRectX2 = x2;
            newGameRectY2 = y2 + yD/2 + yD/8;
            newGameTextX = (newGameRectX2 - newGameRectX1)/2 + newGameRectX1;
            newGameTextY = newGameRectY2 - (newGameRectY2 - newGameRectY1)/2 + yD/8;

            exitGameRectX1 = X1;
            exitGameRectY1 = y1 - yD/2 - yD/8;
            exitGameRectX2 = x2;
            exitGameRectY2 = y1 - yD/8;
            exitGameTextX = (exitGameRectX2 - exitGameRectX1)/2 + exitGameRectX1;
            exitGameTextY = exitGameRectY2 - (exitGameRectY2 - exitGameRectY1)/2 + yD/8;
            ///////////////////
            mPaint.setColor(colorTextWin);
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mPaint.setTextSize((float) yD*2/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(textFinishGame, (float) ((x4 - x3)/2 + x3), (float) ((y4 - y3)/2 + y3), mPaint);
            // Новая игра
            mPaint.setColor(colorFigureNewGame);
            canvas.drawRect(newGameRectX1, newGameRectY1, newGameRectX2, newGameRectY2, mPaint);
            mPaint.setTextSize((float) yD*1/3);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(colorTextNewGame);
            canvas.drawText(textNewGame, newGameTextX, newGameTextY, mPaint);
            // Выход из игры
            mPaint.setColor(colorFigureExitGame);
            canvas.drawRect(exitGameRectX1, exitGameRectY1, exitGameRectX2 , exitGameRectY2, mPaint);
            mPaint.setColor(colorTextExitGame);
            canvas.drawText(textExitGame, exitGameTextX, exitGameTextY, mPaint);
            invalidate(); // перерисовываем
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        // координаты Touch-события
        float evX = event.getX();
        float evY = event.getY();
        int tempArr;  // для временного хранения значения с нулем

        switch (event.getAction()) {
            // касание началось
            case MotionEvent.ACTION_UP:
                // если касание было начато в пределах квадрата
                if (evX >= compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0-1) &&
                        evX <= compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0-1) &&
                        evY >= compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0) &&
                        evY <= compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0) &&
                        compFunct.j0 > 0 &&
                        !gameOver &&
                        !switchMenu &&
                        !switchDemo) {
                    SG.playSound(MusicOn);
                    tempArr = arr[compFunct.i0][compFunct.j0];
                    arr[compFunct.i0][compFunct.j0] = arr[compFunct.i0][compFunct.j0-1];
                    arr[compFunct.i0][compFunct.j0-1] = tempArr;
                    compFunct.j0 = compFunct.j0-1;
                     invalidate();
                }
                if (evX >= compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0+1) &&
                        evX <= compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0+1) &&
                        evY >= compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0) &&
                        evY <= compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0) &&
                        compFunct.j0 < 3 &&
                        !gameOver &&
                        !switchMenu &&
                        !switchDemo) {
                    SG.playSound(MusicOn);
                    tempArr = arr[compFunct.i0][compFunct.j0];
                    arr[compFunct.i0][compFunct.j0] = arr[compFunct.i0][compFunct.j0+1];
                    arr[compFunct.i0][compFunct.j0+1] = tempArr;
                    compFunct.j0 = compFunct.j0+1;
                    invalidate();
                }
                if (evX >= compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0) &&
                        evX <= compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0) &&
                        evY >= compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0-1) &&
                        evY <= compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0-1) &&
                        compFunct.i0 > 0 &&
                        !gameOver &&
                        !switchMenu &&
                        !switchDemo) {
                    SG.playSound(MusicOn);
                    tempArr = arr[compFunct.i0][compFunct.j0];
                    arr[compFunct.i0][compFunct.j0] = arr[compFunct.i0-1][compFunct.j0];
                    arr[compFunct.i0-1][compFunct.j0] = tempArr;
                    compFunct.i0 = compFunct.i0-1;
                    invalidate();
                }
                if (evX >= compFunct.calculateSmallSquareX1(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0) &&
                        evX <= compFunct.calculateSmallSquareX2(x3, xD, DIST_BETWEEN_SQUARES, compFunct.j0) &&
                        evY >= compFunct.calculateSmallSquareY1(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0+1) &&
                        evY <= compFunct.calculateSmallSquareY2(y3, yD, DIST_BETWEEN_SQUARES, compFunct.i0+1) &&
                        compFunct.i0 < 3 &&
                        !gameOver &&
                        !switchMenu &&
                        !switchDemo) {
                    SG.playSound(MusicOn);
                    tempArr = arr[compFunct.i0][compFunct.j0];
                    arr[compFunct.i0][compFunct.j0] = arr[compFunct.i0+1][compFunct.j0];
                    arr[compFunct.i0+1][compFunct.j0] = tempArr;
                    compFunct.i0 = compFunct.i0+1;
                    invalidate();
                }
                // кнопка "МЕНЮ"
                if (evX >= menuRectX1 &&
                        evX <= menuRectX2 &&
                        evY >= menuRectY1 &&
                        evY <= menuRectY2 &&
                        !gameOver &&
                        !switchMenu) {
                    SG.playSound(MusicOn);
                    switchMenu = true;
                    backToBasicColor();
                    invalidate();
                }
                // кнопка "ЗВУК"
                if (evX >= muteRectX1 &&
                        evX <= muteRectX2 &&
                        evY >= muteRectY1 &&
                        evY <= muteRectY2 &&
                        !gameOver &&
                        !switchMenu &&
                        !switchDemo) {
                    MusicOn = !MusicOn;
                    if (!MusicOn) {
                        colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOff);
                    }
                    if (MusicOn) {
                        colorFigureMuteOn = getResources().getColor(R.color.colorFigureMuteOn);
                    }
                    invalidate();
                }
                // кнопка "НОВАЯ ИГРА"
               if (evX >= newGameRectX1 &&
                        evX <= newGameRectX2 &&
                        evY >= newGameRectY1 &&
                        evY <= newGameRectY2 &&
                       gameOver) {
                   compFunct.createMixedArray(arr);
                   SG.playSound(MusicOn);
                   // убираем победные цвета внутреннего большого квадрата, возвращаем обычные из ресурсов
                   colorBigSquareIn = getResources().getColor(R.color.colorBigSquareIn);
                   colorBigSquareOut = getResources().getColor(R.color.colorBigSquareOut);
                   colorSmallSquareIn = getResources().getColor(R.color.colorSmallSquareIn);
                   colorTextNumeral = getResources().getColor(R.color.colorTextNumeral);
                   backToBasicColor();
                   gameOver = false;
                   switchDemo = false;
                   invalidate();
                   }
                // кнопка "ВЫХОД"
                if (evX >= exitGameRectX1 &&
                        evX <= exitGameRectX2 &&
                        evY >= exitGameRectY1 &&
                        evY <= exitGameRectY2 &&
                        gameOver) {
                    backToBasicColor();
                    finishFunction();
                }
                // кнопка "НОВАЯ ИГРА" в МЕНЮ
                if (evX >= menuNewGameRectX1 &&
                        evX <= menuNewGameRectX2 &&
                        evY >= menuNewGameRectY1 &&
                        evY <=menuNewGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    switchMenu = false;
                    compFunct.createMixedArray(arr);
                    SG.playSound(MusicOn);
                    switchDemo = false;
                    backToBasicColor();
                    invalidate();
                }
                // кнопка "ВЫХОД" в МЕНЮ
                if (evX >= menuExitGameRectX1 &&
                        evX <= menuExitGameRectX2 &&
                        evY >= menuExitGameRectY1 &&
                        evY <=menuExitGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    backToBasicColor();
                    finishFunction();
                }
                // кнопка "НАЗАД В ИГРУ" в МЕНЮ
                if (evX >= menuBackToGameRectX1 &&
                        evX <= menuBackToGameRectX2 &&
                        evY >= menuBackToGameRectY1 &&
                        evY <=menuBackToGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    switchMenu = false;
                    switchDemo = false;
                    SG.playSound(MusicOn);
                    backToBasicColor();
                    invalidate();
                }
                // кнопка "ДЕМОНСТРАЦИЯ" в МЕНЮ
                if (evX >= menuDemoGameRectX1 &&
                        evX <= menuDemoGameRectX2 &&
                        evY >= menuDemoGameRectY1 &&
                        evY <=menuDemoGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    switchDemo = true;
                    switchMenu = false;
                    SG.playSound(MusicOn);
                    backToBasicColor();
                    invalidate();
                }
                break;
/////////////////////////// для мигания кнопок
            case MotionEvent.ACTION_DOWN:
                // кнопка "МЕНЮ"
                if (evX >= menuRectX1 &&
                        evX <= menuRectX2 &&
                        evY >= menuRectY1 &&
                        evY <= menuRectY2 &&
                        !gameOver &&
                        !switchMenu) {
                    colorFigureMenuOn = getResources().getColor(R.color.colorFigureBlink); // делаем другой цвет кнопки МЕНЮ
                    invalidate();
                }
// кнопка "НОВАЯ ИГРА"
               if (evX >= newGameRectX1 &&
                        evX <= newGameRectX2 &&
                        evY >= newGameRectY1 &&
                        evY <= newGameRectY2 &&
                       gameOver) {
                   colorFigureNewGame = getResources().getColor(R.color.colorFigureBlink);
                   invalidate();
                   }
                // кнопка "ВЫХОД"
                if (evX >= exitGameRectX1 &&
                        evX <= exitGameRectX2 &&
                        evY >= exitGameRectY1 &&
                        evY <= exitGameRectY2 &&
                        gameOver) {
                    colorFigureExitGame = getResources().getColor(R.color.colorFigureBlink);
                    invalidate();
                }
                // кнопка "НОВАЯ ИГРА" в МЕНЮ
                if (evX >= menuNewGameRectX1 &&
                        evX <= menuNewGameRectX2 &&
                        evY >= menuNewGameRectY1 &&
                        evY <=menuNewGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    colorFigureNewGame = getResources().getColor(R.color.colorFigureBlink);
                    invalidate();
                }
                // кнопка "ВЫХОД" в МЕНЮ
                if (evX >= menuExitGameRectX1 &&
                        evX <= menuExitGameRectX2 &&
                        evY >= menuExitGameRectY1 &&
                        evY <=menuExitGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    colorFigureExitGame = getResources().getColor(R.color.colorFigureBlink);
                    invalidate();
                }
                // кнопка "НАЗАД В ИГРУ" в МЕНЮ
                if (evX >= menuBackToGameRectX1 &&
                        evX <= menuBackToGameRectX2 &&
                        evY >= menuBackToGameRectY1 &&
                        evY <=menuBackToGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    colorFigureMenuBackToGame = getResources().getColor(R.color.colorFigureBlink);
                    invalidate();
                }
                // кнопка "ДЕМОНСТРАЦИЯ" в МЕНЮ
                if (evX >= menuDemoGameRectX1 &&
                        evX <= menuDemoGameRectX2 &&
                        evY >= menuDemoGameRectY1 &&
                        evY <=menuDemoGameRectY2 &&
                        !gameOver &&
                        switchMenu) {
                    colorFigureMenuDemo = getResources().getColor(R.color.colorFigureBlink);
                    invalidate();
                }

              break;

            case MotionEvent.ACTION_MOVE:
                backToBasicColor();
                invalidate();
        }
        return true;
    }
    void backToBasicColor() {
        colorFigureMenuOn = getResources().getColor(R.color.colorFigureMenuOn);
        colorFigureNewGame = getResources().getColor(R.color.colorFigureNewGame);
        colorFigureExitGame = getResources().getColor(R.color.colorFigureExitGame);
        colorFigureMenuBackToGame = getResources().getColor(R.color.colorFigureMenuBackToGame);
        colorFigureMenuDemo = getResources().getColor(R.color.colorFigureMenuDemo);
       }

    private void finishFunction() {
        Activity activity = (Activity)getContext();
        activity.finish();
    }
}