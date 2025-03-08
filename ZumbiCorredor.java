import java.util.Random;

class ZumbiCorredor extends Zumbi {
    public ZumbiCorredor(int x, int y) {
        super(2, x, y);
    }

    @Override
    public void mover(int x, int y) {
        Random random = new Random();

        int direction = random.nextInt(4);

        switch (direction) {
            case 0: this.x += 2; break; // Move para a direita
            case 1: this.x -= 2; break; // Move para a esquerda
            case 2: this.y += 2; break; // Move para cima
            case 3: this.y -= 2; break; // Move para baixo
        }

        System.out.println("ZumbiCorredor se moveu para (" + x + ", " + y + ")");
    }

    @Override
    public boolean validarAtaque(Arma arma) {
        // Não pode ser atacado com arma de fogo
        return !(arma instanceof Revolver);
    }

    // Mensagem de erro
    public String getMensagemErro() {
        return "O zumbi corredor é imune a armas de fogo!";
    }
}
