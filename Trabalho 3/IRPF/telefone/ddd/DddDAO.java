package ddd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class DddDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Ddd> buscarDdd()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Ddd> Ddd = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from ddd;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Ddd b = new Ddd();
				b.setIdDdd(rs.getInt("idddd"));
				b.setNro(rs.getInt("nro"));
				Ddd.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ddd;
		}
		
		//insert
		public void inserirDdd (int nro) throws Exception{
			
			if(DddDAO.existData(nro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into ddd (idddd, nro)");
			sql.append("values (?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setInt(2, nro);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeDdd (int nro) throws Exception{
			if(!DddDAO.existData(nro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from ddd where idddd = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nro));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int nro)  throws Exception{
			DddDAO tp = new DddDAO ();
			List<Ddd> tipos = new ArrayList<>(); 
			tipos = tp.buscarDdd();
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddd t = new Ddd();
				t = tipos.get(i);
				if(t.getNro() == nro) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			DddDAO tp = new DddDAO();
			List<Ddd> tipos = new ArrayList<>();
			tipos = tp.buscarDdd();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddd t = new Ddd();
				t = tipos.get(i);
				if(t.getIdDdd() > id) {
					id = t.getIdDdd();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(int nro) throws Exception{
			DddDAO tp = new DddDAO();
			List<Ddd> tipos = new ArrayList<>();
			tipos = tp.buscarDdd();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddd t = new Ddd();
				t = tipos.get(i);
				if(t.getNro() == nro) {
					id = t.getIdDdd();
				}
			}
			return id;
		}
}
