package core;

import combat.AttackStrategy;
import combat.PhysicalAttack;
import combat.BowAttack;
import combat.MagicAttack;
import entities.GameCharacter;
import entities.Warrior;
import entities.Goblin;
import items.HealingPotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// clase main unimos las entidades con sus estrategias de combate y consumibles en un bucle interactivo
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // creamos a kratos
        Warrior miGuerrero = new Warrior("Kratos");

        // generics: Usamos el diamante <GameCharacter> para crear una lista
        List<GameCharacter> horda = new ArrayList<>();
        horda.add(new Goblin("Duende Novato"));
        horda.add(new Goblin("Duende Jefe"));

        // clase anonima, lambda y comparator
        // se usa la función flecha -> para ordenar la lista de enemigos por longitud de nombre
        horda.sort((e1, e2) -> Integer.compare(e1.getName().length(), e2.getName().length()));

        // usamos random para seleccionar un enemigo de la lista al azar
        Random rand = new Random();
        GameCharacter enemigoGenerico = horda.get(rand.nextInt(horda.size()));

        Goblin enemigo;
        // cast(yo casteo...) sacamos un GameCharacter de la lista y lo forzamos a ser un Goblin
        if (enemigoGenerico instanceof Goblin) {
            enemigo = (Goblin) enemigoGenerico; // aplicamos el Cast explícito en este apartado
        } else {
            enemigo = new Goblin("Duende de Respaldo");
        }

        AttackStrategy espada = new PhysicalAttack();
        AttackStrategy fuego = new MagicAttack();
        AttackStrategy arco = new BowAttack();

        // aplicamos has-a que cambia el comportamiento en tiempo de ejecución
        enemigo.setCurrentWeapon(arco);

        HealingPotion pocion = new HealingPotion(30);
        int pocionesKratos = 3;

        System.out.println("¡COMIENZA LA BATALLA ÉPICA!");
        System.out.println(miGuerrero.getName() + " VS " + enemigo.getName() + " (Arco)\n");

        int ronda = 1;
        while (miGuerrero.getHealth() > 0 && enemigo.getHealth() > 0) {
            System.out.println("--- Ronda " + ronda + " ---");
            System.out.println("Salud de " + miGuerrero.getName() + ": " + miGuerrero.getHealth() + "/" + miGuerrero.getMaxHealth());
            System.out.println("Salud de " + enemigo.getName() + ": " + enemigo.getHealth() + "/" + enemigo.getMaxHealth());

            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Atacar con Espada");
            System.out.println("2. Atacar con Magia");
            System.out.println("3. Usar Poción (" + pocionesKratos + " restantes)");
            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine();
            System.out.println();

            // excepciones ponemos un bloque de try-catch por si el usuario escribe texto en lugar de un número
            try {
                int opcionNumerica = Integer.parseInt(opcion); // si no es un número, esto lanza un error

                if (opcionNumerica == 1) {
                    miGuerrero.setCurrentWeapon(espada);
                    int danio = miGuerrero.attack();
                    System.out.println("¡" + miGuerrero.getName() + " da un espadazo y causa " + danio + " de daño!");
                    enemigo.takeDamage(danio);
                } else if (opcionNumerica == 2) {
                    miGuerrero.setCurrentWeapon(fuego);
                    int danio = miGuerrero.attack();
                    System.out.println("¡" + miGuerrero.getName() + " lanza fuego y causa " + danio + " de daño!");
                    enemigo.takeDamage(danio);
                } else if (opcionNumerica == 3 && pocionesKratos > 0) {
                    pocion.consume(miGuerrero);
                    pocionesKratos--;
                } else {
                    System.out.println("Opción no válida o sin pociones.");
                }
            } catch (NumberFormatException e) {
                // atrapamos el error silenciosamente y castigamos al jugador por equivocarse
                System.out.println("¡ERROR: Ingresaste letras en lugar de números! Pierdes tu turno por los nervios.");
            }

            if (enemigo.getHealth() > 0) {
                int danioEnemigo = enemigo.attack();
                System.out.println("¡" + enemigo.getName() + " dispara y te causa " + danioEnemigo + " de daño!\n");
                miGuerrero.takeDamage(danioEnemigo);
            }
            ronda++;
        }

        // se ejecuta el metodo heredado y sobrescrita de la clase padre de celebrar
        System.out.println("\n¡LA BATALLA HA TERMINADO!");
        if (miGuerrero.getHealth() > 0) {
            System.out.println("¡Ganaste la batalla!");
            miGuerrero.celebrateVictory();
        } else {
            System.out.println("¡Has muerto!");
            enemigo.celebrateVictory();
        }
        // cerramos escaner por buena practica
        scanner.close();
    }
}