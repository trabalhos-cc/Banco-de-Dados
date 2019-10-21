package main;

import embaixada.EmbaixadaDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		EmbaixadaDAO b = new EmbaixadaDAO();
		try {
			
		
			b.removeEmbaixada("Brasil", "Cascavel");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
