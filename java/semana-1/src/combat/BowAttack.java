package combat;

// arco para ataques a distancia
public class BowAttack implements AttackStrategy {

    @Override
    public int calculateDamage() {
        // da un tiro preciso y consistente, tiene un dado mas grande 12 lados (1-12) pero con mucho bono (+8)
        return Dice.roll(12) + 8;
    }
}