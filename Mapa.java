public class Mapa {
    private static int heroiX;
    private static int heroiY;
    static String[][] mapa1;

    public Mapa(String[][] mapaInicial, int xInicial, int yInicial) {
        this.mapa1 = mapaInicial;
        this.heroiX = xInicial;
        this.heroiY = yInicial;
    }

    // transformar em um json
    protected static final String[][] MAPA1 = {
            {"", "p", "", "b", "", "", "", "z", "", ""},
            {"", "p", "", "p", "zr", "", "", "b", "", ""},
            {"", "p", "", "p", "", "", "p", "p", "", "z"},
            {"", "p", "", "p", "", "", "p", "p", "", ""},
            {"", "", "b", "p", "", "zc", "p", "p", "zc", ""},
            {"", "p", "p", "p", "", "", "p", "p", "zc", ""},
            {"z", "p", "", "", "", "z", "p", "p", "", ""},
            {"", "p", "", "zr", "", "b", "", "", "", ""},
            {"", "p", "p", "p", "p", "p", "", "", "p", ""},
            {"b", "", "", "z", "", "", "", "", "p", "zg"}
    };

    public static String getCelula(int x, int y) { return mapa1[x][y]; }
}