package com.example.finalmodule3.service;

import com.example.finalmodule3.model.Offices;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfficesDao implements IOffices {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quan_ly_mat_bang";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "12345@abc";

    public OfficesDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void addOffices(Offices offices) throws SQLException {
        String sql = "INSERT INTO offices (ma_mat_bang, trang_thai, dien_tich, tang, loai_mat_bang, gia_tien, ngay_bat_dau, ngay_ket_thuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, offices.getMaMatBang());
            statement.setString(2, offices.getTrangThai());
            statement.setDouble(3, offices.getDienTich());
            statement.setInt(4, offices.getTang());
            statement.setString(5, offices.getLoaiMatBang());
            statement.setDouble(6, offices.getGiaTien());
            statement.setDate(7, java.sql.Date.valueOf(offices.getNgayBatDau()));
            statement.setDate(8, java.sql.Date.valueOf(offices.getNgayKetThuc()));
            statement.executeUpdate();
        }
    }

    @Override
    public Offices selectOffices(int id) {
        Offices offices = null;
        String sql = "SELECT * FROM offices WHERE id =?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maMatBang = resultSet.getString("ma_mat_bang");
                String trangThai = resultSet.getString("trang_thai");
                double dienTich = resultSet.getDouble("dien_tich");
                int tang = resultSet.getInt("tang");
                String loaiMatBang = resultSet.getString("loai_mat_bang");
                double giaTien = resultSet.getDouble("gia_tien");
                LocalDate ngayBatDau = resultSet.getDate("ngay_bat_dau").toLocalDate();
                LocalDate ngayKetThuc = resultSet.getDate("ngay_ket_thuc").toLocalDate();

                offices = new Offices(maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offices;
    }

    @Override
    public List<Offices> selectAllOffices() {
        List<Offices> officesList = new ArrayList<>();
        String sql = "SELECT * FROM offices";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String maMatBang = resultSet.getString("ma_mat_bang");
                String trangThai = resultSet.getString("trang_thai");
                double dienTich = resultSet.getDouble("dien_tich");
                int tang = resultSet.getInt("tang");
                String loaiMatBang = resultSet.getString("loai_mat_bang");
                double giaTien = resultSet.getDouble("gia_tien");
                LocalDate ngayBatDau = resultSet.getDate("ngay_bat_dau").toLocalDate();
                LocalDate ngayKetThuc = resultSet.getDate("ngay_ket_thuc").toLocalDate();

                officesList.add(new Offices(maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return officesList;
    }

    @Override
    public boolean deleteOffices(String maMatBang) throws SQLException {
        String sql = "DELETE FROM offices WHERE ma_mat_bang = ?";
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maMatBang);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public List<Offices> findOffices(String loaiMatBang, double giaTien, int tang) {
        List<Offices> officesList = new ArrayList<>();
        String sql = "SELECT * FROM offices WHERE loai_mat_bang =? AND gia_tien =? AND tang =?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, loaiMatBang);
            statement.setDouble(2, giaTien);
            statement.setInt(3, tang);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maMatBang = resultSet.getString("ma_mat_bang");
                String trangThai = resultSet.getString("trang_thai");
                double dienTich = resultSet.getDouble("dien_tich");
                int tangResult = resultSet.getInt("tang");
                String loaiMatBangResult = resultSet.getString("loai_mat_bang");
                double giaTienResult = resultSet.getDouble("gia_tien");
                LocalDate ngayBatDau = resultSet.getDate("ngay_bat_dau").toLocalDate();
                LocalDate ngayKetThuc = resultSet.getDate("ngay_ket_thuc").toLocalDate();

                Offices offices = new Offices(
                        maMatBang,
                        trangThai,
                        dienTich,
                        tangResult,
                        loaiMatBangResult,
                        giaTienResult,
                        ngayBatDau,
                        ngayKetThuc
                );
                officesList.add(offices);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return officesList;
    }
}
