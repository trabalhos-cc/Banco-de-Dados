package ddi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class DdiDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Ddi> buscarDdi()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Ddi> Ddi = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from ddi;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Ddi b = new Ddi();
				b.setIdDdi(rs.getInt("idddi"));
				b.setNro(rs.getInt("nro"));
				Ddi.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ddi;
		}
		
		//insert
		public void inserirDdi (int nro) throws Exception{
			
			if(DdiDAO.existData(nro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into ddi (idddi, nro)");
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
			if(!DdiDAO.existData(nro)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from ddi where idddi = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nro));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int nro)  throws Exception{
			DdiDAO tp = new DdiDAO ();
			List<Ddi> tipos = new ArrayList<>(); 
			tipos = tp.buscarDdi();
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddi t = new Ddi();
				t = tipos.get(i);
				if(t.getNro() == nro) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			DdiDAO tp = new DdiDAO();
			List<Ddi> tipos = new ArrayList<>();
			tipos = tp.buscarDdi();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddi t = new Ddi();
				t = tipos.get(i);
				if(t.getIdDdi() > id) {
					id = t.getIdDdi();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(int nro) throws Exception{
			DdiDAO tp = new DdiDAO();
			List<Ddi> tipos = new ArrayList<>();
			tipos = tp.buscarDdi();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Ddi t = new Ddi();
				t = tipos.get(i);
				if(t.getNro() == nro) {
					id = t.getIdDdi();
				}
			}
			return id;
		}
}
