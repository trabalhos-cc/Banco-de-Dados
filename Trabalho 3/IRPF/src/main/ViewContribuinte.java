package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contribuinte.Contribuinte;
import endereco.Endereco;
import tipoLogradouro.TipoLogradouro;
import tipoLogradouro.TipoLogradouroDAO;

public class ViewContribuinte {

	private static String nome;
	private static String dt;
	private static int titulo;
	private static boolean deficiencia;
	private static boolean conjuge;
	private static String tipoEnd;
	private static boolean mudanca;
	private static String tipoLogradouro;
	private static String logradouro;
	private static int numero;
	private static String complemento; 
	private static String bairro;
	private static int CEP;
	private static String email;
	private static int CPFProcurador;

	
	public static void menuContribuinte () throws Exception{
		Scanner sc = new Scanner (System.in);
		System.out.println("Nome: ");
		nome = sc.nextLine();
		
		System.out.println("Data de Nascimento: ");
		dt = sc.nextLine();
		
		System.out.println("Titulo Eleitoral: ");
		titulo = sc.nextInt();
		
		System.out.println("Deficiencia: ");
		deficiencia = sc.nextBoolean();
		
		System.out.println("Conjuge: ");
		conjuge = sc.nextBoolean();
		
		System.out.println("---------------------------------------------");
		
		System.out.println("Endereço Brasileiro ou Exterior: ");
		tipoEnd = sc.nextLine();
		
		
		
		if(tipoEnd.equals("Brasil")) {
			System.out.println("Houve mudança de Endereço: ");
			mudanca = sc.nextBoolean();
			
			listaTipoLogradouro();
			System.out.println("TipoLogradouro: ");
			tipoLogradouro = sc.nextLine();
			
			System.out.println("Logradouro: ");
			logradouro = sc.nextLine();
			
			System.out.println("Numero: ");
			numero = sc.nextInt();
			
			System.out.println("Complemento: ");
			complemento = sc.nextLine();
			
			System.out.println("Bairro: ");
			bairro = sc.nextLine();
			
			
			//listaUf();
			
			//ListaCidade();
			
			System.out.println("CEP: ");
			CEP = sc.nextInt();
			
			//Telefone();
			
			System.out.println("Email: ");
			email = sc.nextLine();
		}else {
			System.out.println("CPF do Procurador: ");
			CPFProcurador = sc.nextInt();
			
			//listaPais();
			//listaEmbaixada();
		}
		
		System.out.println("-------------------------------------------");
		//listaNatureza();
		//ListaOcupacao();
		
		enviarSQL();
		sc.close();
	}
	
	public static void enviarSQL () {
		
		Contribuinte c = new Contribuinte();
		c.setComplemento(complemento);
		c.setConjugue(conjuge);
		c.setData(dt);
		c.setDeficiente(deficiencia);
		c.setMudancaEnd(mudanca);
		c.setNumero(numero);
		c.setTituloEleitoral(titulo);
		
		Endereco end = new Endereco();
		end.setCEP(CEP);
		
		
	}
	
	public static void listaTipoLogradouro() throws Exception{
		List <TipoLogradouro> l = new ArrayList<>();
		TipoLogradouroDAO td = new TipoLogradouroDAO();
		 l = td.buscarTipoLogradouro();
		 
		 for(TipoLogradouro d : l) {
				System.out.println(d.getNome());
			}
	}
}
