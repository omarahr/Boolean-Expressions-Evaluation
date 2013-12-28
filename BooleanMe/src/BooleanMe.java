
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author omar
 */
public class BooleanMe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        UserInteface newui = new UserInteface();
//        newui.setVisible(true);
//        newui.setTitle("omaro v1.1");
//        newui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInteface().setVisible(true);
            }
        });
    }
}
