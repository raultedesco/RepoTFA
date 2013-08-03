package orq.mpls.org;


import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;



public class Db4manager {
	
	static List<CurrentConfig> lc1;
	
	private static String DB4OFILENAME = "orqmpls.db4o";

	public static void storeCurrentconfigObjects (CurrentConfig c1) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
		        .newConfiguration(), DB4OFILENAME  );
		try {
		    // do something with db4o
			
			db.store(c1);
		} finally {
		    db.close();
		}
	}
	public static void listAllconfigObjects(){
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
		        .newConfiguration(), DB4OFILENAME  );
		try {
		    // do something with db4o
			CurrentConfig c1_empty= new CurrentConfig();
			ObjectSet<Object> result =db.queryByExample(c1_empty);	
			listResult(result);
		} finally {
		    db.close();
		}
		
	}
	

public static void listResult(List<?> result){
    System.out.println(result.size());
    for (Object o : result) {
        System.out.println(o);
    }
}

public static JTable mostrarjtable(JTable table, DefaultTableModel modelo){
	//TODO chequear este metodo para llenar el jtable
ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
	        .newConfiguration(), DB4OFILENAME  );

try {

		List<CurrentConfig> ps = null;
			ps = db.query(CurrentConfig.class);//Cojemos todos los registros



		Object[] fila = new Object[4];
		for (CurrentConfig c1 : ps) {//A�adimos los registros a la tabla
			fila[0] = c1.getDeviceType();
			fila[1] = c1.getDateEstamp();
			fila[3] = c1;//A�adimos tambien el objeto
			modelo.addRow(fila);
		}
		
		table.updateUI();
	
}finally {
	db.close();
}
return table;
}


}
