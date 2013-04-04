package main.com.dev.saurabh.others;

import java.util.Stack;

/**
 * AlgorithmDesignManual
 * <p/>
 * Copyright 2013 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
public class NextHighestNumberOnRight {

    /**
     * if input is 4,5,2,25 then o/p should be
     * 5,25,25,-1
     *
     * Essentially 4->5, 5->25, 2->25, 25->-1
     */

    public static void main(String [] args)
    {
        int [] input = new int[] {4,5,7,2,1,6,25};

        int [] out = getNextHighestNumbersOnTheRight(input);

        for(int num : out)
        {
            System.out.print(num + " ");
        }
    }

    private static int[] getNextHighestNumbersOnTheRight(int[] input)
    {
        int [] out = new int[input.length];
        Stack <Node> integers = new Stack<Node>();
        integers.push(new Node(0, input[0]));

        for(int i=1; i<input.length; i++)
        {
            while(!integers.empty() && (integers.peek().data < input[i]))
            {
                Node node = integers.pop();
                out[node.index] = input[i];
            }

            integers.push(new Node(i,input[i]));
        }
        while(!integers.empty())
        {
            Node node = integers.pop();
            out[node.index] = -1;
        }
        return out;
    }

    private static class Node
    {
        int index;
        int data;

        public Node(int index, int data)
        {
            this.index = index;
            this.data = data;
        }
    }
}
