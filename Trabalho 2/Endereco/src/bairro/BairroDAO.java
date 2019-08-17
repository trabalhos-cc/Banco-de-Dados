package bairro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bin.conexaoBD.Conexao;

public class BairroDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Bairro> buscarBairro()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Bairro> bairros = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from bairro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Bairro b = new Bairro();
				b.setIdBairro(rs.getInt("idBairro"));
				b.setNomeBairro(rs.getString("nomeBairro"));
				bairros.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return bairros;
		}
		
		//insert
		public void inserirEndereco (int idBairro, String nomeBairro) throws Exception{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into bairro (idBairro, nomeBairro");
			sql.append("values (?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, idBairro);
			comando.setString(2, nomeBairro);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		
}
