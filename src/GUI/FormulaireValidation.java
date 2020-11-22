package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DaoClient;
import DAO.DaoCommande;
import ENTITIES.Client;
import ENTITIES.Commande;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormulaireValidation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	DaoCommande daoCommande = new DaoCommande();
	DaoClient daoClient = new DaoClient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormulaireValidation frame = new FormulaireValidation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * Create the frame.
	 */
	public FormulaireValidation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\karim\\Downloads\\l3.jpg"));
		setTitle("Valider");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(0, 0, 139));
		panel.setBounds(0, 0, 226, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbNom = new JLabel("Nom :");
		lbNom.setForeground(Color.WHITE);
		lbNom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbNom.setBounds(66, 108, 150, 26);
		panel.add(lbNom);
		
		JLabel lbPrenom = new JLabel("Pr\u00E9nom :");
		lbPrenom.setForeground(Color.WHITE);
		lbPrenom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbPrenom.setBounds(66, 162, 150, 26);
		panel.add(lbPrenom);
		
		JLabel lbEmail = new JLabel("E-mail :");
		lbEmail.setForeground(Color.WHITE);
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbEmail.setBounds(66, 216, 150, 26);
		panel.add(lbEmail);
		
		JLabel lbTel = new JLabel("T\u00E9l\u00E9phone :");
		lbTel.setForeground(Color.WHITE);
		lbTel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTel.setBounds(66, 270, 150, 26);
		panel.add(lbTel);
		
		JLabel lbAdresse = new JLabel("Adresse :");
		lbAdresse.setForeground(Color.WHITE);
		lbAdresse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbAdresse.setBounds(66, 324, 150, 26);
		panel.add(lbAdresse);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = daoClient.ajouterClient(new Client(0,textField.getText().toString(),textField_1.getText().toString(),
						textField_2.getText().toString(),textField_3.getText().toString(),textField_4.getText().toString()));
			  
			    
			    Random r = new Random();
			    int n;
		       
		        List<Integer> l = daoCommande.listId();
		        do {
		         n = r.nextInt(99999);
		        }while (l.contains(n) == true) ;
				
		        daoCommande.ajouterCommande(new Commande(n,Panier.lb,Panier.prixt,id));
				ChoisirLivre.livreChoisi.clear();
				close();
				ListerCommande lc = new ListerCommande();
				lc.setVisible(true);
		
			}
		});
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBackground(new Color(0, 0, 139));
		btnValider.setBounds(482, 385, 130, 46);
		contentPane.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Panier panier = new Panier();
				panier.setVisible(true);
			}
		});
		
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetour.setBackground(new Color(0, 0, 139));
		btnRetour.setBounds(651, 385, 130, 46);
		contentPane.add(btnRetour);
		
		JLabel lbAjouterCoordonnees = new JLabel("Ajouter vos coordonn\u00E9es");
		lbAjouterCoordonnees.setForeground(new Color(0, 0, 139));
		lbAjouterCoordonnees.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 31));
		lbAjouterCoordonnees.setBounds(341, 35, 670, 48);
		contentPane.add(lbAjouterCoordonnees);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(284, 108, 497, 26);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(284, 162, 497, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(284, 216, 497, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(284, 270, 497, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(284, 324, 497, 26);
		contentPane.add(textField_4);
	}
}