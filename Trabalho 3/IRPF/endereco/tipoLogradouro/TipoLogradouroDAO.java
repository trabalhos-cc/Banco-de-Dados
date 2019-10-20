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

	protected static int id = 0;
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
			stmt = conn.prepareStatement("select * from tipologradouro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoLogradouro tp = new TipoLogradouro();
				tp.setIdTipoLogradouro(rs.getInt("idtipoLogradouro"));
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
	public void inserirTipoLogradouro (String nome) throws Exception{
		
		if(TipoLogradouroDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into tipologradouro (idtipoLogradouro, nome)");
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
	public void removeTipoLogradouro (String nome) throws Exception{
		if(!TipoLogradouroDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from tipologradouro where idtipoLogradouro = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	
	public static boolean existData (String nome)  throws Exception{
		TipoLogradouroDAO tp = new TipoLogradouroDAO ();
		List<TipoLogradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoLogradouro();
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoLogradouro t = new TipoLogradouro();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		TipoLogradouroDAO tp = new TipoLogradouroDAO ();
		List<TipoLogradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoLogradouro();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoLogradouro t = new TipoLogradouro();
			t = tipos.get(i);
			if(t.getIdTipoLogradouro() > id) {
				id = t.getIdTipoLogradouro();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		TipoLogradouroDAO tp = new TipoLogradouroDAO ();
		List<TipoLogradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoLogradouro();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoLogradouro t = new TipoLogradouro();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdTipoLogradouro();
			}
		}
		return id;
	}
}
