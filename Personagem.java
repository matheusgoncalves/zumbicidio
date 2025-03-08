public abstract class Personagem {
    private int saude;
    private int x, y;

    public Personagem(int saude, int x, int y) {
        this.saude = saude;
        this.x = x;
        this.y = y;
    }

    public abstract void mover(int novoX, int novoY);
    
    public boolean estaVivo() {
        return saude > 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
