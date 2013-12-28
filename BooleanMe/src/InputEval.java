/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author omar
 */
public class InputEval extends JPanel {
    
    ArrayList<JTextField> inputdata;
    ArrayList<JLabel> labels;
    GridBagConstraints c = new GridBagConstraints();
    public InputEval(int n)
    {
        setLayout(new GridBagLayout());
	c.fill = GridBagConstraints.HORIZONTAL;
        inputdata = new ArrayList<JTextField>();
        labels = new ArrayList<JLabel>();
        for (int i = 0; i < n; i++) {
            JTextField newtext = new JTextField();
            String name = "expression " + (i+1) +" :";
            JLabel newlabel = new JLabel(name);
            inputdata.add(newtext);
            labels.add(newlabel);
        }
        for (int i = 0; i < n; i++) {
            c.gridx = 0;
            c.gridy = i;
            add(labels.get(i),c);
            c.gridx = 1;
            c.gridy = i;
            inputdata.get(i).setColumns(10);
            add(inputdata.get(i),c);
        }
        JButton evaluate = new JButton("evaluate");
        c.gridx = 0;
        c.gridy = n;
        c.gridwidth = 11;
        add(evaluate,c);
        event e = new event();
        evaluate.addActionListener(e);        
    }
    
    public class event implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            ArrayList<String> input = new ArrayList<String>();
            for (int i = 0; i < inputdata.size(); i++) {
                input.add(inputdata.get(i).getText());
            }
            try {
                MainFeatures.oneExpressionAndFileSaveing(input);
                
                
            } catch (IOException ex) {
                Logger.getLogger(InputEval.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
