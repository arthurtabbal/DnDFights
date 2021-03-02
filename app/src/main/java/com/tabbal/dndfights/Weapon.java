package com.tabbal.dndfights;

import java.util.ArrayList;

public class Weapon extends Item {

    enum Tags { FINESSE, TWO_HANDED, HEAVY, LIGHT }
    private final String name;
    private final Damage damage;
    private final Damage critical_damage;
    private final int range;
    private boolean is_finesse = false;

    public Weapon (String name, Damage damage, int range) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.critical_damage = new Damage( 2 * damage.dices, damage.faces, damage.damageType);

    }

    public Weapon (String name, Damage damage, int range, ArrayList<Tags> tags) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.critical_damage = new Damage( 2 * damage.dices, damage.faces, damage.damageType);
        if (tags.contains(Tags.FINESSE)) {
            this.is_finesse = true;
        }
    }

    public String getName() { return name; }
    public Damage getDamage() {  return damage; }
    public int getRange() { return range; };
    public Damage getCriticalDamage() { return critical_damage; }
    public boolean isMelee() { return (range == 1); }
    public boolean isRange() { return (range > 2); }
    public boolean isFinesse() { return is_finesse; }

    public int rollDamage(boolean critical_hit) {
        int sum = 0;
        int multiplier = critical_hit ? 2 : 1;
        for (int dice = 0; dice < damage.dices * multiplier; dice++) {
            sum += DiceRoller.roll(damage.faces);
        }
        return sum;
    }
}

