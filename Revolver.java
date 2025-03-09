class Revolver extends Arma {
    private int municao;

    public Revolver() {
        super("Revólver", 2);
        this.municao = 1;
    }

    public boolean atirar() {
        if (municao > 0) {
            municao--;
            System.out.println("Disparo realizado! Munição restante: " + municao);
            return true;
        } else {
            System.out.println("Sem munição!");
            return false;
        }
    }

    public void adicionarMunicao() {
        municao++;
        System.out.println("Munição recarregada! Munição atual: " + municao);
    }

    public int getMunicao() {
        return this.municao;
    }
}
