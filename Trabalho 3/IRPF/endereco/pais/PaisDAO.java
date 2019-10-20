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
			stmt = conn.prepareStatement("select * from pais;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pais p = new Pais();
				p.setIdPais(rs.getInt("idpais"));
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
		
		sql.append("insert into pais (idpais, nome)");
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
	
	/*delete*/
	public void removePais (String nome) throws Exception{
		if(!PaisDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from pais where idpais = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		PaisDAO tp = new PaisDAO ();
		List<Pais> tipos = new ArrayList<>(); 
		tipos = tp.buscarPais();
		
		for(int i = 0; i < tipos.size(); i++) {
			Pais t = new Pais();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		PaisDAO tp = new PaisDAO ();
		List<Pais> tipos = new ArrayList<>(); 
		tipos = tp.buscarPais();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			Pais t = new Pais();
			t = tipos.get(i);
			if(t.getIdPais() > id) {
				id = t.getIdPais();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		PaisDAO tp = new PaisDAO ();
		List<Pais> tipos = new ArrayList<>(); 
		tipos = tp.buscarPais();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			Pais t = new Pais();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdPais();
			}
		}
		System.out.println(id);
		return id;
	}
}
