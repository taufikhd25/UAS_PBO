package barang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowData {
    static final String jdbc = "jdbc:mysql://localhost:3306/";
    static final String url = "jdbc:mysql://localhost:3306/toko";  // ganti mahasiswa menjadi barang
    static final String user = "root";
    static final String password = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM tbl_barang";  // ganti mahasiswa menjadi barang
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);

            int nomor = 0;
            while (rs.next()) {
                nomor++;
                System.out.println("No: " + nomor);
                System.out.println("KODE BARANG: " + rs.getString("kode_barang"));
                System.out.println("NAMA BARANG: " + rs.getString("nama_barang"));
                System.out.println("HARGA BARANG: " + rs.getString("harga_barang"));
                System.out.println("STOK BARANG: " + rs.getString("stock_barang"));
                System.out.println("====================================");
            }

            conn.close(); // tutup koneksi setelah selesai
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}