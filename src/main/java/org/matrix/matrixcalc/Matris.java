package org.matrix.matrixcalc;

import java.util.Scanner;

/**
 *
 * @author muhammed
 */
public class Matris {

    static Scanner input = new Scanner(System.in);
    static int satir, sutun;

    public static void getMatrixInfo() {

        boolean check;

        do {
            System.out.println("Matrisin boyutunu 'satır x sütün' formatında girin Ör. '5x4' :");

            String boyut = input.nextLine();

            String[] boyutlar = boyut.split("x");

            if (boyutlar.length != 2) {

                System.out.println("Matrisin formatını doğru girdiğinizden emin olun !");
                check = true;
            } else {

                satir = Integer.parseInt(boyutlar[0]);
                sutun = Integer.parseInt(boyutlar[1]);

                if (satir < 1 || sutun < 1) {
                    System.out.print("Matrisin boyutunu doğru girdiğinizden emin olun ! \nTekrar Deneyin...\n");
                    check = true;
                } else {
                    check = false;
                }

            }
        } while (check);

    }

    public static int[][] createMatrix(int satir, int sutun) {

        int[][] matris = new int[satir][sutun];

        System.out.println("Matrisi doldurun :");
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[0].length; j++) {
                while (true) {
                    try {
                        matris[i][j] = Integer.parseInt(input.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Geçersiz giriş! Lütfen sayısal bir değer girin:");
                    }
                }
            }
        }
        //input.nextLine();   //nextInt() sonrası boşluğu temizlemek için
        return matris;
    }

    public static void printMatrix(int[][] matris, String adMatris) {
        System.out.println("\n" + adMatris + ": ");
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[0].length; j++) {
                System.out.print("[ " + matris[i][j] + " ] ");
            }
            System.out.print("\n");
        }
    }
}
