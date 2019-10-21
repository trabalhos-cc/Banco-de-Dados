package main;

import enderecoExterior.EnderecoExteriorDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		EnderecoExteriorDAO b = new EnderecoExteriorDAO();
		try {
			
		
			b.inserirEnderecoExterior(159, 777, "Foz do Iguaçu");
			b.removeEnderecoExterior(777);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
