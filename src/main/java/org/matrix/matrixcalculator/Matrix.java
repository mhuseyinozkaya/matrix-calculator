package org.matrix.matrixcalculator;

public class Matrix {

    private final String dimension;
    private String name;
    private Double determinant = null;
    private double[][] matrix;
    private int id;

    static int counter = 0; //  ID tutucu

    //Kurucu Metod
    Matrix(String name, int rows, int cols, int id) {
        this.name = name;
        this.dimension = String.valueOf(rows) + "x" + String.valueOf(cols);
        this.matrix = new double[rows][cols];
        this.id = id;
    }

    public static int idCounter() {
        return ++counter;
    }

    // Getter ve Setter yöntemleri
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Double getDeterminant() {
        return this.determinant;
    }

    public void setDeterminant(double determinant) {
        this.determinant = determinant;
    }

    public int getID() {
        return id;
    }

    public void setID(int num) {
        this.id = num;
    }

    public String getDimension() {
        return this.dimension;
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print("[ " + matrix[i][j] + " ] ");
            }
            System.out.println();
        }
    }

    /*  Operations... */
    public Matrix add(Matrix other) {
        if (matrix.length == other.matrix.length && matrix[0].length == other.matrix[0].length) {

            Matrix temp = new Matrix("(" + this.name + "+" + other.name + ")", matrix.length, matrix[0].length, idCounter());

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    temp.matrix[i][j] = matrix[i][j] + other.matrix[i][j];
                }
            }
            return temp;
        } else {
            System.out.println("Matrisler toplanamadı!");
            return null;
        }
    }

    public Matrix subtract(Matrix other) {
        if (matrix.length == other.matrix.length && matrix[0].length == other.matrix[0].length) {

            Matrix temp = new Matrix("(" + this.name + "-" + other.name + ")", matrix.length, matrix[0].length, idCounter());

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    temp.matrix[i][j] = matrix[i][j] - other.matrix[i][j];
                }
            }
            return temp;
        } else {
            System.out.println("Matrisler çıkarılamadı!");
            return null;
        }
    }

    public Matrix scalarMultiplication(double scalar) {

        Matrix temp = new Matrix("(" + scalar + this.name + ")", matrix.length, matrix[0].length, idCounter());

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp.matrix[i][j] = matrix[i][j] * scalar;
            }
        }
        return temp;
    }

    public Matrix multiplyMatrices(Matrix other) {
        if (matrix[0].length == other.matrix.length) {
            Matrix temp = new Matrix("(" + this.name + "*" + other.name + ")", this.matrix.length, other.matrix[0].length, idCounter());
            double cellSum;

            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < other.matrix[0].length; j++) {
                    cellSum = 0;
                    for (int k = 0; k < other.matrix.length; k++) {
                        cellSum += this.matrix[i][k] * other.matrix[k][j];
                    }

                    temp.matrix[i][j] = cellSum;
                }
            }
            return temp;
        } else {
            System.out.println("Matrisler çarpılamadı, boyutlarını kontrol edin!");
            return null;
        }
    }

    //  Verimsiz determinant hesaplama yöntemi  //
    //  determinantı hesaplamak için ilk satıra göre laplace genişlemesi yapıyor //
    public static double calculateDeterminantLaplace(double[][] matrix, int n) {

        double cofactor, determinant = 0;

        double[][] temp = new double[n - 1][n - 1];
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        } else {

            //***
            for (int k = 0; k < n; k++) {
                for (int i = 1, p = 0; i < n; i++) {
                    for (int j = 0, r = 0; j < n; j++) {

                        if (k == j) {
                            continue;
                        }

                        temp[p][r] = matrix[i][j];
                        r++;
                    }
                    p++;
                }

                cofactor = (int) (Math.pow(-1, 0 + k) * calculateDeterminantLaplace(temp, n - 1));
                determinant += matrix[0][k] * cofactor;

            }
        }
        return determinant;
    }

    public Matrix calculateTranspose() {

        Matrix temp = new Matrix("tp(" + this.name + ")", this.matrix[0].length, this.matrix.length, idCounter());

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                temp.matrix[j][i] = this.matrix[i][j];
            }
        }
        return temp;
    }
}
