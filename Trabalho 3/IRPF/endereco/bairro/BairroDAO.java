package bairro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class BairroDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Bairro> buscarBairro()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Bairro> bairros = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from bairro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Bairro b = new Bairro();
				b.setIdBairro(rs.getInt("idBairro"));
				b.setNomeBairro(rs.getString("nome"));
				bairros.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return bairros;
		}
		
		//insert
		public void inserirBairro (String nomeBairro) throws Exception{
			
			if(BairroDAO.existData(nomeBairro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into bairro (idbairro, nome)");
			sql.append("values (?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, nomeBairro);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeBairro (String nomeBairro) throws Exception{
			if(!BairroDAO.existData(nomeBairro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from bairro where idbairro = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nomeBairro));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			BairroDAO tp = new BairroDAO ();
			List<Bairro> tipos = new ArrayList<>(); 
			tipos = tp.buscarBairro();
			
			for(int i = 0; i < tipos.size(); i++) {
				Bairro t = new Bairro();
				t = tipos.get(i);
				if(t.getNomeBairro().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			BairroDAO tp = new BairroDAO();
			List<Bairro> tipos = new ArrayList<>();
			tipos = tp.buscarBairro();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Bairro t = new Bairro();
				t = tipos.get(i);
				if(t.getIdBairro() > id) {
					id = t.getIdBairro();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			BairroDAO tp = new BairroDAO();
			List<Bairro> tipos = new ArrayList<>();
			tipos = tp.buscarBairro();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Bairro t = new Bairro();
				t = tipos.get(i);
				if(t.getNomeBairro().equals(nome)) {
					id = t.getIdBairro();
				}
			}
			return id;
		}
}
