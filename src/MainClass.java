
import java.awt.EventQueue;


public class MainClass {

   
    public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run(){
           Login log = new Login(); 
           log.setVisible(true);
           log.setResizable(false);
         
         }  
      });
    }
    
}
