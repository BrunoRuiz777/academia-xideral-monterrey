package combat;

// clase de utilidad para manejar la aleatoriedad del juego al ser métodos estáticos, no se necesitaria instanciar un dado para usarlo
public class Dice {

    // hace el lanzamiento de un dado, recibe el número de caras como entero y retorna el resultado
    public static int roll(int sides) {

        // Math.random() genera un decimal (0.0 a 0.99) que multiplicamos por el número de caras, el CAST explícito int quita los decimales cambiando el tipo de dato por ejemplo de 5.99 pasa a 5, el + 1 ajusta el rango para que comience en 1 en lugar de 0 ejemplo pasa de 0 a 5 caras a 1 a 6 caras
        return (int) (Math.random() * sides) + 1;
    }

}