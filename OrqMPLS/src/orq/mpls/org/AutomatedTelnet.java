package orq.mpls.org;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.BufferedInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintStream;

public class AutomatedTelnet {
private TelnetClient telnet = new TelnetClient();
private InputStream in;
public static BufferedInputStream br;
private PrintStream out;
private String prompt = "#";
private FileWriter output = null;

//public void tofile() throws IOException {
//	//pasar el stream de la sesion a un archivo para ver que me devolvio 
//		try{
//			int d;
//			output = new FileWriter("salida.txt");
//			while ((d = in.read()) != -1){
//				System.out.println(d);
//				output.write(d);
//			}
//			
//		}
//		catch (Exception e){
//			
//			e.printStackTrace();
//		}
//			
//		
//		finally{
//			
//			output.close();
//		}
//}


public AutomatedTelnet(String server, String user, String password) {
try {
// Connect to the specified server
telnet.connect(server, 23);


// Get input and output stream references
in = telnet.getInputStream();
out = new PrintStream(telnet.getOutputStream());


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

public String readUntil(String pattern) {
	try {
	char lastChar = pattern.charAt(pattern.length() - 1);
	//System.out.println(lastChar);
	StringBuffer sb = new StringBuffer();
	@SuppressWarnings("unused")
	boolean found = false;
	char ch = (char) in.read();
	
	output = new FileWriter("salida.txt" , true);

			while (true) {
			System.out.print(ch);
			//System.out.print(ch);
			output.write(ch);
			output.write(pattern);
			sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						//System.out.println(sb.toString());
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
//System.out.println(value);
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
public String backroot() {
	char c= 26;
	return Character.toString(c);
	
}

public static void main(String[] args) {
try {
AutomatedTelnet telnet = new AutomatedTelnet("192.168.80.110",
"raul",
"cisco");
telnet.ena("cisco");
telnet.sendCommand("configure terminal");
telnet.sendCommand("do show ip route");
telnet.sendCommand("interface Ethernet0/0");
//telnet.sendCommand(backroot());


br = new BufferedInputStream(telnet.in);
System.out.println(br.read());
telnet.disconnect();

}
catch (Exception e) {
e.printStackTrace();
}
}


}

