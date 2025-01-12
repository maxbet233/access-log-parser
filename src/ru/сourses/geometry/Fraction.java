package ru.сourses.geometry;

public class Fraction extends Number {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator <= 0) throw new IllegalArgumentException("Знаменатель не может быть меньше, либо равен нулю");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public Fraction sum(Fraction fraction) {
        int resNumerator = (this.numerator * fraction.denominator) + (fraction.numerator * this.denominator);
        int resDenominator = this.denominator * fraction.denominator;
        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction sum(int n) {
        int resNumerator = (this.numerator) + (n * this.denominator);
        return new Fraction(resNumerator, this.denominator);
    }

    public Fraction minus(Fraction fraction) {
        int resNumerator = (this.numerator * fraction.denominator) - (fraction.numerator * this.denominator);
        int resDenominator = this.denominator * fraction.denominator;
        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction minus(int n) {
        int resNumerator = (this.numerator) - (n * this.denominator);
        return new Fraction(resNumerator, this.denominator);
    }

    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return (long) numerator / (long) denominator;
    }

    @Override
    public float floatValue() {
        return (float) numerator / (float) denominator;
    }

    @Override
    public double doubleValue() {
        return (double) numerator / (double) denominator;
    }
}
