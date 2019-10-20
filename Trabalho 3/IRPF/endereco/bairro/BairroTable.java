package bairro;

import java.util.ArrayList;

public class BairroTable {

	private ArrayList<Object> linhas;
	private ArrayList<String> colunas;
	
	public BairroTable() {
		colunas = new ArrayList<String>();
		colunas.add("Bairro");
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
	
	public void adicionar (ArrayList<Bairro> lista) {
		linhas = new ArrayList<Object>();
		
		for(Bairro d : lista) {
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(d.getNomeBairro());
			
			linhas.add(linha);
		}
		
	}
}
