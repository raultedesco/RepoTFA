package orq.mpls.org;

import java.util.ArrayList;

import javax.swing.JTextArea;



public class CurrentConfig {

private int countestatic;
private int countdinamic;
private boolean ifCpe;
private boolean ifPe;
private boolean ifP;
private boolean bgpFlag;
private boolean eigrpFlag;
private boolean cefFlag;
private boolean mplsipGlobalFlag;
private boolean [] checkMplsIPOverIface = new boolean[4];
private String vrfname;
private String vrfrd;
private String vrfrt;
private String bgpProcess;
private String eigrpProcess;
private String [][] rutasEstaticas= new String[5][4];
private String [] rutasdinamic= new String[5];
private String [] mascarasdinamic= new String[5];
private ArrayList<String> interfacesNames = new ArrayList<>();
private String [] ipsInterfaces = new String[4];
private String [] masksInterfaces = new String[4];
private String [] forwardingVRF = new String[4];
private String bgpNeighbor;
private String bgpRemoteAs;



public String[] getForwardingVRF() {
	return forwardingVRF;
}
public void setForwardingVRF(String[] forwardingVRF) {
	this.forwardingVRF = forwardingVRF;
}
public String getInterfacesNames(int index) {
	return interfacesNames.get(index);
}
public void setInterfacesNames(ArrayList<String> interfacesbysnmp) {
	this.interfacesNames = interfacesbysnmp;
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
	return checkMplsIPOverIface;
}
public void setCheckMPLSIP(boolean[] checkMPLSIP) {
	this.checkMplsIPOverIface = checkMPLSIP;
}
public String[][] getRutasEstaticas() {
	return rutasEstaticas;
}
public void setRutasEstaticas(String[][] rutas) {
	this.rutasEstaticas = rutas;
}

public boolean isBgpflag() {
	return bgpFlag;
}
public void setBgpflag(boolean bgpflag) {
	this.bgpFlag = bgpflag;
}
public boolean isEigrpflag() {
	return eigrpFlag;
}
public void setEigrpflag(boolean eigrpflag) {
	this.eigrpFlag = eigrpflag;
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
	return cefFlag;
}
public void setCefboolean(boolean cefboolean) {
	this.cefFlag = cefboolean;
}
public boolean isMplsipboolean() {
	return mplsipGlobalFlag;
}
public void setMplsipboolean(boolean mplsipboolean) {
	this.mplsipGlobalFlag = mplsipboolean;
}

public String listConfig() {
	if (cefFlag == true) {
		System.out.println("CEF: Activado");
	} 
	else {
		System.out.println("CEF: Desactivado");
	}
	if (ifCpe) {
		System.out.println("Tipo de Dispositivo: CPE");
	}
	if (mplsipGlobalFlag == true) {
		System.out.println("MPLS: Activado");
		System.out.println("Vrf : " + getVrfname());
		System.out.println("Router Distinguisher (RD): " + getVrfrd());
		System.out.println("Route Target (RD): " + getVrfrt());
	} 
	else {
		System.out.println("MPLS: Desactivado");
	}
	
	if (bgpFlag) {
		System.out.println("BGP:Activado " + "Proceso: " + getBgpProcess());
	}
	else{
		System.out.println("BGP: Desactivado");
		
	}
	if (eigrpFlag) {
		System.out.println("EIGRP:Activado " + "Proceso: " + getEigrpProcess());
	}
	else {
		System.out.println("EIGRP: Desactivado");
	}
	return null;
	
	
	
}

public String listCEF(){
	if (cefFlag == true) {
		return "CEF: Activado\n";
	} 
	else {
		return "CEF: Desactivado\n";
	}
	
	
	
}



public String listMPLS(){
	if (mplsipGlobalFlag == true) {
		return "MPLS: Activado\n" + "Vrf : " + getVrfname()+"\n" + "Router Distinguisher (RD): " 
	+ getVrfrd() +"\n"+"Route Target (RD): " + getVrfrt() + "\n" ;
		
			} 
	else {
		return "MPLS: Desactivado\n";
	}
	
	
}


public String listBGP() {
	if (bgpFlag) {
		return "BGP:Activado " + "\n" + "Proceso: " + getBgpProcess() + " AS: "+getBgpRemoteAs() +" Vecino BGP: " + getBgpNeighbor()+"\n";
	}
	else{
		return "BGP: Desactivado\n";
}
}

public String listEIGRP() {
	if (eigrpFlag) {
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
	return "Interface: " + getInterfacesNames(j) + 
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
	return interfacesNames;
}

public int getCountestatic() {
	return countestatic;
}
public void setCountestatic(int countestatic) {
	this.countestatic = countestatic;
}
public int getCountdinamic() {
	return countdinamic;
}
public void setCountdinamic(int countdinamic) {
	this.countdinamic = countdinamic;
}


public JTextArea showparameter(JTextArea configResultView) {
	// TODO agregar al jtextarea los valores de la configuracion segun corresponda
if (ifCpe) {
	configResultView.append(this.listCEF());
	if (bgpFlag) {
		configResultView.append(this.listBGP());
		}
	if (eigrpFlag) {
		configResultView.append(this.listEIGRP());
		}
	for (int j = 0; j < 4; j++) {
		
		configResultView.append(this.listinterfaces_Vrf_mplsip(j));
	}
if (countestatic !=0) {
	

		configResultView.append("|  Red Origen  ||    Mascara      ||  Red Destino    ||    Mascara      |\n");
	for (int i = 0; i < countestatic; i++) {
		for (int j = 0; j < 4; j++) {

			configResultView.append(this.listrutasEstaticas(rutasEstaticas,i,j));
			
	}
		configResultView.append("\n");
	}
	
}	
	}


if (ifPe) {
	
}

if (ifP) {
	
}
	return configResultView;
}
private String listrutasEstaticas(String[][] rutasEstaticas, int i, int j) {
	
	return "| "+ rutasEstaticas[i][j]+" |";
}



}




	
