package tipoOrgao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class TipoOrgaoDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
	public List <TipoOrgao> buscarTipoOrgao()throws Exception{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = conexao.abrir();
			
		List<TipoOrgao> tipoOrgao = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement("select * from bairro;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoOrgao to = new TipoOrgao();
				to.setIdTipoOrgao(rs.getInt("idTipoOrgao"));
				to.setNome(rs.getString("nome"));
				tipoOrgao.add(to);
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
		conn.close();
		return tipoOrgao;
		}
}
