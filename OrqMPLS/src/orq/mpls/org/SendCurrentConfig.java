package orq.mpls.org;

import javax.swing.JTextArea;

public class SendCurrentConfig {

	AutomatedTelnet send;
	CurrentConfig c1;
	public SendCurrentConfig(CurrentConfig current, JTextArea configGenView) {
	send = new AutomatedTelnet("192.168.80.110", "raul", "cisco");
	this.c1 =current;
	
	try {
		if (c1.isIfCpe()) {
			send.ena("cisco");
			configGenView.append(send.configmode());
			configGenView.append(send.ccef());
			configBGP_EIGRP_Static_Dinamic_Routes(configGenView);
			configInterfaces(configGenView);
			configGenView.append(send.backroot());
			
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		
	try {
		if (c1.isIfP()) {
			send.ena("cisco");
			configureComandsPE_P(configGenView);
			configBGP_EIGRP_Static_Dinamic_Routes(configGenView);
			configInterfaces(configGenView);
			configGenView.append(send.backroot());
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	
	}
	
	try {
		if (c1.isIfPe()) {
			send.ena("cisco");
			configureComandsPE_P(configGenView);
			configBGP_EIGRP_Static_Dinamic_Routes(configGenView);
			configInterfaces(configGenView);
			configGenView.append(send.cvrf(c1));
			configGenView.append(send.cbgpMPLS(c1));
			configGenView.append(send.backroot());
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	finally{
		send.disconnect();
	}
	
	

	}

	/**
	 * @param configGenView
	 */
	public void configBGP_EIGRP_Static_Dinamic_Routes(JTextArea configGenView) {
		if (c1.isBgpflag()) {
			configGenView.append(send.cbgp(c1));
			for (int i = 0; i < c1.getCountdinamic(); i++) {
				System.out.println("countdinamic:"+c1.getCountdinamic());
				configGenView.append(send.crutaDinamicaBGP(c1,i));
			}
			
			configGenView.append(send.sendCommand("exit"));
		}

		if (c1.isEigrpflag()) {
			for (int i = 0; i < c1.getCountdinamic(); i++) {
				System.out.println("countdinamic:"+c1.getCountdinamic());
				configGenView.append(send.ceigrp(c1,i));
			}
			
			configGenView.append(send.sendCommand("exit"));
		}
		if (c1.getCountestatic() != 0) {
			for (int i = 0; i < c1.getCountestatic(); i++) {
				configGenView.append(send.crutaEstatica(c1, i));
			}

		}
		
		
	}

	/**
	 * @param configGenView
	 */
	public void configInterfaces(JTextArea configGenView) {
		for (int i = 0; i < c1.getInterfacesNamesSize(); i++) {
			if (c1.getInterfacesNames(i)!= "None") {
				configGenView.append(send.cinterface(c1, i));
				if (c1.getCheckMPLSIP()[i]) {
					configGenView.append(send.cmplsip());
				}
				
			}
			
		}
	}

	
	
	
	private void configureComandsPE_P(JTextArea configGenView) {
		configGenView.append(send.configmode());
		configGenView.append(send.ccef());
		configGenView.append(send.cmplsip());
				
	}



	

	
}
