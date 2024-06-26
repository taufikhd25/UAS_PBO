package barang;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class LihatBarang {
    //meluncurkan aplikasi
    public static void main(String args[]) {
        Connection connection = null; //manages connection
        Statement statement = null; //query statement
        ResultSet resultSet = null; //manage results
        //JDBC driver name and database URL
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/barang";
        String usr = "root";
        String pwd = "";

        //connect to database books and query database
        try {
            //Load driver class
            Class.forName(DRIVER);
            //establish connection to database
            connection = DriverManager.getConnection(DB_URL, usr, pwd);
            //create statements for querying database
            statement = connection.createStatement();
            //query database
            String q = "SELECT * FROM barang";
            resultSet = statement.executeQuery(q);
            //proses query results
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColom = metaData.getColumnCount();
            System.out.println("Database Barang: ");
            for (int i = 1; i <= numColom; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numColom; i++)
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                System.out.println();
            } //end while
        } catch (SQLException er) {
            er.printStackTrace();
        } catch (ClassNotFoundException er) {
            er.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    } //end main
}