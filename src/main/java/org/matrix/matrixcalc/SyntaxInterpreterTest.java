package org.matrix.matrixcalc;

import java.util.Arrays;
import java.util.regex.*;

/**
 *
 * @author muhammed
 */
public class SyntaxInterpreterTest {

    static String[] token_one, token_two, token_thr;
    static String[] sub_token;
    static String command;

    // cmd 10A+B
    static Pattern pattern_one = Pattern.compile("(det|transp|adjoint)\\s[1-9]?[0-9]?[AB][*+-][1-9]?[0-9]?[AB]", Pattern.CASE_INSENSITIVE);
    static Pattern pattern_two = Pattern.compile("(det|transp|adjoint)\\s[AB]", Pattern.CASE_INSENSITIVE);
    static Pattern pattern_thr = Pattern.compile("[1-9]?[0-9]?[AB][*+-][1-9]?[0-9]?[AB]");

    static void interpret_command(String inputcmd) {

        Matcher matcher_one = pattern_one.matcher(inputcmd);
        Matcher matcher_two = pattern_two.matcher(inputcmd);
        Matcher matcher_thr = pattern_thr.matcher(inputcmd);

        //  ilk parametresi komut olan bölge
        if (matcher_one.matches()) {
            System.out.println("Syntax çalışıyor");
            token_one = inputcmd.split("\\s|[*+-]");
            System.out.println(Arrays.toString(token_one));
            //...ilk önce matrisleri tanımla ve işlem yap
            sub_token = makeSubToken(token_one);
            System.out.println(Arrays.toString(sub_token));
            //...sonuç matrisinin hangi cmd gireceğini yap

        } else if (matcher_two.matches()) {
            token_one = inputcmd.split("\\s");
            System.out.println(Arrays.toString(token_one));
        } else if (matcher_thr.matches()) {
            token_one = inputcmd.split("[*+-]");
            System.out.println(Arrays.toString(token_one));
        } //  ilk parametresi matris işlemi olan bölge
        else {
            System.out.println("Syntax Hatası!!!");
        }

    }

    static String[] makeSubToken(String[] token) {
        String[] subtoken = {token[1],token[2]};
        return subtoken;
    }

}
