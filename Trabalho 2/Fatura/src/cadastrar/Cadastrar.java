package cadastrar;

import java.util.Scanner;

import bairro.Bairro;
import bairro.BairroDAO;
import cidade.Cidade;
import cidade.CidadeDAO;
import endereco.Endereco;
import endereco.EnderecoDAO;
import logradouro.Logradouro;
import logradouro.LogradouroDAO;
import pais.Pais;
import pais.PaisDAO;
import tipoLogradouro.TipoLogradouro;
import tipoLogradouro.TipoLogradouroDAO;
import uf.Uf;
import uf.UfDAO;

public class Cadastrar {

	public static void cadastrar () {
		
		Scanner sc = new Scanner(System.in);
		Scanner s;
		char x;
		
		do {
		
			System.out.println("----------------------Cadastro----------------------");
			System.out.println("[1] - Cadastrar Endereço");
			System.out.println("[2] - Cadastrar Cliente");
			System.out.println("[3] - Cadastrar Empresa");
			System.out.println("----------------------------------------------------");
			
			int op = sc.nextInt();
			System.out.println(op);
			
			try {
				if(op == 1) {
					/*cadastra Endereço*/
					Cadastrar.cadastraEndereco();
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
		
		sc.close();
		s.close();
	}
	
	
	public static void cadastraEndereco() throws Exception{
		Scanner sc = new Scanner(System.in);
		
		TipoLogradouroDAO tpd = new TipoLogradouroDAO();
		TipoLogradouro tp = new TipoLogradouro();
		
		LogradouroDAO ld = new LogradouroDAO();
		Logradouro l = new Logradouro();
		
		Bairro b = new Bairro();
		BairroDAO bd = new BairroDAO();
		
		Pais p = new Pais();
		PaisDAO pd = new PaisDAO();
		
		Uf u = new Uf();
		UfDAO ud = new UfDAO();
		
		Cidade c = new Cidade();
		CidadeDAO cd = new CidadeDAO();
		
		Endereco e = new Endereco();
		EnderecoDAO ed = new EnderecoDAO();
		
		
		System.out.println("----------------------Cadastro de Endereço----------------------");
		System.out.println("Tipo Logradouro: ");
		tp.setNome(sc.nextLine());
		tpd.inserirTipoLogradouro(tp.getNome());
		
		System.out.println("Logradouro: ");
		l.setNome(sc.nextLine());
		ld.inserirLogradouro(l.getNome(), tp.getNome());
		
		System.out.println("Bairro: ");
		b.setNomeBairro(sc.nextLine());
		bd.inserirBairro(b.getNomeBairro());
		
		System.out.println("Pais: ");
		p.setNome(sc.nextLine());
		pd.inserirPais(p.getNome());
		
		System.out.println("UF: ");
		u.setNome(sc.nextLine());
		ud.inserirUf(u.getNome(), p.getNome());
		
		System.out.println("Cidade: ");
		c.setNomeCidade(sc.nextLine());
		cd.inserirCidades(c.getNomeCidade(), u.getNome());
				
		System.out.println("CEP: ");
		e.setCEP(sc.nextLine());
		ed.inserirEndereco(e.getCEP(), c.getNomeCidade(), b.getNomeBairro(), l.getNome());
		
		sc.close();
	}
		
}
