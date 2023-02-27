package src.vue;

import src.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;

import java.awt.GridLayout;

public class PanelSaisirNom extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private FrameSaisirNom frameSaisirNom;
    private JButton btnValider;
    private ArrayList<JTextField> lstTxt = new ArrayList<JTextField>();

    private final FileFilter fileXml;

    public PanelSaisirNom(Controleur ctrl, FrameSaisirNom frameSaisirNom) 
    {
        this.ctrl = ctrl;
        this.frameSaisirNom = frameSaisirNom;
        this.setLayout(new GridLayout(this.ctrl.getMetier().getNbJoueur() + 1,2));

        //création
        this.btnValider = new JButton("Valider");

        for(int i = 0; i < this.ctrl.getMetier().getNbJoueur(); i++)
        {
            JLabel lblNomJoueur = new JLabel("Nom du joueur " + (i + 1) + " :");
            this.add(lblNomJoueur);
            JTextField txtNomJoueur = new JTextField();
            lstTxt.add(txtNomJoueur);
            this.add(txtNomJoueur);
        }

        //positionnement
        this.add(this.btnValider);

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

        //activation
        this.btnValider.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == this.btnValider)
        {

            for (JTextField jTextField : lstTxt) 
            {
                this.ctrl.getMetier().ajouterNomJoueur(jTextField.getText());
            }

            this.ctrl.getMetier().lancerPartie(2);
            this.frameSaisirNom.dispose();
        }

    }    
    
}