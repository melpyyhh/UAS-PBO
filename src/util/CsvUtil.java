package util;

import model.Registrasi;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvUtil {
    public static void writeToCsv(List<Registrasi> registrasiList, String filePath) {
    	try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Nama,NIM,Umur,Kelas,Tanggal,Jam,Keluhan\n");
            for (Registrasi registrasi : registrasiList) {
                writer.append(registrasi.getNama()).append(',')
                      .append(registrasi.getNim()).append(',')
                      .append(String.valueOf(registrasi.getUmur())).append(',')
                      .append(registrasi.getKelas()).append(',')
                      .append(registrasi.getTanggal().toString()).append(',')
                      .append(registrasi.getJam()).append(',')
                      .append(registrasi.getKeluhan()).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
