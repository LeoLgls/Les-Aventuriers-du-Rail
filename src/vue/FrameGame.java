package src.vue;

import javax.swing.*;
import src.metier.*;
import src.Controleur;
import java.awt.BorderLayout;


import java.io.File;

import javax.swing.JFrame;

public class FrameGame extends JFrame
{
	private Controleur ctrl;

	private PanelGame panGame;
	private PanelMenu panMenu;
	private PanelMain panMain;
	private BarreMenu barreMenu;

	public FrameGame(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Game");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.panMain = new PanelMain(this.ctrl);
		this.panGame = new PanelGame(this.ctrl);
		this.panMenu = new PanelMenu(this.ctrl);
		this.barreMenu = new BarreMenu(this.ctrl,this);

		this.setJMenuBar(barreMenu);
		
		this.add(panMain, BorderLayout.WEST);
		this.add(panGame, BorderLayout.CENTER);
		this.add(panMenu, BorderLayout.EAST);


		this.setSize(1800, 1000);
		this.setVisible(true);

	}

	public Controleur getCtrl()
	{
		return this.ctrl;
	}

	public void repaintGame() {
		this.panGame.repaint();
	}

	public PanelGame getPanGame()
	{
		return panGame;
	}

	public PanelMenu getPanMenu()
	{
		return panMenu;
	}

	public void update() 
	{
		this.panMain.update();
	}

	public void updateNomJoueurActuel()
	{
		this.panMenu.updateNomJoueurActuel();
	}

	public PanelMenu getPanelMenu()
	{
		return this.panMenu;
	}

	@Override
	public void dispose()
	{
		this.ctrl.clean();
		super.dispose();
	}
}
