package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DaoBook;
import ENTITIES.Book;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ChoisirLivre extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	private List<Book> livreChoisi = new ArrayList<>();

	private DaoBook dao = new DaoBook();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoisirLivre frame = new ChoisirLivre();
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
	public DefaultTableModel loadList() {
		String columns[] = { "ID", "Titre", "Auteur", "Prix", "Date", "Check" };
		List<Book> l = new ArrayList<>();
		l = dao.listBook();
		Object data[][] = new Object[l.size()][7];
		int x = 0;

		for (int i = 0; i < l.size(); i++) {

			data[x][0] = String.valueOf(l.get(i).getId());
			data[x][1] = l.get(i).getTitle();
			data[x][2] = l.get(i).getAuthor();
			data[x][3] = String.valueOf(l.get(i).getPrice());
			data[x][4] = l.get(i).getReleaseDate();
			data[x][5] = (boolean) false;

			/*
			 * if (l.get(i).getImage() != null) {
			 * 
			 * ImageIcon image = new ImageIcon(new
			 * ImageIcon(l.get(i).getImage()).getImage().getScaledInstance(20, 20,
			 * Image.SCALE_SMOOTH)); data[x][5] =image;
			 * 
			 * 
			 * }else {data[x][5] =null;}
			 * 
			 */
			x++;
		}

		return model = new DefaultTableModel(data, columns) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

		};
	}

	public ChoisirLivre() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 811, 458);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 99, 748, 245);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(loadList());
		scrollPane.setViewportView(table);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(0, 0, 139));
		btnRetour.setBounds(29, 383, 143, 53);
		panel.add(btnRetour);

		JButton btnPanier = new JButton("Panier");
		btnPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 5).toString());
					if (checked) {
						Book b = new Book(Integer.parseInt(table.getValueAt(i, 0).toString()),
								table.getValueAt(i, 1).toString(), null,
								Double.parseDouble(table.getValueAt(i, 3).toString()), null, null);
						livreChoisi.add(b);
					}
				}
				close();
				Panier p = new Panier();
				p.setVisible(true);
			}
		});
		btnPanier.setForeground(Color.WHITE);
		btnPanier.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPanier.setBackground(new Color(0, 0, 139));
		btnPanier.setBounds(634, 383, 143, 53);
		panel.add(btnPanier);

		JLabel lblNewLabel = new JLabel("Choisir les livres ");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(304, 26, 203, 38);
		panel.add(lblNewLabel);
	}
}
