import java.util.Random;

class ZumbiRastejante extends Zumbi {
    public ZumbiRastejante(int x, int y) {
        super(1, x, y, 2, 1);
    }

    @Override
    public boolean validarAtaque(Arma arma) {
        // Sem restrições
        return true;
    }
}
