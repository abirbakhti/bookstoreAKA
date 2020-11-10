package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class Acceuil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil frame = new Acceuil();
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
	public Acceuil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 809, 456);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 0, 300, 456);
		panel.add(panel_1);
		
		JButton btnAjout = new JButton("Ajouter un livre\r\n");
		btnAjout.setBackground(new Color(0, 0, 139));
		btnAjout.setForeground(Color.WHITE);
		btnAjout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAjout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajouter ajout = new Ajouter();
				ajout.setVisible(true); 
				/*Acceuil frame = new Acceuil();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));*/
			}
		});
		btnAjout.setBounds(426, 214, 252, 47);
		panel.add(btnAjout);
		
		JButton btnList = new JButton("Lister les livres\r\n");
		btnList.setBackground(new Color(0, 0, 139));
		btnList.setForeground(Color.WHITE);
		btnList.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lister list = new Lister();
				list.setVisible(true); 
			}
		});
		btnList.setBounds(426, 321, 252, 47);
		panel.add(btnList);
		
		JLabel lbMenu = new JLabel("Menu ");
		lbMenu.setForeground(new Color(0, 0, 139));
		lbMenu.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 43));
		lbMenu.setBounds(476, 119, 266, 47);
		panel.add(lbMenu);
		
		JLabel lbBienvenue = new JLabel("Bienvenue a notre bookstore");
		lbBienvenue.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 31));
		lbBienvenue.setBounds(344, 23, 425, 47);
		panel.add(lbBienvenue);
	}
}
