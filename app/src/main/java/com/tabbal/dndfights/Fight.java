package com.tabbal.dndfights;

import java.util.ArrayList;
import android.util.Log;

public class Fight {

    static int totId = 0;
    int id;
    boolean running = false;
    int round = 0;
    Grid grid;
    ArrayList<Char> charConsciousList = new ArrayList<>();
    ArrayList<Char> charUnconsciousList = new ArrayList<>();
    ArrayList<Char> charAllList = new ArrayList<>();


    int party_conscious;
    int monster_conscious;

    public Fight(ArrayList<Char> charList, Grid grid) {

        this.id = ++totId;
        this.grid = grid;
        for (Char eachChar : charList) {
            charAllList.add(eachChar);
        }
    }

    public void addChar(Char charToAdd) {
        this.charConsciousList.add(charToAdd);
    }
    public void removeChar(Char charToRemove) {
        this.charConsciousList.remove(charToRemove);
    }
    public void removeAllChars() {
        ArrayList<Char> toRemove = charUnconsciousList;
        charUnconsciousList.removeAll(toRemove);
        toRemove = charConsciousList;
        charConsciousList.removeAll(toRemove);
        toRemove = charAllList;
        charAllList.removeAll(toRemove);
    }

    public ArrayList<Char> getCharConsciousList() {
        return charConsciousList;
    }
    public ArrayList<Char> getCharAllList() { return charAllList; }
    public ArrayList<Char> getCharUnconsciousList() { return charUnconsciousList; }


    public void setCharAllList(ArrayList<Char> charAllList) {
        this.charAllList = charAllList;
    }
    public void setCharUnconsciousList(ArrayList<Char> charUnconsciousList) {
        this.charUnconsciousList = charUnconsciousList;
    }





    public void rollInitiatives() {
        Log.println(Log.DEBUG,"RollingInit", "-- Fight " + this.id + " -- Rolling initiatives...");
        for (Char eachChar : charConsciousList) {
            eachChar.rollOwnInitiative();
            //Log.println((Log.DEBUG),"RollingCharInit", "Fight " + this.id + " : " + eachChar.name + " : Initiative " + eachChar.getInitiative());
        }
        quickSort(0, charConsciousList.size() - 1);
        this.setCharAllList(charConsciousList);
    }


    public void setRandomPositions() {
        for (Char eachChar : this.charConsciousList) {
            eachChar.setPosition(DiceRoller.roll(grid.getSizeWidth()), DiceRoller.roll(grid.getSizeHeight()));
        }
    }

    public void setCharPosition(Char _char , int x, int y) {
        if (charAllList.contains(_char)) {
            _char.setPosition(x, y);
        } else {
            Log.println(Log.DEBUG, "Error", "\n !!!!!!!  Char not in this fight");
        }
    }


    private void quickSort(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(charConsciousList, low, high);
            quickSort(low, partitionIndex - 1);
            quickSort(partitionIndex + 1 , high);
        }
    }

    private int partition(ArrayList<Char> charList, int low, int high) {
        int pivot = charList.get(high).getInitiative();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            //Log.println(Log.DEBUG,"DEBUG", "DEBUG i = " + i + "  j = " + j);
            if (charList.get(j).getInitiative() > pivot) {
                i++;
                Char swap = charList.get(i);
                charList.set(i, charList.get(j));
                charList.set(j, swap);
            } else if (charList.get(j).getInitiative() == pivot) {
                do {
                    charList.get(j).rollInitiativeDraw();
                    charList.get(high).rollInitiativeDraw();
                }
                while (charList.get(j).getInitiative_draw().equals(charList.get(high).getInitiative_draw()));
                if (charList.get(j).getInitiative_draw() > charList.get(high).getInitiative_draw()) {
                    i++;
                    Char swap = charList.get(i);
                    charList.set(i, charList.get(j));
                    charList.set(j, swap);
                }
            }
        }
        Char swap = charList.get( i+1 );
        charList.set( i+1, charList.get(high)  );
        charList.set( high, swap );
        return i+1;

    }

    public void startCombat() {
        Log.println(Log.DEBUG,"DND Fight", " --- Fight " + this.id + " --- Starting combat...");
        rollInitiatives();
        resetConsciousCounters();
        round = 0;
        running = true;
    }

    public void nextRound() {
        if (running) {
            round++;
            Log.println(Log.DEBUG,"Round","      >>> Round " + round + " <<< " + this.toString());
            for (Char eachChar : charConsciousList) {
                if (eachChar.isConscious()) {
                    eachChar.act(this);
                    updateRunningConditions();
                    if (!running) {
                        break;
                    }
                }
            }
            updateConsciousCounters();
            if (!running) {
                endCombat();
            }
        }
    }

    public void endCombat() {
        Log.println(Log.DEBUG,"Fight", "        ++++ Ending combat " + this.id + " ++++");
        Log.println(Log.DEBUG,"FightEnd", this.toString());
        Log.println(Log.DEBUG, "Unconscious", this.charUnconsciousList.toString());
    }

    public void updateConsciousCounters() {
        for (Char eachChar : charConsciousList){
            if (eachChar.isParty() & !eachChar.isConscious()) {
                party_conscious--;
                charUnconsciousList.add(eachChar);
            } else if (!eachChar.isParty() & !eachChar.isConscious()) {
                monster_conscious--;
                charUnconsciousList.add(eachChar);
            }
        }
        for (Char eachChar : charUnconsciousList) {
            charConsciousList.remove(eachChar);
        }

    }

    public void resetConsciousCounters() {
        party_conscious = 0;
        monster_conscious = 0;

        for (Char eachChar : charConsciousList){
            if (eachChar.isParty() & eachChar.isConscious()) {
                party_conscious++;
            } else if (!eachChar.isParty() & eachChar.isConscious()){
                monster_conscious++;
            }
        }
    }

    public void updateRunningConditions() {
        int party_remaining = 0;
        int monster_remaining = 0;
        for (Char eachChar : charConsciousList) {
            if (eachChar.isConscious() & eachChar.isParty()) {
                party_remaining++;
            } else if (eachChar.isConscious() & !eachChar.isParty()) {
                monster_remaining++;
            }
        }
        if (party_remaining == 0 || monster_remaining == 0){
            running = false;
        }
    }

    @Override
    public String toString() {
        return "\n --- Fight " + id +
                " --- Party: " + party_conscious +
                "     Monster: " + monster_conscious +
                "    --- \n" + charConsciousList;
    }
}
