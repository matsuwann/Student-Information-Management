import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{
    JLabel lblLogin, lblStudentID, lblPassword, lblCreate, logoLabel;

    JTextField txtStudentID;
    JPasswordField txtPassword;
    JButton btnLogin, btnForgot;
    
    
    Login(){
        setTitle("Polytechnic University of the Philippines - Biñan");
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,218,102));
        
        ImageIcon icon = new ImageIcon("pup.png");
        setIconImage(icon.getImage());

        
        lblLogin = new JLabel("ADMIN LOGIN");
        lblLogin.setBounds(300, 40, 500, 100);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 50));
        add(lblLogin);
       
        lblStudentID = new JLabel("Admin ID: ");
        lblStudentID.setBounds(500, 230, 500, 100);
        lblStudentID.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblStudentID);
        
        txtStudentID = new JTextField();
        txtStudentID.setBounds(500, 300, 250, 30);
        add(txtStudentID);
       
        lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(500, 310, 500, 100);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(500, 380, 250, 30);
        add(txtPassword);
        
//        lblCreate = new JLabel("CREATE ACCOUNT");
//        lblCreate.setBounds(500, 550, 200, 100);
//        lblCreate.setFont(new Font("Arial", Font.BOLD, 12));
//        add(lblCreate);
       
        btnLogin = new JButton("SIGN IN");
        btnLogin.setBounds(500, 450, 250, 50);
        btnLogin.setFont(new Font ("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(179, 94, 37));
        add(btnLogin);
        btnLogin.addActionListener(this);
        
        btnForgot = new JButton("FORGOT PASSWORD?");
        btnForgot.setBounds(500, 510, 250, 50);
        btnForgot.setFont(new Font ("Arial", Font.BOLD, 18));
        btnForgot.setBackground(new Color(179, 94, 37));
        add(btnForgot);
        btnForgot.addActionListener(this);
        
        ImageIcon logoImage = new ImageIcon("pup.png"); // Provide the correct path to your logo image file
        Image scaledLogoImage = logoImage.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Resize image to 120x90
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setBounds(60, 210, 400, 400); // Set bounds to match resized image
        add(logoLabel);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         dispose();
           Loading load = new Loading();
           load.setVisible(true);
           load.setResizable(true);
    }
    
}
