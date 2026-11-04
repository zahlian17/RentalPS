package com.mycompany.rentalps;

public class PelangganRental {
    private final String idPelanggan;
    private final String namaPelanggan;

    public PelangganRental(String idPelanggan, String namaPelanggan) {
        if (idPelanggan == null || idPelanggan.trim().isEmpty()) {
            throw new IllegalArgumentException("ID pelanggan tidak boleh kosong.");
        }
        if (namaPelanggan == null || namaPelanggan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong.");
        }
        this.idPelanggan = idPelanggan.trim();
        this.namaPelanggan = namaPelanggan.trim();
    }

    public String getIdPelanggan() { return idPelanggan; }
    public String getNamaPelanggan() { return namaPelanggan; }

    public void tampilkanPelanggan() {
        System.out.println(idPelanggan + " - " + namaPelanggan);
    }
}