package orq.mpls.org;
import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibSymbol;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.value.ObjectIdentifierValue;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpManager {
	public String snmpGet(String host, String community, String strOID) {
		  String strResponse="";
		  ResponseEvent response;
		  Snmp snmp;
		  try {
		    OctetString community1 = new OctetString(community);
		    host= host+"/"+"161";
		    Address tHost = new UdpAddress(host);
		    TransportMapping<?> transport = new DefaultUdpTransportMapping();
		    transport.listen();
		    CommunityTarget comtarget = new CommunityTarget();
		    comtarget.setCommunity(community1);
		    comtarget.setVersion(SnmpConstants.version1);
		    comtarget.setAddress(tHost);
		    comtarget.setRetries(2);
		    comtarget.setTimeout(5000);
		    PDU pdu = new PDU();
		    pdu.add(new VariableBinding(new OID(strOID)));
		    pdu.setType(PDU.GET); 
		    snmp = new Snmp(transport);
		    response = snmp.get(pdu,comtarget);
		    
		     
		    if(response != null) {
		      if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
		        PDU pduresponse=response.getResponse();
		        strResponse=pduresponse.getVariableBindings().firstElement().toString();
		        if(strResponse.contains("=")) {
		          int len = strResponse.indexOf("=");
		          strResponse=strResponse.substring(len+1, strResponse.length());
		        }
		      }
		    } else {
		      System.out.println("Looks like a TimeOut occured ");
		    }
		  
		  
		  
		    snmp.close();
		  } catch(Exception e) {
		     e.printStackTrace();
		  }
		System.out.println("Response="+strResponse);
		 return strResponse;
		}
	
	public void snmpSet(String host, String community, String strOID, String Value) {
		  host= host+"/"+"161";
		  Address tHost = GenericAddress.parse(host);
		  Snmp snmp;
		  try {
		    TransportMapping<?> transport = new DefaultUdpTransportMapping();
		    snmp = new Snmp(transport);
		    transport.listen();
		    CommunityTarget target = new CommunityTarget();
		    target.setCommunity(new OctetString(community));
		    target.setAddress(tHost);
		    target.setRetries(2);
		    target.setTimeout(5000);
		    target.setVersion(SnmpConstants.version1); //Set the correct SNMP version here
		    PDU pdu = new PDU();
		    //Depending on the MIB attribute type, appropriate casting can be done here
		    pdu.add(new VariableBinding(new OID(strOID), new OctetString(Value))); 
		    pdu.setType(PDU.SET);
		    ResponseListener listener = new ResponseListener() {
		      public void onResponse(ResponseEvent event) {
		        PDU strResponse;
		        String result;
		        ((Snmp)event.getSource()).cancel(event.getRequest(), this);
		        strResponse = event.getResponse();
		        if (strResponse!= null) {
		          result = strResponse.getErrorStatusText();
		          System.out.println("Set Status is: "+result);
		        }
		      }};
		      snmp.send(pdu, target, null, listener);
		      snmp.close();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}
/*public String getinfo (String ip,String comunidad,  String oid) {
	SnmpTest client = new SnmpTest();
	String result;
	client.snmpGet(ip, comunidad, client.getoid(oid));
	return result;
}
	
public String getoid(String oid	){
	
	
	return oid;
}*/


	

	public ObjectIdentifierValue extractOid(MibSymbol symbol) {
	    MibValue  value;

	    if (symbol instanceof MibValueSymbol) {
	        value = ((MibValueSymbol) symbol).getValue();
	        if (value instanceof ObjectIdentifierValue) {
	            return (ObjectIdentifierValue) value;
	        }
	    }
	    return null;
	}
	
	@SuppressWarnings("unused")
	private void name2OID() {
		       String oid = null;
		       try {
		           MibLoader mibLoader = new MibLoader();
		           mibLoader.addResourceDir("mib");
		           Mib mib = mibLoader.load("RFC1213-MIB");
		           MibSymbol symbol = mib.getSymbol("ifDescr");
		           
		           if (symbol != null && symbol instanceof MibValueSymbol) {
		               MibValue value = ((MibValueSymbol) symbol).getValue();
		              if (value instanceof ObjectIdentifierValue) {
		                   oid = ((ObjectIdentifierValue) value).toString();
		               }
		           }
		           System.out.println(oid);
		       } catch (Exception ex) {
		           ex.printStackTrace();
		       }
		   }
	
	@SuppressWarnings("unused")
	private String name2OIDparametros(String namemib, String name){
		
		String oid = null;
	       try {
	           MibLoader mibLoader = new MibLoader();
	           mibLoader.addResourceDir("mib");
	           Mib mib = mibLoader.load(namemib);
	           MibSymbol symbol = mib.getSymbol(name);
	           
	           if (symbol != null && symbol instanceof MibValueSymbol) {
	               MibValue value = ((MibValueSymbol) symbol).getValue();
	              if (value instanceof ObjectIdentifierValue) {
	                   oid = ((ObjectIdentifierValue) value).toString();
	               }
	           }
	           System.out.println(oid);
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       }
		return oid;
		
		
	}
	

		public String snmpGetNext(String host, String community, String strOID) {
			  String strResponse="";
			  ResponseEvent response;
			  Snmp snmp;
			  try {
			    OctetString community1 = new OctetString(community);
			    host= host+"/"+"161";
			    Address tHost = new UdpAddress(host);
			    TransportMapping<?> transport = new DefaultUdpTransportMapping();
			    transport.listen();
			    CommunityTarget comtarget = new CommunityTarget();
			    comtarget.setCommunity(community1);
			    comtarget.setVersion(SnmpConstants.version1);
			    comtarget.setAddress(tHost);
			    comtarget.setRetries(2);
			    comtarget.setTimeout(5000);
			    PDU pdu = new PDU();
			    pdu.add(new VariableBinding(new OID(strOID)));
			    pdu.setType(PDU.GET); 
			    snmp = new Snmp(transport);
			    response = snmp.get(pdu,comtarget);
			    
			     
			    if(response != null) {
			      if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
			        PDU pduresponse=response.getResponse();
			        strResponse=pduresponse.getVariableBindings().firstElement().toString();
			        if(strResponse.contains("=")) {
			          int len = strResponse.indexOf("=");
			          strResponse=strResponse.substring(len+1, strResponse.length());
			        }
			      }
			    } else {
			      System.out.println("Looks like a TimeOut occured ");
			    }
			  
			  
			  
			    snmp.close();
			  } catch(Exception e) {
			     e.printStackTrace();
			  }
			System.out.println("Response="+strResponse);
			 return strResponse;
			}
	
	
	public static void main(String[]args){ 
//		SnmpManager client = new SnmpManager();
//		String host = "192.168.80.110";
//		String comunity="mpls-rw";
//		client.snmpGet("192.168.80.110","mpls-rw","1.3.6.1.2.1.1.1.0");
//		client.snmpGet("192.168.80.110","mpls-rw","1.3.6.1.2.1.1.5.0");
//		client.snmpGet("192.168.80.110", "mpls-rw", "1.3.6.1.2.1.2.2.1.2.1");
//		client.snmpGet(host, comunity, ".1.3.6.1.2.1.2.2.1.2.0");
//		client.name2OIDparametros("RFC1213-MIB", "ifDescr");

		OID rootoid = new OID(".1.3.6.1.2.1.2.2");
		System.out.println("oid:" + rootoid.toString());

		
		
		//client.snmpSet("192.168.80.110", "mpls-rw", "1.3.6.1.2.1.1.5.0", "PEC1");
	
	}
	
	
	
}
