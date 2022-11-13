package cbpoo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import especialistas.Autor;
import especialistas.Revisor;
import organizacao.GeneralChair;
import organizacao.ProgramChair;
import validadores.Validador;

public class Cbpoo {
	
	private static Cbpoo instance;             //Classe do congresso

	private HashSet<Especialista> especialistas;
	private TreeSet<Membro> participantes;
	private TreeSet<Artigo> artigosAceitos;
	private TreeSet<Artigo> artigosNegados;
	private ArrayList<Membro> pendentes;
	private ArrayList<Artigo> artPendentes;
	
	private GeneralChair gc;
	private ProgramChair pc;
	
	private Cbpoo() {
		especialistas = new HashSet<Especialista>();
		participantes = new TreeSet<Membro>();
		artigosAceitos = new TreeSet<Artigo> ();
		artigosNegados = new TreeSet<Artigo> ();
		pendentes = new ArrayList<Membro>();
		artPendentes = new ArrayList<Artigo>();
		
		//**************************************************************
		gc = new GeneralChair();
		gc.setCpf("12345678978");              //cpf: 1 2 3 4 5 6 7 8 9 7 8 
		gc.setNome("General Chair");          //login do General Chair           //Autoriza inscricoes   
		gc.setSenha("12345");                   
		//****************************************************************
		
		pc = new ProgramChair();
		pc.setCpf("12345678998");          //cpf: 1 2 3 4 5 6 7 8 9 9 8  
		pc.setNome("Program Chair");    //login do Program Chair                //Aceita ou recusa artigos
		pc.setSenha("12345");
		
	}
	
	public static Cbpoo getInst() {           //Instancia Singleton
		if(instance == null) {
			instance = new Cbpoo();
		}
		return instance;
	}
	
	public void inscreverParticipante() {
		
		GeneralChair g = new GeneralChair();
		
		
	}
	
//*********************************************************************	
	public TreeSet<Artigo> getArtigosAceitos() {
		return artigosAceitos;                        //artigos aceitos
	}

	public void inserirArtigoAceito(Artigo a) {
		artigosAceitos.add(a);
	}
	//****************************************************
	public TreeSet<Artigo> getArtigosNegados() {
		return artigosNegados;
	}                                                 //artigos negados

	public void inserirArtigoNegado(Artigo a) {
		artigosAceitos.add(a);
	}
	//****************************************************
	public ArrayList<Artigo> getArtPendentes() {

		return artPendentes;
	}
                                              //artigos pendentes
	public void incArtPendente(Artigo a) {
		this.getArtPendentes().add(a);
	}
	//**********************************************
	
	//************************************************
	public Membro buscarMembro(String cpf, String senha) {      //Buscar membro a cada acesso ao login
		
		if((cpf.equals("12345678978")) && (senha.equals(this.gc.getSenha()))) {
			gc.boasVindas();
			gc.menuGeneralChair();;
			return gc;
		}
		if((cpf.equals("12345678998")) && (senha.equals(this.pc.getSenha()))) {
			pc.boasVindas();
			pc.menuProgramChair();;
			return gc;
		}
		
		for(Membro i: getPendentes()) {
			if(i.getCpf().equals(cpf)) {
				System.out.println("CADASTRO EM ANALISE. AGUARDE VALIDACAO!");
				return (Participante)i;
			}
		}
		
		for(Membro i: getParticipantes()) {
			if((i.getCpf().equals(cpf)) && (i.getSenha().equals(senha))) {
				 
					if(i instanceof Participante) {
						i.boasVindas();
						i.menuParticipanteComum();
					}
						
					else if(i instanceof Autor) {
						i.boasVindas();
						i.menuAutor();
					}
						
					else if(i instanceof Revisor) {
						i.boasVindas();
						i.menuRevisor();
					}
						
					
				 return i;
			}
		}


		
		return null;
		
	}
//******************************************************************
	public Membro buscaParticipante(String cpf) {                 // busca por cpf
		
		for(Membro i: getParticipantes()) {
			if((i.getCpf().equals(cpf))) 
				 return (Participante)i;
		}
		
		return null;
	}
	
	
	//*************************************************
	public void incluirArtigo(Artigo a) {
		
		if(a.getAutores().size()>=1) {
			
		}
		
	}
//*************************************************************
	public ArrayList<Membro> getPendentes() {
		return pendentes;
	}

	public void incPendentes(Membro p) {
		this.getPendentes().add(p);
	}

	
	//************************************************
	
	

	public TreeSet<Membro> getParticipantes() {  //retorna lista de participantes
		return this.participantes;
	}
//*********************************************************

	public void incParticipante(Participante p) {   //insere participante
		getParticipantes().add(p);
	}
//*************************************************************

	public HashSet<Especialista> getEspecialistas() {     //retorna lista de especialistas
		return especialistas;
	}

//************************************************************
	public void incEspecialista(Especialista p) {   //insere especialista
		getEspecialistas().add(p);
	}
//***********************************************************************
	
	public Pessoa cadastro(String id) {             //Realiza cadastro de um participante
		
		Scanner l = new Scanner (System.in);
		
		System.out.println("Qual tipo de participante voce quer ser? Escolha uma opcao:");
		System.out.println("\n1- Comum (apenas assistir palestras)");
		System.out.println("2- Autor de artigos");
		System.out.println("3- Revisor de artigos");
		
		String r;
		Membro p;
		
		System.out.print("\n-->");
		do {
				r = l.nextLine();
				if(r.equals("1")) {
					System.out.println("PARTICIPANTE COMUM (APENAS ASSISTIR PALESTRAS)");
					p = new Participante();
					p.setCpf(id);
					break;
					
				}else if(r.equals("2")) {
					System.out.println("AUTOR DE ARTIGOS");
					p = new Autor();
					p.setCpf(id);
					break;
				}
				else if(r.equals("3")) {
					System.out.println("REVISOR DE ARTIGOS");
					p = new Revisor();
					p.setCpf(id);
					break;
				}
				else
					System.out.println("\nDigite uma opcao valida: ");
				
		}while(true);
		//*****************************************
		String nome;
		System.out.println("Digite seu nome:");
	
		do {
			try {
				
				nome = l.nextLine();
				if(Validador.verificaNome(nome)) {
					p.setNome(nome);
					break;
				}
				
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
		
		String d;
		System.out.print("\nDigite seu nascimento no formato ddmmaaaa, apenas numeros:\n");
		do {
			try {
				
				d = l.nextLine();
				if(Validador.dataNasc(d)!=null) {
					p.setDataNasc(d);
					break;
				}

				
			}catch(RuntimeException e){
				System.out.println(e.getMessage());
			}

			
		}while(true);
		
		//********************************************************
		System.out.println("\nTitulo academico:\n");
		System.out.println("1- Tecnologo");
		System.out.println("2- Bacharelado");
		System.out.println("3- Licenciatura");
		System.out.println("4- Especializacao");
		System.out.println("5- Mestrado");
		System.out.println("6- Doutorado");
		System.out.println("7- Postdoc");
		System.out.println("8- nenhum");
		
		String resp;
		String tit = null;
		do {
			try {
				System.out.print("Digite uma opcao entre 1 e 8: ");
				resp = l.nextLine();

					p.setTitAcad(Validador.titulos[Validador.titulo(resp)]);
					break;

			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
		//*********************************************
		String inst;
		do {
			try {
				System.out.print("\nDigite sua instituicao de vinculo:\n");
				inst = l.nextLine();
				
				if(Validador.verificaNome(inst)) {
					p.setInstVinc(inst);
					break;
				}
					
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
		}while(true);
		//*********************************************************
		
		String senha;
		do {
			try {
				System.out.printf("Digite uma senha entre 4 e 20 caracteres:\n");
				senha = l.nextLine();
				
				if(Validador.verificaSenha(senha)) {
					p.setSenha(senha);
					break;
				}
					
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
		}while(true);
		//**********************************
			
			System.out.printf("(1+ Enter) PARA FINALIZAR CADASTRO E AGUARDAR VALIDACAO-> ");
			int f;
			
			do {
				try {
					f = l.nextInt();
					if(f == 1) {
						System.out.print("CADASTRO ENVIADO PARA AVALIACAO!!! (Enter p/ sair)");
						l.nextLine();
						break;
					}else {
						System.out.printf("\n(1+ Enter)-> " );
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Digite uma entrada valida: ");
				}
			}while(true);
	
			this.incPendentes(p);
			return p;
		
		
		}
	
	
//*********************************************************************************	

	
}
