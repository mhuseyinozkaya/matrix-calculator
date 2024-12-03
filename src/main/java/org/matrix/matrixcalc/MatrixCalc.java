package org.matrix.matrixcalc;
import java.util.ArrayList;

public class MatrixCalc {

    static int[][] matris_one = null,
            matris_two = null,
            matrisTemp = null;
    static String nameMatrix;
    static ArrayList<String> nameMatrices = new ArrayList<String>();
    
    public static int[][] defMatrix(String adMatris) {

        Matris.getMatrixInfo();

        int[][] matris = Matris.createMatrix(Matris.satir, Matris.sutun, adMatris);

        Matris.printMatrix(matris, adMatris);

        return matris;
    }

    public static boolean boyutKontrol(int[][] matrisA, int[][] matrisB, String islem) {
        //  Toplama ve Çıkarma için boyut kontrol
        if (("cikarma".equals(islem) || "toplama".equals(islem)) && matrisA.length == matrisB.length && matrisA[0].length == matrisB[0].length) {
            return true;
        } //  Matris çarpımı için kontrol
        else if ("carpma".equals(islem) && matrisA[0].length == matrisB.length) {
            return true;
        }

        System.out.println("Matrislerin boyutları " + islem + " işlemi için uygun değil, kontrol edin");
        return false;
    }

    public static void addMat(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "toplama")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, 1);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    /*Çıkarma işlemi için paramatre olarak -1 gönderilir ve her bir elemanın negatifi alınıp toplanır*/
    public static void subtractMat(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "cikarma")) {
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB, -1);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    public static void multiplyMatrices(int[][] matrisA, int[][] matrisB) {
        if (boyutKontrol(matrisA, matrisB, "carpma")) {
            matrisTemp = MatrisHesaplama.matrisCarp(matrisA, matrisB);
            Matris.printMatrix(matrisTemp, "Sonuç");
        }
    }

    public static void calcDeterminant(int[][] matrisA) {
        if (matrisA.length == matrisA[0].length) {
            long det = MatrisHesaplama.calculateDeterminantLaplace(matrisA, matrisA.length);
            System.out.println("Determinant : " + det);
        } else {
            System.out.println("Determinant hesaplanamadı!");
        }
    }

    public static int[][] scalarMultiplication(int[][] matrix, int scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= scalar;
            }
        }
        Matris.printMatrix(matrix,"Sonuç");
        return matrix;
    }

    public static void main(String[] args) {

        byte choice;
        
        
        do {            
            
            System.out.println("Matris Oluştur '1' ");
            System.out.println("Matris(leri) listele '2' ");
            System.out.println("Matris Sil '3' ");
            System.out.println("Çıkış '0' ");
            
            choice = Byte.parseByte(Matris.input.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.println("Oluşturmak istediğiniz matrisin adını girin : ");
                    nameMatrix = Matris.input.nextLine();
                    defMatrix(nameMatrix);
                    nameMatrices.add(nameMatrix);
                    
                    break;
                default:
                    throw new AssertionError();
            }
        } while (choice!=0);
        
        SyntaxInterpreterTest.interpret_command(Matris.input.nextLine());
        
    }

}
