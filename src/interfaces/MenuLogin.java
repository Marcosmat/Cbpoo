package interfaces;

public enum MenuLogin {

	A  ("Listar artigos aceitos"),
	B ( "Listar artigos negados"),
	C ("Visualizar dados de artigo"),
	D ("Listar participantes"),
	E ("Validar/invalidar inscricao de participante"),
	F ("Emitir certificado para participante"),
	G ("Submeter artigo"),
	H ("Enviar avaliacao de artigo"),
	I ("Ver avaliacoes de artigo"),
	J ("Aceitar/rejeitar Artigo"),
	K ("Sair");
	
	private String valor;

	MenuLogin(String string) {
		this.valor = string;
	}

	public String getValor() {
		return valor;
	}

}
