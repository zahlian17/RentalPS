package com.mycompany.rentalps;

public class FasilitasRental {
    private final String namaFasilitas;
    private final int jumlahUnit;

    public FasilitasRental(String namaFasilitas, int jumlahUnit) {
        if (namaFasilitas == null || namaFasilitas.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama fasilitas tidak boleh kosong.");
        }
        if (jumlahUnit <= 0) {
            throw new IllegalArgumentException("Jumlah unit harus lebih dari 0.");
        }
        this.namaFasilitas = namaFasilitas.trim();
        this.jumlahUnit = jumlahUnit;
    }

    public void tampilkanFasilitas() {
        System.out.println("\\n=== FASILITAS RENTAL PS (COMPOSITION) ===");
        System.out.println("Nama fasilitas : " + namaFasilitas);
        System.out.println("Jumlah unit    : " + jumlahUnit);
    }
}
