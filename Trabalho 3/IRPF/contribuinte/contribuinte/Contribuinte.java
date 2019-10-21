package contribuinte;

public class Contribuinte {

	private int idContribuinte;
	private boolean mudancaEnd;
	private boolean conjugue;
	private String data;
	private int tituloEleitoral;
	private boolean deficiente;
	private int numero;
	private String complemento;
	private int idOcupacao;
	private int idEndereco;
	
	public int getIdContribuinte() {
		return idContribuinte;
	}
	public void setIdContribuinte(int idContribuinte) {
		this.idContribuinte = idContribuinte;
	}
	public boolean isMudancaEnd() {
		return mudancaEnd;
	}
	public void setMudancaEnd(boolean mudancaEnd) {
		this.mudancaEnd = mudancaEnd;
	}
	public boolean isConjugue() {
		return conjugue;
	}
	public void setConjugue(boolean conjugue) {
		this.conjugue = conjugue;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getTituloEleitoral() {
		return tituloEleitoral;
	}
	public void setTituloEleitoral(int tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}
	public boolean isDeficiente() {
		return deficiente;
	}
	public void setDeficiente(boolean deficiente) {
		this.deficiente = deficiente;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public int getIdOcupacao() {
		return idOcupacao;
	}
	public void setIdOcupacao(int idOcupacao) {
		this.idOcupacao = idOcupacao;
	}
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	
}
