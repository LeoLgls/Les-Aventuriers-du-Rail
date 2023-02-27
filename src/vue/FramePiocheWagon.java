package src.vue;

import src.Controleur;

import javax.swing.*;

public class FramePiocheWagon extends JFrame
{
	private JPanel panelPioche;
	private Controleur ctrl;
	private boolean pioche;

	public FramePiocheWagon( Controleur ctrl, boolean pioche)
	{
		this.setSize(600, 500);
		this.ctrl = ctrl;
		this.pioche = pioche;
		if (pioche) {
			this.setTitle("Piochez une carte");
		} else {
			this.setTitle("Cartes disponibles");
		}
		this.panelPioche = new PanelPiocheWagon(ctrl, pioche);
		this.add(panelPioche);
		this.setVisible(true);
	}
}
