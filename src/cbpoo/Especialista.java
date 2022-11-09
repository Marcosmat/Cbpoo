package cbpoo;
import java.util.HashSet;

public class Especialista extends Membro {
	
	
	public Especialista() {
		
	}
	
	public Especialista(String n) {
		this.setNome(n);
	}
	
	protected String especialidade;
	public HashSet<Especialista> listaDeEspecialista;

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void menu() {
		// TODO Auto-generated method stub
		
	}
	

}
