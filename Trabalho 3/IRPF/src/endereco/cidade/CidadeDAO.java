package endereco.cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class CidadeDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
		public List <Cidade> buscarCidades()throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
			
			List<Cidade> cidades = new ArrayList<>();
			
			try {
				stmt = conn.prepareStatement("select * from cidade;");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Cidade c = new Cidade();
					c.setIdCidade(rs.getInt("idCidade"));
					c.setNomeCidade(rs.getString("nomeCidade"));
					c.setIdUF(rs.getInt("idUF"));
					cidades.add(c);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return cidades;
		}

		//insert
		public void inserirCidades (String nomeCidade, String idUf)throws Exception{
			
			if(CidadeDAO.existData(nomeCidade)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into cidade (idCidade, nomeCidade, idUF)");
			sql.append("select ?, ? , idUF from UF where nome = ?");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, id);
			comando.setString(2, nomeCidade);
			comando.setString(3, idUf);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		
		public static boolean existData (String nome)  throws Exception{
			CidadeDAO tp = new CidadeDAO ();
			List<Cidade> tipos = new ArrayList<>(); 
			
			tipos = tp.buscarCidades();
			CidadeDAO.id = tipos.size();
			for(int i = 0; i < tipos.size(); i++) {
				Cidade t = new Cidade();
				t = tipos.get(i);
				if(t.getNomeCidade().equals(nome)) {
					return true;
				}
			}
			
			return false;
		}
}
