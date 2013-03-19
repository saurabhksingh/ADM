package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedInputStream;
import java.util.Locale;
import java.util.Scanner;

public class UVA108MaximumSum
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
                int matrixSize = scanner.nextInt();
                int [][] matrix = new int[matrixSize][matrixSize];
                int maxSum = 0;

                //initialize matrix
                for(int i=0; i<matrixSize; i++)
                {
                    for(int j=0; j<matrixSize; j++)
                    {
                        matrix[i][j] = scanner.nextInt();
                    }
                }

                for(int left=0; left<matrixSize; left++)
                {
                    int [] temp = new int[matrixSize];

                    for(int right = left; right<matrixSize; right++)
                    {
                        for(int i=0; i<matrixSize; i++)
                        {
                            temp [i] += matrix[i][right];
                        }

                        //apply Kadane method
                        int localMaxSum = Integer.MIN_VALUE;
                        int sum = 0;
                        for(int k=0; k<matrixSize; k++)
                        {
                            sum += temp[k];
                            if(sum < 0)
                            {
                                sum = 0;
                            }
                            else if(sum > localMaxSum)
                            {
                                localMaxSum = sum;
                            }
                        }

                        if(maxSum < localMaxSum)
                        {
                            maxSum = localMaxSum;
                        }
                    }
                }

                System.out.println(maxSum);
            }

        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
