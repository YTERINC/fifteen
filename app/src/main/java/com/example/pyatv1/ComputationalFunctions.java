package com.example.pyatv1;

import java.util.Random;

public class ComputationalFunctions {

    // координаты нулевого значения массива
    int i0;
    int j0;

    public void copyArray(int destArr[][], int srcArr[][]) {
        for(int ic=0; ic<4; ic++)
            for(int jc=0; jc<4; jc++)
                destArr[ic][jc]=srcArr[ic][jc];
    }


    public void createRightArray(int[][] arr) {
        int count = 1; // для заполнения массива по порядку с 1 до 15
         // заполняем массив по порядку
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                arr[i][j] = +count;
                ++count;
            }
        arr[3][3] = 0; // последней клетке присваиваем ноль, т.е. это будет пустая клетка

    }

    public void createMixedArray(int[][] arr) {
        int count = 1; // для заполнения массива по порядку с 1 до 15
        int tempArr;  // для временного хранения значения с нулем
        int rand;
        Random rnd = new Random();
        // заполняем массив по порядку
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                arr[i][j] = + count;
                ++count;
            }
        arr[3][3] = 0; // последней клетке присваиваем ноль, т.е. это будет пустая клетка
        i0 = 3;
        j0 = 3;

        // размешиваем массив правильными ходами
         for (int count2 = 0; count2 < 5; count2++) {
             rand = rnd.nextInt(4); // случайный ход от 0 до 3
              // rand=0 - перемещение пустой клетке (0) влево
              // rand=1 - перемещение пустой клетке (0) вверх
              // rand=2 - перемещение пустой клетке (0) вправо
              // rand=3 - перемещение пустой клетке (0) вниз
             if (i0 == 0 && rand == 1) rand = 3; // т.е. если вверх нельзя переместить мы идем вниз
             if (i0 == 3 && rand == 3) rand = 1; // т.е. если винз нельзя переместить мы идем вверх
             if (j0 == 0 && rand == 0) rand = 2; // т.е. если влево нельзя переместить мы идем вправо
             if (j0 == 3 && rand == 2) rand = 0; // т.е. если вправо нельзя переместить мы идем влево

             switch (rand) {
                 case 0: {
                     tempArr = arr[i0][j0];
                     arr[i0][j0] = arr[i0][j0-1];
                     arr[i0][j0-1] = tempArr;
                     j0 = j0-1; break;}
                 case 1: {
                     tempArr = arr[i0][j0];
                     arr[i0][j0] = arr[i0-1][j0];
                     arr[i0-1][j0] = tempArr;
                     i0 = i0-1; break;}
                 case 2: {
                     tempArr = arr[i0][j0];
                     arr[i0][j0] = arr[i0][j0+1];
                     arr[i0][j0+1] = tempArr;
                     j0 = j0+1; break;}
                 case 3: {
                     tempArr = arr[i0][j0];
                     arr[i0][j0] = arr[i0+1][j0];
                     arr[i0+1][j0] = tempArr;
                     i0 = i0+1; break;}
             }
         }
    }

    public int calculateSmallSquareX1(int sX3, int sXd, int distBetweenSquares, int sJ) {
        return sX3 + distBetweenSquares + (sXd + distBetweenSquares) * sJ;
    }

    public int calculateSmallSquareX2(int sX3, int sXd, int distBetweenSquares, int sJ) {
        return sX3 + distBetweenSquares + sXd + (sXd + distBetweenSquares) * sJ;
    }

    public int calculateSmallSquareY1(int sY3, int sYd, int distBetweenSquares, int sI) {
        return sY3 + distBetweenSquares + (sYd + distBetweenSquares) * sI;
    }

    public int calculateSmallSquareY2(int sY3, int sYd, int distBetweenSquares, int sI) {
        return sY3 + distBetweenSquares + sYd + (sYd + distBetweenSquares) * sI;
    }

    public int sizeOneSquareX(int sX4, int sX3, int distBetweenSquares) {
        return (sX4 - sX3 - distBetweenSquares * 5) / 4;
    }

    public int sizeOneSquareY(int Sy4, int sY3, int distBetweenSquares) {
        return (Sy4 - sY3 - distBetweenSquares * 5) / 4;
    }

    public boolean checkWin(int[][] arrWin) {
        int winCount = 0;
        int winCheck = 0;
        boolean winRes = false;

        for (int i1 = 0; i1 < 4; i1++)
            for (int j1 = 0; j1 < 4; j1++) {
                winCount++;
                if (arrWin[i1][j1] == winCount) winCheck++;
                if (winCheck == 15) winRes = true;
            }
        return winRes;
    }

}