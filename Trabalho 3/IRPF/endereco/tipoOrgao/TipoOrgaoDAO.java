package tipoOrgao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoOrgaoDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TipoOrgao> buscarTipoOrgao()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TipoOrgao> tipoOrgao = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from tipoorgao;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoOrgao to = new TipoOrgao();
				to.setIdTipoOrgao(rs.getInt("idtipoOrgao"));
				to.setNome(rs.getString("nome"));
				tipoOrgao.add(to);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return tipoOrgao;
		}
	
	//insert
	public void inserirTipoOrgao (String nome) throws Exception{
		
		if(TipoOrgaoDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into tipoorgao  (idtipoOrgao, nome)");
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
	public void removeTipoOrgao  (String nome) throws Exception{
		if(!TipoOrgaoDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from tipoorgao where idtipoOrgao = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		TipoOrgaoDAO tp = new TipoOrgaoDAO ();
		List<TipoOrgao> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoOrgao();
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoOrgao t = new TipoOrgao();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		TipoOrgaoDAO tp = new TipoOrgaoDAO ();
		List<TipoOrgao> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoOrgao();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoOrgao t = new TipoOrgao();
			t = tipos.get(i);
			if(t.getIdTipoOrgao() > id) {
				id = t.getIdTipoOrgao();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		TipoOrgaoDAO tp = new TipoOrgaoDAO ();
		List<TipoOrgao> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoOrgao();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoOrgao t = new TipoOrgao();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdTipoOrgao();
			}
		}
		return id;
	}	
}
