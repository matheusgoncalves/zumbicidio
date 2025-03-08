// Classe para armas
public class Arma extends Item {
    protected int dano;

    public Arma(String nome, int dano) {
        super(nome);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }
}
