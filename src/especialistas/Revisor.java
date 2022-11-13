package especialistas;

import java.util.ArrayList;
import java.util.InputMismatchException;

import cbpoo.Artigo;
import cbpoo.Cbpoo;
import cbpoo.Especialista;
import cbpoo.Membro;
import interfaces.MenuLogin;
import interfaces.RevisorInterface;
import validadores.Validador;

public class Revisor extends Especialista implements RevisorInterface{


	@Override
	public void visualizarAvaliacoes() {                              //Classe Revisor
		
		this.listarTodosArtigos(Cbpoo.getInst());
		
		
		System.out.println("\nDigite o codigo (numerico) do artigo a ser visualizado: ");
		String resp;
		l.nextLine();
		do {
			try {
				resp = l.nextLine();
				if(Validador.apenasNumeros(resp)!=null) {
					
					int id = Integer.parseInt(resp);
					
					
					for(Artigo i: Cbpoo.getInst().getArtigosAceitos() ) { //itera sobre os artigos pendentes
						
						this.visualizaAvRevisor(i, id, this.getCpf());
						
					}
					
					
					for(Artigo i: Cbpoo.getInst().getArtPendentes() ) { //itera sobre os artigos pendentes
						
						this.visualizaAvRevisor(i, id, this.getCpf());
						
					}
					break;
				}
				
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
				
	}
	//******************************************************************************
	@Override
	public void enviarAvaliacaoArt() {             //revisor 
		
		
		if(Cbpoo.getInst().getArtPendentes().isEmpty()) {
			System.out.println("Ainda nao ha artigos pendentes/submetidos!");
			return;
		}else {
			System.out.println("Artigos pendentes:");
			
			for(Artigo i: Cbpoo.getInst().getArtPendentes()) {
				System.out.printf("cod: %d - %s\n", i.getId(), i.getTitulo());
			}
		}
		
		System.out.println("\nDigite o codigo (numerico) do artigo a ser avaliado:");
		String resp;
		l.nextLine();
		do {
			try {
				resp = l.nextLine();
				if(Validador.apenasNumeros(resp)!=null) {
					
					int id = Integer.parseInt(resp);
					
					for(Artigo i: Cbpoo.getInst().getArtPendentes() ) {
						
						if(i.getId() == id) {
							i.mostrarDadosArtigo();
							ArrayList <String> av = new ArrayList <String>();
							System.out.print("Digite sua avaliacao ( ENTER apos cada frase e 0 (zero+Enter) para concluir):\n");
							String frase;
							
							do{
								
								
								frase = l.nextLine();
								if(!frase.equals("0")) {
									av.add(frase);
								
								}else {
									i.InsereAvaliacao(this.getCpf(), av);
									System.out.println("\nSua avaliacao foi enviada. Obrigado!");
									break;
								}
								
							}while(true);
							
							return;
						}
					}
					System.out.println("\nArtigo nao localizado!");
					break;
				}
				
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
		
	}
	
	
	@Override
	public void menu() {

		this.boasVindas();
		
		

		
	}

}
