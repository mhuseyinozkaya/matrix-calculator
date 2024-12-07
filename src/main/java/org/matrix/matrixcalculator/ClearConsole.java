package org.matrix.matrixcalculator;

public class ClearConsole {

    public static void clrscr() {
        try {
            // İşletim sistemi belirleme
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Windows için cmd.exe üzerinden cls komutunu çalıştır
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // Linux/Mac için clear komutunu çalıştır
                processBuilder = new ProcessBuilder("clear");
            }

            // Process'in giriş/çıkış/hata akışlarını ana işlemle paylaş
            processBuilder.inheritIO();

            // Komutu çalıştır
            Process process = processBuilder.start();

            // İşlemin tamamlanmasını bekle
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
