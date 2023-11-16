package SinhVienDatabase2;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DAO {

    public ArrayList<SinhVien> getAllSinhVien()throws SQLException;

    public void insert(SinhVien sv)throws SQLException;

    public void updateSinhVien(SinhVien sv)throws SQLException;

    public boolean DeleteSinhVien(SinhVien sv)throws SQLException;

    public SinhVien findSinhVienByID(int id)throws SQLException;

    public SinhVien findByName(String name)throws SQLException;
}
