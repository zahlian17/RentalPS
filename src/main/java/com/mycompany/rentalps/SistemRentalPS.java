package com.mycompany.rentalps;

import java.util.ArrayList;
import java.util.HashMap;

public class SistemRentalPS {
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
}
