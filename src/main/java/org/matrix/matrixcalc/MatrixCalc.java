/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.matrix.matrixcalc;

/**
 *
 * @author muhammed
 */
public class MatrixCalc {

    static int[][] matrisA, matrisB, matrisTemp;

    public static int[][] defMatrix() {

        int[][] matris;

        Matris.matrisBilgiAl();
        matris = Matris.matrisOlustur(Matris.satir, Matris.sutun);
        matris = Matris.matrisDoldur(matris);

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

    public static void toplaMatris() {
        if (boyutKontrol(matrisA, matrisB, "toplama")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, 1);
            Matris.matrisYazdir(matrisTemp, "Sonuç");
        }
    }

    /*Çıkarma işlemi için paramatre olarak -1 gönderilir ve her bir elemanın negatifi alınıp toplanır*/
    public static void cikarMatris() {
        if (boyutKontrol(matrisA, matrisB, "cikarma")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, -1);
            Matris.matrisYazdir(matrisTemp, "Sonuç");
        }
    }

    public static void carpMatris() {
        if (boyutKontrol(matrisA, matrisB, "carpma")) {
            matrisTemp = MatrisHesaplama.matrisCarp(matrisA, matrisB);
            Matris.matrisYazdir(matrisTemp, "Sonuç");
        }
    }

    public static void calcDet() {
        if (matrisA.length == matrisA[0].length) {
            int det = MatrisHesaplama.calcDetWith_LaplaceExp(matrisA, matrisA.length);
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

            switch (secim) {
                case 1 -> {
                    matrisA = defMatrix();
                    matrisB = defMatrix();
                    Matris.matrisYazdir(matrisA, "Matris A");
                    Matris.matrisYazdir(matrisB, "Matris B");
                    toplaMatris();

                }
                case 2 -> {
                    matrisA = defMatrix();
                    matrisB = defMatrix();
                    Matris.matrisYazdir(matrisA, "Matris A");
                    Matris.matrisYazdir(matrisB, "Matris B");
                    cikarMatris();
                }
                case 3 -> {
                    matrisA = defMatrix();
                    matrisB = defMatrix();
                    Matris.matrisYazdir(matrisA, "Matris A");
                    Matris.matrisYazdir(matrisB, "Matris B");
                    carpMatris();
                }
                case 4 -> {
                    matrisA = defMatrix();
                    Matris.matrisYazdir(matrisA, "Matris A");
                    calcDet();
                    break;
                }
                default -> {
                    break;
                }
            }
        } while (secim != 0);
    }

}
