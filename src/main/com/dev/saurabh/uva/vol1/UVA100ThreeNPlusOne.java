package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UVA100ThreeNPlusOne {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int [] cache = new int[1000001];
            cache[1] = 1;
            String line = "";
            boolean isFirstLine = true;
            while(true)
            {
                line = br.readLine();
                line = line.trim();
                String [] input = line.split(" ");
                long start = Long.valueOf(input[0]);
                long end = Long.valueOf(input[1]);
                if(start > end)
                {
                    long tmp = start;
                    start = end;
                    end = tmp;
                }
                int max = Integer.MIN_VALUE;

                for(long i=start; i<=end; i++)
                {
                    int result = getCycleLength(i, cache);
                    if(result > max)
                    {
                        max = result;
                    }
                }
                if(!isFirstLine)
                {
                     System.out.println();
                }
                else
                {
                    isFirstLine = false;
                }
                System.out.print(start+ " " + end + " " + max);
            }
        }
        catch (Exception exc)
        {

        }

    }

    private static int getCycleLength(long number, int[] cache)
    {
        if(number < cache.length && cache[(int)number] != 0)
        {
            return cache[(int)number];
        }
        else
        {
            if(number % 2 == 0)
            {
                int result = 1+getCycleLength(number>>1, cache);
                if(number < cache.length)
                {
                    cache[(int)number] = result;
                }

                return result;
            }
            else
            {
                int result = 1+getCycleLength((3*number) + 1 , cache);;
                if(number < cache.length)
                {
                    cache[(int)number] = result;
                }

                return result;
            }
        }
    }
}
