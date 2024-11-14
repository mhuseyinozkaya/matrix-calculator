/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.matrix.matrixcalc;

/**
 *
 * @author muhammed
 */
public class MatrixCalc {
    
    static int[][] matrisA,matrisB,matrisTemp;
    
    public static int[][] defMatris(){
        
        int[][] matris;
        
        Matris.matrisBilgiAl();
        matris = Matris.matrisOlustur(Matris.satir,Matris.sutun);
        matris = Matris.matrisDoldur(matris);
        
        return matris;
    }
    
    public static boolean boyutKontrol(int[][] matrisA,int[][] matrisB,String islem){
        if("toplama".equals(islem) && matrisA.length == matrisB.length && matrisA[0].length == matrisB[0].length){
            return true;
        }else if("carpma".equals(islem) && matrisA[0].length == matrisB.length){
            return true;
        }
        System.out.println("Matrislerin boyutları "+ islem +" işlemi için uygun değil, kontrol edin");
        return false;
    }
    
    public static void toplaMatris(){
        if(boyutKontrol(matrisA, matrisB,"toplama")){
            matrisTemp = MatrisHesaplama.matrisTopla(matrisA, matrisB);
            Matris.matrisYazdir(matrisTemp,"Sonuç");
        }
    }
    public static void carpMatris(){
        if(boyutKontrol(matrisA, matrisB,"carpma")){
            matrisTemp = MatrisHesaplama.matrisCarp(matrisA, matrisB);
            Matris.matrisYazdir(matrisTemp,"Sonuç");
        }
    }
    public static void main(String[] args) {
        
        byte secim;
        
        while (true){
            
            System.out.println("Matris Toplama(1) , Matris Çarpımı(2) , Çıkış(0)");
            secim = Matris.input.nextByte();
            
            if(secim == 0) break;
            
            matrisA = defMatris();
            matrisB = defMatris();
            
            Matris.matrisYazdir(matrisA,"Matris A");
            Matris.matrisYazdir(matrisB,"Matris B");
            
            switch (secim) {
                case 1 -> toplaMatris();
                case 2 -> carpMatris();
                default -> {
                    break;
                }
            }
        }
        
    }
}

