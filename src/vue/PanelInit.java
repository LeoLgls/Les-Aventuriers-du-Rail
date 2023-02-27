package src.vue;

import src.Controleur;
import src.metier.Voie;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

import javax.swing.filechooser.FileFilter;

import java.io.*;

public class PanelInit extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private FrameInit frameInit;
	private JPanel panelContenu;
    private JButton btnJouer;
    private JLabel lblTitrePrincipal;

    private JRadioButton rBtnSolo;
    private JRadioButton rBtnLocal;
//    private JRadioButton rBtnReseau;
    private JRadioButton rBtnDemo;
    private ButtonGroup  bgModeDeJeu;
   
    private final FileFilter fileXml;


    public PanelInit(Controleur ctrl, FrameInit frameInit) 
    {
        this.ctrl = ctrl;
        this.frameInit = frameInit;

        this.setLayout(new GridLayout(6,1));
        this.lblTitrePrincipal = new JLabel("Les Aventuriers du Rail");
		this.btnJouer = new JButton("Jouer");

        // Créer les boutons radio
        rBtnSolo = new JRadioButton("Solo");
        rBtnLocal = new JRadioButton("Local Multijoueur");
//        rBtnReseau = new JRadioButton("Réseau Multijoueur");  
        rBtnDemo = new JRadioButton("Démo");
        
        // Ajouter les boutons radio au groupe
        bgModeDeJeu = new ButtonGroup();  
        bgModeDeJeu.add(rBtnSolo);
        bgModeDeJeu.add(rBtnLocal);  
//        bgModeDeJeu.add(rBtnReseau);  
        bgModeDeJeu.add(rBtnDemo);  
        
        // Sélectionner le bouton radio par défaut
        rBtnSolo.setSelected(true);

        this.add(this.lblTitrePrincipal);
        this.add(this.btnJouer);
        this.add(new JLabel("Mode de jeu :"));
        this.add(rBtnSolo);
        this.add(rBtnLocal);   
//        this.add(rBtnReseau);  
        this.add(rBtnDemo); 

        fileXml = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription()
			{
				return "Fichier XML";
			}
		};


        this.btnJouer.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == this.btnJouer)
        {
            if (rBtnSolo.isSelected())
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(fileXml);
                fileChooser.setDialogTitle("Specify a file to open");
                fileChooser.setApproveButtonText("Open");

                int returnVal = fileChooser.showOpenDialog(frameInit);


                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try
                {
                    this.ctrl.getMetier().importerXML(file);
                }
                catch (Exception exc) 
                { 
                    System.out.println("Erreur d'import");
                    //afficher l'ecran d'erreur
                    //renvoyer sur le menu prncipal
                    this.ctrl = new Controleur();
                }
                if (file != null)
                {
                    this.ctrl.getMetier().lancerPartie(1);
                }
                else
                {
                    this.ctrl=new Controleur();
                }

            }
            else if (rBtnLocal.isSelected())
            {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(fileXml);
                fileChooser.setDialogTitle("Specify a file to open");
                fileChooser.setApproveButtonText("Open");

                int returnVal = fileChooser.showOpenDialog(frameInit);

                try {this.ctrl.getMetier().importerXML(new File(fileChooser.getSelectedFile().getAbsolutePath()));} 
                catch (Exception exc) 
                { 
                    System.out.println("Erreur d'import");
                    //afficher l'ecran d'erreur
                    //renvoyer sur le menu prncipal
                }

                //ouvrir un menu pour demander le nombre de joueurs et indiquer le nombre de joueurs max de la carte actuelle
                JFrame frameSelectNbJoueur = new JFrame("Nombre de joueurs");

                class PanelSelectJoueur extends JPanel implements ActionListener
                {
                    private Controleur ctrl;
                    private JSpinner spinSelectJoueur;
                    private JLabel lblSelectJoueur;
                    private JLabel lblNbJoueurMax;
                    private JLabel lblNbJoueurMaxVal;
                    private JButton btnValider;
    
                    public PanelSelectJoueur(Controleur ctrl) 
                    {
                        this.ctrl = ctrl;
                        this.setLayout(new GridLayout(3,2));

                        //création
                        this.spinSelectJoueur = new JSpinner(new SpinnerNumberModel(2, 2, this.ctrl.getMetier().getRegles().getNbJoueursMax(), 1));
                        this.lblSelectJoueur = new JLabel("Nombre de joueurs :");
                        this.lblNbJoueurMax = new JLabel("Nombre de joueurs max :");
                        this.lblNbJoueurMaxVal = new JLabel(this.ctrl.getMetier().getRegles().getNbJoueursMax() + "");
                        this.btnValider = new JButton("Valider");

                        //positionnement
                        this.add(this.lblSelectJoueur);
                        this.add(this.spinSelectJoueur);
                        this.add(this.lblNbJoueurMax);
                        this.add(this.lblNbJoueurMaxVal);
                        this.add(this.btnValider);

                        //activation
                        this.btnValider.addActionListener(this);
                    }
    
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        if (e.getSource() == this.btnValider)
                        {
                            this.ctrl.getMetier().setNbJoueur((int) this.spinSelectJoueur.getValue());
                            new FrameSaisirNom(this.ctrl);
                            frameSelectNbJoueur.dispose();
                        }

                    }
                }

                PanelSelectJoueur panelSelectJoueur = new PanelSelectJoueur(ctrl);

                frameSelectNbJoueur.add(panelSelectJoueur);
                frameSelectNbJoueur.pack();
                frameSelectNbJoueur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameSelectNbJoueur.setLocationRelativeTo(null);
                frameSelectNbJoueur.setVisible(true);          
                
            }
            else if (rBtnDemo.isSelected())
            {
                this.ctrl.getMetier().lancerPartie(3);
            }
            this.frameInit.dispose();
        }
    }
}
