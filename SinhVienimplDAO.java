package SinhVienDatabase2;

import SinhVienDatabase.ConnectDemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SinhVienimplDAO implements DAO {   
    private String selectall = "select * from sinhvien";
    private String sqlInsert = "Insert into sinhvien (hoten,diachi,tenlop,namsinh) values (?,?,?,?)";
    private String sqlUpdate = "update sinhvien set hoTen=?,diaChi=?,tenLop=?,namSinh=? where id=?";
    private String sqlDelete = "delete from SINHVIEN where ID=?";
    private String sqlFindByID ="select * from sinhvien  where id=?";
    private String sqlFindByName = "select *from sinhvien where hoTen=?";
    
    private static void closeConnec(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void closeStatement(Statement stmt) {// truy vấn csdl
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public ArrayList<SinhVien> getAllSinhVien() throws SQLException {
        Connection con = getConnection();
        Statement st = null;
        ArrayList<SinhVien> listAll = new ArrayList<SinhVien>();
        ResultSet rs = null;
        if (con != null) {
            st = con.createStatement();
            rs = st.executeQuery(selectall);
            while (rs.next()) {
                SinhVien sv = new SinhVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                listAll.add(sv);
            }
        }
        return listAll;
    }

    public void insert(SinhVien sv) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            pr = con.prepareStatement(sqlInsert);
            //  pr.setInt(1, _id);
            pr.setString(1, sv.getHoTen());
            pr.setString(2, sv.getDiaChi());
            pr.setString(3, sv.getTenLop());
            pr.setString(4, sv.getNamSinh());
            System.out.println(pr.toString());
            pr.executeUpdate();
            pr.close();

        }
    }

    public void updateSinhVien(SinhVien sv) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pr = null;

        pr = con.prepareStatement(sqlUpdate);
        pr.setString(1, sv.getHoTen());
        pr.setString(2, sv.getDiaChi());
        pr.setString(3, sv.getTenLop());
        pr.setString(4, sv.getNamSinh());
        pr.setInt(5, sv.getId());
        pr.executeUpdate();
        pr.close();
    }

    public boolean DeleteSinhVien(SinhVien sv) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pr = null;
        int k = 0;

        pr = con.prepareStatement(sqlDelete);
        pr.setInt(1, sv.getId());
        k = pr.executeUpdate();
        pr.close();

        if (k > 0) {
            return true;
        }
        return false;
    }

    public SinhVien findSinhVienByID(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        con = getConnection();
        stmt = con.prepareStatement(sqlFindByID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            SinhVien sv = new SinhVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            rs.close();
            return sv;
        } else {
            return null;
        }
    }

    public SinhVien findByName(String name) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        con = getConnection();
        stmt = con.prepareStatement(sqlFindByName);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            SinhVien sv = new SinhVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            rs.close();
            stmt.close();
            return sv;
        } else {
            return null;
        }
    }
//
    public void showData() throws SQLException {
        ArrayList<SinhVien> listSinhVien = getAllSinhVien();
//        for(int i=0;i<listSinhVien.size();i++)
//        {SinhVien sv=listSinhVien.get(i);
//            System.out.println(sv.toString());
//        }
        for (SinhVien sv : listSinhVien) {
            System.out.println(sv.toString());
        }
    }

    public static void main(String args[]) {
        try {
            SinhVienimplDAO demo = new SinhVienimplDAO();

//            SinhVien sv = new SinhVien("7", "Truong Quang Dat", "Viet Giang", "2002");
//            demo.insert(sv);
//             SinhVien svXoa = demo.findSinhVienByID(6);

//            System.out.println("Sau khi them 1 Sinh vien vao bang");
//            demo.showData();
//            demo.DeleteSinhVien(svXoa);
            System.out.println("Sau khi xoa sinh vien thu 6");
            demo.showData();
            SinhVien svSua = demo.findSinhVienByID(7);
            svSua.setTenLop("KTPMK19A");
            demo.updateSinhVien(svSua);
            System.out.println("Sau khi sua ");
            demo.showData();
//        ResultSet r=demo.getData();
//         if(r!=null){
//             System.out.println("du lieu sau khi insert");
//             demo.showData(r);
//         }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
