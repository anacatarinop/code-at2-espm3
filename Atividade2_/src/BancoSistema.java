import java.util.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

public class BancoSistema {

	private BancoCaixa[] caixas;

	private Queue<BancoOperacao> operacoesQueue;
	private int[] codigoQuantidadeMapa;
	private int clientesAtendidos;
	private int cronometro;

	public BancoSistema() {
		this.caixas = new BancoCaixa[3];
		for (int i = 0; i < caixas.length; i++) {
			caixas[i] = new BancoCaixa(0);
		}
		this.operacoesQueue = new LinkedList<>();
		this.codigoQuantidadeMapa = new int[8];
		this.clientesAtendidos = 0;
		this.cronometro = 0;
	}

	// Método que inicia a simulação
	public void iniciar() {
		int escolha = JOptionPane.showOptionDialog(null, "Deseja iniciar a simulação?", "Agência Bancária",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Iniciar", "Sair" },
				"Iniciar");

		if (escolha == 0) {
			int tempoLimite = obterInt("Digite o tempo em segundos para a simulação:", 0);
			int numeroOperacoes = obterInt("Digite o número de operações a serem realizadas:", 0);

			preencherFila(numeroOperacoes);
			
			simular(tempoLimite);
			
			exibirRelatorio();
			
			JOptionPane.showMessageDialog(null, "Relatório Finalizado.", "Agência Bancária",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Relatório Finalizado.", "Agência Bancária",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

    // Método que exibe um relatório com o desempenho da agência bancária durante a simulação
	private void exibirRelatorio() {
		String output = " RELATÓRIO DE DESEMPENHO  \n" + "________________________________\n" +

				String.format("Clientes atendidos: %d\n", clientesAtendidos) + "-----------------\n"
				+ String.format("Clientes não atendidos: %d\n", operacoesQueue.size()) + "-----------------\n"
				+ String.format("Tempo médio de espera na fila: %.2fs\n", (double) cronometro / clientesAtendidos)
				+ "-----------------\n" + "Operações realizadas:\n";
		for (int codigo = 0; codigo < codigoQuantidadeMapa.length; codigo++) {
			BancoOperacao operacao = new BancoOperacao(codigo);
			output += String.format("%s: %d\n", operacao.getDescricao(), codigoQuantidadeMapa[codigo]);
		}
		output += "________________________________\n";
		showMessageDialog(null, output, "Relatório de desempenho", INFORMATION_MESSAGE);
	}

	// Método que realiza a simulação
	private void simular(int tempoLimite) {
		while (cronometro <= tempoLimite && !operacoesQueue.isEmpty()) {
			BancoOperacao operacao = operacoesQueue.peek();
			if (operacao != null && operacao.getDuracao() + cronometro <= tempoLimite) {
				for (int i = 0; i < caixas.length; i++) {
					BancoCaixa caixa = caixas[i];
					if (caixa.getTempoLiberacao() <= cronometro) {
						operacao = operacoesQueue.remove();
						caixa.atualizarTempoLiberacao(operacao.getDuracao());
						codigoQuantidadeMapa[operacao.getCodigo()]++;
						clientesAtendidos++;
						break;
					}
				}
			} else {
				break;
			}
			cronometro++;
		}
	}
	
	// Método que preenche a fila de operações com operações aleatórias
	private void preencherFila(int numeroOperacoes) {
		String[] operacoes = { "Saldo", "Saque", "Aplicação", "Extrato semanal", "Extrato mensal",
				"Pagamento de conta com dinheiro", "Pagamento de conta com cheque", "Pagamento de conta com saque" };
		Random r = new Random();

		for (int i = 0; i < numeroOperacoes; i++) {
			int codigoOperacao = r.nextInt(operacoes.length);
			operacoesQueue.add(new BancoOperacao(codigoOperacao));
		}
	}
	
	// Método que solicita um número inteiro ao usuário
	private int obterInt(String mensagem, int min) {
		String userInput = showInputDialog(null, mensagem, "BancoSimulacao", QUESTION_MESSAGE);
		int parsedInput = Integer.parseInt(userInput);

		if (parsedInput <= min) {
			showMessageDialog(null, String.format("Digite um valor maior que %d!", min), "BancoSimulacao",
					ERROR_MESSAGE);
		}
		return parsedInput;

	}
}