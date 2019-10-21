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

	protected static int id = 0;
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
			stmt = conn.prepareStatement("select * from uf;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Uf uf = new Uf();
				uf.setIdUF(rs.getInt("iduf"));
				uf.setNome(rs.getString("nome"));
				uf.setIdPais(rs.getInt("idpais"));
				Ufs.add(uf);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ufs;
	}
	
	//insert
	public void inserirUf (String nome, String idPais) throws Exception{
		
		if(UfDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into uf (iduf, nome, idpais)");
		sql.append("select ?, ? , idpais from pais where nome = ?");
			
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, maxId());
		comando.setString(2, nome);
		comando.setString(3, idPais);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public void removeUf(String nome) throws Exception{
		if(!UfDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from uf where iduf = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		UfDAO tp = new UfDAO ();
		List<Uf> tipos = new ArrayList<>(); 
		tipos = tp.buscarUf();
		
		for(int i = 0; i < tipos.size(); i++) {
			Uf t = new Uf();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		UfDAO tp = new UfDAO ();
		List<Uf> tipos = new ArrayList<>(); 
		tipos = tp.buscarUf();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			Uf t = new Uf();
			t = tipos.get(i);
			if(t.getIdUF() > id) {
				id = t.getIdUF();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		UfDAO tp = new UfDAO ();
		List<Uf> tipos = new ArrayList<>(); 
		tipos = tp.buscarUf();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			Uf t = new Uf();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				id = t.getIdUF();
			}
		}
		return id;
	}
}
