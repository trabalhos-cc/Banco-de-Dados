package enderecoExterior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class EnderecoExteriorDAO {
	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
		public List <EnderecoExterior> buscarEnderecoExterior()throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
				
			List<EnderecoExterior> enderecoExterior = new ArrayList<>();
			
			try {
				stmt = conn.prepareStatement("select * from enderecoExterior;");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					EnderecoExterior ee = new EnderecoExterior();
					ee.setIdEnderecoExterior(rs.getInt("idEnderecoExterior"));
					ee.setCPFProcurador(rs.getInt("CPFProcurador"));
					ee.setCodigoPostal(rs.getInt("codigoPostal"));
					ee.setIdEmbaixada(rs.getInt("idEmbaixada"));
					enderecoExterior.add(ee);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return enderecoExterior;
			}
		
	//insert
		public void inserirEnderecoExterior (int CPF, int codPostal, String Cidade) throws Exception{
			
			if(EnderecoExteriorDAO.existData(codPostal)) return;
			StringBuilder sql = new StringBuilder();
			
			System.out.println(CPF);
			System.out.println(codPostal);
			System.out.println(Cidade);
			sql.append("insert into enderecoExterior (idenderecoExterior, CPFProcurador, codigoPostal, idembaixada) ");
			sql.append("select ?, ?, ?, (select idembaixada from embaixada where idcidade = (select idcidade from cidade where nome = ?)); ");
				
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, maxId());
			comando.setInt(2, CPF);
			comando.setInt(3, codPostal);
			comando.setString(4, Cidade);
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
		
		/*delete*/
		public void removeEnderecoExterior(int codPostal) throws Exception{
			if(!EnderecoExteriorDAO.existData(codPostal)) return;
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from enderecoExterior where idenderecoExterior = ?;");
			
			Connection conn = conexao.abrir();
			st = conn.createStatement();
			PreparedStatement comando = conn.prepareStatement(sql.toString());
			comando.setInt(1, buscaId(codPostal));
			comando.executeUpdate();
			
			comando.close();
			conn.close();
		}
	
		public static boolean existData (int cod)  throws Exception{
			EnderecoExteriorDAO tp = new EnderecoExteriorDAO ();
			List<EnderecoExterior> tipos = new ArrayList<>(); 
			tipos = tp.buscarEnderecoExterior();
			
			for(int i = 0; i < tipos.size(); i++) {
				EnderecoExterior t = new EnderecoExterior();
				t = tipos.get(i);
				if(t.getCodigoPostal() == cod) {
					return true;
				}
			}
			return false;
		}
		
		public static int maxId () throws Exception {
			
			EnderecoExteriorDAO tp = new EnderecoExteriorDAO ();
			List<EnderecoExterior> tipos = new ArrayList<>(); 
			tipos = tp.buscarEnderecoExterior();
			int id = 0;
			
			for(int i = 0; i < tipos.size(); i++) {
				EnderecoExterior t = new EnderecoExterior();
				t = tipos.get(i);
				if(t.getIdEnderecoExterior() > id) {
					id = t.getIdEnderecoExterior();
				}
			}
			return id + 1;
		}
		
		public static int buscaId(int cod) throws Exception{
			EnderecoExteriorDAO tp = new EnderecoExteriorDAO ();
			List<EnderecoExterior> tipos = new ArrayList<>(); 
			tipos = tp.buscarEnderecoExterior();
			int id = -1;
			
			for(int i = 0; i < tipos.size(); i++) {
				EnderecoExterior t = new EnderecoExterior();
				t = tipos.get(i);
				if(t.getCodigoPostal() == cod) {
					id = t.getIdEnderecoExterior();
				}
			}
			return id;
		}
}
