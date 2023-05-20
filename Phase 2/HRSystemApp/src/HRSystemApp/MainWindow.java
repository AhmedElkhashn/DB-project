package HRSystemApp;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField addEmpIDTextField;
	private JTextField addNameTextField;
	private JTextField addBirthdateTextField;
	private JTextField addSalTextField;
	private JTextField addCommTextField;
	private JTextField addPhoneTextField;
	private JTable removeTable;
	private JTextField salTextField;
	private JTable salTable;
	private JTable empAllowanceTable;
	private JTable allowanceTable;
	private JTable reportsTable;
	private JComboBox addGendersComboBox;
	private JComboBox addPosComboBox;
	private JComboBox addDnoComboBox;
	private JComboBox reportsComboBox;
	private List<JTextField> allTextFields = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() throws SQLException {
		



		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 814, 379);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Employee", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emp_ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gender:");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		panel.add(lblNewLabel_2);
		
		addEmpIDTextField = new JTextField();
		addEmpIDTextField.setBounds(90, 8, 120, 20);
		panel.add(addEmpIDTextField);
		addEmpIDTextField.setColumns(10);
		
		addNameTextField = new JTextField();
		addNameTextField.setBounds(90, 33, 120, 20);
		panel.add(addNameTextField);
		addNameTextField.setColumns(10);
		
		addGendersComboBox = new JComboBox();
		addGendersComboBox.setBounds(90, 57, 120, 22);
		panel.add(addGendersComboBox);
		addGendersComboBox.addItem("M");
		addGendersComboBox.addItem("F");
		addGendersComboBox.setSelectedIndex(-1);
		
		JLabel lblNewLabel_3 = new JLabel("Birthdate:");
		lblNewLabel_3.setBounds(10, 86, 70, 14);
		panel.add(lblNewLabel_3);
		
		addBirthdateTextField = new JTextField();
		addBirthdateTextField.setBounds(90, 83, 120, 20);
		panel.add(addBirthdateTextField);
		addBirthdateTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Salary:");
		lblNewLabel_4.setBounds(10, 111, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Commission:");
		lblNewLabel_5.setBounds(10, 136, 77, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Position:");
		lblNewLabel_6.setBounds(10, 186, 70, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("DeptNo:");
		lblNewLabel_7.setBounds(10, 211, 46, 14);
		panel.add(lblNewLabel_7);
		
		addSalTextField = new JTextField();
		addSalTextField.setBounds(90, 108, 120, 20);
		panel.add(addSalTextField);
		addSalTextField.setColumns(10);
		
		addCommTextField = new JTextField();
		addCommTextField.setBounds(90, 133, 120, 20);
		panel.add(addCommTextField);
		addCommTextField.setColumns(10);
		
		addPosComboBox = new JComboBox();
		addPosComboBox.setBounds(90, 182, 120, 22);
		panel.add(addPosComboBox);
		
		
		addDnoComboBox = new JComboBox();
		addDnoComboBox.setBounds(90, 207, 120, 22);
		panel.add(addDnoComboBox);
		
		JButton addEmpButton = new JButton("Add");
		addEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utility utility =new Utility();
					utility.addEmp(addEmpIDTextField.getText(),addNameTextField.getText(),addGendersComboBox.getSelectedItem().toString(), addBirthdateTextField.getText(), addSalTextField.getText(), addCommTextField.getText(), addPhoneTextField.getText(), addPosComboBox.getSelectedItem().toString(), addDnoComboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Transaction is Successfull");
					utility.terminate();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Transaction failed");
					
				}
				try {
					refresh();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addEmpButton.setBounds(710, 317, 89, 23);
		panel.add(addEmpButton);
		
		JLabel lblNewLabel_8 = new JLabel("Please use DD-MON-YYYY format");
		lblNewLabel_8.setForeground(new Color(129, 129, 129));
		lblNewLabel_8.setBounds(220, 86, 201, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Phone #:");
		lblNewLabel_9.setBounds(10, 161, 70, 14);
		panel.add(lblNewLabel_9);
		
		addPhoneTextField = new JTextField();
		addPhoneTextField.setBounds(90, 158, 120, 20);
		panel.add(addPhoneTextField);
		addPhoneTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Remove Employee", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 789, 281);
		panel_1.add(scrollPane);
		
		
		
		JLabel removeLabel = new JLabel("No Employee selected");
		removeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeLabel.setBounds(254, 303, 313, 14);
		panel_1.add(removeLabel);
		
		removeTable = new JTable();
		removeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id =  removeTable.getValueAt(removeTable.getSelectedRow(),0).toString();
				removeLabel.setText("Employee with ID "+id+" Selected");
			}
		});
		scrollPane.setViewportView(removeTable);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					Utility utility =new Utility();
					String id =  removeTable.getValueAt(removeTable.getSelectedRow(),0).toString();					
					utility.removeEmp(id);
					JOptionPane.showMessageDialog(null, "Transaction is Successfull");
					utility.terminate();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Transaction failed");
				}
				try {
					refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		removeButton.setBounds(710, 317, 89, 23);
		panel_1.add(removeButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Update Salary", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("New Salary :");
		lblNewLabel_10.setBounds(10, 11, 71, 14);
		panel_2.add(lblNewLabel_10);
		
		salTextField = new JTextField();
		salTextField.setBounds(83, 8, 86, 20);
		panel_2.add(salTextField);
		salTextField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 789, 257);
		panel_2.add(scrollPane_1);
		
		JLabel salLabel = new JLabel("No Employee Selected");
		salLabel.setHorizontalAlignment(SwingConstants.CENTER);
		salLabel.setBounds(271, 311, 234, 14);
		panel_2.add(salLabel);
		
		salTable = new JTable();
		salTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id =  salTable.getValueAt(salTable.getSelectedRow(),0).toString();
				salLabel.setText("Employee with ID "+id+" Selected");

			}
		});
		scrollPane_1.setViewportView(salTable);
		
		JButton salButton = new JButton("Update");
		salButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Utility utility =new Utility();
					String id =  salTable.getValueAt(salTable.getSelectedRow(),0).toString();				
					utility.updateSal(id,salTextField.getText());
					JOptionPane.showMessageDialog(null, "Transaction is Successfull");
					utility.terminate();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Transaction failed");
				}
				try {
					refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		salButton.setBounds(710, 317, 89, 23);
		panel_2.add(salButton);
				
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("View Allowance", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 553, 303);
		panel_3.add(scrollPane_2);
		
		JLabel allowanceLabel = new JLabel("No Employee Selected");
		allowanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allowanceLabel.setBounds(223, 326, 340, 14);
		panel_3.add(allowanceLabel);
		
		empAllowanceTable = new JTable();
		empAllowanceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Utility utility =new Utility();
					
					String pos =  empAllowanceTable.getValueAt(empAllowanceTable.getSelectedRow(),3).toString();
					String id =  empAllowanceTable.getValueAt(empAllowanceTable.getSelectedRow(),0).toString();
					allowanceLabel.setText("Employee with ID "+id+" Selected");
					allowanceTable.setModel(DbUtils.resultSetToTableModel(utility.getAllowance(pos)));
					utility.terminate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		scrollPane_2.setViewportView(empAllowanceTable);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(573, 11, 226, 303);
		panel_3.add(scrollPane_3);
		
		allowanceTable = new JTable();
		scrollPane_3.setViewportView(allowanceTable);
		
		
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Reports", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("Reports:");
		lblNewLabel_12.setBounds(10, 11, 54, 14);
		panel_4.add(lblNewLabel_12);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 42, 789, 298);
		panel_4.add(scrollPane_4);
		
		reportsTable = new JTable();
		reportsTable.setRowSelectionAllowed(false);
		scrollPane_4.setViewportView(reportsTable);
		
		reportsComboBox = new JComboBox();
		reportsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadReport();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		reportsComboBox.setBounds(74, 7, 136, 22);
		panel_4.add(reportsComboBox);
		reportsComboBox.addItem("Employee History");
		reportsComboBox.addItem("Vehicle History");
		reportsComboBox.addItem("Project History");
		reportsComboBox.addItem("Overtime History");
		

		allTextFields.add(addEmpIDTextField);
		allTextFields.add(addNameTextField);
		allTextFields.add(addBirthdateTextField);
		allTextFields.add(addSalTextField);
		allTextFields.add(addCommTextField);
		allTextFields.add(addPhoneTextField);
		allTextFields.add(salTextField);
		
		loadComboBoxes();
		loadTables();

	}
	
	public void loadTables() throws SQLException {
		Utility utility =new Utility();
		removeTable.setModel(DbUtils.resultSetToTableModel(utility.loadEmp()));
		salTable.setModel(DbUtils.resultSetToTableModel(utility.loadEmpSal()));
		empAllowanceTable.setModel(DbUtils.resultSetToTableModel(utility.loadEmpSal()));
		allowanceTable.setModel(DbUtils.resultSetToTableModel(utility.clearAllowance()));
		utility.terminate();
	}
	
	public void loadComboBoxes() throws SQLException {
		Utility utility =new Utility();
		ArrayList<String> positions=utility.getPositions();
		for (int i=0;i<positions.size();i++) {
			addPosComboBox.addItem(positions.get(i));
		}
		ArrayList<Integer> dnumbers=utility.getDno();
		for (int i=0;i<dnumbers.size();i++) {
			addDnoComboBox.addItem(dnumbers.get(i));
		}
		addDnoComboBox.setSelectedIndex(-1);
		addPosComboBox.setSelectedIndex(-1);		
		utility.terminate();
	}
	

	public void loadReport() throws SQLException{
		Utility utility =new Utility();
		String report = reportsComboBox.getSelectedItem().toString();
		if (report=="Employee History") {
			reportsTable.setModel(DbUtils.resultSetToTableModel(utility.loadEmpReport()));
		}else if(report=="Vehicle History") {
			reportsTable.setModel(DbUtils.resultSetToTableModel(utility.loadVehicleReport()));
		}else if(report=="Project History") {
			reportsTable.setModel(DbUtils.resultSetToTableModel(utility.loadProjectReport()));
		}else if(report=="Overtime History") {
			reportsTable.setModel(DbUtils.resultSetToTableModel(utility.loadOvertimeReport()));
		}
		utility.terminate();

	}
	
	public void refresh() throws SQLException {
		clr();
		loadTables();
		
	}
	
	public void clr() {
		for(JTextField tf:allTextFields) {
			tf.setText("");
		}
		removeTable.getSelectionModel().clearSelection();
		salTable.getSelectionModel().clearSelection();
		allowanceTable.getSelectionModel().clearSelection();
		addGendersComboBox.setSelectedIndex(-1);
		addPosComboBox.setSelectedIndex(-1);
		addDnoComboBox.setSelectedIndex(-1);
		reportsComboBox.setSelectedIndex(0);
		
	}
	
}
