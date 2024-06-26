package barang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowDataGUI extends JFrame {
    private JTextField kodeField, namaField, stokField, hargaField;
    private JButton newButton, saveButton, updateButton, deleteButton;
    private JTable table;
    private DefaultTableModel model;
    private connecDB db;

    public ShowDataGUI() {
        db = new connecDB();
        setTitle("Data Barang");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("Kode Barang"));
        kodeField = new JTextField();
        inputPanel.add(kodeField);
        
        inputPanel.add(new JLabel("Nama Barang"));
        namaField = new JTextField();
        inputPanel.add(namaField);
        
        inputPanel.add(new JLabel("Stok Barang"));
        stokField = new JTextField();
        inputPanel.add(stokField);
        
        inputPanel.add(new JLabel("Harga Barang"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);
        
        newButton = new JButton("Baru");
        saveButton = new JButton("Simpan");
        updateButton = new JButton("Ubah");
        deleteButton = new JButton("Hapus");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(inputPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.EAST);

        add(leftPanel, BorderLayout.NORTH);

        String[] columnNames = {"Kode Barang", "Nama Barang", "Stok Barang", "Harga Barang"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
        addListeners();
    }

    private void loadData() {
        List<String[]> data = db.getDataBarang();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    private void addListeners() {
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save data to the database and refresh table
                // Add your code here
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update data in the database and refresh table
                // Add your code here
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete data from the database and refresh table
                // Add your code here
            }
        });
    }

    private void clearInput() {
        kodeField.setText("");
        namaField.setText("");
        stokField.setText("");
        hargaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowDataGUI().setVisible(true);
            }
        });
    }
}
