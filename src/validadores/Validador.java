package validadores;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validador {
	
	
	public static final String titulos[]= {	"Tecnologo", "Bacharelado",
											"Licenciatura", "Especializacao",
											"Mestrado", "Doutorado", "PostDoc", "nenhum"};
	//*********************************************************************************
	public static String dataNasc(String data) {
		
		String d = null;                                       //Classe que verifica alguns dados de entrada
		
		if(data.length()==8) {
			
			if(apenasNumeros(data)==null)
				throw new RuntimeException("Digite apenas numeros (8): ");
			d = "ok";
			
		}else 
			throw new RuntimeException("Erro com numero de digitos. Digite 8 numeros: ");
		
		
		return d;	
	}
	//*********************************************
	public static String validaCpf(String cpf) {
		
		
		if(cpf.length()==11) {
			
			if(apenasNumeros(cpf)==null) 
				throw new RuntimeException("Digite apenas numeros.");
			
		}else throw new RuntimeException("Digitos insuficientes. Digite 11 numeros.");
		
		
		return cpf;	
	}
	//***********************************************
	public static int titulo(String valor) {
		
		char[] v = valor.toCharArray();
		int vlr = Character.getNumericValue(v[0]);
		
		
		if(valor.length()==1 && v[0] != ' ') {
			
			if(Character.isLetter(v[0]))
				throw new RuntimeException("Entrada invalida.");
			
			if((vlr>8) || (vlr<1)) 
				throw new RuntimeException("Opcao invalida.");	
			
			return vlr-1;
		}
		
		else throw new RuntimeException("Entrada invalida.");
			
			
	}
			

	//********************************************
	public static String apenasNumeros(String valor) {
		
		char c[] = valor.toCharArray();
		
		for(int i=0; i<valor.length(); i++) {
			if( ! Character.isDigit(c[i])) 
				return null;
		}
		
		return valor;
		
	}
	//*************************************************
	public static boolean verificaNome(String nome) {
		
		char[] c = nome.toCharArray(); 
		
		if(nome.isBlank()) {
			throw new RuntimeException("\nNome vazio. Digite um nome.");
		}
		
		if(!Character.isLetter(c[0])) {
			throw new RuntimeException("O primeiro caracter do nome tem que ser letra.");
		}

		
		return true;
		
	}
	
	//******************************************************
	public static boolean verificaSenha(String senha) {
		
		char[]s = senha.toCharArray();
		
		if((senha.length()>=4) && (senha.length()<20)) {
			
			for(char i: s) {
				if(i == ' ') {
					throw new RuntimeException("Digite uma senha sem espacos.");
				}
			}		
			
		}else throw new RuntimeException("Tamanho da senha incorreto.");

		return true;
	}
	//********************************
	public String[] tits() {
		return titulos;
	}
	
	
	
	
	
	
	
	

}
