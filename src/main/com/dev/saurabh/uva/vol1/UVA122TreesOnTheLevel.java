package main.com.dev.saurabh.uva.vol1;


import java.io.BufferedInputStream;
import java.util.*;
import java.util.regex.Pattern;

public class UVA122TreesOnTheLevel
{
    private static class Node
    {
        int weight;
        String data;
        int level;

        @Override
        public boolean equals(Object other)
        {
            Node otherNode = (Node)other;
            return otherNode.weight == weight;
        }
    }

    private static String charsetName = "UTF-8";
    private static Locale usLocale = new Locale("en", "US");
    private static Pattern delimiters = Pattern.compile(System.getProperty("line.separator") + "|\\s");
    private static Scanner scanner = new Scanner(new BufferedInputStream(System.in), charsetName).useDelimiter(delimiters);
    static
    {
        scanner.useLocale(usLocale);
    }

    public static void main(String [] args)
    {
        try
        {
            String str;
            while(true)
            {
                TreeSet<Node>[] bfsResult = new TreeSet[257];
                int maxIndex = -1;
                StringBuilder result = null;
                boolean invalidInputWasEncountered = false;
                while(!"()".equals(str = scanner.next()))
                {
                    int indexOfComma = str.indexOf(",");
                    String data = str.substring(1, indexOfComma);
                    String location = str.substring(indexOfComma+1, str.length()-1);
                    int weight = 0;
                    if(location != null)
                    {
                        for(int i=0; i<location.length(); i++)
                        {
                            weight  = weight*10 + (location.charAt(i) == 'L' ? 0:1);
                        }
                    }
                    int index = location!= null ?location.length():0;
                    if(index > maxIndex)
                    {
                        maxIndex = index;
                    }
                    Node currentNode = new Node();
                    currentNode.weight = weight;
                    currentNode.data = data;
                    currentNode.level = index;
                    if(bfsResult[index] == null)
                    {
                        bfsResult[index] = new TreeSet<Node>(new Comparator<Node>() {
                            @Override
                            public int compare(Node node, Node node2) {
                                return Double.compare(node.weight, node2.weight);
                            }
                        });
                    }

                    if(!bfsResult[index].add(currentNode) || currentNode.data == null || "".equals(currentNode.data))
                    {
                        while(!"()".equals(str))
                        {
                            str = scanner.next();
                        }
                        invalidInputWasEncountered = true;
                        break;
                    }
                }

                if(!invalidInputWasEncountered)
                {
                    for(int j=0; j<=maxIndex; j++)
                    {
                        if(bfsResult[j] == null)
                        {
                            result = new StringBuilder("not complete");
                            break;
                        }

                        TreeSet <Node> nodes = bfsResult[j];
                        String space = " ";
                        while (!nodes.isEmpty())
                        {
                            if(result == null)
                            {
                                result = new StringBuilder();
                                result.append(nodes.pollFirst().data);
                            }
                            else
                            {

                                result.append(space).append(nodes.pollFirst().data);
                            }

                        }
                    }

                    System.out.println(result);
                }
                else
                {
                    System.out.println("not complete");
                }



            }
        }
        catch (Exception exc)
        {
            //exc.printStackTrace();
        }

    }
}
