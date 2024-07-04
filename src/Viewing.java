import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Viewing extends JFrame implements ActionListener{
    JLabel lblPUP, logoLabel;
    JLabel lblStudentNumber, lblLastName, lblFirstName, lblMiddleName;
    JLabel lblYrLvl, lblCourse, lblAddress, lblContact;
    
    JTextField txtSearch;
    
    Viewing(){
        setTitle("Polytechnic University of the Philippines - Biñan");
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,218,102));
        
        ImageIcon logoImage = new ImageIcon("pup.png"); // Provide the correct path to your logo image file
        Image scaledLogoImage = logoImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image to 120x90
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setBounds(25, 15, 100, 100); // Set bounds to match resized image
        add(logoLabel);
        
        lblPUP = new JLabel("Polytechnic University of the Philippines - Biñan");
        lblPUP.setBounds(150, 15, 1000, 100);
        lblPUP.setFont(new Font("Arial", Font.BOLD, 30));
        add(lblPUP);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(320, 100, 350, 30);
        add(txtSearch);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
