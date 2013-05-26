package orq.mpls.org;

import java.util.ArrayList;



public class CurrentConfig {

private boolean ifCpe;
private boolean ifPe;
private boolean ifP;
private String vrfname;
private String vrfrd;
private String vrfrt;
private boolean bgpflag;
private boolean eigrpflag;
private String bgpProcess;
private String eigrpProcess;
private boolean cefboolean;
private boolean mplsipboolean;
private String [] rutas= new String[5];
private String [] mascaras= new String[5];
private String [] rutasdinamic= new String[5];
private String [] mascarasdinamic= new String[5];
private ArrayList<String> interfaces = new ArrayList<>();
private String [] ipsInterfaces = new String[4];
private String [] masksInterfaces = new String[4];
private boolean [] checkMPLSIP = new boolean[4];
private String [] forwardingVRF = new String[4];
private String bgpNeighbor;
private String bgpRemoteAs;



public String[] getForwardingVRF() {
	return forwardingVRF;
}
public void setForwardingVRF(String[] forwardingVRF) {
	this.forwardingVRF = forwardingVRF;
}
public String getInterfaces(int index) {
	return interfaces.get(index);
}
public void setInterfaces(ArrayList<String> interfacesbysnmp) {
	this.interfaces = interfacesbysnmp;
}
public String[] getIpsInterfaces() {
	return ipsInterfaces;
}
public void setIpsInterfaces(String[] ipsInterfaces) {
	this.ipsInterfaces = ipsInterfaces;
}
public String[] getMasksInterfaces() {
	return masksInterfaces;
}
public void setMasksInterfaces(String[] masksInterfaces) {
	this.masksInterfaces = masksInterfaces;
}
public boolean[] getCheckMPLSIP() {
	return checkMPLSIP;
}
public void setCheckMPLSIP(boolean[] checkMPLSIP) {
	this.checkMPLSIP = checkMPLSIP;
}
public String[] getRutas() {
	return rutas;
}
public void setRutas(String[] rutas) {
	this.rutas = rutas;
}
public String[] getMascaras() {
	return mascaras;
}
public void setMascaras(String[] mascaras) {
	this.mascaras = mascaras;
}
public boolean isBgpflag() {
	return bgpflag;
}
public void setBgpflag(boolean bgpflag) {
	this.bgpflag = bgpflag;
}
public boolean isEigrpflag() {
	return eigrpflag;
}
public void setEigrpflag(boolean eigrpflag) {
	this.eigrpflag = eigrpflag;
}
public String getEigrpProcess() {
	return eigrpProcess;
}
public void setEigrpProcess(String eigrpProcess) {
	this.eigrpProcess = eigrpProcess;
}
public String getVrfname() {
	return vrfname;
}
public void setVrfname(String vrfname) {
	this.vrfname = vrfname;
}
public String getVrfrd() {
	return vrfrd;
}
public void setVrfrd(String vrfrd) {
	this.vrfrd = vrfrd;
}
public String getVrfrt() {
	return vrfrt;
}
public void setVrfrt(String vrfrt) {
	this.vrfrt = vrfrt;
}
public String getBgpProcess() {
	return bgpProcess;
}
public void setBgpProcess(String bgpProcess) {
	this.bgpProcess = bgpProcess;
}
public boolean isCefboolean() {
	return cefboolean;
}
public void setCefboolean(boolean cefboolean) {
	this.cefboolean = cefboolean;
}
public boolean isMplsipboolean() {
	return mplsipboolean;
}
public void setMplsipboolean(boolean mplsipboolean) {
	this.mplsipboolean = mplsipboolean;
}

public void listConfig() {
	if (cefboolean == true) {
		System.out.println("CEF: Activado");
	} 
	else {
		System.out.println("CEF: Desactivado");
	}
	
	if (mplsipboolean == true) {
		System.out.println("MPLS: Activado");
		System.out.println("Vrf : " + getVrfname());
		System.out.println("Router Distinguisher (RD): " + getVrfrd());
		System.out.println("Route Target (RD): " + getVrfrt());
	} 
	else {
		System.out.println("MPLS: Desactivado");
	}
	
	if (bgpflag) {
		System.out.println("BGP:Activado " + "Proceso: " + getBgpProcess());
	}
	else{
		System.out.println("BGP: Desactivado");
		
	}
	if (eigrpflag) {
		System.out.println("EIGRP:Activado " + "Proceso: " + getEigrpProcess());
	}
	else {
		System.out.println("EIGRP: Desactivado");
	}
	
	
	
}

public String listCEF(){
	if (cefboolean == true) {
		return "CEF: Activado\n";
	} 
	else {
		return "CEF: Desactivado\n";
	}
	
	
	
}



public String listMPLS(){
	if (mplsipboolean == true) {
		return "MPLS: Activado\n" + "Vrf : " + getVrfname()+"\n" + "Router Distinguisher (RD): " 
	+ getVrfrd() +"\n"+"Route Target (RD): " + getVrfrt() + "\n" ;
		
			} 
	else {
		return "MPLS: Desactivado\n";
	}
	
	
}


public String listBGP() {
	if (bgpflag) {
		return "BGP:Activado " + "Proceso: " + getBgpProcess() + "\n";
	}
	else{
		return "BGP: Desactivado\n";
}
}

public String listEIGRP() {
	if (eigrpflag) {
		return "EIGRP:Activado " + "Proceso: " + getEigrpProcess() + "\n";
	}
	else{
		return "EIGRP: Desactivado\n";
}
}
public String getBgpNeighbor() {
	return bgpNeighbor;
	
}
public void setBgpNeighbor(String bgpNeighbor) {
	this.bgpNeighbor = bgpNeighbor;
}


public String getBgpRemoteAs() {
	return bgpRemoteAs;
}
public void setBgpRemoteAs(String bgpRemoteAs) {
	this.bgpRemoteAs = bgpRemoteAs;
}
public String listStaticRoutes() {
	// TODO Auto-generated method stub
	return null;
}

public String listinterfaces_Vrf_mplsip(int j){
	return "Interface: " + getInterfaces(j) + 
									" |IP: " + getIpsInterfaces()[j] + " |Mascara: " + getMasksInterfaces()[j] + 
									" \n[Forwarding VRF:" + getForwardingVRF()[j] + "]"+"\n";
	
	
}
public String [] getRutasdinamic() {
	return rutasdinamic;
}
public void setRutasdinamic(String [] rutasdinamic) {
	this.rutasdinamic = rutasdinamic;
}
public String [] getMascarasdinamic() {
	return mascarasdinamic;
}
public void setMascarasdinamic(String [] mascarasdinamic) {
	this.mascarasdinamic = mascarasdinamic;
}
public boolean isIfCpe() {
	return ifCpe;
}
public void setIfCpe(boolean ifCpe) {
	this.ifCpe = ifCpe;
}
public boolean isIfPe() {
	return ifPe;
}
public void setIfPe(boolean ifPe) {
	this.ifPe = ifPe;
}
public boolean isIfP() {
	return ifP;
}
public void setIfP(boolean ifP) {
	this.ifP = ifP;
}
public ArrayList<String> getInterfaces() {
	return interfaces;
}

}




	
