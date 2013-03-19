package main.com.dev.saurabh.uva.vol1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class UVA103StackingBoxes
{
    private static class Cube
    {
        int index;
        int [] dimensionData;
        int sum;

        public Cube(int sum)
        {
            this.sum = sum;
        }

        public boolean canContain(Cube other)
        {
            boolean result = true;
            for(int i=0; i<dimensionData.length; i++)
            {
                if(other.dimensionData[i] >= dimensionData[i])
                {
                    result = false;
                    break;
                }
            }

            return result;
        }

        @Override
        public int hashCode()
        {
            int sum = 0;
            for(int dimension : dimensionData)
            {
                sum += dimension;
            }

            return sum;
        }

        @Override
        public boolean equals(Object other)
        {
            Cube otherCube = (Cube)other;
            boolean result = true;
            for(int i=0; i<otherCube.dimensionData.length; i++)
            {
                if(dimensionData[i] != otherCube.dimensionData[i])
                {
                    result = false;
                    break;
                }
            }

            return result;
        }
    }

    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            while(line != null)
            {
                line = br.readLine();
                String [] input = line.split(" ");

                int numberOfCubes = Integer.valueOf(input[0].trim());
                int dimension = Integer.valueOf(input[1].trim());

                Cube [] cubes = new Cube[numberOfCubes];

                for(int i=0; i<numberOfCubes; i++)
                {
                    input = br.readLine().split(" ");
                    int [] dimensions = new int[dimension];
                    int count = 0;
                    int sum = 0;
                    for(String value : input)
                    {
                        dimensions[count] = Integer.valueOf(value.trim());
                        sum += dimensions[count];
                        count++;
                    }
                    Arrays.sort(dimensions);
                    Cube cube = new Cube(sum);
                    cube.index = i+1;
                    cube.dimensionData = dimensions;
                    cubes[i] = cube;
                }

                Arrays.sort(cubes, new Comparator<Cube>() {
                    @Override
                    public int compare(Cube cube, Cube cube2) {
                        return Double.compare(cube.sum, cube2.sum);
                    }
                });

                int[] parent = new int[numberOfCubes];

                for (int i = 0; i < numberOfCubes; i++)
                    parent[i] = i;
                int[] lis = new int[numberOfCubes];
                Arrays.fill(lis, 1);

                int index = 0;
                for (int i = 0; i < numberOfCubes; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        if (lis[j] + 1 > lis[i] && cubes[i].canContain(cubes[j])) {
                            lis[i] = lis[j] + 1;
                            parent[i] = j;
                        }
                    }
                }

                int max = lis[0];
                for (int i = 1; i < numberOfCubes; i++)
                {
                    if (lis[i] > max)
                    {
                        max = lis[i];
                        index = i;
                    }
                }

                System.out.println(max);

                Stack<Integer> res = new Stack<Integer>();
                res.push(cubes[index].index);

                while (parent[index] != index)
                {
                    index = parent[index];
                    res.push(cubes[index].index);
                }

                while (res.size() > 1)
                {
                    System.out.print(res.pop() + " ");
                }
                System.out.println(res.pop());
            }
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
