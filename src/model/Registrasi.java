package model;

import java.time.LocalDate;

public class Registrasi {
	private String nama;
    private String nim;
    private int umur;
    private String kelas;
    private LocalDate tanggal;
    private String jam;
    private String keluhan;
    
    public Registrasi(String nama, String nim, int umur, String kelas, LocalDate tanggal, String jam, String keluhan) {
        this.nama = nama;
        this.nim = nim;
        this.umur = umur;
        this.kelas = kelas;
        this.tanggal = tanggal;
        this.jam = jam;
        this.keluhan = keluhan;
    }

	public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public int getUmur() {
        return umur;
    }

    public String getKelas() {
        return kelas;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getJam() {
        return jam;
    }

    public String getKeluhan() {
        return keluhan;
    }
}
