package endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class enderecoDAO {
	
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <Endereco> buscarEndereco()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
		
		List<Endereco> enderecos = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from endereço;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCEP(rs.getString("CEP"));
				end.setIdCidade(rs.getInt("idCidade"));
				end.setIdbairro(rs.getInt("idBairro"));
				end.setIdLogradouro(rs.getInt("idLogradouro"));
				enderecos.add(end);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return enderecos;
	}
	
	//insert
	public void inserirEndereco (int idEndereco, String CEP, int idCidade,
			int idBairro, int idLogradouro) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into endereço (idEndereço, CEP, idCidade, idBairro, idLogradouro");
		sql.append("values (?,?,?,?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, idEndereco);
		comando.setString(2, CEP);
		comando.setInt(3, idCidade);
		comando.setInt(4, idBairro);
		comando.setInt(5, idLogradouro);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	

}
