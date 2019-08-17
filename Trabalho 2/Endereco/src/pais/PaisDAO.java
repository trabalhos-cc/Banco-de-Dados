package pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bin.conexaoBD.Conexao;

public class PaisDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Pais> buscarPais()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<Pais> paises = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from pais;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pais p = new Pais();
				p.setIdPais(rs.getInt("idPais"));
				p.setNome(rs.getString("nome"));
				paises.add(p);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return paises;
	}
	
	//insert
	public void inserirPais (int idPais, String nome) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into pais (idPais, nome");
		sql.append("values (?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, idPais);
		comando.setString(2, nome);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	
}
