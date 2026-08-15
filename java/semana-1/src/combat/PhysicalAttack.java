package combat;

// Estrategia que implementa el contrato de AttackStrategy encapsula la lógica matemática específica para un ataque
public class PhysicalAttack implements AttackStrategy {

    // Sobrescribimos el metodo con override del contrato para definir nuestra fórmula de daño
    @Override
    public int calculateDamage() {
        // Generamos el daño invocando Dice.roll(10(lados)) sin necesidad de crear un objeto, simula la tirada del dado más un bono de 5 puntos
        return Dice.roll(10) + 5;
    }
}