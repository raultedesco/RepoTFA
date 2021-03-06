package orq.mpls.org;
import org.apache.commons.net.telnet.TelnetClient;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class AutomatedTelnet {
private TelnetClient telnet = new TelnetClient();
private InputStream in;
private PrintStream out;
private String prompt = "#";


public AutomatedTelnet(String server, String user, String password) {
try {
// Connect to the specified server
telnet.connect(server, 23);


// Get input and output stream references
in = telnet.getInputStream();
out = new PrintStream(telnet.getOutputStream());
//partde de creacion de archivo para guardar



// Log the user on
readUntil("Username:");
write(user);
readUntil("Password:");
write(password);

// Advance to a prompt
readUntil(prompt);

}
catch (Exception e) {
	JOptionPane.showMessageDialog(null,
	        "No se pudo establecer la conexion con el Router",
	        "Sin Conexion",
	        JOptionPane.INFORMATION_MESSAGE,(new ImageIcon("images/config.png")));
			
	  
e.printStackTrace();
}
}

public AutomatedTelnet() {
	// TODO Auto-generated constructor stub
}

public boolean TestConeccion(String server, String user, String password) throws IOException {
	boolean flaglogin = true;
	try {
		// Connect to the specified server
		telnet.connect(server, 23);
		// Get input and output stream references
		in = telnet.getInputStream();
		out = new PrintStream(telnet.getOutputStream());
		//partde de creacion de archivo para guardar
		
		// Log the user on
		readUntil("Username:");
		write(user);
		readUntil("Password:");
		write(password);
		//System.out.println(writeReturnString(password));
		if (readUntilboolean("% Login invalid")) {
			flaglogin = false;

		}	
		telnet.disconnect();
		return flaglogin;
		
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
			        "No se pudo establecer la conexion con el Router",
			        "Sin Conexion",
			        JOptionPane.INFORMATION_MESSAGE,(new ImageIcon("images/config.png")));
				
			  
		e.printStackTrace();
		flaglogin= false;
		return flaglogin;
		}

}

public String readUntil(String pattern) {
	try {
	char lastChar = pattern.charAt(pattern.length() - 1);
	StringBuffer sb = new StringBuffer();
	//@SuppressWarnings("unused")
	//boolean found = false;
	char ch = (char) in.read();
	

			while (true) {
			System.out.print(ch);
			sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						//System.out.println("\ncontenido del buffer:"+sb.toString());
						return sb.toString();
					}

				}
				
				ch = (char) in.read();
			}
			
	}
	catch (Exception e) {
	e.printStackTrace();
	}
	return null;
	}


public boolean readUntilboolean(String pattern) {
	try {
	char lastChar = pattern.charAt(pattern.length() - 1);
	StringBuffer sb = new StringBuffer();
	//@SuppressWarnings("unused")
	//boolean found = false;
	char ch = (char) in.read();
	

			while (true) {
			System.out.print(ch);
			sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						//System.out.println("\ncontenido del buffer:"+sb.toString());

						return true;
					}

				}
				if (sb.toString().endsWith(prompt)) {
					return false;
				}
				ch = (char) in.read();
			}
			
	}
	catch (Exception e) {
	e.printStackTrace();
	}

	return false;
	}
	



public void write(String value) {
try {
out.println(value);
out.flush();

}
catch (Exception e) {
e.printStackTrace();
}
}


public String sendCommand(String command) {
try {
write(command);
return readUntil(prompt);
}
catch (Exception e) {
e.printStackTrace();
}
return null;
}




public void disconnect() {
try {
telnet.disconnect();
}
catch (Exception e) {
e.printStackTrace();
}
}
//enviar CtrlZ a la terminal para asegurar que se vuelve al prompt root


public String ena(String password) {
	String callback = null;
	try {

write("ena");
callback=readUntil("Password:");
write(password);
prompt = "#";
readUntil(prompt);
return callback + password;
}
catch (Exception e) {
e.printStackTrace();
}
return callback;
}

public String backroot() {
//	char c= 26;
//	return Character.toString(c);
	String end ="end";
	return this.sendCommand(end);

}

public String cvrf(CurrentConfig c1){
	String resul;
	String cvrf = "ip vrf " + c1.getVrfname();
	resul=this.sendCommand(cvrf);
	String crd="rd " + c1.getVrfrd();
	resul = resul + this.sendCommand(crd);
	String crt="route-target " + c1.getVrfrt();
	resul = resul + this.sendCommand(crt);
	return resul;
	
	
}
public String cinterface(CurrentConfig c1, int index ) {
	// TODO chequear metodo de configuracion de ip
	String resul;
	String cinterface ="interface " + c1.getInterfacesNames(index);
	String cip = "ip address " + c1.getIpsInterfaces()[index] + " " + c1.getMasksInterfaces()[index];
	String cipUp="no shutdown";
	resul=this.sendCommand(cinterface);
	resul= resul+ this.sendCommand(cip);
	resul= resul+this.sendCommand(cipUp);
	return resul;
}

public String cbgpMPLS(CurrentConfig c1){
	//TODO chequear metodo de configuracion de bgp / falta config para mplsvpn - neigbors as etc
	String resul;
	String cbgp="router bgp " + c1.getBgpProcess();
	String cvecino="neighbor " +c1.getBgpNeighbor() +" remote-as " +c1.getBgpRemoteAs();
	String cvecinoA="neighbor " +c1.getBgpNeighbor() +" activate";
	String caddressfamily ="address-family ipv4 vrf " + c1.getVrfname();
	String cvecinoI="neighbor " +c1.getBgpNeighborInternal() +" remote-as " +c1.getBgpRemoteAsInternal();
	resul=this.sendCommand(cbgp);
	resul=resul + this.sendCommand(cvecino);
	resul=resul + this.sendCommand(cvecinoA);
	resul=resul + this.sendCommand(caddressfamily);
	resul=resul + this.sendCommand(cvecinoI);
	return resul;
	
	
}

public String cbgp(CurrentConfig c1){
	//TODO chequear metodo de configuracion de bgp / falta config para mplsvpn - neigbors as etc
	
	String resul;
	String cbgp="router bgp " + c1.getBgpProcess();
	String cvecino="neighbor " +c1.getBgpNeighbor() +" remote-as " +c1.getBgpRemoteAs();

	resul=this.sendCommand(cbgp);
	resul=resul+this.sendCommand(cvecino);
	return resul;
	
}
public String crutaEstatica(CurrentConfig c1,int i) {
	// TODO Metodo crutaEstatica terminar
	String crutaEstatica="ip route " +c1.getRutasEstaticas()[i][0]+" "+c1.getRutasEstaticas()[i][1]+" "+
	c1.getRutasEstaticas()[i][2]; 
	return this.sendCommand(crutaEstatica);
	
	

}

public String crutaDinamicaBGP(CurrentConfig c1, int i) {
	// TODO Metodo crutaDinamica
	String resul;
	String rutadinamica="network "+c1.getRutasDinamicas()[i][0]+" mask "+c1.getRutasDinamicas()[i][1];
	resul=this.sendCommand(rutadinamica);
	return resul;

}

public String ceigrp(CurrentConfig c1,int i){
	//TODO Config EIGRP
	String resul;
	String ceigrp="router eigrp " + c1.getEigrpProcess();
	String rutadinamica="network "+c1.getRutasDinamicas()[i][0]+" "+c1.getRutasDinamicas()[i][1];
	resul=this.sendCommand(ceigrp);
	resul=resul+this.sendCommand(rutadinamica);
	return resul;

	
	
}
public String ccef() {
	String ccef = "ip cef";
	return this.sendCommand(ccef);
	
}

public String cmplsip() {
	String cmplsip = "mpls ip";
	return this.sendCommand(cmplsip);

}

public void savetofile() {
	//TODO Generar metodo para guardar en archivo
}


public static void main(String[] args) {
try {
AutomatedTelnet telnet = new AutomatedTelnet("192.168.80.110",
"raul",
"cisco1");
telnet.ena("cisco");




telnet.disconnect();
}
catch (Exception e) {
e.printStackTrace();
}
}


public String configmode() {
	
	String cmode="configure terminal";
	return this.sendCommand(cmode);
	
}


public String showIPRoute() {
	// TODO Auto-generated method stub
	String showiproute = "show ip route";
	String resul=this.sendCommand(showiproute);
	String newresul=resul.substring(13);
	return newresul;
}

public String showIPInterfacesBrief() {
	// TODO Auto-generated method stub
	String showipinterfacebrief = "show ip interface brief";
	String resul= this.sendCommand(showipinterfacebrief);
	String newresult=resul.substring(23);
	return newresult;
}

}

//(config)#
//(config-if)#
//#
//(config-router)#
//(config-vrf)#


