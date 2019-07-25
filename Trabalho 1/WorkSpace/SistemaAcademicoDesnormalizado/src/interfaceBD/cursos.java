package interfaceBD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import matriculaAluno.MatriculaAluno;
import matriculaAluno.MatriculaAlunoDAO;

public class cursos {
	
	private MatriculaAlunoDAO ma = new MatriculaAlunoDAO();
	Scanner sc = new Scanner(System.in);
	private List<MatriculaAluno> m = new ArrayList<>();
	
	char x;
	Scanner s;
	
	public cursos() {
		do {
			System.out.println("-------------------------------------------------------");
			System.out.println("\n\n\n");
			System.out.println("\t[1] - Cursos Disponiveis");
			System.out.println("\t[2] - Disciplinas Disponiveis");
			System.out.println("\n\n\n");
			System.out.println("-------------------------------------------------------");
			System.out.print("\t opcao: ");
			int op = sc.nextInt();
			
			try {
				
				if(op == 1) {
					m = ma.buscaCurso();
					
					for (int i = 0; i < m.size(); i++) {
						System.out.println(m.get(i).getNomeCurso());
					}
				}
				
				if(op == 2) {
					m = ma.buscaDisciplinas();
						
					System.out.println("Disciplinas:");
					for (int i = 0; i < m.size(); i++) {
						System.out.println(m.get(i).getNomeDisciplina());
						System.out.print(m.get(i).getNomeProfessor()+"||");
						System.out.println(m.get(i).getEmailProfessor());
					}
					
				}
				
			}catch (Exception e) {
				System.err.println(e);
			}
			System.out.println("Continuar? [s/n]");
			s = new Scanner(System.in);
			x = s.next().charAt(0);
			
		}while(x != 'n');
		s.close();	

	}
	
	
}
