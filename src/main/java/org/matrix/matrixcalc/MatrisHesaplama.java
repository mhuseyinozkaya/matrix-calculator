package org.matrix.matrixcalc;

/**
 *
 * @author muhammed
 */
public class MatrisHesaplama {
        
    static int[][] matrisTopla(int[][] matrisA, int[][] matrisB){
        
        int[][] matrisSonuc = new int[matrisA.length][matrisA[0].length];
            
            for(int i=0;i<matrisA.length;i++){
                for(int j=0;j<matrisA[0].length;j++){
                    matrisSonuc[i][j] = matrisA[i][j] + matrisB[i][j]; 
                }
            }
        
        return matrisSonuc;
    }
    
    static int[][] matrisCarp(int[][] matrisA, int[][] matrisB){
        int hucreToplam;
        int[][] matrisSonuc = new int[matrisA.length][matrisB[0].length];
            
            for(int i=0;i<matrisA.length;i++){
                for(int j=0;j<matrisB[0].length;j++){
                    hucreToplam = 0;
                    
                    for(int k=0;k<matrisB.length;k++){
                        hucreToplam += matrisA[i][k] * matrisB[k][j];
                    }
                    
                    matrisSonuc[i][j] = hucreToplam;
                }
            }
        return matrisSonuc;
    }
}