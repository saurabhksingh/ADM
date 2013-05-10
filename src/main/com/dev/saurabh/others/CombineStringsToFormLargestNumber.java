package main.com.dev.saurabh.others;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CombineStringsToFormLargestNumber
 * Copyright 2013 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
public class CombineStringsToFormLargestNumber
{
    public static void main(String [] args)
    {
       String [] in = new String [] {"965", "96", "45", "101", "99"};
       Arrays.sort(in, new StringComparator());

        StringBuilder res = new StringBuilder();
        for(String str : in)
        {
           res.append(str);
        }

        System.out.println(res);
    }

    private static class StringComparator implements Comparator<String>
    {

        @Override
        public int compare(String first, String second)
        {
            if(empty(first) && empty(second))
            {
                return 0;
            }

            if(empty(first)) return -1;

            if(empty(second)) return 1;

            int first_len = first.length();
            int second_len = second.length();

            for(int i=0; i<first_len && i<second_len; i++)
            {
                if(first.charAt(i) > second.charAt(i)) return -1;

                else if(first.charAt(i) < second.charAt(i)) return 1;
            }

            if(first_len == second_len) return 0;

            if(first_len > second_len)
            {
                String tmp = first.substring(second_len) + second;
                return compare(tmp, first);
            }

            else
            {
                String tmp = second.substring(first_len) + first;
                return compare(second, tmp);
            }
        }

        private boolean empty(String str)
        {
            return (str == null  ||"".equals(str.trim()));
        }
    }
}
