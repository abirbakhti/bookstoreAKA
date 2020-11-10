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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ENTITIES.Book ;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	 public DefaultTableModel loadList() {
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
		  return model = new DefaultTableModel(data, columns);
		   
	   }
	
	public Lister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 10, 472, 353);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//selectioner un ligne du tableau 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				textFieldId.setText(model.getValueAt(i,0).toString());
				textFieldTitre.setText(model.getValueAt(i,1).toString());
				textFieldAuteur.setText(model.getValueAt(i,2).toString());
				textFieldPrix.setText(model.getValueAt(i,3).toString());
				textFieldDate.setText(model.getValueAt(i,4).toString());
			}
		});
		scrollPane.setViewportView(table);
		 
		//appel de la fonction
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
		textFieldDate.setBounds(109, 242, 179, 29);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setBounds(109, 190, 179, 29);
		panel_1.add(textFieldPrix);
		textFieldPrix.setColumns(10);
		
		textFieldAuteur = new JTextField();
		textFieldAuteur.setBounds(109, 139, 179, 29);
		panel_1.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);
		
		textFieldTitre = new JTextField();
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
		btnModifier.setBounds(36, 306, 252, 46);
		panel_1.add(btnModifier);
		
		
		JButton btnSupp = new JButton("Supprimer");
		//supprimer
		btnSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				if(i>=0) {
					dao.deleteBook(Integer.parseInt(model.getValueAt(i, 0).toString()));
					JOptionPane.showMessageDialog(null, "Supprimer avec succees");
				    table.setModel(loadList());
				}else {
					JOptionPane.showMessageDialog(null, "Selectioner un livre");
				}
			}
		});
		btnSupp.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupp.setBackground(new Color(173, 216, 230));
		btnSupp.setForeground(new Color(0, 0, 139));
		btnSupp.setBounds(36, 380, 252, 46);
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
		
		JLabel lblId = new JLabel("Id :");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblId.setBounds(20, 44, 79, 27);
		panel_1.add(lblId);
		//modifier
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.updateBook(Integer.parseInt(textFieldId.getText()), textFieldTitre.getText(), textFieldAuteur.getText(),Double.parseDouble(textFieldPrix.getText()), textFieldDate.getText());
				JOptionPane.showMessageDialog(null, "modifie avec succees");
			    table.setModel(loadList());
			}
		});
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(0, 0, 139));
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRetour.setBounds(630, 387, 155, 46);
		panel.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acceuil acceuil = new Acceuil();
				acceuil.setVisible(true);
			}
		});
	}
}
