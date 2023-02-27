package src.vue;

import src.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class FrameDemo extends JFrame implements ActionListener, ItemListener
{
	private JPanel panel;
	private JButton btnAv;
	private JButton btnAr;
	private JLabel lblTexte;
	private JComboBox<String> cBBSituation;
	private Controleur ctrl;

	public FrameDemo(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize(600, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("FrameDemo");
		this.cBBSituation = new JComboBox<String>();

		this.panel = new JPanel(new BorderLayout());
		this.btnAv = new JButton("==>");
		this.btnAr = new JButton("<==");
		this.lblTexte = new JLabel("");
		this.lblTexte.setHorizontalAlignment(JLabel.CENTER);

		//cBBSituation.addItem("Situation initiale");
		//cBBSituation.addItem("Situation jocker");
		//cBBSituation.addItemListener(null);

		this.panel.add(this.lblTexte, BorderLayout.CENTER);
		//this.panel.add(this.cBBSituation, BorderLayout.NORTH);
		this.panel.add(this.btnAv, BorderLayout.EAST);
		this.panel.add(this.btnAr, BorderLayout.WEST);

		this.add(panel);

		this.btnAv.addActionListener(this);
		this.btnAr.addActionListener(this);

		this.setVisible(true);
	}

	public void setTexte(String texte)
	{
		this.lblTexte.setText(texte);
	}


	@Override
	public void dispose()
	{
		this.ctrl.getIhmGame().dispose();
		this.ctrl = null;
		super.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAv)
		{
			ctrl.getMetier().incrementerDemo();
			ctrl.getMetier().majDemo();
		}
		else if (e.getSource() == this.btnAr)
		{
			ctrl.getMetier().decrementerDemo();
			ctrl.getMetier().majDemo();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{ 
		if (e.getSource() == this.cBBSituation)
		{
			ctrl.getMetier().setDemo(cBBSituation.getSelectedIndex());
			ctrl.getMetier().majDemo();
		}
	 }
}
