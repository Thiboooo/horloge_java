import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.math.*;

public class MonHorlogeBase extends JPanel implements Runnable {


	Image imgTmp;
	Graphics gTmp;
	boolean menu = false;
	static int coef = 0;
	static int heure_base;
	static int minute_base ;
	static int seconde_base ;
	static int milli_base ;
	static int heure_chx ;
	static int minute_chx ;
	static int seconde_chx ;
	static int milli_chx ;
	static boolean premier_heure = true ;
	static boolean premier_heure_v2 = true ;
	static int decal ;
	static int heure_button;
	static int minute_button;
	static int seconde_button;
	static int choixgmt = 0;
	static int choix = 0 ;
	
	/* Méthode paint qui dessine l'horloge */
	public void paint(Graphics gsp) {

		super.paint(gsp);

		setBackground(Color.white);

		int haut = getHeight(); 
		int larg = getWidth();

		imgTmp = createImage(larg, haut);
		gTmp = imgTmp.getGraphics();
		LocalDateTime currentTime = LocalDateTime.now();
		int heure = currentTime.getHour();
		int minute = currentTime.getMinute();
		int seconde = currentTime.getSecond();
		int milliseconde = currentTime.getNano()/1000000 ;
		/*if ( menu == false ){
			System.out.println("Menu :");
			System.out.println(" - Changement de creneau horaire : 1 ");
			System.out.println(" - Modifier l'heure courante : 2 ");
			System.out.println(" - Démarre un chronometre : 3 ");*/
			//Scanner sc = new Scanner(System.in);
			//int choix = 0;
			
			/*System.out.print("  Veuillez entrer le choix du mode que vous voulez : ");*/
			//choix = sc.nextInt();
			if (choix == 1 ){
				/*System.out.println("    - Vous voulez quelle changement d'horaire ? ");
				System.out.println("            - 1 : GMT+1 ");
				System.out.println("            - 2 : GMT+2 ");
				System.out.println("            - 3 : GMT+3 ");
				System.out.println("            - 4 : GMT ");
				System.out.println("            - 5 : GMT-1 ");
				System.out.println("            - 6 : GMT-2 ");
				System.out.print("  Veuillez entrer le choix du creneau horaire : ");*/
				//choixgmt = sc.nextInt();
				switch (choixgmt) {
					case 1:
						System.out.println("heure de base ");
						coef = 0 ; 
						break;
					case 2:
						coef = 1 ;
						System.out.println("2");
						break;
					case 3:
						coef = 2 ;
						System.out.println("3");
						break;
					case 4:
						coef = -1;
						System.out.println("4");
						break;
					case 5:
						coef = -2;
						System.out.println("5");
						break;
					case 6:
						coef = -3;
						System.out.println("6");
						break;
					case 7:
						coef = -4;
						System.out.println("7");
						break;
				
					default:
						System.out.println("Vous avez rentré un mauvais chiffre , on prends le gmt+1 c'est a dire paris ");
						break;
				}
			}else if (choix == 3){
					/* il faut faire heure - heure de base pour avoir l'heure a zero et apres tu scan f toute les heures minutes ect  */
				/*  faire un boolean pour gérer le premier cas pour avoir un truc de base  */
				if ( premier_heure == true ){
					heure_base = heure ;
					minute_base = minute ;
					seconde_base = seconde ;
					milli_base = milliseconde ;
					premier_heure = false ;
				}
				
			}else if (choix == 2){
					/* il faut faire heure - heure de base pour avoir l'heure a zero */

					if ( premier_heure == true ){
						heure_base = heure ;
						minute_base = minute ;
						seconde_base = seconde ;
						milli_base = milliseconde ;
						premier_heure = false ;
					}

				if ( premier_heure_v2 == true ){
					/*System.out.print("Veulliez choisir l'heure : ");
					//heure_chx = sc.nextInt();
					System.out.println(" ");
					System.out.print("Veulliez choisir les minutes : ");
					//minute_chx = sc.nextInt();
					System.out.println(" ");
					System.out.print("Veulliez choisir les secondes : ");
					//seconde_chx = sc.nextInt();
					System.out.println(" ");*/

					premier_heure_v2 = false ;
				}
			}else if (choix == 4 ){
				heure = currentTime.getHour();
				minute = currentTime.getMinute();
				seconde = currentTime.getSecond();
				milliseconde = currentTime.getNano()/1000000 ;
			}
		/*	menu = true ;
		}*/
		/* cette partie sert a faire un chrono ou bien meme une horloge avec des trucs definit  */
		if (premier_heure == false ) {
		heure = heure - heure_base ;
		minute = minute - minute_base ;
		seconde = seconde - seconde_base ;
		milliseconde =  milliseconde - milli_base ;
		}

		if (premier_heure_v2 == false ) {
			heure = heure + heure_chx ;
			minute = minute + minute_chx ; 
			seconde = seconde + seconde_chx  ;

			}


		heure = heure + coef;
		DessinHorloge.dessineHorloge(gTmp, haut, larg, heure, minute, seconde , milliseconde);
		gsp.drawImage(imgTmp, 0, 0, this);

	}

	public MonHorlogeBase(){
		Thread tr = new Thread(this);
        tr.start();
	}

	static public void main(String[] args) {
		/*JFrame frame = new JFrame();
		frame.getContentPane().add(new MonHorlogeBase());
		//panel.setSize(400 , 300);
		
		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		decal = 0;
		JFrame frame = new JFrame();
		frame.getContentPane().add(new MonHorlogeBase());
		JPanel panel = new JPanel();
		panel.setSize(400, 300);
		JPanel panel2 = new JPanel(new FlowLayout() {
			public Dimension preferredLayoutSize(Container target) {
				Dimension sd = super.preferredLayoutSize(target);
				sd.width = Math.min(200, sd.width);
				return sd;
			}
		});
		panel2.setSize(200, 200);
		JButton gmtp1 = new JButton("GMT+1");
		JButton gmtp2 = new JButton("GMT+2");
		JButton gmtp3 = new JButton("GMT+3");
		JButton gmtp = new JButton("GMT");
		JButton gmtm1 = new JButton("GMT-1");
		JButton gmtm2 = new JButton("GMT-2");
		JButton gmtm3 = new JButton("GMT-3");
		JButton valider = new JButton("Valider");
		JTextField textFieldheure = new JTextField();
		JTextField textFieldminute = new JTextField();
		JTextField textFieldseconde = new JTextField();
		JLabel label = new JLabel("Saisir le changement de ");
		JLabel labell = new JLabel("l'heure que vous voulez : ");
		JLabel label2 = new JLabel("Fuseau Horaire :");
		JLabel heure_label = new JLabel("Heure :");
		JLabel minute_label = new JLabel("Minute :");
		JLabel seconde_label = new JLabel("Seconde :");
		JLabel br = new JLabel("      ");
		JButton chrono = new JButton("Démarrer le chronomètre ");
		JButton reset = new JButton("Reset !");
		textFieldheure.setColumns(10);
		textFieldminute.setColumns(10);
		textFieldseconde.setColumns(10);
		valider.addActionListener(e -> {
			heure_chx = Integer.parseInt(textFieldheure.getText());
			minute_chx = Integer.parseInt(textFieldminute.getText());
			seconde_chx = Integer.parseInt(textFieldseconde.getText());
			textFieldheure.setText("");
			textFieldminute.setText("");
			textFieldseconde.setText("");
			choix = 2 ; 
		});
		gmtp.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 4;
		});
		gmtp1.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 1;
		});
		gmtp2.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 2;
		});
		gmtp3.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 3;
		});
		gmtm1.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 5;
		});
		gmtm2.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 6;
		});
		gmtm3.addActionListener(e -> {
			choix = 1 ;
			choixgmt = 7;
		});
		chrono.addActionListener(e -> {
			premier_heure = true ;
			premier_heure_v2 = true ;
			choix = 3;
		});
		reset.addActionListener(e -> {
			premier_heure = true ; 
			premier_heure_v2 = true ;
			coef = 0 ;
			choix = 4 ;
		});

		panel2.add(label2);
		panel2.add(gmtp1);
		panel2.add(gmtm1);
		panel2.add(gmtp2);
		panel2.add(gmtm2);
		panel2.add(gmtp3);
		panel2.add(gmtm3);
		panel2.add(gmtp);
		panel2.add(label);
		panel2.add(labell);
		panel2.add(heure_label);
		panel2.add(textFieldheure);
		panel2.add(minute_label);
		panel2.add(textFieldminute);
		panel2.add(seconde_label);
		panel2.add(textFieldseconde);
		panel2.add(valider);
		panel2.add(br);
		panel2.add(chrono);
		panel2.add(reset);
		frame.getContentPane().add(panel, BorderLayout.WEST);
		frame.getContentPane().add(panel2, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void run() {
		while(true){
		repaint();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
