package logradouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class LogradouroDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Logradouro> buscarLogradouro()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<Logradouro> logradouros = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from logradouro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Logradouro log = new Logradouro();
				log.setIdLogradouro(rs.getInt("idLogradouro"));
				log.setNome(rs.getString("nome"));
				log.setIdTipoLogradouro(rs.getInt("idTipoLogradouro"));
				logradouros.add(log);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return logradouros;
	}
	
	//insert
	public void inserirLogradouro (int idLogradouro, String nome, int idTipoLogradouro) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into logradouro (idLogradouro, nome, idTipoLogradouro");
		sql.append("values (?,?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, idLogradouro);
		comando.setString(2, nome);
		comando.setInt(3, idTipoLogradouro);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
}
