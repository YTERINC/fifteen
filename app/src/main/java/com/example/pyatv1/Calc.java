package com.example.pyatv1;

import java.util.Random;

public class Calc {
    int i0;
    int j0;

   // int[][] arrcalc = new int[4][4];

    public void initArr(int[][] arrcalc) {

        int k;
        int i;
        int j;
        int i1;
        int j1;

        Random rnd = new Random();

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                arrcalc[i][j] = 77;
            }

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                do {
                    k=0;
                    arrcalc[i][j] = rnd.nextInt(16);
                    for (i1 = 0; i1 < 4; i1++)
                        for (j1 = 0; j1 < 4; j1++) {
                            if (arrcalc[i][j] == arrcalc[i1][j1])
                                k++;
                        }
                }
                while (k > 1);

                if (arrcalc[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                }

            }


    }


    public int Sxin1(int Sx3, int Sxd, int Sdrect2, int Sj) {
        return Sx3 + Sdrect2 + (Sxd + Sdrect2) * Sj;
    }

    public int Sxin2(int Sx3, int Sxd, int Sdrect2, int Sj) {
        return Sx3 + Sdrect2 + Sxd + (Sxd + Sdrect2) * Sj;
    }

    public int Syin1(int Sy3, int Syd, int Sdrect2, int Si) {
        return Sy3 + Sdrect2 + (Syd + Sdrect2) * Si;
    }

    public int Syin2(int Sy3, int Syd, int Sdrect2, int Si) {
        return Sy3 + Sdrect2 + Syd + (Syd + Sdrect2) * Si;
    }

    public int Fxd(int Sx4, int Sx3, int Sdrect2) {
        return (Sx4 - Sx3 - Sdrect2 * 5) / 4;
    }

        public int Fyd(int Sy4, int Sy3, int Sdrect2) {
        return (Sy4 - Sy3 - Sdrect2 * 5) / 4;
    }

}
