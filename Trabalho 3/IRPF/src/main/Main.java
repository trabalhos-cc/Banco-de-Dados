package main;

import tipoLogradouro.TipoLogradouroDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		TipoLogradouroDAO b = new TipoLogradouroDAO();
	
		try {
			
			b.inserirTipoLogradouro("Teste");
			b.inserirTipoLogradouro("Teste1");
			b.removeTipoLogradouro("Teste");
			b.inserirTipoLogradouro("Teste2");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
