package inventariobombero.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
	private static String servidor="jdbc:mysql://localhost:3306/bomberoinventario";
	private static String user= "root";
	private static String password = "";
	private static String driver = "com.mysql.jdbc.Driver";
	//private static Connection con = null;

	Connection conection;
	public BDConexion(){
		this.conection = null;
		//try{
		//	Class.forName(driver).newInstance();  //Levanto el Driver de Conexion
		//	con = DriverManager.getConnection(servidor,user,password);  //Establezco la conexion
		//	Class.forName("com.mysql.jdbc.Driver").newInstance();  //Levanto el Driver de Conexion
		//	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHorarioclase","root","");
		//}catch(ClassNotFoundException |InstantiationException |IllegalAccessException | SQLException ex){
		//	ex.printStackTrace();
		//	System.out.println("Conexion fallida: " + ex.toString());   //Muestro el error 
		//}
		
		//metodo para obtener la conexion
		
		
		
		
//	public Connection getConnection(){
	//	return con;   //Devuelve el objeto de conexion
	//}
	
	}
	
	public Connection getConnection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class mysql no encontrada");
		}
		
		try {
			conection=(Connection) DriverManager.getConnection(servidor,user,password);
			//this.conection= DriverManager.getConnection(this.servidor,this.user,this.password);
			//System.out.println("conexion exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error en conexion");
		}
		
		return conection;
	}
}


