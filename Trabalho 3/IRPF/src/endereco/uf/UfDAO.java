package endereco.uf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class UfDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Uf> buscarUf()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<Uf> Ufs = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from Uf;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Uf uf = new Uf();
				uf.setIdUF(rs.getInt("idUf"));
				uf.setNome(rs.getString("nome"));
				uf.setIdPais(rs.getInt("idPais"));
				Ufs.add(uf);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return Ufs;
	}
	
	//insert
	public void inserirUf (String nome, String idPais) throws Exception{
		
		if(UfDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into UF (idUF, nome, idPaís)");
		sql.append("select ?, ? , idPaís from país where nome = ?");
			
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, id);
		comando.setString(2, nome);
		comando.setString(3, idPais);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public static boolean existData (String nome)  throws Exception{
		UfDAO tp = new UfDAO ();
		List<Uf> tipos = new ArrayList<>(); 
		
		tipos = tp.buscarUf();
		UfDAO.id = tipos.size();
		System.out.println(id);
		for(int i = 0; i < tipos.size(); i++) {
			Uf t = new Uf();
			t = tipos.get(i);
			if(t.getNome().equals(nome)) {
				return true;
			}
		}
		
		return false;
	}
}
