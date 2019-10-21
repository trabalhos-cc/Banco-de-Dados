package cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class CidadeDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
		public List <Cidade> buscarCidades()throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
			
			List<Cidade> cidades = new ArrayList<>();
			
			try {
				stmt = conn.prepareStatement("select * from cidade;");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Cidade c = new Cidade();
					c.setIdCidade(rs.getInt("idcidade"));
					c.setNomeCidade(rs.getString("nome"));
					c.setIdUF(rs.getInt("iduf"));
					cidades.add(c);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return cidades;
		}
		
		public Cidade buscarCidades(int id)throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
			
			Cidade c = new Cidade();
			
			try {
				stmt = conn.prepareStatement("select * from cidade where idcidade = ? ;");
				
				
				stmt = conn.prepareStatement(stmt.toString());
				stmt.setInt(1, id);
				//stmt.executeUpdate();
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					c.setIdCidade(rs.getInt("idcidade"));
					c.setNomeCidade(rs.getString("nome"));
					c.setIdUF(rs.getInt("iduf"));
					
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return c;
		}

		//insert
		public void inserirCidades (String nomeCidade, String idUf)throws Exception{
			
			if(CidadeDAO.existData(nomeCidade)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into cidade (idcidade, nome, iduf)");
			sql.append("select ?, ? , iduf from uf where nome = ?");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, nomeCidade);
			comando.setString(3, idUf);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeCidade(String nome) throws Exception{
			if(!CidadeDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from cidade where idcidade = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nome));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			CidadeDAO tp = new CidadeDAO ();
			List<Cidade> tipos = new ArrayList<>(); 
			tipos = tp.buscarCidades();
			
			for(int i = 0; i < tipos.size(); i++) {
				Cidade t = new Cidade();
				t = tipos.get(i);
				if(t.getNomeCidade().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			CidadeDAO tp = new CidadeDAO ();
			List<Cidade> tipos = new ArrayList<>(); 
			tipos = tp.buscarCidades();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Cidade t = new Cidade();
				t = tipos.get(i);
				if(t.getIdCidade() > id) {
					id = t.getIdCidade();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			CidadeDAO tp = new CidadeDAO ();
			List<Cidade> tipos = new ArrayList<>(); 
			tipos = tp.buscarCidades();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Cidade t = new Cidade();
				t = tipos.get(i);
				if(t.getNomeCidade().equals(nome)) {
					id = t.getIdCidade();
				}
			}
			return id;
		}
}
