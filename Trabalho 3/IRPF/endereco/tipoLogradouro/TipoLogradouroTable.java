package tipoLogradouro;

import java.util.ArrayList;

public class TipoLogradouroTable {

	private ArrayList<Object> linhas;
	private ArrayList<String> colunas;
	
	public TipoLogradouroTable() {
		colunas = new ArrayList<String>();
		colunas.add("TipoLogradouro");
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
	
	public void adicionar (ArrayList<TipoLogradouro> lista) {
		linhas = new ArrayList<Object>();
		
		for(TipoLogradouro d : lista) {
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(d.getNome());
			
			linhas.add(linha);
		}
		
	}
}
