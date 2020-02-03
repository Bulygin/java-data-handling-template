package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
      return new BigDecimal(a).divide(new BigDecimal(b), range, 1);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
      int number = 2;
      BigInteger[] arrayValues = new BigInteger[range + 1];
      int index = 0;
      while (index <= range) {
        if (BigInteger.valueOf(number).isProbablePrime(10)) {
          arrayValues[index] = BigInteger.valueOf(number);
          index++;
        }
        number++;
      }
      return arrayValues[index - 1];
    }
}
