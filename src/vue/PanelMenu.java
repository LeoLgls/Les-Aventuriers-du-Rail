package src.vue;

import src.Controleur;
import src.metier.*;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class PanelMenu extends JPanel implements ActionListener {
    private Controleur ctrl;
    private JLabel lblInfoJoueur;
    private JLabel lblAffMain;
    private JLabel lblAffCarteObjectif;
    private JLabel lblAffPioche;
    private JLabel lblAffScore;
    private JLabel lblTourDeJeu;
    private JLabel lblPiocherWagons;
    private JLabel lblPiocherCarteObjectif;
    private JLabel lblPrendreVoie;
    private JLabel lblNomJoueurActuel;

    private JButton btnAffCarteObjectif;
    private JButton btnAffPioche;
    private JButton btnAffScore;    

    private JButton btnPiocherWagons;
    private JButton btnPiocherCarteObjectif;
    private JButton btnPrendreVoie;

    private boolean isOpen = false;

    public PanelMenu(Controleur ctrl)
    {
        GridLayout gl = new GridLayout(9,1);
        gl.setVgap(20);
        this.ctrl = ctrl;
        Color couleur = new ColorUIResource(246,232,213);
        this.setBackground(couleur);


        this.setLayout(gl);

        //creation

        this.lblNomJoueurActuel = new JLabel("Tour de " + this.ctrl.getMetier().getJoueurActuel().getNom());

        this.lblInfoJoueur           = new JLabel("Informations Joueur");
        this.lblInfoJoueur.setFont(new Font("", Font.BOLD, 20));
        this.lblInfoJoueur.setHorizontalAlignment(JLabel.CENTER);

//        this.lblAffMain              = new JLabel("Afficher main du joueur");
//        this.lblAffCarteObjectif     = new JLabel("Afficher carte objectif du joueur");
//        this.lblAffPioche            = new JLabel("Afficher pioche");
//        this.lblAffScore             = new JLabel("Afficher score");

        this.lblTourDeJeu            = new JLabel("Tour de jeu");
        this.lblTourDeJeu.setFont(new Font("", Font.BOLD, 20));
        this.lblTourDeJeu.setHorizontalAlignment(JLabel.CENTER);

//        this.lblPiocherWagons        = new JLabel("Piocher des cartes de couleurs");
//        this.lblPiocherCarteObjectif = new JLabel("Piocher une carte objectif");
//        this.lblPrendreVoie          = new JLabel("Prendre une voie");

        this.btnAffCarteObjectif = new JButton("Afficher carte objectif du joueur");
        this.btnAffPioche = new JButton("Afficher pioche");
        this.btnAffScore = new JButton("Afficher score");

        this.btnPiocherWagons = new JButton("Piocher des wagons");
        this.btnPiocherCarteObjectif = new JButton("Piocher une carte objectif");
        this.btnPrendreVoie = new JButton("Prendre une voie");



        //positionnement
        this.add(this.lblInfoJoueur);

        this.add(this.lblNomJoueurActuel);
        this.add(this.btnAffCarteObjectif);
        this.add(this.btnAffPioche);
        this.add(this.btnAffScore);
        this.add(this.lblTourDeJeu);
        this.add(this.btnPiocherWagons);
        this.add(this.btnPiocherCarteObjectif);
        this.add(this.btnPrendreVoie);

        this.lblNomJoueurActuel.setHorizontalTextPosition   (JLabel.CENTER);
        this.lblNomJoueurActuel.setHorizontalAlignment      (JLabel.CENTER);

        //actionnement
        this.btnAffCarteObjectif.addActionListener(this);
        this.btnAffPioche.addActionListener(this);
        this.btnAffScore.addActionListener(this);
        this.btnPiocherWagons.addActionListener(this);
        this.btnPiocherCarteObjectif.addActionListener(this);
        this.btnPrendreVoie.addActionListener(this);
        

    }

    public void updateNomJoueurActuel()
    {
        this.lblNomJoueurActuel.setText("Tour de " + this.ctrl.getMetier().getJoueurActuel().getNom());
    }

    public void changeStateIsOpen() {
        if (this.isOpen) {this.isOpen = false;}
        else {this.isOpen = true;}
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //affichage des cartes objectifs du joueur
        if (e.getSource() == this.btnAffCarteObjectif) 
        {
            afficherCarteObjectif();
        }
        if (e.getSource() == this.btnAffScore)
        {
            if (!this.isOpen) {
                this.isOpen = true;
                JFrame frame = new JFrame("Score") {
                    @Override
                    public void dispose() {
                        changeStateIsOpen();
                        super.dispose();
                    }
                };

                //creation nouveau panel
                class PanelScore extends JPanel implements ActionListener {
                    private Controleur ctrl;
                    private JButton btnFermer;
                    private JFrame frame;

                    public PanelScore(Controleur ctrl, JFrame frame) {
                        this.ctrl = ctrl;
                        this.frame = frame;
                        this.btnFermer = new JButton("Fermer");
                        this.setLayout(new BorderLayout());

                        JPanel panelScore = new JPanel(new GridLayout(this.ctrl.getMetier().getNbJoueur(), 2));


                        for (Joueur j : this.ctrl.getMetier().getJoueurs())
                        {
                            JLabel lblScore = new JLabel("Score de " + j.getNom() + " : " + j.getPoints());
                            panelScore.add(lblScore);
                        }

                        this.add(panelScore, BorderLayout.CENTER);
                        this.add(btnFermer, BorderLayout.SOUTH);

                        this.btnFermer.addActionListener(this);
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == this.btnFermer)
                        {
                            this.frame.dispose();
                        }
                    }
                }

                PanelScore pnlScore = new PanelScore(ctrl, frame);

                frame.add(pnlScore);
                //frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(300, 75 + 25*this.ctrl.getMetier().getNbJoueur());
                frame.setLocation(450,450);
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
                frame.setResizable(false);
            }
        }


        if (e.getSource() == this.btnAffPioche ) 
        {
            if (!this.isOpen) {
                this.isOpen = true;
                JFrame frame = new JFrame("Cartes Wagons") {
                    @Override
                    public void dispose() {
                        changeStateIsOpen();
                        super.dispose();
                    }
                };

                class PanelPioche extends JPanel implements ActionListener{
                    private Controleur ctrl;
                    private JButton btnFermer;
                    private JFrame frame;
                    private double imgScale;
                    private int imgWidth;
                    private int imgHeight;
                    private int maxWidth = 200;
                    private int maxHeight = 150;

                    public PanelPioche(Controleur ctrl, JFrame frame) {
                        this.ctrl = ctrl;
                        this.frame = frame;
                        ImageIcon imgIcon = null;

                        this.btnFermer = new JButton("Fermer");
                        this.setLayout(new BorderLayout());

                        JPanel panelCarte = new JPanel();
                        panelCarte.setLayout(null);;

                        JLabel lblImage = new JLabel(imgIcon);

                        panelCarte.add(lblImage);


                        this.add(panelCarte, BorderLayout.CENTER);
                        this.add(btnFermer, BorderLayout.SOUTH);

                        this.btnFermer.addActionListener(this);
                    }

                    public void paint(Graphics g) {
                        super.paint(g);
                        Graphics2D g2D = (Graphics2D) g;


                        ImageIcon imgIcon = new ImageIcon(this.ctrl.getMetier().getPioche().getImageVersoCarteWagon().getAbsolutePath());

                        imgScale = (double) imgIcon.getIconWidth() / (double) imgIcon.getIconHeight();
                        imgWidth = imgIcon.getIconWidth();
                        imgHeight = imgIcon.getIconHeight();

                        if (imgWidth > this.getMaxWidth())
                        {
                            imgWidth = this.getMaxWidth();
                            imgHeight = (int) (imgWidth / imgScale);
                        }
                        if (imgHeight > this.getMaxHeight())
                        {
                            imgHeight = this.getMaxHeight();
                            imgWidth = (int) (imgHeight * imgScale);
                        }

                        int x = 20;
                        for (CarteWagons c : this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible()) {
                            g.setColor(c.getCouleur());
                            g2D.draw3DRect(x, 18, 125, 175, getFocusTraversalKeysEnabled());
                            g2D.fillRect(x, 18, 125, 175);
                            x = x + 162;
                        }



                        g.setColor(Color.BLACK);
                        g2D.drawImage(imgIcon.getImage(), x, 18, 125, 175, null);

                        g2D.draw3DRect(x, 18, 125, 175, getFocusTraversalKeysEnabled());

                    }

                    public int getMaxWidth()
                    {
                        return maxWidth;
                    }

                    public int getMaxHeight()
                    {
                        return maxHeight;
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == this.btnFermer) {
                            this.frame.dispose();
                        }

                    }
                }

                PanelPioche panelPioche = new PanelPioche(ctrl, frame);

                frame.add(panelPioche);
                //frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1000, 300);
                frame.setLocation(450, 450);
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
                frame.setResizable(false);
            }
        }

        if (e.getSource() == this.btnPiocherWagons) 
        {
            if (!this.isOpen) {
                this.isOpen = true;
                JFrame frame = new JFrame("Piocher") {
                    @Override
                    public void dispose() {
                        changeStateIsOpen();
                        super.dispose();
                    }
                };

                class PanelPiocher extends JPanel implements ActionListener{
                    private Controleur ctrl;
                    private JButton btnFermer;
                    private JFrame frame;
                    private ArrayList<JButton> ensBtnCartes;
                    private PanelMenu pnlMere;
                    private int piocheCarte;
                    private JButton btnPioche;

                    public PanelPiocher(Controleur ctrl, JFrame frame, PanelMenu pnlMere) {
                        this.ctrl = ctrl;
                        this.frame = frame;
                        this.pnlMere = pnlMere;
                        this.piocheCarte = 0;
                        this.ensBtnCartes = new ArrayList<JButton>();

                        this.btnFermer = new JButton("Fermer");
                        this.setLayout(new BorderLayout());

                        this.add(btnFermer, BorderLayout.SOUTH);

                        this.btnFermer.addActionListener(this);

                        JPanel panelCarte = new JPanel();
                        FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 37, 5);
                        ImageIcon imgIcon = null;
                        panelCarte.setLayout(flow);

                        imgIcon = new ImageIcon(this.ctrl.getMetier().getPioche().getImageVersoCarteWagon().getAbsolutePath());
                        ImageIcon copie = new ImageIcon(imgIcon.getImage().getScaledInstance(125, 175,Image.SCALE_DEFAULT )); 

                        //ajout des cartes wagons visibles
                        for (CarteWagons c : this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible()) {
                            JButton btnCarte = new JButton();
                            btnCarte.setPreferredSize(new Dimension(125, 175));
                            btnCarte.setBackground(c.getCouleur());
                            
                            this.ensBtnCartes.add(btnCarte);
                            btnCarte.addActionListener(this);
                            panelCarte.add(btnCarte);
                        }

                        this.btnPioche = new JButton(copie);
                        this.btnPioche.setPreferredSize(new Dimension(125, 175));

                        this.btnPioche.addActionListener(this);

                        panelCarte.add(btnPioche);


                        this.add(panelCarte, BorderLayout.CENTER);
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == this.btnFermer) {
                            if (this.piocheCarte == 0) {
                                this.frame.dispose();
                            }
                        }

                        if (e.getSource() == this.btnPioche) {
                            this.ctrl.getMetier().getJoueurActuel().ajouterCarteWagon(this.ctrl.getMetier().getPioche().tirerCarteWagonInvisible());
                            this.piocheCarte++;
                        }
                        else{
                            for (JButton btn : ensBtnCartes) {
                                if (e.getSource()== btn) {
                                    if (this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(ensBtnCartes.indexOf(btn)).getCouleur() == Color.GRAY) {
                                        if (this.piocheCarte == 0) {
                                            this.ctrl.getMetier().getJoueurActuel().ajouterCarteWagon(this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(ensBtnCartes.indexOf(btn)));
                                            this.ctrl.getMetier().getPioche().retirerCarteWagonVisible(this.ensBtnCartes.indexOf(btn));
                                            btn.setBackground(this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(this.ensBtnCartes.indexOf(btn)).getCouleur());
                                            this.piocheCarte = this.piocheCarte + 2;
                                        }

                                    }
                                    else {
                                        this.piocheCarte++;
                                    
                                        this.ctrl.getMetier().getJoueurActuel().ajouterCarteWagon(this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(ensBtnCartes.indexOf(btn)));
                                        this.ctrl.getMetier().getPioche().retirerCarteWagonVisible(this.ensBtnCartes.indexOf(btn));
                                        btn.setBackground(this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(this.ensBtnCartes.indexOf(btn)).getCouleur());
                                        verificationCartesJoker();
                                    }
                                }
                            }

                        }

                        this.ctrl.getIhmGame().update();
                        if (this.piocheCarte == 2) {
                            System.out.println(this.ctrl.getMetier().getJoueurActuel().getNbCartesWagon());
                            this.frame.dispose();
                            this.ctrl.getMetier().finDeTour();
                            
                        }


                        //System.out.println(this.ctrl.getMetier().);
                    }

                    public void verificationCartesJoker() {
                        int nbJoker = 0;
                        for (CarteWagons c : this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible()) {
                            if (c.getCouleur() == Color.GRAY) {
                                nbJoker++;
                            }
                        }

                        if (nbJoker >= 3) {
                            JOptionPane.showMessageDialog(this, "3 cartes joker sont visibles, un mélange va être éffectué", "Information", JOptionPane.INFORMATION_MESSAGE);
                            this.ctrl.getMetier().getPioche().reset();
                            for (JButton btn : ensBtnCartes) {
                                btn.setBackground(this.ctrl.getMetier().getPioche().getEnsCarteWagonVisible().get(this.ensBtnCartes.indexOf(btn)).getCouleur());
                            }
                        }
                    }
                }

                PanelPiocher panelPiocher = new PanelPiocher(ctrl, frame, this);

                frame.add(panelPiocher);
                //frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1000, 300);
                frame.setLocation(450, 450);
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
                frame.setResizable(false);
            }
        }

        if (e.getSource() == this.btnPiocherCarteObjectif) {
            this.PiocheCarteObj(1);
        }
            //new FramePiocheWagon(this.ctrl, true);
        if (e.getSource() == this.btnPrendreVoie)
        {
            if (!isOpen) {
                new FramePriseVoie(this.ctrl);
            }
        }

    }

    public void afficherCarteObjectif()
    {
        if (!this.isOpen) 
        {
            this.isOpen = true;
            JFrame frame = new JFrame("Cartes Objectifs") 
            {
                @Override
                public void dispose() {
                    changeStateIsOpen();
                    super.dispose();
                }
            };

            //creation nouveau panel
            class PanelCarteObj extends JPanel implements ActionListener {
                private Controleur ctrl;
                private JButton btnFermer;
                private JFrame frame;
                private PanelMenu pnlMere;
                private JPanel panelCarte;

                public PanelCarteObj(Controleur ctrl, JFrame frame, PanelMenu pnlMere) {
                    this.ctrl = ctrl;
                    this.frame = frame;
                    this.pnlMere = pnlMere;
                    int nbCartes = this.ctrl.getMetier().getJoueurActuel().getCarteObjectif().size();
                    if(nbCartes % 2 != 0)
                    {
                        nbCartes = (nbCartes + 1) / 2;
                    }
                    else
                    {
                        nbCartes = nbCartes / 2;
                    }
                    GridLayout gl = new GridLayout(nbCartes,2);
                    Color couleur = new ColorUIResource(186, 178, 122);
                    this.setLayout(new BorderLayout());

                    this.panelCarte = new JPanel();
                    this.panelCarte.setLayout(gl);
                    gl.setVgap(10);
                    gl.setHgap(10);
                    this.btnFermer = new JButton("Fermer");

                    for (CarteObjectif c : this.ctrl.getMetier().getJoueurActuel().getCarteObjectif()) {

                        JLabel lblCarte = new JLabel(c.getNoeud1().getNom() + " ---> " + c.getNoeud2().getNom() + " | " + c.getNbPoints());
                        lblCarte.setOpaque(true);
                        lblCarte.setBackground(couleur);
                        lblCarte.setHorizontalTextPosition(JLabel.CENTER);
                        lblCarte.setHorizontalAlignment(JLabel.CENTER);
                        lblCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        this.panelCarte.add(lblCarte);
                    }

                    this.add(panelCarte, BorderLayout.CENTER);
                    this.add(btnFermer, BorderLayout.SOUTH);

                    this.btnFermer.addActionListener(this);
                }

                public JFrame getFrame() {
                    return frame;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == this.btnFermer) {
                        this.frame.dispose();
                    }

                }
            }

            PanelCarteObj pnlCrtObj = new PanelCarteObj(ctrl, frame, this);

            frame.add(pnlCrtObj);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.setSize(400, 100);
            frame.setLocation(500,300);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            frame.setResizable(false);
        }
    }

    public void PiocheCarteObj(int nbCarteMini) {
        if (!this.isOpen) {
            this.isOpen = true;
            JFrame frame = new JFrame("Choississez vos cartes") {
                @Override
                public void dispose() {
                    changeStateIsOpen();
                    super.dispose();
                }
            };

            class PanelPiocherCarte extends JPanel implements ActionListener{
                private Controleur ctrl;
                private JButton btnAnnuler;
                private JButton btnValider;
                private JFrame frame;
                private ArrayList<CarteObjectif> cartesObjTires;
                private ArrayList<CarteObjectif> cartesObjChoisies;
                private ArrayList<JButton>       ensButtons;

                private JButton btnCarte1;
                private JButton btnCarte2;
                private JButton btnCarte3;

                public PanelPiocherCarte(Controleur ctrl, JFrame frame, int nbCarteMini) {
                    this.ctrl = ctrl;
                    this.frame = frame;
                    this.btnAnnuler = new JButton("Annuler");
                    this.btnValider = new JButton("Valider");
                    this.cartesObjTires = new ArrayList<CarteObjectif>();
                    this.cartesObjChoisies = new ArrayList<CarteObjectif>();
                    this.ensButtons = new ArrayList<JButton>();

                    this.setLayout(new BorderLayout());

                    JPanel panelCartes = new JPanel();
                    JPanel panelChoix = new JPanel();

                    panelChoix.setLayout(new GridLayout(1, 2));


                    GridLayout gl = new GridLayout(3, 1);
                    Color couleur = new Color(186, 178, 122);
                    panelCartes.setLayout(gl);

                    gl.setVgap(20);
                    gl.setHgap(20);

                    for (int i = 0; i < 3; i++) {
                        this.cartesObjTires.add(this.ctrl.getMetier().getPioche().tirerCarteObjectif());
                    }

                    this.btnCarte1 = new JButton(this.cartesObjTires.get(0).toString());
                    this.btnCarte2 = new JButton(this.cartesObjTires.get(1).toString());
                    this.btnCarte3 = new JButton(this.cartesObjTires.get(2).toString());

                    this.ensButtons.add(this.btnCarte1);
                    this.ensButtons.add(this.btnCarte2);
                    this.ensButtons.add(this.btnCarte3);

                    this.btnCarte1.setBackground(couleur);
                    this.btnCarte2.setBackground(couleur);
                    this.btnCarte3.setBackground(couleur);

                    this.btnCarte1.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                    this.btnCarte2.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                    this.btnCarte3.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

                    panelCartes.add(this.btnCarte1);
                    panelCartes.add(this.btnCarte2);
                    panelCartes.add(this.btnCarte3);

                    this.add(panelCartes, BorderLayout.CENTER);
                    panelChoix.add(this.btnAnnuler);
                    panelChoix.add(this.btnValider);
                    this.add(panelChoix, BorderLayout.SOUTH);

                    this.btnAnnuler.addActionListener(this);
                    this.btnValider.addActionListener(this);
                    this.btnCarte1.addActionListener(this);
                    this.btnCarte2.addActionListener(this);
                    this.btnCarte3.addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == this.btnAnnuler) {
                        this.frame.dispose();
                    }
                    if (e.getSource() == this.btnValider) {
                        if (this.cartesObjChoisies.size() < nbCarteMini || this.cartesObjChoisies == null) {
                            JOptionPane.showMessageDialog(this, "Vous n'avez pas choisi assez de carte objectif", "Erreur", JOptionPane.ERROR_MESSAGE);

                        }
                        else {
                            for (CarteObjectif c : this.cartesObjChoisies) {
                                this.ctrl.getMetier().getPioche().retirerCarteObjectif(c);
                                this.ctrl.getMetier().getJoueurActuel().ajouterCarteObjectif(c);
                                this.cartesObjTires.remove(c);
                            }

                            for (CarteObjectif c : this.ctrl.getMetier().getJoueurActuel().getCarteObjectif()) {
                                System.out.println(c.getNoeud1() + " ---> " + c.getNoeud2());
                            }

                            if (this.cartesObjTires.size() != 0) {
                                for (CarteObjectif c : this.cartesObjTires) {
                                    this.ctrl.getMetier().getPioche().ajouterCarteObjectif(c);
                                }
                            }
                            this.frame.dispose();
                            this.ctrl.getMetier().finDeTour();
                        }
                    }
                    else {
                        if (this.cartesObjChoisies.contains(this.cartesObjTires.get(this.ensButtons.indexOf(e.getSource())))) {
                            this.cartesObjChoisies.remove(this.cartesObjTires.get(this.ensButtons.indexOf(e.getSource())));
                            this.ensButtons.get(this.ensButtons.indexOf(e.getSource())).setBorder(BorderFactory.createLineBorder(Color.RED, 5));

                        }
                        else {
                            this.cartesObjChoisies.add(this.cartesObjTires.get(this.ensButtons.indexOf(e.getSource())));
                            this.ensButtons.get(this.ensButtons.indexOf(e.getSource())).setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
                        }
                        
                    }

                }
            }
            PanelPiocherCarte panelPiocherCarte = new PanelPiocherCarte(ctrl, frame, nbCarteMini);

            frame.add(panelPiocherCarte);
            //frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(250, 200);
            frame.setLocation(450, 450);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            frame.setResizable(false);

            
        }
    }
}