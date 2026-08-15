package combat;

// define el contrato base del Patrón Strategy para la dinamica de combate, permite que el comportamiento de ataque sea intercambiable en tiempo de ejecución
public interface AttackStrategy {

    // firma del metodo que obliga a cualquier estrategia de ataque física, mágica, etc. a tener una fórmula que calcule y devuelva daño en forma de int
    int calculateDamage();
}