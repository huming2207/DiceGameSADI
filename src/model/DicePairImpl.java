package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;

import java.util.concurrent.ThreadLocalRandom;

public class DicePairImpl implements DicePair
{
    private int firstDice;
    private int secondDice;

    public DicePairImpl()
    {
        // Generate a random integer between 1 to 6
        // It seems to be the best way to generate any random number with range in Java is using "ThreadLocalRandom"...
        //
        // Ref:
        // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        firstDice = ThreadLocalRandom.current().nextInt(1, GameEngine.NUM_FACES + 1);
        secondDice = ThreadLocalRandom.current().nextInt(1, GameEngine.NUM_FACES + 1);
    }

    @Override
    public int getDice1()
    {
        return firstDice;
    }

    @Override
    public int getDice2()
    {
        return secondDice;
    }

    @Override
    public int getNumFaces()
    {
        return GameEngine.NUM_FACES;
    }

    @Override
    public String toString()
    {
        return String.format("Dice 1: %d,  Dice 2: %d .. Total: %d", firstDice, secondDice, firstDice + secondDice);
    }
}
