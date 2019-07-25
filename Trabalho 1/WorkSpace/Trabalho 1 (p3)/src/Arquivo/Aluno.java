/*
 * CLASSE ENFEITE - NÃO SERVE PRA NADA KKKKKKK
 * */
package Arquivo;

public class Aluno {
	 //Conteudo inicial.
    String nome = null; //nome do aluno
    String cpf = null; // CPF do aluno
    String fone = null; //Telefone do aluno
    String email = null; //Enail do aluno
	
    public Aluno(String nome, String cpf, String fone, String email) {
    	this.nome = nome;
    	this.cpf = cpf;
    	this.fone = fone;
    	this.email = email;
    }
    
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return "Nome: " + this.nome +
				"\nCPF: " + this.cpf + 
				"\nTelefone: " + this.fone +
				"\nEmail: " + this.email;
	}
    
	
    
}
