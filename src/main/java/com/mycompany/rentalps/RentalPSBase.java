package com.mycompany.rentalps;
public class RentalPSBase {
    private int idRental;
    private String namaPenyewa;
    private int durasiJam;
    private double hargaPerJam;
    private static int totalObjek = 0;

    public RentalPSBase(int idRental, String namaPenyewa, int durasiJam, double hargaPerJam) {
        setIdRental(idRental);
        setNamaPenyewa(namaPenyewa);
        setDurasiJam(durasiJam);
        setHargaPerJam(hargaPerJam);
        totalObjek++;
    }

    public int getIdRental() { return idRental; }
    public void setIdRental(int idRental) {
        if (idRental <= 0) throw new IllegalArgumentException("ID harus lebih dari 0.");
        this.idRental = idRental;
    }

    public String getNamaPenyewa() { return namaPenyewa; }
    public void setNamaPenyewa(String namaPenyewa) {
        if (namaPenyewa == null || namaPenyewa.trim().isEmpty())
            throw new IllegalArgumentException("Nama penyewa tidak boleh kosong.");
        this.namaPenyewa = namaPenyewa.trim();
    }

    public int getDurasiJam() { return durasiJam; }
    public void setDurasiJam(int durasiJam) {
        if (durasiJam <= 0) throw new IllegalArgumentException("Durasi harus lebih dari 0 jam.");
        this.durasiJam = durasiJam;
    }

    public double getHargaPerJam() { return hargaPerJam; }
    public void setHargaPerJam(double hargaPerJam) {
        if (hargaPerJam <= 0) throw new IllegalArgumentException("Harga harus lebih dari 0.");
        this.hargaPerJam = hargaPerJam;
    }

    public static int getTotalObjek() { return totalObjek; }
    public double hitungTotal() { return durasiJam * hargaPerJam; }
    public String getJenisPS() { return "PlayStation"; }

    public void tampilkanDetail() {
        System.out.printf("| %-4d | %-18s | %-8s | %-5d | Rp%-10.0f | Rp%-10.0f |%n",
                idRental, namaPenyewa, getJenisPS(), durasiJam, hargaPerJam, hitungTotal());
    }
}
