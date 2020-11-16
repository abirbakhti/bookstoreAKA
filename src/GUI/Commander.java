package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Color;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Commander extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commander frame = new Commander();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DefaultTableModel loadList() {
		String columns[] = {"Livre", "Quantité", "Prix", "Date commande"};
		Object[][] data = {
			    {"Test", 1, 100.0, "2020-11-16"},
			    {"Java", 1, 200.0, "2020-11-16"},
			    {"OCA", 1, 300.0, "2020-11-16"},
			};
		return model = new DefaultTableModel(data, columns);
	}
	
	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}
	
	/**
	 * Create the frame.
	 */
	public Commander() {
		setTitle("Commander");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(651, 385, 130, 46);
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetour.setBackground(new Color(0, 0, 139));
		contentPane.add(btnRetour);
		
		JButton btnCommander = new JButton("Commander");
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				FormulaireValidation formVal = new FormulaireValidation();
				formVal.setVisible(true);
			}
		});
		btnCommander.setBounds(482, 385, 130, 46);
		btnCommander.setForeground(Color.WHITE);
		btnCommander.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCommander.setBackground(new Color(0, 0, 139));
		contentPane.add(btnCommander);
		
		JLabel lblVrifierVotreCommande = new JLabel("V\u00E9rifier votre commande");
		lblVrifierVotreCommande.setBounds(244, 31, 368, 48);
		lblVrifierVotreCommande.setForeground(new Color(0, 0, 139));
		lblVrifierVotreCommande.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 31));
		contentPane.add(lblVrifierVotreCommande);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 107, 400, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(loadList());
		
		JLabel lbPrixTotal = new JLabel("Prix total :");
		lbPrixTotal.setBounds(482, 208, 100, 26);
		contentPane.add(lbPrixTotal);
		lbPrixTotal.setForeground(new Color(0, 0, 139));
		lbPrixTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setBounds(613, 210, 168, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnRetirer = new JButton("Retirer");
		btnRetirer.setBounds(565, 285, 130, 46);
		contentPane.add(btnRetirer);
		btnRetirer.setForeground(Color.WHITE);
		btnRetirer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetirer.setBackground(new Color(0, 0, 139));
	}
}