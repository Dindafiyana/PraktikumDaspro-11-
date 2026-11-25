import java.util.Scanner;

public class BankSampah09 {

    static Scanner sc = new Scanner(System.in);

    static double[] inputSetoran(int n) {
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Berat setoran nasabah ke-" + (i + 1) + " (kg): ");
            setoran[i] = sc.nextDouble();
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\n=== Data Setoran ===");
        for (int i = 0; i < setoran.length; i++) {
            System.out.printf("Nasabah ke-%d : %.2f kg%n", (i + 1), setoran[i]);
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (double s : setoran) {
            total += s;
        }
        return total;
    }

    static double hitungSaldo(double berat, double hargaPerKg) {
        return berat * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int idxMax = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    public static void main(String[] args) {
        System.out.print("Masukkan jumlah nasabah (N): ");
        int n = sc.nextInt();

        double[] setoran = inputSetoran(n);

        tampilSetoran(setoran);

        System.out.printf("%nTotal setoran seluruh nasabah: %.2f kg%n", totalSetoran(setoran));

        double hargaPerKg = 3000;
        System.out.println("\n=== Saldo Nasabah (Rp3.000/kg) ===");
        for (int i = 0; i < setoran.length; i++) {
            double saldo = hitungSaldo(setoran[i], hargaPerKg);
            System.out.printf("Nasabah ke-%d : Rp%.0f%n", (i + 1), saldo);
        }

        int idx = nasabahTerbanyak(setoran);
        System.out.printf("%nNasabah dengan setoran terbanyak: nasabah ke-%d (%.2f kg)%n",
                (idx + 1), setoran[idx]);

        sc.close();
    }
}