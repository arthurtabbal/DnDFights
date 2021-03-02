package com.tabbal.dndfights;

public class Damage {

    enum types {FIRE, ACID, ICE, FORCE, BLUDGEONING, PIERCING, SLASHING, POISON, THUNDER, ELECTRIC, NECROTIC, PSYCH}

    types damageType;
    int dices;
    int faces;

    public Damage(int dices, int faces, types damageType) {
        this.damageType = damageType;
        this.dices = dices;
        this.faces = faces;
    }
}

