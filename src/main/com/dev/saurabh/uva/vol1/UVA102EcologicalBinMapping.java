package main.com.dev.saurabh.uva.vol1;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UVA102EcologicalBinMapping
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line = br.readLine(); //B/G/C
            while (line != null)
            {


                String [] glassCountsString = line.split(" ");
                int [] glassCount = new int[glassCountsString.length];

                int [] B = new int[3];
                int [] G = new int[3];
                int [] C = new int[3];

                B[0]=Integer.valueOf(glassCountsString[0]); B[1]=Integer.valueOf(glassCountsString[3]); B[2]=Integer.valueOf(glassCountsString[6]);
                G[0]=Integer.valueOf(glassCountsString[1]); G[1]=Integer.valueOf(glassCountsString[4]); G[2]=Integer.valueOf(glassCountsString[7]);
                C[0]=Integer.valueOf(glassCountsString[2]); C[1]=Integer.valueOf(glassCountsString[5]); C[2]=Integer.valueOf(glassCountsString[8]);

                char firstChar= 'B';
                char secondChar = 'C';
                char thirdChar = 'G';
                int minimum = B[1] + B[2] + C[0] + C[2] + G[0] + G[1]; //BCG

                int sum = B[1] + B[2] + G[0] + G[2] + C[0] + C[1]; // BGC;
                if(sum < minimum)
                {
                    minimum = sum;
                    firstChar = 'B'; secondChar = 'G'; thirdChar = 'C';
                }

                sum = C[1] + C[2] + B[0] + B[2] + G[0] + G[1]; // CBG
                if(sum < minimum)
                {
                    minimum = sum;
                    firstChar = 'C'; secondChar = 'B'; thirdChar = 'G';
                }

                sum = C[1] + C[2] + G[0] + G[2] + B[0] + B[1]; // CGB
                if(sum < minimum)
                {
                    minimum = sum;
                    firstChar = 'C'; secondChar = 'G'; thirdChar = 'B';
                }

                sum = G[1] + G[2] + B[0] + B[2] + C[0] + C[1]; // GBC
                if(sum < minimum)
                {
                    minimum = sum;
                    firstChar = 'G'; secondChar = 'B'; thirdChar = 'C';
                }

                sum = G[1] + G[2] + C[0] + C[2] + B[0] + B[1]; // GCB
                if(sum < minimum)
                {
                    minimum = sum;
                    firstChar = 'G'; secondChar = 'C'; thirdChar = 'B';
                }
                StringBuilder result = new StringBuilder();
                result.append(firstChar).append(secondChar).append(thirdChar).append(" ").append(minimum);

                System.out.println(result);

                line = br.readLine(); //B/G/C

            }
        }
        catch (Exception exc)
        {

        }

    }
}
