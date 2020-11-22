package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ENTITIES.Livre;


import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Panier extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel model;
	private JTable table;
	private JTextField textField= new JTextField();
	private JButton buttonmoin = new JButton();
	private JButton buttonplus = new JButton();
	private JButton buttonEffacer = new JButton();
	public static Double prixt = 0.0;
	public static HashMap<Livre, Integer> lb = new HashMap<Livre, Integer>();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panier frame = new Panier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DefaultTableModel loadList() {

		String columns[] = {"Id","Livre", "Prix", "Quantité", "+", "-" ,"Effacer"};

		Object data[][] = new Object[ChoisirLivre.livreChoisi.size()][4];

		int x = 0;
		prixt=0.0;
		for (int j = 0; j < ChoisirLivre.livreChoisi.size(); j++) {
			data[x][0] = ChoisirLivre.livreChoisi.get(j).getId();
			data[x][1] = ChoisirLivre.livreChoisi.get(j).getTitre();
			data[x][2] = ChoisirLivre.livreChoisi.get(j).getPrix();
			data[x][3] = 1;
			
			prixt = prixt + ((Double) data[x][2] * (int) data[x][3]);
			x++;

		}

		textField.setText(String.valueOf(prixt));
		return model = new DefaultTableModel(data, columns);
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * Create the frame.
	 */
	// button Remove
		class ButtonRenderer3 extends JButton implements TableCellRenderer {
			public ButtonRenderer3() {
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				setText((value == null) ? " Effacer " : value.toString());
				return this;
			}
		}

		class ButtonEditor3 extends DefaultCellEditor {
			private String label;

			public ButtonEditor3(JCheckBox checkBox) {
				super(checkBox);
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				label = (value == null) ? " Effacer " : value.toString();
				buttonEffacer.setText(label);
				return buttonEffacer;
			}

			public Object getCellEditorValue() {
				return new String(label);
			}
		}
		// end button
	
	// button +
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value == null) ? "+" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {
		private String label;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "+" : value.toString();
			buttonplus.setText(label);
		
			return buttonplus;
		}

		public Object getCellEditorValue() {
			return new String(label);
		}
	}
	// end button

	// button -
	class ButtonRenderer1 extends JButton implements TableCellRenderer {
		public ButtonRenderer1() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value == null) ? "-" : value.toString());
			return this;
		}
	}

	class ButtonEditor1 extends DefaultCellEditor {
		private String label;

		public ButtonEditor1(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "-" : value.toString();
			buttonmoin.setText(label);
			Color  red  = new Color(255, 0, 0);
			buttonmoin.setBackground(red);
			return buttonmoin;
		}

		public Object getCellEditorValue() {
			return new String(label);
		}
	}

	// end button
	public Panier() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\karim\\Downloads\\l3.jpg"));
		
		
		
		setTitle("Panier");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				ChoisirLivre cl = new ChoisirLivre();
				cl.setVisible(true);
			}
		});
		btnRetour.setBounds(613, 385, 168, 46);
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetour.setBackground(new Color(0, 0, 139));
		contentPane.add(btnRetour);

		JButton btnCommander = new JButton("Commander");
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0 ;  i < table.getRowCount() ; i++)
				{
					Livre b = new Livre(Integer.parseInt(table.getValueAt(i, 0).toString()),table.getValueAt(i, 1).toString() ,null,0,null,null);
					int qts = Integer.parseInt(table.getValueAt(i, 3).toString());
					lb.put(b, qts);
				}
				
				
				close();
				FormulaireValidation formVal = new FormulaireValidation();
				formVal.setVisible(true);
				
			}
		});
		btnCommander.setBounds(613, 313, 168, 46);
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
		scrollPane.setBounds(25, 107, 554, 324);
		contentPane.add(scrollPane);

		table = new JTable();

		// load table
		scrollPane.setViewportView(table);
		table.setModel(loadList());
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		
		table.getColumn("+").setCellRenderer(new ButtonRenderer());
		table.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox()));
		Color  green   = new Color(0, 255,  0);
		buttonplus.setBackground(green);
		table.getColumn("-").setCellRenderer(new ButtonRenderer1());
		table.getColumn("-").setCellEditor(new ButtonEditor1(new JCheckBox()));
		
		table.getColumn("Effacer").setCellRenderer(new ButtonRenderer3());
		table.getColumn("Effacer").setCellEditor(new ButtonEditor3(new JCheckBox()));
		
		//
		JLabel lbPrixTotal = new JLabel("Prix total :");
		lbPrixTotal.setBounds(613, 107, 100, 26);
		contentPane.add(lbPrixTotal);
		lbPrixTotal.setForeground(new Color(0, 0, 139));
		lbPrixTotal.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField.setBounds(613, 144, 168, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		// action method Remove
		buttonEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = table.getSelectedRow();
				boolean test = false ;
				int j = 0 ;
				while (j < ChoisirLivre.livreChoisi.size() && test == false) {
					if(ChoisirLivre.livreChoisi.get(i).getId()==Integer.parseInt(table.getValueAt(i, 0).toString())) {
						
							ChoisirLivre.livreChoisi.remove(i);
						test=true;
						}
						else 
							j++;
						
				}
			/*	for(Livre b : ChoisirLivre.livreChoisi)
				{
					if(b.getId()==Integer.parseInt(table.getValueAt(i, 0).toString())) {
						ChoisirLivre.livreChoisi.remove(b);
						table.setModel(loadList());
						table.getColumn("+").setCellRenderer(new ButtonRenderer());
						table.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox()));

						table.getColumn("-").setCellRenderer(new ButtonRenderer1());
						table.getColumn("-").setCellEditor(new ButtonEditor1(new JCheckBox()));
						
						table.getColumn("Effacer").setCellRenderer(new ButtonRenderer3());
						table.getColumn("Effacer").setCellEditor(new ButtonEditor3(new JCheckBox()));
					}*/
					table.setModel(loadList());
					table.getColumn("+").setCellRenderer(new ButtonRenderer());
					table.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox()));

					table.getColumn("-").setCellRenderer(new ButtonRenderer1());
					table.getColumn("-").setCellEditor(new ButtonEditor1(new JCheckBox()));
					
					table.getColumn("Effacer").setCellRenderer(new ButtonRenderer3());
					table.getColumn("Effacer").setCellEditor(new ButtonEditor3(new JCheckBox()));	
				
			}
		});
		
		
		// action method +
		buttonplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = table.getSelectedRow();
				table.setValueAt(Integer.parseInt(table.getValueAt(i, 3).toString()) + 1, i, 3);
				prixt = prixt + Double.parseDouble(table.getValueAt(i, 2).toString());
				textField.setText(String.valueOf(prixt));
				// JOptionPane.showMessageDialog(null,"plus");
			}
		});
		
		// action method -
		buttonmoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = table.getSelectedRow();
				if(Integer.parseInt(table.getValueAt(i, 3).toString())>1) {
					table.setValueAt(Integer.parseInt(table.getValueAt(i, 3).toString()) - 1, i, 3);
					prixt = prixt - Double.parseDouble(table.getValueAt(i, 2).toString());
					textField.setText(String.valueOf(prixt));
				}
				else {
					JOptionPane.showMessageDialog(null, "Quantité invalide ! ");
				}
				//JOptionPane.showMessageDialog(null, "moin");
			}
		});
		

	}
}