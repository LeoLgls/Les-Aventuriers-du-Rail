package src.vue;

import src.Controleur;
import src.metier.Voie;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInformationValideVoie extends JPanel
{
    private Controleur ctrl;
    private Voie voie;
    private Color gris;

    private JPanel panelBtn;

    private JLabel lblJoker;
    private JLabel lblColor;

    private JLabel lblVoie;
    private JLabel lblNbTroncons;
    private JLabel lblCouleurVoie;

    private JLabel lblInfoJoueur;
    private JLabel lblNbPions;
    private JLabel lblNbPointsRapportes;

    private JSpinner spinCarteJoker;
    private JSpinner spinCarteColor;

    private JComboBox<String> cBbChoixCouleur;


    public PanelInformationValideVoie(Controleur ctrl, Voie voie)
    {
        this.ctrl = ctrl;
        this.voie = voie;
        this.gris = new ColorUIResource(192,192,192);
        this.panelBtn = new JPanel();
        this.setLayout(new GridLayout(4, 2));

        //création
       


        this.cBbChoixCouleur = new JComboBox<String>();

        this.lblJoker = new JLabel("Cartes Joker");
        if(voie.getCouleur().equals(Color.RED))
        {
            this.lblColor = new JLabel("Cartes Couleurs Rouge");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Rouge");
        }

        if(voie.getCouleur().equals(Color.BLUE))
        {
            this.lblColor = new JLabel("Cartes Couleurs Bleu");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Bleu");
        }

        if(voie.getCouleur().equals(Color.GREEN))
        {
            this.lblColor = new JLabel("Cartes Couleurs Vert");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Vert");
        }
            
        if(voie.getCouleur().equals(Color.YELLOW))
        {
            this.lblColor = new JLabel("Cartes Couleurs Jaune");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Jaune");
        }

        if(voie.getCouleur().equals(Color.BLACK))
        {
            this.lblColor = new JLabel("Cartes Couleurs Noir");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Noir");
        }

        if(voie.getCouleur().equals(Color.WHITE))
        {
            this.lblColor = new JLabel("Cartes Couleurs Blanc");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Blanc");
        }

        if(voie.getCouleur().equals(Color.ORANGE))
        {
            this.lblColor = new JLabel("Cartes Couleurs Orange");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Orange");
        }

        if(voie.getCouleur().equals(Color.PINK))
        {
            this.lblColor = new JLabel("Cartes Couleurs Rose");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Rose");
        }


        if(voie.getCouleur().equals(gris))
        {
            this.setLayout(new GridLayout(5,2));
            JLabel lblChoixCouleur = new JLabel("Choix de la couleur : ");
            

            this.cBbChoixCouleur.addItem("Rouge");
            this.cBbChoixCouleur.addItem("Bleu");
            this.cBbChoixCouleur.addItem("Vert");
            this.cBbChoixCouleur.addItem("Jaune");
            this.cBbChoixCouleur.addItem("Noir");
            this.cBbChoixCouleur.addItem("Blanc");
            this.cBbChoixCouleur.addItem("Orange");
            this.cBbChoixCouleur.addItem("Rose");


            this.lblColor = new JLabel("Cartes Couleurs");
            this.lblCouleurVoie = new JLabel("Couleur de la voie : Gris");

            this.add(lblChoixCouleur);
            this.add(this.cBbChoixCouleur);

            this.spinCarteColor = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));

        }
        else
        {
            this.spinCarteColor = new JSpinner(new SpinnerNumberModel(0, 0, this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.voie.getCouleur()), 1));
        }

        this.spinCarteJoker = new JSpinner(new SpinnerNumberModel(0, 0, this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker(), 1));

        this.lblVoie = new JLabel("Voie : " + voie.getNoeud1().getNom() + " - " + voie.getNoeud2().getNom());
        this.lblNbTroncons = new JLabel("Nombre de Tronçons : " + this.voie.getNbWagons());
        
        this.lblNbPointsRapportes = new JLabel("+" + this.ctrl.getMetier().getRegles().getTabScore()[this.voie.getNbWagons()-1] + " point(s)");


        //positionnement        
        this.add(this.lblJoker);
        this.add(this.lblColor);

        this.add(this.spinCarteJoker);
        this.add(this.spinCarteColor);

        this.add(this.lblVoie);
        this.add(this.lblNbTroncons);

        this.add(this.lblCouleurVoie);
        this.add(this.lblNbPointsRapportes);

        
        //activation des composants

    }

    public int getValeurJoker() {
        return (int) this.spinCarteJoker.getValue();
    }

    public int getValeurCarteCouleur() {
        return (int) this.spinCarteColor.getValue();
    }

    public Color getValueComboBox()
    {
        switch ( (String)this.cBbChoixCouleur.getSelectedItem() ) {
            case "Rouge":
                return Color.RED;
            case "Bleu":
                return Color.BLUE;
            case "Vert":
                return Color.GREEN;
            case "Jaune":
                return Color.YELLOW;
            case "Noir":
                return Color.BLACK;
            case "Blanc":
                return Color.WHITE;
            case "Orange":
                return Color.ORANGE;
            case "Rose":
                return Color.PINK;
                
            default: return null;
                
        }
        
    }
    
}