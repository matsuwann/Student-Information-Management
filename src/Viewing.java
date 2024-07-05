import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Viewing extends JFrame implements ActionListener{
    JLabel lblPUP, logoLabel;
    JTable table;
    String[] columnHeader = {"Student ID", "Last Name", "First Name", "Middle Name", "Course", "Year", "Address", "Contact Number", "Birthday", "Position", "Affiliation" };
    JTextField txtSearch;
    JButton btnSearch, btnAdd, btnBack, btnDelete;
    
    Connection conn;
    
    Viewing(){
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_cite", "root", "");
            Statement st = conn.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        setTitle("Polytechnic University of the Philippines - Biñan");
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,218,102));
        
        ImageIcon logoImage = new ImageIcon("pup.png"); 
        Image scaledLogoImage = logoImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setBounds(25, 15, 100, 100); 
        add(logoLabel);
        
        lblPUP = new JLabel("Polytechnic University of the Philippines - Biñan");
        lblPUP.setBounds(150, 15, 1000, 100);
        lblPUP.setFont(new Font("Arial", Font.BOLD, 30));
        add(lblPUP);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(320, 100, 350, 30);
        add(txtSearch);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(700,100, 90, 30); 
        btnSearch.setFont(new Font ("Arial", Font.BOLD, 12));
        btnSearch.addActionListener(this);
        add(btnSearch);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(700,680,90,50);
        btnBack.setFont(new Font ("Arial", Font.BOLD, 16));
        btnBack.addActionListener(this);
        add(btnBack);
        
        DefaultTableModel model = new DefaultTableModel(columnHeader, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 950, 500);
        add(scrollPane);
        
        displayData(model);
        
        setVisible(true);
    }
    
    public void displayData(DefaultTableModel model) {
        String query = "SELECT * FROM tbl_cite";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String[] rowData = new String[columnHeader.length];
                for (int i = 0; i < columnHeader.length; i++) {
                    rowData[i] = rs.getString(i + 1);
                }
                model.addRow(rowData);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void searchData(String studentNumber) {
        String query = "SELECT * FROM tbl_cite WHERE Student_ID LIKE '%" + studentNumber + "%'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Student ID: ").append(rs.getString(1)).append("\n");
                sb.append("Last Name: ").append(rs.getString(2)).append("\n");
                sb.append("First Name: ").append(rs.getString(3)).append("\n");
                sb.append("Middle Name: ").append(rs.getString(4)).append("\n");
                sb.append("Course: ").append(rs.getString(5)).append("\n");
                sb.append("Year: ").append(rs.getString(6)).append("\n");
                sb.append("Address: ").append(rs.getString(7)).append("\n");
                sb.append("Contact Number: ").append(rs.getString(8)).append("\n");
                sb.append("Birthday: ").append(rs.getString(9)).append("\n");
                sb.append("Position: ").append(rs.getString(10)).append("\n");
                sb.append("Affiliation: ").append(rs.getString(11)).append("\n");
                
                JOptionPane.showMessageDialog(this, sb.toString(), "Student Information", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                JOptionPane.showMessageDialog(this, "Student not found.", "STUDENT NOT FOUND", JOptionPane.WARNING_MESSAGE);
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String searchQuery = txtSearch.getText().trim(); 
            
            if (!searchQuery.isEmpty()) {
                searchData(searchQuery);
            } 
            else {
                JOptionPane.showMessageDialog(this, "Please enter a student ID.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnBack) {
            dispose();
            Login log = new Login();
            log.setVisible(true);
            log.setResizable(false);
        }
    }
}