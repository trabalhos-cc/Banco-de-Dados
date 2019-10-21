package endereco;

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
				end.setCEP(rs.getInt("CEP"));
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
	public void inserirEndereco (int CEP, String idCidade,
			String idBairro, String idLogradouro, String tipo, int idEE) throws Exception{
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into endereco (idendereco, idenderecoExterior,  CEP, idtipoEndereco, idcidade, idbairro, idlogradouro)");
		sql.append("select ?, ?, ?,");
		sql.append("(select idtipoEndereco from tipoEndereco where tipo = ?), ");
		sql.append("(select idcidade from cidade where nome = ?), ");
		sql.append("(select idbairro from bairro where nome = ?),");
		sql.append("(select idlogradouro from logradouro where nome = ?);");
				
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, id);
		
		if(isExterior(tipo)) {
			comando.setInt(2, idEE);
		}else {
			comando.setInt(2, 0);
		}
		
		comando.setInt(3, CEP);
		comando.setString(4, tipo);
		comando.setString(5, idCidade);
		comando.setString(6, idBairro);
		comando.setString(7, idLogradouro);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public void removeEndereco(int id) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from endereco where idendereco = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, id);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean isExterior (String tipo)  throws Exception{
		return (tipo.equals("Exterior"));	
	}
	
}
