package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;



import DAO.DaoBook;
import ENTITIES.Book;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
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
	private  String s ;

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
		lbTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTitre.setBounds(60, 110, 166, 26);
		panel_1.add(lbTitre);

		JLabel lbAuteur = new JLabel("Auteur :");
		lbAuteur.setForeground(Color.WHITE);
		lbAuteur.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbAuteur.setBounds(60, 163, 166, 26);
		panel_1.add(lbAuteur);

		JLabel lbPrix = new JLabel("Prix :");
		lbPrix.setForeground(Color.WHITE);
		lbPrix.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbPrix.setBounds(60, 216, 166, 26);
		panel_1.add(lbPrix);

		JLabel lbDate = new JLabel("Date (yyyy-mm-jj) :");
		lbDate.setForeground(Color.WHITE);
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbDate.setBounds(60, 270, 166, 26);
		panel_1.add(lbDate);
		
		JLabel lblImage = new JLabel("Image :");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblImage.setBounds(60, 319, 166, 26);
		panel_1.add(lblImage);
		JLabel lblImage2 = new JLabel("");
		lblImage2.setBounds(530, 213, 238, 131);
		panel.add(lblImage2);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldTitre.getText().equals("") && !textFieldAuteur.getText().equals("")
						&& !textFieldPrix.getText().equals("") && !textFieldDate.getText().equals("")) {
					Book book = new Book(0, textFieldTitre.getText(), textFieldAuteur.getText(),
							Double.parseDouble(textFieldPrix.getText()), textFieldDate.getText(),null);

					if (dao.addBook(book,s)) {
						JOptionPane.showMessageDialog(null, "Livre ajouté avec succès");
						textFieldTitre.setText("");
						textFieldAuteur.setText("");
						textFieldPrix.setText("");
						textFieldDate.setText("");
						lblImage2.setIcon(null);
					} else {
						JOptionPane.showMessageDialog(null, "Vérfifier les données");
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
		textFieldTitre.setBounds(271, 108, 497, 26);
		panel.add(textFieldTitre);

		textFieldAuteur = new JTextField();
		textFieldAuteur.setColumns(10);
		textFieldAuteur.setBounds(271, 162, 497, 26);
		panel.add(textFieldAuteur);

		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(271, 214, 249, 26);
		panel.add(textFieldPrix);

		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(271, 268, 249, 26);
		panel.add(textFieldDate);

		
		JLabel lbAjouterUnLivre = new JLabel("Ajouter Un Livre");
		lbAjouterUnLivre.setForeground(new Color(0, 0, 139));
		lbAjouterUnLivre.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 31));
		lbAjouterUnLivre.setBounds(400, 49, 266, 33);
		panel.add(lbAjouterUnLivre);
		
		JButton btnNewButton = new JButton("Choisissez une image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setCurrentDirectory(new File(""));
				 FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE","jpg","png","gif");
				 fileChooser.addChoosableFileFilter(filter);
				 int result = fileChooser.showSaveDialog(null);
				 if (result== JFileChooser.APPROVE_OPTION) {
					 File selectedFile =  fileChooser.getSelectedFile();
					 String path = selectedFile.getAbsolutePath();
					 ImageIcon myImage = new ImageIcon(path);
					 java.awt.Image img = myImage.getImage();
					 java.awt.Image newImage = img.getScaledInstance(lblImage2   .getWidth(), lblImage2 .getHeight(),  java.awt.Image.SCALE_SMOOTH);
					 ImageIcon finalImg = new ImageIcon(newImage);
					 lblImage2  .setIcon(finalImg);
					 s=path ;
				 }else 
				 {
					 if (result== JFileChooser.CANCEL_OPTION)
						 JOptionPane.showMessageDialog(null, "T'as rien choisi");
				 }
			}
		});
		btnNewButton.setBounds(271, 321, 249, 23);
		panel.add(btnNewButton);
		
		
	}
}