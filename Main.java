package SinhVienDatabase2;

import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) {
        
        SinhVienView2 sinhvienView = new SinhVienView2();// khởi tạo obj sinhvienView ( tạo ra cái bảng , có hàng cột, button nhưng chưa có dữ liệu)
        try {
            SinhVienimplDAO sinhVienDAO = new SinhVienimplDAO();// kết nối tới dtb
            SinhVienTableModel sinhvienModel = new SinhVienTableModel(sinhVienDAO.getAllSinhVien());// lấy dữ liệu trong dtb
            SinhVienController2 controller = new SinhVienController2(sinhvienView, sinhvienModel);// tương tác với dữ liệu trong dtb
            controller.showSinhVienView();// cập nhật lại bảng sau khi thay đổi
        } catch (SQLException e) {
            sinhvienView.showMessage(e.toString());
        }
    }
}