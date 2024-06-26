package barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connecDB {
    private String url;
    private String usr;
    private String pwd;
    private String db;

    public connecDB() {
        db = "toko";
        url = "jdbc:mysql://localhost/" + db;
        usr = "root";
        pwd = "";
    }

    public Connection getConnect() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, usr, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }

    public List<String[]> getDataBarang() {
        List<String[]> data = new ArrayList<>();
        try {
            Connection cn = getConnect();
            String query = "SELECT * FROM tbl_barang";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String[] row = {
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("stock_barang"),
                    rs.getString("harga_barang")
                };
                data.add(row);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // Methods to add, update, and delete data (if needed)
    // ...
}
