package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DaoCommande;
import ENTITIES.Livre;
import ENTITIES.Commande;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListerCommande extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model;
	DefaultTableModel model1;
	private JTable table;
	private JTable table_1;
	private DaoCommande daoCommande ;
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListerCommande frame = new ListerCommande();
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
	
	
	public void loadList(int i ) 
	{
		
		String columns[] = { "ID", "Date" ,"Prix Totale"};
		
		String columns1[] = { "Nom du livre", "Quantite " ,"Prix "};
		
		//Object data[][] = new Object[2][3];
		daoCommande= new DaoCommande();
		List<Commande> listCommandeClient = daoCommande.listCommandeClient();
		
		Object data[][] = new Object[listCommandeClient.size()][3];
		int x = 0;

		for (int j = 0; j < listCommandeClient.size(); j++) {
		
			data[x][0] =  listCommandeClient.get(j).getId();
			data[x][1] = listCommandeClient.get(j).getDateCommande();
			data[x][2] =listCommandeClient.get(j).getPrix();
			x++;
		}
		
		// row selected ==> i 
		//data [i][0]
		//select from command where id_command=" "
		
		
		 int a = (int) data[i][0];
		 HashMap<Livre, Integer> lhm = new HashMap<Livre, Integer>();
		 lhm = daoCommande.listLivresCommande(a);
		 Object data2[][] = new Object[lhm.size()][3];
		 x = 0;

			for (Livre b : lhm.keySet()){
			
				data2[x][0] =  b.getTitle();
				data2[x][1] = lhm.get(b);
				data2[x][2] =b.getPrice();
				x++;
			//	System.out.println(b.getTitle()+" "+lhm.get(b)+" "+b.getPrice());
			}
		 
		 
		
		

		model = new DefaultTableModel(data,columns);
		table.setModel(model);
		model1=  new DefaultTableModel(data2,columns1);
		table_1.setModel(model1);
	}
	
	
	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}
	public ListerCommande() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 811, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Acceuil a = new Acceuil();
				a.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 139));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(624, 381, 139, 51);
		panel.add(btnNewButton);
		
		JButton btnAjouterUneCommande = new JButton("Ajouter une commande");
		btnAjouterUneCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				ChoisirLivre cl = new ChoisirLivre();
				cl.setVisible(true);
			}
		});
		btnAjouterUneCommande.setBackground(new Color(0, 0, 139));
		btnAjouterUneCommande.setForeground(Color.WHITE);
		btnAjouterUneCommande.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouterUneCommande.setBounds(38, 381, 215, 51);
		panel.add(btnAjouterUneCommande);
		
		JLabel lblNewLabel = new JLabel("Mes Commandes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBounds(307, 24, 190, 38);
		panel.add(lblNewLabel);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(444, 83, 0, 0);
		panel.add(scrollPane_1);
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(50, 83, 701, 266);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(UIManager.getFont("Spinner.font"));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				scrollPane.setBounds(50, 83, 362, 266);
				
				scrollPane_1.setBounds(444, 83, 319, 266);
				loadList(i);
			}
		});
		scrollPane.setViewportView(table);
		//load the table
		loadList(0);
		
		
		
		
		
		
	}
}
