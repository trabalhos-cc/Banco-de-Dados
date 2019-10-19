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
}
