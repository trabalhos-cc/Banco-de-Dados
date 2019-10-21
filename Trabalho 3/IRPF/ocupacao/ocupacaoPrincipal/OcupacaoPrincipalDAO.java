package ocupacaoPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class OcupacaoPrincipalDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <OcupacaoPrincipal> buscarOcupacaoPrincipal()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<OcupacaoPrincipal> OcupacaoPrincipal = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from ocupacaoPrincipal;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				OcupacaoPrincipal b = new OcupacaoPrincipal();
				b.setIdOcupacaoPrincipal(rs.getInt("idocupacaoPrincipal"));
				b.setNome(rs.getString("nome"));
				OcupacaoPrincipal.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return OcupacaoPrincipal;
		}
		
		//insert
		public void inserirOcupacaoPrincipal (String nome) throws Exception{
			
			if(OcupacaoPrincipalDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into ocupacaoPrincipal (idocupacaoPrincipal, nome)");
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
		public void removeOcupacaoPrincipal (String nome) throws Exception{
			if(!OcupacaoPrincipalDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from ocupacaoPrincipal where idocupacaoPrincipal = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nome));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			OcupacaoPrincipalDAO tp = new OcupacaoPrincipalDAO ();
			List<OcupacaoPrincipal> tipos = new ArrayList<>(); 
			tipos = tp.buscarOcupacaoPrincipal();
			
			for(int i = 0; i < tipos.size(); i++) {
				OcupacaoPrincipal t = new OcupacaoPrincipal();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			OcupacaoPrincipalDAO tp = new OcupacaoPrincipalDAO();
			List<OcupacaoPrincipal> tipos = new ArrayList<>();
			tipos = tp.buscarOcupacaoPrincipal();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				OcupacaoPrincipal t = new OcupacaoPrincipal();
				t = tipos.get(i);
				if(t.getIdOcupacaoPrincipal() > id) {
					id = t.getIdOcupacaoPrincipal();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			OcupacaoPrincipalDAO tp = new OcupacaoPrincipalDAO();
			List<OcupacaoPrincipal> tipos = new ArrayList<>();
			tipos = tp.buscarOcupacaoPrincipal();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				OcupacaoPrincipal t = new OcupacaoPrincipal();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					id = t.getIdOcupacaoPrincipal();
				}
			}
			return id;
		}
}
