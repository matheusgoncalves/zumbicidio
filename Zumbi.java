import java.util.Random;

public abstract class Zumbi extends Personagem {
    protected int x, y;
    protected int dano;
    protected int movimento;

    public Zumbi(int saude, int x, int y, int dano, int movimento) {
        super(saude, x, y);
        this.dano = dano;
        this.movimento = movimento;
    }

    @Override
    public void mover(int x, int y) {
        Random random = new Random();

        int direction = random.nextInt(4);

        switch (direction) {
            case 0: this.x += this.getMovimento(); break; // Move para a direita
            case 1: this.x -= this.getMovimento(); break; // Move para a esquerda
            case 2: this.y += this.getMovimento(); break; // Move para cima
            case 3: this.y -= this.getMovimento(); break; // Move para baixo
        }

        System.out.println(this.getClass().getSimpleName() + " se moveu para (" + x + ", " + y + ")");
    }

    public  boolean validarAtaque(Arma arma) {
        return true;
    }

    public void atacar(Jogador jogador) {
        jogador.receberDano(this.getDano());
        Mensagem.exibirMensagem("O jogador recebeu " + this.getDano() + " de dano! Saúde atual: " + jogador.saude);
    }

    public void receberDano(int dano) {
        saude -= dano;
        Mensagem.exibirMensagem("Zumbi sofreu " + dano + " de dano!");

        if (saude <= 0) {
            Mensagem.exibirMensagem("Zumbi eliminado!");
            saude = 0;
        }
    }

    public int getDano() {
        return this.dano;
    }

    public int getMovimento() {
        return this.movimento;
    }

    public String getMensagemErro() {
        return "Ataque inválido!";
    }
}
