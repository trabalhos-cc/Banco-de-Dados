package conexaoBD;

import java.sql.*;

public class Conexao {

	protected Statement stmt = null;
	protected Connection conn = null;
	
	public  Connection abrir () throws Exception{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection)DriverManager.getConnection
					("jdbc:mysql://localhost:3306/fatura", "root", "root");
			
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
