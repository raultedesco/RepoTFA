	package orq.mpls.org;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
	
	private SNMPUtils con1 = new SNMPUtils();
	
	private JTextField textFieldProcesoBGP;
	private JCheckBox checkboxActiveBGP;
	private boolean activeBGPFlag;
	private JTextField textFieldvrf;
	private JCheckBox checkboxCPE;
	private boolean activeCPEFlag;
	private JCheckBox checkboxPE;
	private boolean activePEFlag;
	private JCheckBox checkboxP;
	private boolean activePFlag;
	private JCheckBox checkboxActiveMpls;
	private boolean activeMplsFlag;
	private JCheckBox checkboxActiveCEF;
	private boolean activeCEFFlag;
	private JCheckBox checkboxVrf;
	private CurrentConfig c1 = new CurrentConfig();
	private JCheckBox checkboxActiveEigrp;
	private boolean activeEigrpFlag;
	private JTextField textFieldProcesoEigrp;
	private JPanel jp1;
	private JLabel mensajelimiterutas;
	private JTextField textFieldRD;
	private JTextField textFieldRT;
	private int count = 0;
	private int y=430;
	private JTextField []rutas = new JTextField[5]; 
	private JTextField [] mascara= new JTextField[5]; 	
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

	private JComboBox comboBoxInterface1;

	private JComboBox comboBoxInterface2;

	private JComboBox comboBoxInterface3;

	private JComboBox comboBoxInterface4;

	private ArrayList<String> interfacesbysnmp;
	private JTextField textField;
	private JTextField textField_1;
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public CMpls() {
		
		
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		//con esta linea se hace que cuando la ventana es instanciada esta se inicie maximizada
		this.setSize(this.getToolkit().getScreenSize());  
		
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		activeCEFFlag=true;
		
		
		jp1 = new JPanel(null);
		JLabel jl1 = new JLabel("MPLS");
		jl1.setBounds(10, 10, 123, 35);
		jp1.add(jl1);
		tabbedPane.addTab("MPLS",null,jp1,"primer panel");
		
		checkboxActiveCEF = new JCheckBox("Active CEF");
		checkboxActiveCEF.setSelected(true);
		checkboxActiveCEF.setBounds(10, 37, 97, 23);
		jp1.add(checkboxActiveCEF);
		
		JLabel lblSettingForCpe = new JLabel("Tipo de Dispositivo");
		lblSettingForCpe.setBounds(151, 20, 137, 14);
		jp1.add(lblSettingForCpe);
		
		checkboxCPE = new JCheckBox("CPE");
		checkboxCPE.setSelected(false);
		checkboxCPE.setBounds(147, 37, 53, 23);
		jp1.add(checkboxCPE);
		
		checkboxPE = new JCheckBox("PE");
		checkboxPE.setBounds(202, 37, 53, 23);
		jp1.add(checkboxPE);
		
		checkboxP = new JCheckBox("P");
		checkboxP.setBounds(252, 37, 53, 23);
		jp1.add(checkboxP);
		
		checkboxActiveMpls = new JCheckBox("Active MPLS Global");
		checkboxActiveMpls.setBounds(10, 62, 158, 23);
		checkboxActiveMpls.setEnabled(false);
		jp1.add(checkboxActiveMpls);
		
		checkboxVrf = new JCheckBox();
		checkboxVrf.setBounds(10, 89, 45, 23);
		checkboxVrf.setEnabled(false);
		jp1.add(checkboxVrf);
		
		textFieldvrf = new JTextField("vrf");
		textFieldvrf.setBounds(63, 89, 80, 20);
		textFieldvrf.setEditable(false);
		jp1.add(textFieldvrf);
		textFieldvrf.setColumns(10);
		
		JLabel jl2 = new JLabel("Protocolos de Ruteo");
		jl2.setBounds(10, 111, 230, 35);
		jp1.add(jl2);
		
		checkboxActiveBGP = new JCheckBox("BGP");
		checkboxActiveBGP.setSelected(false);
		checkboxActiveBGP.setBounds(10, 141, 58, 23);
		jp1.add(checkboxActiveBGP);
		
		JLabel lblProcess = new JLabel("Proceso");
		lblProcess.setBounds(84, 145, 65, 14);
		jp1.add(lblProcess);
		
		textFieldProcesoBGP = new JTextField();
		textFieldProcesoBGP.setBounds(151, 143, 89, 20);
		textFieldProcesoBGP.setEditable(false);
		jp1.add(textFieldProcesoBGP);
		textFieldProcesoBGP.setColumns(10);
		
		checkboxActiveEigrp = new JCheckBox("EIGRP");
		checkboxActiveEigrp.setSelected(false);
		checkboxActiveEigrp.setBounds(10, 168, 65, 23);
		jp1.add(checkboxActiveEigrp);
		
		JLabel lblProcessEigrp = new JLabel("Proceso");
		lblProcessEigrp.setBounds(84, 171, 65, 14);
		jp1.add(lblProcessEigrp);
		
		textFieldProcesoEigrp = new JTextField();
		textFieldProcesoEigrp.setBounds(151, 170, 89, 20);
		textFieldProcesoEigrp.setEditable(false);
		jp1.add(textFieldProcesoEigrp);
		textFieldProcesoBGP.setColumns(10);
		//Final Componentes Panel 1
		
		botonesCancel_Enviar(jp1);
		
					
				mensajelimiterutas = new JLabel("");
				mensajelimiterutas.setBounds(10, 602, 589, 15);
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
				textFieldRD.setBounds(202, 91, 80, 20);
				textFieldRD.setEditable(false);
				jp1.add(textFieldRD);
				textFieldRD.setColumns(10);
				
				textFieldRT = new JTextField();
				textFieldRT.setToolTipText("Formato RD : ASN:nn");
				textFieldRT.setColumns(10);
				textFieldRT.setBounds(355, 91, 80, 20);
				textFieldRT.setEditable(false);
				jp1.add(textFieldRT);
				
				JLabel lblNewLabel_1 = new JLabel("Parametros de Configuracion");
				lblNewLabel_1.setBounds(826, 20, 218, 15);
				jp1.add(lblNewLabel_1);
				//jp1.add(textArea);
				
					
				scrollPane = new JScrollPane();
				scrollPane.setBounds(826, 37, 500, 196);
				//scrollPane.add(textArea);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp1.add(scrollPane);
				
				configResultView = new JTextArea();
				scrollPane.setViewportView(configResultView);
				configResultView.setLineWrap(true);
				configResultView.setEditable(false);
				
				
				//segundo jtexarea para mostrar los comandos
				JLabel lblNewLabel_29 = new JLabel("Configuracion Generada");
				lblNewLabel_29.setBounds(826, 255, 218, 15);
				jp1.add(lblNewLabel_29);
				//jp1.add(textArea);
				
					
				scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(826, 280, 500, 196);
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
				textIP1.setBounds(151, 245, 114, 21);
				jp1.add(textIP1);
				textIP1.setColumns(10);
				
				textMask1 = new JTextField();
				textMask1.setText("None");
				textMask1.setBounds(275, 245, 114, 21);
				jp1.add(textMask1);
				textMask1.setColumns(10);
				
				checkboxMPLSIP1 = new JCheckBox("");
				checkboxMPLSIP1.setBounds(400, 241, 45, 23);
				jp1.add(checkboxMPLSIP1);
				
				textForwardingVRF1 = new JTextField();
				textForwardingVRF1.setText("None");
				textForwardingVRF1.setBounds(461, 245, 114, 21);
				jp1.add(textForwardingVRF1);
				textForwardingVRF1.setColumns(10);
						
						textIP2 = new JTextField();
						textIP2.setText("None");
						textIP2.setColumns(10);
						textIP2.setBounds(151, 280, 114, 21);
						jp1.add(textIP2);
						
						textMask2 = new JTextField();
						textMask2.setText("None");
						textMask2.setColumns(10);
						textMask2.setBounds(275, 280, 114, 21);
						jp1.add(textMask2);
						
						checkboxMPLSIP2 = new JCheckBox("");
						checkboxMPLSIP2.setBounds(400, 276, 45, 23);
						jp1.add(checkboxMPLSIP2);
						
						textForwardingVRF2 = new JTextField();
						textForwardingVRF2.setText("None");
						textForwardingVRF2.setColumns(10);
						textForwardingVRF2.setBounds(461, 280, 114, 21);
						jp1.add(textForwardingVRF2);
						
						textIP3 = new JTextField();
						textIP3.setText("None");
						textIP3.setColumns(10);
						textIP3.setBounds(151, 315, 114, 21);
						jp1.add(textIP3);
						
						textMask3 = new JTextField();
						textMask3.setText("None");
						textMask3.setColumns(10);
						textMask3.setBounds(275, 315, 114, 21);
						jp1.add(textMask3);
						
						checkboxMPLSIP3 = new JCheckBox("");
						checkboxMPLSIP3.setBounds(400, 311, 45, 23);
						jp1.add(checkboxMPLSIP3);
						
						textForwardingVRF3 = new JTextField();
						textForwardingVRF3.setText("None");
						textForwardingVRF3.setColumns(10);
						textForwardingVRF3.setBounds(461, 315, 114, 21);
						jp1.add(textForwardingVRF3);
						
						textIP4 = new JTextField();
						textIP4.setText("None");
						textIP4.setColumns(10);
						textIP4.setBounds(151, 346, 114, 21);
						jp1.add(textIP4);
						
						textMask4 = new JTextField();
						textMask4.setText("None");
						textMask4.setColumns(10);
						textMask4.setBounds(275, 346, 114, 21);
						jp1.add(textMask4);
						
						checkboxMPLSIP4 = new JCheckBox("");
						checkboxMPLSIP4.setBounds(400, 342, 45, 23);
						jp1.add(checkboxMPLSIP4);
						
						textForwardingVRF4 = new JTextField();
						textForwardingVRF4.setText("None");
						textForwardingVRF4.setColumns(10);
						textForwardingVRF4.setBounds(461, 346, 114, 21);
						jp1.add(textForwardingVRF4);
						
						
						
		
		JLabel lblRutas = new JLabel("Rutas");
		lblRutas.setBounds(10, 400, 70, 15);
		jp1.add(lblRutas);
		
		JLabel lblNewLabel = new JLabel("Mascara");
		lblNewLabel.setBounds(155, 400, 70, 15);
		jp1.add(lblNewLabel);
		
		buttonAddRutas_Mask = new JButton("+");
		//agrego el boton +  al panel
		buttonAddRutas_Mask.setBounds(242, 400, 44, 20);
		jp1.add(buttonAddRutas_Mask);
		
		
		interfacesbysnmp = new ArrayList<>();
				try {
					interfacesbysnmp = con1.printInterface();
					interfacesbysnmp.add("None");
							} catch (IOException e2) {
					
					e2.printStackTrace();
				}
		
		comboBoxInterface1 = new JComboBox();
		comboBoxInterface1.setEnabled(true);
		comboBoxInterface1.setToolTipText("Interfaces Equipo");
		comboBoxInterface1.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface1.setBounds(10, 246, 133, 19);
		jp1.add(comboBoxInterface1);

		comboBoxInterface2 = new JComboBox();
		comboBoxInterface2.setEnabled(true);
		comboBoxInterface2.setToolTipText("Interfaces Equipo");
		comboBoxInterface2.removeAllItems();
		//comboBoxInterface2.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface2.setBounds(10, 280, 133, 19);
		jp1.add(comboBoxInterface2);
		
		comboBoxInterface3 = new JComboBox();
		comboBoxInterface3.setEnabled(true);
		comboBoxInterface3.setToolTipText("Interfaces Equipo");
		comboBoxInterface3.removeAllItems();
		//comboBoxInterface3.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface3.setBounds(10, 315, 133, 19);
		jp1.add(comboBoxInterface3);
		
		comboBoxInterface4 = new JComboBox();
		comboBoxInterface4.setEnabled(true);
		comboBoxInterface4.setToolTipText("Interfaces Equipo");
		//comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
		comboBoxInterface4.setBounds(10, 346, 133, 19);
		jp1.add(comboBoxInterface4);
		
		
		//enviar configuracion al dispositivo
		JButton btnNewButton = new JButton("Enviar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setcurrentconfigvalues();
				AutomatedTelnet send = new AutomatedTelnet("192.168.80.110", "raul", "cisco");
				send.ena("cisco");
				send.configmode();
				send.cvrf(c1);
				send.disconnect();
			}
		});
		btnNewButton.setBounds(920, 629, 114, 23);
		jp1.add(btnNewButton);
		
		JLabel lblAs = new JLabel("AS");
		lblAs.setBounds(252, 144, 37, 17);
		jp1.add(lblAs);
		
		textField = new JTextField();
		textField.setBounds(295, 139, 65, 27);
		jp1.add(textField);
		textField.setColumns(10);
		
		JLabel lblVecinoBgp = new JLabel("Vecino BGP");
		lblVecinoBgp.setBounds(382, 144, 89, 17);
		jp1.add(lblVecinoBgp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(479, 139, 140, 27);
		jp1.add(textField_1);
		textField_1.setColumns(10);
		
		buttonAddRutas_Mask.addActionListener(new ActionListener() {
		

		

			public void actionPerformed(ActionEvent e) {
				
			
				//establesco un maximo de 5 rutas								
				if (count < 5) {
					//llamo a los metodos que agregara dinamicamente los campos para insercion de las rutas
					addroute(jp1,y,count); 
					addroutemask(jp1,y,count);
					
					//incremento contadores de posicion en array y ventana
					count++;
					y=y+30;
					
					
				} 
				
	
				else {
					
				mensajelimiterutas.setText("Solo se permite un maximo de 5 rutas por envio de configuracion...");
					
				}	
				
				
				
			}//action performed boton mas para agregar rutas ok

	

			
		});
		//Panel 2 Routing Protocols	
		JPanel jp2 = new JPanel(null);
		tabbedPane.addTab("Monitoring Network",null,jp2,"segundo panel");
		//coloco los botones cancelar y enviar en el tab monitoring Network			
		botonesCancel_Enviar(jp2);
	
		
		
		
		//chequeos de los listeners para saber que se esta seleccionando al instanciar objeto CMpls
				this.checkActiveDeviceCPE();
				//this.checkActiveCheckVrf();
				this.checkActiveDevicePE();
				this.checkActiveDeviceP();
				this.checkActiveCheckBGP();
				this.checkActiveCheckEigrp();
				this.checkActiveCheckMpls();
				this.checkOverlapingInterface();
				

	}// final constructor CMPLS()
		
		
		private void botonesCancel_Enviar(JPanel jpGeneric) {
		JButton btnEnviar = new JButton("Ver Config Generada");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setcurrentconfigvalues();
						
				
			
			
			
			
			
			
						
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
	
		public void setcurrentconfigvalues() {
			
			
			
			if (activeCEFFlag) {
				c1.setCefboolean(true);
			}
			
			if (activeMplsFlag) {
				c1.setMplsipboolean(true);
				c1.setVrfname(textFieldvrf.getText());
				c1.setVrfrd(textFieldRD.getText());
				c1.setVrfrt(textFieldRT.getText());
			}
						
			if (activeBGPFlag) {
				c1.setBgpflag(true);
				c1.setBgpProcess(textFieldProcesoBGP.getText());
				
			}
			if (activeEigrpFlag) {
				c1.setEigrpflag(true);
				c1.setEigrpProcess(textFieldProcesoEigrp.getText());
			}
			;
			configResultView.setText("");
			configResultView.append(c1.listCEF());
			configResultView.append(c1.listMPLS());
			configResultView.append(c1.listBGP());
			configResultView.append(c1.listEIGRP());
			
			c1.listConfig();
			
					// Parte de Configuracion de las rutas
					for (int i = 0; i < count; i++) {
						System.out.println("ruta:" + rutas[i].getText());
						System.out.println("mascara: " + mascara[i].getText());
						//mover los valores del arreglo de jtexfield a una variable temporal de tipo String
						String temprutas[] = new String[count];
						String tempmascaras[] = new String[count];
						//crear e inicializar arreglos temporales para las rutas y mascaras
						String tempr= rutas[i].getText();
						String tempm= mascara[i].getText();
						//pasarles el valor de lo jtexfield correspondiente que estan contenidas en las variales temporales
						temprutas[i]=tempr;
						tempmascaras[i]=tempm;
						//guardar los valores en el objeto configuracion para su almacenado en BD y posterior confeccion de logs
						c1.setRutas(temprutas);
						c1.setMascaras(tempmascaras);
						configResultView.append("Ruta:" + c1.getRutas()[i] + " Mascara:" + c1.getMascaras()[i]+"\n");
					}
				
					//Parte de Configuracion de las parte de mpls sobre las interfaces
						String ips[] = new String[4];
						String masks[] = new String[4];
						boolean mpslip[] = new boolean[4];
						String forwardingVRF[] = new String[4];
						
						interfacesbysnmp.add(0, comboBoxInterface1.getSelectedItem().toString());
						interfacesbysnmp.add(1, comboBoxInterface1.getSelectedItem().toString());		
						interfacesbysnmp.add(2, comboBoxInterface1.getSelectedItem().toString());
						interfacesbysnmp.add(3, comboBoxInterface1.getSelectedItem().toString());
						c1.setInterfaces(interfacesbysnmp);
						
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
						
						for (int j = 0; j < 4; j++) {
							configResultView.append("Interface: " + c1.getInterfaces(j) + 
									" |IP: " + c1.getIpsInterfaces()[j] + " |Mascara: " + c1.getMasksInterfaces()[j] + 
									" |Forwarding VRF:" + c1.getForwardingVRF()[j] + "\n"
									);
						}
}	
		
		
		
	public String addroute(JPanel jpgeneric, int y, int count) {
		
			rutas[count]= new JTextField("ruta");
			rutas[count].setVisible(true);
			rutas[count].setBounds(10, y, 135, 21);
			rutas[count].validate();

		    jpgeneric.add(rutas[count]);
		    
		    return rutas[count].getText();
		    
	
	}


	public String addroutemask(JPanel jpgeneric, int y, int count) {
			mascara[count]  = new JTextField("mascara");
			mascara[count].setVisible(true);
			mascara[count].setBounds(151, y,135 , 21);
		    jpgeneric.add(mascara[count]);
		    return mascara[count].getText();
	
	}
	
	
	


		
	public boolean  checkOverlapingInterface() {
		ActionListener actionListener = new ActionListener() {
			@Override
			// TODO Reprogramar este metodo para que valide correctamente
			public void actionPerformed(ActionEvent e) {// chequear la validacion
				
				
				
				
				
				if (comboBoxInterface1.getSelectedItem() == interfacesbysnmp.get(0)) {
					interfacesbysnmp.remove(0);
					comboBoxInterface2.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
					
							}
				if (comboBoxInterface2.getSelectedItem() == interfacesbysnmp.get(1-1)) {
					interfacesbysnmp.remove(1);
					comboBoxInterface3.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
					
				}
				if (comboBoxInterface3.getSelectedItem() == interfacesbysnmp.get(2-1)) {
					interfacesbysnmp.remove(2);
					comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				}
				if (comboBoxInterface4.getSelectedItem() == interfacesbysnmp.get(3-1)) {
					//interfacesbysnmp.remove(3);
					comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp.toArray()));
				}
			}
				
			};
		
		comboBoxInterface1.addActionListener(actionListener);
		comboBoxInterface2.addActionListener(actionListener);
		comboBoxInterface3.addActionListener(actionListener);
		comboBoxInterface4.addActionListener(actionListener);
		boolean resultado = true;
		return resultado ;
	}

	
	 public boolean  checkActiveCheckBGP() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveBGP.isSelected()) {
					 textFieldProcesoBGP.setEditable(true);
					 checkboxActiveEigrp.setEnabled(false);
					 textFieldProcesoEigrp.setEditable(false);
					 activeBGPFlag=true;
					
				}
				 if (checkboxActiveBGP.isSelected() == false) {
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveEigrp.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					 activeBGPFlag=false;
					
				}			}
		};
		checkboxActiveBGP.addItemListener(itemlistener);
		return activeBGPFlag;
	 }
	 
	 public boolean  checkActiveCheckEigrp() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveEigrp.isSelected()) {
					 textFieldProcesoEigrp.setEditable(true);
					 checkboxActiveBGP.setEnabled(false);
					 textFieldProcesoBGP.setEditable(false);
					 activeEigrpFlag=true;
					 
					
				}
				 if (checkboxActiveEigrp.isSelected() == false) {
					 textFieldProcesoBGP.setEditable(false);
					 checkboxActiveBGP.setEnabled(true);
					 textFieldProcesoEigrp.setEditable(false);
					 activeEigrpFlag=false;
				}			}
		};
		checkboxActiveEigrp.addItemListener(itemlistener);
		return activeEigrpFlag;
	}
	
	 public boolean  checkActiveCheckCEF() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveCEF.isSelected()) {
					 activeCEFFlag = true;
					 ;
				}
				 if (checkboxActiveMpls.isSelected() == false) {
					 activeCEFFlag = false;
					
				}			}
			
			
		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return activeCEFFlag;
	}
	 
	 
	 
	 
	 
	 public boolean  checkActiveCheckMpls() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxActiveMpls.isSelected()) {
					 activeVRF();
					 activeMplsFlag = true;
					 ;
				}
				 if (checkboxActiveMpls.isSelected() == false) {
					 desactiveVRF();
					 activeMplsFlag = false;
					
				}			}
			
			
		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return activeMplsFlag;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 public void  checkActiveCheckVrf() {
		 ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 if (checkboxVrf.isSelected()) {
					 activeVRF();
					
				}
				 if (checkboxVrf.isSelected() == false) {
					desactiveVRF();
					
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
				desactiveVRF();
				activeCPEFlag=true;
				
			}
			if (checkboxCPE.isSelected() == false) {
				checkboxP.setEnabled(true);
				checkboxPE.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				desactiveVRF();
				activeCPEFlag = false;

			}
		}
	};
	checkboxCPE.addItemListener(itemlistenerActiveDeviceCPE);
	return activeCPEFlag;
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
				activePEFlag=true;
			}
			if (checkboxPE.isSelected() == false) {
				checkboxCPE.setEnabled(true);
				checkboxP.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				checkboxActiveMpls.setSelected(false);
				desactiveVRF();
				activePEFlag=false;
			}
		}
	};
	checkboxPE.addItemListener(itemlistenerActiveDevicePE);
	return activePEFlag;
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
				activePFlag=true;
			}
			if (checkboxP.isSelected() == false) {
				checkboxCPE.setEnabled(true);
				checkboxPE.setEnabled(true);
				checkboxActiveMpls.setEnabled(false);
				checkboxActiveMpls.setSelected(false);
				desactiveVRF();
				activePFlag=false;
			}
		}
	};
	checkboxP.addItemListener(itemlistenerActiveDeviceP);
	return activePFlag;
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

	public boolean isCheckboxCPEFlag() {
		return activeCPEFlag;
	}

	public void setCheckboxCPEFlag(boolean checkboxCPEFlag) {
		this.activeCPEFlag = checkboxCPEFlag;
	}

	public JCheckBox getCheckboxPE() {
		return checkboxPE;
	}

	public void setCheckboxPE(JCheckBox checkboxPE) {
		this.checkboxPE = checkboxPE;
	}

	public boolean isCheckboxPEFlag() {
		return activePEFlag;
	}

	public void setCheckboxPEFlag(boolean checkboxPEFlag) {
		this.activePEFlag = checkboxPEFlag;
	}

	public JCheckBox getCheckboxP() {
		return checkboxP;
	}

	public void setCheckboxP(JCheckBox checkboxP) {
		this.checkboxP = checkboxP;
	}

	public boolean isCheckboxPFlag() {
		return activePFlag;
	}

	public void setCheckboxPFlag(boolean checkboxPFlag) {
		this.activePFlag = checkboxPFlag;
	}

	public JCheckBox getCheckboxActiveMpls() {
		return checkboxActiveMpls;
	}

	public void setCheckboxActiveMpls(JCheckBox checkboxActiveMpls) {
		this.checkboxActiveMpls = checkboxActiveMpls;
	}

	public boolean isCheckboxActiveMplsFlag() {
		return activeMplsFlag;
	}

	public void setCheckboxActiveMplsFlag(boolean checkboxActiveMplsFlag) {
		this.activeMplsFlag = checkboxActiveMplsFlag;
	}

	public JCheckBox getCheckboxActiveCEF() {
		return checkboxActiveCEF;
	}

	public void setCheckboxActiveCEF(JCheckBox checkboxActiveCEF) {
		this.checkboxActiveCEF = checkboxActiveCEF;
	}

	public boolean isCheckboxActiveCEFFlag() {
		return activeCEFFlag;
	}

	public void setCheckboxActiveCEFFlag(boolean checkboxActiveCEFFlag) {
		this.activeCEFFlag = checkboxActiveCEFFlag;
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

	public boolean isCheckboxBGPFlag() {
		return activeBGPFlag;
	}

	public void setCheckboxBGPFlag(boolean checkboxBGPFlag) {
		this.activeBGPFlag = checkboxBGPFlag;
	}

	public boolean isCheckboxEigrpFlag() {
		return activeEigrpFlag;
	}

	public void setCheckboxEigrpFlag(boolean checkboxEigrpFlag) {
		this.activeEigrpFlag = checkboxEigrpFlag;
	}

	public JTextField getTextFieldProcesoEigrp() {
		return textFieldProcesoEigrp;
	}

	public void setTextFieldProcesoEigrp(JTextField textFieldProcesoEigrp) {
		this.textFieldProcesoEigrp = textFieldProcesoEigrp;
	}



	public void desactiveVRF() {
		checkboxVrf.setEnabled(false);
		 checkboxVrf.setSelected(false);
		 textFieldvrf.setEditable(false);
		 checkboxRD.setEnabled(false);
		 checkboxRD.setSelected(false);
		 textFieldRD.setEditable(false);
		 checkboxRT.setEnabled(false);
		 checkboxRT.setSelected(false);
		 textFieldRT.setEditable(false);
	}

	public void activeVRF() {
		checkboxVrf.setEnabled(true);
		 checkboxVrf.setSelected(true);
		 textFieldvrf.setEditable(true);
		 checkboxRD.setEnabled(true);
		 checkboxRD.setSelected(true);
		 textFieldRD.setEditable(true);
		 checkboxRT.setEnabled(true);
		 checkboxRT.setSelected(true);
		 textFieldRT.setEditable(true);
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

	
	
	
	

