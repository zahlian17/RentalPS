package com.mycompany.rentalps;
import java.util.Scanner;

public class RentalPS {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Array untuk menyimpan nama pelanggan
        String[] namaPelanggan = new String[10];

        // Array untuk menyimpan jenis PS
        String[] jenisPS = new String[10];

        // Array untuk menyimpan lama rental
        int[] lamaRental = new int[10];

        // Variabel untuk menghitung jumlah data rental
        int jumlahRental = 0;

        // Penanda apakah program masih berjalan
        boolean isRunning = true;

        System.out.println("==================================");
        System.out.println("       RENTAL PLAYSTATION");
        System.out.println("==================================");

        while (isRunning) {

            System.out.println("\nMenu Rental PS:");
            System.out.println("1. Tambah Data Rental");
            System.out.println("2. Lihat Data Rental");
            System.out.println("3. Keluar");
            System.out.println("==================================");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {

                case 1:

                    if (jumlahRental < namaPelanggan.length) {

                        System.out.println("\n--- Tambah Data Rental ---");

                        System.out.print("Nama Pelanggan : ");
                        namaPelanggan[jumlahRental] = scanner.nextLine();

                        System.out.print("Jenis PS (PS3/PS4/PS5) : ");
                        jenisPS[jumlahRental] = scanner.nextLine();

                        System.out.print("Lama Rental (jam) : ");
                        lamaRental[jumlahRental] = scanner.nextInt();
                        scanner.nextLine();

                        jumlahRental++;

                        System.out.println("Data rental berhasil ditambahkan!");

                    } else {

                        System.out.println("Data rental sudah penuh!");

                    }

                    break;

                case 2:

                    System.out.println("\n--- Daftar Rental PS ---");

                    if (jumlahRental == 0) {

                        System.out.println("Belum ada data rental.");

                    } else {

                        for (int i = 0; i < jumlahRental; i++) {

                            System.out.println("\nData Rental ke-" + (i + 1));
                            System.out.println("Nama Pelanggan : "
                                    + namaPelanggan[i]);
                            System.out.println("Jenis PS       : "
                                    + jenisPS[i]);
                            System.out.println("Lama Rental    : "
                                    + lamaRental[i] + " jam");

                            int hargaPerJam;

                            if (jenisPS[i].equalsIgnoreCase("PS3")) {
                                hargaPerJam = 5000;
                            } else if (jenisPS[i].equalsIgnoreCase("PS4")) {
                                hargaPerJam = 8000;
                            } else if (jenisPS[i].equalsIgnoreCase("PS5")) {
                                hargaPerJam = 10000;
                            } else {
                                hargaPerJam = 0;
                            }

                            int totalBiaya = hargaPerJam * lamaRental[i];

                            System.out.println("Harga Per Jam  : Rp " + hargaPerJam);
                            System.out.println("Total Biaya    : Rp " + totalBiaya);
                        }
                    }

                    break;

                case 3:

                    isRunning = false;

                    System.out.println("\nTerima kasih telah menggunakan");
                    System.out.println("sistem Rental PlayStation!");

                    break;

                default:

                    System.out.println("\nPilihan menu tidak tersedia!");

                    break;
            }
        }

        scanner.close();
    }
}