package entities;

// clase abstracta personaje del juego que implementa la interface atacable
public abstract class GameCharacter implements Attackable {

    // atributos
    private String name;
    protected int health;
    private int maxHealth;

    // constructor cuando creamos un personaje debemos definirle un nombre y vida maxima
    public GameCharacter(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    // metodo con logica sobre escribimos el comportamiento de tomar daño con override y con la logica del if evitamos que tenga vida negativa
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    // dejamos este metodo abstracto para que cada personaje pueda definir como celebrar su vistoria, no retorna nada, es publico.
    public abstract void celebrateVictory();

    // Getters da el acceso de solo lectura a los atributos protegidos del personaje.
    public String getName() {return this.name;}
    public int getHealth() {return this.health;}
    public int getMaxHealth() {return this.maxHealth;}
}