package tech.kozlov;

/**
 * Класс для умножения точек эллиптической кривой
 */
public class ECPoint {

    public BigInteger x;
    public BigInteger y;
    public BigInteger a;
    public BigInteger b;
    public BigInteger fieldChar;

    public ECPoint(ECPoint p)
    {
        x = p.x;
        y = p.y;
        a = p.a;
        b = p.b;
        fieldChar = p.fieldChar;
    }

    public ECPoint()
    {
        x = new BigInteger();
        y = new BigInteger();
        a = new BigInteger();
        b = new BigInteger();
        fieldChar = new BigInteger();
    }

    /**
     * Сложение двух точек
     * @param p1 первая точка
     * @param p2 вторая точка
     */
    public static ECPoint operatorPlus(ECPoint p1, ECPoint p2) {
        ECPoint p3 = new ECPoint();
        p3.a = p1.a;
        p3.b = p1.b;
        p3.fieldChar = p1.fieldChar;

        BigInteger dy = BigInteger.operatorMinus(p2.y, p1.y);
        BigInteger dx = BigInteger.operatorMinus(p2.x, p1.x);

        if (BigInteger.operatorLess(dx, new BigInteger(0))) {
            dx = BigInteger.operatorPlus(dx, p1.fieldChar);
        }
        if (BigInteger.operatorLess(dy, new BigInteger(0))) {
            dy = BigInteger.operatorPlus(dy, p1.fieldChar);
        }

        BigInteger m = BigInteger.operatorMod((BigInteger.operatorMultiply(dy, dx.modInverse(p1.fieldChar))), p1.fieldChar);
        if (BigInteger.operatorLess(m, new BigInteger(0))) {
            m = BigInteger.operatorPlus(m, p1.fieldChar);
        }
        p3.x = BigInteger.operatorMod(BigInteger.operatorMinus(BigInteger.operatorMinus(BigInteger.operatorMultiply(m, m), p1.x), p2.x), p1.fieldChar);
        p3.y =BigInteger.operatorMod(BigInteger.operatorMinus(BigInteger.operatorMultiply(m, BigInteger.operatorMinus(p1.x, p3.x)), p1.y), p1.fieldChar);
        if (BigInteger.operatorLess(p3.x, new BigInteger(0))) {
            p3.x = BigInteger.operatorPlus(p3.x, p1.fieldChar);
        }
        if (BigInteger.operatorLess(p3.y, new BigInteger(0))) {
            p3.y = BigInteger.operatorPlus(p3.y, p1.fieldChar);
        }
        return p3;
    }

    /**
     * Сложение точки P с собой же
     * @param p точка
     * @return удвоенную точку
     */
    public static ECPoint doubleECPoint(ECPoint p) {
        ECPoint p2 = new ECPoint();
        p2.a = p.a;
        p2.b = p.b;
        p2.fieldChar = p.fieldChar;

        BigInteger dy = BigInteger.operatorPlus(BigInteger.operatorMultiply(BigInteger.operatorMultiply(new BigInteger(3), p.x), p.x), p.a);
        BigInteger dx = BigInteger.operatorMultiply(new BigInteger(2), p.y);

        if (BigInteger.operatorLess(dx, new BigInteger(0))) {
            dx = BigInteger.operatorPlus(dx, p.fieldChar);
        }
        if (BigInteger.operatorLess(dy, new BigInteger(0))) {
            dy = BigInteger.operatorPlus(dy, p.fieldChar);
        }

        BigInteger m = BigInteger.operatorMod(BigInteger.operatorMultiply(dy, dx.modInverse(p.fieldChar)), p.fieldChar);
        p2.x = BigInteger.operatorMod(BigInteger.operatorMinus(BigInteger.operatorMinus(BigInteger.operatorMultiply(m, m), p.x), p.x), p.fieldChar);
        p2.y = BigInteger.operatorMod(BigInteger.operatorMinus(BigInteger.operatorMultiply(m, BigInteger.operatorMinus(p.x, p2.x)), p.y), p.fieldChar);


        if (BigInteger.operatorLess(p2.x, new BigInteger(0))) {
            p2.x = BigInteger.operatorPlus(p2.x, p.fieldChar);
        }
        if (BigInteger.operatorLess(p2.y, new BigInteger(0))) {
            p2.y = BigInteger.operatorPlus(p2.y, p.fieldChar);
        }
        return p2;
    }

    /**
     * Умножение точки на число x, по сути своей представляет x сложений точки самой с собой
     * @param x число
     * @param p точка
     * @return х сложений точки самой с собой
     */
    public static ECPoint multiply(BigInteger x, ECPoint p) {
        ECPoint temp = p;
        x = BigInteger.operatorMinus(x, new BigInteger(1));
        while (BigInteger.operatorNotEquals(x, new BigInteger(0))) {
            if (BigInteger.operatorNotEquals(BigInteger.operatorMod(x, new BigInteger(2)), new BigInteger(0))) {
                if (temp.x.equals(p.x) || temp.y.equals(p.y)) {
                    temp = doubleECPoint(temp);
                } else {
                    temp = operatorPlus(temp, p);
                }
                x = BigInteger.operatorMinus(x, new BigInteger(1));
            }
            x = BigInteger.operatorDivide(x, new BigInteger(2));
            p = doubleECPoint(p);
        }
        return temp;
    }
}
