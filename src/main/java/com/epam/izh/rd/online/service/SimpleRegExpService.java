package com.epam.izh.rd.online.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() throws FileNotFoundException {
        String s = "";
        Scanner in = new Scanner(new File(".\\src\\main\\resources\\sensitive_data.txt"));
        while (in.hasNext()) {
            s += in.nextLine();
        }
        Pattern pattern = Pattern.compile("(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})(\\s\\d{4})");
        Matcher matcher = pattern.matcher(s.trim());
        if (matcher.find()) {
            s = matcher.replaceAll("$1 **** ****$4");
        }
        return s;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance)
        throws FileNotFoundException {
        String s = "";
        Scanner in = new Scanner(new File(".\\src\\main\\resources\\sensitive_data.txt"));
        while (in.hasNext()) {
            s += in.nextLine();
        }

        Pattern pattern = Pattern.compile("\\W{2}payment_amount\\W");
        Matcher matcher = pattern.matcher(s.trim());

        if (matcher.find()) {
            s = matcher.replaceAll("" + (int) paymentAmount);
        }

        pattern = Pattern.compile("\\W{2}balance\\W");
        matcher = pattern.matcher(s.trim());

        if (matcher.find()) {
            s = matcher.replaceAll("" + (int) balance);
        }

        System.out.println(s);
        return s;
    }
}
