package menu;

import java.util.Scanner;

import cadastrar.Cadastrar;

public class Menu {

	public static void mainMenu() {
		
		Scanner sc = new Scanner(System.in);
		Scanner s;
		boolean x = false;
		
		do {
		
			System.out.println("--------------Operadora de Celular GKR--------------");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Buscar");
			System.out.println("[3] - Emitir fatura");
			System.out.println("[4] - Sair");
			System.out.println("----------------------------------------------------");
			
			int op = sc.nextInt();
			
			try {
				if(op == 1) {
					/*cadastra novo dado*/
					Menu.menuCadastro();
				}
				if(op == 2) {
					/*busca dado */
				}
				if(op == 3) {
					/*emite uma fatura */
				}
				if(op == 4) {
					/*Sair*/
					x = true;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}

			s = new Scanner(System.in);
			
		}while(x != true);
		
		System.out.println("Saindo do programa ...");
		sc.close();
		s.close();
	}
	
	public static void menuCadastro() {
		
		System.out.println("menucadastro");
		Cadastrar.cadastrar();
	}
}
