package org.matrix.matrixcalc;
import java.util.Scanner;
/**
 *
 * @author muhammed
 */
public class Matris {
    static Scanner input = new Scanner(System.in);
    static int satir,sutun;
    
    public static void matrisBilgiAl(){
        System.out.println("Matrisin satırını girin : ");
        satir = input.nextInt();
        System.out.println("Matrisin sutununu girin : ");
        sutun = input.nextInt();
    }
    
    public static int[][] matrisOlustur(int satir,int sutun){
        int[][] matris = new int[satir][sutun];
        return matris;
    }
    
    public static int[][] matrisDoldur(int[][] matris){
        System.out.println("Matrisi doldurun :");
        for(int i=0;i<matris.length;i++){
            for(int j=0;j<matris[0].length;j++){
                matris[i][j] = input.nextInt();
            }
        }
        
        return matris;
    }
    
    public static void matrisYazdir(int[][] matris,String adMatris){
        System.out.println("\n"+ adMatris +": ");
        for(int i=0;i<matris.length;i++){
            for(int j=0;j<matris[0].length;j++){
                System.out.print("[ "+ matris[i][j] + " ] ");
            }
            System.out.print("\n");
        }
    }
}
