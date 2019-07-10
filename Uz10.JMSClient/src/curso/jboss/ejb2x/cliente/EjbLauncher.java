package curso.jboss.ejb2x.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import curso.jboss.jms.cliente.EnviarAColaJMS11;

public class EjbLauncher {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtMensaje;
	private JTextPane txtResult;
	private JLabel lblNumeroDeElementos;
	private JComboBox<String> cbNumeroElementos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjbLauncher window = new EjbLauncher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EjbLauncher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		frame.getContentPane().add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText("Ruben");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(txtNombre, gbc_textField);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 0;
		gbc_lblDescripcin.gridy = 2;
		frame.getContentPane().add(lblDescripcin, gbc_lblDescripcin);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtResult.setText("");
				int veces = Integer.parseInt(cbNumeroElementos.getSelectedItem().toString());
				for (int i = 0; i < veces;i++){
				String result = null;
				try {
					
					result = EnviarAColaJMS11.sendMessage(txtNombre.getText(), txtMensaje.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					result = e1.getMessage();
				}
				
				txtResult.setText(txtResult.getText() + "\n" + result);
				
			}
				
			}
		}
			
				);
		
		txtMensaje = new JTextField();
		txtMensaje.setText("Portatil Asus");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(txtMensaje, gbc_textField_1);
		txtMensaje.setColumns(10);
		
		lblNumeroDeElementos = new JLabel("Numero de mensajes");
		GridBagConstraints gbc_lblNumeroDeElementos = new GridBagConstraints();
		gbc_lblNumeroDeElementos.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumeroDeElementos.gridx = 0;
		gbc_lblNumeroDeElementos.gridy = 4;
		frame.getContentPane().add(lblNumeroDeElementos, gbc_lblNumeroDeElementos);
		
		cbNumeroElementos = new JComboBox<String>();
		cbNumeroElementos.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7"}));
		cbNumeroElementos.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		frame.getContentPane().add(cbNumeroElementos, gbc_comboBox);
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnviar.gridx = 2;
		gbc_btnEnviar.gridy = 6;
		frame.getContentPane().add(btnEnviar, gbc_btnEnviar);
		
		txtResult = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 3;
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 7;
		frame.getContentPane().add(txtResult, gbc_textPane);
	}

}
