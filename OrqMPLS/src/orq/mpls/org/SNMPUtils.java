package orq.mpls.org;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPUtils
{
  private static String  ipAddress  = "192.168.80.110";

  private static String  port    = "161";

  // sysDescr OID of MIB RFC 1213; Scalar Object = .iso.org.dod.internet.mgmt.mib-2.system.sysDescr
  private String  oidValue = ".1.3.6.1.2.1.2.2.1.2.0";  // ends with 0 for scalar object

  private static int    snmpVersion  = SnmpConstants.version2c;

  private static String  community  = "mpls-rw";

  public static String getIpAddress() {
	return ipAddress;
}


public static void setIpAddress(String ipAddress) {
	SNMPUtils.ipAddress = ipAddress;
}


public static String getPort() {
	return port;
}


public static void setPort(String port) {
	SNMPUtils.port = port;
}


public String getOidValue() {
	return oidValue;
}


public void setOidValue(String oidValue) {
	this.oidValue = oidValue;
}


public static int getSnmpVersion() {
	return snmpVersion;
}


public static void setSnmpVersion(int snmpVersion) {
	SNMPUtils.snmpVersion = snmpVersion;
}


public static String getCommunity() {
	return community;
}


public static void setCommunity(String community) {
	SNMPUtils.community = community;
}


public static void main(String[] args) throws Exception {
  printInterface();	
		

		
		
	 
     
  }


public static ArrayList<String> printInterface() throws IOException {
	ArrayList<String> interfaces = new ArrayList<>();
	SNMPUtils obj1 = new SNMPUtils();
	String print=null;
	  
	  	System.out.println("Interface: " +  obj1.getnext(obj1.getOidValue()));
	  	interfaces.add(obj1.getnext(obj1.getOidValue()));
		while (!"Null0".equals(print)) {
			
			print = obj1.getnext(obj1.nextoid(obj1.getOidValue()));
			obj1.setOidValue(obj1.nextoid(obj1.getOidValue()));
			if (!"Null0".equals(print)) {
				System.out.println("Interface: " + print);
				interfaces.add(print);
				
				
			}
		}
		return interfaces;
}


private String nextoid(String oidValue) {
	//System.out.println("numero de elementos del string" + oidValue.length());
	String newvalue = oidValue.substring(oidValue.length()-1);
	String delulitmoelemento=oidValue.substring(0,oidValue.length()-1);
	int valor = Integer.parseInt(newvalue);
	//System.out.println(oidValue );
	valor++;
	String oidincrementada = String.valueOf(valor);
	String oidfinal = delulitmoelemento + oidincrementada ;;
	return oidfinal;
	
}

public String getnext(String OID) throws IOException {
	//System.out.println("SNMP GET-NEXT Demo");

    // Create TransportMapping and Listen
    TransportMapping transport = new DefaultUdpTransportMapping();
    transport.listen();

    // Create Target Address object
    CommunityTarget comtarget = new CommunityTarget();
    comtarget.setCommunity(new OctetString(community));
    comtarget.setVersion(snmpVersion);
    comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
    comtarget.setRetries(2);
    comtarget.setTimeout(1000);

    // Create the PDU object
    PDU pdu = new PDU();
    pdu.add(new VariableBinding(new OID(OID))); //Querying GetNext of sysDescr will get the sysObjectID OID value
    pdu.setRequestID(new Integer32(1));
    pdu.setType(PDU.GETNEXT);
    
    
    // Create Snmp object for sending data to Agent
    Snmp snmp = new Snmp(transport);
    ResponseEvent response = snmp.getNext(pdu, comtarget);
    PDU responsePDU = response.getResponse();
    
//    System.out.println("Request:\n[ Note: GetNext Request is sent for sysDescr oid in RFC 1213 MIB.");
//    System.out.println("GetNext Response should get the sysObjectID value rather than sysDescr value ]");
//    System.out.println("Sending GetNext Request to Agent for sysDescr ...");
    
   
    

    // Process Agent Response
    if (response != null)
    {
      //System.out.println("\nResponse:\nGot GetNext Response from Agent...");
      

      if (responsePDU != null)
      {
        int errorStatus = responsePDU.getErrorStatus();
        int errorIndex = responsePDU.getErrorIndex();
        String errorStatusText = responsePDU.getErrorStatusText();

        if (errorStatus == PDU.noError)
        {
        	
        		//System.out.println("Snmp GetNext Response for sysObjectID = " + responsePDU.get(0).toValueString());	
        		return responsePDU.get(0).toValueString();
          
        }
        else
        {
          System.out.println("Error: Request Failed");
          System.out.println("Error Status = " + errorStatus);
          System.out.println("Error Index = " + errorIndex);
          System.out.println("Error Status Text = " + errorStatusText);
        }
      }
      else
      {
        System.out.println("Error: GetNextResponse PDU is null");
      }
    }
    else
    {
      System.out.println("Error: Agent Timeout... ");
    }
    snmp.close();
	return responsePDU.get(0).toValueString();
	
}
}