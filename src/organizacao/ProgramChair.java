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
		
		this.boasVindas();
		
		
		
	}

//***********************************************************
	@Override
	public Artigo verAvaliacaoDeArtigoQualquer() {  //ProgramChair
		
		
		if(Cbpoo.getInst().getArtPendentes().isEmpty() ) {
			System.out.println("Nao ha artigos pendentes!");
			return null;
		}else {
			System.out.println("Lista de artigos pendentes:");
			for(Artigo i: Cbpoo.getInst().getArtPendentes()) {
				System.out.printf("cod: %d - %s\n", i.getId(), i.getTitulo());
			}
		}
		
		System.out.println("\nDigite o codigo (numerico) do artigo que deseja ver avaliacoes: ");
		String resp;
		l.nextLine();
		do {
			try {
				resp = l.nextLine();
				if(Validador.apenasNumeros(resp)!=null) {
					
					int id = Integer.parseInt(resp);
					
					for(Artigo i: Cbpoo.getInst().getArtPendentes() ) { //itera sobre os artigos pendentes
						
						if(i.getId() == id) {                    // se o cod digitado for igual					
							System.out.printf("cod: %d - %s:\n", i.getId(), i.getTitulo());	
							System.out.println("Avaliacoes:");
							for(int j=0; j< i.getAvaliacoes().size();j++) {  //itera sobre a lista de avaliacoes
								
									//System.out.printf(" -%s\n", i.getAvaliacoes().get(j)); 
								System.out.printf("- %s\n", i.getAvaliacoes().values());
							}
							return i;
						}
					}
					System.out.println("\nArtigo nao encontrado. Verifique codigo digitado!");
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
			return;
		}
		
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
