package contribuinte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class ContribuinteDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Contribuinte> buscarContribuinte()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Contribuinte> Contribuinte = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from contribuinte;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Contribuinte b = new Contribuinte();
				b.setIdContribuinte(rs.getInt("idContribuinte"));
				b.setMudancaEnd(rs.getBoolean("mudancaEndereco"));
				b.setConjugue(rs.getBoolean("conjuge"));
				b.setData(rs.getString("dtNascimento"));
				b.setTituloEleitoral(rs.getInt("tituloEleitoral"));
				b.setDeficiente(rs.getBoolean("deficiente"));
				b.setNumero(rs.getInt("numemro"));
				b.setComplemento(rs.getString("complemento"));
				b.setIdOcupacao(rs.getInt("idocupacao"));
				b.setIdEndereco(rs.getInt("idlocalidade"));

				
				Contribuinte.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Contribuinte;
		}
		
		//insert
		public void inserirContribuinte (boolean mud, boolean conj, String dt, int titulo, boolean def, int num, String comp, int idOcu, int idEnd) throws Exception{
			
			if(ContribuinteDAO.existData(titulo)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into bairro (idcontribuinte, mudancaEndereco, conjuge, dtNascimento, tituloEleitoral, deficiencia, numero, complemento, idocupacao, idlocalidade)");
			sql.append("values (?,?,?, ?, ?, ?, ?, ?, ?, ?)");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setBoolean(2, mud);
			comando.setBoolean(3, conj);
			comando.setString(4,  dt);
			comando.setInt(5, titulo);
			comando.setBoolean(6, def);
			comando.setInt(7, num);
			comando.setString(8, comp);
			comando.setInt(9, idOcu);
			comando.setInt(10, idEnd);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeContribuinte (int titulo) throws Exception{
			if(!ContribuinteDAO.existData(titulo)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from contribuinte where idcontribuinte = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(titulo));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (int titulo)  throws Exception{
			ContribuinteDAO tp = new ContribuinteDAO ();
			List<Contribuinte> tipos = new ArrayList<>(); 
			tipos = tp.buscarContribuinte();
			
			for(int i = 0; i < tipos.size(); i++) {
				Contribuinte t = new Contribuinte();
				t = tipos.get(i);
				if(t.getTituloEleitoral() == titulo) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			ContribuinteDAO tp = new ContribuinteDAO();
			List<Contribuinte> tipos = new ArrayList<>();
			tipos = tp.buscarContribuinte();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Contribuinte t = new Contribuinte();
				t = tipos.get(i);
				if(t.getIdContribuinte() > id) {
					id = t.getIdContribuinte();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(int tit) throws Exception{
			ContribuinteDAO tp = new ContribuinteDAO();
			List<Contribuinte> tipos = new ArrayList<>();
			tipos = tp.buscarContribuinte();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Contribuinte t = new Contribuinte();
				t = tipos.get(i);
				if(t.getTituloEleitoral() == tit) {
					id = t.getIdContribuinte();
				}
			}
			return id;
		}
}
