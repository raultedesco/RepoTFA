package orq.mpls.org;
import java.sql.*;

public class MysqlManager {

	/**
	 * @param args
	 */
	public static void main(String args[]){
		try {
		//Cargar clase de controlador de base de datos
		Class.forName("com.mysql.jdbc.Driver");
		//Crear el objeto de conexion a la base de datos
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/orqmpls?user=root&password=spurs200");
		//Crear objeto Statement para realizar queries a la base de datos
		Statement instruccion = conexion.createStatement();
		//Un objeto ResultSet, almacena los datos de resultados de una consulta
		ResultSet tabla = instruccion.executeQuery("SELECT * from comandos");
		System.out.println("Comandos");
		//recorrer el resulset
		 while (tabla.next())
         {
             System.out.println (tabla.getInt ("idcomandos") + " " + tabla.getString ("descripcion")+ 
                 " ");
         }
		 //se cierra la conexion con la base de datos
		conexion.close();
		}
	
		catch(ClassNotFoundException e){ System.out.println(e); }
		catch(SQLException e){ System.out.println(e); }
		catch(Exception e){ System.out.println(e); }
		}


}