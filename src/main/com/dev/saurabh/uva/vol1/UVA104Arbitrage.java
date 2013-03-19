package main.com.dev.saurabh.uva.vol1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class UVA104Arbitrage
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true)
            {
                String line = br.readLine();
                int matrixSize = Integer.valueOf(line.trim());

                double [][][] table = new double[22][22][22];
                int [][][] parent = new int[22][22][22];

                for (int i=0; i<matrixSize; i++)
                {
                    line = br.readLine();
                    String [] input = line.trim().split("\\s+");
                    int count = 0;
                    for (int j=0; j<matrixSize; j++)
                    {
                        if(i == j)
                        {
                            table[0][i][j] = 1.0;
                        }
                        else
                        {
                           // System.out.println(input[count].trim());
                           // System.out.println(Double.valueOf(input[count].trim()));
                           // System.out.println("*********");
                            table[0][i][j] = Double.valueOf(input[count].trim());
                            count++;
                        }

                        parent[0][i][j] = i;
                    }
                }

                for (int length = 1; length < matrixSize; ++length)
                {
                    for (int k = 0; k < matrixSize; ++k)
                    {
                        for (int i = 0; i < matrixSize; ++i)
                        {
                            for (int j = 0; j < matrixSize; ++j)
                            {
                                double value = table[length - 1][i][k] * table[0][k][j];
                                if (value > table[length][i][j])
                                {
                                    table[length][i][j] = value;
                                    parent[length][i][j] = k;
                                }
                            }
                        }
                    }
                }


                int resLen = -1;
                int index = -1;
                for (int length = 1; length < matrixSize; ++length)
                {
                    for (int i = 0; i < matrixSize; ++i)
                    {
                        if (table[length][i][i] > 1.01) {
                            index = i;
                            resLen = length;
                            break;
                        }
                    }

                    if (index != -1)
                    {
                        break;
                    }
                }

                if (index == -1)
                {
                    System.out.println("no arbitrage sequence exists");
                }
                else
                {
                    Stack<Integer> path = new Stack<Integer>();
                    path.push(index);
                    int current = index;
                    for (int length = resLen; length >= 0; --length)
                    {
                        current = parent[length][index][current];
                        path.push(current);
                    }
                    System.out.print((path.pop() + 1));
                    while(!path.empty())
                    {
                        System.out.print(" " + (path.pop() + 1));
                    }

                    System.out.println();
                }
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
