package src.vue;

import src.Controleur;

import src.metier.Joueur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;


public class FrameFinDePartie extends JFrame
{
    private Controleur ctrl;
    private PanelFinDePartie panelFinDePartie;
    private Joueur joueurDepart;
    private int cptjoueurs;

    public FrameFinDePartie(Controleur ctrl, Joueur joueurDepart)
    {
        this.ctrl = ctrl;
        this.joueurDepart = joueurDepart;
        this.cptjoueurs = 1;
        this.setTitle("Fin de partie");
        this.setSize(600, 100 * this.ctrl.getMetier().getNbJoueur());
        this.setVisible(true);

        this.panelFinDePartie = new PanelFinDePartie(ctrl,this);

        this.add(panelFinDePartie);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(500, 500);
        this.pack();
        this.setVisible(true);
    }

    public void reset() {
        if (cptjoueurs < this.ctrl.getMetier().getNbJoueur()) {
            cptjoueurs++;
            this.panelFinDePartie.removeAll(); 
            this.panelFinDePartie.add(new PanelFinDePartie(ctrl, this));   
            System.out.println(this.ctrl.getMetier().getNbJoueur());
            revalidate();
        }
        else {

            JPanel panelScore = new JPanel();

            panelScore.setLayout(new FlowLayout());

            for (Joueur j : this.ctrl.getMetier().getJoueurs())
            {
                JLabel lblScore = new JLabel("Score final de " + j.getNom() + " : " + j.getPoints());
                panelScore.add(lblScore);
                System.out.println(j.getPoints());
            }

            this.panelFinDePartie.removeAll();
            this.panelFinDePartie.add(panelScore);
            revalidate();
        }
    }
    
    

    
}

