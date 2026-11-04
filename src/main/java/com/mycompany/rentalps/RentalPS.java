package com.mycompany.rentalps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RentalPS {

    private static final Scanner input = new Scanner(System.in);
    private static final SistemRentalPS sistem = new SistemRentalPS();
    private static int idBerikutnya = 5;

    public static void main(String[] args) {
        isiDataAwal();
        isiDataAnggotaAwal();

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInteger("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    tampilkanSemuaData();
                    break;
                case 3:
                    menuPencarian();
                    break;
                case 4:
                    menuSimulasi();
                    break;
                case 5: {
                    int id = bacaInteger("Masukkan ID rental: ");
                    RentalPSBase data = cariRental(id);

                    if (data != null) {
                        prosesKemampuan(data);
                    } else {
                        System.out.println("Data rental tidak ditemukan.");
                    }
                    break;
                }
                case 6:
                    System.out.println("Total objek berhasil dibuat: "
                            + RentalPSBase.getTotalObjek());
                    System.out.println("Terima kasih telah menggunakan RentalPS.");
                    break;
                case 7:
                    menuAnggota();
                    break;
                case 8:
                    sistem.tampilkanFasilitas();
                    break;
                case 9:
                    menuTransaksi();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Masukkan angka 1-9.");
            }
        } while (pilihan != 6);

        input.close();
    }

    private static void isiDataAwal() {
        sistem.tambahRental(new RentalPS3(1, "Andi", 2, 2));
        sistem.tambahRental(new RentalPS4(2, "Budi", 3, "FIFA"));
        sistem.tambahRental(new RentalPS3(3, "Citra", 1, 1));
        sistem.tambahRental(new RentalPS5(4, "Doni", 2, "Grafis 4K"));
    }

    private static void isiDataAnggotaAwal() {
        sistem.tambahAnggota(new PelangganRental("A001", "Andi"));
        sistem.tambahAnggota(new PelangganRental("A002", "Budi"));
        sistem.tambahAnggota(new PelangganRental("A003", "Citra"));
    }

    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("       RENTAL PLAYSTATION");
        System.out.println("==================================");
        System.out.println("1. Tambah Data Rental");
        System.out.println("2. Tampilkan Seluruh Data");
        System.out.println("3. Pencarian Data");
        System.out.println("4. Simulasi Rental");
        System.out.println("5. Proses Fitur dan Interface");
        System.out.println("6. Keluar");
        System.out.println("7. Kelola Data Anggota");
        System.out.println("8. Tampilkan Fasilitas (Composition)");
        System.out.println("9. Simulasi Transaksi (Association)");
        System.out.println("----------------------------------");
    }

    private static void tambahData() {
        System.out.println("\n--- Tambah Data Rental ---");
        System.out.println("1. PS3 (Rp5.000/jam)");
        System.out.println("2. PS4 (Rp10.000/jam)");
        System.out.println("3. PS5 (Rp15.000/jam)");

        int tipe = bacaInteger("Pilih tipe PS: ");

        if (tipe < 1 || tipe > 3) {
            System.out.println("Tipe PS tidak valid.");
            return;
        }

        try {
            System.out.print("Nama penyewa: ");
            String nama = input.nextLine().trim();

            if (nama.isEmpty()) {
                throw new IllegalArgumentException(
                        "Nama penyewa tidak boleh kosong.");
            }

            int durasi = bacaInteger("Durasi sewa (jam): ");

            if (durasi <= 0) {
                throw new IllegalArgumentException(
                        "Durasi sewa harus lebih dari 0 jam.");
            }

            RentalPSBase dataBaru;

            switch (tipe) {
                case 1: {
                    int stik = bacaInteger("Jumlah stik (1-4): ");
                    dataBaru = new RentalPS3(
                            idBerikutnya, nama, durasi, stik);
                    break;
                }
                case 2: {
                    System.out.print("Game favorit: ");
                    String game = input.nextLine().trim();

                    if (game.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Game favorit tidak boleh kosong.");
                    }

                    dataBaru = new RentalPS4(
                            idBerikutnya, nama, durasi, game);
                    break;
                }
                case 3: {
                    System.out.print("Fitur unggulan PS5: ");
                    String fitur = input.nextLine().trim();

                    if (fitur.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Fitur PS5 tidak boleh kosong.");
                    }

                    dataBaru = new RentalPS5(
                            idBerikutnya, nama, durasi, fitur);
                    break;
                }
                default:
                    return;
            }

            sistem.tambahRental(dataBaru);
            idBerikutnya++;

            System.out.println("Data berhasil ditambahkan.");
            System.out.printf("Total biaya: Rp%.0f%n",
                    dataBaru.hitungTotal());

        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        } finally {
            System.out.println("Proses tambah data selesai.");
        }
    }

    private static void tampilkanSemuaData() {
        System.out.println("\n====================== DAFTAR RENTAL ======================");
        System.out.printf("| %-4s | %-18s | %-8s | %-5s | %-11s | %-11s |%n",
                "ID", "Penyewa/Info", "Jenis", "Jam", "Harga/Jam", "Total");
        System.out.println("-----------------------------------------------------------");

        if (sistem.getDaftarRental().isEmpty()) {
            System.out.println("Belum ada data rental.");
            return;
        }

        for (int i = 0; i < sistem.getDaftarRental().size(); i++) {
            sistem.getDaftarRental().get(i).tampilkanDetail();
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("Jumlah data: " + sistem.getDaftarRental().size());
    }

    private static RentalPSBase cariRentalWajib(int id)
            throws RentalTidakDitemukanException {

        for (RentalPSBase data : sistem.getDaftarRental()) {
            if (data.getIdRental() == id) {
                return data;
            }
        }

        throw new RentalTidakDitemukanException(
                "Data rental dengan ID " + id + " tidak ditemukan.");
    }

    private static RentalPSBase cariRental(int id) {
        try {
            return cariRentalWajib(id);
        } catch (RentalTidakDitemukanException e) {
            return null;
        }
    }

    private static RentalPSBase cariRental(String nama) {
        for (RentalPSBase data : sistem.getDaftarRental()) {
            if (data.getNamaPenyewa().equalsIgnoreCase(nama.trim())) {
                return data;
            }
        }

        return null;
    }

    private static void menuPencarian() {
        System.out.println("\n--- Pencarian Data ---");
        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan nama");

        int pilihan = bacaInteger("Pilih cara pencarian: ");
        RentalPSBase hasil;

        switch (pilihan) {
            case 1: {
                int id = bacaInteger("Masukkan ID: ");

                try {
                    hasil = cariRentalWajib(id);
                } catch (RentalTidakDitemukanException e) {
                    System.out.println("Pencarian gagal: " + e.getMessage());
                    return;
                }
                break;
            }
            case 2:
                System.out.print("Masukkan nama penyewa: ");
                hasil = cariRental(input.nextLine());
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        if (hasil != null) {
            System.out.printf(
                    "Ditemukan: ID %d | %s | %s | %d jam | Total Rp%.0f%n",
                    hasil.getIdRental(),
                    hasil.getNamaPenyewa(),
                    hasil.getJenisPS(),
                    hasil.getDurasiJam(),
                    hasil.hitungTotal());
        } else {
            System.out.println("Data rental tidak ditemukan.");
        }
    }

    private static void menuSimulasi() {
        System.out.println("\n--- Menu Simulasi Rental ---");
        int id = bacaInteger(
                "Masukkan ID rental yang ingin disimulasikan: ");

        RentalPSBase data = cariRental(id);

        if (data == null) {
            System.out.println("Data rental tidak ditemukan.");
            return;
        }

        simulasiRental(data);
    }

    private static void simulasiRental(RentalPSBase data) {
        System.out.println("\n--- Simulasi Rental ---");
        System.out.println("ID Rental   : " + data.getIdRental());
        System.out.println("Nama        : " + data.getNamaPenyewa());
        System.out.println("Jenis PS    : " + data.getJenisPS());
        System.out.println("Durasi      : " + data.getDurasiJam() + " jam");

        // Dynamic binding
        data.tampilkanDetail();

        System.out.printf("Total biaya : Rp%.0f%n", data.hitungTotal());
    }

    private static void prosesKemampuan(RentalPSBase data) {
        System.out.println("\n--- Proses Abstract Method dan Interface ---");
        data.tampilkanFiturKhusus();

        if (data instanceof DapatDipinjam) {
            DapatDipinjam pinjam = (DapatDipinjam) data;
            pinjam.prosesPinjamFisik();
        } else {
            System.out.println(
                    "Objek ini tidak mengimplementasikan DapatDipinjam.");
        }

        if (data instanceof DapatDinilai) {
            DapatDinilai nilai = (DapatDinilai) data;
            nilai.beriRating();
        } else {
            System.out.println(
                    "Objek ini tidak mengimplementasikan DapatDinilai.");
        }
    }

    private static void menuAnggota() {
        int pilihan;

        do {
            System.out.println("\n--- Menu Data Anggota ---");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tampilkan Semua Anggota");
            System.out.println("3. Cari Anggota");
            System.out.println("4. Kembali");

            pilihan = bacaInteger("Pilih menu anggota: ");

            switch (pilihan) {
                case 1:
                    tambahAnggota();
                    break;

                case 2:
                    if (sistem.getDaftarAnggota().isEmpty()) {
                        System.out.println("Belum ada data anggota.");
                    } else {
                        System.out.println("\n--- Daftar Anggota ---");

                        for (PelangganRental anggota : sistem.getDaftarAnggota().values()) {
                            anggota.tampilkanPelanggan();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Masukkan ID anggota: ");
                    String id = input.nextLine().trim();

                    if (sistem.cariAnggota(id) != null) {
                        System.out.println(
                                "Nama anggota: " + sistem.cariAnggota(id).getNamaPelanggan());
                    } else {
                        System.out.println("Anggota tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    private static void tambahAnggota() {
        try {
            System.out.print("ID anggota: ");
            String id = input.nextLine().trim();

            if (id.isEmpty()) {
                throw new IllegalArgumentException(
                        "ID anggota tidak boleh kosong.");
            }

            if (sistem.cariAnggota(id) != null) {
                throw new IllegalArgumentException(
                        "ID anggota sudah terdaftar.");
            }

            System.out.print("Nama anggota: ");
            String nama = input.nextLine().trim();

            if (nama.isEmpty()) {
                throw new IllegalArgumentException(
                        "Nama anggota tidak boleh kosong.");
            }

            sistem.tambahAnggota(new PelangganRental(id, nama));
            System.out.println("Anggota berhasil ditambahkan.");

        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan anggota: " + e.getMessage());
        } finally {
            System.out.println("Proses anggota selesai.");
        }
    }

    private static void menuTransaksi() {
        System.out.println("\\n--- Simulasi Transaksi Rental (Association) ---");
        System.out.print("Masukkan ID anggota: ");
        String idAnggota = input.nextLine().trim();
        PelangganRental anggota = sistem.cariAnggota(idAnggota);

        if (anggota == null) {
            System.out.println("Anggota tidak ditemukan. Tambahkan anggota terlebih dahulu.");
            return;
        }

        int idRental = bacaInteger("Masukkan ID rental: ");
        RentalPSBase rental;
        try {
            rental = cariRentalWajib(idRental);
        } catch (RentalTidakDitemukanException e) {
            System.out.println("Transaksi gagal: " + e.getMessage());
            return;
        }

        TransaksiRental transaksi = new TransaksiRental(anggota, rental);
        transaksi.prosesTransaksi();
    }

    private static int bacaInteger(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                String teks = input.nextLine().trim();

                if (teks.isEmpty()) {
                    throw new InputMismatchException(
                            "Input tidak boleh kosong.");
                }

                return Integer.parseInt(teks);

            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(
                        "Input harus berupa angka bulat. Coba lagi.");
            } finally {
            }
        }
    }
}
