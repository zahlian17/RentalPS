package com.mycompany.rentalps;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SistemRentalPS implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    // Composition: komponen dibuat langsung di dalam constructor manager.
    private final FasilitasRental fasilitas;
    // Aggregation: manager mengelola koleksi objek rental dan pelanggan.
    private final ArrayList<RentalPSBase> daftarRental;
    private final HashMap<String, PelangganRental> daftarAnggota;

    public SistemRentalPS() {
        this.fasilitas = new FasilitasRental("Unit PlayStation", 4);
        this.daftarRental = new ArrayList<>();
        this.daftarAnggota = new HashMap<>();
    }

    public void tambahRental(RentalPSBase rental) {
        if (rental == null) throw new IllegalArgumentException("Data rental tidak boleh null.");
        daftarRental.add(rental);
    }

    public ArrayList<RentalPSBase> getDaftarRental() {
        return daftarRental;
    }

    public void tambahAnggota(PelangganRental anggota) {
        if (anggota == null) throw new IllegalArgumentException("Data anggota tidak boleh null.");
        if (daftarAnggota.containsKey(anggota.getIdPelanggan())) {
            throw new IllegalArgumentException("ID anggota sudah terdaftar.");
        }
        daftarAnggota.put(anggota.getIdPelanggan(), anggota);
    }

    public HashMap<String, PelangganRental> getDaftarAnggota() {
        return daftarAnggota;
    }

    public PelangganRental cariAnggota(String id) {
        if (id == null) return null;
        return daftarAnggota.get(id.trim());
    }

    public void tampilkanSemuaRental() {
        if (daftarRental.isEmpty()) {
            System.out.println("Belum ada data rental.");
            return;
        }
        for (RentalPSBase rental : daftarRental) {
            rental.tampilkanDetail();
        }
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        for (PelangganRental anggota : daftarAnggota.values()) {
            anggota.tampilkanPelanggan();
        }
    }

    public void tampilkanFasilitas() {
        fasilitas.tampilkanFasilitas();
    }

    // MODUL 11: Serialization menyimpan seluruh objek manager ke file .dat.
    public void simpanDataKeFile() {
        try (FileOutputStream fileKeluar = new FileOutputStream("data_rentalps.dat");
             ObjectOutputStream objekKeluar = new ObjectOutputStream(fileKeluar)) {
            objekKeluar.writeObject(this);
            System.out.println("[BERHASIL] Data rental dan anggota tersimpan ke data_rentalps.dat.");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    public static SistemRentalPS muatDataDariFile() {
        File file = new File("data_rentalps.dat");
        if (!file.exists()) {
            System.out.println("File data belum tersedia. Program menggunakan data awal.");
            return null;
        }
        try (FileInputStream fileMasuk = new FileInputStream(file);
             ObjectInputStream objekMasuk = new ObjectInputStream(fileMasuk)) {
            SistemRentalPS hasil = (SistemRentalPS) objekMasuk.readObject();
            System.out.println("[BERHASIL] Data berhasil dimuat dari data_rentalps.dat.");
            return hasil;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Gagal memuat data: " + e.getMessage());
            return null;
        }
    }

    // Membaca file log transaksi menggunakan File dan Scanner.
    public void bacaLogTransaksi() {
        System.out.println("\n========== RIWAYAT LOG TRANSAKSI ==========");
        File fileLog = new File("log_transaksi.txt");
        try (Scanner pembaca = new Scanner(fileLog)) {
            while (pembaca.hasNextLine()) {
                System.out.println(pembaca.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Belum ada file log transaksi.");
        }
    }

}
