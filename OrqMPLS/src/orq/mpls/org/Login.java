package orq.mpls.org;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Login extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField ip;
	private JTextField puerto;
	private JTextField comunitySnmp;
	private JTextField usuario;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					Login frame = new Login();
					// inicia maximizado el frame login
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					// establece que la operacion por defecto de la ventana
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	public Login() {
		setTitle("Orquestador MPLS - Login");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {

			private AutomatedTelnet testtelnet;

			public void actionPerformed(ActionEvent e) {
				// Antes de crear cmpls probamos que los valores pasados para
				// telnet sean correctos
				// nueva ventana jdialog
				if (validarCampos() == false) {
					JOptionPane
							.showMessageDialog(
									null,
									"Solo el Campo Comunidad puede estar vacio.. los demas campos son obligatorios!!!",
									"Error de Validacion",
									JOptionPane.INFORMATION_MESSAGE,
									(new ImageIcon("images/config.png")));
				}

				if (validarCampos()) {
					boolean flag = false;

					String pass = new String(password.getPassword());
					testtelnet = new AutomatedTelnet();

					try {
						flag = testtelnet.TestConeccion(ip.getText(),
								usuario.getText(), pass);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (flag == true) {
						CMpls v1 = new CMpls();
						v1.setVisible(true);
						v1.parametrosconex(ip.getText(), puerto.getText(),
								comunitySnmp.getText(), usuario.getText(),
								password.getPassword());
						v1.connectSnmp();
						// bloquea las demas ventanas, la setea como modal
						v1.setModal(true);

					}
					if (flag == false) {
						JOptionPane.showMessageDialog(null,
								"Usuario o Password Incorrecto");
					}
				}

			}
		});
		btnNewButton.setBounds(12, 234, 167, 25);
		contentPane.add(btnNewButton);

		JLabel lblEquipo = new JLabel("Parametros de Conexion");
		lblEquipo.setBounds(12, 12, 199, 15);
		contentPane.add(lblEquipo);

		JLabel lblIpEquipo = new JLabel("IP Equipo");
		lblIpEquipo.setBounds(12, 61, 86, 15);
		contentPane.add(lblIpEquipo);

		ip = new JTextField();
		ip.setBounds(109, 59, 220, 21);
		contentPane.add(ip);
		ip.setColumns(10);
		ip.setText("IP");

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(337, 61, 70, 15);
		contentPane.add(lblPuerto);

		puerto = new JTextField();
		puerto.setText("23");
		puerto.setBounds(400, 58, 36, 21);
		contentPane.add(puerto);
		puerto.setColumns(10);

		JLabel lblcomunitySnmp = new JLabel("Comunidad\nSnmp");
		lblcomunitySnmp.setToolTipText("");
		lblcomunitySnmp.setBounds(12, 142, 146, 41);
		contentPane.add(lblcomunitySnmp);

		comunitySnmp = new JTextField();
		comunitySnmp.setBounds(152, 152, 177, 21);
		contentPane.add(comunitySnmp);
		comunitySnmp.setColumns(10);
		comunitySnmp.setText("Ingresar Comunidad");

		JLabel lbluser = new JLabel("Usuario");
		lbluser.setBounds(12, 88, 70, 15);
		contentPane.add(lbluser);

		usuario = new JTextField();
		usuario.setBounds(109, 86, 220, 21);
		contentPane.add(usuario);
		usuario.setColumns(10);
		usuario.setText("Usuario");

		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBackground(UIManager.getColor("Button.background"));
		lblpassword.setBounds(12, 115, 70, 15);
		contentPane.add(lblpassword);

		// campo para ingresar el password
		password = new JPasswordField();
		password.setBounds(109, 113, 220, 21);
		contentPane.add(password);
		password.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource(
				"images/MPLS.png")));
		lblNewLabel.setBounds(223, 185, 213, 70);
		contentPane.add(lblNewLabel);
	}

	public JTextField getIp() {
		return ip;
	}

	public void setIp(JTextField ip) {
		this.ip = ip;
	}

	public JTextField getPuerto() {
		return puerto;
	}

	public void setPuerto(JTextField puerto) {
		this.puerto = puerto;
	}

	public JTextField getComunitySnmp() {
		return comunitySnmp;
	}

	public void setComunitySnmp(JTextField comunitySnmp) {
		this.comunitySnmp = comunitySnmp;
	}

	public JTextField getUsuario() {
		return usuario;
	}

	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public boolean validarCampos() {

		if (this.ip.getText().isEmpty()) {

			return false;
		}
		if (this.usuario.getText().isEmpty()) {

			return false;
		}
		if (this.password.getText().isEmpty()) {

			return false;
		}
		return true;
	}

}
