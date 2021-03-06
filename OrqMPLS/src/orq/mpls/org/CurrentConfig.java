package orq.mpls.org;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;




public class CurrentConfig {
private Date dateEstamp;
private int countestatic;
private int countdinamic;
private boolean ifCpe;
private boolean ifPe;
private boolean ifP;
private String DeviceType;
private boolean bgpFlag;
private boolean eigrpFlag;
private boolean cefFlag;
private boolean mplsipGlobalFlag;
private boolean vrfFlag;
private boolean [] checkMplsIPOverIface = new boolean[4];
private String vrfname;
private String vrfrd;
private String vrfrt;
private String bgpProcess;
private String eigrpProcess;
private String [][] rutasEstaticas= new String[3][3];
private String [][] rutasDinamicas= new String[3][2];
private ArrayList<String> interfacesNames = new ArrayList<>();
private String [] ipsInterfaces = new String[4];
private String [] masksInterfaces = new String[4];
private String [] forwardingVRF = new String[4];
private String bgpNeighbor;
private String bgpRemoteAs;
private String bgpNeighborInternal;
private String bgpRemoteAsInternal;
private String telnetip;
private String telnetpuerto;
private String telnetcomunity;
private String telnetusuario;
private String telnetpassword;



public void SetDeviceType() {
if (ifCpe) {
	this.DeviceType="CPE";
}
if (ifPe) {
	this.DeviceType="PE";
}
if (ifP) {
	this.DeviceType="P";
}
}
public String[] getForwardingVRF() {
	return forwardingVRF;
}
public void setForwardingVRF(String[] forwardingVRF) {
	this.forwardingVRF = forwardingVRF;
}
public String getInterfacesNames(int index) {
	return interfacesNames.get(index);
}
public int getInterfacesNamesSize(){
	return this.interfacesNames.size();
	
	
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
public String[][] getRutasDinamicas() {
	return rutasDinamicas;
}
public void setRutasDinamicas(String[][] rutasDinamicas) {
	this.rutasDinamicas = rutasDinamicas;
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
public boolean isVrfFlag() {
	return vrfFlag;
}
public void setVrfFlag(boolean vrfFlag) {
	this.vrfFlag = vrfFlag;
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
		return "MPLS: Activado\n"; 
		
			} 
	else {
		return "MPLS: Desactivado\n";
	}
	
	
}

public String listBGP() {
	if (bgpFlag) {
		return "BGP:Activado " + "\n" + "Proceso: " + getBgpProcess() + "External AS: "+getBgpRemoteAs() +" Vecino BGP External: " + getBgpNeighbor()+"\n" 
				+ "Internal AS: "+getBgpRemoteAsInternal() +" Vecino BGP Internal: " + getBgpNeighborInternal()+"\n";
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

public String listStaticRoutes() {
	// TODO Auto-generated method stub
	return null;
}

public String listinterfaces_Vrf_mplsip(int j){
	return "Interface: " + getInterfacesNames(j) + 
									" |IP: " + getIpsInterfaces()[j] + " |Mascara: " + getMasksInterfaces()[j] + 
									" \n[Forwarding VRF:" + getForwardingVRF()[j] + "]"+"\n";
	
	
}

public String listVrf(){
	if (vrfFlag) {
		return "Vrf : " + getVrfname()+"\n" + "Router Distinguisher (RD): " 
				+ getVrfrd() +"\n"+"Route Target (RD): " + getVrfrt() + "\n" ;
	}
	return null;
	
} 




public JTextArea showparameter(JTextArea configResultView) {
	// TODO agregar al jtextarea los valores de la configuracion segun corresponda


	
//Dispositivo CPE
if (ifCpe) {
	configResultView.append(this.listCEF());
	//BGP
	checkRoutingProtocol(configResultView);
	
	}

//Dispositivo PE
if (ifPe) {
	checkmplsipGlobal(configResultView);
	if (vrfFlag) {
		configResultView.append(this.listVrf());
	}
	checkRoutingProtocol(configResultView);
}
//Dispositivo P
if (ifP) {
	checkmplsipGlobal(configResultView);
	checkRoutingProtocol(configResultView);
}
//INTERFACES Para todos los Dispositivos
for (int j = 0; j < this.interfacesNames.size(); j++) {
	configResultView.append(this.listinterfaces_Vrf_mplsip(j));
}

//RUTAS ESTATICAS Para todos los Dispositivos
if (countestatic !=0) {
	

	configResultView.append("|  Red Origen  ||    Mascara      ||  Red Destino    \n");
for (int i = 0; i < countestatic; i++) {
	for (int j = 0; j < 3; j++) {

		configResultView.append(this.listrutasEstaticas(rutasEstaticas,i,j));
		
}
	configResultView.append("\n");
}

}	
//RUTAS DINAMICAS Para todos los Dispositivos
if (countdinamic !=0) {
	

	configResultView.append("| Rutas BGP/EIGRP  ||    Mascara      |\n");
for (int i = 0; i < countdinamic; i++) {
	for (int j = 0; j < 2; j++) {

		configResultView.append(this.listrutasDinamicas(rutasDinamicas,i,j));
		
}
	configResultView.append("\n");
}

}	
	
	return configResultView;
}

public void checkmplsipGlobal(JTextArea configResultView) {
	if (mplsipGlobalFlag) {
		configResultView.append(this.listMPLS());
	}
}
public void checkRoutingProtocol(JTextArea configResultView) {
	if (bgpFlag) {
		configResultView.append(this.listBGP());
		}
	//EIGRP
	if (eigrpFlag) {
		configResultView.append(this.listEIGRP());
		}
}
private String listrutasDinamicas(String[][] rutasDinamicas, int i, int j) {
	// TODO Auto-generated method stub
	return "| "+ rutasDinamicas[i][j]+" |";
}
private String listrutasEstaticas(String[][] rutasEstaticas, int i, int j) {
	
	return "| "+ rutasEstaticas[i][j]+" |";
}
public String getBgpNeighborInternal() {
	return bgpNeighborInternal;
}
public void setBgpNeighborInternal(String bgpNeighborInternal) {
	this.bgpNeighborInternal = bgpNeighborInternal;
}
public String getBgpRemoteAsInternal() {
	return bgpRemoteAsInternal;
}
public void setBgpRemoteAsInternal(String bgpRemoteAsInternal) {
	this.bgpRemoteAsInternal = bgpRemoteAsInternal;
}
public void setTelnetIP(String ip) {
	// TODO Auto-generated method stub
	this.telnetip=ip;
}
public void setTelnetPuerto(String puerto) {
	// TODO Auto-generated method stub
	this.telnetpuerto=puerto;
}
public void setTelnetComunity(String comunity) {
	// TODO Auto-generated method stub
	this.telnetcomunity=comunity;
}
public void setTelnetUsuario(String usuario) {
	// TODO Auto-generated method stub
	this.telnetusuario=usuario;
}
public void setTelnetPassword(String password) {
	// TODO Auto-generated method stub
	this.telnetpassword=password;
}
public String getTelnetip() {
	return telnetip;
}
public Object getTelnetpuerto() {
	return telnetpuerto;
}
public String getTelnetcomunity() {
	return telnetcomunity;
}
public String getTelnetusuario() {
	return telnetusuario;
}
public String getTelnetpassword() {
	return telnetpassword;
}
public Date getDateEstamp() {
	return dateEstamp;
}
public void setDateEstamp(Date dateEstamp) {
	this.dateEstamp = dateEstamp;
}
@Override
public String toString() {
	
	// TODO Auto-generated method stub
	return "Tipo de Dispositivo: "+ DeviceType+" [ " +this.getDateEstamp() + " ] ";
}
public String getDeviceType() {
	return DeviceType;
}




}




	
