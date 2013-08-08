package orq.mpls.org;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import javax.swing.ScrollPaneConstants;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

@SuppressWarnings("serial")
public class CMpls extends JDialog {
	/**
	 * 
	 */
	// Comentario realizado 08-08-2013
	private String ip;
	private String puerto;
	private String comunity;
	private String usuario;
	private String password;
	private DefaultTableModel modelo;

	// TODO descomentar esta linea para generar objeto que hable SNMP

	private JTextField jtextProcesoBGP;
	private JCheckBox checkboxActiveBGP;
	private JTextField jtextVrf;
	private JCheckBox checkboxCPE;
	private JCheckBox checkboxPE;
	private JCheckBox checkboxP;
	private JCheckBox checkboxActiveMpls;
	private JCheckBox checkboxActiveCEF;
	private JCheckBox checkboxVrf;
	private CurrentConfig c1 = new CurrentConfig();
	private JCheckBox checkboxActiveEigrp;
	private JTextField jtextProcesoEigrp;
	private JPanel jp1;
	private JLabel mensajelimiterutas;
	private JTextField jtextRD;
	private JTextField jtextRT;
	private int countestatic = 0;
	private int countDinamic = 0;
	private JCheckBox checkboxRt;
	private JCheckBox checkboxRd;
	private JTextArea configResultView;
	private JScrollPane scrollPane;
	private JTextField interfaceIP1;
	private JTextField interfaceMask1;
	private JTextField interfaceForwardingVRF1;
	private JTextField interfaceIP2;
	private JTextField interfaceMask2;
	private JTextField interfaceForwardingVRF2;
	private JTextField interfaceIP3;
	private JTextField interfaceMask3;
	private JTextField interfaceForwardingVRF3;
	private JTextField interfaceIP4;
	private JTextField interfaceMask4;
	private JTextField interfaceForwardingVRF4;
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
	private JTextField jtextNeighborASExternal;
	private JTextField jtextNeighborIPExternal;
	private JLabel labelRutasBGP_EIGRP;
	private JLabel labelMaskDinamic;
	private JButton buttonAddRutas_Mask_Dinamic;
	private JLabel labelProcesoBGP;
	private JLabel labelProcessEigrp;
	private JLabel labelAsExternal;
	private JLabel labelVecinoBgpExternal;
	private JLabel labelRedDestinoEstatic;
	private JLabel labelMaskEstatic;
	private JLabel labelRedOrigenEstatic;

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
	private JTextField jtextAsInternal;
	private JTextField jtextNeighborIPInternal;
	private JLabel labelASInternal;
	private JLabel labelExternal;
	private JLabel labelVecinoBGPInternal;
	private JLabel iconCPE;
	private JPanel jp2;
	private JTextArea configGenViewRoutingTable;
	private JScrollPane monitoringnetwork;
	private JButton btnActualizarRoutingTable;
	private JButton btnActualizarIPInterfacesBrief;
	private JTextArea configGenViewIPInterfaceBrief;
	private JScrollPane scrollPane_2;
	private JLabel iconParamConfig;
	private JLabel IconResultAppConfig;
	private JTable jtable;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	private JTextArea configResultViewlogs;
	private JLabel lblNewLabel_11;
	private JCheckBox checkBoxLine1;
	private JCheckBox checkBoxLine2;
	private JCheckBox checkBoxLine3;
	private JCheckBox checkBoxLine4;

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

		// con esta linea se hace que cuando la ventana es instanciada esta se
		// inicie maximizada
		this.setSize(this.getToolkit().getScreenSize());

		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);

		jp1 = new JPanel(null);
		JLabel jl1 = new JLabel("MPLS");
		jl1.setBounds(10, 0, 123, 35);
		jp1.add(jl1);
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				"images/1.png"));
		tabbedPane.addTab("MPLS", icon, jp1, "primer panel");

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

		jtextVrf = new JTextField("vrf");
		jtextVrf.setBounds(63, 89, 80, 22);
		jtextVrf.setEditable(false);
		jp1.add(jtextVrf);
		jtextVrf.setColumns(10);

		JLabel labelProtocoloRuteo = new JLabel("Protocolos de Ruteo");
		labelProtocoloRuteo.setBounds(10, 111, 230, 35);
		jp1.add(labelProtocoloRuteo);

		checkboxActiveBGP = new JCheckBox("BGP");
		checkboxActiveBGP.setSelected(false);
		checkboxActiveBGP.setBounds(10, 144, 58, 23);
		jp1.add(checkboxActiveBGP);

		labelProcesoBGP = new JLabel("Proceso");
		labelProcesoBGP.setBounds(84, 144, 65, 23);
		labelProcesoBGP.setEnabled(false);
		jp1.add(labelProcesoBGP);

		jtextProcesoBGP = new JTextField();
		jtextProcesoBGP.setBounds(151, 143, 89, 22);
		jtextProcesoBGP.setEditable(false);
		jtextProcesoBGP.setEnabled(false);
		jp1.add(jtextProcesoBGP);
		jtextProcesoBGP.setColumns(10);

		checkboxActiveEigrp = new JCheckBox("EIGRP");
		checkboxActiveEigrp.setSelected(false);
		checkboxActiveEigrp.setBounds(10, 188, 65, 23);
		jp1.add(checkboxActiveEigrp);

		labelProcessEigrp = new JLabel("Proceso");
		labelProcessEigrp.setBounds(84, 188, 65, 20);
		labelProcessEigrp.setEnabled(false);
		jp1.add(labelProcessEigrp);

		jtextProcesoEigrp = new JTextField();
		jtextProcesoEigrp.setBounds(151, 188, 89, 22);
		jtextProcesoEigrp.setEditable(false);
		jtextProcesoEigrp.setEnabled(false);
		jp1.add(jtextProcesoEigrp);
		jtextProcesoBGP.setColumns(10);
		// Final Componentes Panel 1

		botonesCancel_Enviar(jp1);

		mensajelimiterutas = new JLabel("");
		mensajelimiterutas.setBounds(10, 602, 804, 15);
		jp1.add(mensajelimiterutas);

		checkboxRd = new JCheckBox("RD");
		checkboxRd.setBounds(148, 89, 53, 23);
		checkboxRd.setEnabled(false);
		jp1.add(checkboxRd);

		checkboxRt = new JCheckBox("RT");
		checkboxRt.setBounds(302, 89, 53, 23);
		checkboxRt.setEnabled(false);
		jp1.add(checkboxRt);

		jtextRD = new JTextField();
		jtextRD.setToolTipText("Formato RD : ASN:nn");
		jtextRD.setBounds(202, 91, 80, 22);
		jtextRD.setEditable(false);
		jp1.add(jtextRD);
		jtextRD.setColumns(10);

		jtextRT = new JTextField();
		jtextRT.setToolTipText("Formato RD : ASN:nn");
		jtextRT.setColumns(10);
		jtextRT.setBounds(355, 91, 80, 22);
		jtextRT.setEditable(false);
		jp1.add(jtextRT);

		JLabel labelParamConfig = new JLabel("Parametros de Configuracion");
		labelParamConfig.setBounds(863, 10, 218, 15);
		jp1.add(labelParamConfig);
		// jp1.add(textArea);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(826, 37, 517, 196);
		// scrollPane.add(textArea);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1.add(scrollPane);

		configResultView = new JTextArea();
		scrollPane.setViewportView(configResultView);
		configResultView.setLineWrap(true);
		configResultView.setEditable(false);

		// segundo jtexarea para mostrar los comandos
		JLabel labelResulAppConfig = new JLabel(
				"Resultado de Aplicacion de Configuracion  ");
		labelResulAppConfig.setBounds(863, 253, 314, 15);
		jp1.add(labelResulAppConfig);
		// jp1.add(textArea);

		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(826, 280, 517, 337);
		// scrollPane.add(textArea);
		scrollPane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1.add(scrollPane1);

		configGenView = new JTextArea();
		scrollPane1.setViewportView(configGenView);
		configGenView.setLineWrap(true);
		configGenView.setEditable(false);
		// fin segundo jtexarea

		JLabel labelInterface = new JLabel("Interfaces");
		labelInterface.setBounds(12, 243, 95, 15);
		jp1.add(labelInterface);

		JLabel labelInterfaceIP = new JLabel("IP");
		labelInterfaceIP.setBounds(151, 243, 37, 15);
		jp1.add(labelInterfaceIP);

		JLabel labelInterfaceMask = new JLabel("Mascara");
		labelInterfaceMask.setBounds(275, 243, 70, 15);
		jp1.add(labelInterfaceMask);

		JLabel labelInterfaceMpls = new JLabel("MPLS ");
		labelInterfaceMpls.setBounds(401, 243, 70, 15);
		jp1.add(labelInterfaceMpls);

		JLabel labelForwardingVrf = new JLabel("Forwarding VRF");
		labelForwardingVrf.setBounds(461, 243, 132, 15);
		jp1.add(labelForwardingVrf);

		interfaceIP1 = new JTextField();
		interfaceIP1.setText("None");
		interfaceIP1.setBounds(151, 270, 114, 22);
		jp1.add(interfaceIP1);
		interfaceIP1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceIP1.selectAll();
			}
		});
		interfaceIP1.setColumns(10);
		interfaceIP1.setEnabled(false);
		interfaceMask1 = new JTextField();
		interfaceMask1.setText("None");
		interfaceMask1.setBounds(275, 270, 114, 22);
		jp1.add(interfaceMask1);
		interfaceMask1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceMask1.selectAll();
			}
		});

		interfaceMask1.setColumns(10);
		interfaceMask1.setEnabled(false);

		checkboxMPLSIP1 = new JCheckBox("");
		checkboxMPLSIP1.setBounds(401, 269, 45, 23);
		jp1.add(checkboxMPLSIP1);

		interfaceForwardingVRF1 = new JTextField();
		interfaceForwardingVRF1.setText("None");
		interfaceForwardingVRF1.setBounds(461, 270, 114, 22);
		jp1.add(interfaceForwardingVRF1);
		interfaceForwardingVRF1.setColumns(10);
		interfaceForwardingVRF1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceForwardingVRF1.selectAll();
			}
		});
		interfaceForwardingVRF1.setEnabled(false);
		interfaceIP2 = new JTextField();
		interfaceIP2.setText("None");
		interfaceIP2.setColumns(10);
		interfaceIP2.setBounds(151, 305, 114, 22);
		jp1.add(interfaceIP2);
		interfaceIP2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceIP2.selectAll();
			}
		});

		interfaceMask2 = new JTextField();
		interfaceMask2.setText("None");
		interfaceMask2.setColumns(10);
		interfaceMask2.setBounds(275, 305, 114, 22);
		jp1.add(interfaceMask2);
		interfaceMask2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceMask2.selectAll();
			}
		});

		checkboxMPLSIP2 = new JCheckBox("");
		checkboxMPLSIP2.setBounds(401, 304, 45, 23);
		jp1.add(checkboxMPLSIP2);

		interfaceForwardingVRF2 = new JTextField();
		interfaceForwardingVRF2.setText("None");
		interfaceForwardingVRF2.setColumns(10);
		interfaceForwardingVRF2.setBounds(461, 305, 114, 22);
		jp1.add(interfaceForwardingVRF2);
		interfaceForwardingVRF2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceForwardingVRF2.selectAll();
			}
		});
		interfaceIP2.setEnabled(false);
		interfaceMask2.setEnabled(false);
		interfaceForwardingVRF2.setEnabled(false);

		interfaceIP3 = new JTextField();
		interfaceIP3.setText("None");
		interfaceIP3.setColumns(10);
		interfaceIP3.setBounds(151, 340, 114, 22);
		jp1.add(interfaceIP3);
		interfaceIP3.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceIP3.selectAll();
			}
		});

		interfaceMask3 = new JTextField();
		interfaceMask3.setText("None");
		interfaceMask3.setColumns(10);
		interfaceMask3.setBounds(275, 340, 114, 22);
		jp1.add(interfaceMask3);
		interfaceMask3.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceMask3.selectAll();
			}
		});

		checkboxMPLSIP3 = new JCheckBox("");
		checkboxMPLSIP3.setBounds(401, 339, 45, 23);
		jp1.add(checkboxMPLSIP3);

		interfaceForwardingVRF3 = new JTextField();
		interfaceForwardingVRF3.setText("None");
		interfaceForwardingVRF3.setColumns(10);
		interfaceForwardingVRF3.setBounds(461, 340, 114, 22);
		jp1.add(interfaceForwardingVRF3);
		interfaceForwardingVRF3.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceForwardingVRF3.selectAll();
			}
		});
		interfaceIP3.setEnabled(false);
		interfaceMask3.setEnabled(false);
		interfaceForwardingVRF3.setEnabled(false);

		interfaceIP4 = new JTextField();
		interfaceIP4.setText("None");
		interfaceIP4.setColumns(10);
		interfaceIP4.setBounds(151, 371, 114, 22);
		jp1.add(interfaceIP4);
		interfaceIP4.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceIP4.selectAll();
			}
		});
		interfaceMask4 = new JTextField();
		interfaceMask4.setText("None");
		interfaceMask4.setColumns(10);
		interfaceMask4.setBounds(275, 371, 114, 22);
		jp1.add(interfaceMask4);
		interfaceMask4.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceMask4.selectAll();
			}
		});

		checkboxMPLSIP4 = new JCheckBox("");
		checkboxMPLSIP4.setBounds(401, 370, 45, 23);
		jp1.add(checkboxMPLSIP4);

		interfaceForwardingVRF4 = new JTextField();
		interfaceForwardingVRF4.setText("None");
		interfaceForwardingVRF4.setColumns(10);
		interfaceForwardingVRF4.setBounds(461, 371, 114, 22);
		jp1.add(interfaceForwardingVRF4);
		interfaceForwardingVRF4.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				interfaceForwardingVRF4.selectAll();
			}
		});
		interfaceIP4.setEnabled(false);
		interfaceMask4.setEnabled(false);
		interfaceForwardingVRF4.setEnabled(false);

		labelRedOrigenEstatic = new JLabel("Red Origen");
		labelRedOrigenEstatic.setBounds(10, 444, 114, 15);
		jp1.add(labelRedOrigenEstatic);

		labelMaskEstatic = new JLabel("Mascara");
		labelMaskEstatic.setBounds(130, 444, 70, 15);
		jp1.add(labelMaskEstatic);

		labelRedDestinoEstatic = new JLabel("Red Destino");
		labelRedDestinoEstatic.setBounds(250, 444, 114, 15);
		jp1.add(labelRedDestinoEstatic);

		// enviar configuracion al dispositivo
		JButton BotonEnviar = new JButton("Enviar ");
		BotonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SendCurrentConfig SConfig = new SendCurrentConfig(c1,
							configGenView);

				} catch (Exception e2) {
					// TODO: handle exception

				}

			}
		});
		BotonEnviar.setBounds(920, 629, 114, 23);
		jp1.add(BotonEnviar);

		labelAsExternal = new JLabel("AS");
		labelAsExternal.setBounds(302, 144, 37, 23);
		labelAsExternal.setEnabled(false);
		jp1.add(labelAsExternal);

		jtextNeighborASExternal = new JTextField();
		jtextNeighborASExternal.setBounds(325, 144, 65, 22);
		jtextNeighborASExternal.setEnabled(false);
		jp1.add(jtextNeighborASExternal);
		jtextNeighborASExternal.setColumns(10);

		labelVecinoBgpExternal = new JLabel("Vecino BGP");
		labelVecinoBgpExternal.setBounds(405, 144, 89, 23);
		labelVecinoBgpExternal.setEnabled(false);
		jp1.add(labelVecinoBgpExternal);

		jtextNeighborIPExternal = new JTextField();
		jtextNeighborIPExternal.setBounds(509, 144, 114, 22);
		jtextNeighborIPExternal.setEnabled(false);
		jp1.add(jtextNeighborIPExternal);
		jtextNeighborIPExternal.setColumns(10);

		labelRutasBGP_EIGRP = new JLabel("Rutas BGP/EIGRP");
		labelRutasBGP_EIGRP.setBounds(461, 444, 133, 15);
		jp1.add(labelRutasBGP_EIGRP);

		labelMaskDinamic = new JLabel("Mascara");
		labelMaskDinamic.setBounds(589, 444, 70, 15);
		jp1.add(labelMaskDinamic);

		ImageIcon plus = new ImageIcon(this.getClass().getResource(
				"images/3.png"));
		buttonAddRutas_Mask = new JButton(plus);
		// agrego el boton + al panel
		buttonAddRutas_Mask.setBounds(345, 440, 44, 22);
		jp1.add(buttonAddRutas_Mask);
		buttonAddRutas_Mask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countestatic++;
				if (countestatic == 1) {
					rorigen.setVisible(true);
					rmascara.setVisible(true);
					rdestino.setVisible(true);
					rorigen.setEnabled(true);
					rmascara.setEnabled(true);
					rdestino.setEnabled(true);
				}
				if (countestatic == 2) {
					rorigen1.setVisible(true);
					rmascara1.setVisible(true);
					rdestino1.setVisible(true);
					rorigen1.setEnabled(true);
					rmascara1.setEnabled(true);
					rdestino1.setEnabled(true);
				}
				if (countestatic == 3) {
					rorigen2.setVisible(true);
					rmascara2.setVisible(true);
					rdestino2.setVisible(true);
					rorigen2.setEnabled(true);
					rmascara2.setEnabled(true);
					rdestino2.setEnabled(true);
				}

				if (countestatic > 2) {
					mensajelimiterutas
							.setText("Solo se permite un maximo de 3 rutas (Estaticas/Dinamicas) por envio de configuracion...");
					countestatic = 3;

				}

			}// action performed boton mas para agregar rutas ok
		});

		buttonAddRutas_Mask_Dinamic = new JButton(plus);
		buttonAddRutas_Mask_Dinamic.setEnabled(false);
		buttonAddRutas_Mask_Dinamic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO codigo para generar botones de rutas BGP / EIGRP
				countDinamic++;
				if (checkboxActiveBGP.isSelected()
						| checkboxActiveEigrp.isSelected()) {
					if (rdorigen.isVisible() && rdmascara.isVisible()) {

						if (countDinamic == 2) {
							rdorigen1.setVisible(true);
							rdmascara1.setVisible(true);
							rdorigen1.setEnabled(true);
							rdmascara1.setEnabled(true);
						}

						if (countDinamic == 3) {
							rdorigen2.setVisible(true);
							rdmascara2.setVisible(true);
							rdorigen1.setEnabled(false);
							rdmascara2.setEnabled(true);

						}

					}
				}
				if (countDinamic > 2) {
					mensajelimiterutas
							.setText("Solo se permite un maximo de 3 rutas (Estaticas/Dinamicas) por envio de configuracion...");
					countDinamic = 3;
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

		jtextAsInternal = new JTextField();
		jtextAsInternal.setEnabled(false);
		jtextAsInternal.setColumns(10);
		jtextAsInternal.setBounds(327, 188, 65, 22);
		jp1.add(jtextAsInternal);

		labelVecinoBGPInternal = new JLabel("Vecino BGP");
		labelVecinoBGPInternal.setEnabled(false);
		labelVecinoBGPInternal.setBounds(407, 188, 89, 23);
		jp1.add(labelVecinoBGPInternal);

		jtextNeighborIPInternal = new JTextField();
		jtextNeighborIPInternal.setEnabled(false);
		jtextNeighborIPInternal.setColumns(10);
		jtextNeighborIPInternal.setBounds(511, 188, 114, 22);
		jp1.add(jtextNeighborIPInternal);
		JLabel iconP = new JLabel(new ImageIcon(this.getClass().getResource(
				"images/ip.png")));
		iconP.setBounds(434, 37, 24, 24);
		jp1.add(iconP);

		iconCPE = new JLabel(new ImageIcon(this.getClass().getResource(
				"images/irouter.png")));
		iconCPE.setBounds(275, 37, 24, 24);
		jp1.add(iconCPE);

		JLabel iconPE = new JLabel(new ImageIcon(this.getClass().getResource(
				"images/ipe.png")));
		iconPE.setBounds(354, 37, 24, 24);
		jp1.add(iconPE);

		iconParamConfig = new JLabel(new ImageIcon(this.getClass().getResource(
				"images/config.png")));
		iconParamConfig.setBounds(826, 0, 24, 24);
		jp1.add(iconParamConfig);

		IconResultAppConfig = new JLabel(new ImageIcon(this.getClass()
				.getResource("images/configresult.png")));
		IconResultAppConfig.setBounds(826, 245, 24, 24);
		jp1.add(IconResultAppConfig);

		checkBoxLine1 = new JCheckBox("");
		checkBoxLine1.setBounds(587, 269, 45, 23);
		jp1.add(checkBoxLine1);

		checkBoxLine2 = new JCheckBox("");
		checkBoxLine2.setBounds(587, 304, 45, 23);
		jp1.add(checkBoxLine2);

		checkBoxLine3 = new JCheckBox("");
		checkBoxLine3.setBounds(587, 339, 45, 23);
		jp1.add(checkBoxLine3);

		checkBoxLine4 = new JCheckBox("");
		checkBoxLine4.setBounds(587, 370, 45, 23);
		jp1.add(checkBoxLine4);

		JLabel labelHabilitarLineInterfaces = new JLabel("Habilitar ");
		labelHabilitarLineInterfaces.setBounds(587, 243, 70, 15);
		jp1.add(labelHabilitarLineInterfaces);

		jp2 = new JPanel(null);
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource(
				"images/2.png"));
		tabbedPane.addTab("Monitoring Network", icon2, jp2, "segundo panel");
		// coloco los botones cancelar y enviar en el tab monitoring Network
		monitoringnetwork = new JScrollPane();
		monitoringnetwork.setBounds(53, 37, 517, 337);
		monitoringnetwork
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2.add(monitoringnetwork);

		configGenViewRoutingTable = new JTextArea();
		monitoringnetwork.setViewportView(configGenViewRoutingTable);
		configGenViewRoutingTable.setLineWrap(true);
		configGenViewRoutingTable.setEditable(false);

		JLabel lblmonitoringroutingtable = new JLabel("Tabla de Ruteo  ");
		lblmonitoringroutingtable.setBounds(53, 5, 314, 22);
		jp2.add(lblmonitoringroutingtable);

		scrollPane_2 = new JScrollPane();
		scrollPane_2
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(631, 37, 713, 337);
		jp2.add(scrollPane_2);

		configGenViewIPInterfaceBrief = new JTextArea();
		scrollPane_2.setViewportView(configGenViewIPInterfaceBrief);
		configGenViewIPInterfaceBrief.setLineWrap(true);
		configGenViewIPInterfaceBrief.setEditable(false);

		JLabel label = new JLabel("Monitoring Interfaces  ");
		label.setBounds(631, 5, 314, 22);
		jp2.add(label);
		ImageIcon refresh = new ImageIcon(this.getClass().getResource(
				"images/refresh.png"));
		btnActualizarRoutingTable = new JButton("Actualizar", refresh);
		btnActualizarRoutingTable.setBounds(435, 5, 135, 24);
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
		btnActualizarIPInterfacesBrief.setBounds(1209, 5, 135, 24);
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

		// Panel 3 Logs Configuraciones
		JPanel jp3 = new JPanel(null);
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource(
				"images/slogs.png"));
		tabbedPane.addTab("Logs Configuraciones", icon3, jp3, "tercer panel");
		// coloco los botones cancelar y enviar en el tab monitoring Network
		// botonesCancel_Enviar(jp3);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 12, 430, 115);
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp3.add(scrollPane_1);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 179, 430, 251);
		jp3.add(scrollPane_3);

		configResultViewlogs = new JTextArea();
		scrollPane_3.setViewportView(configResultViewlogs);

		JButton btnNewButton_2 = new JButton("Mostrar", new ImageIcon(this
				.getClass().getResource("images/logs.png")));
		btnNewButton_2.setBounds(452, 12, 100, 24);
		jp3.add(btnNewButton_2);

		JLabel lblNewLabel_10 = new JLabel("Configuracion");
		lblNewLabel_10.setIcon(new ImageIcon(this.getClass().getResource(
				"images/log.png")));
		lblNewLabel_10.setBounds(12, 150, 150, 17);
		jp3.add(lblNewLabel_10);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				configResultViewlogs.setText("");
				mostrarvaloresseleccionados();

			}
		});

		// mejorar este codigo con singleton
		rellenarTabla();

		// Panel 4 Futura Implementacion Calidad de Servicio
		JPanel jp4 = new JPanel(null);
		tabbedPane.addTab("Calidad de Servicio (QoS)", new ImageIcon(
				"images/qos-icon.png"), jp4, "tercer panel");

		lblNewLabel_11 = new JLabel("Futura Implementacion en Etapa 2... ");
		lblNewLabel_11.setBounds(0, 42, 554, 17);
		jp4.add(lblNewLabel_11);

		// chequeos de los listeners para saber que se esta seleccionando al
		// instanciar objeto CMpls
		this.checkActiveDeviceCPE();
		this.checkActiveDevicePE();
		this.checkActiveDeviceP();
		this.checkActiveCheckBGP();
		this.checkActiveCheckEigrp();
		this.checkActiveCheckMpls();
		this.checkActiveLine1();
		this.checkActiveLine2();
		this.checkActiveLine3();
		this.checkActiveLine4();
	}// Final Constructor CMPLS

	/**
	 * 
	 */
	public void connectSnmp() {
		interfacesbysnmp = new ArrayList<>();
		try {

			// TODO descomentar esta linea para generar objeto que hable SNMP
			// JOptionPane.showMessageDialog(null, this.ip+this.comunity);
			SNMPUtils con1 = new SNMPUtils(this.ip, this.comunity);
			interfacesbysnmp = con1.printInterface();
			interfacesbysnmp.add(0, "None");
		} catch (Exception e2) {// cambiar por IOException

			JOptionPane message = new JOptionPane();
			message.showMessageDialog(this, "No se registro Conexion SNMP",
					"Mensaje SNMP", JOptionPane.INFORMATION_MESSAGE);
			// TODO descomentar esta linea si quiero ver el contenido de la
			// Exepcion
			// e2.printStackTrace();

		}

		comboBoxInterface1 = new JComboBox();
		comboBoxInterface1.setEnabled(false);
		comboBoxInterface1.setEditable(true);
		comboBoxInterface1.setToolTipText("Interfaces Equipo");
		comboBoxInterface1.setModel(new DefaultComboBoxModel(interfacesbysnmp
				.toArray()));
		comboBoxInterface1.setBounds(10, 271, 133, 22);
		jp1.add(comboBoxInterface1);

		comboBoxInterface2 = new JComboBox();
		comboBoxInterface2.setEnabled(false);
		comboBoxInterface2.setEditable(true);
		comboBoxInterface2.setToolTipText("Interfaces Equipo");
		comboBoxInterface2.removeAllItems();
		comboBoxInterface2.setModel(new DefaultComboBoxModel(interfacesbysnmp
				.toArray()));
		comboBoxInterface2.setBounds(10, 305, 133, 22);
		jp1.add(comboBoxInterface2);

		comboBoxInterface3 = new JComboBox();
		comboBoxInterface3.setEnabled(false);
		comboBoxInterface3.setEditable(true);
		comboBoxInterface3.setToolTipText("Interfaces Equipo");
		comboBoxInterface3.removeAllItems();
		comboBoxInterface3.setModel(new DefaultComboBoxModel(interfacesbysnmp
				.toArray()));
		comboBoxInterface3.setBounds(10, 340, 133, 22);
		jp1.add(comboBoxInterface3);

		comboBoxInterface4 = new JComboBox();
		comboBoxInterface4.setEnabled(false);
		comboBoxInterface4.setEditable(true);
		comboBoxInterface4.setToolTipText("Interfaces Equipo");
		comboBoxInterface4.setModel(new DefaultComboBoxModel(interfacesbysnmp
				.toArray()));
		comboBoxInterface4.setBounds(10, 371, 133, 22);
		jp1.add(comboBoxInterface4);

	}

	public void parametrosconex(String ipp, String puertop, String comunityp,
			String usuariop, char[] cs) {
		// se setean las variable de conexion snmp
		this.ip = ipp;
		this.puerto = puertop;
		this.comunity = comunityp;
		this.usuario = usuariop;
		this.password = "";
		for (int i = 0; i < cs.length; i++) {

			this.password = this.password + cs[i];
		}

	}

	public void addRutasEstaticas() {
		rorigen = new JTextField();
		rorigen.setBounds(10, 471, 114, 22);
		jp1.add(rorigen);
		rorigen.setEnabled(false);
		rorigen.setColumns(10);
		rorigen.setVisible(false);
		rmascara = new JTextField();
		rmascara.setBounds(130, 471, 114, 22);
		jp1.add(rmascara);
		rmascara.setEnabled(false);
		rmascara.setColumns(10);
		rmascara.setVisible(false);
		rdestino = new JTextField();
		rdestino.setBounds(250, 471, 114, 22);
		jp1.add(rdestino);
		rdestino.setColumns(10);
		rdestino.setVisible(false);
		rdestino.setEnabled(false);

		rorigen1 = new JTextField();
		rorigen1.setBounds(10, 501, 114, 22);
		jp1.add(rorigen1);
		rorigen1.setColumns(10);
		rorigen1.setVisible(false);
		rorigen1.setEnabled(false);
		rmascara1 = new JTextField();
		rmascara1.setBounds(130, 501, 114, 22);
		jp1.add(rmascara1);
		rmascara1.setColumns(10);
		rmascara1.setVisible(false);
		rmascara1.setEnabled(false);
		rdestino1 = new JTextField();
		rdestino1.setBounds(250, 501, 114, 22);
		jp1.add(rdestino1);
		rdestino1.setColumns(10);
		rdestino1.setVisible(false);
		rdestino1.setEnabled(false);

		rorigen2 = new JTextField();
		rorigen2.setBounds(10, 531, 114, 22);
		jp1.add(rorigen2);
		rorigen2.setColumns(10);
		rorigen2.setVisible(false);
		rorigen2.setEnabled(false);
		rmascara2 = new JTextField();
		rmascara2.setBounds(130, 531, 114, 22);
		jp1.add(rmascara2);
		rmascara2.setColumns(10);
		rmascara2.setVisible(false);
		rmascara2.setEnabled(false);
		rdestino2 = new JTextField();
		rdestino2.setBounds(250, 531, 114, 22);
		jp1.add(rdestino2);
		rdestino2.setColumns(10);
		rdestino2.setVisible(false);
		rdestino2.setEnabled(false);
	}

	public void addRutasDinamicas() {

		rdorigen = new JTextField();
		rdorigen.setBounds(461, 471, 114, 22);
		jp1.add(rdorigen);
		rdorigen.setColumns(10);
		rdorigen.setVisible(false);
		rdorigen.setEnabled(false);
		rdmascara = new JTextField();
		rdmascara.setBounds(581, 471, 114, 22);
		jp1.add(rdmascara);
		rdmascara.setColumns(10);
		rdmascara.setVisible(false);
		rdmascara.setEnabled(false);

		rdorigen1 = new JTextField();
		rdorigen1.setBounds(461, 501, 114, 22);
		jp1.add(rdorigen1);
		rdorigen1.setColumns(10);
		rdorigen1.setVisible(false);
		rdorigen1.setEnabled(false);

		rdmascara1 = new JTextField();
		rdmascara1.setBounds(581, 501, 114, 22);
		jp1.add(rdmascara1);
		rdmascara1.setColumns(10);
		rdmascara1.setVisible(false);
		rdmascara1.setEnabled(false);

		rdorigen2 = new JTextField();
		rdorigen2.setBounds(461, 531, 114, 22);
		jp1.add(rdorigen2);
		rdorigen2.setColumns(10);
		rdorigen2.setVisible(false);
		rdorigen2.setEnabled(false);

		rdmascara2 = new JTextField();
		rdmascara2.setBounds(581, 531, 114, 22);
		jp1.add(rdmascara2);
		rdmascara2.setColumns(10);
		rdmascara2.setVisible(false);
		rdmascara2.setEnabled(false);

	}

	private void botonesCancel_Enviar(JPanel jpGeneric) {
		JButton botonVerConfigGen = new JButton("Ver Config Generada");
		botonVerConfigGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCurrentConfig();
				Db4manager.storeCurrentconfigObjects(c1);
				Db4manager.listAllconfigObjects();
			}

		});
		botonVerConfigGen.setBounds(707, 629, 190, 23);
		jpGeneric.add(botonVerConfigGen);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCancelar.setBounds(567, 629, 114, 23);
		jpGeneric.add(botonCancelar);
	}

	// fin botones canel_enviar

	protected void saveCurrentConfig() {
		setValoresConexion();
		configResultView.setText("");// limpia el JTexArea cada vez que se
										// apreta el boton ver parametros
		// TODO terminar las validaciones de acuerdo al tipo de dispositivo...
		if (validarcampos()) {
			c1.setCountestatic(countestatic);
			c1.setCountdinamic(countDinamic);
			c1.setIfCpe(checkboxCPE.isSelected());
			c1.setIfPe(checkboxPE.isSelected());
			c1.setIfP(checkboxP.isSelected());
			c1.setCefboolean(checkboxActiveCEF.isSelected());
			c1.setMplsipboolean(checkboxActiveMpls.isSelected());
			c1.setVrfFlag(checkboxVrf.isSelected());
			c1.setVrfname(jtextVrf.getText());
			c1.setVrfrd(jtextRD.getText());
			c1.setVrfrt(jtextRT.getText());
			c1.setBgpflag(checkboxActiveBGP.isSelected());
			c1.setBgpProcess(jtextProcesoBGP.getText());
			c1.setBgpNeighbor(jtextNeighborIPExternal.getText());
			c1.setBgpRemoteAs(jtextNeighborASExternal.getText());
			c1.setEigrpflag(checkboxActiveEigrp.isSelected());
			c1.setEigrpProcess(jtextProcesoEigrp.getText());
			c1.setBgpNeighborInternal(jtextNeighborIPInternal.getText());
			c1.setBgpRemoteAsInternal(jtextAsInternal.getText());

			// Parte de Configuracion de mpls sobre las interfaces
			String ips[] = new String[4];
			String masks[] = new String[4];
			boolean mpslip[] = new boolean[4];
			String forwardingVRF[] = new String[4];

			try {
				interfacesbysnmp.clear();
				checkcomboboxinterface(comboBoxInterface1, 0);
				checkcomboboxinterface(comboBoxInterface2, 1);
				checkcomboboxinterface(comboBoxInterface3, 2);
				checkcomboboxinterface(comboBoxInterface4, 3);
				c1.setInterfacesNames(interfacesbysnmp);
			} catch (Exception e) {
				// TODO: handle exception

			}

			ips[0] = interfaceIP1.getText();
			ips[1] = interfaceIP2.getText();
			ips[2] = interfaceIP3.getText();
			ips[3] = interfaceIP4.getText();
			c1.setIpsInterfaces(ips);

			masks[0] = interfaceMask1.getText();
			masks[1] = interfaceMask2.getText();
			masks[2] = interfaceMask3.getText();
			masks[3] = interfaceMask4.getText();
			c1.setMasksInterfaces(masks);

			mpslip[0] = checkboxMPLSIP1.isSelected();
			mpslip[1] = checkboxMPLSIP2.isSelected();
			mpslip[2] = checkboxMPLSIP3.isSelected();
			mpslip[3] = checkboxMPLSIP4.isSelected();
			c1.setCheckMPLSIP(mpslip);

			forwardingVRF[0] = interfaceForwardingVRF1.getText();
			forwardingVRF[1] = interfaceForwardingVRF2.getText();
			forwardingVRF[2] = interfaceForwardingVRF3.getText();
			forwardingVRF[3] = interfaceForwardingVRF4.getText();
			c1.setForwardingVRF(forwardingVRF);

			// TODO Modificar esta parte Rutas Estaticas
			String temprutasestaticas[][] = new String[countestatic][3];
			if (countestatic == 1 && rorigen.isVisible()
					&& rmascara.isVisible() && rdestino.isVisible()) {
				temprutasestaticas[0][0] = rorigen.getText();
				temprutasestaticas[0][1] = rmascara.getText();
				temprutasestaticas[0][2] = rdestino.getText();

			}
			if (countestatic == 2 && rorigen1.isVisible()
					&& rmascara1.isVisible() && rdestino1.isVisible()) {
				temprutasestaticas[0][0] = rorigen.getText();
				temprutasestaticas[0][1] = rmascara.getText();
				temprutasestaticas[0][2] = rdestino.getText();
				temprutasestaticas[1][0] = rorigen1.getText();
				temprutasestaticas[1][1] = rmascara1.getText();
				temprutasestaticas[1][2] = rdestino1.getText();
			}

			if (countestatic == 3 && rorigen2.isVisible()
					&& rmascara2.isVisible() && rdestino2.isVisible()) {
				temprutasestaticas[0][0] = rorigen.getText();
				temprutasestaticas[0][1] = rmascara.getText();
				temprutasestaticas[0][2] = rdestino.getText();
				temprutasestaticas[1][0] = rorigen1.getText();
				temprutasestaticas[1][1] = rmascara1.getText();
				temprutasestaticas[1][2] = rdestino1.getText();
				temprutasestaticas[2][0] = rorigen2.getText();
				temprutasestaticas[2][1] = rmascara2.getText();
				temprutasestaticas[2][2] = rdestino2.getText();
			}

			c1.setRutasEstaticas(temprutasestaticas);

			// TODO Modificar esta parte Rutas Dinamicas
			String temprutasdinamicas[][] = new String[countDinamic][2];
			if (countDinamic == 1 && rdorigen.isVisible()
					&& rdmascara.isVisible()) {
				temprutasdinamicas[0][0] = rdorigen.getText();
				temprutasdinamicas[0][1] = rdmascara.getText();

			}
			if (countDinamic == 2 && rdorigen1.isVisible()
					&& rdmascara1.isVisible()) {
				temprutasdinamicas[0][0] = rdorigen.getText();
				temprutasdinamicas[0][1] = rdmascara.getText();
				temprutasdinamicas[1][0] = rdorigen1.getText();
				temprutasdinamicas[1][1] = rdmascara1.getText();
			}
			if (countDinamic == 3 && rdorigen2.isVisible()
					&& rdmascara2.isVisible()) {
				temprutasdinamicas[0][0] = rdorigen.getText();
				temprutasdinamicas[0][1] = rdmascara.getText();
				temprutasdinamicas[1][0] = rdorigen1.getText();
				temprutasdinamicas[1][1] = rdmascara1.getText();
				temprutasdinamicas[2][0] = rdorigen2.getText();
				temprutasdinamicas[2][1] = rdmascara2.getText();

			}
			c1.setRutasDinamicas(temprutasdinamicas);

			c1.showparameter(configResultView);
			c1.setDateEstamp(new Date());
			c1.SetDeviceType();
		}
		if (validarcampos() == false) {
			JOptionPane.showMessageDialog(null, "No debe haber campos Vacios!");
		}

	}

	private boolean validarcampos() {
		boolean resultado = false;
		if (checkboxCPE.isSelected() | checkboxP.isSelected()
				| checkboxPE.isSelected()) {
			resultado = ifJtextFieldsllenos();
			if (resultado) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean ifJtextFieldsllenos() {
		// TODO Auto-generated method stub
		if (jtextProcesoBGP.isEnabled()) {
			if (jtextProcesoBGP.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextVrf.isEnabled()) {
			if (jtextVrf.getText().isEmpty()) {
				return true;
			}
		}

		if (jtextProcesoEigrp.isEnabled()) {
			if (jtextProcesoEigrp.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextRD.isEnabled()) {
			if (jtextRD.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextRT.isEnabled()) {
			if (jtextRT.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceIP1.isEnabled()) {
			if (interfaceIP1.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceMask1.isEnabled()) {
			if (interfaceMask1.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceForwardingVRF1.isEnabled()) {
			if (interfaceForwardingVRF1.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceIP2.isEnabled()) {
			if (interfaceIP2.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceMask2.isEnabled()) {
			if (interfaceMask2.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceForwardingVRF2.isEnabled()) {
			if (interfaceForwardingVRF2.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceIP3.isEnabled()) {
			if (interfaceIP3.getText().isEmpty()) {
				return true;
			}
		}

		if (interfaceMask3.isEnabled()) {
			if (interfaceMask3.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceForwardingVRF3.isEnabled()) {
			if (interfaceForwardingVRF3.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceIP4.isEnabled()) {
			if (interfaceIP4.getText().isEmpty()) {
				return true;
			}
		}

		if (interfaceMask4.isEnabled()) {
			if (interfaceMask4.getText().isEmpty()) {
				return true;
			}
		}
		if (interfaceForwardingVRF4.isEnabled()) {
			if (interfaceForwardingVRF4.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextNeighborASExternal.isEnabled()) {
			if (jtextNeighborASExternal.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextNeighborIPExternal.isEnabled()) {
			if (jtextNeighborIPExternal.getText().isEmpty()) {
				return true;
			}
		}
		if (rorigen.isEnabled()) {
			if (rorigen.getText().isEmpty()) {
				return true;
			}
		}
		if (rmascara.isEnabled()) {
			if (rmascara.getText().isEmpty()) {
				return true;
			}
		}
		if (rdestino.isEnabled()) {
			if (rdestino.getText().isEmpty()) {
				return true;
			}
		}
		if (rorigen1.isEnabled()) {
			if (rorigen1.getText().isEmpty()) {
				return true;
			}
		}
		if (rmascara1.isEnabled()) {
			if (rmascara1.getText().isEmpty()) {
				return true;
			}
		}
		if (rdestino1.isEnabled()) {
			if (rdestino1.getText().isEmpty()) {
				return true;
			}
		}
		if (rorigen2.isEnabled()) {
			if (rorigen2.getText().isEmpty()) {
				return true;
			}
		}
		if (rmascara2.isEnabled()) {
			if (rmascara2.getText().isEmpty()) {
				return true;
			}
		}
		if (rdestino2.isEnabled()) {
			if (rdestino2.getText().isEmpty()) {
				return true;
			}
		}
		if (rdorigen.isEnabled()) {
			if (rdorigen.getText().isEmpty()) {
				return true;
			}
		}
		if (rdorigen.isEnabled()) {
			if (rdorigen.getText().isEmpty()) {
				return true;
			}
		}
		if (rdmascara.isEnabled()) {
			if (rdmascara.getText().isEmpty()) {
				return true;
			}
		}
		if (rdorigen1.isEnabled()) {
			if (rdorigen1.getText().isEmpty()) {
				return true;
			}
		}
		if (rdmascara1.isEnabled()) {
			if (rdmascara1.getText().isEmpty()) {
				return true;
			}
		}
		if (rdorigen2.isEnabled()) {
			if (rdorigen2.getText().isEmpty()) {
				return true;
			}
		}
		if (rdmascara2.isEnabled()) {
			if (rdmascara2.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextAsInternal.isEnabled()) {
			if (jtextAsInternal.getText().isEmpty()) {
				return true;
			}
		}
		if (jtextNeighborIPInternal.isEnabled()) {
			if (jtextNeighborIPInternal.getText().isEmpty()) {
				return true;
			}
		}

		// ////////////////////////////
		if (interfaceIP1.isEnabled()) {
			if (interfaceIP1.getText() == "None") {
				return true;
			}
		}
		if (interfaceMask1.isEnabled()) {
			if (interfaceMask1.getText() == "None") {
				return true;
			}
		}
		if (interfaceForwardingVRF1.isEnabled()) {
			if (interfaceForwardingVRF1.getText() == "None") {
				return true;
			}
		}
		if (interfaceIP2.isEnabled()) {
			if (interfaceIP2.getText() == "None") {
				return true;
			}
		}
		if (interfaceMask2.isEnabled()) {
			if (interfaceMask2.getText() == "None") {
				return true;
			}
		}
		if (interfaceForwardingVRF2.isEnabled()) {
			if (interfaceForwardingVRF2.getText() == "None") {
				return true;
			}
		}
		if (interfaceIP3.isEnabled()) {
			if (interfaceIP3.getText() == "None") {
				return true;
			}
		}

		if (interfaceMask3.isEnabled()) {
			if (interfaceMask3.getText() == "None") {
				return true;
			}
		}
		if (interfaceForwardingVRF3.isEnabled()) {
			if (interfaceForwardingVRF3.getText() == "None") {
				return true;
			}
		}
		if (interfaceIP4.isEnabled()) {
			if (interfaceIP4.getText() == "None") {
				return true;
			}
		}

		if (interfaceMask4.isEnabled()) {
			if (interfaceMask4.getText() == "None") {
				return true;
			}
		}
		if (interfaceForwardingVRF4.isEnabled()) {
			if (interfaceForwardingVRF4.getText() == "None") {
				return true;
			}
		}
		// ////////////////////////////

		return false;

	}

	/**
 * 
 */

	public void checkcomboboxinterface(JComboBox<?> JComboBoxInterface,
			int index) {
		if (JComboBoxInterface.getSelectedItem() != null) {
			interfacesbysnmp.add(index, JComboBoxInterface.getSelectedItem()
					.toString());
		}
	}

	public boolean checkActiveCheckBGP() {
		ItemListener itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxActiveBGP.isSelected()) {
					buttonAddRutas_Mask_Dinamic.setEnabled(true);
					jtextProcesoBGP.setEditable(true);
					checkboxActiveEigrp.setEnabled(false);
					jtextProcesoEigrp.setEditable(false);
					activebgp(true);
					if (checkActiveDevicePE()) {
						activebgpinternal(true);
					}
					rdorigen.setVisible(true);
					rdmascara.setVisible(true);
					rdorigen.setEnabled(true);
					rdmascara.setEnabled(true);
					countDinamic++;

				}
				if (checkboxActiveBGP.isSelected() == false) {
					buttonAddRutas_Mask_Dinamic.setEnabled(false);
					jtextProcesoBGP.setEditable(false);
					checkboxActiveEigrp.setEnabled(true);
					jtextProcesoEigrp.setEditable(false);
					activebgp(false);
					activebgpinternal(false);
					offRedesDinamicas();
					countDinamic = 0;

				}
			}

			/**
			 * 
			 */

		};
		checkboxActiveBGP.addItemListener(itemlistener);
		return checkboxActiveBGP.isSelected();
	}

	protected void activebgpinternal(boolean estado) {
		labelExternal.setEnabled(estado);
		labelASInternal.setEnabled(estado);
		jtextAsInternal.setEnabled(estado);
		jtextAsInternal.setEditable(estado);
		labelVecinoBGPInternal.setEnabled(estado);
		jtextNeighborIPInternal.setEnabled(estado);
		jtextNeighborIPInternal.setEditable(estado);

	}

	public void offRedesDinamicas() {
		if (rdorigen.isVisible() && rdmascara.isVisible()) {
			rdorigen.setVisible(false);
			rdmascara.setVisible(false);
			rdorigen.setEnabled(false);
			rdmascara.setEnabled(false);
		}
		if (rdorigen1.isVisible() && rdmascara1.isVisible()) {
			rdorigen1.setVisible(false);
			rdmascara1.setVisible(false);
			rdorigen1.setEnabled(false);
			rdmascara1.setEnabled(false);
		}
		if (rdorigen2.isVisible() && rdmascara2.isVisible()) {
			rdorigen2.setVisible(false);
			rdmascara2.setVisible(false);
			rdorigen2.setEnabled(false);
			rdmascara2.setEnabled(false);
		}
		countDinamic = 0;
	}

	protected void activebgp(boolean estado) {
		// TODO Auto-generated method stub
		labelProcesoBGP.setEnabled(estado);
		jtextProcesoBGP.setEnabled(estado);
		jtextProcesoBGP.setEditable(estado);
		labelAsExternal.setEnabled(estado);
		jtextNeighborASExternal.setEnabled(estado);
		jtextNeighborASExternal.setEditable(estado);
		labelVecinoBgpExternal.setEnabled(estado);
		jtextNeighborIPExternal.setEnabled(estado);
		jtextNeighborIPExternal.setEditable(estado);

	}

	public boolean checkActiveCheckEigrp() {
		ItemListener itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxActiveEigrp.isSelected()) {
					buttonAddRutas_Mask_Dinamic.setEnabled(true);
					jtextProcesoEigrp.setEnabled(true);
					jtextProcesoEigrp.setEditable(true);
					checkboxActiveBGP.setEnabled(false);
					jtextProcesoBGP.setEditable(false);
					rdorigen.setVisible(true);
					rdmascara.setVisible(true);
					countDinamic++;

				}
				if (checkboxActiveEigrp.isSelected() == false) {
					buttonAddRutas_Mask_Dinamic.setEnabled(false);
					jtextProcesoEigrp.setEnabled(false);
					jtextProcesoBGP.setEditable(false);
					checkboxActiveBGP.setEnabled(true);
					jtextProcesoEigrp.setEditable(false);
					offRedesDinamicas();
					countDinamic = 0;

				}
			}
		};
		checkboxActiveEigrp.addItemListener(itemlistener);
		return checkboxActiveEigrp.isSelected();
	}

	public boolean checkActiveCheckCEF() {
		ItemListener itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

			}

		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return checkboxActiveCEF.isSelected();
	}

	public boolean checkActiveCheckMpls() {
		ItemListener itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxActiveMpls.isSelected()) {
					activeVRF(true);
					;
				}
				if (checkboxActiveMpls.isSelected() == false) {
					activeVRF(false);

				}
			}

		};
		checkboxActiveMpls.addItemListener(itemlistener);
		return checkboxActiveMpls.isSelected();
	}

	public void checkActiveCheckVrf() {
		ItemListener itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxVrf.isSelected()) {
					activeVRF(true);

				}
				if (checkboxVrf.isSelected() == false) {
					activeVRF(false);

				}
			}
		};
		checkboxVrf.addItemListener(itemlistener);
	}

	public boolean checkActiveDeviceCPE() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveDeviceCPE = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxCPE.isSelected()) {
					// activar aqui las opciones de CPE
					checkboxP.setEnabled(false);
					checkboxPE.setEnabled(false);
					checkboxActiveCEF.setEnabled(true);
					checkboxActiveMpls.setEnabled(false);

					activeVRF(false);
					activeMplsIP(false);

				}
				if (checkboxCPE.isSelected() == false) {
					checkboxP.setEnabled(true);
					checkboxPE.setEnabled(true);
					checkboxActiveMpls.setEnabled(false);
					activeVRF(false);
					activeMplsIP(false);

				}
			}
		};
		checkboxCPE.addItemListener(itemlistenerActiveDeviceCPE);
		return checkboxCPE.isSelected();
	}

	public boolean checkActiveDevicePE() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveDevicePE = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxPE.isSelected()) {
					// activar aqui las opciones de CPE
					checkboxCPE.setEnabled(false);
					checkboxP.setEnabled(false);
					checkboxActiveMpls.setEnabled(true);
					checkboxActiveMpls.setSelected(true);
					activeVRF(true);
					activeMplsIP(false);
					if (checkboxActiveBGP.isSelected()) {
						activebgpinternal(true);
					}

				}
				if (checkboxPE.isSelected() == false) {
					checkboxCPE.setEnabled(true);
					checkboxP.setEnabled(true);
					checkboxActiveMpls.setEnabled(false);
					checkboxActiveMpls.setSelected(false);
					activeVRF(false);
					activeMplsIP(false);
					if (checkboxActiveBGP.isSelected()) {
						activebgpinternal(false);
					}

				}
			}
		};
		checkboxPE.addItemListener(itemlistenerActiveDevicePE);
		return checkboxPE.isSelected();
	}

	public boolean checkActiveDeviceP() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveDeviceP = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkboxP.isSelected()) {
					// activar aqui las opciones de CPE
					checkboxCPE.setEnabled(false);
					checkboxPE.setEnabled(false);
					checkboxActiveMpls.setEnabled(true);
					checkboxActiveMpls.setSelected(true);
					activeMplsIP(true);
					activeVRF(false);

				}
				if (checkboxP.isSelected() == false) {
					checkboxCPE.setEnabled(true);
					checkboxPE.setEnabled(true);
					checkboxActiveMpls.setEnabled(false);
					checkboxActiveMpls.setSelected(false);
					activeVRF(false);

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
		return jtextProcesoBGP;
	}

	public void setTextFieldProcesoBGP(JTextField textFieldProcesoBGP) {
		this.jtextProcesoBGP = textFieldProcesoBGP;
	}

	public JTextField getTextFieldProcesoEigrp() {
		return jtextProcesoEigrp;
	}

	public void setTextFieldProcesoEigrp(JTextField textFieldProcesoEigrp) {
		this.jtextProcesoEigrp = textFieldProcesoEigrp;
	}

	public void activeVRF(boolean estado) {
		checkboxVrf.setEnabled(estado);
		checkboxVrf.setSelected(estado);
		jtextVrf.setEditable(estado);
		jtextVrf.setEnabled(estado);
		checkboxRd.setEnabled(estado);
		checkboxRd.setSelected(estado);
		jtextRD.setEditable(estado);
		jtextRD.setEnabled(estado);
		checkboxRt.setEnabled(estado);
		checkboxRt.setSelected(estado);
		jtextRT.setEditable(estado);
		jtextRT.setEnabled(estado);
	}

	public void activeMplsIP(boolean estado) {
		checkboxMPLSIP1.setEnabled(estado);
		checkboxMPLSIP2.setEnabled(estado);
		checkboxMPLSIP3.setEnabled(estado);
		checkboxMPLSIP4.setEnabled(estado);
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

	public void rellenarTabla() {
		// TODO Mejorar el codigo con el patron singleton
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), "orqmpls.db4o");
		modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;// Le decimos que ninguna celda se puede editar
								// directamente en la tabla
			}
		};

		jtable = new JTable(modelo);
		modelo.addColumn("Device");// Le ponemos la columnas a la tabla
		modelo.addColumn("Fecha");
		modelo.addColumn("usuario");
		modelo.addColumn("Objeto");// Metemos el objeto en la tabla para
									// manipularlo mas facilmente
		jtable.removeColumn(jtable.getColumn("Objeto"));// Eliminamos la columna
														// de la tabla para no
														// mostrarlo, pero en el
														// modelo sigue estando,
														// por lo que se puede
														// cojer y editar
														// igualmente

		TableRowSorter sorter = new TableRowSorter(modelo);
		jtable.setRowSorter(sorter);// Le decimos que la tabla se pueda ordenar
									// (Haciendo clic en las columnas)

		// final codigo para probar
		List<CurrentConfig> ps = null;
		// Si no hay nada
		ps = db.query(CurrentConfig.class);// Cojemos todos los registros

		Object[] fila = new Object[4];
		for (CurrentConfig config : ps) {// A�adimos los registros a la tabla
			fila[0] = config.getDeviceType();
			fila[1] = config.getDateEstamp();
			fila[2] = config.getTelnetusuario();
			fila[3] = config;// A�adimos tambien el objeto
			modelo.addRow(fila);

		}
		scrollPane_1.add(jtable);
		scrollPane_1.setViewportView(jtable);
		db.close();
	}

	public void mostrarvaloresseleccionados() {
		if (jtable.getSelectedRow() != -1) {
			try {
				CurrentConfig configleida = (CurrentConfig) modelo.getValueAt(
						jtable.getSelectedRow(), 3);// Abrimos la ventana
													// diciendole que es de
													// edici�n
				configleida.showparameter(configResultViewlogs); // y pasandole
																	// el objeto
																	// seleccionado
																	// en la
																	// tabla

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Ha habido algun error inesperado");
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Tienes que seleccionar alguna opcion");
		}

	}

	// /////
	public boolean checkActiveLine1() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveLine1 = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkBoxLine1.isSelected()) {
					// activar aqui las opciones de Linea de interfaces 1
					comboBoxInterface1.setEnabled(true);
					interfaceIP1.setEnabled(true);
					interfaceMask1.setEnabled(true);
					if (checkboxCPE.isSelected() | checkboxP.isSelected()) {
						interfaceForwardingVRF1.setEnabled(false);
					}
					if (checkboxPE.isSelected()) {
						interfaceForwardingVRF1.setEnabled(true);
						interfaceForwardingVRF1.setEditable(true);

					}
				}
				if (checkBoxLine1.isSelected() == false) {
					// hacer algo cuando no este seleccionado
					comboBoxInterface1.setEnabled(false);
					interfaceIP1.setEnabled(false);
					interfaceMask1.setEnabled(false);
					interfaceForwardingVRF1.setEnabled(false);
					interfaceForwardingVRF1.setEditable(false);

				}
			}
		};
		checkBoxLine1.addItemListener(itemlistenerActiveLine1);
		return checkboxP.isSelected();
	}

	// /////
	public boolean checkActiveLine2() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveLine2 = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkBoxLine2.isSelected()) {
					// activar aqui las opciones de Linea de interfaces 1
					comboBoxInterface2.setEnabled(true);
					interfaceIP2.setEnabled(true);
					interfaceMask2.setEnabled(true);
					if (checkboxCPE.isSelected() | checkboxP.isSelected()) {
						interfaceForwardingVRF2.setEnabled(false);
					}
					if (checkboxPE.isSelected()) {
						interfaceForwardingVRF2.setEnabled(true);
						interfaceForwardingVRF2.setEditable(true);

					}
				}
				if (checkBoxLine2.isSelected() == false) {
					// hacer algo cuando no este seleccionado
					comboBoxInterface2.setEnabled(false);
					interfaceIP2.setEnabled(false);
					interfaceMask2.setEnabled(false);
					interfaceForwardingVRF2.setEnabled(false);
					interfaceForwardingVRF2.setEditable(false);
				}
			}
		};
		checkBoxLine2.addItemListener(itemlistenerActiveLine2);
		return checkboxP.isSelected();
	}

	// //////
	public boolean checkActiveLine3() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveLine3 = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkBoxLine3.isSelected()) {
					// activar aqui las opciones de Linea de interfaces 1
					comboBoxInterface3.setEnabled(true);
					interfaceIP3.setEnabled(true);
					interfaceMask3.setEnabled(true);
					if (checkboxCPE.isSelected() | checkboxP.isSelected()) {
						interfaceForwardingVRF3.setEnabled(false);
					}
					if (checkboxPE.isSelected()) {
						interfaceForwardingVRF3.setEnabled(true);
						interfaceForwardingVRF3.setEditable(true);

					}
				}
				if (checkBoxLine3.isSelected() == false) {
					// hacer algo cuando no este seleccionado
					comboBoxInterface3.setEnabled(false);
					interfaceIP3.setEnabled(false);
					interfaceMask3.setEnabled(false);
					interfaceForwardingVRF3.setEnabled(false);
					interfaceForwardingVRF3.setEditable(false);
				}
			}
		};
		checkBoxLine3.addItemListener(itemlistenerActiveLine3);
		return checkboxP.isSelected();
	}

	// ///////
	public boolean checkActiveLine4() {

		// chequear si algun dispositivo esta activo y desabilitar las opciones
		// que no correspondan
		ItemListener itemlistenerActiveLine4 = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (checkBoxLine4.isSelected()) {
					// activar aqui las opciones de Linea de interfaces 1
					comboBoxInterface4.setEnabled(true);
					interfaceIP4.setEnabled(true);
					interfaceMask4.setEnabled(true);
					if (checkboxCPE.isSelected() | checkboxP.isSelected()) {
						interfaceForwardingVRF4.setEnabled(false);
					}
					if (checkboxPE.isSelected()) {
						interfaceForwardingVRF4.setEnabled(true);
						interfaceForwardingVRF4.setEditable(true);

					}
				}
				if (checkBoxLine4.isSelected() == false) {
					// hacer algo cuando no este seleccionado
					comboBoxInterface4.setEnabled(false);
					interfaceIP4.setEnabled(false);
					interfaceMask4.setEnabled(false);
					interfaceForwardingVRF4.setEnabled(false);
					interfaceForwardingVRF4.setEditable(false);
				}
			}
		};
		checkBoxLine4.addItemListener(itemlistenerActiveLine4);
		return checkboxP.isSelected();
	}

}
