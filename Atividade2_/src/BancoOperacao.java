public class BancoOperacao {
	
	private String descricao;
	private int codigo; // das operações 
	private int duracao; // de cada operação em segundos

	private static final BancoOperacao[] operacoes = {
		new BancoOperacao("Saldo", 10),
		new BancoOperacao("Saque", 20),
		new BancoOperacao("Aplicação", 30),
		new BancoOperacao("Extrato semanal", 40),
		new BancoOperacao("Extrato mensal", 50),
		new BancoOperacao("Pagamento de conta com dinheiro", 60),
		new BancoOperacao("Pagamento de conta com cheque", 70),
		new BancoOperacao("Pagamento de conta com saque", 80)
	};
    
    // Construtor da classe, inicializa os atributos de acordo com o código da operação
	public BancoOperacao(int codigo) {
		if (codigo >= 0 && codigo < operacoes.length) {
			this.descricao = operacoes[codigo].getDescricao();
			this.duracao = operacoes[codigo].getDuracao();
		} else {
			this.descricao = "Operação inválida";
			this.duracao = 0;
		}
		this.codigo = codigo;
	}

	public BancoOperacao(String descricao, int duracao) {
		this.descricao = descricao;
		this.duracao = duracao;
	}
    
	// Método que retorna a descrição da operação
	public String getDescricao() {
		return descricao;
	}
	
	// Método que retorna o código da operação
	public int getCodigo() {
		return codigo;
	}
    
	// Método que retorna a duração da operação em segundos
	public int getDuracao() {
		return duracao;
	}
}
