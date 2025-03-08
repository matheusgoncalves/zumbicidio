class Cura extends Item implements Usavel {
    private int pontosCura;

    public Cura() {
        super("Atadura");
        this.pontosCura = 1;
    }

    @Override
    public void usar(Jogador jogador) {
        jogador.recuperarVida(pontosCura);
        System.out.println("O jogador usou uma atadura e recuperou " + pontosCura + " ponto de vida!");
    }
}
