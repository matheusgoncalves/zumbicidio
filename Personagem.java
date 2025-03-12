public abstract class Personagem {
    protected int saude;
    protected static int heroiX;
    protected static int heroiY;

    public Personagem(int saude, int x, int y) {
        this.saude = saude;
        this.heroiX = x;
        this.heroiY = y;
    }

    public static void moverHeroi(int novoX, int novoY) {
        int xFinal = heroiX;
        int yFinal = heroiY;

        if ((heroiX - novoX == -1 || heroiX - novoX == 1) && heroiY == novoY) {
            xFinal = novoX;
            Mapa.MAPA1[xFinal][yFinal]  = "h";
        }

        if ((heroiY - novoY == -1 || heroiY - novoY == 1) && heroiX == novoX) {
            yFinal = novoY;
            Mapa.MAPA1[xFinal][yFinal]  = "h";
        }

        if (xFinal >= 0 && xFinal < 10 && yFinal >= 0 && yFinal < 10) {
            if (!Mapa.mapa1[xFinal][yFinal].equals("p")) {
                heroiX = xFinal;
                heroiY = yFinal;
                Mapa.MAPA1[xFinal][yFinal]  = "h";
            }
        }
    }

    public static int getHeroiX() { return heroiX; }
    public static int getHeroiY() { return heroiY; }
    
    public boolean estaVivo() {
        return saude > 0;
    }

    public int getSaude() {
        return saude;
    }
}
