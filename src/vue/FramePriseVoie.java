package src.vue;

import src.Controleur;
import src.metier.Voie;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePriseVoie extends JFrame implements ActionListener
{
	private JScrollPane scrollBase;
	private MyTable tabVoie;
	private PanelMenu panelMere;
	private JPanel panVoie;
	private JPanel panBouton;
	private Controleur ctrl;
	private JButton btnValid;
	private JButton btnAnnul;
	private DefaultTableModel tableModel;

	public class MyTable extends JTable
	{

		private Controleur ctrl;

		public MyTable(TableModel model)
		{
			super(model);
			this.ctrl = FramePriseVoie.this.ctrl;
		}


		/*public MyTable(int rows, int cols) {
			super(rows, cols);
		}*/

		@Override
		public Class getColumnClass(int column)
		{
			switch (column)
			{
				case 1:
				case 2:
					return Integer.class;

				default:
					return String.class;
			}
		}

		@Override
		public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
		{
			Component c = super.prepareRenderer(renderer, row, column);
			Color colour = Color.WHITE;
			if (!c.getBackground().equals(getSelectionBackground()))
			{
				if (this.ctrl.getMetier().getNbJoueur() >= this.ctrl.getMetier().getRegles().getNbJVoiesDoubles())
				{
					if (row < this.ctrl.getMetier().getVoiesDoubles().size() * 2)
					{
						if (row % 2 == 0)
						{
							colour = this.ctrl.getMetier().getVoiesDoubles().get(row / 2)[0].getCouleur();
						} else
						{
							colour = this.ctrl.getMetier().getVoiesDoubles().get(row / 2)[1].getCouleur();
						}
					} else
					{
						colour = this.ctrl.getMetier().getVoies().get(row - this.ctrl.getMetier().getVoiesDoubles().size() * 2).getCouleur();
					}
				} else
				{
					colour = ctrl.getVoies().get(row).getCouleur();
				}
				if (colour.equals(Color.RED) || colour.equals(Color.BLUE) || colour.equals(Color.BLACK) || colour.equals(Color.GRAY))
				{
					c.setForeground(Color.WHITE);
				} else
				{
					c.setForeground(Color.BLACK);
				}
				c.setBackground(colour);
				c.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				c.setFont(new Font("", Font.BOLD, 15));
			}
			return c;
		}

	}

	public FramePriseVoie(Controleur ctrl)

	{
		//new String[]{"Couleur", "Distance", "Ville1", "Ville2"}
		this.setTitle("Prise de voie");

		this.ctrl = ctrl;
		this.panVoie = new JPanel(new BorderLayout());
		this.setSize(600, 500);
		setLocationRelativeTo(null);
		this.panBouton = new JPanel(new GridLayout(1, 2));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setAlwaysOnTop(true);

		this.ctrl.getIhmGame().getPanMenu().changeStateIsOpen();

		this.tableModel = new DefaultTableModel(new String[]{"Distance", "Ville1", "Ville2", "Propriétaire"}, 0)
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};


		this.tabVoie = new MyTable(this.tableModel);
		this.scrollBase = new JScrollPane(this.tabVoie);
		this.btnValid = new JButton("Valider");
		this.btnAnnul = new JButton("Annuler");
		this.panVoie.add(this.scrollBase, BorderLayout.CENTER);
		this.panBouton.add(this.btnAnnul);
		this.panBouton.add(this.btnValid);
		this.panVoie.add(this.panBouton, BorderLayout.SOUTH);
		this.add(this.panVoie);


		if (this.ctrl.getMetier().getNbJoueur() >= this.ctrl.getMetier().getRegles().getNbJVoiesDoubles())
		{
			for (Voie[] v : this.ctrl.getMetier().getVoiesDoubles())
			{
				if (v[0].getProprietaire() == null)
				{
					this.tableModel.addRow(new String[]{
							String.valueOf(v[0].getWagons()),
							v[0].getNoeud1().getNom(), v[0].getNoeud2().getNom(), ""});

				} else
				{
					this.tableModel.addRow(new String[]{
							String.valueOf(v[0].getWagons()),
							v[0].getNoeud1().getNom(), v[0].getNoeud2().getNom(), v[0].getProprietaire().getNom()});
				}
				if (v[1].getProprietaire() == null)
				{
					this.tableModel.addRow(new String[]{
							String.valueOf(v[1].getWagons()),
							v[1].getNoeud1().getNom(), v[1].getNoeud2().getNom(), ""});
				} else
				{
					this.tableModel.addRow(new String[]{
							String.valueOf(v[1].getWagons()),
							v[1].getNoeud1().getNom(), v[1].getNoeud2().getNom(), v[1].getProprietaire().getNom()});
				}
			}
		}

		for (Voie v : this.ctrl.getMetier().getVoies())
		{
			if (v.getProprietaire() == null)
			{
				this.tableModel.addRow(new String[]{
						String.valueOf(v.getWagons()),
						v.getNoeud1().getNom(), v.getNoeud2().getNom(), ""});
			} else
			{
				this.tableModel.addRow(new String[]{
						String.valueOf(v.getWagons()),
						v.getNoeud1().getNom(), v.getNoeud2().getNom(), v.getProprietaire().getNom()});
			}
		}

		this.btnValid.addActionListener(this);
		this.btnAnnul.addActionListener(this);


		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAnnul)
		{
			this.dispose();
		}
		if (e.getSource() == this.btnValid)
		{

			if (tabVoie.getSelectedRow() != -1)
			{
				if (this.ctrl.getMetier().getRegles().getNbJVoiesDoubles() <= this.ctrl.getMetier().getNbJoueur())
				{
					System.out.println("test1");


					System.out.println("checkpoint 1");
					if (tabVoie.getSelectedRow() < this.ctrl.getMetier().getVoiesDoubles().size() * 2)
					{
						if ((this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[0]).getProprietaire() == null)
						{
							if (tabVoie.getSelectedRow() % 2 == 0)
							{
								if (this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[0].getCouleur()) +
										this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker() >=
										this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[0].getWagons())
								{
									Voie v = this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[0];
									Color gris = new ColorUIResource(192,192,192);
						if (v.getCouleur().equals(gris))
						{
							new FrameValidePriseVoie(ctrl, v);
							this.dispose();
						}
						else
									if ((this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= v.getNbWagons()))
									{
										new FrameValidePriseVoie(ctrl, v);
										this.dispose();
									} else
									{
										JOptionPane.showMessageDialog(this, "Vous n'avez pas assez de pions", "Erreur", JOptionPane.ERROR_MESSAGE);
									}

								}
							} else
							{
								if (this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[1].getCouleur()) +
										this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker() >=
										this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[1].getWagons())
								{
									Voie v = this.ctrl.getMetier().getVoiesDoubles().get(tabVoie.getSelectedRow() / 2)[1];
									Color gris = new ColorUIResource(192,192,192);
						if (v.getCouleur().equals(gris))
						{
							new FrameValidePriseVoie(ctrl, v);
							this.dispose();
						}
						else
									if ((this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= v.getNbWagons()))
									{
										new FrameValidePriseVoie(ctrl, v);
										this.dispose();
									} else
									{
										JOptionPane.showMessageDialog(this, "Vous n'avez pas assez de pions", "Erreur", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						} else
						{
							JOptionPane.showMessageDialog(this, "Un Joueur possède deja cette voie", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} else
					{
						if(this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow() - this.ctrl.getMetier().getVoiesDoubles().size() * 2).getProprietaire() == null )
						{
						if (this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow() - this.ctrl.getMetier().getVoiesDoubles().size() * 2).getCouleur()) +
								this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker() >=
								this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow() - this.ctrl.getMetier().getVoiesDoubles().size() * 2).getWagons())
						{
							Voie v = this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow() - this.ctrl.getMetier().getVoiesDoubles().size() * 2);
							Color gris = new ColorUIResource(192,192,192);
						if (v.getCouleur().equals(gris))
						{
							new FrameValidePriseVoie(ctrl, v);
							this.dispose();
						}
						else
							if ((this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= v.getNbWagons()))
							{
								new FrameValidePriseVoie(ctrl, v);
								this.dispose();
							} else
							{
								JOptionPane.showMessageDialog(this, "Vous n'avez pas assez de pions", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
						}
						else
						{
							JOptionPane.showMessageDialog(this, "Un Joueur possède deja cette voie", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else
				{
					System.out.println("test2");
					if ((this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow())).getProprietaire() == null)
					{
						System.out.println("checkpoint 2");
						Voie v = this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow());
						Color gris = new ColorUIResource(192,192,192);
						if (v.getCouleur().equals(gris))
						{
							new FrameValidePriseVoie(ctrl, v);
							this.dispose();
						}
						else
						if (this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonsColor(this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow()).getCouleur()) +
								this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJoker() >=
								this.ctrl.getMetier().getVoies().get(tabVoie.getSelectedRow()).getWagons())
						{
							
							if ((this.ctrl.getMetier().getJoueurActuel().getNbPionsWagon() >= v.getNbWagons()))
							{
								new FrameValidePriseVoie(ctrl, v);
								this.dispose();
							} else
							{
								JOptionPane.showMessageDialog(this, "Vous n'avez pas assez de pions", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else
					{
						JOptionPane.showMessageDialog(this, "Un Joueur possède deja cette voie", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}

				System.out.println("carteRouges : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRouge());
				System.out.println("carteBleues : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBleue());
				System.out.println("carteVertes : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonVerte());
				System.out.println("carteJaunes : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonJaune());
				System.out.println("carteNoires : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonNoire());
				System.out.println("carteBlanches : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonBlanche());
				System.out.println("carteRose : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonRose());
				System.out.println("carteOrange : " + this.ctrl.getMetier().getJoueurActuel().getNbCartesWagonOrange());
			}

		}
		//this.dispose();
	}

	public Controleur getCtrl()
	{
		return ctrl;
	}

	@Override
	public void dispose()
	{
		this.ctrl.getIhmGame().repaintGame();
		this.ctrl.getIhmGame().getPanMenu().changeStateIsOpen();
		super.dispose();
	}
}