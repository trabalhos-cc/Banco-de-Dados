package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		try {
			menu();
		
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public static void menu () throws Exception{
		
		System.out.println("-------Menu de Opera��es-------");
		System.out.println();
		System.out.println();
		System.out.println("[1] - Informa��es do Contribuinte");
		System.out.println("[2] - Dependentes");
		System.out.println("[3] - Rendimento Tribut�rio");
		System.out.println("[4] - Bens e Servi�os");
		
		Scanner sc = new Scanner (System.in);
		int x = sc.nextInt();
		
		switch(x) {
		case 1:
			ViewContribuinte.menuContribuinte();
			break;
		case 2:
			
		case 3:
			
		case 4:
			
		default:
			System.out.println("Comando Inv�lido");
		}
		
		sc.close();
	}
}
