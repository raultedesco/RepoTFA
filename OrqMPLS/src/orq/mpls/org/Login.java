package orq.mpls.org;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;

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
					Login frame = new Login();
					//inicia maximizado el frame login
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					//establece que la operacion por defecto de la ventana
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
	
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nueva ventana jdialog
				CMpls v1 = new CMpls();
				//bloquea las demas ventanas, la setea como modal
				v1.setModal(true);
				v1.setVisible(true);
				
		
				
				
			}
		});
		btnNewButton.setBounds(269, 234, 167, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblEquipo = new JLabel("Parametros de Conexion");
		lblEquipo.setBounds(12, 12, 199, 15);
		contentPane.add(lblEquipo);
		
		JLabel lblIpEquipo = new JLabel("IP Equipo");
		lblIpEquipo.setBounds(12, 61, 86, 15);
		contentPane.add(lblIpEquipo);
		
		ip = new JTextField();
		ip.setBounds(109, 59, 114, 19);
		contentPane.add(ip);
		ip.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(342, 115, 70, 15);
		contentPane.add(lblPuerto);
		
		puerto = new JTextField();
		puerto.setText("23");
		puerto.setBounds(302, 59, 36, 19);
		contentPane.add(puerto);
		puerto.setColumns(10);
		
		JTextArea lblcomunitySnmp = new JTextArea("Comunidad\nSnmp");
		lblcomunitySnmp.setFont(new Font("Dialog", Font.BOLD, 12));
		lblcomunitySnmp.setBackground(UIManager.getColor("Button.background"));
		lblcomunitySnmp.setToolTipText("");
		lblcomunitySnmp.setBounds(12, 152, 86, 41);
		contentPane.add(lblcomunitySnmp);
		
		comunitySnmp = new JTextField();
		comunitySnmp.setBounds(109, 150, 114, 19);
		contentPane.add(comunitySnmp);
		comunitySnmp.setColumns(10);
		
		JLabel lbluser = new JLabel("Usuario");
		lbluser.setBounds(12, 88, 70, 15);
		contentPane.add(lbluser);
		
		usuario = new JTextField();
		usuario.setBounds(109, 86, 114, 19);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(12, 115, 70, 15);
		contentPane.add(lblpassword);
		
		// campo para ingresar el password
		password = new JPasswordField();
		password.setBounds(109, 113, 114, 19);
		contentPane.add(password);
		password.setColumns(10);
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



}
