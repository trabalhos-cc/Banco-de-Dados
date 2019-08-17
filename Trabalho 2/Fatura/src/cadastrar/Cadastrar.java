package cadastrar;

import bin.endereco.EnderecoDAO;

public class Cadastrar {

	public static void cadastrarEndereco () {
		
		//Scanner sc = new Scanner(System.in);
		//System.out.println("cadastro de um novo Endereço");
		System.out.println("qui");
		EnderecoDAO end = new EnderecoDAO();
		 System.out.println("endereco");
		try {
			end.inserirEndereco(0, "85851-390", 0, 0, 0);
		}catch(Exception e) {
			System.err.println(e);
		}
		
		
		
	}
}
