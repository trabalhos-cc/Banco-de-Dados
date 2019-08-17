package uf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class UfDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Uf> buscarUf()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<Uf> Ufs = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from Uf;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Uf uf = new Uf();
				uf.setIdUF(rs.getInt("idUf"));
				uf.setNome(rs.getString("nome"));
				uf.setIdPais(rs.getInt("idPais"));
				Ufs.add(uf);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ufs;
	}
	
	//insert
	public void inserirUf (int idUf, String nome, int idPais) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into uf (idUf, nome, idPais");
		sql.append("values (?,?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, idUf);
		comando.setString(2, nome);
		comando.setInt(3, idPais);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
}
