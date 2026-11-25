import java.util.Scanner;

public class RekapLaundry09 {

    static Scanner sc = new Scanner(System.in);

    static void inputLayanan(String[]layanan, int[] harga) {
        for (int i = 0; i < layanan.length; i++) {
            System.out.print("Nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine();
            System.out.print("Harga per kg " + layanan[i] + ": ");
            harga[i] = sc.nextInt();
            sc.nextLine();  
            }
        }

    static void inputData(int[][] data, String[] layanan) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Berat " + layanan[i] + " Hari ke-" + (j + 1) + " (kg): ");
                data[i][j] = sc.nextInt();
        }
    }
}
        static void tampilTabel(int[][] data, String[] layanan, int[] harga) {
        System.out.println("\n=== Rekap Berat Cucian (kg) ===");
        System.out.printf("%-16s %-10s", "Layanan", "Harga/kg");
        for (int j = 0; j < data[0].length; j++) {
            System.out.printf("%-10s", "Hari ke-" + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-16s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-10d", data[i][j]);
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
        System.out.println("Total pendapatan " + data[0].length + " hari : Rp" + totalPendapatan);
    }

    public static void main(String[] args) {
        System.out.print("Masukkan jumlah layanan: ");
        int jumlahLayanan = sc.nextInt();
        System.out.print("Masukkan jumlah hari: ");
        int jumlahHari = sc.nextInt();
        sc.nextLine();   // buang sisa Enter sebelum membaca nama

        String[] layanan = new String[jumlahLayanan];
        int[] harga = new int[jumlahLayanan];
        int[][] data = new int[jumlahLayanan][jumlahHari];

        inputLayanan(layanan, harga);
        inputData(data, layanan);
        tampilTabel(data, layanan, harga);

        System.out.println();
        for (int i = 0; i < jumlahLayanan; i++) {
            System.out.println("Total " + layanan[i] + " : " + totalPerLayanan(data, i) + " kg");
        }

        tampilLayananTertinggi(data, layanan);

        int idxHari = hariTersibuk(data);
        System.out.println("Hari tersibuk: Hari ke-" + (idxHari + 1)
                + " (" + totalPerHari(data, idxHari) + " kg)");

        hitungPendapatan(data, layanan, harga);

        sc.close();
    }
}