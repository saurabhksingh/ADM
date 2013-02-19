package main.com.dev.saurabh.chap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=10&page=show_problem&problem=788
 */
public class UVAMultiplicationGame847 {

    //Stan always start the game so stan. As per greedy algortithm Stan will try to multiply with max number to reach first
    //Ollie will use minimum number for multiplying so that he can hold back Stan
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = br.readLine();
                if (line == null || "".equals(line.trim())) {
                    break;
                }
                long input = Long.valueOf(line);

                long p = 1;
                boolean isStan = true;

                while (p < input) {
                    if (isStan) {
                        p *= 9;
                        isStan = false;
                    } else {
                        p *= 2;
                        isStan = true;
                    }
                }

                if (!isStan) {
                    System.out.println("Stan wins.");
                } else {
                    System.out.println("Ollie wins.");
                }

            }
        } catch (Exception exc) {

        }
    }
}
