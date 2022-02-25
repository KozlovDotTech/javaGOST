package tech.kozlov;

import java.util.Arrays;
import java.util.Random;

public class DSGost {

    private BigInteger p = new BigInteger();
    private BigInteger a = new BigInteger();
    private BigInteger b = new BigInteger();
    private BigInteger n = new BigInteger();
    private byte[] xG;
    private ECPoint G = new ECPoint();

    public DSGost(BigInteger p, BigInteger a, BigInteger b, BigInteger n, byte[] xG)
    {
        this.a = a;
        this.b = b;
        this.n = n;
        this.p = p;
        this.xG = xG;
    }

    /**
     * Генерируем секретный ключ заданной длины
     * @param BitSize длина
     * @return
     */
    public BigInteger genPrivateKey(int BitSize)
    {
        BigInteger d = new BigInteger();
        do
        {
            d.genRandomBits(BitSize, new Random());
        } while (BigInteger.operatorLess(d, new BigInteger(0)) || BigInteger.operatorMore(d, n));
        return d;
    }

    /**
     * С помощью секретного ключа d вычисляем точку Q=d*G, это и будет наш публичный ключ
     * @param d секретный ключ
     * @return
     */
    public ECPoint genPublicKey(BigInteger d)
    {
        ECPoint G=GDecompression();
        ECPoint Q = ECPoint.multiply(d, G);
        return Q;
    }

    //Восстанавливаем координату y из координаты x и бита четности y
    private ECPoint GDecompression() {
        byte y = xG[0];
        byte[] x = new byte[xG.length-1];
        x = Arrays.copyOfRange(xG, 1, xG.length - 1);
        BigInteger Xcord = new BigInteger(x);
        BigInteger temp = BigInteger.operatorMod(BigInteger.operatorPlus(BigInteger.operatorPlus(BigInteger.operatorMultiply(BigInteger.operatorMultiply(Xcord, Xcord), Xcord), BigInteger.operatorMultiply(a, Xcord)), b), p);
//        BigInteger beta = ModSqrt(temp, p);
        BigInteger beta = modSqrt(temp, p);
        BigInteger Ycord = new BigInteger();
//        if ((beta % 2) == (y % 2))
//            Ycord = beta;
        if (BigInteger.operatorMod(beta, new BigInteger(2)).equals(new BigInteger(y % 2))) {
            Ycord = beta;
        } else {
            Ycord = BigInteger.operatorMinus(p, beta);
        }
        ECPoint G = new ECPoint();
        G.a = a;
        G.b = b;
        G.fieldChar = p;
        G.x = Xcord;
        G.y = Ycord;
        this.G = G;
        return G;
    }

    //функция вычисления квадратоного корня по модулю простого числа q
    public BigInteger modSqrt(BigInteger a, BigInteger q) {
        BigInteger b = new BigInteger();
        do
        {
            b.genRandomBits(255, new Random());
        } while (Legendre(b, q).equals(new BigInteger(1)));
        BigInteger s = new BigInteger(0);
        BigInteger t = BigInteger.operatorMinus(q, new BigInteger(1));
        while (BigInteger.operatorNotEquals(BigInteger.operatorAND(t, new BigInteger(1)), new BigInteger(1))) {
            BigInteger.operatorPlusPlus(s);
            t = BigInteger.operatorMoveRight(t, new BigInteger(1));
        }
        BigInteger Inva = a.modInverse(q);
        BigInteger c = b.modPow(t, q);
        BigInteger r = a.modPow(BigInteger.operatorDivide(BigInteger.operatorPlus(t, new BigInteger(1)), new BigInteger(2)), q);
        BigInteger d = new BigInteger();
        for (int i = 0; BigInteger.operatorLess(new BigInteger(i), s); i++) {
            BigInteger temp = new BigInteger(2);
            temp = temp.modPow(BigInteger.operatorMinus(BigInteger.operatorMinus(s, new BigInteger(i)), new BigInteger(1)), q);
            d = BigInteger.operatorMultiply(r.modPow(new BigInteger(2), q), Inva).modPow(temp, q);
            if (d.equals(BigInteger.operatorMinus(q, new BigInteger(1)))) {
                r = BigInteger.operatorMod(BigInteger.operatorMultiply(r, c), q);
            }
            c = c.modPow(new BigInteger(2), q);
        }
        return r;
    }

    //Вычисляем символ Лежандра
    public BigInteger Legendre(BigInteger a, BigInteger q)
    {
        return a.modPow(BigInteger.operatorDivide(BigInteger.operatorMinus(q, new BigInteger(1)), new BigInteger(2)), q);
    }

    //подписываем сообщение
    public String signGen(byte[] h, BigInteger d) {
        BigInteger alpha = new BigInteger(h);
        BigInteger e = BigInteger.operatorMod(alpha, n);
        if (e.equals(new BigInteger(0))) {
            e = new BigInteger(1);
        }
        BigInteger k = new BigInteger();
        ECPoint C = new ECPoint();
        BigInteger r = new BigInteger();
        BigInteger s = new BigInteger();
        do {
            do {
                k.genRandomBits(n.bitCount(), new Random());
            } while (BigInteger.operatorLess(k, new BigInteger(0)) || BigInteger.operatorMore(k, n));
            C = ECPoint.multiply(k, G);
            r = BigInteger.operatorMod(C.x, n);
            s = BigInteger.operatorMod(BigInteger.operatorPlus(BigInteger.operatorMultiply(r, d), BigInteger.operatorMultiply(k, e)), n);

        } while (r.equals(new BigInteger(0)) || s.equals(new BigInteger(0)));
        String Rvector = padding(r.ToHexString(), n.bitCount() / 4);
        String Svector = padding(s.ToHexString(), n.bitCount() / 4);
        return Rvector + Svector;
    }

    //проверяем подпись
    public boolean signVer(byte[] H, String sign, ECPoint Q) {
        String Rvector = sign.substring(0, 0 + n.bitCount() / 4);
        String Svector = sign.substring(n.bitCount() / 4, n.bitCount() / 4 + n.bitCount() / 4);
        BigInteger r = new BigInteger(Rvector, 16);
        BigInteger s = new BigInteger(Svector, 16);

        if (BigInteger.operatorLess(r, new BigInteger(1))
                || BigInteger.operatorMore(r, BigInteger.operatorMinus(n, new BigInteger(1)))
                || BigInteger.operatorLess(s, new BigInteger(1))
                || BigInteger.operatorMore(s, BigInteger.operatorMinus(n, new BigInteger(1)))) {
            return false;
        }

        BigInteger alpha = new BigInteger(H);
        BigInteger e = BigInteger.operatorMod(alpha, n);
        if (e.equals(new BigInteger(0))) {
            e = new BigInteger(1);
        }

        BigInteger v = e.modInverse(n);
        BigInteger z1 = BigInteger.operatorMod(BigInteger.operatorMultiply(s, v), n);

        BigInteger z2 = BigInteger.operatorPlus(n, BigInteger.operatorMod(BigInteger.operatorUnaryMinus(BigInteger.operatorMultiply(r, v)), n));
        this.G = GDecompression();
        ECPoint A = ECPoint.multiply(z1, G);
        ECPoint B = ECPoint.multiply(z2, Q);
        ECPoint C = ECPoint.operatorPlus(A, B);
        BigInteger R = BigInteger.operatorMod(C.x, n);
        if (R.equals(r)) {
            return true;
        } else {
            return false;
        }
    }

    //дополняем подпись нулями слева до длины n, где n - длина модуля в битах
    private String padding(String input, int size)
    {
        if (input.length() < size)
        {
            do
            {
                input = "0" + input;
            } while (input.length() < size);
        }
        return input;
    }

}
