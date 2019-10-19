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
					te.setIdTipoEndereco(rs.getInt("idTipoEndereco"));
					te.setTipo(rs.getString("tipo"));
					tipoEndereco.add(te);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return tipoEndereco;
			}
}
