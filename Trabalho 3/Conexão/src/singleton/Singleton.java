package singleton;

import java.sql.SQLException;

import conexaoBD.Conexao;

public class Singleton extends Conexao {

	private static Singleton me = new Singleton();
	private Singleton() {};
	
	public static Singleton getInstance() throws SQLException, Exception{
		if(me.stmt == null) {
			me.abrir();
		}
		return me;
	}
	
	public String toString() {
		try{
			return "Status connection Banco database: " + (me.conn.isClosed()?"closed" : "open");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
