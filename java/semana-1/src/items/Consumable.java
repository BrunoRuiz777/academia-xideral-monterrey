package items;
import entities.GameCharacter;

// Contrato para cualquier objeto que se pueda consumir en el juego
public interface Consumable {
    void consume(GameCharacter target);
}