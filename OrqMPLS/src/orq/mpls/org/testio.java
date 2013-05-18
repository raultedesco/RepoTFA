package orq.mpls.org;

public class testio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//(config)#
		//(config-if)#
		//#
		//(config-router)#
		//(config-vrf)#
		
		// este string deberia venir del stream del telnet
	String cadena1 = "server(config)#";
	// este string seria un valor de una tabla de todos los posibles prompts
	String cadena2 = "(config)#";
	//obtengo la posicion del prompt dentro de todo lo que viene desde el stram in
	int valor= cadena1.indexOf(cadena2);
	//lo extraigo de la cadena y comparo de nuevo si es el promp correcto para luego enviar el comando correcto
	String extraido = cadena1.substring(valor, cadena1.length());
	if (extraido.equals(cadena2)){
		System.out.println("prmpt correcto");
	}
	//System.out.println(cadena1.indexOf(cadena2));;
	System.out.println(extraido);
		
		
		

	}

}
