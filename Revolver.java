class Revolver extends Arma {
    private int municao;

    public Revolver() {
        super("Revólver", 2);
        this.municao = 1;
    }

    public boolean atirar(Zumbi zumbi) {
        if (municao > 0) {
            municao--;
            System.out.println("Disparo realizado! Munição restante: " + municao);
            //computar dano no zumbi
            if (zumbi.getClass() == ZumbiCorredor.class){
                System.out.println("Disparo realizado! Munição restante: " + municao);
                System.out.println("O Zumbi é muito rapido! Você errou o disparo.");
            }
            else{
                zumbi.receberDano(2);
            }
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
