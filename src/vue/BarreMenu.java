package src.vue;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import src.metier.*;
import src.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class BarreMenu extends JMenuBar implements ActionListener
{
	private Controleur ctrl;
	private FrameGame frameG;
	private JMenu menuQuitter;
	private JMenuItem itemQuitter;
	private JMenu menuAide;
	private JMenuItem itemRegle;
	private JMenuItem itemRegleParametre;

	public BarreMenu(Controleur ctrl, FrameGame frameG)
	{
		this.frameG = frameG;
		this.ctrl = frameG.getCtrl();

		//creation des menus
		this.menuQuitter = new JMenu("Quitter");
		this.itemQuitter = new JMenuItem("Retour à l'écran titre");

		this.menuAide = new JMenu("Aide");
		this.itemRegle = new JMenuItem("Règles du jeu");
		this.itemRegleParametre = new JMenuItem("Règles paramétrées");

		menuQuitter.add(itemQuitter);
		menuAide.add(itemRegle);
		menuAide.add(itemRegleParametre);

		itemQuitter.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
		itemRegle.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK));
		itemRegleParametre.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));

		

		this.add(menuQuitter);
		this.add(menuAide);

		itemQuitter.addActionListener(this);
		itemRegle.addActionListener(this);
		itemRegleParametre.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == itemQuitter)
		{
			this.frameG.dispose();
			Metier metier = new Metier(this.ctrl);
			this.ctrl.setMetier(metier);
			new FrameInit(ctrl);
		}
		else if(e.getSource() == itemRegle)
		{
			JFrame frame = new JFrame("Règles du jeu");

			//creation nouveau panel
			class PanelRegle extends JPanel implements ActionListener 
			{
				private Controleur ctrl;
				private JButton btnFermer;
				private JPanel panelRegle;
				private JPanel panelTexte;
				private JPanel panelImage;
				private JScrollPane scrollPane;
				private JLabel lblImage;
				private JFrame frame;

				public PanelRegle(Controleur ctrl, JFrame frame) 
				{
					this.ctrl = ctrl;
					this.frame = frame;
					this.setLayout(new BorderLayout());
					
					//creation des composants
					this.panelRegle = new JPanel(new GridLayout(1,2));

					this.panelTexte = new JPanel(new GridLayout(1,1));
					this.panelImage = new JPanel();

					this.btnFermer = new JButton("Fermer");

					ImageIcon imageIcon = new ImageIcon("src/images/regle.png");
					this.lblImage = new JLabel(imageIcon);

					//positionnement
					this.panelTexte.add(new JLabel("<html><h3>Règles du jeu</h3> <br>" +
					"Le but du jeu est d’obtenir le plus de points. On gagne des points de la manière suivante :<br>" +
					"1. En capturant une route entre 2 villes.<br>" +
					"2. En reliant par une route, en continu, les deux villes d’une carte Destination <br>" +
					"conservée par le joueur ne sont pas reliées en continu à la fin du jeu.<br><br>" +
					"Tour de jeu :<br>" +
					"Le joueur qui a le plus voyagé commence. Par la suite, on joue dans le sens des aiguilles d’une montre.<br>" +
					"À son tour, le joueur doit faire une et une seule des trois actions suivantes :<br>" +
					"1. Prendre des cartes Wagons – le joueur peut prendre 2 cartes Wagon. Il peut prendre n’importe quelle<br>" +
					"carte visible parmi les 5 posées sur la table ou tirer une carte du dessus de la pioche (tirage en aveugle).<br>" +
					"Si le joueur prend une carte visible, il la remplace immédiatement par une autre du dessus de la pioche. Il peut ensuite prendre<br>" +
					"une deuxième carte,<br>" +
					"soit visible, soit en aveugle (voir section Cartes Wagon pour les cartes locomotives).<br>" +
					"2. Prendre possession d’une route – Le joueur peut s’emparer d’une route sur le plateau en posant autant de cartes<br>" +
					"Wagon que d’espaces composant la route. Après avoir défaussé ses cartes, le joueur pose alors ses wagons sur chaque espace<br>" +
					"constituant la route. Enfin, il déplace son marqueur de score en se référant au tableau de décompte des points.<br>" +
					"3. Prendre des cartes Destination supplémentaires – Le joueur prend 3 cartes Destination du dessus de la pioche.<br>" +
					"Il doit en conserver au moins une, mais peut aussi garder 2 ou 3 cartes. Chaque carte qui n’est pas conservée est posée<br>" +
					"face cachée sous la pioche des cartes Destination.<br><br>" +
					"Cartes Wagon :<br>" +
					"Il existe 8 types de wagons différents en plus de la locomotive. Les couleurs de chaque carte Wagon correspondent aux couleurs<br>" +
					"présentes sur le plateau afin de connecter les villes – Bleu, Violet, Marron, Blanc, Vert, Jaune, Noir et Rouge.<br>" +
					"Les locomotives sont multicolores et, comme des cartes joker, peuvent remplacer n’importe quelle couleur<br>" +
					"lors de la prise de possession d’une route.<br>" +
					"Il est possible de ne jouer que des cartes Locomotive pour prendre une route. Si une carte Locomotive figure parmi les 5 cartes<br>" +
					"visibles, le joueur peut la prendre, mais son tour s’arrête alors. La locomotive compte comme si l’on avait pris 2 cartes.<br>" +
					"Si, après avoir pris une carte visible (qui n’est pas une locomotive), la carte de remplacement est une locomotive, le joueur<br>" +
					"ne peut pas la prendre. Si, au cours du jeu, 3 cartes visibles sur 5 sont des locomotives, les 5 cartes sont alors immédiatement<br>" +
					"défaussées et remplacées par 5 nouvelles cartes.<br>" +
					"Un joueur peut avoir en main et à tout moment autant de cartes qu’il le souhaite.<br>" +
					"Quand la pioche de cartes est épuisée, les cartes défaussées sont soigneusement mélangées pour reconstituer une <br>" +
					"nouvelle pioche. Il est important de bien mélanger les cartes car elles ont été défaussées par séries de couleurs !<br>" +
					"Dans le cas peu probable où il n’y aurait plus de cartes disponibles (toutes les cartes sont dans les mains de joueurs),<br>" +
					"un joueur ne peut alors plus prendre de cartes. Il ne peut donc que prendre possession d’une route ou tirer de nouvelles<br>" +
					"cartes Destination.<br><br>" +
					"Prendre possession des routes :<br>" +
					"Pour prendre possession d’une route, un joueur doit jouer une série de cartes égale au nombre d’espaces composant la route.<br>" +
					"La série de cartes doit être composée de cartes du même type. La plupart des routes nécessitent une série de cartes de couleur<br>" +
					"spécifique. Par exemple, les routes bleues sont capturées en posant des cartes Wagon bleues.<br>" +
					"Certaines routes – en gris sur le plateau - peuvent être capturées en utilisant n’importe quelle série d’une même couleur.<br>" +
					"Lorsqu’une route a été capturée, le joueur pose ses wagons en plastique sur chacun des espaces qui constituent la route.<br>" +
					"Toutes les cartes utilisées pour s’approprier cette route sont défaussées.<br>" +
					"Un joueur peut prendre possession de n’importe quelle route sur le plateau de jeu. <br>" +
					"Il n’est pas obligé de se connecter avec les routes déjà à son actif.<br>" +
					"Une route prise par un joueur devient sa propriété exclusive.<br>" +
					"Aucun autre joueur ne peut plus revendiquer son usage ou son occupation.<br>" +
					"Certaines villes sont connectées par des routes doubles. Un même joueur ne peut pas prendre 2 routes reliant les 2 mêmes villes.<br><br>" +
					"Décompte des points :<br>" +
					"Lorsqu’un joueur prend possession d’une route, il obtient un certain nombre de point en<br>" +
					"fonction de la longueur de la voie (voir tableau)<br><br>" +
					"Prendre des cartes Destination :<br>" +
					"Un joueur peut utiliser son tour de jeu pour récupérer des cartes Destination supplémentaires. Pour cela, il doit prendre 3<br>" +
					"cartes sur le dessus de la pile des cartes Destination. Il doit conserver au moins l’une des trois cartes, mais peut bien <br>" +
					"sûr en garder 2 ou même 3. S’il reste moins de 3 cartes Destination dans la pile, le joueur ne peut prendre que le nombre de <br>" +
					"cartes disponibles. Chaque carte qui n’est pas conservée par le joueur est remise face cachée sous la pile.<br>" +
					"Chaque carte Destination fait référence à deux villes de la carte et un nombre de points y est associé. Si le joueur réalise <br>" +
					"la connexion entre les deux villes d’une carte Destination, il remporte le nombre de points indiqué sur la carte et l’additionne, <br>" +
					"en fin de partie, aux points déjà acquis. La route reliant ces deux villes doit être formée uniquement par les trains de ce <br>" +
					"joueur. Si la connexion n’est pas réalisée, le joueur déduit de son nombre de points déjà acquis le nombre indiqué sur la carte.<br>" +
					"Les cartes Destination sont gardées secrètes tout au long de la partie. Elles sont rendues publiques à la fin de la partie et <br>" +
					"chaque joueur calcule son score. Au cours du jeu, un joueur peut avoir autant de cartes Destination qu’il le souhaite.<br><br>" +
					"Fin du jeu :<br>" +
					"Lorsque la réserve de wagons d’un joueur est de inférieur ou égale au nombre passé en paramètres du jeu, <br>" +
					"après avoir joué son tour, chaque joueur, en incluant celui-ci, joue encore un tour.<br>" +
					"À l’issue de ce dernier tour, le jeu s’arrête et chacun compte ses points.<br></html>"));


					this.scrollPane = new JScrollPane(this.panelTexte);
					this.panelImage.add(this.lblImage);	
					
					this.panelRegle.add(scrollPane);
					this.panelRegle.add(this.panelImage);					

					this.add(this.panelRegle, BorderLayout.CENTER);
					this.add(this.btnFermer, BorderLayout.SOUTH);

					this.btnFermer.addActionListener(this);
				}
			

			
				public void actionPerformed(ActionEvent e) 
				{
					if(e.getSource() == this.btnFermer)
					{
						frame.dispose();
					}
				}

			}
		
			PanelRegle panelRegle = new PanelRegle(ctrl, frame);

			frame.add(panelRegle);
			//frame.pack();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setSize(2100, 500);
			frame.setLocation(0,100);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			//frame.setResizable(false);
		}
		else if(e.getSource() == itemRegleParametre)
		{
			JFrame frame = new JFrame("Règles par défaut");

			//creation nouveau panel
			class PanelRegle extends JPanel implements ActionListener 
			{
				private Controleur ctrl;
				private JButton btnFermer;

				private JFrame frame;

				private JPanel panelInfo;

				public PanelRegle(Controleur ctrl, JFrame frame) 
				{
					this.ctrl = ctrl;
					this.frame = frame;
					this.panelInfo = new JPanel(new GridLayout(10,2));
					this.setLayout(new BorderLayout());

					//creation
					this.btnFermer = new JButton("Fermer");
					this.panelInfo.add(new JLabel("Nombre de cartes roses : " + ctrl.getMetier().getRegles().getNbCartesWagonRose()));
					this.panelInfo.add(new JLabel("Nombre de joueurs : " + ctrl.getMetier().getNbJoueur()));
					this.panelInfo.add(new JLabel("Nombre de cartes noirs : " + ctrl.getMetier().getRegles().getNbCartesWagonNoir()));
					this.panelInfo.add(new JLabel("Nombre de Pions restant necessaire pour mettre fin a la partie : " + ctrl.getMetier().getRegles().getNbWagonFin()));
					this.panelInfo.add(new JLabel("Nombre de cartes bleues : " + ctrl.getMetier().getRegles().getNbCartesWagonBleu()));

					if (ctrl.getMetier().getRegles().getNbJVoiesDoubles() >= ctrl.getMetier().getNbJoueur())
					{
						this.panelInfo.add(new JLabel("Voies doubles actives : NON"));
					}
					else
					{
						this.panelInfo.add(new JLabel("Voies doubles actives : OUI"));
					}

					this.panelInfo.add(new JLabel("Nombre de cartes rouges : " + ctrl.getMetier().getRegles().getNbCartesWagonRouge()));

					if (ctrl.getMetier().getRegles().getLongCheminVal() == 0)
					{
						this.panelInfo.add(new JLabel("Long chemin actif : NON"));
					}
					else
					{
						this.panelInfo.add(new JLabel("Long chemin actif : OUI"));
					}

					this.panelInfo.add(new JLabel("Nombre de cartes vertes : " + ctrl.getMetier().getRegles().getNbCartesWagonVert()));
					this.panelInfo.add(new JLabel("Point du chemin le plus long : " + ctrl.getMetier().getRegles().getLongCheminVal()));
					this.panelInfo.add(new JLabel("Nombre de cartes jaunes : " + ctrl.getMetier().getRegles().getNbCartesWagonJaune()));
					this.panelInfo.add(new JLabel("Nombre de cartes objectifs : " + ctrl.getMetier().getPioche().getCarteObjectif().size()));
					this.panelInfo.add(new JLabel("Nombre de cartes oranges : " + ctrl.getMetier().getRegles().getNbCartesWagonOrange()));
					this.panelInfo.add(new JLabel("Nombre de pions par joueurs : " + ctrl.getMetier().getRegles().getNbWagons()));
					this.panelInfo.add(new JLabel("Nombre de cartes blanches : " + ctrl.getMetier().getRegles().getNbCartesWagonBlanc()));
					this.panelInfo.add(new JLabel("Nombre de cartes jockers : " + ctrl.getMetier().getRegles().getNbJocker()));

					//positionnement
					this.add(this.btnFermer, BorderLayout.SOUTH);
					this.add(this.panelInfo, BorderLayout.NORTH);

					this.btnFermer.addActionListener(this);
				}
			
			
				public void actionPerformed(ActionEvent e) 
				{
					if(e.getSource() == this.btnFermer)
					{
						frame.dispose();
					}
				}

			}
		
			PanelRegle panelRegle = new PanelRegle(ctrl, frame);

			frame.add(panelRegle);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			//frame.setSize(400, 100);
			frame.setLocation(500,300);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			//frame.setResizable(false);
		}
		
	}
}
