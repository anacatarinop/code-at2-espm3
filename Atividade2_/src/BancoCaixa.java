public class BancoCaixa {
    
    private int tempoLiberacao;

    public BancoCaixa(int tempoLiberacao) {
        this.tempoLiberacao = tempoLiberacao;
    }

    // Método que retorna o tempo de liberação do caixa
    public int getTempoLiberacao() {
        return tempoLiberacao;
    }
    
    // Método que atualiza o tempo de liberação do caixa de acordo com o tempo de serviço da operação
    public void atualizarTempoLiberacao(int tempoServico) {
        tempoLiberacao += tempoServico;
    }
}
