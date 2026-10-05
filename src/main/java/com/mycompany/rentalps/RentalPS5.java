package com.mycompany.rentalps;

public class RentalPS5 extends RentalPSBase {
    private String fiturUnggulan;

    public RentalPS5(int idRental, String namaPenyewa, int durasiJam, String fiturUnggulan) {
        super(idRental, namaPenyewa, durasiJam, 15000);
        setFiturUnggulan(fiturUnggulan);
    }

    public String getFiturUnggulan() {
        return fiturUnggulan;
    }

    public void setFiturUnggulan(String fiturUnggulan) {
        if (fiturUnggulan == null || fiturUnggulan.trim().isEmpty()) {
            throw new IllegalArgumentException("Fitur unggulan tidak boleh kosong.");
        }
        this.fiturUnggulan = fiturUnggulan.trim();
    }

    @Override
    public String getJenisPS() {
        return "PS5";
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("| %-4d | %-18s | %-8s | %-5d | Rp%-10.0f | Rp%-10.0f |%n",
                getIdRental(),
                getNamaPenyewa() + " (" + fiturUnggulan + ")",
                getJenisPS(),
                getDurasiJam(),
                getHargaPerJam(),
                hitungTotal());
    }
}
