package src.vue;

import src.Controleur;
import src.metier.Metier;
import src.metier.CarteObjectif;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class PanelFinDePartie extends JPanel implements ActionListener
{

    private Controleur ctrl;
    private FrameFinDePartie frameFinDePartie;
    private JButton btnMenu;
    private JButton btnSuivant;
    private JPanel panelCartes;
    private ArrayList<JButton> ensButtons;
    private ArrayList<CarteObjectif> carteObjChoisies;
    private ArrayList<CarteObjectif> carteObjJoueur;

    public PanelFinDePartie(Controleur ctrl, FrameFinDePartie frameFinDePartie) 
    {
        this.ctrl = ctrl;
        this.frameFinDePartie = frameFinDePartie;

        this.carteObjChoisies = new ArrayList<CarteObjectif>();
        this.carteObjJoueur = this.ctrl.getMetier().getJoueurActuel().getCarteObjectif();
        this.ensButtons = new ArrayList<JButton>();

        this.setLayout(new BorderLayout());

        //creation différents panels
        JPanel panelInfo = new JPanel();
        JLabel lblInfo = new JLabel("Fin de la partie, " + this.ctrl.getMetier().getJoueurActuel().getNom() + " sélectionner vos cartes objectifs remplies");
        panelInfo.add(lblInfo);

        this.panelCartes = new JPanel();

        GridLayout gl = new GridLayout(this.ctrl.getMetier().getJoueurActuel().getCarteObjectif().size() / 2 + 1, 2);
        gl.setVgap(20);
        gl.setHgap(20);
        this.panelCartes.setLayout(gl);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        this.btnSuivant = new JButton("Suivant");
        panelBtn.add(this.btnSuivant);
        this.btnSuivant.addActionListener(this);


        Color couleur = new Color(186, 178, 122);

        for (CarteObjectif c : this.ctrl.getMetier().getJoueurActuel().getCarteObjectif()) {
            JButton btn = new JButton(c.toString());
            btn.setBackground(couleur);
            btn.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            this.ensButtons.add(btn);
            this.panelCartes.add(btn);
            btn.addActionListener(this);
        }


        //positionnement des différents panels
        this.add(panelInfo, BorderLayout.NORTH);
        this.add(this.panelCartes, BorderLayout.CENTER);
        this.add(panelBtn, BorderLayout.SOUTH);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == this.btnMenu)
        {
            this.frameFinDePartie.dispose();
            Metier metier = new Metier(this.ctrl);
			this.ctrl.setMetier(metier);
			new FrameInit(ctrl);
        }
        if (e.getSource() == this.btnSuivant) {
            for (CarteObjectif c : this.carteObjChoisies) {
                this.ctrl.getMetier().getJoueurActuel().ajouterPoints(c.getNbPoints());
            }
            
            for (CarteObjectif c : this.carteObjJoueur) {
                if (!this.carteObjChoisies.contains(c)) {
                    this.ctrl.getMetier().getJoueurActuel().retirerPoints(c.getNbPoints());
                }
            }

            this.ctrl.getMetier().tourSuivant();
            this.frameFinDePartie.reset();

  
        }
        else {
            if (this.carteObjChoisies.contains(this.carteObjJoueur.get(this.ensButtons.indexOf(e.getSource())))) {
                this.carteObjChoisies.remove(this.carteObjJoueur.get(this.ensButtons.indexOf(e.getSource())));
                this.ensButtons.get(this.ensButtons.indexOf(e.getSource())).setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            }
            else {
                this.carteObjChoisies.add(this.carteObjJoueur.get(this.ensButtons.indexOf(e.getSource())));
                this.ensButtons.get(this.ensButtons.indexOf(e.getSource())).setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
            }
        }
    }
}