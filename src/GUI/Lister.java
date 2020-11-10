package GUI;


import java.awt.AWTEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DaoBook;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ENTITIES.Book ;
import java.util.ArrayList;
import java.util.List;
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
	DefaultTableModel model ;
    private List<Book> listBook = new ArrayList<>();
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
	public Lister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 11, 486, 337);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		 
		  String columns[] = { "ID", "Titre", "Auteur", "Prix", "Date" };
	  
	   
	     
		  List<Book> l = new ArrayList<>();
		  l= dao.listBook();
		  String data[][] = new String[l.size()][5];
		  int x=0;
		  for (int i = 0 ; i < l.size();i++) {
		
			  data[x][0]= String.valueOf( l.get(i).getId());
			  data[x][1]= l.get(i).getTitle();
			  data[x][2]= l.get(i).getAuthor();
			  data[x][3]= String.valueOf(l.get(i).getPrice());	
			  data[x][4]= l.get(i).getReleaseDate();
			  x++; 
			 
			  }
		DefaultTableModel model = new DefaultTableModel(data, columns);
	    table.setModel(model);
	    
		
		JButton btnSupp = new JButton("Supprimer");
		btnSupp.setBounds(61, 353, 89, 23);
		contentPane.add(btnSupp);
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifier.setBounds(61, 325, 89, 23);
		contentPane.add(btnModifier);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(110, 32, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(110, 63, 86, 20);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		textFieldAuteur = new JTextField();
		textFieldAuteur.setBounds(110, 94, 86, 20);
		contentPane.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setBounds(110, 125, 86, 20);
		contentPane.add(textFieldPrix);
		textFieldPrix.setColumns(10);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acceuil acceuil = new Acceuil();
				acceuil.setVisible(true);
			}
		});
		btnRetour.setBounds(61, 381, 89, 23);
		contentPane.add(btnRetour);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(24, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setBounds(24, 66, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Auteur");
		lblNewLabel_2.setBounds(24, 97, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prix");
		lblNewLabel_3.setBounds(24, 128, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(110, 156, 86, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setBounds(24, 159, 46, 14);
		contentPane.add(lblNewLabel_4);
	}
}
