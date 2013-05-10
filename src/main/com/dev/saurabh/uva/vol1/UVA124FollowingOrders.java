package main.com.dev.saurabh.uva.vol1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class UVA124FollowingOrders
{
    static LinkedList<Integer>[] G;
    static int[] indegree;
    static int[] map = new int[128];
    static char[] unmap;
    static int variablesCount;
    static StringBuilder out;
    static char[] res;

    //topological sort starting( via DFS ) with different vertices with in-degree 0
    public static void dfs(int left) {
        if (left == 0)
            out.append(new String(res) + '\n');
        for (int i = 0; i < variablesCount; i++) {
            if (indegree[i] == 0) {

                indegree[i] = -1;

                res[variablesCount - left] = unmap[i];
                for (int j : G[i])
                {
                    indegree[j]--;
                }

                dfs(left - 1);

                for (int j : G[i])
                {
                    indegree[j]++;
                }

                indegree[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean firstInputRead = false;

        while (in.hasNext()) {
            if (firstInputRead)
                System.out.println();

            firstInputRead = true;
            String[] variables = in.nextLine().split(" ");
            String[] constraints = in.nextLine().split(" ");

            Arrays.sort(variables);

            out = new StringBuilder();

            variablesCount = variables.length;
            unmap = new char[variablesCount];
            G = new LinkedList[variables.length];
            indegree = new int[variables.length];

            for (int i = 0; i < G.length; i++)
                G[i] = new LinkedList<Integer>();

            for (int i = 0; i < variables.length; i++) {
                map[variables[i].charAt(0)] = i;
                unmap[i] = variables[i].charAt(0);
            }

            res = new char[variablesCount];

            for (int i = 0; i < constraints.length; i += 2) {
                int s = map[constraints[i].charAt(0)];
                int t = map[constraints[i + 1].charAt(0)];
                indegree[t]++;
                G[s].add(t);
            }

            dfs(variablesCount);

            System.out.print(out);
        }
    }
}
