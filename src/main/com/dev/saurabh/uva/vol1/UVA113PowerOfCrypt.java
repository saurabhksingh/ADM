package main.com.dev.saurabh.uva.vol1;


import org.apache.commons.configuration.BaseConfigurationXMLReader;

import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class UVA113PowerOfCrypt
{
    private static String charsetName = "UTF-8";
    private static Locale usLocale = new Locale("en", "US");
    private static Scanner scanner = new Scanner(new BufferedInputStream(System.in), charsetName);
    static
    {
        scanner.useLocale(usLocale);
    }

    public static void main(String [] args)
    {
        try
        {
            while(true)
            {
                int n = scanner.nextInt();
                BigInteger number = scanner.nextBigInteger();
                String numberInString = number.toString();
                int digitCount = numberInString.length();
                digitCount--;
                double firstFactor = digitCount * Math.log(10) + Math.log(Double.valueOf(numberInString.charAt(0)+"."+ numberInString.substring(1)));
                System.out.println((int)Math.pow(Math.E, firstFactor/n));
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
