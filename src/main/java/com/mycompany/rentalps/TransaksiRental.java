package com.mycompany.rentalps;

public class TransaksiRental {
    private final PelangganRental pelanggan;
    private final RentalPSBase rental;

    public TransaksiRental(PelangganRental pelanggan, RentalPSBase rental) {
        if (pelanggan == null || rental == null) {
            throw new IllegalArgumentException("Pelanggan dan rental wajib diisi.");
        }
        this.pelanggan = pelanggan;
        this.rental = rental;
    }

    public void prosesTransaksi() {
        System.out.println("\\n=== STATUS TRANSAKSI RENTAL (ASSOCIATION) ===");
        System.out.println("ID Pelanggan : " + pelanggan.getIdPelanggan());
        System.out.println("Nama         : " + pelanggan.getNamaPelanggan());
        System.out.println("ID Rental    : " + rental.getIdRental());
        System.out.println("Jenis PS     : " + rental.getJenisPS());
        System.out.println("Durasi       : " + rental.getDurasiJam() + " jam");
        System.out.printf("Total biaya  : Rp%.0f%n", rental.hitungTotal());
        System.out.println("Status       : Transaksi berhasil disimulasikan.");
    }
}
