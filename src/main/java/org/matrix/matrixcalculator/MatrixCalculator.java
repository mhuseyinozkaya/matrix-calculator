package org.matrix.matrixcalculator;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MatrixCalculator {

    static HashMap<String, Matrix> matrixMap = new HashMap<>(); // Matrisleri saklamak için HashMap tanımı

    static Scanner input = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("--- Matris Hesaplayıcı ---");
        System.out.println("1. Matris Ekle");
        System.out.println("2. Matrisleri Listele");
        System.out.println("3. Matris Ara");
        System.out.println("4. Matris Sil");
        System.out.println("5. Matris Toplama");
        System.out.println("6. Matris Çıkarma");
        System.out.println("7. Skaler Çarpma");
        System.out.println("8. Matris Çarpımı");
        System.out.println("9. Determinant Hesaplama");
        System.out.println("10. Transpoz Hesaplama");
        System.out.println("0. Çıkış");
        System.out.print(">> ");
        //System.out.println("Shell");
    }

    public static Matrix getMatrixInfo() {

        String name;
        int rows, cols;

        System.out.print("Matrisin adını girin: \n>> ");
        name = input.nextLine();

        if (name.matches("[a-zA-Z]")) {
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

                return new Matrix(name, rows, cols);

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

    public static Matrix searchMatrix() {

        //ilgili matrisi Arama
        Matrix temp = null;

        System.out.print("Aramak istediğiniz matrisin adını girin: \n>> ");
        String inputName = input.nextLine();

        temp = matrixMap.get(inputName);

        if (temp == null) {
            System.out.print("Bu isimde bir matris bulunamadı.");
        } else {
            temp.printMatrix();
        }
        input.nextLine();
        return temp;
    }

    public static void listAllMatrices() {
        if (matrixMap.isEmpty()) {
            System.out.println("HashMap boş.");
            return;
        }
        for (Map.Entry<String, Matrix> entry : matrixMap.entrySet()) {
            System.out.print("\n>> Matris Adı : " + entry.getKey() + " | ");

            System.out.println("Determinant : " + entry.getValue().getDeterminant());
            System.out.println("////////////////////////////////////////\n");
            entry.getValue().printMatrix();
            System.out.println("\n////////////////////////////////////////"); // İki matris arasında boş bir satır eklemek için
        }
        input.nextLine();
    }

    public static Matrix selectMatrix() {

        Matrix temp = null;

        System.out.print("Seçmek istediğiniz matrisin adını girin: \n>> ");
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
    }

    public static void add() {

        System.out.print("Matrisleri boşluk bırakarak girin: Ör. 'A B' \n>> ");

        try {
            String[] tokens = input.nextLine().split(" ");

            Matrix temp = selectMatrix(tokens[0]).add(selectMatrix(tokens[1]));

            if (temp != null) {
                matrixMap.put(temp.getName(), temp);

                temp.printMatrix();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }

        input.nextLine();
    }

    public static void subtract() {

        System.out.print("Matrisleri boşluk bırakarak girin: Ör. 'A B' \n>> ");

        try {
            String[] tokens = input.nextLine().split(" ");

            Matrix temp = selectMatrix(tokens[0]).subtract(selectMatrix(tokens[1]));

            if (temp != null) {
                matrixMap.put(temp.getName(), temp);

                temp.printMatrix();

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }
        input.nextLine();
    }

    public static void scalarMultiplication() {
        System.out.print("Boşluk bırakarak matris ile çarpmak istediğiniz skaleri ardından işlem yapmak istediğiniz matrisi girin: Ör. '3 A' \n>> ");
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

    public static void multiplyMatrices() {
        System.out.print("Çarpmak istediğiniz matrislerin formatını örnekteki gibi girin:  Ör. 'A B' \n>> ");

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
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }
        input.nextLine();
    }

    public static void calculateDeterminant() {
        System.out.print("Determinantı hesaplanacak matrisi girin Ör. 'A' \n>> ");
        try {

            Matrix temp = selectMatrix(input.nextLine());

            if (temp.getMatrix().length != temp.getMatrix()[0].length) {
                System.out.println("Girdiğiniz matris kare matris değil, matris hesaplanamaz!");
                input.nextLine();
                return;
            }
            temp.setDeterminant(Matrix.calculateDeterminantLaplace(temp.getMatrix(), temp.getMatrix().length));
            System.out.println("Determinant : " + temp.getDeterminant());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Doğru formatta girdiğinizden emin olun!");
        } catch (NullPointerException e) {
            System.out.println("Matrislerin mevcut olduğundan emin olun!");
        }
        input.nextLine();
    }

    public static void calculateTranspose() {
        System.out.print("Transpozunu hesaplamak istediğiniz matrisi girin: Ör. 'A' \n>> ");
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
                    searchMatrix();
                    break;
                case "4":
                    clrscr();
                    deleteMatrix();
                    break;
                case "5":
                    clrscr();
                    add();
                    break;
                case "6":
                    clrscr();
                    subtract();
                    break;
                case "7":
                    clrscr();
                    scalarMultiplication();
                    break;
                case "8":
                    clrscr();
                    multiplyMatrices();
                    break;
                case "9":
                    clrscr();
                    calculateDeterminant();
                    break;
                case "10":
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

    public static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
