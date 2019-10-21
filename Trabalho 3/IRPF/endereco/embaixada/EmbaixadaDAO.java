package embaixada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cidade.Cidade;
import cidade.CidadeDAO;
import conexaoBD.Conexao;

public class EmbaixadaDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
		//search
		public List <Embaixada> buscarEmbaixada()throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
				
			List<Embaixada> embaixada = new ArrayList<>();
			
			try {
				stmt = conn.prepareStatement("select * from embaixada;");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Embaixada emb = new Embaixada();
					emb.setIdEmbaixada(rs.getInt("idembaixada"));
					emb.setIdCidade(rs.getInt("idcidade"));
					emb.setIdPais(rs.getInt("idpais"));
					emb.setIdTipoOrgao(rs.getInt("idtipoOrgao"));
					embaixada.add(emb);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return embaixada;
			}
		
		public List<Embaixada> buscaEmbaixadaPais(String idPais) throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
			
			List<Embaixada> embaixada = new ArrayList<>();
			try {
				stmt = conn.prepareStatement("select * from embaixada where idpais = (select idpais from pais where nome = ?);");

				stmt = conn.prepareStatement(stmt.toString());
				stmt.setString(1, idPais);
				//stmt.executeUpdate();
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Embaixada emb = new Embaixada();
					emb.setIdEmbaixada(rs.getInt("idembaixada"));
					emb.setIdCidade(rs.getInt("idcidade"));
					emb.setIdPais(rs.getInt("idpais"));
					emb.setIdTipoOrgao(rs.getInt("idtipoOrgao"));
					embaixada.add(emb);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return embaixada;
			
		}
		
		//insert
		public void inserirEmbaixada (String idTipoOrgao, String idCidade, String idPais) throws Exception{
			
			if(EmbaixadaDAO.existData(idCidade)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into embaixada (idembaixada, idtipoOrgao, idcidade, idpais)");
			sql.append("select ?, "
					+ "(select idtipoOrgao from tipoOrgao where nome = ?),"
					+ "(select idcidade from cidade where nome = ?), "
					+ "(select idpais from pais where nome = ?);");
				
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, idTipoOrgao);
			comando.setString(3, idCidade);
			comando.setString(4, idPais);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		//delete
		public void removeEmbaixada(String pais, String cidade) throws Exception{
			if(!EmbaixadaDAO.existData(cidade)) return;
			StringBuilder sql = new StringBuilder();
			
			try {
			sql.append("delete from embaixada where idembaixada = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			
			comando.setInt(1, buscaId(pais, cidade));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
			
			
		}
		
		public static boolean existData (String cidade)  throws Exception{
			EmbaixadaDAO tp = new EmbaixadaDAO ();
			List<Embaixada> tipos = new ArrayList<>(); 
			tipos = tp.buscarEmbaixada();
			Cidade c = new Cidade();
			CidadeDAO cd = new CidadeDAO();
			
			for(int i = 0; i < tipos.size(); i++) {
				Embaixada t = new Embaixada();
				t = tipos.get(i);
				c = cd.buscarCidades(t.getIdCidade());
				
				if(c.getNomeCidade().equals(cidade)) {
					return true;
				}
			}
			return false;
		} 
		public static int maxId () throws Exception {
			
			EmbaixadaDAO tp = new EmbaixadaDAO ();
			List<Embaixada> tipos = new ArrayList<>(); 
			tipos = tp.buscarEmbaixada();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Embaixada t = new Embaixada();
				t = tipos.get(i);
				if(t.getIdEmbaixada() > id) {
					id = t.getIdEmbaixada();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String pais, String cidade) throws Exception{
			EmbaixadaDAO tp = new EmbaixadaDAO ();
			List<Embaixada> tipos = new ArrayList<>(); 
			tipos = tp.buscaEmbaixadaPais(pais);
			Cidade c = new Cidade();
			CidadeDAO cd = new CidadeDAO();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Embaixada t = new Embaixada();
				
				
				t = tipos.get(i);
				
				c = cd.buscarCidades(t.getIdCidade());
				if(c.getNomeCidade().equals(cidade)) {
					id = t.getIdEmbaixada();
				}
			}
			System.out.println(id);
			return id;
		}
}
