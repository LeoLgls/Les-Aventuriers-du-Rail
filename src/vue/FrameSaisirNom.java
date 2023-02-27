package src.vue;

import src.Controleur;

import javax.swing.*;


public class FrameSaisirNom extends JFrame
{
    public FrameSaisirNom(Controleur ctrl)
    {
        this.setSize(300, 150);
        this.setVisible(true);

        PanelSaisirNom panelSaisirNom = new PanelSaisirNom(ctrl,this);

        this.add(panelSaisirNom);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }


    
    

    
}
