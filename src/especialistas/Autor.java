package especialistas;

import java.util.InputMismatchException;

import cbpoo.Artigo;
import cbpoo.Cbpoo;
import cbpoo.Especialista;
import cbpoo.Membro;
import interfaces.AutorInterface;
import interfaces.MenuLogin;
import validadores.Validador;

public class Autor extends Especialista implements AutorInterface {
	
	

	@Override
	public void submeterArtigo() {
			
			Artigo a = new Artigo();
			
			System.out.print("\nSubmissao de artigo\n");             // Classe Autor
			System.out.print("--------------------\n");
			
			System.out.print("Digite o titulo do artigo: ");
			l.nextLine();
			String titArt = l.nextLine();
			a.setTitulo(titArt);
			
			
			//l.nextLine();
			System.out.print("Digite o abstract ( ENTER apos cada frase e 0 (zero+Enter) para concluir):\n");
			
			while(true) {
				String frase = l.nextLine();
				if(frase.equals("0")) 
					break;
				a.setAbst(frase);
			}
			
			System.out.print("Digite as palavras-chave do artigo ( ENTER apos cada palavra e 0 (zero+Enter) para concluir):\n");
			while(true) {
				String frase = l.next();
				if(frase.equals("0")) 
					break;
				a.setPalavraChave(frase);

			}
			l.nextLine();
			
			
			int qtAut;
			do {
				try {
					System.out.print("Quantidade de autores do artigo (1 a 5): ");
					qtAut = l.nextInt();
					
					if((qtAut > 0) && (qtAut < 6)) {
					
						String nomeAutor, cpf;
					
						l.nextLine();
						for(int i=0; i<qtAut; i++) {
							
							Autor x = new Autor();
							do {
								try {
									System.out.printf("\nNome do Autor %d: ", i+1);
									nomeAutor = l.nextLine();
									if(Validador.verificaNome(nomeAutor)) {
										x.setNome(nomeAutor);
										break;
									}
								}catch(RuntimeException e) {
									System.out.print(e.getMessage());
								}
							}while(true);
							
							//*******
							do {
								try {
									System.out.printf("CPF do Autor %d: ", i+1);
									cpf = l.nextLine();
									if(Validador.validaCpf(cpf)!= null) {
										x.setCpf(cpf);
										break;
									}
								}catch(RuntimeException e) {
									System.out.print(e.getMessage());
								}
							}while(true);

							//********
							System.out.println("********************");
							a.setAutor(x);
						}
						break;
						
					}else {
						System.out.println("Opcao invalida. Digite entre 1 e 5: ");
					}
				}catch(InputMismatchException e) {
					System.out.println("Entrada invalida. Digite apenas numeros: ");
				}
				l.nextLine();
			}while(true);
			
			int pg = 0;
			do {
				try {
					System.out.print("Quantidade de paginas do artigo (>0): ");
					pg = l.nextInt();
					a.setQtdPaginas(pg);
					
				}catch(InputMismatchException e) {
					System.out.println("Digite apenas digitos!");
				}
				l.nextLine();
				
			}while(pg <=0 );
			
			a.setDataSub();     //guardando data do sistema
			
			a.mostrarDadosArtigo();
			
			System.out.println("Submeter artigo? (1+ Enter)");
			int resp = l.nextInt();
			
			if(resp == 1) {
				Cbpoo.getInst().incArtPendente(a);
				System.out.println("\nArtigo enviado para avaliacao. Obrigado!");
				l.nextLine();
				return;
				
			}else
				return;
	}
		
	
	@Override
	public void menu() {
		
		this.boasVindas();
		
		
	}

}
