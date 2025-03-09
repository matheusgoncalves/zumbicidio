public class Bau {
    private Item conteudo;
    private boolean aberto;

    public Bau(int numero) {
        this.aberto = false;
        this.conteudo = definirConteudoFixo(numero);
    }

    private Item definirConteudoFixo(int numero) {
        switch (numero) {
            case 1: return new Cura();
            case 2: return new Revolver();  // Baú 2 contém revólver (+ Zumbi)
            case 3: return new Revolver();  // Baú 3 contém revólver (+ Zumbi)
            case 4: return new TacoDeBeisebol();
            default: return null;
        }
    }

    public String abrir() {
        if (aberto) {
            return "Este baú já foi aberto!";
        }
        aberto = true;
        return "O baú continha: " + (conteudo != null ? conteudo.getNome() : "nada");
    }

    public Item getConteudo() {
        return conteudo;
    }

    public boolean estaAberto() {
        return aberto;
    }
}
