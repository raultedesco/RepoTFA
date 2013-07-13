	package orq.mpls.org;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Icon;

@SuppressWarnings("serial")
public class CMpls extends JDialog {
	/**
	 * 
	 */
	
	private String ip;
	private String puerto;
	private String comunity;
	private String usuario;
	private String password;
	
	
	//TODO descomentar esta linea para generar objeto que hable SNMP
	private SNMPUtils con1 = new SNMPUtils();
	
	private JTextField textFieldProcesoBGP;
	private JCheckBox checkboxActiveBGP;
	private JTextField textFieldvrf;
	private JCheckBox checkboxCPE;
	private JCheckBox checkboxPE;
	private JCheckBox checkboxP;
	private JCheckBox checkboxActiveMpls;
	private JCheckBox checkboxActiveCEF;
	private JCheckBox checkboxVrf;
	private CurrentConfig c1 = new CurrentConfig();
	private JCheckBox checkboxActiveEigrp;
	private JTextField textFieldProcesoEigrp;
	private JPanel jp1;
	private JLabel mensajelimiterutas;
	private JTextField textFieldRD;
	private JTextField textFieldRT;
	private int countestatic = 0;
	private int countDinamic = 0;
	private JCheckBox checkboxRT;
	private JCheckBox checkboxRD;
	private JTextArea configResultView;
	private JScrollPane scrollPane;
	private JTextField textIP1;
	private JTextField textMask1;
	private JTextField textForwardingVRF1;
	private JTextField textIP2;
	private JTextField textMask2;
	private JTextField textForwardingVRF2;
	private JTextField textIP3;
	private JTextField textMask3;
	private JTextField textForwardingVRF3;
	private JTextField textIP4;
	private JTextField textMask4;
	private JTextField textForwardingVRF4;
	private JButton buttonAddRutas_Mask;
	private JScrollPane scrollPane1;
	private JTextArea configGenView;
	private JCheckBox checkboxMPLSIP1;
	private JCheckBox checkboxMPLSIP2;
	private JCheckBox checkboxMPLSIP3;
	private JCheckBox checkboxMPLSIP4;

	private JComboBox<?> comboBoxInterface1;

	private JComboBox<?> comboBoxInterface2;

	private JComboBox<?> comboBoxInterface3;

	private JComboBox<?> comboBoxInterface4;

	private ArrayList<String> interfacesbysnmp;
	private JTextField neighborAS;
	private JTextField neighborIP;
	private JLabel label_2;
	private JLabel label_3;
	private JButton buttonAddRutas_Mask_Dinamic;
	private JLabel lblProcess;
	private JLabel lblProcessEigrp;
	private JLabel lblAs;
	private JLabel lblVecinoBgp;
	private JLabel lblRutas2;
	private JLabel lblNewLabel;
	private JLabel lblRutas;
	
	private JTextField rorigen;
	private JTextField rmascara;
	private JTextField rdestino;
	private JTextField rorigen1;
	private JTextField rmascara1;
	private JTextField rdestino1;
	private JTextField rorigen2;
	private JTextField rmascara2;
	private JTextField rdestino2;
	
	private JTextField rdorigen;
	private JTextField rdmascara;
	private JTextField rdorigen1;
	private JTextField rdmascara1;
	private JTextField rdorigen2;
	private JTextField rdmascara2;
	private JLabel labelInternal;
	private JTextField asInternal;
	private JTextField neighborIPInternal;
	private JLabel labelASInternal;
	private JLabel labelExternal;
	private JLabel labelVecinoBGPInternal;
	private JLabel lblNewLabel_7;
	private JPanel jp2;
	private JTextArea configGenViewRoutingTable;
	private JScrollPane monitoringnetwork;
	private JButton btnActualizarRoutingTable;
	private JButton btnActualizarIPInterfacesBrief;
	private JTextArea configGenViewIPInterfaceBrief;
	private JScrollPane scrollPane_2;
	private JLabel lblNewLabel_9;
	private JLabel label_1;

	
	
	
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("static-access")
	public CMpls() {
		setTitle("Orquestador MPLS");

		
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//con esta linea se hace que cuando la ventana es instanciada esta se inicie maximizada
		this.setSize(this.getToolkit().getScreenSize());  
		
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);

		
		
		interfacesbysnmp = new ArrayList<>();
				try {

					//TODO descomentar esta linea para generar objeto que hable SNMP
					interfacesbysnmp = con1.printInterface();
					interfacesbysnmp.add(0,"None");
							} catch (Exception e2) {//cambiar por IOException
					
								JOptionPane message = new JOptionPane();
								message.showMessageDialog(this,"No se registro Conexion SNMP","Mensaje SNMP",JOptionPane.INFORMATION_MESSAGE);
								// TODO descomentar esta linea si quiero ver el contenido de la Exepcion
								//e2.printStackTrace();
			
				}
				

		
		
		jp1 = new JPanel(null);
		JLabel jl1 = new JLabel("MPLS");
		jl1.setBounds(10, 0, 123, 35);
		jp1.add(jl1);
		ImageIcon icon = new ImageIcon("images/1.png") ;
		tabbedPane.addTab("MPLS",icon,jp1,"primer panel");
		
		checkboxActiveCEF = new JCheckBox("Active CEF");
		checkboxActiveCEF.setSelected(true);
		checkboxActiveCEF.setBounds(10, 37, 114, 23);
		jp1.add(checkboxActiveCEF);
		
		JLabel lblSettingForCpe = new JLabel("Tipo de Dispositivo");
		lblSettingForCpe.setBounds(130, 0, 149, 35);
		jp1.add(lblSettingForCpe);
		
		checkboxCPE = new JCheckBox("CPE");
		checkboxCPE.setSelected(false);
		checkboxCPE.setBounds(214, 37, 65, 23);
		jp1.add(checkboxCPE);
		
		checkboxPE = new JCheckBox("PE");
		checkboxPE.setBounds(304, 37, 53, 23);
		jp1.add(checkboxPE);
		
		checkboxP = new JCheckBox("P");
		checkboxP.setBounds(390, 37, 53, 23);
		jp1.add(checkboxP);
		
		checkboxActiveMpls = new JCheckBox("Active MPLS Global");
		checkboxActiveMpls.setBounds(10, 62, 158, 23);
		checkboxActiveMpls.setEnabled(false);
		jp1.add(checkboxActiveMpls);
		
		checkboxVrf = new JCheckBox("Vrf");
		checkboxVrf.setBounds(10, 89, 58, 23);
		checkboxVrf.setEnabled(false);
		jp1.add(checkboxVrf);
		
		textFieldvrf = new JTextField("vrf");
		textFieldvrf.setBounds(63, 89, 80, 22);
		textFieldvrf.setEditable(false);
		jp1.add(textFieldvrf);
		textFieldvrf.setColumns(10);
		
		JLabel jl2 = new JLabel("Protocolos de Ruteo");
		jl2.setBounds(10, 111, 230, 35);
		jp1.add(jl2);
		
		checkboxActiveBGP = new JCheckBox("BGP");
		checkboxActiveBGP.setSelected(false);
		checkboxActiveBGP.setBounds(10, 144, 58, 23);
		jp1.add(checkboxActiveBGP);
		
		lblProcess = new JLabel("Proceso");
		lblProcess.setBounds(84, 144, 65, 23);
		lblProcess.setEnabled(false);
		jp1.add(lblProcess);
		
		textFieldProcesoBGP = new JTextField();
		textFieldProcesoBGP.setBounds(151, 143, 89, 22);
		textFieldProcesoBGP.setEditable(false);
		textFieldProcesoBGP.setEnabled(false);
		jp1.add(textFieldProcesoBGP);
		textFieldProcesoBGP.setColumns(10);
		
		checkboxActiveEigrp = new JCheckBox("EIGRP");
		checkboxActiveEigrp.setSelected(false);
		checkboxActiveEigrp.setBounds(10, 188, 65, 23);
		jp1.add(checkboxActiveEigrp);
		
		lblProcessEigrp = new JLabel("Proceso");
		lblProcessEigrp.setBounds(84, 188, 65, 20);
		lblProcessEigrp.setEnabled(false);
		jp1.add(lblProcessEigrp);
		
		textFieldProcesoEigrp = new JTextField();
		textFieldProcesoEigrp.setBounds(151, 188, 89, 22);
		textFieldProcesoEigrp.setEditable(false);
		textFieldProcesoEigrp.setEnabled(false);
		jp1.add(textFieldProcesoEigrp);
		textFieldProcesoBGP.setColumns(10);
		//Final Componentes Panel 1
		
		botonesCancel_Enviar(jp1);
		
					
					mensajelimiterutas = new JLabel("");
					mensajelimiterutas.setBounds(10, 602, 804, 15);
					jp1.add(mensajelimiterutas);
					
					checkboxRD = new JCheckBox("RD");
					checkboxRD.setBounds(148, 89, 53, 23);
					checkboxRD.setEnabled(false);
					jp1.add(checkboxRD);
					
					checkboxRT = new JCheckBox("RT");
					checkboxRT.setBounds(302, 89, 53, 23);
					checkboxRT.setEnabled(false);
					jp1.add(checkboxRT);
					
					textFieldRD = new JTextField();
					textFieldRD.setToolTipText("Formato RD : ASN:nn");
					textFieldRD.setBounds(202, 91, 80, 22);
					textFieldRD.setEditable(false);
					jp1.add(textFieldRD);
					textFieldRD.setColumns(10);
					
					textFieldRT = new JTextField();
					textFieldRT.setToolTipText("Formato RD : ASN:nn");
					textFieldRT.setColumns(10);
					textFieldRT.setBounds(355, 91, 80, 22);
					textFieldRT.setEditable(false);
					jp1.add(textFieldRT);
					
					JLabel lblNewLabel_1 = new JLabel("Parametros de Configuracion");
					lblNewLabel_1.setBounds(863, 10, 218, 15);
					jp1.add(lblNewLabel_1);
					//jp1.add(textArea);
					
						
					scrollPane = new JScrollPane();
					scrollPane.setBounds(826, 37, 517, 196);
					//scrollPane.add(textArea);
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp1.add(scrollPane);
					
					configResultView = new JTextArea();
					scrollPane.setViewportView(configResultView);
					configResultView.setLineWrap(true);
					configResultView.setEditable(false);
					
					
					//segundo jtexarea para mostrar los comandos
					JLabel lblNewLabel_29 = new JLabel("Resultado de Aplicacion de Configuracion  ");
					lblNewLabel_29.setBounds(863, 253, 314, 15);
					jp1.add(lblNewLabel_29);
					//jp1.add(textArea);
					
						
					scrollPane1 = new JScrollPane();
					scrollPane1.setBounds(826, 280, 517, 337);
					//scrollPane.add(textArea);
					scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp1.add(scrollPane1);
					
					configGenView = new JTextArea();
					scrollPane1.setViewportView(configGenView);
					configGenView.setLineWrap(true);
					configGenView.setEditable(false);
					//fin segundo jtexarea
					
								
					
					
					
					JLabel lblInterfaces = new JLabel("Interfaces");
					lblInterfaces.setBounds(12, 243, 95, 15);
					jp1.add(lblInterfaces);
					
					JLabel lblNewLabel_2 = new JLabel("IP");
					lblNewLabel_2.setBounds(151, 243, 37, 15);
					jp1.add(lblNewLabel_2);
					
					JLabel lblNewLabel_3 = new JLabel("Mascara");
					lblNewLabel_3.setBounds(275, 243, 70, 15);
					jp1.add(lblNewLabel_3);
					
					JLabel lblNewLabel_4 = new JLabel("MPLS ");
					lblNewLabel_4.setBounds(401, 243, 70, 15);
					jp1.add(lblNewLabel_4);
					
					JLabel lblNewLabel_5 = new JLabel("Forwarding VRF");
					lblNewLabel_5.setBounds(461, 243, 132, 15);
					jp1.add(lblNewLabel_5);
					
					textIP1 = new JTextField();
					textIP1.setText("None");
					textIP1.setBounds(151, 270, 114, 22);
					jp1.add(textIP1);
					textIP1.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textIP1.selectAll();
						}
					});
					textIP1.setColumns(10);
					
					textMask1 = new JTextField();
					textMask1.setText("None");
					textMask1.setBounds(275, 270, 114, 22);
					jp1.add(textMask1);
					textMask1.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textMask1.selectAll();
						}
					});
					
					textMask1.setColumns(10);
					
					checkboxMPLSIP1 = new JCheckBox("");
					checkboxMPLSIP1.setBounds(401, 269, 45, 23);
					jp1.add(checkboxMPLSIP1);
					
					textForwardingVRF1 = new JTextField();
					textForwardingVRF1.setText("None");
					textForwardingVRF1.setBounds(461, 270, 114, 22);
					jp1.add(textForwardingVRF1);
					textForwardingVRF1.setColumns(10);
					textForwardingVRF1.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textForwardingVRF1.selectAll();
						}
					});
					textIP2 = new JTextField();
					textIP2.setText("None");
					textIP2.setColumns(10);
					textIP2.setBounds(151, 305, 114, 22);
					jp1.add(textIP2);
					textIP2.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textIP2.selectAll();
						}
					});
					
					textMask2 = new JTextField();
					textMask2.setText("None");
					textMask2.setColumns(10);
					textMask2.setBounds(275, 305, 114, 22);
					jp1.add(textMask2);
					textMask2.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textMask2.selectAll();
						}
					});
					
					checkboxMPLSIP2 = new JCheckBox("");
					checkboxMPLSIP2.setBounds(401, 304, 45, 23);
					jp1.add(checkboxMPLSIP2);
					
					textForwardingVRF2 = new JTextField();
					textForwardingVRF2.setText("None");
					textForwardingVRF2.setColumns(10);
					textForwardingVRF2.setBounds(461, 305, 114, 22);
					jp1.add(textForwardingVRF2);
					textForwardingVRF2.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textForwardingVRF2.selectAll();
						}
					});
					
					textIP3 = new JTextField();
					textIP3.setText("None");
					textIP3.setColumns(10);
					textIP3.setBounds(151, 340, 114, 22);
					jp1.add(textIP3);
					textIP3.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textIP3.selectAll();
						}
					});
					
					textMask3 = new JTextField();
					textMask3.setText("None");
					textMask3.setColumns(10);
					textMask3.setBounds(275, 340, 114, 22);
					jp1.add(textMask3);
					textMask3.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textMask3.selectAll();
						}
					});
					
					checkboxMPLSIP3 = new JCheckBox("");
					checkboxMPLSIP3.setBounds(401, 339, 45, 23);
					jp1.add(checkboxMPLSIP3);
					
					textForwardingVRF3 = new JTextField();
					textForwardingVRF3.setText("None");
					textForwardingVRF3.setColumns(10);
					textForwardingVRF3.setBounds(461, 340, 114, 22);
					jp1.add(textForwardingVRF3);
					textForwardingVRF3.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textForwardingVRF3.selectAll();
						}
					});
					
					textIP4 = new JTextField();
					textIP4.setText("None");
					textIP4.setColumns(10);
					textIP4.setBounds(151, 371, 114, 22);
					jp1.add(textIP4);
					textIP4.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textIP4.selectAll();
						}
					});
					textMask4 = new JTextField();
					textMask4.setText("None");
					textMask4.setColumns(10);
					textMask4.setBounds(275, 371, 114, 22);
					jp1.add(textMask4);
					textMask4.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textMask4.selectAll();
						}
					});
					
					checkboxMPLSIP4 = new JCheckBox("");
					checkboxMPLSIP4.setBounds(401, 370, 45, 23);
					jp1.add(checkboxMPLSIP4);
					
					textForwardingVRF4 = new JTextField();
					textForwardingVRF4.setText("None");
					textForwardingVRF4.setColumns(10);
					textForwardingVRF4.setBounds(461, 371, 114, 22);
					jp1.add(textForwardingVRF4);
					textForwardingVRF4.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							textForwardingVRF4.selectAll();
						}
					});
					
					
					
		
		lblRutas = new JLabel("Red Origen");
		lblRutas.setBounds(10, 444, 114, 15);
		jp1.add(lblRutas);
		
		lblNewLabel = new JLabel("Mascara");
		lblNewLabel.setBounds(130, 444, 70, 15);
		jp1.add(lblNewLabel);
		
		lblRutas2 = new JLabel("Red Destino");
		lblRutas2.setBounds(250, 444, 114, 15);
		jp1.add(lblRutas2);
		

		comboBoxInterface1 = new JComboBox();
		comboBoxInterface1.setEnabled(true);
		comboBoxInterface1.setEditable(true);
		comboBoxInterface1.setToolTipText("Interfaces Equipo");
		comboBoxInterface1.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface1.setBounds(10, 271, 133, 22);
		jp1.add(comboBoxInterface1);
		
				comboBoxInterface2 = new JComboBox();
				comboBoxInterface2.setEnabled(true);
				comboBoxInterface2.setEditable(true);
				comboBoxInterface2.setToolTipText("Interfaces Equipo");
				comboBoxInterface2.removeAllItems();
				comboBoxInterface2.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface2.setBounds(10, 305, 133, 22);
				jp1.add(comboBoxInterface2);
				
				comboBoxInterface3 = new JComboBox();
				comboBoxInterface3.setEnabled(true);
				comboBoxInterface3.setEditable(true);
				comboBoxInterface3.setToolTipText("Interfaces Equipo");
				comboBoxInterface3.removeAllItems();
				comboBoxInterface3.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface3.setBounds(10, 340, 133, 22);
				jp1.add(comboBoxInterface3);
				
				comboBoxInterface4 = new JComboBox();
				comboBoxInterface4.setEnabled(true);
				comboBoxInterface4.setEditable(true);
				comboBoxInterface4.setToolTipText("Interfaces Equipo");
				comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface4.setBounds(10, 371, 133, 22);
				jp1.add(comboBoxInterface4);
				
				
				//enviar configuracion al dispositivo
				JButton btnNewButton = new JButton("Enviar ");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SendCurrentConfig SConfig = new SendCurrentConfig(c1,configGenView);
						JOptionPane message = new JOptionPane();
						message.showMessageDialog(jp1,	"Configuracion Enviada...");
					
						
					}
				});
				btnNewButton.setBounds(920, 629, 114, 23);
				jp1.add(btnNewButton);
				
				lblAs = new JLabel("AS");
				lblAs.setBounds(302, 144, 37, 23);
				lblAs.setEnabled(false);
				jp1.add(lblAs);
				
				neighborAS = new JTextField();
				neighborAS.setBounds(325, 144, 65, 22);
				neighborAS.setEnabled(false);
				jp1.add(neighborAS);
				neighborAS.setColumns(10);
				
				lblVecinoBgp = new JLabel("Vecino BGP");
				lblVecinoBgp.setBounds(405, 144, 89, 23);
				lblVecinoBgp.setEnabled(false);
				jp1.add(lblVecinoBgp);
				
				neighborIP = new JTextField();
				neighborIP.setBounds(509, 144, 114, 22);
				neighborIP.setEnabled(false);
				jp1.add(neighborIP);
				neighborIP.setColumns(10);
				
				label_2 = new JLabel("Rutas BGP/EIGRP");
				label_2.setBounds(461, 444, 133, 15);
				jp1.add(label_2);
				
				label_3 = new JLabel("Mascara");
				label_3.setBounds(589, 444, 70, 15);
				jp1.add(label_3);
				
				ImageIcon plus=new ImageIcon("images/3.png");
				buttonAddRutas_Mask = new JButton(plus);
				//agrego el boton +  al panel
				buttonAddRutas_Mask.setBounds(345, 440, 44, 22);
				jp1.add(buttonAddRutas_Mask);
				buttonAddRutas_Mask.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					countestatic++;
						if (countestatic==1) {
							rorigen.setVisible(true);
							rmascara.setVisible(true);
							rdestino.setVisible(true);
						}
						if (countestatic==2) {
							rorigen1.setVisible(true);
							rmascara1.setVisible(true);
							rdestino1.setVisible(true);
						}
						if (countestatic==3) {
							rorigen2.setVisible(true);
							rmascara2.setVisible(true);
							rdestino2.setVisible(true);
						}
					
						if (countestatic>2) {
							mensajelimiterutas.setText("Solo se permite un maximo de 3 rutas (Estaticas/Dinamicas) por envio de configuracion...");
							countestatic=3;
							
						}

				}//action performed boton mas para agregar rutas ok
			});
				
							
				buttonAddRutas_Mask_Dinamic = new JButton(plus);
				buttonAddRutas_Mask_Dinamic.setEnabled(false);
				buttonAddRutas_Mask_Dinamic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO codigo para generar botones de rutas BGP / EIGRP
					countDinamic++;
					if(checkboxActiveBGP.isSelected() | checkboxActiveEigrp.isSelected()){		
						if (rdorigen.isVisible()&&rdmascara.isVisible()) {

						
								if (countDinamic==2) {
									rdorigen1.setVisible(true);
									rdmascara1.setVisible(true);
									
								}

								
								if (countDinamic==3) {
									rdorigen2.setVisible(true);
									rdmascara2.setVisible(true);

								}
							
						}
					}
								if (countDinamic>2) {
									mensajelimiterutas.setText("Solo se permite un maximo de 3 rutas (Estaticas/Dinamicas) por envio de configuracion...");
									countDinamic=3;
								}
							}

														
					 
						
						
						
					
				});
				buttonAddRutas_Mask_Dinamic.setBounds(668, 440, 44, 22);
				jp1.add(buttonAddRutas_Mask_Dinamic);
				addRutasEstaticas();
				addRutasDinamicas();
				
						
						labelExternal = new JLabel("External");
						labelExternal.setBounds(304, 111, 230, 35);
						jp1.add(labelExternal);
						
						labelInternal = new JLabel("Internal");
						labelInternal.setBounds(304, 158, 230, 35);
						jp1.add(labelInternal);
						
						labelASInternal = new JLabel("AS");
						labelASInternal.setEnabled(false);
						labelASInternal.setBounds(304, 188, 37, 23);
						jp1.add(labelASInternal);
						
						asInternal = new JTextField();
						asInternal.setEnabled(false);
						asInternal.setColumns(10);
						asInternal.setBounds(327, 188, 65, 22);
						jp1.add(asInternal);
						
						labelVecinoBGPInternal = new JLabel("Vecino BGP");
						labelVecinoBGPInternal.setEnabled(false);
						labelVecinoBGPInternal.setBounds(407, 188, 89, 23);
						jp1.add(labelVecinoBGPInternal);
						
						neighborIPInternal = new JTextField();
						neighborIPInternal.setEnabled(false);
						neighborIPInternal.setColumns(10);
						neighborIPInternal.setBounds(511, 188, 114, 22);
						jp1.add(neighborIPInternal);
						JLabel lblNewLabel_6 = new JLabel(new ImageIcon("images/ip.png"));
						lblNewLabel_6.setBounds(434, 37, 24, 24);
						jp1.add(lblNewLabel_6);
						
						lblNewLabel_7 = new JLabel(new ImageIcon("images/irouter.png"));
						lblNewLabel_7.setBounds(275, 37, 24, 24);
						jp1.add(lblNewLabel_7);
						
						JLabel lblNewLabel_8 = new JLabel(new ImageIcon("images/ipe.png"));
						lblNewLabel_8.setBounds(354, 37, 24, 24);
						jp1.add(lblNewLabel_8);
						
						lblNewLabel_9 = new JLabel(new ImageIcon("images/config.png"));
						lblNewLabel_9.setBounds(826, 0, 24, 24);
						jp1.add(lblNewLabel_9);
						
						label_1 = new JLabel(new ImageIcon("images/configresult.png"));
						label_1.setBounds(826, 245, 24, 24);
						jp1.add(label_1);
						
						
		
						
						
			
						
							
				
						
						
						
						
						
						
						
						
				
				

		jp2 = new JPanel(null);
		ImageIcon icon2 = new ImageIcon("images/2.png") ;
		tabbedPane.addTab("Monitoring Network",icon2,jp2,"segundo panel");
		//coloco los botones cancelar y enviar en el tab monitoring Network			
		monitoringnetwork = new JScrollPane();
		monitoringnetwork.setBounds(53, 37, 517, 337);
		monitoringnetwork.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2.add(monitoringnetwork);
		
		configGenViewRoutingTable = new JTextArea();
		monitoringnetwork.setViewportView(configGenViewRoutingTable);
		configGenViewRoutingTable.setLineWrap(true);
		configGenViewRoutingTable.setEditable(false);
		
		JLabel lblmonitoringroutingtable = new JLabel("Tabla de Ruteo  ");
		lblmonitoringroutingtable.setBounds(53, 5, 314, 22);
		jp2.add(lblmonitoringroutingtable);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(631, 37, 713, 337);
		jp2.add(scrollPane_2);
		
		configGenViewIPInterfaceBrief = new JTextArea();
		scrollPane_2.setViewportView(configGenViewIPInterfaceBrief);
		configGenViewIPInterfaceBrief.setLineWrap(true);
		configGenViewIPInterfaceBrief.setEditable(false);
		
		JLabel label = new JLabel("Monitoring Interfaces  ");
		label.setBounds(631, 5, 314, 22);
		jp2.add(label);
		ImageIcon refresh = new ImageIcon("images/refresh.png");
		btnActualizarRoutingTable = new JButton("Actualizar", refresh);
		btnActualizarRoutingTable.setBounds(435, 5, 135, 22);
		jp2.add(btnActualizarRoutingTable);
		
		btnActualizarIPInterfacesBrief = new JButton("Actualizar", refresh);
		btnActualizarIPInterfacesBrief.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValoresConexion();
				configGenViewIPInterfaceBrief.setText("");
				MonitoringNetworkRefresh m2 = new MonitoringNetworkRefresh(c1);
				m2.refreshIPInterfaceBrief(configGenViewIPInterfaceBrief);
			}
		});
		btnActualizarIPInterfacesBrief.setBounds(1209, 5, 135, 22);
		jp2.add(btnActualizarIPInterfacesBrief);
		btnActualizarRoutingTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setValoresConexion();
				configGenViewRoutingTable.setText("");
				MonitoringNetworkRefresh m1 = new MonitoringNetworkRefresh(c1);
				m1.refreshIPRoutingTable(configGenViewRoutingTable);
				
			}
		});
				
		//Panel 3 Logs Configuraciones
		JPanel jp3 = new JPanel(null);
		ImageIcon icon3= new ImageIcon("images/slogs.png") ;
		tabbedPane.addTab("Logs Configuraciones",icon3,jp3,"tercer panel");
		//coloco los botones cancelar y enviar en el tab monitoring Network			
		botonesCancel_Enviar(jp3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 25, 358, 25);
		TableModel tablemodel = new TableModel() {
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub
				
			}
		};
		JTable jtable = new JTable();
		jtable.setModel(tablemodel);
		scrollPane_1.add(jtable);
		jp3.add(scrollPane_1);
		
		
//chequeos de los listeners para saber que se esta seleccionando al instanciar objeto CMpls
this.checkActiveDeviceCPE();
this.checkActiveDevicePE();
this.checkActiveDeviceP();
this.checkActiveCheckBGP();
this.checkActiveCheckEigrp();
this.checkActiveCheckMpls();
				

}// Final Constructor CMPLS


	

	public void addRutasEstaticas() {
		rorigen = new JTextField();
		rorigen.setBounds(10, 471, 114, 22);
		jp1.add(rorigen);
		rorigen.setColumns(10);
		rorigen.setVisible(false);
		rmascara = new JTextField();
		rmascara.setBounds(130, 471, 114, 22);
		jp1.add(rmascara);
		rmascara.setColumns(10);
		rmascara.setVisible(false);
		rdestino = new JTextField();
		rdestino.setBounds(250, 471, 114, 22);
		jp1.add(rdestino);
		rdestino.setColumns(10);
		rdestino.setVisible(false);
		
				rorigen1 = new JTextField();
				rorigen1.setBounds(10, 501, 114, 22);
				jp1.add(rorigen1);
				rorigen1.setColumns(10);
				rorigen1.setVisible(false);
				rmascara1 = new JTextField();
				rmascara1.setBounds(130, 501, 114, 22);
				jp1.add(rmascara1);
				rmascara1.setColumns(10);
				rmascara1.setVisible(false);
				rdestino1 = new JTextField();
				rdestino1.setBounds(250, 501, 114, 22);
				jp1.add(rdestino1);
				rdestino1.setColumns(10);
				rdestino1.setVisible(false);
				
				rorigen2 = new JTextField();
				rorigen2.setBounds(10, 531, 114, 22);
				jp1.add(rorigen2);
				rorigen2.setColumns(10);
				rorigen2.setVisible(false);
				rmascara2 = new JTextField();
				rmascara2.setBounds(130, 531, 114, 22);
				jp1.add(rmascara2);
				rmascara2.setColumns(10);
				rmascara2.setVisible(false);
				rdestino2 = new JTextField();
				rdestino2.setBounds(250, 531, 114, 22);
				jp1.add(rdestino2);
				rdestino2.setColumns(10);
				rdestino2.setVisible(false);
	}
		
	
	public void addRutasDinamicas() {

				rdorigen = new JTextField();
				rdorigen.setBounds(461, 471, 114, 22);
				jp1.add(rdorigen);
				rdorigen.setColumns(10);
				rdorigen.setVisible(false);
				rdmascara = new JTextField();
				rdmascara.setBounds(581, 471, 114, 22);
				jp1.add(rdmascara);
				rdmascara.setColumns(10);
				rdmascara.setVisible(false);
				

				rdorigen1 = new JTextField();
				rdorigen1.setBounds(461, 501, 114, 22);
				jp1.add(rdorigen1);
				rdorigen1.setColumns(10);
				rdorigen1.setVisible(false);
				rdmascara1 = new JTextField();
				rdmascara1.setBounds(581, 501, 114, 22);
				jp1.add(rdmascara1);
				rdmascara1.setColumns(10);
				rdmascara1.setVisible(false);
				
				
				rdorigen2 = new JTextField();
				rdorigen2.setBounds(461, 531, 114, 22);
				jp1.add(rdorigen2);
				rdorigen2.setColumns(10);
				rdorigen2.setVisible(false);
				rdmascara2 = new JTextField();
				rdmascara2.setBounds(581, 531, 114, 22);
				jp1.add(rdmascara2);
				rdmascara2.setColumns(10);
				rdmascara2.setVisible(false);
	
	}
		
		private void botonesCancel_Enviar(JPanel jpGeneric) {
			JButton btnEnviar = new JButton("Ver Config Generada");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 saveCurrentConfig();
					 Db4manager.storeCurrentconfigObjects(c1);
				}


			});
			btnEnviar.setBounds(707, 629, 190, 23);
			jpGeneric.add(btnEnviar);
			
			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_1.setBounds(567, 629, 114, 23);
			jpGeneric.add(btnNewButton_1);
		}
		
		
		
		
	

	


	
	
//fin botones canel_enviar
	
		protected void saveCurrentConfig() {
			setValoresConexion();
			configResultView.setText("");//limpia el JTexArea cada vez que se apreta el boton ver parametros
			c1.setCountestatic(countestatic);
			c1.setCountdinamic(countDinamic);
			c1.setIfCpe(checkboxCPE.isSelected());
			c1.setIfPe(checkboxPE.isSelected());
			c1.setIfP(checkboxP.isSelected());
			c1.setCefboolean(checkboxActiveCEF.isSelected());
			c1.setMplsipboolean(checkboxActiveMpls.isSelected());
			c1.setVrfFlag(checkboxVrf.isSelected());
			c1.setVrfname(textFieldvrf.getText());
			c1.setVrfrd(textFieldRD.getText());
			c1.setVrfrt(textFieldRT.getText());
			c1.setBgpflag(checkboxActiveBGP.isSelected());
			c1.setBgpProcess(textFieldProcesoBGP.getText());
			c1.setBgpNeighbor(neighborIP.getText());
			c1.setBgpRemoteAs(neighborAS.getText());
			c1.setEigrpflag(checkboxActiveEigrp.isSelected());
			c1.setEigrpProcess(textFieldProcesoEigrp.getText());
			c1.setBgpNeighborInternal(neighborIPInternal.getText());
			c1.setBgpRemoteAsInternal(asInternal.getText());
			
			//Parte de Configuracion de mpls sobre las interfaces
			String ips[] = new String[4];
			String masks[] = new String[4];
			boolean mpslip[] = new boolean[4];
			String forwardingVRF[] = new String[4];
			
try {
			interfacesbysnmp.clear();
			checkcomboboxinterface(comboBoxInterface1,0);
			checkcomboboxinterface(comboBoxInterface2,1);
			checkcomboboxinterface(comboBoxInterface3,2);
			checkcomboboxinterface(comboBoxInterface4,3);
			c1.setInterfacesNames(interfacesbysnmp);
			} catch (Exception e) {
				// TODO: handle exception
					

			}
			
			ips[0] = textIP1.getText();
			ips[1] = textIP2.getText();
			ips[2] = textIP3.getText();
			ips[3] = textIP4.getText();
			c1.setIpsInterfaces(ips);
			
			masks[0]=textMask1.getText();
			masks[1]=textMask2.getText();
			masks[2]=textMask3.getText();
			masks[3]=textMask4.getText();
			c1.setMasksInterfaces(masks);
			
			mpslip[0]=checkboxMPLSIP1.isSelected();
			mpslip[1]=checkboxMPLSIP2.isSelected();
			mpslip[2]=checkboxMPLSIP3.isSelected();
			mpslip[3]=checkboxMPLSIP4.isSelected();
			c1.setCheckMPLSIP(mpslip);
			
			forwardingVRF[0]=textForwardingVRF1.getText();
			forwardingVRF[1]=textForwardingVRF2.getText();
			forwardingVRF[2]=textForwardingVRF3.getText();
			forwardingVRF[3]=textForwardingVRF4.getText();
			c1.setForwardingVRF(forwardingVRF);
			
			// TODO Modificar esta parte Rutas Estaticas
			String temprutasestaticas[][] = new String[countestatic][3];	
			if (countestatic==1&&rorigen.isVisible()&&rmascara.isVisible()&&rdestino.isVisible()) {
				temprutasestaticas[0][0]= rorigen.getText();
				temprutasestaticas[0][1]=rmascara.getText();
				temprutasestaticas[0][2]=rdestino.getText();
				

			}
			if (countestatic==2&&rorigen1.isVisible()&&rmascara1.isVisible()&&rdestino1.isVisible()) {
				temprutasestaticas[0][0]= rorigen.getText();
				temprutasestaticas[0][1]=rmascara.getText();
				temprutasestaticas[0][2]=rdestino.getText();
				temprutasestaticas[1][0]= rorigen1.getText();
				temprutasestaticas[1][1]=rmascara1.getText();
				temprutasestaticas[1][2]=rdestino1.getText();
			}
				
			if (countestatic==3&&rorigen2.isVisible()&&rmascara2.isVisible()&&rdestino2.isVisible()) {
				temprutasestaticas[0][0]= rorigen.getText();
				temprutasestaticas[0][1]=rmascara.getText();
				temprutasestaticas[0][2]=rdestino.getText();
				temprutasestaticas[1][0]= rorigen1.getText();
				temprutasestaticas[1][1]=rmascara1.getText();
				temprutasestaticas[1][2]=rdestino1.getText();
				temprutasestaticas[2][0]=rorigen2.getText();
				temprutasestaticas[2][1]=rmascara2.getText();
				temprutasestaticas[2][2]=rdestino2.getText();
			}
		
					

			c1.setRutasEstaticas(temprutasestaticas);

			// TODO Modificar esta parte Rutas Dinamicas
			String temprutasdinamicas[][] = new String[countDinamic][2];	
			if (countDinamic==1&&rdorigen.isVisible()&&rdmascara.isVisible()) {
				temprutasdinamicas[0][0]= rdorigen.getText();
				temprutasdinamicas[0][1]=rdmascara.getText();
			
			}
			if (countDinamic==2&&rdorigen1.isVisible()&&rdmascara1.isVisible()) {
				temprutasdinamicas[0][0]= rdorigen.getText();
				temprutasdinamicas[0][1]=rdmascara.getText();
				temprutasdinamicas[1][0]= rdorigen1.getText();
				temprutasdinamicas[1][1]=rdmascara1.getText();

			}
			if (countDinamic==3&&rdorigen2.isVisible()&&rdmascara2.isVisible()) {
				temprutasdinamicas[0][0]= rdorigen.getText();
				temprutasdinamicas[0][1]=rdmascara.getText();
				temprutasdinamicas[1][0]= rdorigen1.getText();
				temprutasdinamicas[1][1]=rdmascara1.getText();
				temprutasdinamicas[2][0]= rdorigen2.getText();
				temprutasdinamicas[2][1]=rdmascara2.getText();

			}
			c1.setRutasDinamicas(temprutasdinamicas);
			
			c1.showparameter(configResultView);
			c1.setDateEstamp(new Date());
			configResultView.append(c1.getTelnetpassword());
		}


public void checkcomboboxinterface(JComboBox<?> JComboBoxInterface,int index) {
	if (JComboBoxInterface.getSelectedItem()!=null) {
		interfacesbysnmp.add(index, JComboBoxInterface.getSelectedItem().toString());
	}
}


	

	



	 public boolean  checkActiveCheckBGP() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveBGP.isSelected()) {
					 buttonAddRutas_Mask_Dinamic.setEnabled(true);
					 textFieldProcesoBGP.setEditable(true);
					 checkboxActiveEigrp.setEnabled(false);
					 textFieldProcesoEigrp.setEditable(false);
					 activebgp(true);
					 if (checkActiveDevicePE()) {
						activebgpinternal(true);
					}
					 rdorigen.setVisible(true);
					 rdmascara.setVisible(true);
					 countDinamic++;

				}
				 if (checkboxActiveBGP.isSelected() == false) {
					 buttonAddRutas_Mask_Dinamic.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveEigrp.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					 activebgp(false);
					 activebgpinternal(false);
					 offRedesDinamicas();
					 countDinamic=0;
					
				}			}

			/**
			 * 
			 */

		};
		checkboxActiveBGP.addItemListener(itemlistener);
		return checkboxActiveBGP.isSelected();
	 }
	 
		protected void activebgpinternal(boolean estado) {
			labelExternal.setEnabled(estado);
			textFieldProcesoBGP.setEnabled(estado);
			textFieldProcesoBGP.setEditable(estado);
			labelASInternal.setEnabled(estado);
			asInternal.setEnabled(estado);
			asInternal.setEditable(estado);
			labelVecinoBGPInternal.setEnabled(estado);
			neighborIPInternal.setEnabled(estado);
			neighborIPInternal.setEditable(estado);
		
	}




		public void offRedesDinamicas() {
			if (rdorigen.isVisible()&& rdmascara.isVisible()) {
				rdorigen.setVisible(false);
				rdmascara.setVisible(false);
			}
			if (rdorigen1.isVisible()&& rdmascara1.isVisible()) {
				rdorigen1.setVisible(false);
				rdmascara1.setVisible(false);
			}
			 if (rdorigen2.isVisible()&& rdmascara2.isVisible()) {
				rdorigen2.setVisible(false);
				rdmascara2.setVisible(false);
			}
			 countDinamic=0;
		}
		
	 protected void activebgp(boolean estado) {
		// TODO Auto-generated method stub
		lblProcess.setEnabled(estado);
		textFieldProcesoBGP.setEnabled(estado);
		textFieldProcesoBGP.setEditable(estado);
		lblAs.setEnabled(estado);
		neighborAS.setEnabled(estado);
		neighborAS.setEditable(estado);
		lblVecinoBgp.setEnabled(estado);
		neighborIP.setEnabled(estado);
		neighborIP.setEditable(estado);
		
		
	}


	public boolean  checkActiveCheckEigrp() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveEigrp.isSelected()) {
					 buttonAddRutas_Mask_Dinamic.setEnabled(true);
					 textFieldProcesoEigrp.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(true);
					 checkboxActiveBGP.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
					 rdorigen.setVisible(true);
					 rdmascara.setVisible(true);
					 countDinamic++;
					 
					
				}
				 if (checkboxActiveEigrp.isSelected() == false) {
					 buttonAddRutas_Mask_Dinamic.setEnabled(false);
					 textFieldProcesoEigrp.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveBGP.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					 offRedesDinamicas();
					 countDinamic=0;
					
				}			}
		};
		checkboxActiveEigrp.addItemListener(itemlistener);
		return checkboxActiveEigrp.isSelected();
	}
	
	 public boolean  checkActiveCheckCEF() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				}
			
			
		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return checkboxActiveCEF.isSelected();
	}
	 
	 public boolean  checkActiveCheckMpls() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveMpls.isSelected()) {
					 activeVRF(true);
			 ;
				}
				 if (checkboxActiveMpls.isSelected() == false) {
					 activeVRF(false);
					 
					
				}			}
			
			
		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return checkboxActiveMpls.isSelected();
	}
	 
	 
	 public void  checkActiveCheckVrf() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxVrf.isSelected()) {
					 activeVRF(true);
					
				}
				 if (checkboxVrf.isSelected() == false) {
					activeVRF(false);
					
				}			}
		};
		checkboxVrf.addItemListener(itemlistener);
	}

	 public boolean	checkActiveDeviceCPE(){
	
	//chequear si algun dispositivo esta activo y desabilitar las opciones que no correspondan
	ItemListener itemlistenerActiveDeviceCPE = new  ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if (checkboxCPE.isSelected()) {
				//activar aqui las opciones de CPE
				checkboxP.setEnabled(false);
				checkboxPE.setEnabled(false);
				checkboxActiveCEF.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);

							
				activeVRF(false);
				activeVrfForwarding(false);
				activeMplsIP(false);

				
			}
			if (checkboxCPE.isSelected() == false) {
				checkboxP.setEnabled(true);
				checkboxPE.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				activeVRF(false);
				activeVrfForwarding(false);
				activeMplsIP(false);


			}
		}
	};
	checkboxCPE.addItemListener(itemlistenerActiveDeviceCPE);
	return checkboxCPE.isSelected();
}

	 public  boolean 	checkActiveDevicePE(){
	
	//chequear si algun dispositivo esta activo y desabilitar las opciones que no correspondan
	ItemListener itemlistenerActiveDevicePE = new  ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
	
			if (checkboxPE.isSelected()) {
				//activar aqui las opciones de CPE
				checkboxCPE.setEnabled(false);
				checkboxP.setEnabled(false);
				checkboxActiveMpls.setEnabled(true);
				checkboxActiveMpls.setSelected(true);
				activeVRF(true);
				activeMplsIP(false);
				activeVrfForwarding(true);
				
			}
			if (checkboxPE.isSelected() == false) {
				checkboxCPE.setEnabled(true);
				checkboxP.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				checkboxActiveMpls.setSelected(false);
				activeVRF(false);
				activeMplsIP(false);

			}
		}
	};
	checkboxPE.addItemListener(itemlistenerActiveDevicePE);
	return checkboxPE.isSelected();
}


	 public  boolean 	checkActiveDeviceP(){
	
	//chequear si algun dispositivo esta activo y desabilitar las opciones que no correspondan
	ItemListener itemlistenerActiveDeviceP = new  ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if (checkboxP.isSelected()) {
				//activar aqui las opciones de CPE
				checkboxCPE.setEnabled(false);
				checkboxPE.setEnabled(false);
				checkboxActiveMpls.setEnabled(true);
				checkboxActiveMpls.setSelected(true);
				activeMplsIP(true);
				activeVRF(false);
				activeVrfForwarding(false);
				
			}
			if (checkboxP.isSelected() == false) {
				checkboxCPE.setEnabled(true);
				checkboxPE.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				checkboxActiveMpls.setSelected(false);
				activeVRF(false);
				activeVrfForwarding(false);
			
			}
		}
	};
	checkboxP.addItemListener(itemlistenerActiveDeviceP);
	return checkboxP.isSelected();
}

	public JCheckBox getChckbxBGP() {
		return checkboxActiveBGP;
	}

	public void setChckbxBGP(JCheckBox chckbxBGP) {
		this.checkboxActiveBGP = chckbxBGP;
	}

	public JCheckBox getCheckboxCPE() {
		return checkboxCPE;
	}

	public void setCheckboxCPE(JCheckBox checkboxCPE) {
		this.checkboxCPE = checkboxCPE;
	}




	public JCheckBox getCheckboxPE() {
		return checkboxPE;
	}

	public void setCheckboxPE(JCheckBox checkboxPE) {
		this.checkboxPE = checkboxPE;
	}





	public JCheckBox getCheckboxP() {
		return checkboxP;
	}

	public void setCheckboxP(JCheckBox checkboxP) {
		this.checkboxP = checkboxP;
	}





	public JCheckBox getCheckboxActiveMpls() {
		return checkboxActiveMpls;
	}

	public void setCheckboxActiveMpls(JCheckBox checkboxActiveMpls) {
		this.checkboxActiveMpls = checkboxActiveMpls;
	}





	public JCheckBox getCheckboxActiveCEF() {
		return checkboxActiveCEF;
	}

	public void setCheckboxActiveCEF(JCheckBox checkboxActiveCEF) {
		this.checkboxActiveCEF = checkboxActiveCEF;
	}





	public JCheckBox getCheckboxVrf() {
		return checkboxVrf;
	}

	public void setCheckboxVrf(JCheckBox checkboxVrf) {
		this.checkboxVrf = checkboxVrf;
	}

	public JCheckBox getChckbxEigrp() {
		return checkboxActiveEigrp;
	}

	public void setChckbxEigrp(JCheckBox chckbxEigrp) {
		this.checkboxActiveEigrp = chckbxEigrp;
	}

	public JTextField getTextFieldProcesoBGP() {
		return textFieldProcesoBGP;
	}

	public void setTextFieldProcesoBGP(JTextField textFieldProcesoBGP) {
		this.textFieldProcesoBGP = textFieldProcesoBGP;
	}

	

	public JTextField getTextFieldProcesoEigrp() {
		return textFieldProcesoEigrp;
	}

	public void setTextFieldProcesoEigrp(JTextField textFieldProcesoEigrp) {
		this.textFieldProcesoEigrp = textFieldProcesoEigrp;
	}



	public void activeVRF(boolean estado) {
		checkboxVrf.setEnabled(estado);
		 checkboxVrf.setSelected(estado);
		 textFieldvrf.setEditable(estado);
		 textFieldvrf.setEnabled(estado);
		 checkboxRD.setEnabled(estado);
		 checkboxRD.setSelected(estado);
		 textFieldRD.setEditable(estado);
		 textFieldRD.setEnabled(estado);
		 checkboxRT.setEnabled(estado);
		 checkboxRT.setSelected(estado);
		 textFieldRT.setEditable(estado);
		 textFieldRT.setEnabled(estado);
	}

	public void activeVrfForwarding(boolean estado) {
		textForwardingVRF1.setEditable(estado);
		textForwardingVRF2.setEditable(estado);
		textForwardingVRF3.setEditable(estado);
		textForwardingVRF4.setEditable(estado);
		textForwardingVRF1.setEnabled(estado);
		textForwardingVRF2.setEnabled(estado);
		textForwardingVRF3.setEnabled(estado);
		textForwardingVRF4.setEnabled(estado);
	}


	public void activeMplsIP(boolean estado) {
		checkboxMPLSIP1.setEnabled(estado);
		checkboxMPLSIP2.setEnabled(estado);
		checkboxMPLSIP3.setEnabled(estado);
		checkboxMPLSIP4.setEnabled(estado);
	}

		
	public void parametrosconex(String ipp, String puertop, String comunityp,String usuariop, char[]  cs) {
		// se setean las variable de conexion snmp
		this.ip=ipp;
		this.puerto=puertop;
		this.comunity=comunityp;
		this.usuario=usuariop;
		this.password = "";
		for (int i = 0; i < cs.length; i++) {
			
			this.password=this.password+cs[i];
		}

		System.out.println("password"+this.password);
		
	}




	/**
	 * 
	 */
	public void setValoresConexion() {
		c1.setTelnetIP(ip);
		c1.setTelnetPuerto(puerto);
		c1.setTelnetComunity(comunity);
		c1.setTelnetUsuario(usuario);
		c1.setTelnetPassword(password);
	}
	}

	
	
	
	

