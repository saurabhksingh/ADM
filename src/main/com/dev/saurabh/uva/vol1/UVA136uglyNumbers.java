package main.com.dev.saurabh.uva.vol1;


public class UVA136uglyNumbers
{
    public static void main(String [] args)
    {
        int [] uglyNumbers = new int[1500];

        uglyNumbers[0] = 1;
        int i2=0, i3=0, i5=0;
        int next_2_multiple = uglyNumbers[i2] * 2;
        int next_3_multiple = uglyNumbers[i3] * 3;
        int next_5_multiple = uglyNumbers[i5] * 5;

        int uglyNumber = 0;

        for(int i=1; i<1500; i++)
        {
            uglyNumber = Math.min(next_2_multiple, Math.min(next_3_multiple, next_5_multiple));
            uglyNumbers[i] = uglyNumber;

            if(uglyNumber == next_2_multiple)
            {
                i2++;
                next_2_multiple = uglyNumbers[i2] * 2;
            }

            if(uglyNumber == next_3_multiple)
            {
                i3++;
                next_3_multiple =  uglyNumbers[i3] * 3;
            }

            if(uglyNumber == next_5_multiple)
            {
                i5++;
                next_5_multiple =  uglyNumbers[i5] * 5;
            }
        }

        System.out.println("The 1500'th ugly number is "+uglyNumber+".");
    }
}
