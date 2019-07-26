package conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	protected Statement stmt = null;
	protected Connection conn = null;
	
	public  Connection abrir () throws Exception{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection)DriverManager.getConnection
					(" ", "root", "root");
			
			createStatement();
			
			return conn;
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Não foi possivel conectar!");
		}
		return null;
	}
	
	public Statement getStatement() {
		return stmt;
	}
	
	protected void createStatement() throws SQLException {
		if (this.stmt != null)
			this.stmt.close();

		stmt = conn.createStatement();
	}
}
