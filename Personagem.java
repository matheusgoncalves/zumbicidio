public abstract class Personagem {
    protected int saude;
    protected static int heroiX;
    protected static int heroiY;

    public Personagem(int saude, int x, int y) {
        this.saude = saude;
        this.heroiX = x;
        this.heroiY = y;
    }

    public boolean estaVivo() {
        return saude > 0;
    }

    public int getSaude() {
        return saude;
    }
}
