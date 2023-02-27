package src.vue;

import src.Controleur;
import src.metier.Voie;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameValidePriseVoie extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private Voie voie;
    private Color gris;

    private PanelInformationValideVoie panInfo;
    private JPanel panButton;

    private JButton btnValider;
    private JButton btnAnnuler;

    public FrameValidePriseVoie(Controleur ctrl, Voie voie) 
    {
        this.ctrl = ctrl;
        this.voie = voie;
        this.gris = new ColorUIResource(192,192,192);
        this.setTitle("Valider la Prise de la voie");
        this.setSize(600, 500);

        //création
        this.panInfo = new PanelInformationValideVoie(ctrl,voie);
        this.panButton = new JPanel(new GridLayout(1, 2));
        this.btnValider = new JButton("Valider");
        this.btnAnnuler = new JButton("Annuler");

        //positionnement
        this.panButton.add(this.btnAnnuler);
        this.panButton.add(this.btnValider);
        
        this.add(this.panInfo, BorderLayout.CENTER);
        this.add(this.panButton, BorderLayout.SOUTH);

        //activation des composants
        this.btnValider.addActionListener(this);
        this.btnAnnuler.addActionListener(this);

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnAnnuler )
        {
            new FramePriseVoie(this.ctrl);
            this.dispose();
        }

        if (e.getSource() == this.btnValider) 
        {
            if (voie.getCouleur().equals(gris))
            {
                if(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.panInfo.getValueComboBox()) < this.panInfo.getValeurCarteCouleur())
                {
                    JOptionPane.showMessageDialog(this, "Vous sélectionnez plus de cartes couleurs que vous n'en possédez", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else
                if (this.panInfo.getValeurCarteCouleur() + this.panInfo.getValeurJoker() == voie.getWagons())
                {
                    if ((this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= voie.getNbWagons()))
                    {
                        voie.setProprietaire(this.ctrl.getMetier().getJoueurActuel());
                        this.ctrl.getMetier().getJoueurActuel().retirerCarteWagonCol(this.panInfo.getValueComboBox(), this.panInfo.getValeurCarteCouleur());
                        this.ctrl.getMetier().getJoueurActuel().retirerCarteWagonCol(Color.GRAY, this.panInfo.getValeurJoker());
                        this.ctrl.getMetier().getJoueurActuel().retirerPions(this.panInfo.getValeurCarteCouleur());
                        this.ctrl.getMetier().getJoueurActuel().retirerPions(this.panInfo.getValeurJoker());
                        this.ctrl.getMetier().getJoueurActuel().ajouterPoints(this.ctrl.getMetier().getRegles().getTabScore()[this.voie.getNbWagons()-1]);
                        this.dispose();
                        this.ctrl.getIhmGame().update();
                        this.ctrl.getMetier().finDeTour();
                    } 
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Vous n'avez pas assez de pions", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "La somme des cartes et des jokers ne correspond pas au nombre de wagons de la voie", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            else if (this.panInfo.getValeurCarteCouleur() + this.panInfo.getValeurJoker() == this.voie.getNbWagons() && (this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= this.voie.getNbWagons())) {
                this.voie.setProprietaire(this.ctrl.getMetier().getJoueurActuel());
                this.ctrl.getMetier().getJoueurActuel().retirerCarteWagonCol(this.voie.getCouleur(), this.panInfo.getValeurCarteCouleur());
                this.ctrl.getMetier().getJoueurActuel().retirerCarteWagonCol(Color.GRAY, this.panInfo.getValeurJoker());
                this.ctrl.getMetier().getJoueurActuel().retirerPions(this.panInfo.getValeurCarteCouleur());
                this.ctrl.getMetier().getJoueurActuel().retirerPions(this.panInfo.getValeurJoker());
                this.ctrl.getMetier().getJoueurActuel().ajouterPoints(this.ctrl.getMetier().getRegles().getTabScore()[this.voie.getNbWagons()-1]);
                this.dispose();
                this.ctrl.getIhmGame().update();
                this.ctrl.getMetier().finDeTour();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "La somme des cartes et des jokers ne correspond pas au nombre de wagons de la voie", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        this.ctrl.getIhmGame().update();

    }

    
    
}