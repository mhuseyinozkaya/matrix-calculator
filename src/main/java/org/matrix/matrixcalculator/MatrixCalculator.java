package org.matrix.matrixcalculator;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MatrixCalculator extends ClearConsole {

    static HashMap<String, Matrix> matrixMap = new HashMap<>(); // Matrisleri saklamak için HashMap tanımı

    static Scanner input = new Scanner(System.in);  // Girdi alma

    public static void showMenu() {
        System.out.println("--- Matris Hesaplayıcı ---");
        System.out.println("1. Matris Ekle");
        System.out.println("2. Matrisleri Listele");
        System.out.println("3. Matris Sil\n--------------------");
        System.out.println("4. Matris Toplama");
        System.out.println("5. Matris Çıkarma");
        System.out.println("6. Skaler Çarpma");
        System.out.println("7. Matris Çarpımı");
        System.out.println("8. Determinant Hesaplama");
        System.out.println("9. Transpoz Hesaplama");
        System.out.println("0. Çıkış\n--------------------");
        System.out.print(">> ");
        //System.out.println("Shell");
    }

    public static Matrix getMatrixInfo() {

        String name;
        int rows, cols;

        System.out.print("Matrisin adını girin: \n>> ");
        name = input.nextLine();

        if (name.matches("[a-zA-Z]+")) {
            for (String key : matrixMap.keySet()) {
                if (key.equals(name)) {
                    System.out.print(name + " adında zaten bir matris var, matris oluşturulamıyor!");
                    input.nextLine();
                    return null;
                }
            }
        } else {
            System.out.print("Matris oluşturulamadı, matrisin adını harf girdiğinizden emin olun!");
            input.nextLine();
            return null;

        }

        System.out.println("Matrisin satır ve sütununu sırasıyla boşluk bırakarak girin: Ör. '2 3'");

        while (true) {
            try {
                System.out.print(">> ");
                String[] tokens = input.nextLine().split(" ");

                if (tokens.length != 2) {
                    throw new ArrayIndexOutOfBoundsException();
                }

                rows = Integer.parseInt(tokens[0]);
                cols = Integer.parseInt(tokens[1]);

                if (rows < 1 || cols < 1) {
                    throw new NegativeArraySizeException();
                }

                return new Matrix(name, rows, cols, Matrix.idCounter());

            } catch (NumberFormatException e) {
                System.out.println("Lütfen sayı girdiğinizden emin olun!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Lütfen iki boyutlu matris girdiğinizden emin olun!");
            } catch (NegativeArraySizeException e) {
                System.out.println("Matrisin boyutunu pozitif girdiğinizden emin olun!");
            }
        }
    }

    public static Matrix fillMatrix(Matrix temp) {

        System.out.print("Matrisi doldurun : ");
        for (int i = 0; i < temp.getMatrix().length; i++) {
            for (int j = 0; j < temp.getMatrix()[0].length; j++) {
                while (true) {
                    try {
                        System.out.print("\n>> ");
                        temp.getMatrix()[i][j] = Float.parseFloat(input.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Geçersiz giriş! Lütfen sayısal bir değer girin: ");
                    }
                }
            }
        }
        System.out.println("Matris başarıyla oluşturuldu!...");
        input.nextLine();
        return temp;
    }

    /*public static Matrix searchMatrix() {

    }*/
    public static void listAllMatrices() {
        if (matrixMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Matrix> entry : matrixMap.entrySet()) {
            System.out.println("=========================");
            System.out.println(
                    "\n>> Matris Adı : " + entry.getKey()
                    + "\n>> Boyut : " + entry.getValue().getDimension()
                    + "\n>> Matris ID : " + entry.getValue().getID()
                    + "\n>> Determinant : " + entry.getValue().getDeterminant());

            System.out.println("\n=========================\n");
            entry.getValue().printMatrix();
            System.out.println("\n=========================\n"); // İki matris arasında boş bir satır eklemek için
        }
        input.nextLine();
    }

    public static Matrix selectMatrix(String input) {

        Matrix temp = null;

        //Eğer matris adı ile girerse
        if (input.matches("[a-zA-Z]+")) {
            temp = matrixMap.get(input);
        } //Eğer matris ID'si ile girerse
        else if (input.matches("[0-9]+")) {
            for (Map.Entry<String, Matrix> entry : matrixMap.entrySet()) {
                //Eğer matrisin ID'si ile input eşitse
                if (input.equals(String.valueOf(entry.getValue().getID()))) {
                    temp = matrixMap.get(entry.getValue().getName());
                }
            }

        } else {
            System.out.println("Lütfen doğru formatta girdiğinizden emin olun!");
        }

        if (temp == null) {
            System.out.println("Seçmek istediğiniz matris bulunumadı!");
        }

        return temp;
    }

    public static void deleteMatrix() {
        System.out.print("Silinecek matrisi girin : ");
        Matrix temp = selectMatrix(input.nextLine());

        if (temp == null) {
            System.out.println("Matris silinemedi!");
        } else {
            matrixMap.remove(temp.getName());
            System.out.println("Matris silindi!");
        }
        input.nextLine();
    }

    public static void add() {

        System.out.print("Matrisleri boşluk bırakarak girin: Ör. 'A B' \n>> ");

        Matrix[] matrices = parseMatrixInput(input.nextLine(), 2);
        if (matrices == null) {
            return;
        }

        Matrix result = matrices[0].add(matrices[1]);
        if (result != null) {
            matrixMap.put(result.getName(), result);
            result.printMatrix();

        }
        input.nextLine();
    }

    public static void subtract() {

        System.out.print("Matrisleri boşluk bırakarak girin: Ör. 'A B' \n>> ");

        Matrix[] matrices = parseMatrixInput(input.nextLine(), 2);
        if (matrices == null) {
            return;
        }

        Matrix result = matrices[0].subtract(matrices[1]);
        if (result != null) {
            matrixMap.put(result.getName(), result);
            result.printMatrix();
        }
        input.nextLine();
    }

    public static void multiplyMatrices() {
        System.out.print("Çarpmak istediğiniz matrislerin formatını örnekteki gibi girin:  Ör. 'A B' \n>> ");

        Matrix[] matrices = parseMatrixInput(input.nextLine(), 2);
        if (matrices == null) {
            return;
        }

        Matrix result = matrices[0].multiplyMatrices(matrices[1]);
        if (result != null) {
            matrixMap.put(result.getName(), result);
            result.printMatrix();
        }
        input.nextLine();
    }

    public static void scalarMultiplication() {
        System.out.print("Boşluk bırakarak skaleri ardından işlem yapmak istediğiniz matrisi girin: Ör. '3 A' \n>> ");
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
    }

    public static void calculateDeterminant() {
        System.out.println("Determinantı hesaplanacak matrisi girin Ör. 'A'");
        try {
            String argument = input.nextLine();
            Matrix temp = selectMatrix(argument);   //  Matrisi alma

            if (temp.getMatrix().length != temp.getMatrix()[0].length) {
                System.out.println("Girdiğiniz matris kare matris değil, matris hesaplanamaz!");
                input.nextLine();
                return;
            }

            temp.setDeterminant(Matrix.calculateDeterminantLaplace(temp.getMatrix(), temp.getMatrix().length)); //  Determinant hesaplama
            System.out.println("Determinant : " + temp.getDeterminant());

        } catch (Exception e) {
            System.out.println("Matrisin mevcut olduğundan emin olun!");
        }
        input.nextLine();
    }

    public static void calculateTranspose() {
        System.out.println("Transpozunu hesaplamak istediğiniz matrisi girin: Ör. 'A'");
        try {
            String argument = input.nextLine();

            Matrix temp = selectMatrix(argument);   //  Matrisi alma

            Matrix result = temp.calculateTranspose();

            matrixMap.put(result.getName(), result);

            result.printMatrix();

        } catch (Exception e) {
            System.out.println("Matrisin mevcut olduğundan emin olun!");
        }
        input.nextLine();
    }

    public static Matrix[] parseMatrixInput(String argument, int expectedCount) {
        try {
            String[] tokens = argument.split(" ");
            if (tokens.length != expectedCount) {
                System.out.println("Girdi formatı yanlış! Beklenen matris sayısı: " + expectedCount);
                return null;
            }

            Matrix[] matrices = new Matrix[expectedCount];

            for (int i = 0; i < expectedCount; i++) {
                matrices[i] = selectMatrix(tokens[i]); //Matrisi getirme
                if (matrices[i] == null) {
                    System.out.println("Matris bulunamadı: " + tokens[i]);
                    return null;
                }
            }
            return matrices;
        } catch (Exception e) {
            System.out.println("Bir hata oluştu! Lütfen giriş formatını kontrol edin.");
            input.nextLine();
            return null;
        }
    }

    public static void main(String[] args) {

        String choice;

        do {
            clrscr();
            showMenu();
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    clrscr();
                    Matrix temp = getMatrixInfo(); // Matrisin bilgisini alma
                    if (temp != null) {
                        temp = fillMatrix(temp); // Matrisi doldurma
                        matrixMap.put(temp.getName(), temp); // Matrisleri HashMap'e ekleme
                    }
                    break;
                case "2":
                    clrscr();
                    listAllMatrices();
                    break;
                case "3":
                    clrscr();
                    deleteMatrix();
                    break;
                case "4":
                    clrscr();
                    add();
                    break;
                case "5":
                    clrscr();
                    subtract();
                    break;
                case "6":
                    clrscr();
                    scalarMultiplication();
                    break;
                case "7":
                    clrscr();
                    multiplyMatrices();
                    break;
                case "8":
                    clrscr();
                    calculateDeterminant();
                    break;
                case "9":
                    clrscr();
                    calculateTranspose();
                    break;
                case "0":
                    System.out.println("Programdan çıkılıyor...");
                    break;
                case "shell":
                    //...
                    break;
                default:
                    System.out.println("Lütfen geçerli işlem girin!");
                    break;
            }
            if (choice.equals("0")) {
                break;
            }
        } while (true);
    }

    /*public static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/
}
