public abstract class Zumbi extends Personagem {
    protected int x, y;

    public Zumbi(int saude, int x, int y) {
        super(saude, x, y);
    }

    @Override
    public abstract void mover(int x, int y);

    public  boolean validarAtaque(Arma arma) {
        return true;
    }

    public void atacar(Jogador jogador) {
        jogador.receberDano(1);
    }

    public void receberDano(int dano) {
        saude -= dano;
        System.out.println("Zumbi sofreu " + dano + " de dano!");

        if (saude <= 0) {
            System.out.println("Zumbi eliminado!");
            // Remover zumbi da tela
        }
    }

    public String getMensagemErro() {
        return "Ataque inválido!";
    }
}
