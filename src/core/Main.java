package core;

import combat.AttackStrategy;
import combat.PhysicalAttack;
import combat.BowAttack;
import combat.MagicAttack;
import entities.Warrior;
import entities.Goblin;
import items.HealingPotion;
import java.util.Scanner;

// clase main unimos las entidades con sus estrategias de combate y sus consumibles en un bucle interactivo
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // instanciamos a nuestros personajes y su constructor, se les asigna el nombre y vida base, 150 y 80
        Warrior miGuerrero = new Warrior("Kratos");
        Goblin enemigo = new Goblin("Duende Verde");

        // usamos el patrón strategy y polimorfismo declaramos el arma usando el tipo de la interfaz pero instanciamos el comportamiento concreto que queremos
        AttackStrategy espadaDelOlimpo = new PhysicalAttack();
        AttackStrategy truenos = new MagicAttack();
        AttackStrategy arco = new BowAttack();

        // has-a composición e inyección de dependencias le equipamos el arma por defecto al enemigo
        enemigo.setCurrentWeapon(arco);

        // inicializamos el consumible de curación cumpliendo su contrato
        HealingPotion pocion = new HealingPotion(30);
        int pocionesKratos = 3;

        System.out.println("¡COMIENZA LA BATALLA ÉPICA!");
        System.out.println(miGuerrero.getName() + " VS " + enemigo.getName() + " (Arco)\n");

        int ronda = 1;
        // el bucle se repite mientras ambos personajes tengan vida
        while (miGuerrero.getHealth() > 0 && enemigo.getHealth() > 0) {
            System.out.println("--- Ronda " + ronda + " ---");
            System.out.println("Salud de " + miGuerrero.getName() + ": " + miGuerrero.getHealth() + "/" + miGuerrero.getMaxHealth());
            System.out.println("Salud de " + enemigo.getName() + ": " + enemigo.getHealth() + "/" + enemigo.getMaxHealth());

            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Atacar con Espada (Daño constante)");
            System.out.println("2. Atacar con Magia (Daño inestable)");
            System.out.println("3. Usar Poción (" + pocionesKratos + " restantes)");
            System.out.print("Elige una opción (1, 2 o 3): ");

            String opcion = scanner.nextLine();
            System.out.println();

            // cambiamos el arma dinámicamente y el personaje ataca delegando el cálculo matemático a su arma equipada
            if (opcion.equals("1")) {
                miGuerrero.setCurrentWeapon(espadaDelOlimpo);
                int danio = miGuerrero.attack();
                System.out.println("¡" + miGuerrero.getName() + " da un espadazo y causa " + danio + " de daño!");
                enemigo.takeDamage(danio);
            } else if (opcion.equals("2")) {
                miGuerrero.setCurrentWeapon(truenos);
                int danio = miGuerrero.attack();
                System.out.println("¡" + miGuerrero.getName() + " lanza fuego y causa " + danio + " de daño!");
                enemigo.takeDamage(danio);
            } else if (opcion.equals("3") && pocionesKratos > 0) {
                pocion.consume(miGuerrero);
                pocionesKratos--;
            } else {
                System.out.println("Opción no válida o sin pociones. ¡Pierdes tu turno por dudar!");
            }

            // validamos si el enemigo sigue vivo antes de que pueda contraatacar
            if (enemigo.getHealth() > 0) {
                int danioEnemigo = enemigo.attack();
                System.out.println("¡" + enemigo.getName() + " dispara una flecha y te causa " + danioEnemigo + " de daño!\n");
                miGuerrero.takeDamage(danioEnemigo);
            }
            ronda++;
        }

        System.out.println("\n¡LA BATALLA HA TERMINADO!");
        // is-a validamos que el ganador herede los métodos de su clase padre gamecharacter y sobrescribe este en particular
        // si tiene vida gano, si no perdio
        if (miGuerrero.getHealth() > 0) {
            System.out.println("¡Ganaste la batalla!");
            miGuerrero.celebrateVictory();
        } else {
            System.out.println("¡Has muerto!");
            enemigo.celebrateVictory();
        }
        //cerramos escaner
        scanner.close();
    }
}