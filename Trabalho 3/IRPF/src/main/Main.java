package main;

import bensDireito.BensDireitoDAO;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BensDireitoDAO b = new BensDireitoDAO();
		try {
			
		//b.inserirBensDireito("compra", 0, 1000, 1, "xxxx", 1064798, "Brasil");
		b.removeBensDireito(1);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
