package main;

import endereco.EnderecoDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		EnderecoDAO b = new EnderecoDAO();
		try {
			
		
			//b.inserirEndereco(85851390, "Foz do Iguaçu", "Teste1", "JK", "Brasil", 0);
			b.removeEndereco(0);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
