package titularDependente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TitularDependenteDAO {


	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TitularDependente> buscarTitularDependente()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TitularDependente> TitularDependente = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from titularDependente;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TitularDependente b = new TitularDependente();
				b.setIdDependente(rs.getInt("iddependente"));
				b.setIdContribuinte(rs.getInt("idcontribuinte"));
				TitularDependente.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return TitularDependente;
		}
		
		//insert
		public void inserirTitularDependente (int idDependente, int idContribuinte) throws Exception{
			
			if(TitularDependenteDAO.existData(idDependente, idContribuinte)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into TitularDependente (iddependente, idcontribuinte)");
			sql.append("values (?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, idDependente);
			comando.setInt(2, idContribuinte);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeTitularDependente (int idDependente, int idContribuinte) throws Exception{
			if(!TitularDependenteDAO.existData(idDependente, idContribuinte)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from titularDependente where idtitularDependente = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, idDependente);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int idD, int idC)  throws Exception{
			TitularDependenteDAO tp = new TitularDependenteDAO ();
			List<TitularDependente> tipos = new ArrayList<>(); 
			tipos = tp.buscarTitularDependente();
			
			for(int i = 0; i < tipos.size(); i++) {
				TitularDependente t = new TitularDependente();
				t = tipos.get(i);
				if((t.getIdDependente() == idD) && (t.getIdContribuinte() == idC)) {
					return true;
				}
			}
			return false;
		}
		
		
		
}
