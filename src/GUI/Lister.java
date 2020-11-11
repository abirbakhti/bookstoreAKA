package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DaoBook;
import DAO.Singleton;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ENTITIES.Book;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Lister extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldTitre;
	private JTextField textFieldAuteur;
	private JTextField textFieldPrix;
	private JTextField textFieldDate;
	private JLabel test;
	DefaultTableModel model;

	private DaoBook dao = new DaoBook();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lister frame = new Lister();
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
    
	public DefaultTableModel loadList() {
		String columns[] = { "ID", "Titre", "Auteur", "Prix", "Date", "Image" };
		List<Book> l = new ArrayList<>();
		l = dao.listBook();
		Object data[][] = new Object[l.size()][6];
		int x = 0;
		ImageIcon image;
		for (int i = 0; i < l.size(); i++) {
			data[x][0] = String.valueOf(l.get(i).getId());
			data[x][1] = l.get(i).getTitle();
			data[x][2] = l.get(i).getAuthor();
			data[x][3] = String.valueOf(l.get(i).getPrice());
			data[x][4] = l.get(i).getReleaseDate();
			image = new ImageIcon(l.get(i).getImage());
			data[x][5] =image;
			
			x++;
		}
		return model = new DefaultTableModel(data, columns);
	}

	public Lister() {
		setTitle("Lister");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 10, 472, 353);
		contentPane.add(scrollPane);

	

		table = new JTable();
		// selectioner une ligne du tableau
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();
				textFieldId.setText(model.getValueAt(i, 0).toString());
				textFieldTitre.setText(model.getValueAt(i, 1).toString());
				textFieldAuteur.setText(model.getValueAt(i, 2).toString());
				textFieldPrix.setText(model.getValueAt(i, 3).toString());
				textFieldDate.setText(model.getValueAt(i, 4).toString());
				textFieldTitre.setEnabled(true);
				textFieldAuteur.setEnabled(true);
				textFieldPrix.setEnabled(true);
				textFieldDate.setEnabled(true);
/*
				byte[] img = (byte[]) model.getValueAt(i, 5);

				// String stringValue = "" + img + "]";

				// img = stringValue.getBytes();
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				Image myImage = im.getScaledInstance(test.getWidth(), test.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImage);
				test.setIcon(image);

				ImageIcon icon = new ImageIcon(model.getValueAt(i, 5).toString());
				test.setIcon(icon);*/
			}
		});
		scrollPane.setViewportView(table);

		// appel de la fonction
		table.setModel(loadList());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 811, 458);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 139));
		panel_1.setBounds(0, 0, 316, 458);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textFieldDate = new JTextField();
		textFieldDate.setEnabled(false);
		textFieldDate.setBounds(109, 242, 179, 29);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);

		textFieldPrix = new JTextField();
		textFieldPrix.setEnabled(false);
		textFieldPrix.setBounds(109, 190, 179, 29);
		panel_1.add(textFieldPrix);
		textFieldPrix.setColumns(10);

		textFieldAuteur = new JTextField();
		textFieldAuteur.setEnabled(false);
		textFieldAuteur.setBounds(109, 139, 179, 29);
		panel_1.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);

		textFieldTitre = new JTextField();
		textFieldTitre.setEnabled(false);
		textFieldTitre.setBounds(109, 93, 179, 29);
		panel_1.add(textFieldTitre);
		textFieldTitre.setColumns(10);

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setBounds(109, 46, 179, 29);
		panel_1.add(textFieldId);
		textFieldId.setColumns(10);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnModifier.setBackground(new Color(173, 216, 230));
		btnModifier.setForeground(new Color(0, 0, 139));
		btnModifier.setBounds(75, 319, 213, 46);
		panel_1.add(btnModifier);

		JButton btnSupp = new JButton("Supprimer");
		// supprimer
		btnSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();

				if (i >= 0) {
					int y = JOptionPane.showConfirmDialog(null, "Vous voullez suppimer ce livre !");
					if (y == 0) {
						if (dao.deleteBook(Integer.parseInt(model.getValueAt(i, 0).toString()))) {

							JOptionPane.showMessageDialog(null, "Liver supprimé avec succès");
							table.setModel(loadList());
							textFieldId.setText("");
							textFieldTitre.setText("");
							textFieldAuteur.setText("");
							textFieldPrix.setText("");
							textFieldDate.setText("");
							textFieldTitre.setEnabled(false);
							textFieldAuteur.setEnabled(false);
							textFieldPrix.setEnabled(false);
							textFieldDate.setEnabled(false);
						} else
							JOptionPane.showMessageDialog(null, "Erreur du serveur");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Sélectionner un livre à supprimer");
				}
			}
		});
		btnSupp.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupp.setBackground(new Color(173, 216, 230));
		btnSupp.setForeground(new Color(0, 0, 139));
		btnSupp.setBounds(75, 387, 213, 46);
		panel_1.add(btnSupp);

		JLabel lblTitre = new JLabel("Titre : ");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitre.setBounds(20, 95, 79, 27);
		panel_1.add(lblTitre);

		JLabel lblAuteur = new JLabel("Auteur : ");
		lblAuteur.setForeground(Color.WHITE);
		lblAuteur.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAuteur.setBounds(20, 139, 79, 27);
		panel_1.add(lblAuteur);

		JLabel lblPrix = new JLabel("Prix : ");
		lblPrix.setForeground(Color.WHITE);
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrix.setBounds(20, 192, 79, 27);
		panel_1.add(lblPrix);

		JLabel lblDate = new JLabel("Date : ");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDate.setBounds(20, 242, 79, 27);
		panel_1.add(lblDate);

		JLabel lblId = new JLabel("ID :");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblId.setBounds(20, 44, 79, 27);
		panel_1.add(lblId);

		JLabel lbModifier = new JLabel("");
		lbModifier.setForeground(Color.WHITE);
		lbModifier.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbModifier.setBounds(20, 305, 45, 71);
		panel_1.add(lbModifier);
		Image iconModifier = new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		lbModifier.setIcon(new ImageIcon(iconModifier));

		JLabel lbSupprimer = new JLabel("");
		lbSupprimer.setForeground(Color.WHITE);
		lbSupprimer.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbSupprimer.setBounds(20, 376, 45, 71);
		panel_1.add(lbSupprimer);
		Image iconSupp = new ImageIcon(this.getClass().getResource("/iconSupp.png")).getImage();
		lbSupprimer.setIcon(new ImageIcon(iconSupp));

		// modifier
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldTitre.getText().equals("") && !textFieldAuteur.getText().equals("")
						&& !textFieldPrix.getText().equals("") && !textFieldDate.getText().equals("")) {
					Book book = new Book(Integer.parseInt(textFieldId.getText()), textFieldTitre.getText(),
							textFieldAuteur.getText(), Double.parseDouble(textFieldPrix.getText()),
							textFieldDate.getText(), null);
					int yy = JOptionPane.showConfirmDialog(null, "Vous voullez modifier ce livre !");
					if (yy == 0) {
						if (dao.updateBook(book)) {
							JOptionPane.showMessageDialog(null, "Livre modifié avec succès");
							textFieldId.setText("");
							textFieldTitre.setText("");
							textFieldAuteur.setText("");
							textFieldPrix.setText("");
							textFieldDate.setText("");
							textFieldTitre.setEnabled(false);
							textFieldAuteur.setEnabled(false);
							textFieldPrix.setEnabled(false);
							textFieldDate.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "Erreur de saisie");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Sélectionner un livre à modifier");
				}
				table.setModel(loadList());
			}
		});

		JButton btnRetour = new JButton("Retour");
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(0, 0, 139));
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRetour.setBounds(588, 388, 213, 46);
		panel.add(btnRetour);

		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Acceuil acceuil = new Acceuil();
				acceuil.setVisible(true);
			}
		});
	}
}