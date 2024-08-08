package com.example.finalmodule3.service;

import com.example.finalmodule3.model.Offices;

import java.util.List;
import java.sql.SQLException;

public interface IOffices {
    public void addOffices(Offices offices) throws SQLException;

    public Offices selectOffices(int id);

    public List<Offices> selectAllOffices();

    public boolean deleteOffices(String maMatBang) throws SQLException;

    public List<Offices> findOffices(String loaiMatBang, double giaTien, int tang);
}
