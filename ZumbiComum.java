import java.util.Random;

class ZumbiComum extends Zumbi {
    public ZumbiComum(int x, int y) {
        super(2, x, y);
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

        System.out.println("ZumbiComum se moveu para (" + x + ", " + y + ")");
    }
}
