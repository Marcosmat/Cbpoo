package cbpoo;

import interfaces.ParticipanteInterface;

public class Participante extends Membro implements ParticipanteInterface {
	
	@Override
	public void menu() {
		
		this.boasVindas();	
		
	}

}