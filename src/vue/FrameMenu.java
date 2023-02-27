package src.vue;

import src.*;

import javax.swing.*;

public class FrameMenu extends JFrame {
    private Controleur ctrl;
	private PanelMenu pnlMenu;

	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		//parametres de la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1800, 1000);
		this.setVisible(true);
		
		//creation composant
		this.pnlMenu = new PanelMenu(ctrl);

        //ajout des composants
        this.add(this.pnlMenu);
	}
}
