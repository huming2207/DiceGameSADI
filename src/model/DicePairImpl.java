package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;

import java.util.concurrent.ThreadLocalRandom;

public class DicePairImpl implements DicePair
{
    private int firstDice;
    private int secondDice;
    private int numFaces;

    public DicePairImpl(int firstDice, int secondDice, int numFaces)
    {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.numFaces = numFaces;
    }

    @Override
    public int getDice1()
    {
        return this.firstDice;
    }

    @Override
    public int getDice2()
    {
        return this.secondDice;
    }

    @Override
    public int getNumFaces()
    {
        return this.numFaces;
    }

    @Override
    public String toString()
    {
        return String.format("Dice 1: %d,  Dice 2: %d .. Total: %d", firstDice, secondDice, firstDice + secondDice);
    }
}
