package interfaceBD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import matriculaAluno.MatriculaAluno;
import matriculaAluno.MatriculaAlunoDAO;

public class interfaceSecretaria {

	Scanner sc = new Scanner(System.in);
	private MatriculaAlunoDAO ma = new MatriculaAlunoDAO();
	private List<MatriculaAluno> m = new ArrayList<>();
	Scanner curso;
	Scanner s;
	char x;
	
	public interfaceSecretaria() {
		do {
			System.out.println("-------------------------------------------------------");
			System.out.println("\n\n\n");
			System.out.println("\t[1] - Inserir Curso");
			System.out.println("\t[2] - Inserir Disciplina");
			System.out.println("\t[3] - Inserir Aluno");
			System.out.println("\t[4] - Matricular Aluno");
			System.out.println("\t[5] - Informacoes Aluno");
			System.out.println("\t[5] - Disciplinas Disponiveis");
			System.out.println("\n\n\n");
			System.out.println("-------------------------------------------------------");
			System.out.print("\t opcao: ");
			int op = sc.nextInt();
			
			try {
				if(op == 1) { //inserir curso
					System.out.print("Nome do Curso: ");
					curso = new Scanner(System.in);
					ma.InserirCurso(curso.nextLine());
	
				}
				if(op == 2) { //inserir disciplina
					System.out.print("Nome da Disciplina: ");
					curso = new Scanner(System.in);
					String nomeDisc = curso.nextLine();
					
					System.out.print("Nome do professor: ");
					curso = new Scanner(System.in);
					String nomeProf = curso.nextLine();
					
					System.out.print("Email do professor: ");
					curso = new Scanner(System.in);
					String emailProf = curso.nextLine();
					ma.InserirDisciplina(nomeDisc, nomeProf, emailProf);
				}
				
				
				if(op == 3) { //inserir aluno
					System.out.print("CPF: ");
					Scanner curso = new Scanner(System.in);
					int CPF = curso.nextInt();
					
					System.out.print("Nome: ");
					curso = new Scanner(System.in);
					String nome = curso.nextLine();
					
					System.out.print("Fone: ");
					curso = new Scanner(System.in);
					String fone = curso.nextLine();
					
					System.out.print("Email: ");
					curso = new Scanner(System.in);
					String email = curso.nextLine();
					ma.InserirAluno(CPF, nome, fone, email);
				}
				
				
				if(op == 4) { //matricular aluno
					m = ma.buscaAlunosNaoMatriculados();
					
					System.out.println("CPF \t\t\t Nome \t\t\t Fone \t\t\t Email");
					for (int i = 0; i < m.size(); i++) {
						System.out.print(m.get(i).getCPFAluno()+"||");
						System.out.print(m.get(i).getNomeAluno()+"||");
						System.out.print(m.get(i).getFoneAluno()+"||");
						System.out.println(m.get(i).getEmailAluno());
					}
					
					
					System.out.print("CPF Do Aluno: ");
					curso = new Scanner(System.in);
					int cpf = curso.nextInt();
					
					System.out.print("Nome Do Curso: ");
					curso = new Scanner(System.in);
					String NomeC = curso.nextLine();
					
					System.out.print("Data de matricula no Curso: ");
					Scanner curso = new Scanner(System.in);
					String dmc = curso.nextLine();
					
					System.out.print("Ano Letivo: ");
					curso = new Scanner(System.in);
					int al = curso.nextInt();
					
					System.out.print("data da Matricula no Ano Letivo: ");
					curso = new Scanner(System.in);
					String dmal = curso.nextLine();
						
					
					m = ma.buscaDisciplinas();
					
					System.out.println("Disciplinas:");
					for (int i = 0; i < m.size(); i++) {
						System.out.print(m.get(i).getNomeDisciplina()+"||");
						System.out.print(m.get(i).getNomeProfessor()+"||");
						System.out.println(m.get(i).getEmailProfessor());
					}
					boolean a = true;
					do {
						System.out.print("Disciplina: ");
						curso = new Scanner(System.in);
						String d = curso.nextLine();
						
						ma.MatricularAluno(cpf, NomeC, dmc, al, dmal, d);
						if(curso.nextInt() == 0) { a = false;}

					}while(a);
					
					
				}
				
				
				if(op == 5) {
					m = ma.buscaAlunos();
						
					System.out.println("CPF \t\t\t Nome \t\t\t Fone \t\t\t Email");
					for (int i = 0; i < m.size(); i++) {
						System.out.print(m.get(i).getCPFAluno()+"||");
						System.out.print(m.get(i).getNomeAluno()+"||");
						System.out.print(m.get(i).getFoneAluno()+"||");
						System.out.println(m.get(i).getEmailAluno());
					}
	
				}
				
				if(op == 6) {
					m = ma.buscaDisciplinas();
					
					System.out.println("Disciplinas:");
					for (int i = 0; i < m.size(); i++) {
						System.out.println(m.get(i).getNomeDisciplina()+"||");
						System.out.print(m.get(i).getNomeProfessor()+"||");
						System.out.print(m.get(i).getEmailProfessor());
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Continuar? [s/n]");
			s = new Scanner(System.in);
			x = s.next().charAt(0);
			
		}while(x != 'n');
		
		try {
			ma.mostrarTabela();
			//System.out.println("CPF \t\t\t Nome \t\t\t Fone \t\t\t Email");
			for (int i = 0; i < m.size(); i++) {
				System.out.print(m.get(i).getCPFAluno()+"||");
				System.out.print(m.get(i).getNomeAluno()+"||");
				System.out.print(m.get(i).getFoneAluno()+"||");
				System.out.print(m.get(i).getEmailAluno()+"||");
				System.out.print(m.get(i).getNomeCurso()+"||");
				System.out.print(m.get(i).getDataMatCurso()+"||");
				System.out.print(m.get(i).getAnoLetivo()+"||");
				System.out.print(m.get(i).getDataMatAnoLetivo()+"||");
				System.out.print(m.get(i).getNomeDisciplina()+"||");
				System.out.print(m.get(i).getNomeProfessor()+"||");
				System.out.println(m.get(i).getEmailProfessor());
			}
			
		}catch(Exception e) {System.err.println(e);}
		sc.close();
	}
}
