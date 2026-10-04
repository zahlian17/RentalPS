package com.mycompany.rentalps;
public class RentalPS4 extends RentalPSBase {
    private String gameFavorit;

    public RentalPS4(int idRental, String namaPenyewa, int durasiJam, String gameFavorit) {
        super(idRental, namaPenyewa, durasiJam, 10000);
        setGameFavorit(gameFavorit);
    }

    public String getGameFavorit() { return gameFavorit; }
    public void setGameFavorit(String gameFavorit) {
        if (gameFavorit == null || gameFavorit.trim().isEmpty())
            throw new IllegalArgumentException("Game favorit tidak boleh kosong.");
        this.gameFavorit = gameFavorit.trim();
    }

    @Override
    public String getJenisPS() { return "PS4"; }

    @Override
    public void tampilkanDetail() {
        System.out.printf("| %-4d | %-18s | %-8s | %-5d | Rp%-10.0f | Rp%-10.0f |%n",
                getIdRental(), getNamaPenyewa() + " (" + gameFavorit + ")",
                getJenisPS(), getDurasiJam(), getHargaPerJam(), hitungTotal());
    }
}
