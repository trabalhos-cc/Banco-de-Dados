package dependentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class DependentesDAO {

	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Dependentes> buscarDependentes()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<Dependentes> Dependentes = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from dependentes;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Dependentes b = new Dependentes();
				b.setIdDependentes(rs.getInt("idDependentes"));
				b.setCPF(rs.getInt("cpf"));
				b.setDt(rs.getString("dtNascimento"));
				b.setNome(rs.getString("nome"));
				b.setValor(rs.getFloat("valor"));
				b.setItem(rs.getInt("item"));
				b.setIdTipodependente(rs.getInt("idtipoDependente"));

				Dependentes.add(b);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Dependentes;
		}
		
		//insert
		public void inserirDependentes (int cpf, String dt, String nome, float valor, int item, String dependente) throws Exception{
			
			if(DependentesDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into dependentes (idDependentes, cpf, nome, valor, item, idtipoDependente)");
			sql.append("select ?,?,?,?,?, (select idtipoDependente from tipodependente where nome = ? );");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setInt(2, cpf);
			comando.setString(3, dt);
			comando.setString(4, nome);
			comando.setFloat(5, valor);
			comando.setInt(6, item);
			comando.setString(7, dependente);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeDependentes (String nome) throws Exception{
			if(!DependentesDAO.existData(nome)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from dependentes where iddependentes = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(nome));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		public static boolean existData (String nome)  throws Exception{
			DependentesDAO tp = new DependentesDAO ();
			List<Dependentes> tipos = new ArrayList<>(); 
			tipos = tp.buscarDependentes();
			
			for(int i = 0; i < tipos.size(); i++) {
				Dependentes t = new Dependentes();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			DependentesDAO tp = new DependentesDAO();
			List<Dependentes> tipos = new ArrayList<>();
			tipos = tp.buscarDependentes();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				Dependentes t = new Dependentes();
				t = tipos.get(i);
				if(t.getIdDependentes() > id) {
					id = t.getIdDependentes();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(String nome) throws Exception{
			DependentesDAO tp = new DependentesDAO();
			List<Dependentes> tipos = new ArrayList<>();
			tipos = tp.buscarDependentes();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				Dependentes t = new Dependentes();
				t = tipos.get(i);
				if(t.getNome().equals(nome)) {
					id = t.getIdDependentes();
				}
			}
			return id;
		}
}
