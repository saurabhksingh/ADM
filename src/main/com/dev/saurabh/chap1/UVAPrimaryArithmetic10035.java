package main.com.dev.saurabh.chap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * http://uva.onlinejudge.org/index.php?option=onlinejudge&Itemid=99999999&page=show_problem&category=&problem=982&mosmsg=Submission+received+with+ID+11337210
 */
public class UVAPrimaryArithmetic10035 {

    public static void main(String [] args ) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        line = br.readLine();
        while(!"0 0".equals(line))
        {
            String [] nos = line.split(" ");
            String first = nos[0];
            String second = nos[1];

            //swap if first's length is greater than second
            if(first.length() > second.length())
            {
                String tmp = first;
                first = second;
                second = tmp;
            }

            int delta = second.length() - first.length();

            int iterateUpTo = first.length();
            int carry = 0;
            int carryCount = 0;

            for(int i=iterateUpTo-1; i>=0; i--)
            {
               carry = (carry + first.charAt(i)-'0' + second.charAt(i+delta) - '0')/10;
               carryCount += carry;
            }

            if(delta > 0)
            {
                for(int i= delta-1; i>=0; i--)
                {
                    carry = (carry +  second.charAt(i) - '0')/10;
                    carryCount += carry;
                }
            }

            if(carryCount > 0)
            {
               if(carryCount == 1)
               {
                   System.out.println(carryCount+" carry operation.");
               }
               else
               {
                   System.out.println(carryCount+" carry operations.");
               }

            }
            else
            {
                System.out.println("No carry operation.");
            }

            line = br.readLine();
        }
    }
}
