package entities;

import combat.AttackStrategy;

// clase abstracta personaje del juego que implementa la interface atacable
public abstract class GameCharacter implements Attackable {

    // atributos
    private String name;
    protected int health;
    private int maxHealth;
    protected AttackStrategy currentWeapon;

    // constructor cuando creamos un personaje debemos definirle un nombre y vida maxima
    public GameCharacter(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    // el personaje ataca usando el arma que tenga equipada, si no tiene arma equipada para evitar un error NullPointerException y pega con las manos
    public int attack() {
        if (this.currentWeapon != null) {
            return this.currentWeapon.calculateDamage();
        } else {
            System.out.println(this.getName() + " no tiene un arma equipada y ataca con los puños.");
            return 1; // Daño mínimo por golpear sin arma
        }
    }

    // metodo con logica sobre escribimos el comportamiento de tomar daño con override y con la logica del if evitamos que tenga vida negativa
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    // metodo para curar al personaje tomando en cuenta su vida maxima
    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        System.out.println(this.getName() + " recupera vida. (Salud actual: " + this.health + ")");
    }

    // dejamos este metodo abstracto para que cada personaje pueda definir como celebrar su vistoria, no retorna nada, es publico.
    public abstract void celebrateVictory();

    // Getters da el acceso de solo lectura a los atributos protegidos del personaje.
    public String getName() {return this.name;}
    public int getHealth() {return this.health;}
    public int getMaxHealth() {return this.maxHealth;}

    public void setCurrentWeapon(AttackStrategy currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
}