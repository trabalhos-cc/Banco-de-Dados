package foneContribuinte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class FoneContribuinteDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <FoneContribuinte> buscarFoneContribuinte()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<FoneContribuinte> FoneContribuinte = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from foneContribuinte;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FoneContribuinte b = new FoneContribuinte();
				b.setIdFoneContribuinte(rs.getInt("idfoneContribuinte"));
				b.setIdContribuinte(rs.getInt("contribuinte"));
				b.setIdDdd(rs.getInt("ddd"));
				b.setIdDdi(rs.getInt("ddi"));
				b.setIdTipoFone(rs.getInt("tipoFone"));
				b.setNumero(rs.getInt("numero"));

				
				FoneContribuinte.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return FoneContribuinte;
		}
		
		//insert
		public void inserirFoneContribuinte (int numero, int contribuinte, int ddd, int ddi, String tipo) throws Exception{
			
			if(FoneContribuinteDAO.existData(numero)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into bairro (idfoneContribuinte, idcontribuinte, idddd, idtipoFone, idddi, numero)");
			sql.append("select ?,?,(select idddd from ddd where nro = ?), (select idtipoFone from tipoFone where tipo = ?), (select idddi from ddi where nro = ?) , ?)");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setInt(2, contribuinte);
			comando.setInt(3, ddd);
			comando.setString(4, tipo);
			comando.setInt(5, ddi);
			comando.setInt(6, numero);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeFoneContribuinte (int numero) throws Exception{
			if(!FoneContribuinteDAO.existData(numero)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from foneContribuinte where idfoneContribuinte = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(numero));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int numero)  throws Exception{
			FoneContribuinteDAO tp = new FoneContribuinteDAO ();
			List<FoneContribuinte> tipos = new ArrayList<>(); 
			tipos = tp.buscarFoneContribuinte();
			
			for(int i = 0; i < tipos.size(); i++) {
				FoneContribuinte t = new FoneContribuinte();
				t = tipos.get(i);
				if(t.getNumero() == numero) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			FoneContribuinteDAO tp = new FoneContribuinteDAO();
			List<FoneContribuinte> tipos = new ArrayList<>();
			tipos = tp.buscarFoneContribuinte();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				FoneContribuinte t = new FoneContribuinte();
				t = tipos.get(i);
				if(t.getIdFoneContribuinte() > id) {
					id = t.getIdFoneContribuinte();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(int tit) throws Exception{
			FoneContribuinteDAO tp = new FoneContribuinteDAO();
			List<FoneContribuinte> tipos = new ArrayList<>();
			tipos = tp.buscarFoneContribuinte();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				FoneContribuinte t = new FoneContribuinte();
				t = tipos.get(i);
				if(t.getNumero() == tit) {
					id = t.getIdContribuinte();
				}
			}
			return id;
		}
}
