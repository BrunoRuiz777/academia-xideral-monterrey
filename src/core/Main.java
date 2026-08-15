package core;

import combat.AttackStrategy;
import combat.PhysicalAttack;
import entities.Warrior;

// Clase main unimos las entidades con sus estrategias de combate.
public class Main {
    public static void main(String[] args) {

        // instanciamos a nuestro personaje y al nacer, su constructor le asigna el nombre y la vida máxima base (150).
        Warrior miGuerrero = new Warrior("Kratos");

        // Aplicamos Polimorfismo: Declaramos el arma bajo el contrato universal (AttackStrategy) para mantener la flexibilidad del código, inicializándola con la lógica específica de un ataque físico.
        AttackStrategy espada = new PhysicalAttack();

        // Has-A composición e inyección de dependencias le equipamos el arma al guerrero
        miGuerrero.setCurrentWeapon(espada);

        // el personaje ataca delegando el cálculo matemático a su arma equipada, si más adelante se cambia de arma, esta línea no cambia, pero si el daño, este será distinto
        System.out.println(miGuerrero.getName() + " ataca y causa " + miGuerrero.attack() + " puntos de daño.");

        // Is-A (es un... herencia) validamos que el guerrero herede los métodos de su clase padre GameCharacter y sobrescribe este en particular
        miGuerrero.celebrateVictory();
    }
}