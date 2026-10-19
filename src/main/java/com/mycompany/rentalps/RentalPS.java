package com.mycompany.rentalps;

import java.util.Scanner;

public class RentalPS {
    private static final int KAPASITAS = 100;
    private static final Scanner input = new Scanner(System.in);
    // Array superclass menyimpan objek RentalPS3, RentalPS4, dan RentalPS5.
    private static final RentalPSBase[] daftarRental = new RentalPSBase[KAPASITAS];
    private static int jumlahData = 0;
    // [MODUL 7] Empat data awal memakai ID 1-4, sehingga ID baru dimulai dari 5.
    private static int idBerikutnya = 5;

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
                case 4: menuSimulasi(); break;
                // [BARU MODUL 7] Menu untuk menguji abstract method dan interface.
                case 5: {
                    int id = bacaInteger("Masukkan ID rental: ");
                    RentalPSBase data = cariRental(id);
                    if (data != null) prosesKemampuan(data);
                    else System.out.println("Data rental tidak ditemukan.");
                    break;
                }
                case 6:
                    System.out.println("Total objek berhasil dibuat: " + RentalPSBase.getTotalObjek());
                    System.out.println("Terima kasih telah menggunakan RentalPS.");
                    break;
                default: System.out.println("Pilihan tidak tersedia. Masukkan angka 1-6.");
            }
        } while (pilihan != 6);
        input.close();
    }

    private static void isiDataAwal() {
        daftarRental[jumlahData++] = new RentalPS3(1, "Andi", 2, 2);
        daftarRental[jumlahData++] = new RentalPS4(2, "Budi", 3, "FIFA");
        daftarRental[jumlahData++] = new RentalPS3(3, "Citra", 1, 1);
        // [BARU MODUL 7] Data PS5 agar ketiga subclass terwakili.
        daftarRental[jumlahData++] = new RentalPS5(4, "Doni", 2, "Grafis 4K");
    }

    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("       RENTAL PLAYSTATION");
        System.out.println("==================================");
        System.out.println("1. Tambah Data Rental");
        System.out.println("2. Tampilkan Seluruh Data");
        System.out.println("3. Pencarian Data");
        System.out.println("4. Simulasi Rental");
        // [BARU MODUL 7] Menu interface dan abstract method.
        System.out.println("5. Proses Fitur dan Interface");
        System.out.println("6. Keluar");
        System.out.println("----------------------------------");
    }

    private static void tambahData() {
        if (jumlahData >= daftarRental.length) {
            System.out.println("Penyimpanan data penuh.");
            return;
        }
        System.out.println("\n--- Tambah Data Rental ---");
        System.out.println("1. PS3 (Rp5.000/jam)");
        System.out.println("2. PS4 (Rp10.000/jam)");
        // [BARU MODUL 7] Opsi PS5 pada menu tambah data.
        System.out.println("3. PS5 (Rp15.000/jam)");
        int tipe = bacaInteger("Pilih tipe PS: ");
        if (tipe < 1 || tipe > 3) {
            System.out.println("Tipe PS tidak valid.");
            return;
        }

        System.out.print("Nama penyewa: ");
        String nama = input.nextLine().trim();
        int durasi = bacaInteger("Durasi sewa (jam): ");

        try {
            RentalPSBase dataBaru;
            switch (tipe) {
                case 1: {
                    int stik = bacaInteger("Jumlah stik (1-4): ");
                    dataBaru = new RentalPS3(idBerikutnya, nama, durasi, stik);
                    break;
                }
                case 2: {
                    System.out.print("Game favorit: ");
                    String game = input.nextLine().trim();
                    dataBaru = new RentalPS4(idBerikutnya, nama, durasi, game);
                    break;
                }
                // [BARU MODUL 7] Membuat objek PS5 dari menu.
                case 3: {
                    System.out.print("Fitur unggulan PS5: ");
                    String fitur = input.nextLine().trim();
                    dataBaru = new RentalPS5(idBerikutnya, nama, durasi, fitur);
                    break;
                }
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
        System.out.println("\n====================== DAFTAR RENTAL ======================");
        System.out.printf("| %-4s | %-18s | %-8s | %-5s | %-11s | %-11s |%n",
                "ID", "Penyewa/Info", "Jenis", "Jam", "Harga/Jam", "Total");
        System.out.println("-----------------------------------------------------------");
        if (jumlahData == 0) {
            System.out.println("Belum ada data rental.");
            return;
        }
        for (int i = 0; i < jumlahData; i++) {
            // Dynamic binding: Java menjalankan override sesuai jenis objek sebenarnya.
            daftarRental[i].tampilkanDetail();
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Jumlah data: " + jumlahData);
    }

    // Method overloading pertama: mencari berdasarkan ID.
    private static RentalPSBase cariRental(int id) {
        for (int i = 0; i < jumlahData; i++)
            if (daftarRental[i].getIdRental() == id) return daftarRental[i];
        return null;
    }

    // Method overloading kedua: mencari berdasarkan nama.
    private static RentalPSBase cariRental(String nama) {
        for (int i = 0; i < jumlahData; i++)
            if (daftarRental[i].getNamaPenyewa().equalsIgnoreCase(nama.trim())) return daftarRental[i];
        return null;
    }

    private static void menuPencarian() {
        System.out.println("\n--- Pencarian Data ---");
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

    private static void menuSimulasi() {
        System.out.println("\n--- Menu Simulasi Rental ---");
        int id = bacaInteger("Masukkan ID rental yang ingin disimulasikan: ");
        RentalPSBase data = cariRental(id);
        if (data == null) {
            System.out.println("Data rental tidak ditemukan.");
            return;
        }
        simulasiRental(data);
    }

    // Method menerima parameter superclass sehingga bisa memproses semua subclass.
    private static void simulasiRental(RentalPSBase data) {
        System.out.println("\n--- Simulasi Rental ---");
        System.out.println("ID Rental   : " + data.getIdRental());
        System.out.println("Nama        : " + data.getNamaPenyewa());
        System.out.println("Jenis PS    : " + data.getJenisPS());
        System.out.println("Durasi      : " + data.getDurasiJam() + " jam");
        data.tampilkanDetail();
        System.out.printf("Total biaya : Rp%.0f%n", data.hitungTotal());
    }

    // [BARU MODUL 7] Method parameter superclass, memakai abstract method dan instanceof.
    private static void prosesKemampuan(RentalPSBase data) {
        System.out.println("\n--- Proses Abstract Method dan Interface ---");
        data.tampilkanFiturKhusus();

        // [BARU MODUL 7] Cek dukungan interface sebelum pemanggilan method.
        if (data instanceof DapatDipinjam) {
            DapatDipinjam pinjam = (DapatDipinjam) data;
            pinjam.prosesPinjamFisik();
        } else {
            System.out.println("Objek ini tidak mengimplementasikan DapatDipinjam.");
        }

        // [BARU MODUL 7] Cek dukungan interface rating.
        if (data instanceof DapatDinilai) {
            DapatDinilai nilai = (DapatDinilai) data;
            nilai.beriRating();
        } else {
            System.out.println("Objek ini tidak mengimplementasikan DapatDinilai.");
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
