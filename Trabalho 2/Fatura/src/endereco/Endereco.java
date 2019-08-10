package endereco;

public class Endereco {

	private int idEndereco;
	private String CEP;
	
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
	
	public String toString() {
		return "Id: " + this.idEndereco + "; CEP: " + this.CEP; 
	}
}
