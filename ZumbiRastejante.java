import java.util.Random;

class ZumbiRastejante extends Zumbi {
    public ZumbiRastejante(int x, int y) {
        super(1, x, y);
    }

    @Override
    public void mover(int x, int y) {
        Random random = new Random();

        int direction = random.nextInt(4);

        switch (direction) {
            case 0: this.x += 1; break; // Move para a direita
            case 1: this.x -= 1; break; // Move para a esquerda
            case 2: this.y += 1; break; // Move para cima
            case 3: this.y -= 1; break; // Move para baixo
        }

        System.out.println("ZumbiRastejante se moveu para (" + x + ", " + y + ")");
    }

    @Override
    public boolean validarAtaque(Arma arma) {
        // Sem restrições
        return true;
    }
}
