package pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class PaisDAO {

	protected static int id = 0;
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
			stmt = conn.prepareStatement("select * from país;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pais p = new Pais();
				p.setIdPais(rs.getInt("idPaís"));
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
	public void inserirPais (String nome) throws Exception{
		
		if(PaisDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into país (idPaís, nome)");
		sql.append("values (?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, id);
		comando.setString(2, nome);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public static boolean existData (String nome)  throws Exception{
		PaisDAO tp = new PaisDAO ();
		List<Pais> tipos = new ArrayList<>(); 
		
		tipos = tp.buscarPais();
		PaisDAO.id = tipos.size();
		for(int i = 0; i < tipos.size(); i++) {
			Pais t = new Pais();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		
		return false;
	}
}
