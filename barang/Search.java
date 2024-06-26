package barang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Search {
    static final String jdbc = "jdbc:mysql://localhost:3306/";
    static final String url = "jdbc:mysql://localhost:3306/barang"; // Menggunakan database "barang"
    static final String user = "root";
    static final String password = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver tidak ditemukan.");
            e.printStackTrace();
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Cari Kode Barang : ");
            String kodeBarang = sc.next();
            System.out.print("Cari Nama Barang : ");
            String namaBarang = sc.next();
            String query = "SELECT * FROM barang WHERE kode_barang = ? OR nama_barang = ?";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, kodeBarang);
                ps.setString(2, namaBarang);
                ResultSet rs = ps.executeQuery();

                int nomor = 0;
                while (rs.next()) {
                    nomor++;
                    System.out.println("No: " + nomor);
                    System.out.println("KODE BARANG: " + rs.getString("kode_barang"));
                    System.out.println("NAMA BARANG: " + rs.getString("nama_barang"));
                    System.out.println("HARGA BARANG: " + rs.getInt("harga_barang"));
                    System.out.println("STOK BARANG: " + rs.getInt("stock_barang"));
                    System.out.println("====================================");
                }
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}