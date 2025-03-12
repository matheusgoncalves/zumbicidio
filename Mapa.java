public class Mapa {
    private static int heroiX;
    private static int heroiY;
    private static String[][] mapa1;

    public Mapa(String[][] mapaInicial, int xInicial, int yInicial) {
        this.mapa1 = mapaInicial;
        this.heroiX = xInicial;
        this.heroiY = yInicial;
    }

    public void moverHeroi(int novoX, int novoY) {
        int xFinal = heroiX;
        int yFinal = heroiY;

        if ((heroiX - novoX == -1 || heroiX - novoX == 1) && heroiY == novoY) {
            xFinal = novoX;
        }

        if ((heroiY - novoY == -1 || heroiY - novoY == 1) && heroiX == novoX) {
            yFinal = novoY;
        }

        if (xFinal >= 0 && xFinal < 10 && yFinal >= 0 && yFinal < 10) {
            if (!mapa1[xFinal][yFinal].equals("p")) {
                heroiX = xFinal;
                heroiY = yFinal;
            }
        }
    }

    public static int getHeroiX() { return heroiX; }
    public static int getHeroiY() { return heroiY; }
    public static String getCelula(int x, int y) { return mapa1[x][y]; }
}