/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author omar
 */
public class CheckTC extends JFrame {
    JTextField test1;
    public CheckTC() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel lab1 = new JLabel("experssion is : ");
        c.gridx = 0;
        c.gridy = 0;
        add(lab1,c);
        test1 = new JTextField("");
        c.gridx =1;
        c.gridy =0;
        test1.setColumns(10);
        add(test1,c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        JButton testTC = new JButton("Test");
        event e = new event();
        testTC.addActionListener(e);
        add(testTC,c);
    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            MainFeatures.check_Tautology_Contradiction(test1.getText());
        }
    }
}
