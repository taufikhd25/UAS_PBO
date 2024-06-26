package barang;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormBarang extends JFrame {
    private String[] judul = {"Kode Barang", "Nama Barang", "Stok Barang", "Harga Barang"};
    DefaultTableModel df;
    JTable tab = new JTable();
    JScrollPane scp = new JScrollPane();
    JPanel pnl = new JPanel();
    JLabel lblnama = new JLabel("Nama Barang");
    JTextField txnama = new JTextField(20);
    JLabel lblkode = new JLabel("Kode Barang");
    JTextField txkode = new JTextField(10);
    JLabel lblstok = new JLabel("Stok Barang");
    JTextField txstok = new JTextField(10);
    JLabel lblharga = new JLabel("Harga Barang");
    JTextField txharga = new JTextField(10);
    JButton btadd = new JButton("Simpan");
    JButton btnew = new JButton("Baru");
    JButton btdel = new JButton("Hapus");
    JButton btedit = new JButton("Ubah");

    public FormBarang() {
        super("Data Barang");
        setSize(460, 300);
        pnl.setLayout(null);
        pnl.add(lblkode);
        lblkode.setBounds(20, 10, 100, 20);
        pnl.add(txkode);
        txkode.setBounds(125, 10, 100, 20);
        pnl.add(lblnama);
        lblnama.setBounds(20, 33, 100, 20);
        pnl.add(txnama);
        txnama.setBounds(125, 33, 175, 20);
        pnl.add(lblstok);
        lblstok.setBounds(20, 56, 100, 20);
        pnl.add(txstok);
        txstok.setBounds(125, 56, 100, 20);
        pnl.add(lblharga);
        lblharga.setBounds(20, 79, 100, 20);
        pnl.add(txharga);
        txharga.setBounds(125, 79, 100, 20);

        pnl.add(btnew);
        btnew.setBounds(300, 10, 125, 20);
        btnew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnewAksi(e);
            }
        });
        pnl.add(btadd);
        btadd.setBounds(300, 33, 125, 20);
        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btaddAksi(e);
            }
        });
        pnl.add(btedit);
        btedit.setBounds(300, 56, 125, 20);
        btedit.setEnabled(false);
        btedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bteditAksi(e);
            }
        });
        pnl.add(btdel);
        btdel.setBounds(300, 79, 125, 20);
        btdel.setEnabled(false);
        btdel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btdelAksi(e);
            }
        });
        df = new DefaultTableModel(null, judul);
        tab.setModel(df);
        scp.getViewport().add(tab);
        tab.setEnabled(true);
        tab.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });
        scp.setBounds(20, 110, 405, 130);
        pnl.add(scp);
        getContentPane().add(pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void loadData() {
        try {
            Connection cn = new connecDB().getConnect();
            Statement st = cn.createStatement();
            String sql = "SELECT * FROM barang";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String kodeBarang, namaBarang, stockBarang, hargaBarang;
                kodeBarang = rs.getString("kode_barang");
                namaBarang = rs.getString("nama_barang");
                stockBarang = rs.getString("stock_Barang");
                hargaBarang = rs.getString("harga_barang");
                String[] data = {kodeBarang, namaBarang, stockBarang, hargaBarang};
                df.addRow(data);
            }
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void clearTable() {
        int numRow = df.getRowCount();
        for (int i = 0; i < numRow; i++) {
            df.removeRow(0);
        }
    }

    void clearTextField() {
        txkode.setText(null);
        txnama.setText(null);
        txstok.setText(null);
        txharga.setText(null);
    }

    void simpanData(Barang B) {
        try {
            Connection cn = new connecDB().getConnect();
            String sql = "INSERT INTO barang (kode_barang, nama_barang, stock_Barang, harga_barang) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, B.getKodeBarang());
            ps.setString(2, B.getNamaBarang());
            ps.setString(3, B.getStockBarang());
            ps.setString(4, B.getHargaBarang());
            ps.executeUpdate();
            cn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Info Proses", JOptionPane.INFORMATION_MESSAGE);
            String[] data = {B.getKodeBarang(), B.getNamaBarang(), B.getStockBarang(), B.getHargaBarang()};
            df.addRow(data);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void hapusData(String kodeBarang) {
        try {
            Connection cn = new connecDB().getConnect();
            String sql = "DELETE FROM barang WHERE kode_barang = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, kodeBarang);
            ps.executeUpdate();
            cn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Info Proses", JOptionPane.INFORMATION_MESSAGE);
            df.removeRow(tab.getSelectedRow());
            clearTextField();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void ubahData(Barang B, String kodeBarang) {
        try {
            Connection cn = new connecDB().getConnect();
            String sql = "UPDATE barang SET kode_barang=?, nama_barang=?, stock_Barang=?, harga_barang=? WHERE kode_barang=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, B.getKodeBarang());
            ps.setString(2, B.getNamaBarang());
            ps.setString(3, B.getStockBarang());
            ps.setString(4, B.getHargaBarang());
            ps.setString(5, kodeBarang);
            ps.executeUpdate();
            cn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Info Proses", JOptionPane.INFORMATION_MESSAGE);
            clearTable();
            loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void btnewAksi(ActionEvent evt) {
        clearTextField();
        btedit.setEnabled(false);
        btdel.setEnabled(false);
        btadd.setEnabled(true);
    }

    private void btaddAksi(ActionEvent evt) {
        Barang B = new Barang();
        B.setKodeBarang(txkode.getText());
        B.setNamaBarang(txnama.getText());
        B.setStockBarang(txstok.getText());
        B.setHargaBarang(txharga.getText());
        simpanData(B);
    }

    private void btdelAksi(ActionEvent evt) {
        int status;
        status = JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
        if (status == 0) {
            hapusData(txkode.getText());
        }
    }

    private void bteditAksi(ActionEvent evt) {
        Barang B = new Barang();
        B.setKodeBarang(txkode.getText());
        B.setNamaBarang(txnama.getText());
        B.setStockBarang(txstok.getText());
        B.setHargaBarang(txharga.getText());
        ubahData(B, tab.getValueAt(tab.getSelectedRow(), 0).toString());
    }

    private void tabMouseClicked(MouseEvent evt) {
        btedit.setEnabled(true);
        btdel.setEnabled(true);
        btadd.setEnabled(false);
        String kodeBarang, namaBarang, stockBarang, hargaBarang;
        kodeBarang = tab.getValueAt(tab.getSelectedRow(), 0).toString();
        namaBarang = tab.getValueAt(tab.getSelectedRow(), 1).toString();
        stockBarang = tab.getValueAt(tab.getSelectedRow(), 2).toString();
        hargaBarang = tab.getValueAt(tab.getSelectedRow(), 3).toString();
        txkode.setText(kodeBarang);
        txnama.setText(namaBarang);
        txstok.setText(stockBarang);
        txharga.setText(hargaBarang);
    }

    public static void main(String[] args) {
        new FormBarang().loadData();
    }
}