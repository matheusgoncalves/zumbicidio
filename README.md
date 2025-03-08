# Zumbicidio
**`Personagem`**

* Atributos: `saude`, `percepcao`, `posicaoX`, `posicaoY`  
* Métodos: `mover()`, `atacar()`, `curar()`, `esquivar()`

**`Zumbi` (classe abstrata)**

* Atributos: `saude`, `posicaoX`, `posicaoY`  
* Métodos: `mover()`, `atacar()`  
* Subclasses:  
  * `ZumbiRastejante`  
  * `ZumbiComum`  
  * `ZumbiCorredor`  
  * `ZumbiGigante`

**`Item` (classe abstrata)**

* Subclasses:  
  * `Cura`  
  * `Arma` (com subclasses `TacoDeBeisebol`, `Revolver`)

**`Mapa`**

* Atributos: `matriz[][]`, `zumbis`, `baus`  
* Métodos: `carregarMapa()`, `mostrarMapa()`, `moverPersonagem()`, `moverZumbis()`

**`Jogo`**

* Controla o fluxo principal do jogo  
* Métodos: `iniciarJogo()`, `verificarFim()`, `exibirOpcoes()`

**`InterfaceGrafica`**

* Gerencia a exibição do jogo  
* Métodos: `mostrarTelaInicial()`, `mostrarTabuleiro()`, `atualizarStatus()`

