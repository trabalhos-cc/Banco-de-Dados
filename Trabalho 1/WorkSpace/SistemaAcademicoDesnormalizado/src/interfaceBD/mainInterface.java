package interfaceBD;

import java.util.Scanner;

public class mainInterface {

	Scanner sc = new Scanner(System.in);
	public mainInterface() {
		this.usuario();
	}
	
	public void usuario() {
		System.out.println("-------------------------------------------------------");
		System.out.println("\n\n\n");
		System.out.println("\t[1] - Secretaria");
		System.out.println("\t[2] - Alunos Matriculados");
		System.out.println("\t[3] - Visitantes");
		System.out.println("\n\n\n");
		System.out.println("-------------------------------------------------------");
		System.out.print("\t opcao: ");
		int op = sc.nextInt();
		
		try {
			if(op == 1) {
				new interfaceSecretaria();
			}
			if(op == 2) {
				new interfaceAluno();
			}
			if(op == 3) {
				new cursos();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		sc.close();
	}
}
