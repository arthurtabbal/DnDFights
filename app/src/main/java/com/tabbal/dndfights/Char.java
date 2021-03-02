package com.tabbal.dndfights;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;


public class Char {

    public static int tot_id = 0;
    public static int getModifier(int attr) {
        if (attr >= 10) {
            return (attr - 10) / 2;
        } else {
            return (attr - 11) / 2;
        }

    }

    private String name; // for both first and last
    private final int id;
    private int max_hp = 10;
    private int current_hp = max_hp;
    private boolean is_alive = true;
    private int prof_bonus = 2;
    private int armor_class = 10;
    private Integer initiative = 10;
    private Integer initiative_draw = 10;
    private Integer initiative_bonus = 0;
    private int stg = 10;
    private int dex = 10;
    private int con = 10;
    private int itl = 10;
    private int wis = 10;
    private int car = 10;

    private int speed = 6;

    private boolean is_party = true;
    private boolean is_arcane_caster = false;
    private boolean is_divine_caster = false;

    private Position position = new Position(0,0);
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private ArrayList<Damage.types> resistances;
    private ArrayList<Damage.types> immunities;

    public Char(String name) {
        this.id = ++tot_id;
        this.name = name;
        this.immunities = new ArrayList<Damage.types>();
        this.resistances = new ArrayList<Damage.types>();
        this.weapons = new ArrayList<Weapon>();
    }

    public int getInitiative() { return this.initiative; }
    public int getProf_bonus() { return this.prof_bonus; }
    public String getName() { return this.name; }
    public int getId() { return this.id; }
    public int getMax_hp() { return this.max_hp; }
    public int getCurrent_hp() { return current_hp; }
    public int getSpeed() {  return speed;  }
    public int getStg() { return this.stg; }
    public int getDex() { return this.dex; }
    public int getCon() { return this.con; }
    public int getItl() { return this.itl; }
    public int getWis() { return this.wis; }
    public int getCar() { return this.car; }
    public int getAc() {  return this.armor_class; }
    public ArrayList<Item> getItems() { return this.items; }
    public ArrayList<Weapon> getWeapons() { return this.weapons; }
    public ArrayList<Damage.types> getResistances() { return this.resistances; }
    public ArrayList<Damage.types> getImmunities() { return this.immunities; }
    public Integer getInitiative_draw() {
        return initiative_draw;
    }

    public boolean isConscious (){
        return this.current_hp >= 1;
    }
    public boolean isAlive () { return this.is_alive; }
    public boolean isParty() { return this.is_party; };

    public void setName(String name) {
        this.name = name;
    }
    public void setMax_hp(int max_hp) {
        this.max_hp = Math.max(max_hp, 1);
    }
    public void setProf_bonus(int prof_bonus) {
        this.prof_bonus = prof_bonus;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public void setAsEnemy() {
        this.is_party = false;
    }
    public void setSpeed(int speed) {   this.speed = speed;  }
    public void setInitiative_draw(Integer initiative_draw) {
        this.initiative_draw = initiative_draw;
    }
    public void calculateArmorClass() {
        this.armor_class = 10 + getModifier(dex);
    }
    public void setStg(int stg) {
        if ((stg >= 1) & (stg <= 20)){
            this.stg = stg;
        }
    }
    public void setDex(int dex) {
        if ((dex >= 1) & (dex <= 20)) {
            this.dex = dex;
        }
    }
    public void setCon(int con) {
        if ((con >= 1) & (con <= 20)){
            this.con = con;
        }
    }
    public void setItl(int itl) {
        if ((itl >= 1) & (itl <= 20)){
            this.itl = itl;
        }
    }
    public void setWis(int wis) {
        if ((wis >= 1) & (wis <= 20)){
            this.wis = wis;
        }
    }

    public void setCar(int car) {
        if ((car >= 1) & (car <= 20)){
            this.car = car;
        }
    }
    public void setCurrent_hp(int current_hp) {
        if (current_hp >= 1) {
            this.current_hp = current_hp;
        } else {
            this.current_hp = 0;
            Log.println(Log.DEBUG,"CharDown","     ++++ " + this.getName() + " is now unconscious. ++++ ");
        }

    }


    public void addItem(Item item) {
        this.items.add(item);
    }
    public void addWeapon(Weapon weapon) { this.weapons.add(weapon); }
    public void addResistance(Damage.types type) { this.resistances.add(type); }
    public void addImmunity(Damage.types type) { this.immunities.add(type); }

    public void removeItem(Item item) { this.items.remove(item); }
    public void removeWeapon(Weapon weapon) { this.weapons.remove(weapon); }
    public void removeResistance(Damage.types type) { this.resistances.remove(type); }
    public void removeImmunity(Damage.types type) { this.immunities.remove(type); }

    public void act(Fight fight) {
        attack(fight);
    }

    public void attack(Fight fight) {
        meleeAttack(fight);
    }

    public void meleeAttack(Fight fight) {
        for (Char eachChar : fight.charConsciousList) {
            if (eachChar != this & (eachChar.is_party != this.is_party) & eachChar.isConscious()) {
                int roll = DiceRoller.rollD20();
                int modifier = getModifier(stg);
                Log.println(Log.DEBUG,"MeleeAttackRoll",
                        " >>> " + this.getName() + " rolled " + roll + " + " + (this.prof_bonus + modifier) +
                                " against " + eachChar.name + " : ");
                if (roll == 20){
                    Log.println(Log.DEBUG,"MeleeAttackRoll","       CRITICAL HIT !!") ;
                    this.inflictDamage(eachChar, this.weapons.get(0), modifier, true);
                } else if (roll + this.prof_bonus + getModifier(this.stg) >= eachChar.armor_class) {
                    Log.println(Log.DEBUG,"MeleeAttackRoll","       HIT !") ;
                    this.inflictDamage(eachChar, this.weapons.get(0), modifier, false);
                } else {
                    Log.println(Log.DEBUG,"MeleeAttackRoll","       Missed ... ");
                }
                break;
            }
        }
    }

    public void rangeAttack(Fight fight) {
        for (Char eachChar : fight.charConsciousList) {
            if (eachChar != this & (eachChar.is_party != this.is_party) & eachChar.isConscious()) {
                int roll = DiceRoller.rollD20();
                int modifier = getModifier(dex);
                Log.println(Log.DEBUG,"RangeAttackRoll",
                        " >>> " + this.getName() + " rolled " + roll + " + " + (this.prof_bonus + modifier) +
                                " against " + eachChar.name + " : ");
                if (roll == 20){
                    Log.println(Log.DEBUG,"RangeAttackRoll","       CRITICAL HIT !!") ;
                    this.inflictDamage(eachChar, this.weapons.get(0), modifier,true);
                } else if (roll + this.prof_bonus + getModifier(this.dex) >= eachChar.armor_class) {
                    Log.println(Log.DEBUG,"RangeAttackRoll","       HIT !") ;
                    this.inflictDamage(eachChar, this.weapons.get(0), modifier, false);
                } else {
                    Log.println(Log.DEBUG,"RangeAttackRoll","       Missed ... ");
                }
                break;
            }
        }
    }




    public void heal(Char target, int hp_healed) {
        target.setCurrent_hp(target.current_hp + hp_healed);
    }

    public void inflictDamage(Char target, Weapon weapon, int attrBonus, boolean critical_hit) {
        if (target.resistances.size() == 0 & target.immunities.size() == 0) {
            int damageRoll = Math.max(0, weapon.rollDamage(critical_hit));
            int multiplier = critical_hit ? 2 : 1;
            int totDamage = damageRoll + attrBonus;
            Log.println(Log.DEBUG,"DamageRoll",
                    "            " + target.getName() + " took " +
                            weapon.getDamage().dices * multiplier + "d" + weapon.getDamage().faces + " + " + attrBonus + " = " + totDamage +
                            " of " + weapon.getDamage().damageType + " damage");
            if (totDamage >= 0) {
                target.setCurrent_hp(target.current_hp - (damageRoll + attrBonus));
            }
        }
    }

    @Override
    public String toString() {
        return "\n" +
                " " + String.format("%10s",name) +
                "   HP = " + String.format(Locale.getDefault(),"%2d",current_hp) +
                "   =====  Init => " + String.format(Locale.getDefault(),"%2d",initiative) +
                "   STG   " + String.format(Locale.getDefault(),"%2d",stg) +
                "   DEX  " + String.format(Locale.getDefault(),"%2d",dex) +
                "   CON  " + String.format(Locale.getDefault(),"%2d",con) +
                "     ---- AC: " + String.format(Locale.getDefault(),"%2d",armor_class);
    }

    public void rollOwnInitiative() {
        this.initiative = DiceRoller.rollD20() + Char.getModifier(this.dex) + this.initiative_bonus;
    }

    public void rollInitiativeDraw() {
        this.initiative_draw = DiceRoller.rollD20() + Char.getModifier(this.dex) + this.initiative_bonus;
    }

    private int getInitiativeBonus() {
        return this.initiative_bonus;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }
}
