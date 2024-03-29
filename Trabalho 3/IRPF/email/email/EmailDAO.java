package email;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class EmailDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Email> buscarEmail()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Email> Email = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from email;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Email b = new Email();
				b.setIdEmail(rs.getInt("idemailContribuinte"));
				b.setEmail(rs.getString("email"));
				b.setIdContribuinte(rs.getInt("idcontribuinte"));
				Email.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Email;
		}
		
		//insert
		public void inserirEmail (String nome, int id) throws Exception{
			
			if(EmailDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into email (idemail, email, idcontribuinte)");
			sql.append("values (?,?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setString(2, nome);
			comando.setInt(3, id);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeEmail (String nomeEmail) throws Exception{
			if(!EmailDAO.existData(nomeEmail)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from email where idemail = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nomeEmail));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			EmailDAO tp = new EmailDAO ();
			List<Email> tipos = new ArrayList<>(); 
			tipos = tp.buscarEmail();
			
			for(int i = 0; i < tipos.size(); i++) {
				Email t = new Email();
				t = tipos.get(i);
				if(t.getEmail().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			EmailDAO tp = new EmailDAO();
			List<Email> tipos = new ArrayList<>();
			tipos = tp.buscarEmail();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Email t = new Email();
				t = tipos.get(i);
				if(t.getIdEmail() > id) {
					id = t.getIdEmail();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			EmailDAO tp = new EmailDAO();
			List<Email> tipos = new ArrayList<>();
			tipos = tp.buscarEmail();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Email t = new Email();
				t = tipos.get(i);
				if(t.getEmail().equals(nome)) {
					id = t.getIdEmail();
				}
			}
			return id;
		}
}
