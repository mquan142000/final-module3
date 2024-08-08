package com.example.finalmodule3.controller;

import com.example.finalmodule3.model.Offices;
import com.example.finalmodule3.service.OfficesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "officesServlet", urlPatterns = "/offices")
public class OfficesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OfficesDao officesDao;

    public void init() {
        officesDao = new OfficesDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    addOffices(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    showNewForm(req, resp);
                    break;
                case "delete":
                    deleteOffice(req, resp);
                    break;
                case "search":
                    searchOffices(req, resp);
                    break;
                default:
                    listOffices(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listOffices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Offices> officesList = officesDao.selectAllOffices();
        request.setAttribute("listOffices", officesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("offices/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("offices/add.jsp");
        dispatcher.forward(request, response);
    }

    private void addOffices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String maMatBang = request.getParameter("ma_mat_bang");
        String trangThai = request.getParameter("trang_thai");
        double dienTich = Double.parseDouble(request.getParameter("dien_tich"));
        int tang = Integer.parseInt(request.getParameter("tang"));
        String loaiMatBang = request.getParameter("loai_mat_bang");
        double giaTien = Double.parseDouble(request.getParameter("gia_tien"));
        LocalDate ngayBatDau = LocalDate.parse(request.getParameter("ngay_bat_dau"));
        LocalDate ngayKetThuc = LocalDate.parse(request.getParameter("ngay_ket_thuc"));

        Offices newOffice = new Offices(maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc);
        officesDao.addOffices(newOffice);
        RequestDispatcher dispatcher = request.getRequestDispatcher("offices/add.jsp");
        dispatcher.forward(request, response);
        response.sendRedirect("offices?action=list");

    }

    private void deleteOffice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String maMatBang = request.getParameter("ma_mat_bang");
        if (maMatBang != null && !maMatBang.isEmpty()) {
            officesDao.deleteOffices(maMatBang);
        }
        response.sendRedirect("offices?action=list");
    }

    private void searchOffices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String loaiMatBang = request.getParameter("loai_mat_bang");
        double giaTien = Double.parseDouble(request.getParameter("gia_tien"));
        int tang = Integer.parseInt(request.getParameter("tang"));

        List<Offices> resultList = officesDao.findOffices(loaiMatBang, giaTien, tang);
        request.setAttribute("listOffices", resultList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("offices/list.jsp");
        dispatcher.forward(request, response);
    }
}
