package barang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Delete {
    static final String jdbc = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/barang";
    static final String username = "root";
    static final String password = "";
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public static void main(String[] args) {
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Masukkan Kode Barang yang akan dihapus: ");
            String kodeBarang = br.readLine();
            String query = "delete from tbl_barang where kode_barang=?";
            ps = con.prepareStatement(query);
            ps.setString(1, kodeBarang);
            if (ps.executeUpdate() > 0) {
                System.out.println("Proses berhasil");
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Kesalahan koneksi driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Kesalahan koneksi database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Kesalahan umum: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Kesalahan penutupan koneksi: " + e.getMessage());
            }
        }
    }
}