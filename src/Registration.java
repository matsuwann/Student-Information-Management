
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Registration extends JFrame implements ActionListener {

    JLabel lblPUP, lblRegistext, logoLabel;
    JLabel lblApplicantInfo, lblStudentNumber, lblLastName, lblFirstName, lblMiddleName;
    JLabel lblYrLvl, lblCourse, lblAddress, lblContact;
    JLabel lblPosition, lblAffiliation;
    JTextField txtStudentNumber, txtLastName, txtFirstName, txtMiddleName;
    JTextField txtYrLvl, txtCourse, txtAddress, txtContact;
    JTextField txtPosition, txtAffiliation;

    JButton btnConfirm, btnCancel;
    JLabel lblMonth, lblDay, lblYear;
    JComboBox<String> cbMonth, cbDay, cbYear;
    Connection conn;

    private String[] Month = {"", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December",};
    private String[] Day = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
        "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",};
    private String[] Year = {"", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995",
        "1994", "1992", "1991", "1990",};

    Registration() {

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

        lblRegistext = new JLabel("STUDENT REGISTRATION FORM");
        lblRegistext.setBounds(330, 45, 1000, 100);
        lblRegistext.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblRegistext);

        lblApplicantInfo = new JLabel("Applicant Information");
        lblApplicantInfo.setBounds(80, 150, 250, 30);
        lblApplicantInfo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblApplicantInfo);

        lblStudentNumber = new JLabel("Student Number");
        lblStudentNumber.setBounds(90, 190, 250, 30);
        lblStudentNumber.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblStudentNumber);

        txtStudentNumber = new JTextField();
        txtStudentNumber.setBounds(80, 215, 350, 30);
        add(txtStudentNumber);

        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(470, 190, 250, 30);
        lblLastName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(460, 215, 350, 30);
        add(txtLastName);

        lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(90, 250, 250, 30);
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(80, 275, 350, 30);
        add(txtFirstName);

        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setBounds(470, 250, 250, 30);
        lblMiddleName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblMiddleName);

        txtMiddleName = new JTextField();
        txtMiddleName.setBounds(460, 275, 350, 30);
        add(txtMiddleName);

        lblMonth = new JLabel("Month");
        lblMonth.setBounds(90, 310, 250, 30);
        lblMonth.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblMonth);

        cbMonth = new JComboBox(Month);
        cbMonth.setBounds(80, 335, 250, 30);
        add(cbMonth);

        lblDay = new JLabel("Day");
        lblDay.setBounds(360, 310, 250, 30);
        lblDay.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDay);

        cbDay = new JComboBox(Day);
        cbDay.setBounds(350, 335, 200, 30);
        add(cbDay);

        lblYear = new JLabel("Year");
        lblYear.setBounds(580, 310, 250, 30);
        lblYear.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblYear);

        cbYear = new JComboBox(Year);
        cbYear.setBounds(570, 335, 240, 30);
        add(cbYear);

        lblYrLvl = new JLabel("Year Level");
        lblYrLvl.setBounds(90, 370, 250, 30);
        lblYrLvl.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblYrLvl);

        txtYrLvl = new JTextField();
        txtYrLvl.setBounds(80, 395, 350, 30);
        add(txtYrLvl);

        lblCourse = new JLabel("Course");
        lblCourse.setBounds(470, 370, 250, 30);
        lblCourse.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblCourse);

        txtCourse = new JTextField();
        txtCourse.setBounds(460, 395, 350, 30);
        add(txtCourse);

        lblAddress = new JLabel("Address");
        lblAddress.setBounds(90, 435, 250, 30);
        lblAddress.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(80, 460, 350, 30);
        add(txtAddress);

        lblContact = new JLabel("Contact Number");
        lblContact.setBounds(470, 435, 250, 30);
        lblContact.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblContact);

        txtContact = new JTextField();
        txtContact.setBounds(460, 460, 350, 30);
        add(txtContact);

        lblPosition = new JLabel("Position");
        lblPosition.setBounds(90, 500, 250, 30);
        lblPosition.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPosition);

        txtPosition = new JTextField();
        txtPosition.setBounds(80, 525, 350, 30);
        add(txtPosition);

        lblAffiliation = new JLabel("Affiliation");
        lblAffiliation.setBounds(470, 500, 250, 30);
        lblAffiliation.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblAffiliation);

        txtAffiliation = new JTextField();
        txtAffiliation.setBounds(460, 525, 350, 30);
        add(txtAffiliation);

        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(450, 600, 180, 50);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 18));
        btnCancel.setBackground(new Color(179, 94, 37));
        add(btnCancel);
        btnCancel.addActionListener(this);

        btnConfirm = new JButton("CONFIRM");
        btnConfirm.setBounds(650, 600, 180, 50);
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 18));
        btnConfirm.setBackground(new Color(179, 94, 37));
        add(btnConfirm);
        btnConfirm.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {

        } 
        else if (e.getSource() == btnCancel) {
            dispose();
            Viewing view = new Viewing();
            view.setVisible(true);
            view.setResizable(false);
        }
    }

}
