package com.mycompany.rentalps;
public class RentalPS3 extends RentalPSBase {
    private int jumlahStik;

    public RentalPS3(int idRental, String namaPenyewa, int durasiJam, int jumlahStik) {
        super(idRental, namaPenyewa, durasiJam, 5000);
        setJumlahStik(jumlahStik);
    }

    public int getJumlahStik() { return jumlahStik; }
    public void setJumlahStik(int jumlahStik) {
        if (jumlahStik < 1 || jumlahStik > 4)
            throw new IllegalArgumentException("Jumlah stik harus 1 sampai 4.");
        this.jumlahStik = jumlahStik;
    }

    @Override
    public String getJenisPS() { return "PS3"; }

    @Override
    public void tampilkanDetail() {
        System.out.printf("| %-4d | %-18s | %-8s | %-5d | Rp%-10.0f | Rp%-10.0f |%n",
                getIdRental(), getNamaPenyewa() + " (" + jumlahStik + " stik)",
                getJenisPS(), getDurasiJam(), getHargaPerJam(), hitungTotal());
    }
}
