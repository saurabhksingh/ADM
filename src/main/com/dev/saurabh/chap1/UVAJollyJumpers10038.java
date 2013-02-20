package main.com.dev.saurabh.chap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=979
 */
public class UVAJollyJumpers10038 {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line = "";
            while (true) {
                line = br.readLine();
                String[] inputs = line.split(" ");
                int inputSize = Integer.valueOf(inputs[0]);

                if (inputSize == (inputs.length - 1)) {

                    BitSet bitSet = new BitSet(inputSize-1);

                    boolean isJolly = true;

                    for(int i = 1; i< inputs.length-1; i++)
                    {
                        int diff = Math.abs(Integer.parseInt(inputs[i]) - Integer.parseInt(inputs[i+1]));
                        if (diff < 1 || diff >= inputSize || bitSet.get(diff))
                        {
                            System.out.println("Not jolly");
                            isJolly = false;
                            break;
                        }
                        bitSet.set(diff);
                    }
                    if(isJolly)
                    {
                        System.out.println("Jolly");
                    }
                } else {
                    System.out.println("Not jolly");
                }
            }
        } catch (Exception exc) {

        }
    }
}
