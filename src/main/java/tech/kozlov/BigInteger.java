package tech.kozlov;

import java.util.Random;

public class BigInteger {

    public BigInteger abs() {

    }

    public int bitCount() {

    }

    private BigInteger barrettReduction(BigInteger x, BigInteger n, BigInteger constant) {

    }

    public BigInteger gcd() {

    }

    public BigInteger genCoPrime(int bits, Random rand) {

    }

    public static BigInteger genPseudoPrime(int bits, int confidence, Random rand) {

    }

    public void genRandomBits(int bits, Random rand) {

    }

    public byte[] getBytes() {

    }

    public void setBit(uint bitNum) {

    }

    public int intValue() {

    }

    public boolean isProbablePrime(int confidence) {

    }

    public static int Jacobi(BigInteger a, BigInteger b) {

    }

    public long longValue() {

    }

    public static BigInteger[] LucasSequence(BigInteger P, BigInteger Q,
                                             BigInteger k, BigInteger n) {

    }

    private static BigInteger[] LucasSequenceHelper(BigInteger P, BigInteger Q,
                                                    BigInteger k, BigInteger n,
                                                    BigInteger constant, int s) {

    }

    public BigInteger max(BigInteger bi) {

    }

    public BigInteger min(BigInteger bi) {

    }

    public BigInteger modInverse(BigInteger modulus) {

    }

    public BigInteger modPow(BigInteger exp, BigInteger n) {

    }

    private static void multiByteDivide(BigInteger bi1, BigInteger bi2,
                                        BigInteger outQuotient, BigInteger outRemainder) {

    }

    public void setBit(uint bitNum) {

    }

    private static int shiftLeft(uint[] buffer, int shiftVal) {

    }

    private static int shiftRight(uint[] buffer, int shiftVal) {

    }

    private static void singleByteDivide(BigInteger bi1, BigInteger bi2,
                                         BigInteger outQuotient, BigInteger outRemainder) {

    }

    public BigInteger sqrt() {

    }

    public String ToHexString() {

    }

    @Override
    public String toString() {
        return toString(10);
    }

    public String toString(int radix) {

    }

    public void unsetBit(uint bitNum) {

    }

    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {

    }
}
