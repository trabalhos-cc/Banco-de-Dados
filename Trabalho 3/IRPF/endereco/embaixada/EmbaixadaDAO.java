package embaixada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.Conexao;

public class EmbaixadaDAO {

	protected static int id = 0;
	protected ResultSet resultado = null;
	protected Statement st = null;
	Conexao conexao = new Conexao();
	
	//search
		public List <Embaixada> buscarEmbaixada()throws Exception{
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection conn = conexao.abrir();
				
			List<Embaixada> embaixada = new ArrayList<>();
			
			try {
				stmt = conn.prepareStatement("select * from embaixada;");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Embaixada emb = new Embaixada();
					emb.setIdEmbaixada(rs.getInt("idEmbaixada"));
					emb.setIdCidade(rs.getInt("idCidade"));
					emb.setIdPais(rs.getInt("idPais"));
					emb.setIdTipoOrgao(rs.getInt("idTipoOrgao"));
					embaixada.add(emb);
				}
			}catch(SQLException e) {
				System.err.println(e);
			}
			conn.close();
			return embaixada;
			}
}
