package tipoFone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoFoneDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TipoFone> buscarTipoFone()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TipoFone> TipoFone = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from tipoFone;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoFone b = new TipoFone();
				b.setIdTipoFone(rs.getInt("idtipoFone"));
				b.setTipo(rs.getString("tipo"));
				TipoFone.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return TipoFone;
		}
		
		//insert
		public void inserirTipoFone(String tipo) throws Exception{
			
			if(TipoFoneDAO.existData(tipo)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into tipoFone (idtipoFone, tipo)");
			sql.append("values (?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, tipo);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeTipoFone(String tipo) throws Exception{
			if(!TipoFoneDAO.existData(tipo)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from tipoFone where idtipoFone = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(tipo));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String tipo)  throws Exception{
			TipoFoneDAO tp = new TipoFoneDAO ();
			List<TipoFone> tipos = new ArrayList<>(); 
			tipos = tp.buscarTipoFone();
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoFone t = new TipoFone();
				t = tipos.get(i);
				if(t.getTipo().equals(tipo)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			TipoFoneDAO tp = new TipoFoneDAO();
			List<TipoFone> tipos = new ArrayList<>();
			tipos = tp.buscarTipoFone();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoFone t = new TipoFone();
				t = tipos.get(i);
				if(t.getIdTipoFone() > id) {
					id = t.getIdTipoFone();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String tipo) throws Exception{
			TipoFoneDAO tp = new TipoFoneDAO();
			List<TipoFone> tipos = new ArrayList<>();
			tipos = tp.buscarTipoFone();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoFone t = new TipoFone();
				t = tipos.get(i);
				if(t.getTipo().equals(tipo)) {
					id = t.getIdTipoFone();
				}
			}
			return id;
		}
}
