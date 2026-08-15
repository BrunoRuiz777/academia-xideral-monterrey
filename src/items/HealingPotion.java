package items;
import entities.GameCharacter;

// objeto que curara al personaje aplicando la implementacion o el contrato Consumable
public class HealingPotion implements Consumable {
    private int healAmount;

    public HealingPotion(int healAmount) {
        this.healAmount = healAmount;
    }

    // sobrescribimos el metodo de la interfaz y al ejecutarse, menciona la acción en consola y le suma los puntos de salud al personaje
    @Override
    public void consume(GameCharacter target) {
        System.out.println(target.getName() + " bebe una Poción Curativa brillante.");
        target.heal(this.healAmount);
    }
}