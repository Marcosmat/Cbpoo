package cbpoo;

import java.util.InputMismatchException;
import java.util.Scanner;

import interfaces.MenuLogin;
import interfaces.ParticipanteInterface;
import validadores.Validador;

public class Participante extends Membro implements ParticipanteInterface {
	

	
	
	@Override
	public void menu() {
		
		this.boasVindas();
		
		
	}

	//**************************************************
	/*@Override
	public void mostrarDadosParticipante() {
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
		
		
		System.out.println("Membro: %s");
	}*/
//******************************************************************

//**************************************

}
