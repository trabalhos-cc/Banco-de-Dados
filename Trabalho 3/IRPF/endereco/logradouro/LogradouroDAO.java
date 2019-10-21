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

	protected static int id = 0;
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
				log.setIdLogradouro(rs.getInt("idlogradouro"));
				log.setNome(rs.getString("nome"));
				log.setIdTipoLogradouro(rs.getInt("idtipoLogradouro"));
				logradouros.add(log);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return logradouros;
	}
	
	//insert
	public void inserirLogradouro (String nome, String tipo) throws Exception{
		
		if(LogradouroDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into logradouro (idlogradouro, nome, idtipoLogradouro)");
		sql.append("select ?, ? , idtipoLogradouro from tipoLogradouro where nome = ?");
			
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, maxId());
		comando.setString(2, nome);
		comando.setString(3, tipo);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public void removeLogradouro (String nome) throws Exception{
		if(!LogradouroDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from logradouro where idlogradouro = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		LogradouroDAO tp = new LogradouroDAO ();
		List<Logradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarLogradouro();
		
		for(int i = 0; i < tipos.size(); i++) {
			Logradouro t = new Logradouro();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		LogradouroDAO tp = new LogradouroDAO ();
		List<Logradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarLogradouro();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			Logradouro t = new Logradouro();
			t = tipos.get(i);
			if(t.getIdLogradouro() > id) {
				id = t.getIdLogradouro();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		LogradouroDAO tp = new LogradouroDAO ();
		List<Logradouro> tipos = new ArrayList<>(); 
		tipos = tp.buscarLogradouro();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			Logradouro t = new Logradouro();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdLogradouro();
			}
		}
		return id;
	}
	
}
