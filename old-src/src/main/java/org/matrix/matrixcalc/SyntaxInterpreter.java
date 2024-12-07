package org.matrix.matrixcalc;

import java.util.Arrays;
import java.util.regex.*;

/**
 *
 * @author muhammed
 */

/*  
 *  regex:  [(]*[1-9]?[AB]{1}[*+-]{1}[1-9]?[AB]{1}[)]*
 *  regex:  [1-9]?[AB]{1}
 */

/*
    cm lm dm
*/
public class SyntaxInterpreter {

    static String[] strArray;
    static char operand;

    static int interpret_command(String inputcmd) {

        Pattern pattern = Pattern.compile("[det]*[(]*[1-9]?[AB]{1}[*+-]{1}[1-9]?[AB]{1}[)]*");
        Matcher matcher = pattern.matcher(inputcmd);

        if (matcher.matches()) {

            if (inputcmd.contains("+")) {
                strArray = inputcmd.split("\\+");
                operand = '+';
            } else if (inputcmd.contains("-")) {
                strArray = inputcmd.split("\\-");
                operand = '-';
            } else if (inputcmd.contains("*")) {
                strArray = inputcmd.split("\\*");
                operand = '*';
            }

            System.out.println(Arrays.toString(strArray));

            if (strArray[0].equals(strArray[1])) {
                MatrixCalc.matris_one = MatrixCalc.defMatrix(strArray[0]);
                MatrixCalc.matris_two = MatrixCalc.matris_one;

            } else {
                MatrixCalc.matris_one = MatrixCalc.defMatrix(strArray[0]);
                MatrixCalc.matris_two = MatrixCalc.defMatrix(strArray[1]);
            }

            switch (operand) {
                case '+':
                    MatrixCalc.addMat(MatrixCalc.matris_one, MatrixCalc.matris_two);
                    break;
                case '-':
                    MatrixCalc.subtractMat(MatrixCalc.matris_one, MatrixCalc.matris_two);
                    break;
                case '*':
                    MatrixCalc.multiplyMatrices(MatrixCalc.matris_one, MatrixCalc.matris_two);
                    break;
            }

        } else {
            System.out.println("Eşleşme yok");

            Pattern pattern2 = Pattern.compile("[1-9]?[AB]{1}");
            Matcher matcher2 = pattern2.matcher(inputcmd);

            if (matcher2.matches()) {
                strArray[0] = inputcmd;

                System.out.println(Arrays.toString(strArray));

                MatrixCalc.matrisTemp = MatrixCalc.defMatrix(strArray[0]);

            } else {
                System.out.println("Syntax Hatası!!!");
                return 0;
            }
        }

        if (inputcmd.startsWith("det")) {
            MatrixCalc.calcDeterminant(MatrixCalc.matrisTemp);
        } else if (inputcmd.startsWith("transpose")) {
            MatrisHesaplama.calculateTranspose(MatrixCalc.matrisTemp);
        }

        return 1;
    }
}
