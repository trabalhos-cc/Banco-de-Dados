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
		
		System.out.println("-------Menu de Operações-------");
		System.out.println();
		System.out.println();
		System.out.println("[1] - Informações do Contribuinte");
		System.out.println("[2] - Dependentes");
		System.out.println("[3] - Rendimento Tributário");
		System.out.println("[4] - Bens e Serviços");
		
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
			System.out.println("Comando Inválido");
		}
		
		sc.close();
	}
}
