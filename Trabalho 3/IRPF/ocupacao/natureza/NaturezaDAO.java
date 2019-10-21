package natureza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class NaturezaDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Natureza> buscarNatureza()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Natureza> natureza = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from natureza;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Natureza b = new Natureza();
				b.setIdNatureza(rs.getInt("idnatureza"));
				b.setNome(rs.getString("nome"));
				natureza.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return natureza;
		}
	
	//insert
	public void inserirNatureza (String nome) throws Exception{
		
		if(NaturezaDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into natureza (idnatureza, nome)");
		sql.append("values (?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, maxId());
		comando.setString(2, nome);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	
	//delete
	public void removeNatureza (String nome) throws Exception{
		if(!NaturezaDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from natureza where idnatureza = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		NaturezaDAO tp = new NaturezaDAO ();
		List<Natureza> tipos = new ArrayList<>(); 
		tipos = tp.buscarNatureza();
		
		for(int i = 0; i < tipos.size(); i++) {
			Natureza t = new Natureza();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		NaturezaDAO tp = new NaturezaDAO();
		List<Natureza> tipos = new ArrayList<>();
		tipos = tp.buscarNatureza();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			Natureza t = new Natureza();
			t = tipos.get(i);
			if(t.getIdNatureza() > id) {
				id = t.getIdNatureza();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		NaturezaDAO tp = new NaturezaDAO();
		List<Natureza> tipos = new ArrayList<>();
		tipos = tp.buscarNatureza();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			Natureza t = new Natureza();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdNatureza();
			}
		}
		return id;
	}
}
