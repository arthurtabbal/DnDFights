package com.example.dndfights;

public class PlayerChar extends Char {

    public enum Class { BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, SORCERER, RANGER, ROGUE, WARLOCK, WIZARD }
    public enum Race { HUMAN, ELF, HALF_ELF, DWARF, HALFLING, HALF_ORC, DRAGONBORN, TIEFLING }
    public enum Gender { FEMALE, MALE, NON_BINARY }

    Class init_class;
    Race race;
    int current_level;

    public PlayerChar(String name, Race race, Class charClass, int current_level) {
        super(name);
        this.init_class = charClass;
        this.race = race;
        this.current_level = current_level;
    }
}
