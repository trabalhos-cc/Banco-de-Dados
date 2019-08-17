package tipoLogradouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoLogradouroDAO {


	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TipoLogradouro> buscarTipoLogradouro()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<TipoLogradouro> tipos = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from tipoLogradouro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoLogradouro tp = new TipoLogradouro();
				tp.setIdTipoLogradouro(rs.getInt("idTipoLogradouro"));
				tp.setNome(rs.getString("nome"));
				tipos.add(tp);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return tipos;
	}
	
	//insert
	public void inserirTipoLogradouro (int idTipoLogradouro, String nome) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into tipoLogradouro (idTipoLogradouro, nome");
		sql.append("values (?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, idTipoLogradouro);
		comando.setString(2, nome);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
}
