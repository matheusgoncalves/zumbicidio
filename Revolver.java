class Revolver extends Arma {
    private int municao;

    public Revolver() {
        super("Revólver", 2);
        this.municao = 1;
    }

    public boolean atirar() {
        if (municao > 0) {
            municao--;
            Mensagem.exibirMensagem("Disparo realizado! Munição restante: " + municao);
            return true;
        } else {
            Mensagem.exibirMensagem("Sem munição!");
            return false;
        }
    }

    public void adicionarMunicao() {
        municao++;
        Mensagem.exibirMensagem("Munição recarregada! Munição atual: " + municao);
    }

    public int getMunicao() {
        return this.municao;
    }
}
