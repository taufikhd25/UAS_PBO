package barang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/barang";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    static Connection conn;
    static PreparedStatement ps;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Tidak dapat menemukan driver JDBC MySQL.");
            e.printStackTrace();
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Masukkan Kode Barang (max 10 karakter): ");
            String kodeBarang = sc.nextLine();
            if (kodeBarang.length() > 10) {
                System.out.println("Kode Barang terlalu panjang. Harus kurang dari 10 karakter.");
                return;
            }
            System.out.print("Masukkan Nama Barang (max 50 karakter): ");
            String namaBarang = sc.nextLine();
            if (namaBarang.length() > 50) {
                System.out.println("Nama Barang terlalu panjang. Harus kurang dari 50 karakter.");
                return;
            }
            System.out.print("Masukkan Harga Barang: ");
            double hargaBarang = sc.nextDouble();
            System.out.print("Masukkan Stok Barang: ");
            int stokBarang = sc.nextInt();

            String query = "INSERT INTO barang (kode_barang, nama_barang, harga_barang, stock_barang) VALUES (?, ?, ?, ?)";
            try {
                conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                ps = conn.prepareStatement(query);
                ps.setString(1, kodeBarang);
                ps.setString(2, namaBarang);
                ps.setDouble(3, hargaBarang);
                ps.setInt(4, stokBarang);
                ps.execute();
                System.out.println("Data berhasil disisipkan!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
