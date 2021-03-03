package com.tabbal.dndfights;

public class Damage {

    enum type {FIRE, ACID, ICE, FORCE, BLUDGEONING, PIERCING, SLASHING, POISON, THUNDER, ELECTRIC, NECROTIC, PSYCH}

    type damageType;
    int dices;
    int faces;

    public Damage(int dices, int faces, type damageType) {
        this.damageType = damageType;
        this.dices = dices;
        this.faces = faces;
    }
}

