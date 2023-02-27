package src;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import src.vue.*;
import src.metier.*;
import src.vue.*;

public class Controleur 
{
    private FrameInit ihm;
	private FrameGame ihmGame;
    private Metier metier;

    public Controleur() {
        this.ihm = new FrameInit(this);
        this.metier = new Metier(this);
        this.ihmGame = null;
    }

    public FrameInit getIhm() {
        return this.ihm;
    }

    public FrameGame getIhmGame() {
		return ihmGame;
	}

    public ArrayList<Voie> getVoies()
	{
		return metier.getVoies();
	}

    public ArrayList<Voie[]> getVoiesDoubles()
	{
		return metier.getVoiesDoubles();
	}

    public ArrayList<Noeud> getNoeuds()
	{
		return metier.getNoeuds();
	}

    public Metier getMetier()
    {
        return metier;
    }

    public void setMetier(Metier metier)
    {
        this.metier = metier;
    }

	public void setIhmGame(FrameGame ihmGame) {
		this.ihmGame = ihmGame;
	}

	public void clean() {
		this.metier.clean();
	}

    public ImageIcon getCalque() {
        return this.metier.getCalque();
    }

    public void repaintGame() {
        this.ihmGame.repaint();
    }

    public void closeMenu() {
        this.ihm.dispose();
    }

    public static void main(String[] args)
    {
        new Controleur();
    }
}
