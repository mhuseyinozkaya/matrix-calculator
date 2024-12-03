package org.matrix.matrixcalculator;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MatrixCalculator {

    static HashMap<String, Matrix> matrixMap = new HashMap<>(); // Matrisleri saklamak için HashMap tanımı

    static Scanner input = new Scanner(System.in);

    public static void menu() {

        System.out.println("Matris oluştur (1)");
        System.out.println("Matrisleri listele (2)");
        System.out.println("Matris ara (3)");
        System.out.println("Matris sil (4)");

        System.out.println("Matrisleri Topla (5)");
        System.out.println("Matrisleri Çıkar (6)");
        System.out.println("Skaler Çarpım (7)");
        System.out.println("Matris Çarpımı (8)");
        System.out.println("Determinant Hesapla (9)");
        System.out.println("Transpoze al (10)");

        //System.out.println("Shell (0)");
        //System.out.println("Çıkış (0)");
    }

    public static Matrix getMatrixInfo() {

        String name;
        int rows, cols;

        input.nextLine();

        System.out.println("Matrisin adını girin: ");
        name = input.nextLine();

        System.out.println("Matrisin satır ve sütununu sırasıyla boşluk bırakarak girin: Ör. '2 3' ");
        String[] tokens = input.nextLine().split(" ");

        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);

        return new Matrix(name, rows, cols);

    }

    public static Matrix fillMatrix(Matrix temp) {

        System.out.println("Matrisi doldurun :");
        for (int i = 0; i < temp.getMatrix().length; i++) {
            for (int j = 0; j < temp.getMatrix()[0].length; j++) {
                while (true) {
                    try {
                        temp.getMatrix()[i][j] = Float.parseFloat(input.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Geçersiz giriş! Lütfen sayısal bir değer girin:");
                    }
                }
            }
        }
        System.out.println("Matris başarıyla oluşturuldu!...");
        input.nextLine();
        clrscr();
        return temp;
    }

    public static Matrix searchMatrix() {

        //ilgili matrisi Arama
        Matrix temp = null;

        input.nextLine();

        System.out.println("Aramak istediğiniz matrisin adını girin: ");
        String inputName = input.nextLine();

        temp = matrixMap.get(inputName);

        if (temp == null) {
            System.out.println("Bu isimde bir matris bulunamadı.");
        } else {
            temp.printMatrix();
        }
        input.nextLine();
        clrscr();
        return temp;
    }

    public static void listAllMatrices(HashMap<String, Matrix> matrixMap) {
        input.nextLine();
        if (matrixMap.isEmpty()) {
            System.out.println("HashMap boş.");
            return;
        }
        for (Map.Entry<String, Matrix> entry : matrixMap.entrySet()) {
            System.out.print("Matris Adı :: " + entry.getKey() + "\t || ");

            System.out.println("Determinant :: " + entry.getValue().getDeterminant());
            entry.getValue().printMatrix();
            System.out.println(); // İki matris arasında boş bir satır eklemek için
        }
        System.out.println("...");
        input.nextLine();
        clrscr();

    }

    public static Matrix selectMatrix() {

        Matrix temp = null;

        input.nextLine();

        System.out.println("Seçmek istediğiniz matrisin adını girin: ");
        String inputName = input.nextLine();

        temp = matrixMap.get(inputName);

        if (temp == null) {
            System.out.println("Seçmek istediğiniz matris bulunamadı.");
        }

        return temp;
    }

    public static Matrix selectMatrix(String name) {

        Matrix temp = null;

        temp = matrixMap.get(name);

        if (temp == null) {
            System.out.println("Seçmek istediğiniz matris bulunamadı.");
        }

        return temp;
    }

    public static void deleteMatrix() {

        Matrix temp = selectMatrix();

        if (temp == null) {
            System.out.println("Matris silinemedi!");
        } else {
            matrixMap.remove(temp.getName());
            System.out.println("Matris silindi!");
        }
        input.nextLine();
        clrscr();
    }

    public static void add() {

        input.nextLine();

        System.out.println("Matrisleri boşluk bırakarak girin: Ör. 'A B'");

        try {
            String[] tokens = input.nextLine().split(" ");

            Matrix temp = selectMatrix(tokens[0]).add(selectMatrix(tokens[1]));

            if (temp != null) {
                //temp.setName(tokens[0] + "+" + tokens[1]);
                matrixMap.put(temp.getName(), temp);

                temp.printMatrix();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }

        input.nextLine();
        clrscr();
    }

    public static void subtract() {

        input.nextLine();

        System.out.println("Matrisleri boşluk bırakarak girin: Ör. 'A B'");

        try {
            String[] tokens = input.nextLine().split(" ");

            Matrix temp = selectMatrix(tokens[0]).subtract(selectMatrix(tokens[1]));

            if (temp != null) {
                //temp.setName(tokens[0] + "-" + tokens[1]);
                matrixMap.put(temp.getName(), temp);

                temp.printMatrix();

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }
        input.nextLine();
        clrscr();
    }

    public static void scalarMultiplication() {
        input.nextLine();
        System.out.println("Boşluk bırakarak matris ile çarpmak istediğiniz skaleri ardından işlem yapmak istediğiniz matrisi girin: Ör. '3 A'");
        try {
            String[] tokens = input.nextLine().split(" ");

            double scalar = Double.parseDouble(tokens[0]);

            Matrix temp = selectMatrix(tokens[1]).scalarMultiplication(scalar);

            matrixMap.put(temp.getName(), temp);

            temp.printMatrix();
        } catch (NumberFormatException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrisin mevcut olduğundan emin olun!");
        }
        input.nextLine();
        clrscr();
    }

    public static void multiplyMatrices() {
        input.nextLine();
        System.out.println("Çarpmak istediğiniz matrislerin formatını örnekteki gibi girin:  Ör. 'A B'");

        try {
            String[] tokens = input.nextLine().split(" ");
            Matrix temp = selectMatrix(tokens[0]).multiplyMatrices(selectMatrix(tokens[1]));
            if (temp != null) {

                matrixMap.put(temp.getName(), temp);

                temp.printMatrix();

            }
        } catch (NumberFormatException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrisin mevcut olduğundan emin olun!");
        }
        input.nextLine();
        clrscr();
    }

    public static void calculateDeterminant() {
        input.nextLine();
        System.out.println("Determinantı hesaplanacak matrisi girin Ör. 'A'");
        try {
            Matrix temp = selectMatrix(input.nextLine());
            temp.setDeterminant(Matrix.calculateDeterminantLaplace(temp.getMatrix(), temp.getMatrix().length));
            System.out.println("Determinant -> " + temp.getDeterminant());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }

        input.nextLine();
        clrscr();
    }

    public static void calculateTranspose() {
        input.nextLine();
        System.out.println("Transpozunu hesaplamak istediğiniz matrisi girin: Ör. 'A'");

        try {

            Matrix temp = selectMatrix(input.nextLine()).calculateTranspose();

            matrixMap.put(temp.getName(), temp);

            temp.printMatrix();

        } catch (NumberFormatException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrisin mevcut olduğundan emin olun!");
        }
        input.nextLine();
        clrscr();
    }

    public static void main(String[] args) {

        byte choice = 0;

        do {
            menu();
            choice = input.nextByte();

            switch (choice) {
                case 1:
                    clrscr();
                    Matrix temp = fillMatrix(getMatrixInfo()); //  Matrisleri oluşturma
                    matrixMap.put(temp.getName(), temp); // Matrisleri HashMap'e ekleme
                    break;
                case 2:
                    clrscr();
                    listAllMatrices(matrixMap);
                    break;
                case 3:
                    clrscr();
                    searchMatrix();
                    break;
                case 4:
                    clrscr();
                    deleteMatrix();
                    break;
                case 5:
                    clrscr();
                    add();
                    break;
                case 6:
                    clrscr();
                    subtract();
                    break;
                case 7:
                    clrscr();
                    scalarMultiplication();
                    break;
                case 8:
                    clrscr();
                    multiplyMatrices();
                    break;
                case 9:
                    clrscr();
                    calculateDeterminant();
                    break;
                case 10:
                    clrscr();
                    calculateTranspose();
                    break;
                default:
                    throw new AssertionError();
            }

        } while (choice != 0);
    }

    public static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
