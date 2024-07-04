import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class Loading extends JFrame {
    JLabel lblPUP, logoLabel;
    
    JLabel lblLoad;
    JProgressBar lbLoad;
    
    Loading(){
        setTitle("Polytechnic University of the Philippines - Biñan");
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,218,102));
        
        ImageIcon icon = new ImageIcon("pup.png");
        setIconImage(icon.getImage());
        
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
        
        //Loading Bar
        lblLoad = new JLabel("LOADING PLEASE WAIT");
        lblLoad.setBounds(300,300,500,40);
        lblLoad.setFont((new Font("Arial", Font.BOLD, 30)));
        add(lblLoad);
        
        lbLoad = new JProgressBar();
        lbLoad.setValue(0);
        lbLoad.setStringPainted(false);
        lbLoad.setBounds(230,350,500,30);
        lbLoad.setBackground(new Color(245,237,207));
        lbLoad.setForeground((Color.WHITE));
        lbLoad.setFont(new Font("Arial",Font.PLAIN,0));
        add(lbLoad);
        
        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws Exception {
                fill();
                return null;
            }

            @Override
            public void done() {
                dispose();
                Viewing view = new Viewing();
           
                view.setVisible(true);
            }
        }.execute();
    }
    private void fill()
    {
        int i = 0;
        try
        {
            while (i<100)
            {
                lbLoad.setValue(i + 6);
                Thread.sleep(50);
                i += 10;
            }
        }
        catch(Exception e)
        {
        }
        
    }
}