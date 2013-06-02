	package orq.mpls.org;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
	
	private JTextField rutasEstaticascompletas[][] = new JTextField[5][4];
	private JTextField rutasDinamicasCompletas[][] =new JTextField[5][2];
	
	
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
	private int y=430;
	private int y_dinamic=430;
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
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel;
	private JLabel lblRutas;
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
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
					interfacesbysnmp.add("None");
							} catch (Exception e2) {//cambiar por IOException
					
								JOptionPane message = new JOptionPane();
								message.showMessageDialog(this,"No se registro Conexion SNMP","Mensaje SNMP",JOptionPane.INFORMATION_MESSAGE);
								// TODO descomentar esta linea si quiero ver el contenido de la Exepcion
								//e2.printStackTrace();
			
				}
		
		
		jp1 = new JPanel(null);
		JLabel jl1 = new JLabel("MPLS");
		jl1.setBounds(10, 10, 123, 35);
		jp1.add(jl1);
		tabbedPane.addTab("MPLS",null,jp1,"primer panel");
		
		checkboxActiveCEF = new JCheckBox("Active CEF");
		checkboxActiveCEF.setSelected(true);
		checkboxActiveCEF.setBounds(10, 37, 114, 23);
		jp1.add(checkboxActiveCEF);
		
		JLabel lblSettingForCpe = new JLabel("Tipo de Dispositivo");
		lblSettingForCpe.setBounds(151, 20, 137, 14);
		jp1.add(lblSettingForCpe);
		
		checkboxCPE = new JCheckBox("CPE");
		checkboxCPE.setSelected(false);
		checkboxCPE.setBounds(145, 37, 65, 23);
		jp1.add(checkboxCPE);
		
		checkboxPE = new JCheckBox("PE");
		checkboxPE.setBounds(202, 37, 53, 23);
		jp1.add(checkboxPE);
		
		checkboxP = new JCheckBox("P");
		checkboxP.setBounds(254, 37, 53, 23);
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
		checkboxActiveEigrp.setBounds(10, 168, 65, 23);
		jp1.add(checkboxActiveEigrp);
		
		lblProcessEigrp = new JLabel("Proceso");
		lblProcessEigrp.setBounds(84, 168, 65, 20);
		lblProcessEigrp.setEnabled(false);
		jp1.add(lblProcessEigrp);
		
		textFieldProcesoEigrp = new JTextField();
		textFieldProcesoEigrp.setBounds(151, 168, 89, 22);
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
					lblNewLabel_1.setBounds(826, 20, 218, 15);
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
					lblNewLabel_29.setBounds(826, 255, 314, 15);
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
					lblInterfaces.setBounds(12, 218, 95, 15);
					jp1.add(lblInterfaces);
					
					JLabel lblNewLabel_2 = new JLabel("IP");
					lblNewLabel_2.setBounds(151, 218, 37, 15);
					jp1.add(lblNewLabel_2);
					
					JLabel lblNewLabel_3 = new JLabel("Mascara");
					lblNewLabel_3.setBounds(275, 218, 70, 15);
					jp1.add(lblNewLabel_3);
					
					JLabel lblNewLabel_4 = new JLabel("MPLS ");
					lblNewLabel_4.setBounds(401, 218, 70, 15);
					jp1.add(lblNewLabel_4);
					
					JLabel lblNewLabel_5 = new JLabel("Forwarding VRF");
					lblNewLabel_5.setBounds(461, 218, 132, 15);
					jp1.add(lblNewLabel_5);
					
					textIP1 = new JTextField();
					textIP1.setText("None");
					textIP1.setBounds(151, 245, 114, 22);
					jp1.add(textIP1);
					textIP1.setColumns(10);
					
					textMask1 = new JTextField();
					textMask1.setText("None");
					textMask1.setBounds(275, 245, 114, 22);
					jp1.add(textMask1);
					textMask1.setColumns(10);
					
					checkboxMPLSIP1 = new JCheckBox("");
					checkboxMPLSIP1.setBounds(401, 244, 45, 23);
					jp1.add(checkboxMPLSIP1);
					
					textForwardingVRF1 = new JTextField();
					textForwardingVRF1.setText("None");
					textForwardingVRF1.setBounds(461, 245, 114, 22);
					jp1.add(textForwardingVRF1);
					textForwardingVRF1.setColumns(10);
					
					textIP2 = new JTextField();
					textIP2.setText("None");
					textIP2.setColumns(10);
					textIP2.setBounds(151, 280, 114, 22);
					jp1.add(textIP2);
					
					textMask2 = new JTextField();
					textMask2.setText("None");
					textMask2.setColumns(10);
					textMask2.setBounds(275, 280, 114, 22);
					jp1.add(textMask2);
					
					checkboxMPLSIP2 = new JCheckBox("");
					checkboxMPLSIP2.setBounds(401, 279, 45, 23);
					jp1.add(checkboxMPLSIP2);
					
					textForwardingVRF2 = new JTextField();
					textForwardingVRF2.setText("None");
					textForwardingVRF2.setColumns(10);
					textForwardingVRF2.setBounds(461, 280, 114, 22);
					jp1.add(textForwardingVRF2);
					
					textIP3 = new JTextField();
					textIP3.setText("None");
					textIP3.setColumns(10);
					textIP3.setBounds(151, 315, 114, 22);
					jp1.add(textIP3);
					
					textMask3 = new JTextField();
					textMask3.setText("None");
					textMask3.setColumns(10);
					textMask3.setBounds(275, 315, 114, 22);
					jp1.add(textMask3);
					
					checkboxMPLSIP3 = new JCheckBox("");
					checkboxMPLSIP3.setBounds(401, 314, 45, 23);
					jp1.add(checkboxMPLSIP3);
					
					textForwardingVRF3 = new JTextField();
					textForwardingVRF3.setText("None");
					textForwardingVRF3.setColumns(10);
					textForwardingVRF3.setBounds(461, 315, 114, 22);
					jp1.add(textForwardingVRF3);
					
					textIP4 = new JTextField();
					textIP4.setText("None");
					textIP4.setColumns(10);
					textIP4.setBounds(151, 346, 114, 22);
					jp1.add(textIP4);
					
					textMask4 = new JTextField();
					textMask4.setText("None");
					textMask4.setColumns(10);
					textMask4.setBounds(275, 346, 114, 22);
					jp1.add(textMask4);
					
					checkboxMPLSIP4 = new JCheckBox("");
					checkboxMPLSIP4.setBounds(401, 345, 45, 23);
					jp1.add(checkboxMPLSIP4);
					
					textForwardingVRF4 = new JTextField();
					textForwardingVRF4.setText("None");
					textForwardingVRF4.setColumns(10);
					textForwardingVRF4.setBounds(461, 346, 114, 22);
					jp1.add(textForwardingVRF4);
					
					
					
		
		lblRutas = new JLabel("Red Origen");
		lblRutas.setBounds(10, 400, 114, 15);
		jp1.add(lblRutas);
		
		lblNewLabel = new JLabel("Mascara");
		lblNewLabel.setBounds(130, 400, 70, 15);
		jp1.add(lblNewLabel);
		
		lblRutas2 = new JLabel("Red Destino");
		lblRutas2.setBounds(250, 400, 114, 15);
		jp1.add(lblRutas2);
		
		lblNewLabel2 = new JLabel("Mascara");
		lblNewLabel2.setBounds(370, 400, 70, 15);
		jp1.add(lblNewLabel2);
		

		comboBoxInterface1 = new JComboBox();
		comboBoxInterface1.setEnabled(true);
		comboBoxInterface1.setEditable(true);
		comboBoxInterface1.setToolTipText("Interfaces Equipo");
		comboBoxInterface1.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface1.setBounds(10, 246, 133, 22);
		jp1.add(comboBoxInterface1);
		
				comboBoxInterface2 = new JComboBox();
				comboBoxInterface2.setEnabled(true);
				comboBoxInterface2.setEditable(true);
				comboBoxInterface2.setToolTipText("Interfaces Equipo");
				comboBoxInterface2.removeAllItems();
				comboBoxInterface2.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface2.setBounds(10, 280, 133, 22);
				jp1.add(comboBoxInterface2);
				
				comboBoxInterface3 = new JComboBox();
				comboBoxInterface3.setEnabled(true);
				comboBoxInterface3.setEditable(true);
				comboBoxInterface3.setToolTipText("Interfaces Equipo");
				comboBoxInterface3.removeAllItems();
				comboBoxInterface3.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface3.setBounds(10, 315, 133, 22);
				jp1.add(comboBoxInterface3);
				
				comboBoxInterface4 = new JComboBox();
				comboBoxInterface4.setEnabled(true);
				comboBoxInterface4.setEditable(true);
				comboBoxInterface4.setToolTipText("Interfaces Equipo");
				comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				comboBoxInterface4.setBounds(10, 346, 133, 22);
				jp1.add(comboBoxInterface4);
				
				
				//enviar configuracion al dispositivo
				JButton btnNewButton = new JButton("Enviar ");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SendCurrentConfig SConfig = new SendCurrentConfig(c1,configGenView);
					
						
					}
				});
				btnNewButton.setBounds(920, 629, 114, 23);
				jp1.add(btnNewButton);
				
				lblAs = new JLabel("AS");
				lblAs.setBounds(252, 144, 37, 23);
				lblAs.setEnabled(false);
				jp1.add(lblAs);
				
				neighborAS = new JTextField();
				neighborAS.setBounds(275, 144, 65, 22);
				neighborAS.setEnabled(false);
				jp1.add(neighborAS);
				neighborAS.setColumns(10);
				
				lblVecinoBgp = new JLabel("Vecino BGP");
				lblVecinoBgp.setBounds(355, 144, 89, 23);
				lblVecinoBgp.setEnabled(false);
				jp1.add(lblVecinoBgp);
				
				neighborIP = new JTextField();
				neighborIP.setBounds(459, 144, 114, 22);
				neighborIP.setEnabled(false);
				jp1.add(neighborIP);
				neighborIP.setColumns(10);
				
				label_2 = new JLabel("Rutas BGP/EIGRP");
				label_2.setBounds(500, 400, 133, 15);
				jp1.add(label_2);
				
				label_3 = new JLabel("Mascara");
				label_3.setBounds(628, 400, 70, 15);
				jp1.add(label_3);
				
				
				buttonAddRutas_Mask = new JButton("+");
				//agrego el boton +  al panel
				buttonAddRutas_Mask.setBounds(446, 396, 44, 22);
				jp1.add(buttonAddRutas_Mask);
				buttonAddRutas_Mask.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//establesco un maximo de 5 rutas								
					if (countestatic < 5) {
						//llamo a los metodos que agregara dinamicamente los campos para insercion de las rutas
						addrutasEstaticas(jp1, y, countestatic);
						//incremento contadores de posicion en array y ventana
						countestatic++;
						y=y+30;
					} 
					else {
					mensajelimiterutas.setText("Solo se permite un maximo de 5 rutas (Estaticas/Dinamicas) por envio de configuracion...");
					
					}	
				}//action performed boton mas para agregar rutas ok
			});
				
								
				buttonAddRutas_Mask_Dinamic = new JButton("+");
				buttonAddRutas_Mask_Dinamic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO codigo para generar botones de rutas BGP / EIGRP
						if (countDinamic < 5) {
							//llamo a los metodos que agregara dinamicamente los campos para insercion de las rutas
							addrutasDinamicas(jp1,y_dinamic,countDinamic);
							//incremento contadores de posicion en array y ventana
							countDinamic++;
							y_dinamic=y_dinamic+30;
						} 
						else {
						mensajelimiterutas.setText("Solo se permite un maximo de 5 rutas (Estaticas/Dinamicas) por envio de configuracion...");
						
						}	
						
						
					}
				});
				buttonAddRutas_Mask_Dinamic.setBounds(707, 396, 44, 22);
				jp1.add(buttonAddRutas_Mask_Dinamic);
				

		//Panel 2 Routing Protocols	
		JPanel jp2 = new JPanel(null);
		tabbedPane.addTab("Monitoring Network",null,jp2,"segundo panel");
		//coloco los botones cancelar y enviar en el tab monitoring Network			
		botonesCancel_Enviar(jp2);
				
	
		
		
		
//chequeos de los listeners para saber que se esta seleccionando al instanciar objeto CMpls
this.checkActiveDeviceCPE();
this.checkActiveDevicePE();
this.checkActiveDeviceP();
this.checkActiveCheckBGP();
this.checkActiveCheckEigrp();
this.checkActiveCheckMpls();
				

}// Final Constructor CMPLS
		
		
		private void botonesCancel_Enviar(JPanel jpGeneric) {
			JButton btnEnviar = new JButton("Ver Config Generada");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 saveCurrentConfig();
				}


			});
			btnEnviar.setBounds(707, 629, 190, 23);
			jpGeneric.add(btnEnviar);
			
			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_1.setBounds(567, 629, 114, 23);
			jpGeneric.add(btnNewButton_1);
			
		}
		
		
		
		
	

	


	
	
//fin botones canel_enviar
	
		protected void saveCurrentConfig() {
			// TODO nuevo metodo save current configuration
			
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
			
			//Rutas Estaticas
			String temprutasestaticas[][] = new String[countestatic][4];	
			for (int i = 0; i < countestatic; i++) {
				for (int j = 0; j < rutasEstaticascompletas[i].length; j++) {

					temprutasestaticas[i][j]= rutasEstaticascompletas[i][j].getText();
					
			}
			}
			c1.setRutasEstaticas(temprutasestaticas);

			//Rutas Dinamicas
			String temprutasdinamicas[][] = new String[countDinamic][2];	
			for (int i = 0; i < countDinamic; i++) {
				for (int j = 0; j < rutasDinamicasCompletas[i].length; j++) {

					temprutasdinamicas[i][j]= rutasDinamicasCompletas[i][j].getText();
					
			}
			}
			c1.setRutasDinamicas(temprutasdinamicas);
			
			c1.showparameter(configResultView);

		
		}


public void checkcomboboxinterface(JComboBox<?> JComboBoxInterface,int index) {
	if (JComboBoxInterface.getSelectedItem()!=null) {
		interfacesbysnmp.add(index, JComboBoxInterface.getSelectedItem().toString());
	}
}


	
	public String addrutasEstaticas(JPanel jpgeneric,int y, int countestatic){
		int xEstatic = 10;
		for (int c = 0; c < 4; c++) {
		rutasEstaticascompletas[countestatic][c] = new JTextField("none");
		rutasEstaticascompletas[countestatic][c].setVisible(true);
		rutasEstaticascompletas[countestatic][c].setBounds(xEstatic,y,114,22);
		jpgeneric.add(rutasEstaticascompletas[countestatic][c]);
		xEstatic=xEstatic+120;
					
		}

		return null;
		
	}
	
	public String addrutasDinamicas(JPanel jpgeneric, int y, int count) {
		int xDinamic = 500;
		for (int c = 0; c < 2; c++) {
		rutasDinamicasCompletas[count][c]= new JTextField("ruta dinamica");
		rutasDinamicasCompletas[count][c].setVisible(true);
		rutasDinamicasCompletas[count][c].setBounds(xDinamic, y, 114, 22);
	    jpgeneric.add(rutasDinamicasCompletas[count][c]);
	    xDinamic=xDinamic+120;
		}
		 return null;
	}


	 public boolean  checkActiveCheckBGP() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveBGP.isSelected()) {
					 textFieldProcesoBGP.setEditable(true);
					 checkboxActiveEigrp.setEnabled(false);
					 textFieldProcesoEigrp.setEditable(false);
					 activebgp(true);
					
				}
				 if (checkboxActiveBGP.isSelected() == false) {
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveEigrp.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					 activebgp(false);
					
				}			}
		};
		checkboxActiveBGP.addItemListener(itemlistener);
		return checkboxActiveBGP.isSelected();
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
					 textFieldProcesoEigrp.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(true);
					 checkboxActiveBGP.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
			
					 
					
				}
				 if (checkboxActiveEigrp.isSelected() == false) {
					 textFieldProcesoEigrp.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveBGP.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					
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

		
	public void parametrosconex(String ipp, String puertop, String comunityp,String usuariop, String passwordp) {
		// se setean las variable de conexion snmp
		ip=ipp;
		puerto=puertop;
		comunity=comunityp;
		usuario=usuariop;
		password=passwordp;
		
	}
	}

	
	
	
	

