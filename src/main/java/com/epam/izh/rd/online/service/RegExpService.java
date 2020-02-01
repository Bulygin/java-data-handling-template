package com.epam.izh.rd.online.service;

import java.io.FileNotFoundException;

public interface RegExpService {

  String maskSensitiveData() throws FileNotFoundException;

  String replacePlaceholders(double paymentAmount, double balance) throws FileNotFoundException;
}
