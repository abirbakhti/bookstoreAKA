package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;



import DAO.DaoBook;
import ENTITIES.Book;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Ajouter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DaoBook dao = new DaoBook();
	private JTextField textFieldTitre;
	private JTextField textFieldAuteur;
	private JTextField textFieldPrix;
	private JTextField textFieldDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajouter frame = new Ajouter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}
	public Ajouter() {
		setTitle("Ajouter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 811, 458);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 139));
		panel_1.setBounds(0, 0, 226, 458);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lbTitre = new JLabel("Titre :");
		lbTitre.setForeground(Color.WHITE);
		lbTitre.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTitre.setBounds(60, 158, 166, 26);
		panel_1.add(lbTitre);

		JLabel lbAuteur = new JLabel("Auteur :");
		lbAuteur.setForeground(Color.WHITE);
		lbAuteur.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbAuteur.setBounds(60, 210, 166, 26);
		panel_1.add(lbAuteur);

		JLabel lbPrix = new JLabel("Prix :");
		lbPrix.setForeground(Color.WHITE);
		lbPrix.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbPrix.setBounds(60, 263, 166, 26);
		panel_1.add(lbPrix);

		JLabel lbDate = new JLabel("Date :");
		lbDate.setForeground(Color.WHITE);
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbDate.setBounds(60, 317, 166, 26);
		panel_1.add(lbDate);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldTitre.getText().equals("") && !textFieldAuteur.getText().equals("")
						&& !textFieldPrix.getText().equals("") && !textFieldDate.getText().equals("")) {
					Book book = new Book(0, textFieldTitre.getText(), textFieldAuteur.getText(),
							Double.parseDouble(textFieldPrix.getText()), textFieldDate.getText());

					if (dao.addBook(book)) {
						JOptionPane.showMessageDialog(null, "Livre a �t� ajout� avec succ�s");
						textFieldTitre.setText("");
						textFieldAuteur.setText("");
						textFieldPrix.setText("");
						textFieldDate.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Verfifier les donnees");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs");
				}

			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setBackground(new Color(0, 0, 139));
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBounds(482, 385, 130, 46);
		panel.add(btnAjouter);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Acceuil acceuil = new Acceuil();
				acceuil.setVisible(true);
				
			}
		});
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(0, 0, 139));
		btnRetour.setBounds(651, 385, 130, 46);
		panel.add(btnRetour);

		textFieldTitre = new JTextField();
		textFieldTitre.setColumns(10);
		textFieldTitre.setBounds(266, 157, 497, 26);
		panel.add(textFieldTitre);

		textFieldAuteur = new JTextField();
		textFieldAuteur.setColumns(10);
		textFieldAuteur.setBounds(266, 211, 497, 26);
		panel.add(textFieldAuteur);

		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(266, 263, 497, 26);
		panel.add(textFieldPrix);

		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(266, 317, 497, 26);
		panel.add(textFieldDate);

		JLabel lbAjouterUnLivre = new JLabel("Ajouter Un Livre ");
		lbAjouterUnLivre.setForeground(new Color(0, 0, 139));
		lbAjouterUnLivre.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 31));
		lbAjouterUnLivre.setBounds(383, 37, 266, 26);
		panel.add(lbAjouterUnLivre);
	}
}
