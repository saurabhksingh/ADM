package main.com.dev.saurabh.chap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=951
 * Copyright 2013 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
public class UVAVitosFamily10041 {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int numberOfTestCases = Integer.valueOf(br.readLine());

            for(int i=0; i<numberOfTestCases; i++)
            {
                String input = br.readLine();
                String [] nums = input.split(" ");
                int houseCount = Integer.valueOf(nums[0]);
                int length = nums.length-1;
                int [] vals = new int[length];
                int count = 0;
                for(int j=1; j<nums.length; j++)
                {
                  vals[count++] = Integer.valueOf(nums[j]);
                }

                Arrays.sort(vals);

                int median = vals[length>>1];

                int dist = 0;

                for(int val : vals)
                {
                    dist += Math.abs(median-val);
                }

                System.out.println(dist);
            }
        }
        catch(Exception exc){
            //exc.printStackTrace();
        }
    }
}
