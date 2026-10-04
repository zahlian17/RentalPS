package com.mycompany.rentalps;1
import java.util.Scanner;

public class RentalPS {
    private static final int KAPASITAS = 100;
    private static final Scanner input = new Scanner(System.in);
    private static final RentalPSBase[] daftarRental = new RentalPSBase[KAPASITAS];
    private static int jumlahData = 0;
    private static int idBerikutnya = 4;

    public static void main(String[] args) {
        isiDataAwal();
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInteger("Pilih menu: ");
            switch (pilihan) {
                case 1: tambahData(); break;
                case 2: tampilkanSemuaData(); break;
                case 3: menuPencarian(); break;
                case 4:
                    System.out.println("Total objek berhasil dibuat: " + RentalPSBase.getTotalObjek());
                    System.out.println("Terima kasih telah menggunakan RentalPS.");
                    break;
                default: System.out.println("Pilihan tidak tersedia. Masukkan angka 1-4.");
            }
        } while (pilihan != 4);
        input.close();
    }

    private static void isiDataAwal() {
        daftarRental[jumlahData++] = new RentalPS3(1, "Andi", 2, 2);
        daftarRental[jumlahData++] = new RentalPS4(2, "Budi", 3, "FIFA");
        daftarRental[jumlahData++] = new RentalPS3(3, "Citra", 1, 1);
    }

    private static void tampilkanMenu() {
        System.out.println("\\n==================================");
        System.out.println("       RENTAL PLAYSTATION");
        System.out.println("==================================");
        System.out.println("1. Tambah Data Rental");
        System.out.println("2. Tampilkan Seluruh Data");
        System.out.println("3. Pencarian Data");
        System.out.println("4. Keluar");
        System.out.println("----------------------------------");
    }

    private static void tambahData() {
        if (jumlahData >= daftarRental.length) {
            System.out.println("Penyimpanan data penuh.");
            return;
        }
        System.out.println("\\n--- Tambah Data Rental ---");
        System.out.println("1. PS3 (Rp5.000/jam)");
        System.out.println("2. PS4 (Rp10.000/jam)");
        int tipe = bacaInteger("Pilih tipe PS: ");
        if (tipe != 1 && tipe != 2) {
            System.out.println("Tipe PS tidak valid.");
            return;
        }

        System.out.print("Nama penyewa: ");
        String nama = input.nextLine().trim();
        int durasi = bacaInteger("Durasi sewa (jam): ");

        try {
            RentalPSBase dataBaru;
            switch (tipe) {
                case 1:
                    int stik = bacaInteger("Jumlah stik (1-4): ");
                    dataBaru = new RentalPS3(idBerikutnya, nama, durasi, stik);
                    break;
                case 2:
                    System.out.print("Game favorit: ");
                    String game = input.nextLine().trim();
                    dataBaru = new RentalPS4(idBerikutnya, nama, durasi, game);
                    break;
                default: return;
            }
            daftarRental[jumlahData++] = dataBaru;
            idBerikutnya++;
            System.out.println("Data berhasil ditambahkan.");
            System.out.printf("Total biaya: Rp%.0f%n", dataBaru.hitungTotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    private static void tampilkanSemuaData() {
        System.out.println("\\n====================== DAFTAR RENTAL ======================");
        System.out.printf("| %-4s | %-18s | %-8s | %-5s | %-11s | %-11s |%n",
                "ID", "Penyewa/Info", "Jenis", "Jam", "Harga/Jam", "Total");
        System.out.println("-----------------------------------------------------------");
        if (jumlahData == 0) {
            System.out.println("Belum ada data rental.");
            return;
        }
        for (int i = 0; i < jumlahData; i++) {
            daftarRental[i].tampilkanDetail(); // dynamic binding / overriding
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Jumlah data: " + jumlahData);
    }

    // Overloading: pencarian berdasarkan ID.
    private static RentalPSBase cariRental(int id) {
        for (int i = 0; i < jumlahData; i++)
            if (daftarRental[i].getIdRental() == id) return daftarRental[i];
        return null;
    }

    // Overloading: pencarian berdasarkan nama.
    private static RentalPSBase cariRental(String nama) {
        for (int i = 0; i < jumlahData; i++)
            if (daftarRental[i].getNamaPenyewa().equalsIgnoreCase(nama.trim())) return daftarRental[i];
        return null;
    }

    private static void menuPencarian() {
        System.out.println("\\n--- Pencarian Data ---");
        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan nama");
        int pilihan = bacaInteger("Pilih cara pencarian: ");
        RentalPSBase hasil;
        switch (pilihan) {
            case 1: hasil = cariRental(bacaInteger("Masukkan ID: ")); break;
            case 2:
                System.out.print("Masukkan nama penyewa: ");
                hasil = cariRental(input.nextLine());
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }
        if (hasil != null) {
            System.out.printf("Ditemukan: ID %d | %s | %s | %d jam | Total Rp%.0f%n",
                    hasil.getIdRental(), hasil.getNamaPenyewa(), hasil.getJenisPS(),
                    hasil.getDurasiJam(), hasil.hitungTotal());
        } else {
            System.out.println("Data rental tidak ditemukan.");
        }
    }

    private static int bacaInteger(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat. Coba lagi.");
            }
        }
    }
}
