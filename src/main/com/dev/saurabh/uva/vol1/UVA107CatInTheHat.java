package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UVA107CatInTheHat
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            while(!"0 0".equals(line))
            {
                if("1 1".equals(line))
                {
                  System.out.println("0 1");
                }
                else
                {
                    String [] in = line.split("\\s+");
                    int height = Integer.valueOf(in[0]);
                    int workerCount = Integer.valueOf(in[1]);

                    if(workerCount == 1)
                    {
                        int count = 0;
                        double tmpHeight = height;
                        double stackHeight = 0;
                       // int lowLimit = (tmpHeight & (tmpHeight-1)) == 0 ? 1 : 0;
                        while(tmpHeight > 1)
                        {
                            stackHeight = stackHeight + tmpHeight;
                            tmpHeight = tmpHeight/2;
                            count++;
                        }

                        System.out.println(count + " "+ (int)(stackHeight+1));

                    }
                    else
                    {
                        int heightBase = 0 , heightExp = (int)((Math.log(height)/Math.log(2))+ 1);

                        if(heightExp > 1)
                        {
                            while(heightExp > 1)
                            {
                                heightBase = getBase(height, workerCount, heightExp);
                                if(heightBase != -1)
                                {
                                    break;
                                }

                                heightExp--;
                            }
                        }
                        if(heightExp == 1)
                        {
                            heightBase = height;
                        }

                        int workerCountBase = workerCount == 1 ? 1 : heightBase-1, workerCountExp = heightExp;

                        int countOfNonWorking = workerCountBase > 1 ? (workerCount-1)/(workerCountBase-1) : (workerCountExp);
                        if(heightBase == 1)
                        {
                            countOfNonWorking = 0;
                        }
                        System.out.print(countOfNonWorking);

                        int stackHeight = Math.max((heightBase * height) - (workerCountBase * workerCount), height);
                        System.out.println( " "+stackHeight);
                    }
                }

                line = br.readLine();
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static int getBase(int height, int workerCount, int power) {
        return getBaseUsingBinarySearch(height, workerCount, power, 2, (int)Math.sqrt(height));
    }

    private static int getBaseUsingBinarySearch(int height, int workerCount, int power, int low, int high) {
        while(low <= high)
        {
            int mid = (low + high)/2;
            int heightExp = (int)Math.pow(mid, power);
            int workerCountExp = (int)Math.pow(mid-1, power);

            if(heightExp == height)
            {
                if(workerCount ==1 || workerCountExp == workerCount)
                {
                    return mid;
                }

                return -1;
            }
            else if(heightExp < height)
            {
                return getBaseUsingBinarySearch(height, workerCount, power, mid+1, high);
            }
            else
            {
                return getBaseUsingBinarySearch(height, workerCount, power, low, mid-1);
            }

        }

        return -1;
    }
}
