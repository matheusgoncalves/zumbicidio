public abstract class Personagem {
    protected int saude;
    protected int posicaoX;
    protected int posicaoY;

    public Personagem(int saude, int x, int y) {
        this.saude = saude;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public boolean estaVivo() {
        return saude > 0;
    }

    public abstract void mover(int novoX, int novoY);

    public int getSaude() {
        return saude;
    }

    public int getX() {
        return posicaoX;
    }

    public int getY() {
        return posicaoY;
    }
}
