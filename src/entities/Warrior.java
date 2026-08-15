package entities;

// clase warrior que hereda de GameCharacter (el Is-A) e implementa interface de attackable
public class Warrior extends GameCharacter implements Attackable {

    // constructor de guerrero, solo pedimos el nombre y usamos super() para enviarle ese nombre y los 150 de vida base al constructor de la clase padre.
    public Warrior(String name) {
        super(name, 150);
    }

    // sobrescribimos el metodo abstracto del padre para que este personaje defina su propia forma única de celebrar la victoria

    @Override
    public void celebrateVictory() {
        System.out.println("Warrior celebrated!");
    }
}
