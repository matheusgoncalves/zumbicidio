import java.util.Random;

public class Jogador extends Personagem {
    private int percepcao;
    private int municao;

    public Jogador(int x, int y, int dificuldade) {
        super(5, x, y);
        this.percepcao = dificuldade;
        this.municao = 0;
    }

    public void atacar(Zumbi zumbi, Arma arma) {
        if (arma != null) {
            if (arma.getTipo().equals("armaDeFogo")) {
                if (municao > 0) {
                    zumbi.receberDano(arma.getDano());
                    municao--;
                    System.out.println("O zumbi recebeu " + arma.getDano() + " de dano!");
                } else {
                    System.out.println("Sem munição!");
                }
            } else {
                zumbi.receberDano(arma.getDano());
                System.out.println("O zumbi recebeu " + arma.getDano() + " de dano!");
            }
        } else {
            // Ataque com as mãos
            Random random = new Random();
            int dado = random.nextInt(6) + 1; // Rolando um dado de 6 lados
            int dano = (dado == 6) ? 2 : 1; // Golpe crítico se o dado for 6
            zumbi.receberDano(dano);
            System.out.println("O zumbi recebeu " + dano + " de dano!");
        }
    }

    public boolean esquivar(int dado) {
        return dado <= percepcao;
    }

    public void receberDano(int dano) {
        saude -= dano;
        System.out.println("O jogador recebeu " + dano + " de dano! Saúde atual: " + saude);
    }

    public void recuperarVida(int vida) {
        saude += vida;
        System.out.println("O jogador recuperou " + vida + " de vida! Saúde atual: " + saude);
    }

    public int getPercepcao() {
        return percepcao;
    }
}
