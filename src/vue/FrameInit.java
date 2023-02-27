package src.vue;

import src.Controleur;

import javax.swing.*;
import java.awt.event.*;

public class FrameInit extends JFrame
{
	private Controleur ctrl;

	private JPanel panelInit;


	public FrameInit(Controleur ctrl)
	{
		this.ctrl = ctrl;
	
		this.setSize(600, 500);

		this.panelInit = new PanelInit(ctrl,this);

		this.add(panelInit);
		

		// call onCancel() on ESCAPE
		panelInit.registerKeyboardAction(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private void onCancel()
	{
		// add your code here if necessary
		dispose();
	}

}
