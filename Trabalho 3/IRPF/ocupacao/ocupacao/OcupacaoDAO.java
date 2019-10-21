package ocupacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;
import ocupacaoPrincipal.OcupacaoPrincipal;
import ocupacaoPrincipal.OcupacaoPrincipalDAO;

public class OcupacaoDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Ocupacao> buscarOcupacao()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Ocupacao> Ocupacao = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from ocupacao;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Ocupacao b = new Ocupacao();
				b.setIdOcupacao(rs.getInt("idocupacao"));
				b.setIdNatureza(rs.getInt("idnatureza"));
				b.setIdOcupacaoPrincipal(rs.getInt("idocupacaoPrincipal"));
				Ocupacao.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ocupacao;
		}
		
		//insert
		public void inserirOcupacao (int idnatureza, int idOcupP) throws Exception{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into ocupacao (idocupacao, idnatureza, idocupacaoPrincipal)");
			sql.append("values (?,?,?);");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setInt(2, idnatureza);
			comando.setInt(3, idOcupP);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeOcupacao (int id) throws Exception{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from ocupacao where idocupacao = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, id);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		
		public static int maxId () throws Exception {
			
			OcupacaoDAO tp = new OcupacaoDAO();
			List<Ocupacao> tipos = new ArrayList<>();
			tipos = tp.buscarOcupacao();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Ocupacao t = new Ocupacao();
				t = tipos.get(i);
				if(t.getIdOcupacao() > id) {
					id = t.getIdOcupacao();
				}
			}
			System.out.println(id);
			return id + 1;
		}
		
	
}
