package matriculaAluno;

import java.util.ArrayList;

public class MatriculaAlunoTabela {

	private ArrayList<Object> linhas;
	private ArrayList<String> colunas;
	
	public MatriculaAlunoTabela() {
		colunas = new ArrayList<String>();
		colunas.add("CPF Aluno");
		colunas.add("Nome Aluno");
		colunas.add("Fone Aluno");
		colunas.add("email Aluno");
		colunas.add("Nome Curso");
		colunas.add("Data Matricula Curso");
		colunas.add("Ano Letivo");
		colunas.add("Data Matricula Ano Letivo");
		colunas.add("Nome Disciplina");
		colunas.add("Nome Professor");
		colunas.add("Email Professor");
		
		linhas = new ArrayList<Object>();
	}
	
	/*contador de linhas*/
	public int getRowCount() {
		return linhas.size();
	}
	
	/*contador de colunas*/
	public int getColumnCount() {
		return colunas.size();
	}
	
	public String getColumnName(int coluna) {
		return (String)colunas.get(coluna);
	}
	
	public Object getValueArt (int linha, int coluna) {
		ArrayList<Object> registro = new ArrayList<Object>();
		registro.add(linhas.get(linha));
		
		return registro.get(coluna);
			
	}
	
	public void adicionar (ArrayList<MatriculaAluno> lista) {
		linhas = new ArrayList<Object>();
		
		for(MatriculaAluno d : lista) {
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(d.getCPFAluno());
			linha.add(d.getNomeAluno());
			linha.add(d.getFoneAluno());
			linha.add(d.getEmailAluno());
			linha.add(d.getNomeCurso());
			linha.add(d.getDataMatCurso());
			linha.add(d.getAnoLetivo());
			linha.add(d.getDataMatAnoLetivo());
			linha.add(d.getNomeDisciplina());
			linha.add(d.getNomeProfessor());
			linha.add(d.getEmailProfessor());
			
			linhas.add(linha);
		}
		
	}
	
	
}
