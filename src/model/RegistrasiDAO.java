package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseConnection;

public class RegistrasiDAO {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static RegistrasiDAO instance;
	
	public static RegistrasiDAO getInstance() {
        if (instance == null) {
            instance = new RegistrasiDAO();
        }
        return instance;
    }

	
	public void save(Registrasi regist) {
        String sql = "INSERT INTO regist(nama, nim, umur, kelas, tanggal, jam, keluhan) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, regist.getNama());
            pstmt.setString(2, regist.getNim());
            pstmt.setInt(3, regist.getUmur());
            pstmt.setString(4, regist.getKelas());
            pstmt.setObject(5, regist.getTanggal());
            pstmt.setString(6, regist.getJam());
            pstmt.setString(7, regist.getKeluhan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public List<Registrasi> getRegistrasiByNim(String nim) {
        List<Registrasi> registrasiList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM regist WHERE nim = ? ORDER BY tanggal ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nim);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int umur = resultSet.getInt("umur");
                String kelas = resultSet.getString("kelas");
                LocalDate tanggal = LocalDate.parse(resultSet.getString("tanggal"), DATE_FORMATTER);
                String jam = resultSet.getString("jam");
                String keluhan = resultSet.getString("keluhan");
                Registrasi registrasi = new Registrasi(nama, nim, umur, kelas, tanggal, jam, keluhan);
                registrasiList.add(registrasi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrasiList;
    }

    public Registrasi getUserData(String nim) {
        Registrasi registrasi = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
        	String query = "SELECT * FROM regist WHERE nim = ? ORDER BY tanggal DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nim);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int umur = resultSet.getInt("umur");
                String kelas = resultSet.getString("kelas");
                LocalDate tanggal = LocalDate.parse(resultSet.getString("tanggal"), DATE_FORMATTER);
                String jam = resultSet.getString("jam");
                String keluhan = resultSet.getString("keluhan");
                registrasi = new Registrasi(nim, nama, umur, kelas, tanggal, jam, keluhan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrasi;
    }
}
