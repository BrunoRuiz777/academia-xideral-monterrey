package entities;

// Clase Goblin que hereda de GameCharacter (Is-A es un)
public class Goblin extends GameCharacter {

    // constructor de goblin, solo pedimos el nombre y usamos super() para enviarle ese nombre y los 80 de vida base al constructor de la clase padre
    public Goblin(String name) {
        super(name, 80);
    }

    // sobrescribimos el metodo abstracto del padre para que este personaje defina su propia forma única de celebrar la victoria
    @Override
    public void celebrateVictory() {
        System.out.println(this.getName() + " se ríe de manera burlona mientras salta.");
    }
}