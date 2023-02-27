package src.metier;

import src.Controleur;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.event.CaretListener;

public class Pioche 
{
    private ArrayList<CarteWagons> ensCarteWagon;
    private ArrayList<CarteObjectif> ensCarteObjectif;
    private ArrayList<CarteWagons> ensCarteWagonVisible;

    private Controleur ctrl;
    
    private CarteWagons[] tabCarteWagonVisible;

    private ArrayList<CarteWagons> ensDefausse;

    private File imageVersoCarteWagon;
    private File imageVersoCarteObjectif;

    public Pioche(Controleur ctrl) 
    {
        this.ensCarteWagon = new ArrayList<CarteWagons>();
        this.ensCarteObjectif = new ArrayList<CarteObjectif>();
        this.ensCarteWagonVisible = new ArrayList<CarteWagons>();

        this.ctrl = ctrl;

        this.ensDefausse = new ArrayList<CarteWagons>();
        
        this.imageVersoCarteWagon = null;
        this.imageVersoCarteObjectif = null;
    }

    public void ajouterCarteWagon(CarteWagons crtWagon) 
    {
        this.ensCarteWagon.add(crtWagon);
    }

    public void ajouterCarteObjectif(CarteObjectif crtObjectif) 
    {
        this.ensCarteObjectif.add(crtObjectif);
    }

    public void setWagonVisible(CarteWagons ctr1, CarteWagons ctr2, CarteWagons ctr3, CarteWagons ctr4, CarteWagons ctr5)
    {
        this.ensCarteWagonVisible.clear();

        this.ensCarteWagonVisible.add(ctr1);
        this.ensCarteWagonVisible.add(ctr2);
        this.ensCarteWagonVisible.add(ctr3);
        this.ensCarteWagonVisible.add(ctr4);
        this.ensCarteWagonVisible.add(ctr5);
    }

    public void viderPiocheWagon()
    {
        this.ensCarteWagon.clear();
    }

    public void viderPiocheWagonVisible()
    {
        this.ensCarteWagonVisible.clear();
    }

    public void viderPiocheObjectif()
    {
        this.ensCarteObjectif.clear();
    }

    public void melangerPioches()
    {
        melangerPiocheObjectifs();
        melangerPiocheWagons();
    }

    //melange des pioches

    private void melangerPiocheWagons()
    {   //mélange la pioche
        Random r1 = new Random();

        for (int i = this.ensCarteWagon.size() - 1; i >= 1; i--) 
        {
            // swapping current index value
            // with random index value
            Collections.swap(this.ensCarteWagon, i, r1.nextInt(i + 1));
        }
    }

    private void melangerPiocheObjectifs() 
    {   //mélange la pioche
        Random r1 = new Random();

        for (int i = this.ensCarteObjectif.size() - 1; i >= 1; i--) 
        {
            Collections.swap(this.ensCarteObjectif, i, r1.nextInt(i + 1));
        }
    }

    //getters et setters des images

    public File getImageVersoCarteWagon() 
    {
        return imageVersoCarteWagon;
    }

    public void setImageVersoCarteWagon(File imageVersoCarteWagon) 
    {
        this.imageVersoCarteWagon = imageVersoCarteWagon;
    }

    public File getImageVersoCarteObjectif() 
    {
        return imageVersoCarteObjectif;
    }

    public void setImageVersoCarteObjectif(File imageVersoCarteObjectif) 
    {
        this.imageVersoCarteObjectif = imageVersoCarteObjectif;
    }

    //getters différentes pioches

    public ArrayList<CarteWagons> getCarteWagons() {
        return this.ensCarteWagon;
    }
    public ArrayList<CarteWagons> getEnsCarteWagonVisible() 
    {
        return this.ensCarteWagonVisible;
    }

    public ArrayList<CarteObjectif> getCarteObjectif() {
        return this.ensCarteObjectif;
    }

        //tirage pioches

    public CarteObjectif tirerCarteObjectif(){ 
        CarteObjectif c = this.ensCarteObjectif.get(0);
        this.retirerCarteObjectif();
        return c;
    }

    public void retirerCarteObjectif() {
        this.ensCarteObjectif.remove(0);
    }

    public void retirerCarteObjectif(CarteObjectif c)
    {
        this.ensCarteObjectif.remove(c);
    }

    public CarteWagons tirerCarteWagonInvisible() {
        CarteWagons c = this.ensCarteWagon.get(0);
        this.retirerCarteWagon();
        return c;
    }

    public void retirerCarteWagon() 
    {
        this.ensCarteWagon.remove(0);
        if (this.ensCarteWagon.size() <= 5)
        {
            this.ensCarteWagon.addAll(this.ensDefausse);
            this.ensDefausse.clear();
            melangerPiocheWagons();
        }
    }


    public void ajouterCarteWagonVisible() 
    {
        this.ensCarteWagonVisible.add(this.tirerCarteWagonInvisible());
    }

    public void ajouterCarteWagonVisible(int index) {
        this.ensCarteWagonVisible.add(index, tirerCarteWagonInvisible());
    }

    public void retirerCarteWagonVisible(int index) {
        this.ensCarteWagonVisible.remove(index);
        ajouterCarteWagonVisible(index);
    }

    public void retirerCarteWagonVisible(CarteWagons c) {
        this.ensCarteWagonVisible.remove(c);
    }

    public void defausserCarteWagon(CarteWagons crtWagon) 
    {
        this.ensDefausse.add(crtWagon);
    }

    public void reset() {
        for (int i = 0; i < 5; i++) {
            defausserCarteWagon(this.ensCarteWagonVisible.get(i));
        }

        
        this.ensCarteWagonVisible.clear();

        for (int i = 0; i < 5; i++) {
            ajouterCarteWagonVisible(i);
        }

        System.out.println("reset");
    }
}
