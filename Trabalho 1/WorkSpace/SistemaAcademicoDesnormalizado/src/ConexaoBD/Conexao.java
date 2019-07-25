package ConexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	
	//conecta ao banco
	public static Connection abrir() throws Exception{
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/sisacademidesnormalizado", "root", "root");
			
			if(conn != null) {System.out.println("conectado");}
			 return conn;
			

		}catch (Exception e) {
			System.out.println(e);
			System.out.println("not connect");
		}
		return null;
		
	}
}
