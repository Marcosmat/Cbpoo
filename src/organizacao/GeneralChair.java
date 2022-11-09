package organizacao;

import java.util.InputMismatchException;

import cbpoo.Cbpoo;
import cbpoo.Membro;
import cbpoo.Participante;
import cbpoo.Pessoa;
import interfaces.GeneralChairInterface;
import interfaces.MenuLogin;
import validadores.Validador;

public class GeneralChair extends Membro implements GeneralChairInterface{

	

	@Override
	public void validarOuInvInscricao() {
		
		if(Cbpoo.getInst().getPendentes().isEmpty()) {
			System.out.println("Nao existem participantes pendentes!");
			return;
		}


		System.out.println("Participante(s) pendente(s):");
		
		for(int i = 0; i < Cbpoo.getInst().getPendentes().size(); i++) { 
			System.out.printf("%d - %s\n", i+1, Cbpoo.getInst().getPendentes().get(i).getNome());
		}
		System.out.println("\n\nDados do primeiro da fila:");
		Cbpoo.getInst().getPendentes().get(0).mostrarDadosParticipante();
		System.out.println();
		
		System.out.print("\n----->Aprovar membro? Sim(1)  nao(0) ->");
		int resp;
		
		do {
			try {
				resp = l.nextInt();
				if(resp == 1) {
					Cbpoo.getInst().getParticipantes().add (Cbpoo.getInst().getPendentes().get(0)); //add aos aceitos
					Cbpoo.getInst().getPendentes().remove(0);  //remove dos pendentes
					System.out.println("----->Participante aceito!");
					break;
				}else if (resp == 0) {
					Cbpoo.getInst().getPendentes().remove(0);
					System.out.println("------>Participante removido!");
					return;
				}
				else
					System.out.println("Digite uma opcao valida: ");
					
				
			}catch(InputMismatchException e) {
				System.out.println("Digite uma entrada valida: ");
			}
			l.nextLine();
		}while(true);
		

	}
//************************************************************************
	@Override
	public void menu() {
		
		this.boasVindas();
		
		
	}
	//*****************************************************
	@Override
	public void emitirCertificado() {                  //general chair
			
			if(Cbpoo.getInst().getParticipantes().isEmpty()) {
				System.out.println("Nao existem participantes aceitos!");
				return;
			}
			System.out.print("\nDigite o cpf do participante: ");
			l.nextLine();
			String id;
			do {
				try {
					id = l.nextLine();
					if(Validador.validaCpf(id)!=null) {
						
						for(Membro i: Cbpoo.getInst().getParticipantes()) {
							if((i.getCpf().equals(id))) {
								i.setCertificado(this.modeloCertif(i.getNome()));
								System.out.println("\n-->Certificado emitido!");
								return;
							}	
						}
						System.out.println("\nParticipante nao encontrado!");
						return;
					}
				}catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}
			}while(true);
			
	}
//*****************************
	private String modeloCertif(String nome) {
		
		String certificado = ""+
		"CERTIFICO QUE " + nome.toUpperCase() +"\nparticipou do "+
		"Congresso Brasileiro de Programacao Orientada a Objetos (CBPOO)\n";
		
		return certificado;
	}
		
	//***********************************************
	
	
	
	
	
	
	
}
