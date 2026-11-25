import java.util.Scanner;

public class RekapLaundry09 {

    static Scanner sc = new Scanner(System.in);

    static void inputData(int[][] data, String[] layanan, String[] hari) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Berat " + layanan[i] + " hari " + hari[j] + " (kg): ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, String[] hari) {
        System.out.println("\n=== Rekap Berat Cucian (kg) ===");
        System.out.printf("%-14s %-10s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%-8s", h);
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-14s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-8d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static void tampilLayananTertinggi(int[][] data, String[] layanan) {
        int idxMax = 0;
        for (int i = 1; i < data.length; i++) {
            if (totalPerLayanan(data, i) > totalPerLayanan(data, idxMax)) {
                idxMax = i;
            }
        }
        System.out.println("\nLayanan dengan total cucian tertinggi: " + layanan[idxMax]
                + " (" + totalPerLayanan(data, idxMax) + " kg)");
    }

    static int totalPerHari(int[][] data, int idxHari) {
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i][idxHari];
        }
        return total;
    }

    static int hariTersibuk(int[][] data) {
        int idxMax = 0;
        for (int j = 1; j < data[0].length; j++) {
            if (totalPerHari(data, j) > totalPerHari(data, idxMax)) {
                idxMax = j;
            }
        }
        return idxMax;
    }

    static void hitungPendapatan(int[][] data, String[] layanan, int[] harga) {
        System.out.println("\n=== Pendapatan per Layanan ===");
        int totalPendapatan = 0;
        for (int i = 0; i < data.length; i++) {
            int pendapatan = totalPerLayanan(data, i) * harga[i];
            System.out.println(layanan[i] + " : Rp" + pendapatan);
            totalPendapatan += pendapatan;
        }
        System.out.println("Total pendapatan 6 hari : Rp" + totalPendapatan);
    }

    public static void main(String[] args) {
        String[] layanan = {"Cuci Kering", "Cuci Setrika", "Setrika Saja", "Express"};
        int[] harga = {6000, 8000, 5000, 12000};
        String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        int[][] data = new int[layanan.length][hari.length];

        inputData(data, layanan, hari);
        tampilTabel(data, layanan, harga, hari);

        System.out.println();
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("Total " + layanan[i] + " : " + totalPerLayanan(data, i) + " kg");
        }

        tampilLayananTertinggi(data, layanan);

        int idxHari = hariTersibuk(data);
        System.out.println("Hari tersibuk: " + hari[idxHari]
                + " (" + totalPerHari(data, idxHari) + " kg)");

        hitungPendapatan(data, layanan, harga);

        sc.close();
    }
}