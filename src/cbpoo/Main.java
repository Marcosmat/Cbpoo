package cbpoo;
import java.util.Scanner;

import especialistas.Autor;
import validadores.Validador;

import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		
		Scanner r = new Scanner(System.in);
		Cbpoo c = Cbpoo.getInst();
		
		boolean sair = false;
		int resp;
		
		while(!sair) {
			
			try {
				System.out.printf("\n----------\n");
				System.out.printf("C B P O O\n");
				System.out.printf("----------\n\n");
				System.out.printf("1 - LOGIN\n");
				System.out.printf("2 - INSCREVER-SE\n");
				System.out.printf("3 - SAIR\n");
				System.out.print("--> ");
				
				
				resp = r.nextInt();
				String cpf = null;
				switch (resp) {
			
					case 1:
						r.nextLine();
						String id;
						do {
							System.out.println("CPF: ");
							try {
								id = r.nextLine();
								if(Validador.validaCpf(id)!=null) {
									
										break;
								}
							}catch(RuntimeException e) {
								System.out.println(e.getMessage());
							}
							
						}while(true);
						
						System.out.println("Senha: ");
						String senha = r.nextLine();
						if(c.buscarMembro(id, senha)==null) {
							System.out.println("Usuario nao encontrado!");
						}
						break;
			
					case 2:
						do {
								System.out.print("Digite seu CPF (11 numeros): ");
								try {
									cpf = r.next();
									
									if(Validador.validaCpf(cpf)!=null) {
										
										if(c.buscarMembro(cpf, "123")!=null) {
											System.out.println("Usuario ja cadastrado!");
											break;
										}else {
											Participante p = new Participante();
											System.out.println("\nCADASTRO DO PARTICIPANTE");
											System.out.println("--------------------------\n");
											p.inscrever(c,cpf);
											break;
										}
										
									}else cpf = null;
								}catch(RuntimeException e){
									System.out.print(e.getMessage());
								}

							
							
						}while(cpf == null);
			
						break;
			
					case 3:
						sair = true;
						System.out.printf("\nPrograma encerrado.");
						break;
			
					default: 
						System.out.println("Digite uma opcao valida (1 - 3)\n");
				}
		   }
			catch(InputMismatchException e) {
				System.out.println("Entrada invalida. Digite apenas numeros (1 - 3)");
			}
			r.nextLine();
		
		}

		
		

	}

}
