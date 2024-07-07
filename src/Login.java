
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JLabel lblLogin, lblAdminID, lblPassword, lblCreate, logoLabel, lblCooldown;
    JTextField txtAdminID;
    JPasswordField txtPassword;
    JButton btnLogin, btnForgot;
    Connection conn;
    int logattempt = 0;
    long cdend = 0;
    final long cd_duration = 7000;
    Timer cooldownTimer;

    Login() {
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
        setSize(1000, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 218, 102));

        ImageIcon icon = new ImageIcon("pup.png");
        setIconImage(icon.getImage());

        lblLogin = new JLabel("ADMIN LOGIN");
        lblLogin.setBounds(300, 40, 500, 100);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 50));
        add(lblLogin);

        lblAdminID = new JLabel("Admin ID: ");
        lblAdminID.setBounds(500, 230, 500, 100);
        lblAdminID.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblAdminID);

        txtAdminID = new JTextField();
        txtAdminID.setBounds(500, 300, 250, 30);
        add(txtAdminID);

        lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(500, 310, 500, 100);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(500, 380, 250, 30);
        add(txtPassword);

        btnLogin = new JButton("SIGN IN");
        btnLogin.setBounds(500, 450, 250, 50);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(179, 94, 37));
        add(btnLogin);
        btnLogin.addActionListener(this);
        
//        btnForgot = new JButton("FORGOT PASSWORD?");
//        btnForgot.setBounds(500, 510, 250, 50);
//        btnForgot.setFont(new Font ("Arial", Font.BOLD, 18));
//        btnForgot.setBackground(new Color(179, 94, 37));
//        add(btnForgot);
//        btnForgot.addActionListener(this);

        lblCooldown = new JLabel();
        lblCooldown.setBounds(500, 510, 300, 30);
        lblCooldown.setFont(new Font("Arial", Font.BOLD, 18));
        lblCooldown.setForeground(Color.BLACK);
        lblCooldown.setVisible(false);
        add(lblCooldown);

        ImageIcon logoImage = new ImageIcon("pup.png");
        Image scaledLogoImage = logoImage.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setBounds(60, 210, 400, 400);
        add(logoLabel);
    }

    private void startCooldownTimer() {
        cooldownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                long currentTime = System.currentTimeMillis();
                if (currentTime < cdend) {
                    long remcd = (cdend - currentTime) / 1000;
                    lblCooldown.setText("Cooldown: " + remcd + " seconds remaining");
                } 
                else {
                    cooldownTimer.stop();
                    lblCooldown.setVisible(false);
                    btnLogin.setEnabled(true);
                    logattempt = 0;
                }
            }
        });
        cooldownTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String adminID = txtAdminID.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (adminID.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Admin ID and Password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT * FROM tbl_user WHERE adminID = ? AND PASSWORD = ?";

            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, adminID);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    logattempt = 0;
                    JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Loading load = new Loading();
                    load.setVisible(true);
                    load.setResizable(false);

                } 
                else {
                    logattempt++;
                    if (logattempt >= 3) {
                        cdend = System.currentTimeMillis() + cd_duration;
                        JOptionPane.showMessageDialog(this, "Too many failed attempts. Please wait for the cooldown period.", "Cooldown", JOptionPane.WARNING_MESSAGE);
                        lblCooldown.setVisible(true);
                        startCooldownTimer();
                        btnLogin.setEnabled(false);
                    } 
                    else {
                        JOptionPane.showMessageDialog(this, "Invalid Admin ID or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database query error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnForgot) {
            
        }
    }
}
