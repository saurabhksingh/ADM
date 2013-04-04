package main.com.dev.saurabh.uva.vol1;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

public class UVA111HistoryGrading
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
                int numberOfTestCases = scanner.nextInt();

                int [] originalRanking = new int[numberOfTestCases];
                for(int i=1; i<=numberOfTestCases; i++)
                {
                   originalRanking[scanner.nextInt()-1] = i;
                }

                while(true)
                {
                    int [] studentAnswers =  new int[numberOfTestCases];

                    for(int i=1; i<=numberOfTestCases; i++)
                    {
                        studentAnswers[scanner.nextInt()-1] = i;
                    }

                    System.out.println(lcs(originalRanking, studentAnswers));
                }

            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static int lcs(int [] originalRanking, int [] studentAnswers)
    {
        int size = originalRanking.length;

        int [][] lengths = new int[size+1][size+1];

        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(originalRanking[i] == studentAnswers[j])
                {
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                }
                else
                {
                    lengths[i+1][j+1] = Math.max(lengths[i+1][j], lengths[i][j+1]);
                }
            }
        }

        return lengths[size][size];
    }
}
