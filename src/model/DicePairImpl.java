package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;

import java.util.concurrent.ThreadLocalRandom;

public class DicePairImpl implements DicePair
{
    @Override
    public int getDice1()
    {
        // Generate a random integer between 1 to 6
        // It seems to be the best way to generate any random number with range in Java is using "ThreadLocalRandom"...
        // Ref: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        return ThreadLocalRandom.current().nextInt(1, GameEngine.NUM_FACES + 1);
    }

    @Override
    public int getDice2()
    {
        return ThreadLocalRandom.current().nextInt(1, GameEngine.NUM_FACES + 1);
    }

    @Override
    public int getNumFaces()
    {
        return GameEngine.NUM_FACES;
    }

    @Override
    public String toString()
    {
        return null;
    }
}
