package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;


public class UVA106FermatPythagoras
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BitSet bitSet = new BitSet(1000002);

            while(true)
            {
                String line = br.readLine();
                int number = Integer.valueOf(line.trim());

                int sqrtOfNumber = (int)Math.sqrt(number);
                if((sqrtOfNumber*sqrtOfNumber) < number) sqrtOfNumber++;
                int tripletCount = 0;

                for(int a=1; a<=sqrtOfNumber; a++)
                {
                    int iterateUpTo = Math.min(number-(a*a), a-1);
                    for(int b=1; b<=iterateUpTo; b++)
                    {
                        int sqrA = a*a;
                        int sqrB = b*b;
                        int p = sqrA - sqrB;
                        int q = (a*b)<<1;
                        int r = sqrA+sqrB;

                        if((p*p + q*q == r*r) && r<=number)
                        {
                            if(gcd(p,q) == 1)
                            {
                                tripletCount++;
                                for(int j=1; j*r<=number; j++)
                                {
                                    bitSet.set(j*p); bitSet.set(j*q); bitSet.set(j*r);
                                }
                            }
                        }
                    }
                }

                int notInTriplet = 0;
                for(int j=1; j<=number; j++)
                {
                    if(!bitSet.get(j))
                    {
                        notInTriplet++;
                    }

                    bitSet.clear(j);
                }

                System.out.println(tripletCount + " "+ notInTriplet);
            }

        }
        catch (Exception exc)
        {

        }
    }

    public static int gcd(int a, int b)
    {
        if(b == 0)
        {
            return a;
        }
        return gcd(b, a%b);
    }
}
