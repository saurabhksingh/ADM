package main.com.dev.saurabh.chap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&category=&problem=1051&mosmsg=Submission+received+with+ID+11312573
 */
public class UVALightMoreLight10110 {
    /*
     *  First pass on all the bulbs, 2nd pass close 2,4,6..., 3rd pass close 3,9,15.. and open 6,12...
      *  only perfect square buld will be on
     */
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line = br.readLine();
            while(!"0".equals(line))
            {
                long number  = Long.valueOf(line);

                long sqrRoot = (long)Math.sqrt(number);

                if(number == (sqrRoot*sqrRoot))
                {
                    System.out.println("yes");
                }
                else
                {
                    System.out.println("no");
                }

                line = br.readLine();
            }
        }
        catch (Exception exc){

        }
    }
}
