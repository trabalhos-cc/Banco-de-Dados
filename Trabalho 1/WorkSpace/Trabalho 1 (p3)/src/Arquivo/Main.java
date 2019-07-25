package Arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		//Declaração do arquivo
		FileWriter arqAluno;
		
		//Abertura do arquivo
		arqAluno = new FileWriter(new File("C:\\Users\\Gabriel\\Desktop\\Hannah\\Exercicio 3/aluno1.txt"));
		
		//Variavel de controle do loop
		char op = 0;
		
		//Dados do aluno
		String nome = null;
		String cpf = null;
		String fone = null;
		String email = null;
       
       do {
    	   //cadastrando os dados do aluno
    	   System.out.println("Nome do Aluno: ");
           nome = scan.next();
           System.out.println("CPF do Aluno: ");
           cpf = scan.next();
           System.out.println("Telefone do Aluno: ");
           fone = scan.next();
           System.out.println("E-mail do Aluno: ");
           email = scan.next(); 
           
    	   try {
    		   //Escrevendo os dados no arquivo
        		arqAluno.write("Nome: " + nome);
        		arqAluno.write("\n");
        		arqAluno.write("CPF: " + cpf);
        		arqAluno.write("\n");
				arqAluno.write("Telefone: " + fone);
				arqAluno.write("\n");
				arqAluno.write("E-mail: " + email);
				arqAluno.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	   
    	   // Controle do loop
    	   System.out.println("Deseja continuar? [s/n]");
    	   op = scan.next().charAt(0);
       	   
	     } while(op != 'n');
       
       //Fechamento do Arquivo
        arqAluno.close();
    }
}
