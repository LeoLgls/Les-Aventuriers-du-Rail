package src.metier;

import java.awt.Color;
import java.util.ArrayList;

public class Joueur
{
    private int nbPionsWagon;
    private int points;

    private int nbCartesRouge = 0;
    private int nbCartesBlanche = 0;
    private int nbCartesNoire = 0;
    private int nbCartesBleue = 0;
    private int nbCartesVerte = 0;
    private int nbCartesJaune = 0;
    private int nbCartesOrange = 0;
    private int nbCartesRose = 0;
    private int nbCartesJoker = 0;
    private String nom;
    private Metier metier;
    
    private ArrayList<CarteObjectif> mainCarteObjectif;
    private ArrayList<CarteWagons> mainCarteWagon;
    private boolean finDePartie;

    public Joueur(String nom, int nbPionsWagon, Metier me)
    {
        this.nom = nom;
        this.nbPionsWagon = nbPionsWagon;
        this.points = 0;
        this.mainCarteObjectif = new ArrayList<CarteObjectif>();
        this.mainCarteWagon = new ArrayList<CarteWagons>();
        this.finDePartie = false;
        this.metier = me;
    }

    public String getNom()
    {
        return this.nom;
    }

    public int getNbPionsWagon() 
    {
        return this.nbPionsWagon;
    }

    public int getPoints() 
    {
        return this.points;
    }

    public void setNbPionsWagon(int nbPionsWagon) 
    {
        this.nbPionsWagon = nbPionsWagon;
    }

    public void retirerPions(int nbPionsWagon) {
        this.nbPionsWagon -= nbPionsWagon;
    }

    public void setPoints(int points) 
    {
        this.points = points;
    }

    public void setFinDePartie() 
    {
        this.finDePartie = true;
    }

    public boolean getFinDePartie() 
    {
        return this.finDePartie;
    }

    public void ajouterPoints(int points) 
    {
        this.points += points;
    }

    public void retirerPoints(int points) {
        this.points -= points;
    }

    public void ajouterCarteWagon(CarteWagons c) {
        this.mainCarteWagon.add(c);
    }
    
    public void retirerCarteWagon(Color c) {
        for ( CarteWagons carte : this.mainCarteWagon ) {
            if ( carte.getCouleur() == c ) {
                this.metier.getPioche().defausserCarteWagon(carte);
                this.mainCarteWagon.remove(carte);
                break;
            }
        }
    }

    public void viderMainObjectif()
    {
        this.mainCarteObjectif.clear();
    }

    public void ajouterCarteObjectif(CarteObjectif c) {
        this.mainCarteObjectif.add(c);
    }

    public int getNbCartesWagon() {
        return this.mainCarteWagon.size();
    }

    public int getNbCartesWagonRouge() 
    {
        nbCartesRouge = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur().equals(Color.RED)) 
            {
                nbCartesRouge++;
            }
        }
        return nbCartesRouge;
    }

    public int getNbCartesWagonBlanche() 
    {
        nbCartesBlanche = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur().equals(Color.WHITE)) 
            {
                nbCartesBlanche++;
            }
        }
        return nbCartesBlanche;
    }

    public int getNbCartesWagonNoire() 
    {
        nbCartesNoire = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.BLACK) 
            {
                nbCartesNoire++;
            }
        }
        return nbCartesNoire;
    }

    public int getNbCartesWagonBleue() 
    {
        nbCartesBleue = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.BLUE) 
            {
                nbCartesBleue++;
            }
        }
        return nbCartesBleue;
    }

    public int getNbCartesWagonVerte() 
    {
        nbCartesVerte = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.GREEN) 
            {
                nbCartesVerte++;
            }
        }
        return nbCartesVerte;
    }

    public int getNbCartesWagonJaune() 
    {
        nbCartesJaune = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.YELLOW) 
            {
                nbCartesJaune++;
            }
        }
        return nbCartesJaune;
    }

    public int getNbCartesWagonOrange() 
    {
        nbCartesOrange = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.ORANGE) 
            {
                nbCartesOrange++;
            }
        }
        return nbCartesOrange;
    }

    public int getNbCartesWagonRose() 
    {
        nbCartesRose = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur() == Color.PINK) 
            {
                nbCartesRose++;
            }
        }
        return nbCartesRose;
    }

    public int getNbCartesWagonJoker() 
    {
        nbCartesJoker = 0;
        for (CarteWagons c : this.mainCarteWagon) 
        {
            if (c.getCouleur().equals(Color.GRAY)) 
            {
                nbCartesJoker++;
            }
        }
        return nbCartesJoker;
    }

    public void retirerCarteWagonCol(Color c, int n) {
        ArrayList<CarteWagons> mod =new ArrayList<>();
        mod.addAll(this.mainCarteWagon);
        if (n != 0) {
            for (CarteWagons carte : mod)
            {
                if (carte.getCouleur().equals(c))
                {
                    this.metier.getPioche().defausserCarteWagon(carte);
                    this.mainCarteWagon.remove(carte);
                    n--;
                    if (n <= 0)
                    {
                        break;
                    }
                }
            }
        }
    }
/*
    public void retirerJoker(int n) {
        if (n != 0) {
            for (CarteWagons carte : this.mainCarteWagon)
                {
                    if (carte.getCouleur() == Color.GRAY)
                    {
                        this.metier.getPioche().defausserCarteWagon(carte);
                        this.mainCarteWagon.remove(carte);
                        n--;
                        if (n <= 0)
                        {
                            break;
                        }
                    }
                }
            }
    }*/

    public int getNbCartesWagonsColor(Color c)
    {
        int nbCartesCol = 0;
        for (CarteWagons carte : this.mainCarteWagon)
        {
            if (carte.getCouleur().equals(c))
            {
                nbCartesCol++;
            }
        }
        return nbCartesCol;
    }

    //getters cartes main
    public ArrayList<CarteObjectif> getCarteObjectif() {
        return this.mainCarteObjectif;
    }

    public ArrayList<CarteWagons> getCarteWagon() {
        return this.mainCarteWagon;
    }


    //affichage cartes

    public void afficherCartesObjectifs() {
        for (CarteObjectif c : this.mainCarteObjectif) {
            System.out.println(c.getNoeud1() + " ------ " + c.getNoeud2());
        }
    }
}