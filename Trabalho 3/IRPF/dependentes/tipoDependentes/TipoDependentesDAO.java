package tipoDependentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoDependentesDAO {


	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TipoDependentes> buscarTipoDependentes()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TipoDependentes> TipoDependentes = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from tipoDependentes;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoDependentes b = new TipoDependentes();
				b.setIdTipodependentes(rs.getInt("idtipoDependentes"));
				b.setNome(rs.getString("nome"));

				TipoDependentes.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return TipoDependentes;
		}
		
		//insert
		public void inserirTipoDependentes (String nome) throws Exception{
			
			if(TipoDependentesDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into tipoDependentes (idtipoDependentes, nome)");
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
		public void removeTipoDependentes (String nome) throws Exception{
			if(!TipoDependentesDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from tipoDependentes where idtipoDependentes = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nome));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			TipoDependentesDAO tp = new TipoDependentesDAO ();
			List<TipoDependentes> tipos = new ArrayList<>(); 
			tipos = tp.buscarTipoDependentes();
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoDependentes t = new TipoDependentes();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			TipoDependentesDAO tp = new TipoDependentesDAO();
			List<TipoDependentes> tipos = new ArrayList<>();
			tipos = tp.buscarTipoDependentes();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoDependentes t = new TipoDependentes();
				t = tipos.get(i);
				if(t.getIdTipodependentes() > id) {
					id = t.getIdTipodependentes();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			TipoDependentesDAO tp = new TipoDependentesDAO();
			List<TipoDependentes> tipos = new ArrayList<>();
			tipos = tp.buscarTipoDependentes();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				TipoDependentes t = new TipoDependentes();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					id = t.getIdTipodependentes();
				}
			}
			return id;
		}
}
