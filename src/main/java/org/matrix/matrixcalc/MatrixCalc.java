/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.matrix.matrixcalc;

/**
 *
 * @author muhammed
 */

public class MatrixCalc {

    static int[][] matrisTemp;

    public static int[][] defMatrix(String adMatris) {

        Matris.getMatrixInfo();

        int[][] matris = Matris.createMatrix(Matris.satir, Matris.sutun);

        Matris.printMatrix(matris, adMatris);

        return matris;
    }

    public static boolean boyutKontrol(int[][] matrisA, int[][] matrisB, String islem) {
        /* Toplama ve Çıkarma için boyut kontrol*/
        if (("cikarma".equals(islem) || "toplama".equals(islem)) && matrisA.length == matrisB.length && matrisA[0].length == matrisB[0].length) {
            return true;
        } else if ("carpma".equals(islem) && matrisA[0].length == matrisB.length) {
            return true;
        }

        System.out.println("Matrislerin boyutları " + islem + " işlemi için uygun değil, kontrol edin");
        return false;
    }

    public static void toplaMatris(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "toplama")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, 1);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    /*Çıkarma işlemi için paramatre olarak -1 gönderilir ve her bir elemanın negatifi alınıp toplanır*/
    public static void cikarMatris(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "cikarma")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, -1);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    public static void carpMatris(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "carpma")) {
            matrisTemp = MatrisHesaplama.matrisCarp(matrisA, matrisB);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    public static void calcDet(int[][] matrisA) {
        if (matrisA.length == matrisA[0].length) {
            long det = MatrisHesaplama.calculateDeterminantLaplace(matrisA, matrisA.length);
            System.out.println("Determinant : " + det);
        } else {
            System.out.println("Determinant hesaplanamadı!");
        }
    }

    public static void main(String[] args) {

        byte secim;

        do {
            System.out.println("Matris Toplama(1) , Matris Çıkarma(2) , Matris Çarpımı(3) , Determinant(4)");

            secim = Matris.input.nextByte();
            Matris.input.nextLine(); //nextInt() sonrası boşluğu temizlemek için

            switch (secim) {
                case 1 ->
                    toplaMatris(defMatrix("Matris A"), defMatrix("Matris B"));
                case 2 ->
                    cikarMatris(defMatrix("Matris A"), defMatrix("Matris B"));
                case 3 ->
                    carpMatris(defMatrix("Matris A"), defMatrix("Matris B"));
                case 4 ->
                    calcDet(defMatrix("Matris A"));
                default ->
                    secim = 0;
            }
        } while (secim != 0);
    }

}
