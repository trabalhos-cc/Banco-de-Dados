package singleton;

import java.sql.SQLException;

import ConexaoBD.ConexaoBase;

public class SingletonBD extends ConexaoBase{

	private static SingletonBD me = new SingletonBD();
	private SingletonBD() {};
	
	public static SingletonBD getInstance() throws SQLException {
		if(me.stmt == null) {
			me.connect();
		}
		
		return me;
	}
	
	public String toString () {
		try {
			return "Status connection Banco database: " + (me.db.isClosed()? "closed" : "open");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}