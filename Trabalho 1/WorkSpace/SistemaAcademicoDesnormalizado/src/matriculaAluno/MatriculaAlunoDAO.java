package matriculaAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConexaoBD.Conexao;

public class MatriculaAlunoDAO {
	
	
	protected ResultSet resultado = null;
	protected Statement st = null;

	public List <MatriculaAluno> buscaCurso () throws Exception{
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = Conexao.abrir();
		
		List<MatriculaAluno> cursos = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement("select nomeCurso from matriculaaluno;");
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				MatriculaAluno ma = new MatriculaAluno();
				ma.setNomeCurso(rs.getString("NomeCurso"));
				cursos.add(ma);
			}
		}catch(SQLException e) {
			System.err.println(e);
			
		}
		return cursos;
	}

	

 	public void InserirCurso (String nomeCurso) throws Exception{
 
		
		//comandos sql
		StringBuilder sql = new StringBuilder();
		
		//Comando para selecionar todos os dados de todos os Alunos
		//select cpfAluno, nomeAluno, foneAluno, emailAluno from matriculaaluno;
		sql.append("insert into matriculaaluno (nomeCurso)");
		sql.append("value (?);");
		
		// Abre a conexão que criamos o retorno é armazenado na variavel conn 
		Connection conn = Conexao.abrir();
		
		st = conn.createStatement();
		
		// Mapeamento objeto relacional 
		// envia o comando sql para o banco de dados
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setString(1, nomeCurso);
		comando.executeUpdate();
				
		//fecha a conexao
		comando.close();
		conn.close();
				
	}
 	
 	public void InserirAluno (int CPF, String nome, String fone, String email) throws Exception{
 
		
		//comandos sql
		StringBuilder sql = new StringBuilder();
		
		//Comando para selecionar todos os dados de todos os Alunos
		//select cpfAluno, nomeAluno, foneAluno, emailAluno from matriculaaluno;
		sql.append("insert into matriculaaluno (CPFAluno, NomeAluno, FoneAluno, EmailAluno, NomeCurso)");
		sql.append("value (?,?,?,?,?);");
		
	
		// Abre a conexão que criamos o retorno é armazenado na variavel conn 
		Connection conn = Conexao.abrir();
		
		st = conn.createStatement();
		
		// Mapeamento objeto relacional 
		// envia o comando sql para o banco de dados
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setInt(1, CPF);
		comando.setString(2, nome);
		comando.setString(3, fone);
		comando.setString(4, email);
		comando.setString(5, "-");
		comando.executeUpdate();
				
		//fecha a conexao
		comando.close();
		conn.close();
				
	}
 	
 	public List <MatriculaAluno> buscaAlunos () throws Exception{
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = Conexao.abrir();
		
		List<MatriculaAluno> cursos = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement("select cpfAluno, nomeAluno, foneAluno, emailAluno from matriculaaluno;");
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				MatriculaAluno ma = new MatriculaAluno();
				ma.setCPFAluno(rs.getInt("cpfAluno"));
				ma.setNomeAluno(rs.getString("nomeAluno"));
				ma.setFoneAluno(rs.getString("foneAluno"));
				ma.setEmailAluno(rs.getString("emailAluno"));
				cursos.add(ma);
			}
		}catch(SQLException e) {
			System.err.println(e);
			
		}
		return cursos;
	}

	public void InserirDisciplina (String nomeDisciplina, String nomeProf, String emailProf) throws Exception{
		 
		
		//comandos sql
		StringBuilder sql = new StringBuilder();
		
		//Comando para selecionar todos os dados de todos os Alunos
		//select cpfAluno, nomeAluno, foneAluno, emailAluno from matriculaaluno;
		sql.append("insert into matriculaaluno (nomeDisciplina, NomeProfessor, emailProfessor)");
		sql.append("values (?,?,?);");
		
		// Abre a conexão que criamos o retorno é armazenado na variavel conn 
		Connection conn = Conexao.abrir();
		
		st = conn.createStatement();
		
		// Mapeamento objeto relacional 
		// envia o comando sql para o banco de dados
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setString(1, nomeDisciplina);
		comando.setString(2, nomeProf);
		comando.setString(3, emailProf);
		comando.executeUpdate();
				
		//fecha a conexao
		comando.close();
		conn.close();
				
	}
	
	public List <MatriculaAluno> buscaDisciplinas () throws Exception{
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = Conexao.abrir();
		
		List<MatriculaAluno> cursos = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement("select nomeDisciplina, nomeProfessor, emailProfessor from matriculaaluno;");
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				MatriculaAluno ma = new MatriculaAluno();
				ma.setNomeDisciplina(rs.getString("nomeDisciplina"));
				ma.setNomeProfessor(rs.getString("NomeProfessor"));
				ma.setEmailProfessor(rs.getString("EmailProfessor"));
				cursos.add(ma);
			}
		}catch(SQLException e) {
			System.err.println(e);
			
		}
		return cursos;
	}

	public void MatricularAluno (int cpf, String nomeCurso, String dataMatCurso, int anoLetivo, String DataMatAnoLetivo, String disciplina) throws Exception{
 
		
		//comandos sql
		StringBuilder sql = new StringBuilder();
		
		//Comando para selecionar todos os dados de todos os Alunos
		sql.append("update matriculaaluno set NomeCurso = ? , DataMatCurso = ?, AnoLetivo = ?, DatamatAnoLetivo = ?, disciplina = ? where cpfAluno = " + 
				cpf + "; ");
		
		// Abre a conexão que criamos o retorno é armazenado na variavel conn 
		Connection conn = Conexao.abrir();
		
		st = conn.createStatement();
		
		// Mapeamento objeto relacional 
		// envia o comando sql para o banco de dados
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setString(1, nomeCurso);
		comando.setString(2, dataMatCurso);
		comando.setInt(3, anoLetivo);
		comando.setString(4, DataMatAnoLetivo);
		comando.setString(5, disciplina);
		comando.executeUpdate();
				
		//fecha a conexao
		comando.close();
		conn.close();
				
	}

public List <MatriculaAluno> buscaAlunosNaoMatriculados () throws Exception{
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = Conexao.abrir();
		
		List<MatriculaAluno> cursos = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement("select cpfAluno, nomeAluno, foneAluno, emailAluno from matriculaaluno where nomecurso = '-';");
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				MatriculaAluno ma = new MatriculaAluno();
				ma.setCPFAluno(rs.getInt("cpfAluno"));
				ma.setNomeAluno(rs.getString("nomeAluno"));
				ma.setFoneAluno(rs.getString("foneAluno"));
				ma.setEmailAluno(rs.getString("emailAluno"));
				cursos.add(ma);
			}
		}catch(SQLException e) {
			System.err.println(e);
			
		}
		return cursos;
	}

public List <MatriculaAluno> mostrarTabela () throws Exception{
	
	ResultSet rs = null;
	PreparedStatement stmt = null;
	Connection conn = Conexao.abrir();
	
	List<MatriculaAluno> cursos = new ArrayList<>();
	
	try {
		
		stmt = conn.prepareStatement("select * from matriculaaluno;");
		rs = stmt.executeQuery();
		
		
		while(rs.next()) {
			MatriculaAluno ma = new MatriculaAluno();
			ma.setCPFAluno(rs.getInt("cpfAluno"));
			ma.setNomeAluno(rs.getString("nomeAluno"));
			ma.setFoneAluno(rs.getString("foneAluno"));
			ma.setEmailAluno(rs.getString("emailAluno"));
			ma.setNomeCurso(rs.getString("NomeCurso"));
			ma.setDataMatCurso(rs.getString("DataMatCurso"));
			ma.setAnoLetivo(rs.getInt("AnoLetivo"));
			ma.setDataMatAnoLetivo(rs.getString("DataMatAnoLetivo"));
			ma.setNomeDisciplina(rs.getString("nomeDisciplina"));
			ma.setNomeProfessor(rs.getString("NomeProfessor"));
			ma.setEmailProfessor(rs.getString("EmailProfessor"));
			cursos.add(ma);
		}
	}catch(SQLException e) {
		System.err.println(e);
		
	}
	return cursos;
}
 	
}
