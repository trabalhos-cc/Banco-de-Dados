package menu;

import java.util.Scanner;

import cadastrar.Cadastrar;

public class Menu {

	public static void mainMenu() {
		
		Scanner sc = new Scanner(System.in);
		Scanner s;
		char x;
		
		do {
		
			System.out.println("--------------Operadora de Celular GKR--------------");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Buscar");
			System.out.println("[3] - Emitir fatura");
			System.out.println("----------------------------------------------------");
			
			int op = sc.nextInt();
			System.out.println(op);
			
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
				
			}catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Continuar? [s/n]");
			s = new Scanner(System.in);
			x = s.next().charAt(0);
			
		}while(x != 'n');
		
		System.out.println("Saindo do programa ...");
		sc.close();
		s.close();
	}
	
	public static void menuCadastro() {
		
		System.out.println("menucadastro");
		Cadastrar.cadastrarEndereco();
	}
}
