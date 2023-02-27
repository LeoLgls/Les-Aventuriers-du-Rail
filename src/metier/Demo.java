package src.metier;

import java.awt.Color;
import java.io.File;

import src.Controleur;
import src.metier.*;
import src.vue.FrameDemo;
import src.vue.FrameGame;

public class Demo 
{
    private Controleur ctrl;
    private int etatPartie;
    private FrameDemo frameDemo;
    private static boolean premierPassage = true;

    public Demo(Controleur ctrl)
    {
        this.etatPartie = 0;
        this.ctrl = ctrl;
        this.frameDemo = null;
    }

    public void LancerDemo(int etatPartie)
    {
        this.etatPartie = etatPartie;

        try {
            ctrl.getMetier().importerXML(new File("Demo.xml"));
            } 
        catch (Exception e) {}

            if (this.premierPassage)
            {
                ctrl.getMetier().initialiserJoueurs();
                this.premierPassage = false;
            }


        ctrl.getMetier().preparerPioches();
        
        ctrl.getMetier().retournerCinqCartesWagons();

        ctrl.setIhmGame(new FrameGame(this.ctrl));
	    ctrl.getMetier().setCalque(ctrl.getMetier().getImg());

        this.frameDemo = new FrameDemo(this.ctrl);

        ctrl.getMetier().getJoueur(0).viderMainObjectif();
        ctrl.getMetier().getJoueur(1).viderMainObjectif();


        ctrl.getMetier().getJoueur(0).ajouterCarteObjectif(ctrl.getMetier().getCarteObjectif(0));
        ctrl.getMetier().getJoueur(0).ajouterCarteObjectif(ctrl.getMetier().getCarteObjectif(1));

        ctrl.getMetier().getJoueur(1).ajouterCarteObjectif(ctrl.getMetier().getCarteObjectif(2));
        ctrl.getMetier().getJoueur(1).ajouterCarteObjectif(ctrl.getMetier().getCarteObjectif(3));

        switch(etatPartie)
        {
            case 0 : System.out.println("Situation initiale de presentation ");
                    
                        break;

            case 1 : System.out.println("Situation en cas de 3 jockers dans la pioche visible");


                    ctrl.getMetier().getPioche().setWagonVisible(
                                                                    new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()),
                                                                    new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()),
                                                                    new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()),
                                                                    new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()),
                                                                    new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon())
                                                                    );
                        
                    ctrl.getMetier().getPioche().viderPiocheWagon();                                                       
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));

                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.GREEN, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.YELLOW, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.RED, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    break;

            case 2 : System.out.println("situation de pioche vide");

                    ctrl.getMetier().getPioche().viderPiocheWagon();

                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.ORANGE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.PINK, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.GRAY, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.ORANGE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().defausserCarteWagon(new CarteWagons(Color.PINK, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));

                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.BLUE, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.RED, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));
                    ctrl.getMetier().getPioche().ajouterCarteWagon(new CarteWagons(Color.YELLOW, ctrl.getMetier().getPioche().getImageVersoCarteWagon()));

                    System.out.println("Taille de la pioche : " + ctrl.getMetier().getPioche().getCarteWagons().size());
                    break;

            case 3 : System.out.println("situation de fin de partie");

                    ctrl.getMetier().getJoueur(1).setFinDePartie();
                    
        }


    }

    public void resetVisuel()
    {
        this.frameDemo.dispose();
    }
    
}
