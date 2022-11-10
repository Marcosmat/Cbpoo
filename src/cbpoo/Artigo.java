package cbpoo;
import java.util.HashSet;
import java.util.Date;
import java.util.HashMap;

import especialistas.Autor;

import java.util.ArrayList;
import java.util.TreeSet;

public class Artigo implements Comparable<Artigo>{
	
	private static int id = 1000;
	private String titulo;
	private ArrayList<String> abst;  //um apos outro obrigatoriamente
	private HashSet<String> palavrasChave; //não podem haver repetições
	private ArrayList<Autor> autores;  //não podem haver repetições
	private int qtdPaginas;
	private String dataSub;
	private HashMap<String, ArrayList<String>> avaliacoes;
	
	public Artigo() {
		
		incremId();
		
		palavrasChave = new HashSet<String>() ;
		autores = new ArrayList<Autor>();
		abst = new ArrayList<String>();
		avaliacoes = new HashMap<String, ArrayList<String>>();
		
	}
	//*************************************************
	@Override
	public int compareTo(Artigo a) {                 //metodo p/ comparação
		
		return this.getTitulo().compareTo(a.getTitulo());
	}
	
//***************************************
	public static int getId() {              //ID ARTIGO
		return id;
	}

	public static void incremId() {
		Artigo.id++;
	}
//******************************************
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {                 //TITULO
		this.titulo = titulo;
	}
//***************************************
	public ArrayList<String> getAbst() {                       //ABSTRACT
		return this.abst;
	}
	
	public void setAbst(String i) { // no main condição de parada "   " 3 espaços
		abst.add(i);
	}
	//**************************************
	public ArrayList<Autor> getAutores() {
		return this.autores;
	}
	public void setAutor(Autor x) {                     //AUTORES
		autores.add(x);
	}
	//****************************************
	public HashSet<String> getPalavrasChave() {
		return this.palavrasChave;
	}
	public void setPalavraChave(String palavraChave) {     //PALAVRAS CHAVE
		palavrasChave.add(palavraChave);
	}
	//******************************************
	public int getQtdPaginas() {
		return this.qtdPaginas;
	}
	public void setQtdPaginas(int qtdPaginas) {           //PAGINAS
		this.qtdPaginas = qtdPaginas;
	}
	//****************************************
	public String getDataSub() {
		return this.dataSub;
	}
	public void setDataSub() {           //DATA SUBMISSAO
		Date data = new Date();
		this.dataSub = data.toString();
	}
	
//********************************************
	public HashMap<String, ArrayList<String>> getAvaliacoes() { //retorna avaliacoes
		return this.avaliacoes;
	}
//*****************************************
	
	public void InsereAvaliacao(String cpf, ArrayList<String> av) { //inclui avaliacao com o cpf do revisor
		
		this.getAvaliacoes().put(cpf, av);
	}
	
	//******************************************
	public void mostrarDadosArtigo() {
		
		
		System.out.println("\nDADOS DO ARTIGO:");
		System.out.println("----------------\n");
		System.out.printf("Codigo: %d\n", this.getId());
		System.out.printf("Titulo: %s\n", this.getTitulo());
		System.out.println("\nAbstract:");
	
		for(String i: getAbst()) 
			System.out.printf("%s\n", i);
		
		System.out.println("\nPalavras-chave:");
		for(String i: getPalavrasChave()) 
			System.out.printf("- %s\n",i);
		
		System.out.println("\nAutores: ");
		for(Autor i: getAutores()) 
			System.out.printf("- %s\n",i.getNome());
		
		System.out.printf("\nNumero de paginas: %d\n", getQtdPaginas());
		System.out.printf("\nData de submissao do artigo: %s\n", getDataSub());	
		
	}
	
	
}
