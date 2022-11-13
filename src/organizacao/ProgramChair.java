package organizacao;

import java.util.InputMismatchException;

import cbpoo.Artigo;
import cbpoo.Cbpoo;
import cbpoo.Membro;
import interfaces.MenuLogin;
import interfaces.ProgramChairInterface;
import validadores.Validador;

public class ProgramChair extends Membro implements ProgramChairInterface {


	@Override
	public void menu() {
		
		this.boasVindas();                             //Program Chair, aceita artigos de acordo com avaliacoes de revisores
		
		
		
	}

//***********************************************************
	@Override
	public Artigo verAvaliacaoDeArtigoQualquer() {  //ProgramChair
		
		
		this.listarTodosArtigos(Cbpoo.getInst());
		Artigo x;
		
		System.out.println("\nDigite o codigo (numerico) do artigo que deseja ver avaliacoes: ");
		String resp;
		l.nextLine();
		do {
			try {
				resp = l.nextLine();
				if(Validador.apenasNumeros(resp)!=null) {
					
					int id = Integer.parseInt(resp);
					
					for(Artigo i: Cbpoo.getInst().getArtigosAceitos()) { //itera sobre os artigos pendentes
						
						x= this.visualizaAvProgramChair(i, id);
						if(x!=null)
							return x;
							
					}
					
					for(Artigo i: Cbpoo.getInst().getArtPendentes() ) { //itera sobre os artigos pendentes
						
						x= this.visualizaAvProgramChair(i, id);
						if(x!=null)
							return x;
						
					}
					//System.out.println("\nArtigo nao encontrado. Verifique codigo digitado!");
					return null;
				}
				
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
	}
//***************************************************************
	@Override
	public void aceitarOuNegarArtigo() { //tratar argumento
        
		Artigo a = this.verAvaliacaoDeArtigoQualquer();
		
		if(a == null) {
			System.out.println("Nao encontrado!");
			return;
		}
		
		this.visualizarDadosArt(Cbpoo.getInst(), a.getId());
		
		System.out.println("Aceitar artigo? Sim (1)   Nao (0)  (digite opcao + Enter) - ");
		int resp;
		do {
			try {
				resp = l.nextInt();	
				if(resp == 1) {
					Cbpoo.getInst().inserirArtigoAceito(a);
					Cbpoo.getInst().getArtPendentes().remove(0);
					System.out.println("---> O artigo foi aceito!!!");
					return;
				}
					
				else if(resp == 0) {
					Cbpoo.getInst().inserirArtigoNegado(a);
					Cbpoo.getInst().getArtPendentes().remove(0);
					return;
				}
					
				else 
					System.out.println("Digite uma opcao valida: ");
				
			}catch(InputMismatchException e) {
				System.out.println("Digite uma entrada valida: ");
			}

	}while(true);
	}

}
