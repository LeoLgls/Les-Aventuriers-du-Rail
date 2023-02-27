package src.vue;

import src.metier.*;
import src.Controleur;

import java.awt.event.*;
import java.awt.*;
//import java.io.File;
import javax.swing.*;

public class PanelGame extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	private Controleur ctrl;
	private JButton btnImporter;
	private ImageIcon img;
	private double imgScale;
	private int imgWidth;
	private int imgHeight;

	private int maxWidth;
	private int maxHeight;
	private boolean carte = false;


	public PanelGame(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		//this.setMaximumSize(new Dimension(1325, 900));
		//this.setMinimumSize(new Dimension(600, 1000));

		this.addMouseMotionListener(this);
		this.addMouseListener(this);

		this.maxHeight = 900;
		this.maxWidth = 1325;
    }


    public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		int x = 0; //(int) ((this.getWidth()-this.maxWidth)/1.5);
		//int space = 10;
		//this.ctrl.getIhm().updateTable();

		if(!carte) {
			this.btnImporter = new JButton("importer carte en xml");
			btnImporter.addActionListener(this);
		}

		this.img = this.ctrl.getCalque();

		if (this.img != null)
		{
			//x =+ this.maxWidth/10;
			this.imgScale = (double) this.img.getIconWidth() / (double) this.img.getIconHeight();
			imgWidth = this.img.getIconWidth();
			imgHeight = this.img.getIconHeight();
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

			g2D.drawImage(this.img.getImage(), 0, 0, imgWidth, imgHeight, null);
			this.setSize(imgWidth, imgHeight);
		}

		for (Voie v : this.ctrl.getVoies())
		{
			if (v.getCouleur() == Color.WHITE) {
				g.setColor(Color.BLACK);
				g2D.setStroke(new BasicStroke(5));
			} else {
				g.setColor(Color.WHITE);
				g2D.setStroke(new BasicStroke(12));
			}

			g2D.drawLine(v.getNoeud1().getPosX() + 3+x,
					v.getNoeud1().getPosY() + 3,
					v.getNoeud2().getPosX() + 3+x,
					v.getNoeud2().getPosY() + 3);

			g.setColor(v.getCouleur());

			if (v.getProprietaire() == null)
			{
				g2D.setStroke(new BasicStroke(4.0f,
						BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER,
						1f, new float[]{v.getLongueur() / (v.getNbWagons()), 10.0f}, 4.0f));
			}
			else
			{
				g2D.setStroke(new BasicStroke(2.0f,
						BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER,
						1f, new float[]{v.getLongueur() / (v.getNbWagons()), 10.0f}, 4.0f));

			/* 	JLabel lblProprietaire = new JLabel(v.getProprietaire().getNom());

				g.setColor(Color.BLACK);
				g2D.fillRect((int) ((((v.getNoeud1().getPosX()+v.getNoeud2().getPosX()) / 2) - 1 - lblProprietaire.getPreferredSize().getWidth() / 2))+25,
				((v.getNoeud1().getPosY() + v.getNoeud2().getPosY()) / 2) + 3 - lblProprietaire.getPreferredSize().height,
				lblProprietaire.getPreferredSize().width,
				lblProprietaire.getPreferredSize().height + 1);*/

				//g.setColor(Color.BLACK);
				//g.setColor(Color.WHITE);
				//g2D.drawString(v.getProprietaire().getNom(), ((v.getNoeud1().getPosX()+v.getNoeud2().getPosX()) / 2), (v.getNoeud1().getPosY() + v.getNoeud2().getPosY()) / 2);
			}
			g.setColor(v.getCouleur());
			g2D.drawLine(v.getNoeud1().getPosX() + 3+x,
					v.getNoeud1().getPosY() + 3,
					v.getNoeud2().getPosX() + 3+x,
					v.getNoeud2().getPosY() + 3);
		}

		if (this.ctrl.getMetier().getRegles().getNbJVoiesDoubles() <= this.ctrl.getMetier().getNbJoueur())
		{
			for (Voie[] v : this.ctrl.getVoiesDoubles())
			{
				float offset = 4;


				int x1 = (int) (v[0].getNoeud1().getPosX() - offset * v[0].getSlopeCoefY());
				int y1 = (int) (v[0].getNoeud1().getPosY() + offset * v[0].getSlopeCoefX());

				int x2 = (int) (v[0].getNoeud2().getPosX() - offset * v[0].getSlopeCoefY());
				int y2 = (int) (v[0].getNoeud2().getPosY() + offset * v[0].getSlopeCoefX());


				int x3 = (int) (v[1].getNoeud1().getPosX() + offset * v[1].getSlopeCoefY());
				int y3 = (int) (v[1].getNoeud1().getPosY() - offset * v[1].getSlopeCoefX());

				int x4 = (int) (v[1].getNoeud2().getPosX() + offset * v[1].getSlopeCoefY());
				int y4 = (int) (v[1].getNoeud2().getPosY() - offset * v[1].getSlopeCoefX());


				g.setColor(Color.WHITE);
				g2D.setStroke(new BasicStroke(16));
				g2D.drawLine(v[0].getNoeud1().getPosX() + 3+x,
						v[0].getNoeud1().getPosY() + 3,
						v[0].getNoeud2().getPosX() + 3+x,
						v[0].getNoeud2().getPosY() + 3);

				g.setColor(v[0].getCouleur());
				if (v[0].getProprietaire() == null)
				{
					g2D.setStroke(new BasicStroke(4.0f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_MITER,
							1f, new float[]{v[0].getLongueur() / (v[0].getNbWagons()), 10.0f}, 4.0f));
				}
				else
				{
					g2D.setStroke(new BasicStroke(2.0f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_MITER,
							1f, new float[]{v[0].getLongueur() / (v[0].getNbWagons()), 10.0f}, 4.0f));
				}
				g2D.drawLine(x1 + 3+x, y1 + 3, x2 + 3+x, y2 + 3);


				g.setColor(v[1].getCouleur());
				if (v[1].getProprietaire() == null)
				{
					g2D.setStroke(new BasicStroke(4.0f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_MITER,
							1f, new float[]{v[1].getLongueur() / (v[1].getNbWagons()), 10.0f}, 4.0f));

				}
				else
				{
					g2D.setStroke(new BasicStroke(2.0f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_MITER,
							1f, new float[]{v[1].getLongueur() / (v[1].getNbWagons()), 10.0f}, 4.0f));
				}
				g2D.drawLine(x3 + 3+x, y3 + 3,x4 + 3+x, y4 + 3);
			}
		}


		for (Noeud n : this.ctrl.getNoeuds())
		{
			g.setColor(Color.BLACK);
			g2D.setStroke(new BasicStroke(5));
			g2D.fillOval(n.getPosX() - 5+x, n.getPosY() - 5, 10, 10);
			g2D.setColor(Color.WHITE);
			JLabel lblNom = new JLabel(n.getNom());

			g2D.fillRect((int) ((n.getStringPosX() - 1 - lblNom.getPreferredSize().getWidth() / 2))+x,
					n.getStringPosY() + 3 - lblNom.getPreferredSize().height,
					lblNom.getPreferredSize().width,
					lblNom.getPreferredSize().height + 1);

			g2D.setColor(Color.BLACK);
			g2D.drawString(lblNom.getText(),
					(int) ((n.getStringPosX() - lblNom.getPreferredSize().getWidth() / 2))+x,
					(int) n.getStringPosY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {//if (e.getSource() == )
		}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {

		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public int getMaxWidth()
	{
		return maxWidth;
	}

	public int getMaxHeight()
	{
		return maxHeight;
	}

}
