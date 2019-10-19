package endereco.endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class EnderecoDAO {
	
	protected static int id = 0;
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
	public void inserirEndereco (String CEP, String idCidade,
			String idBairro, String idLogradouro) throws Exception{
		
		
		if(EnderecoDAO.existData(CEP)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into endereço (idEndereço, CEP, idCidade, idBairro, idLogradouro)");
		sql.append("select ?, ? , ");
		sql.append("(select idcidade from cidade where nomeCidade = ?), ");
		sql.append("(select idbairro from bairro where nome = ?),");
		sql.append("(select idlogradouro from logradouro where nome = ?)");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, id);
		comando.setString(2, CEP);
		comando.setString(3, idCidade);
		comando.setString(4, idBairro);
		comando.setString(5, idLogradouro);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	
	public static boolean existData (String CEP)  throws Exception{
		EnderecoDAO tp = new EnderecoDAO ();
		List<Endereco> tipos = new ArrayList<>(); 
		
		tipos = tp.buscarEndereco();
		EnderecoDAO.id = tipos.size();
		for(int i = 0; i < tipos.size(); i++) {
			Endereco t = new Endereco();
			t = tipos.get(i);
			if(t.getCEP().equals(CEP)) {
				return true;
			}
		}
		
		return false;
	}
}
