package tipoEndereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoEnderecoDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	
	//search
	public List <TipoEndereco> buscarTipoEndereco()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TipoEndereco> tipoEndereco = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from tipoEndereco;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoEndereco te = new TipoEndereco();
				te.setIdTipoEndereco(rs.getInt("idtipoEndereco"));
				te.setTipo(rs.getString("tipo"));
				tipoEndereco.add(te);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return tipoEndereco;
		}
	
	//insert
	public void inserirTipoEndereco (String nome) throws Exception{
		
		if(TipoEnderecoDAO.existData(nome)) return;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into tipoEndereco (idtipoEndereco, tipo)");
		sql.append("values (?,?);");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, maxId());
		comando.setString(2, nome);
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	/*delete*/
	public void removeTipoEndereco (String nome) throws Exception{
		if(!TipoEnderecoDAO.existData(nome)) return;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from tipoendereco where idtipoEndereco = ?;");
		
		Connection conn = conexao.abrir();
		st = conn.createStatement();
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, buscaId(nome));
		comando.executeUpdate();
		
		comando.close();
		conn.close();
	}
	
	public static boolean existData (String nome)  throws Exception{
		TipoEnderecoDAO tp = new TipoEnderecoDAO ();
		List<TipoEndereco> tipos = new ArrayList<>(); 
		tipos = tp.buscarTipoEndereco();
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoEndereco t = new TipoEndereco();
			t = tipos.get(i);
			if(t.getTipo().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxId () throws Exception {
		
		TipoEnderecoDAO tp = new TipoEnderecoDAO();
		List<TipoEndereco> tipos = new ArrayList<>();
		tipos = tp.buscarTipoEndereco();
		int id = 0;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoEndereco t = new TipoEndereco();
			t = tipos.get(i);
			if(t.getIdTipoEndereco() > id) {
				id = t.getIdTipoEndereco();
			}
		}
		return id + 1;
	}
	
	public static int buscaId(String nome) throws Exception{
		TipoEnderecoDAO tp = new TipoEnderecoDAO();
		List<TipoEndereco> tipos = new ArrayList<>();
		tipos = tp.buscarTipoEndereco();
		int id = -1;
		
		for(int i = 0; i < tipos.size(); i++) {
			TipoEndereco t = new TipoEndereco();
			t = tipos.get(i);
			if(t.getTipo().equals(nome)) {
				id = t.getIdTipoEndereco();
			}
		}
		return id;
	}
}
