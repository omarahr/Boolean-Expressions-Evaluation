import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;



public class UI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton evaluation,TC,equ;
	public UI()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 3;
		c.gridwidth = 3;
		evaluation = new JButton("evaluation");
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		add(evaluation,c);
		TC = new JButton("check T/C");
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 3;

		add(TC,c);
		equ = new JButton("eualty");
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 3;
		add(equ,c);
		
	}
	public static void main(String[] args) {
		UI newFrame = new UI();
		newFrame.setVisible(true);
		newFrame.setTitle("Boolean Me");
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.setSize(300,400);
	}

}
