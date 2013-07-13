package orq.mpls.org;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4manager {

	
	private static String DB4OFILENAME = "orqmpls";

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
}
