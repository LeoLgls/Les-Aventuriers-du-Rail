package src.vue;

import src.Controleur;
import src.metier.CarteWagons;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;


import java.awt.*;

public class PanelMain extends JPanel
{
    private Controleur ctrl;

    private JPanel panelCarteWagon;

    private JLabel lblTitreMain;
    private JLabel lblCarteWagonRouge;
    private JLabel lblCarteWagonBlanche;
    private JLabel lblCarteWagonNoire;
    private JLabel lblCarteWagonBleue;
    private JLabel lblCarteWagonVerte;
    private JLabel lblCarteWagonJaune;
    private JLabel lblCarteWagonOrange;
    private JLabel lblCarteWagonRose;
    private JLabel lblCarteWagonJoker;
    private JLabel lblNbPions;


    public PanelMain(Controleur ctrl)
    {
        GridLayout gl = new GridLayout(5,2);
        Color couleur = new ColorUIResource(246,232,213);
        this.setBackground(couleur);
        this.setLayout(new BorderLayout());
        this.ctrl = ctrl;

        //création
        this.panelCarteWagon = new JPanel();
        this.panelCarteWagon.setLayout(gl);
        gl.setHgap(10);
        gl.setVgap(10);
        this.lblTitreMain = new JLabel("Main");
        this.lblTitreMain.setFont(new Font("", Font.BOLD, 20));
        this.lblTitreMain.setHorizontalAlignment(JLabel.CENTER);

        this.lblCarteWagonRouge   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRouge()   + "");
        this.lblCarteWagonOrange  = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonOrange()  + "");
        this.lblCarteWagonNoire   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonNoire()   + "");
        this.lblCarteWagonBleue   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBleue()   + "");
        this.lblCarteWagonVerte   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonVerte()   + "");
        this.lblCarteWagonJaune   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJaune()   + "");
        this.lblCarteWagonBlanche = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBlanche() + "");
        this.lblCarteWagonRose    = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRose()    + "");
        this.lblCarteWagonJoker   = new JLabel(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker()   + "");
        this.lblNbPions           = new JLabel("nb Pions " + this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() + "");

        this.lblCarteWagonRouge.setBackground(Color.RED);
        this.lblCarteWagonRouge.setForeground(Color.WHITE);
        this.lblCarteWagonBlanche.setBackground(Color.WHITE);
        this.lblCarteWagonNoire.setBackground(Color.BLACK);
        this.lblCarteWagonNoire.setForeground(Color.WHITE);
        this.lblCarteWagonBleue.setBackground(Color.BLUE);
        this.lblCarteWagonBleue.setForeground(Color.WHITE);
        this.lblCarteWagonVerte.setBackground(Color.GREEN);
        this.lblCarteWagonJaune.setBackground(Color.YELLOW);
        this.lblCarteWagonOrange.setBackground(Color.ORANGE);
        this.lblCarteWagonRose.setBackground(Color.PINK);
        this.lblCarteWagonJoker.setBackground(Color.GRAY);
        this.lblCarteWagonJoker.setForeground(Color.WHITE);

        this.lblCarteWagonRouge.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonRouge.setHorizontalAlignment      (JLabel.CENTER);
        this.lblCarteWagonBlanche.setHorizontalTextPosition (JLabel.CENTER);
        this.lblCarteWagonBlanche.setHorizontalAlignment    (JLabel.CENTER);
        this.lblCarteWagonNoire.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonNoire.setHorizontalAlignment      (JLabel.CENTER);
        this.lblCarteWagonBleue.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonBleue.setHorizontalAlignment      (JLabel.CENTER);
        this.lblCarteWagonVerte.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonVerte.setHorizontalAlignment      (JLabel.CENTER);
        this.lblCarteWagonJaune.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonJaune.setHorizontalAlignment      (JLabel.CENTER);
        this.lblCarteWagonOrange.setHorizontalTextPosition  (JLabel.CENTER);
        this.lblCarteWagonOrange.setHorizontalAlignment     (JLabel.CENTER);
        this.lblCarteWagonRose.setHorizontalTextPosition    (JLabel.CENTER);
        this.lblCarteWagonRose.setHorizontalAlignment       (JLabel.CENTER);
        this.lblCarteWagonJoker.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblCarteWagonJoker.setHorizontalAlignment      (JLabel.CENTER);

        this.lblCarteWagonRouge.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonBlanche.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonNoire.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonBleue.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonVerte.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonJaune.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonOrange.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonRose.setFont(new Font("", Font.BOLD, 20));
        this.lblCarteWagonJoker.setFont(new Font("", Font.BOLD, 20));

        this.lblCarteWagonRouge.setOpaque(true);
        this.lblCarteWagonBlanche.setOpaque(true);
        this.lblCarteWagonNoire.setOpaque(true);
        this.lblCarteWagonBleue.setOpaque(true);
        this.lblCarteWagonVerte.setOpaque(true);
        this.lblCarteWagonJaune.setOpaque(true);
        this.lblCarteWagonOrange.setOpaque(true);
        this.lblCarteWagonRose.setOpaque(true);
        this.lblCarteWagonJoker.setOpaque(true);


        //positionnement
        this.add(lblTitreMain);


        /*for (CarteWagons c : this.ctrl.getMetier().getJoueur(0).getCarteWagon()) {
            JLabel lblCarteWagon = new JLabel(c.getCouleur());
            this.add(lblCarteWagon);
        }*/
        this.panelCarteWagon.add(this.lblCarteWagonRouge);
        this.panelCarteWagon.add(this.lblCarteWagonOrange);
        this.panelCarteWagon.add(this.lblCarteWagonNoire);
        this.panelCarteWagon.add(this.lblCarteWagonBleue);
        this.panelCarteWagon.add(this.lblCarteWagonVerte);
        this.panelCarteWagon.add(this.lblCarteWagonJaune);
        this.panelCarteWagon.add(this.lblCarteWagonBlanche);
        this.panelCarteWagon.add(this.lblCarteWagonRose);
        this.panelCarteWagon.add(this.lblCarteWagonJoker);
        this.panelCarteWagon.add(this.lblNbPions);

        this.add(lblTitreMain, BorderLayout.NORTH);
        this.add(panelCarteWagon, BorderLayout.CENTER);
    }

    public void update()
    {
        this.lblCarteWagonRouge.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRouge()     + "");
        this.lblCarteWagonBlanche.setText(  this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBlanche()   + "");
        this.lblCarteWagonNoire.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonNoire()     + "");
        this.lblCarteWagonBleue.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBleue()     + "");
        this.lblCarteWagonVerte.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonVerte()     + "");
        this.lblCarteWagonJaune.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJaune()     + "");
        this.lblCarteWagonOrange.setText(   this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonOrange()    + "");
        this.lblCarteWagonRose.setText(     this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRose()      + "");
        this.lblCarteWagonJoker.setText(    this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker()     + "");
        this.lblNbPions.setText("nb Pions " + this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() + "");
    }
    
}