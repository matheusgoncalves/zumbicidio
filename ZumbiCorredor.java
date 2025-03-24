import java.util.Random;

class ZumbiCorredor extends Zumbi {
    public ZumbiCorredor(int x, int y) {
        super(2, x, y, 2, 2);
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
