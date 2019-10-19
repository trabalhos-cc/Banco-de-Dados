package endereco.endereco;

public class Endereco {

	private int idEndereco;
	private String CEP;
	private int idCidade;
	private int idbairro;
	private int idLogradouro;
	
	public int getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}
	
	public int getIdbairro() {
		return idbairro;
	}
	public void setIdbairro(int idbairro) {
		this.idbairro = idbairro;
	}
	
	public int getIdLogradouro() {
		return idLogradouro;
	}
	public void setIdLogradouro(int idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
}
