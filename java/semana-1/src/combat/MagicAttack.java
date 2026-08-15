package combat;

// Estrategia concreta para ataques mágicos.
public class MagicAttack implements AttackStrategy {

    @Override
    public int calculateDamage() {
        // magia inestable un dado enorme (1-20) con menos bono (+2)
        // Daño mínimo: 3, Daño máximo: 22
        return Dice.roll(20) + 2;
    }
}