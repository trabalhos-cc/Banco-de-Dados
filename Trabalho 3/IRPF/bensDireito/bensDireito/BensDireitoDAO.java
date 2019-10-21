package bensDireito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class BensDireitoDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <BensDireito> buscarBensDireito()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<BensDireito> BensDireito = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from bensDireito;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BensDireito b = new BensDireito();
				b.setIdBensDireito(rs.getInt("idbensDireito"));
				b.setDescriminacao(rs.getString("discriminacao"));
				b.setSituacaoFisrt(rs.getFloat("situacaoFirst"));
				b.setSituacaoFisrt(rs.getFloat("situacaoLast"));
				b.setIdBensDireito(rs.getInt("item"));
				b.setNome(rs.getString("nome"));
				b.setDocumento(rs.getInt("documento"));
				b.setIdPais(rs.getInt("idpais"));
				BensDireito.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return BensDireito;
		}
		
		//insert
		public void inserirBensDireito (String disc, float sitFirst, float sitLast, int item, String nome, int doc, String pais) throws Exception{
			
			if(BensDireitoDAO.existData(doc)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into bensDireito (idbensDireito, discriminacao, situacaoFirst, situacaoLast, item, nome, documento, pais_idpais)");
			sql.append("select ?,?, ?, ?, ?, ?, ?, (select idpais from pais where nome = ?) ;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, disc);
			comando.setFloat(3,  sitFirst);
			comando.setFloat(4,  sitLast);
			comando.setInt(5, item);
			comando.setString(6, nome);
			comando.setInt(7, doc);
			comando.setString(8, pais);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeBensDireito (int id) throws Exception{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from bensDireito where idbensDireito = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, id);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int doc)  throws Exception{
			BensDireitoDAO tp = new BensDireitoDAO ();
			List<BensDireito> tipos = new ArrayList<>(); 
			tipos = tp.buscarBensDireito();
			
			for(int i = 0; i < tipos.size(); i++) {
				BensDireito t = new BensDireito();
				t = tipos.get(i);
				if(t.getDocumento() == doc) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			BensDireitoDAO tp = new BensDireitoDAO();
			List<BensDireito> tipos = new ArrayList<>();
			tipos = tp.buscarBensDireito();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				BensDireito t = new BensDireito();
				t = tipos.get(i);
				if(t.getIdBensDireito() > id) {
					id = t.getIdBensDireito();
				}
			}
			return id + 1;
		}
}
