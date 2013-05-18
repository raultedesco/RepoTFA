package orq.mpls.org;
import org.apache.commons.net.telnet.TelnetClient;


import java.io.InputStream;
import java.io.PrintStream;


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
e.printStackTrace();
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


public void ena(String password) {
try {
write("ena");
readUntil("Password:");
write(password);
prompt = "#";
readUntil(prompt);
}
catch (Exception e) {
e.printStackTrace();
}
}

public static String backroot() {
//	char c= 26;
//	return Character.toString(c);
	String end ="end";
	return end;
}

public void cvrf(CurrentConfig c1){
	
	String cvrf = "ip vrf " + c1.getVrfname();
	this.sendCommand(cvrf);
	String crd="rd " + c1.getVrfrd();
	this.sendCommand(crd);
	String crt="route-target " + c1.getVrfrt();
	this.sendCommand(crt);
	this.sendCommand(backroot());
	
	
	
}
public void cinterface(CurrentConfig c1, int index ) {
	// TODO chequear metodo de configuracion de ip
	String cinterface ="interface " + c1.getInterfaces(index);
	String cip = "ip address " + c1.getIpsInterfaces()[index] + "" + c1.getMasksInterfaces()[index];
	this.sendCommand(cinterface);
	this.sendCommand(cip);
	this.sendCommand(backroot());
}

public void cbgp(CurrentConfig c1){
	//TODO chequear metodo de configuracion de bgp / falta config para mplsvpn - neigbors as etc
	//ejemplo config pe bgp dos vrf
//	!
//	router bgp 1
//	 no synchronization
//	 bgp log-neighbor-changes
//	 neighbor 2.2.2.2 remote-as 1
//	 neighbor 2.2.2.2 update-source Loopback0
//	 neighbor 192.168.19.2 remote-as 101
//	 no auto-summary
//	 !
//	 address-family vpnv4
//	  neighbor 2.2.2.2 activate
//	  neighbor 2.2.2.2 send-community extended
//	 exit-address-family
//	 !
//	 address-family ipv4 vrf B
//	  neighbor 192.168.18.2 remote-as 99
//	  neighbor 192.168.18.2 activate
//	  no synchronization
//	 exit-address-family
//	 !
//	 address-family ipv4 vrf A
//	  redistribute connected
//	  neighbor 192.168.19.2 remote-as 101
//	  neighbor 192.168.19.2 activate
//	  no synchronization
//	 exit-address-family
//	!
	
	String cbgp="router bgp " + c1.getBgpProcess();
	this.sendCommand(cbgp);
	this.sendCommand(backroot());
	
	
}
private void changeprompt() {
	// TODO generar un configurador de prompt
	
}
public void savetofile() {
	//TODO Generar metodo para guardar en archivo
}


public static void main(String[] args) {
try {
AutomatedTelnet telnet = new AutomatedTelnet("192.168.80.110",
"raul",
"cisco");
telnet.ena("cisco");




telnet.disconnect();
}
catch (Exception e) {
e.printStackTrace();
}
}


public void configmode() {
	
	String cmode="configure terminal";
	this.sendCommand(cmode);
}

}

//(config)#
//(config-if)#
//#
//(config-router)#
//(config-vrf)#


