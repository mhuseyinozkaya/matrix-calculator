package org.matrix.matrixcalc;

/**
 *
 * @author muhammed
 */
public class MatrisHesaplama {

    static int[][] matrisTopla(int[][] matrisA, int[][] matrisB, int sign) {

        int[][] matrisSonuc = new int[matrisA.length][matrisA[0].length];

        for (int i = 0; i < matrisA.length; i++) {
            for (int j = 0; j < matrisA[0].length; j++) {
                matrisSonuc[i][j] = matrisA[i][j] + (sign * matrisB[i][j]);
            }
        }

        return matrisSonuc;
    }

    static int[][] matrisCarp(int[][] matrisA, int[][] matrisB) {
        int hucreToplam;
        int[][] matrisSonuc = new int[matrisA.length][matrisB[0].length];

        for (int i = 0; i < matrisA.length; i++) {
            for (int j = 0; j < matrisB[0].length; j++) {
                hucreToplam = 0;

                for (int k = 0; k < matrisB.length; k++) {
                    hucreToplam += matrisA[i][k] * matrisB[k][j];
                }

                matrisSonuc[i][j] = hucreToplam;
            }
        }
        return matrisSonuc;
    }

    /*  Verimsiz determinant hesaplama yöntemi  */
    /*  determinantı hesaplamak için ilk satıra göre laplace genişlemesi yapıyor */
    static int calculateDeterminantLaplace(int[][] matris, int n) {

        int[][] tempMatris = new int[n - 1][n - 1];

        int kofaktor, determinant = 0;

        if (n == 2) {
            determinant = (matris[0][0] * matris[1][1]) - (matris[0][1] * matris[1][0]);
            return determinant;
        } else {

            //***
            for (int k = 0; k < n; k++) {
                for (int i = 1, p = 0; i < n; i++) {
                    for (int j = 0, r = 0; j < n; j++) {

                        if (k == j) {
                            continue;
                        }

                        tempMatris[p][r] = matris[i][j];
                        r++;
                    }
                    p++;
                }

                kofaktor = (int) (Math.pow(-1, 0 + k) * calculateDeterminantLaplace(tempMatris, n - 1));
                determinant += matris[0][k] * kofaktor;

            }
        }

        return determinant;

    }
}
