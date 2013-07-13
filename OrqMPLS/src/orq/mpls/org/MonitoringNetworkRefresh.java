package orq.mpls.org;
import javax.swing.JTextArea;

public class MonitoringNetworkRefresh {

	AutomatedTelnet send;
	CurrentConfig c1;
	public MonitoringNetworkRefresh(CurrentConfig current) {
		
		this.c1 = current;
		System.out.println(c1.getTelnetip());
		send = new AutomatedTelnet(c1.getTelnetip(),c1.getTelnetusuario(), c1.getTelnetpassword());

		
	}
	/**
	 * @param configGenViewRoutingTable
	 */
	public void refreshIPRoutingTable(JTextArea configGenViewRoutingTable) {
		try {
			
			send.ena("cisco");
			configGenViewRoutingTable.append(send.showIPRoute());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally{
			
			send.disconnect();
		}
	}
	
	public void refreshIPInterfaceBrief(JTextArea configGenViewIPInterfacesBrief) {
		try {
			
			send.ena("cisco");
			configGenViewIPInterfacesBrief.append(send.showIPInterfacesBrief());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	finally{
			
			send.disconnect();
		}
	}
}
