class ZumbiGigante extends Zumbi {
    public ZumbiGigante(int x, int y) {
        super(3, x, y);
    }

    @Override
    public void mover(int x, int y) {
        // O zumbi gigante não se move
        System.out.println("ZumbiGigante não se move.");
    }

    @Override
    public void atacar(Jogador jogador) {
        jogador.receberDano(2);
    }

    @Override
    public boolean validarAtaque(Arma arma) {
        // Só vai ser válido se o jogador usar uma arma (não pode ser mãos nuas)
        return arma != null;
    }

    // Mensagem de erro
    public String getMensagemErro() {
        return "O zumbi gigante só pode ser atingido com uma arma!";
    }
}
