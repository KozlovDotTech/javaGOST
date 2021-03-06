package tech.kozlov;

import java.util.Random;

public class BigInteger {

    // maximum length of the BigInteger in uint (4 bytes)
    // change this to suit the required level of precision.

    private static final int MAX_LENGTH = 70;

    // простые числа до 2000 для тестирования
    public static final int[] PRIMES_BELOW_2000 = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
            211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293,
            307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397,
            401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
            503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
            601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691,
            701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797,
            809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887,
            907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997,
            1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097,
            1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193,
            1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297,
            1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399,
            1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499,
            1511, 1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597,
            1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699,
            1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789,
            1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889,
            1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999 };

    private int[] data;

    public int dataLength;

    public BigInteger() {



    }

    public BigInteger(long value) {
        data = new int[MAX_LENGTH];
        long tempVal = value;

        dataLength = 0;
        while (value != 0 && dataLength < MAX_LENGTH) {
            data[dataLength] = (int) (value & 0xFFFFFFFF);
            value >>>= 32;
            dataLength++;
        }

        if(tempVal > 0)         // overflow check for +ve value
        {
            if(value != 0 || (data[MAX_LENGTH-1] & 0x80000000) != 0)
                throw new ArithmeticException("Positive overflow in constructor.");
        }
        else if(tempVal < 0)    // underflow check for -ve value
        {
            if(value != -1 || (data[dataLength-1] & 0x80000000) == 0)
                throw new ArithmeticException("Negative underflow in constructor.");
        }

        if(dataLength == 0)
            dataLength = 1;

    }

    public BigInteger(int value)
    {
        this((long)value);
    }

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



    public static BigInteger operatorMinusMinus (BigInteger bi1) {

    }

    public static BigInteger operatorPlusPlus (BigInteger bi1) {

    }

    public static BigInteger operatorMinus (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorPlus (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorAND (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorOR (BigInteger bi1, BigInteger bi2) {

    }

    public static boolean operatorNotEquals (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorDivide (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorMultiply (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorXOR (BigInteger bi1, BigInteger bi2) {

    }

    public static BigInteger operatorUnaryMinus (BigInteger bi) {

    }

    // ~
    public static BigInteger operatorUnaryNot (BigInteger bi) {

    }

    // >
    public static boolean operatorMore (BigInteger bi1, BigInteger bi2) {

    }

    // <
    public static boolean operatorLess (BigInteger bi1, BigInteger bi2) {

    }

    // >=
    public static boolean operatorMoreEquals (BigInteger bi1, BigInteger bi2) {

    }

    // <=
    public static boolean operatorLessEquals (BigInteger bi1, BigInteger bi2) {

    }

    // %
    public static BigInteger operatorMod (BigInteger bi1, BigInteger bi2) {

    }

    // <<
    public static BigInteger operatorMoveLeft (BigInteger bi1, BigInteger bi2) {

    }

    // >>
    public static BigInteger operatorMoveRight (BigInteger bi1, BigInteger bi2) {

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
