package src.metier;

import java.awt.Color;
import java.io.File;

public class CarteWagons 
{
    private Color couleur;
    private File image;

    public CarteWagons(Color couleur, File image) 
    {
        this.couleur = couleur;
        this.image = image;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public File getFile() {
        return this.image;
    }
}