package cbpoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import especialistas.Autor;
import especialistas.Revisor;
import interfaces.MenuLogin;
import validadores.Validador;
import organizacao.GeneralChair;
import organizacao.ProgramChair;

public abstract class  Membro extends Pessoa implements Comparable<Membro> {    //classe comum a todos depois de Pessoa,
	                                                                            //implementa rotinas de todos membros
	public static Scanner l = new Scanner(System.in);
	private String certificado;
	
	public void inscrever(Cbpoo c, String id) {
		
		c.cadastro(id);
		
	}

	@Override
	public int compareTo(Membro m) {
		
		return this.getNome().compareTo(m.getNome());
	}
	//***********************************************
	public void listarArtigoAceitos(Cbpoo c) {
		
		if(c.getArtigosAceitos().isEmpty()) {
			System.out.println("Ainda nao ha artigos aceitos!");
			return;                                                         //todos
		}
		
		int j = 0;
		System.out.println("Lista de artigos aceitos:");
		for(Artigo i: c.getArtigosAceitos()) {
			System.out.printf("%d- %s\n", i.getId(), i.getTitulo());
			j++;
		}
		return;
	}
	//*****************************************************            //todos
	public void listarArtigoNegados(Cbpoo c) {
		
		if(c.getArtigosNegados().isEmpty()) {
			System.out.println("Ainda nao ha artigos negados!");
			return;
		}
		
		int j = 0;
		System.out.println("Lista de artigos negados:");
		for(Artigo i: c.getArtigosNegados()) {
			System.out.printf("Codigo: %d - Titulo: %s\n", i.getId(), i.getTitulo());
			j++;
		}
		
	}
	//************************************************************
	public void listarTodosArtigos(Cbpoo c) {         //lista artigos não negados
		
		if(!c.getArtigosAceitos().isEmpty()) {
			for(Artigo i: c.getArtigosAceitos()) {
				System.out.printf("- %d  - %s\n", i.getId(), i.getTitulo());
			}
		}
		
		if(!c.getArtPendentes().isEmpty()) {
			for(Artigo i: c.getArtPendentes()) {
				System.out.printf("- %d  - %s\n", i.getId(), i.getTitulo());
			}
		}

	}
	//**********************************************************
	public Artigo visualizarDadosArt(Cbpoo c, int id) {
		                                                            //todos

		for(Artigo i: c.getArtigosAceitos()) {
			if(i.getId() == id ) {
				i.mostrarDadosArtigo();
				return i;
			}
		}
			
		
		for(Artigo i: c.getArtigosNegados()) {
			
			if(i.getId() == id ) {
				i.mostrarDadosArtigo();
				return i;
			}
		}
		
		return null;
		
	}
	//***************************************************************
	public void mostrarDadosParticipante() {                          //Organizacao
		
		System.out.printf("CPF: %s\n", 
		 this.getCpf().substring(0, 3) + "." + this.getCpf().substring(3,6) + "." + 
		 this.getCpf().substring(6,9) + "-" + this.getCpf().substring(9,11));
		
		System.out.printf("Nome: %s\n", this.getNome());
		System.out.printf("Data nasc: %s\n",
				this.getDataNasc().substring(0, 2) + "/" + 
				this.getDataNasc().substring(2, 4)+"/"+
				this.getDataNasc().substring(4, 8));
		System.out.printf("Titulo Academico: %s\n", this.getTitAcad());
		System.out.printf("Instituicao de vinculo: %s\n",this.getInstVinc());

	}
	
	//***************************************************
	public void listarParticipantes(Cbpoo c) {
		
		if(c.getParticipantes().isEmpty()) {
			System.out.println("Ainda nao ha participantes aceitos!");          //todos
			return;
		}
		
		System.out.println("Lista de Participantes:");
		for(Membro i: c.getParticipantes()) {
			System.out.printf("- %s\n",i.getNome());
		}
		
	}
//*****************************************************************
	
	public void menuGeral() {                                   //todos

		System.out.println("\n------");
		System.out.printf("MENU:\n-------\n\n");
		System.out.printf("1 - %s\n", MenuLogin.A.getValor());
		System.out.printf("2 - %s\n", MenuLogin.B.getValor());
		System.out.printf("3 - %s\n", MenuLogin.C.getValor());
		System.out.printf("4 - %s\n", MenuLogin.D.getValor());
			
		return;
			
			
	}
	//*************************************************
	public void boasVindas() {
		
		
		System.out.println("-------------------------------------------------------------------");
		System.out.printf("CONGRESSO BRASILEIRO DE PROGRAMACAO ORIENTADA A OBJETOS (CBPOO)\n\n");
		System.out.printf("Bem vindo(a), %s\n", this.getNome());
		
		String f = " ";
		if(this instanceof Participante)
			f = "Participante comum";
		else if(this instanceof Autor)
			f = "Autor de artigos";
		else if(this instanceof Revisor)
			f= "Revisor de artigos";
		else if(this instanceof GeneralChair)
			f= "General Chair";
		else if(this instanceof ProgramChair)
			f= "Program Chair";

		System.out.printf("(%s)", f);
		
	}
	//*************************************************
	
	public void leituraMenuGeral(int opc) {        //todos
		
		if(opc > 4)
			return;
		
		switch (opc) {
		case 1:
			this.listarArtigoAceitos(Cbpoo.getInst());
			break;
		case 2:
			this.listarArtigoNegados(Cbpoo.getInst());
			break;
		case 3:
			if((Cbpoo.getInst().getArtigosAceitos().isEmpty()) && (Cbpoo.getInst().getArtigosNegados().isEmpty())) {
				System.out.println("Ainda nao ha artigos aceitos ou negados.");
				break;
			}
			do {
				try {
					System.out.print("Digite o codigo do artigo desejado: ");
					String resp = l.next();
					if(Validador.apenasNumeros(resp)!=null) {
						if(this.buscarArtigo(resp) == null) {
							System.out.print("Artigo nao encontrado!");
							break;
						}else break;
					}
	
				}catch(RuntimeException e) {
					System.out.print(e.getMessage());
				}
			}while(true);
			
			break;
		case 4:
			this.listarParticipantes(Cbpoo.getInst());
			
			break;

		}
	}
	//******************************************************

	
	//************************************************
	public Artigo buscarArtigo(String codigo) {  // todos
		
		System.out.println("Lista de artigos\n");
		
		if(Validador.apenasNumeros(codigo)!=null) {
			
			int v = Integer.parseInt(codigo);
			
			for(Artigo i: Cbpoo.getInst().getArtigosAceitos()) {
				if(i.getId() == v) {
					i.mostrarDadosArtigo();
					return i;
				}		
			}
			
			for(Artigo i: Cbpoo.getInst().getArtigosNegados()) {
				if(i.getId() == v) {
					i.mostrarDadosArtigo();
					return i;
				}
			}
		}
		return null;
		
	}

	//******************************************************
	
	
	public void menuParticipanteComum() {
		
		boolean sair = false;
		while(!sair) {
			
			this.menuGeral();
			System.out.println("5 - Visualizar Certificado");
			System.out.printf("6 - %s\n", MenuLogin.K.getValor());
			System.out.print("--> ");
			
			do {
				try {
					int resp = l.nextInt();
					if((resp > 0) && (resp < 5)) {
						this.leituraMenuGeral(resp);
						break;
					}else if(resp == 5) {
						if(((Participante)this).getCertificado()!=null) {
							System.out.println(((Participante)this).getCertificado());
						}
						break;

					}else if(resp == 6) {
						sair = true;
						System.out.println("\nSaindo... (Enter)");
						break;
					}
						
					else
						System.out.println("Digite uma opcao valida: ");
						
					
				}catch(InputMismatchException e) {
					System.out.print("\nDigite uma entrada valida: ");
				}
			}while(true);
			
		}
		
	}
	//************************
	public void menuAutor() {
		
		
		boolean sair = false;
		while(!sair) {
		
			this.menuGeral();
		
			System.out.printf("5 - %s\n", MenuLogin.G.getValor());
			System.out.printf("6 - %s\n", MenuLogin.I.getValor());
			System.out.printf("7 - %s\n", MenuLogin.K.getValor());
			System.out.print("--> ");
		
			do {
				try {
					int resp = l.nextInt();
					if((resp > 0) && (resp < 5)) {
						this.leituraMenuGeral(resp);
						break;
					}else if(resp == 5) {
						((Autor)this).submeterArtigo();
						break;
					}else if(resp == 6) {
						((Autor)this).verAvaliacoesArtigo();
						break;
					}else if(resp == 7 ) {
						sair = true;
						System.out.println("\nSaindo... (Enter)");
						break;
					}
						
					else
						System.out.println("Digite uma opcao valida: ");
					
				
				}catch(InputMismatchException e) {
					System.out.print("\nDigite uma entrada valida: ");
				}
			}while(true);
		}
	}
	//*********************
	public void menuRevisor() {
		
		boolean sair = false;
		while(!sair) {
			this.menuGeral();
			
			System.out.printf("5 - %s\n", MenuLogin.H.getValor());
			System.out.printf("6 - %s\n", MenuLogin.I.getValor());
			System.out.printf("7 - %s\n", MenuLogin.K.getValor());
			System.out.print("--> ");
			
			do {
				try {
					int resp = l.nextInt();
					if((resp > 0) && (resp<5)) {
						this.leituraMenuGeral(resp);
						break;
						
					}else if(resp == 5) {
						
						((Revisor)this).enviarAvaliacaoArt();
						break;
					
					}else if(resp == 6) {
						((Revisor)this).visualizarAvaliacoes();
						break;
					
					}else if(resp == 7) {
						sair = true;
						System.out.println("\nSaindo... (Enter)");
						break;
					}
						
					
					else
						System.out.println("Digite uma opcao valida: ");
						
					
				}catch(InputMismatchException e) {
					System.out.print("\nDigite uma entrada valida: ");
				}
			}while(true);
		}
		
	}
	//*******************************************
	public void menuGeneralChair() {
		boolean sair = false;
		while(!sair) {
			this.menuGeral();
		
			System.out.printf("5 - %s(%d novos!)\n", MenuLogin.E.getValor(), Cbpoo.getInst().getPendentes().size());
			System.out.printf("6 - %s\n", MenuLogin.F.getValor());
			System.out.printf("7 - %s\n", MenuLogin.K.getValor());
			System.out.print("--> ");
		
			do {
				try {
					int resp = l.nextInt();
					if(resp > 0 && resp<5) {
						this.leituraMenuGeral(resp);
						break;
					}else if((resp>4) && (resp<8)) {
					
						switch(resp) {
						case 5:
							((GeneralChair)this).validarOuInvInscricao();
							break;
						case 6:
							((GeneralChair)this).emitirCertificado();
							break;
						case 7:
							sair = true;
							System.out.print("\nSaindo... (Enter)");
							break;
						}
						break;
					}
				
					else
						System.out.print("\nDigite um opcao valida: ");

				
			}catch(InputMismatchException e) {
					System.out.print("\nDigite uma entrada valida: ");
			}
			l.nextLine();
			}while(true);
		}
	}
	//*******************************************************
	public void menuProgramChair() {
		
		boolean sair = false;
		while(!sair) {
			this.menuGeral();
			
			System.out.printf("5 - %s\n", MenuLogin.I.getValor());
			System.out.printf("6 - %s (%d novos!)\n", MenuLogin.J.getValor(), Cbpoo.getInst().getArtPendentes().size());
			System.out.printf("7 - %s\n", MenuLogin.K.getValor());
			System.out.print("--> ");
			
			do {
				try {
					int resp = l.nextInt();
					if((resp > 0) && (resp<5)) {
						this.leituraMenuGeral(resp);
						break;
						
					}else if(resp == 5) {
						
						((ProgramChair)this).verAvaliacaoDeArtigoQualquer(); 
						break;
					
					}else if(resp == 6) {
						((ProgramChair)this).aceitarOuNegarArtigo();
						break;
					
					}else if(resp == 7) {
						sair = true;
						System.out.println("\nSaindo... (Enter)");
						break;
					}
						
					
					else
						System.out.println("Digite uma opcao valida: ");
						
					
				}catch(InputMismatchException e) {
					System.out.print("\nDigite uma entrada valida: ");
				}
			}while(true);
		}
		
	}
	//****************************************************
	
	public String getCertificado() {
		if(this.certificado == null) {
			System.out.println("Voce ainda nao possui certificado. Aguarde.");
			return null;
		}
		return certificado;
	}
	//*********
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	
	//***************************************************
	public void visualizaAvAutor(Artigo i, int cod, String cpf) {
		
		if(i.getId() == cod) {
			
			System.out.printf("%d - %s, avaliacoes:\n", i.getId(), i.getTitulo());
			if(i.getAvaliacoes().isEmpty()) {
				System.out.println("Ainda nao ha avaliacoes para este artigo!");
				return;
			}
			else {
				if(buscaAutorDeArtigo(i.getAutores(), cpf)==false) {
					System.out.println("As avaliacoes deste artigo estao restritas ao autor!");
					return;
				}else {
					int ind = 0;
					System.out.println("Avaliacoes:");
					for(ArrayList<String> j: i.getAvaliacoes().values()) {
						System.out.printf("- %s\n", j.get(ind));
						ind++;
					}
					return;
				}

			}
		}else return;
		
		
	}
	//*********************************************************
	public void visualizaAvRevisor(Artigo i, int cod, String cpf) {
		
		if(i.getId() == cod) {
			
			System.out.printf("%d - %s, avaliacoes:\n", i.getId(), i.getTitulo());
			if(i.getAvaliacoes().isEmpty()) {
				System.out.println("Ainda nao ha avaliacoes para este artigo!");
				return;
			}
			else {
				
				if(buscaRevisorDeArtigo(i.getAvaliacoes(), cpf)==false) {
					System.out.println("Voce nao avaliou este artigo!");
					return;
				}else {
					int ind = 0;
					for(ArrayList<String> j: i.getAvaliacoes().values()) {
						System.out.printf("- %s\n", j.get(ind));
						ind++;
					}
					return;
				}

			}
		}else return;
		
	}
	//*************************************************************
	public Artigo visualizaAvProgramChair(Artigo i, int cod) {
		

		if(i.getId() == cod) {
			
			System.out.printf("%d - %s, avaliacoes:\n", i.getId(), i.getTitulo());
			if(i.getAvaliacoes().isEmpty()) {
				System.out.println("Ainda nao ha avaliacoes para este artigo!");
				return null;
			}
			else {
				int ind = 0;
				for(ArrayList<String> j: i.getAvaliacoes().values()) {
					System.out.printf("- %s\n", j.get(ind));
					ind++;
				}
				return i;

			}
		}else return null;
		
		
		
	}
	//******************************
	public boolean buscaAutorDeArtigo(ArrayList<Autor> l, String cpf) {
		
		for(Autor i: l) {
			if(i.getCpf().equals(cpf))
				return true;
		}
		return false;
		
	}
	//**************************************************
	public boolean buscaRevisorDeArtigo(HashMap<String, ArrayList<String>> l, String cpf) {
		
		for(String j: l.keySet()) {
			if(j.equals(cpf))
				return true;
		}
		
		return false;
		
	}
	//****************************************************
	

}
