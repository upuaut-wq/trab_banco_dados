package trab2jav;
import trab2jav.controllerAluno;

import java.io.IOException;
import java.util.Scanner;

public class viewAluno {
	controllerAluno ca;
	Scanner scanner;
	
	
	
	public viewAluno() {
		ca = new controllerAluno();
	}
	
	
	public void menu() throws IOException, InterruptedException {
		boolean op = true;
		scanner = new Scanner(System.in);
		int option_select = -1;
		while(op != false) {
			System.out.println("\n\n\n===Escolher ação==============");
			System.out.println("1 - Inserir Aluno");
			System.out.println("2 - Buscar Aluno");
			System.out.println("3 - Deletar Aluno");
			System.out.println("4 - Sair");
			
		
			option_select = scanner.nextInt();
			switch (option_select) {
			case 1:
				//Insert file
				
				System.out.println("\n\n\n===Inserir dados================");
				modelAluno ma = insert_aluno_scanner();
				boolean ret0 = ca.insert_aluno(ma);
				if(ret0 == true) {
					System.out.println("\n\nInserido com sucesso...");
				}else {
					System.out.println("\n\nFalha ao inserir dados...");
				}
				
				break;
			case 2:
				Runtime.getRuntime().exec("clear");
				System.out.println("\n\n\n===Buscar dados==================");
				modelAluno md = ca.busca_aluno(busca_aluno_scanner());
				if(md != null) {
					System.out.println(ca.convert_model_string(md));
				}else {
					System.out.println("\n\nCpf não existe...");
				}
				
				break;
			case 3:
				Runtime.getRuntime().exec("clear");
				System.out.println("\n\n\n===Deletar dados==================");
				boolean ret = ca.deleta_aluno(deleta_aluno_scanner());
				if(ret == true) {
					System.out.println("\n\nDado Deletado...");
				}else {
					System.out.println("\n\nDado não existe.");
				}
				
				break;
			default:
				break;
			}
		}
		scanner.close();
	}
	
	
	modelAluno insert_aluno_scanner() {
		scanner.nextLine();
		modelAluno ma = new modelAluno();
		System.out.print("nome_aluno:");
		ma.nome_aluno = scanner.nextLine();
		System.out.print("cpf:");
		ma.cpf = scanner.nextLine();
		System.out.print("num:");
		ma.num = scanner.nextLine();
		System.out.print("complemento:");
		ma.complemento = scanner.nextLine();
		System.out.print("id_endereco:");
		ma.id_endereco = scanner.nextInt();
		System.out.print("id_sexo:");
		ma.id_sexo = scanner.nextInt();
		System.out.print("id_email:");
		ma.id_email = scanner.nextInt();
		System.out.print("id_telefone:");
		ma.id_telefone = scanner.nextInt();
		System.out.print("id_tipo_ingresso:");
		ma.id_tipo_ingresso = scanner.nextInt();
		ma.free = 1;
		return ma;
	}
	
	public String busca_aluno_scanner() {
		scanner.nextLine();
		String cpf = "";
		System.out.print("Cpf para pesquisa:");
		cpf = scanner.nextLine();
		return cpf;
	}
	
	public String deleta_aluno_scanner() {
		scanner.nextLine();
		String cpf = "";
		System.out.print("Deleta Aluno(cpf):");
		cpf = scanner.nextLine();
		return cpf;
	}
	
}


