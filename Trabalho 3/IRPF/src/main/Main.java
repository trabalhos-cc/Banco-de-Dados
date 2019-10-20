package main;

import bairro.BairroDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BairroDAO b = new BairroDAO();
	
		try {
		b.inserirBairro("Teste");
		}catch(Exception e) {}
	}

}
