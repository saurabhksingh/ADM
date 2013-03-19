package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *

 move a onto b

 where a and b are block numbers, puts block a onto block b after returning any blocks that are stacked on top of blocks a and b
 to their initial positions.

 move a over b

 where a and b are block numbers, puts block a onto the top of the stack containing block b,
 after returning any blocks that are stacked on top of block a to their initial positions.

 pile a onto b

 where a and b are block numbers, moves the pile of blocks consisting of block a,
 and any blocks that are stacked above block a, onto block b.
 All blocks on top of block b are moved to their initial positions prior to the pile taking place.
 The blocks stacked above block a retain their order when moved.

 pile a over b

 where a and b are block numbers, puts the pile of blocks consisting of block a,
 and any blocks that are stacked above block a, onto the top of the stack containing block b.
 The blocks stacked above block a retain their original order when moved.

 quit
 */
public class UVA101BlocksProblem {

    private static class Block
    {
        int data;
        int listIndex;
        Block next;
        Block prev;

        public Block(int data)
        {
            this.data = data;
            next = null;
            prev = null;
        }

        @Override
        public int hashCode()
        {
            return this.data;
        }

        @Override
        public boolean equals(Object other)
        {
            boolean result = false;
            if(other != null && other.getClass() == Block.class)
            {
                result = this.data == ((Block)other).data;
            }

            return result;
        }
    }

    public static void main(String [] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line = br.readLine();

            if(line == null || "quit".equals(line))
            {
                return;
            }

            int numberOfBlocks = Integer.valueOf(line.trim());

            int [] blockStackIndexList = new int[numberOfBlocks];
            List<MyStack> blockStackRegistry = new ArrayList<MyStack>();

            for(int i=0; i<numberOfBlocks; i++)
            {
                Block block = new Block(i);
                block.listIndex = i;
                blockStackIndexList[i] = i;
                MyStack blockStack = new MyStack();
                blockStack.push(block);
                blockStackRegistry.add(blockStack);
            }

            line = br.readLine();
            while(!"quit".equals(line))
            {
                String [] statement = line.split(" ");
                String action = statement[0];
                int from = Integer.valueOf(statement[1]);
                String actionType = statement[2];
                int to = Integer.valueOf(statement[3]);

                if("move".equals(action))
                {
                  if("onto".equals(actionType))
                  {
                      moveOnTo(from, to, blockStackRegistry, blockStackIndexList);
                  }
                  else
                  {
                      moveOver(from, to, blockStackRegistry, blockStackIndexList);
                  }
                }
                else
                {
                    if("onto".equals(actionType))
                    {
                        pileOnTo(from, to, blockStackRegistry, blockStackIndexList);
                    }
                    else
                    {
                        pileOver(from, to, blockStackRegistry, blockStackIndexList);
                    }
                }

                line = br.readLine();
            }

            for(int i=0; i<numberOfBlocks; i++)
            {
                System.out.print(i+":");
                MyStack blockStack = blockStackRegistry.get(i);
                List <Block> blockList = blockStack.getAsList();
                for(Block block : blockList)
                {
                    System.out.print(" "+block.data);
                }

                System.out.println();
            }
        }
        catch (Exception exc)
        {

        }
    }

    private static void pileOver(int from, int to, List<MyStack> blockRegistryList, int[] blockStackIndexList)
    {
        int fromBlockIndex = blockStackIndexList[from];
        int toBlockIndex = blockStackIndexList[to];

        if(fromBlockIndex != toBlockIndex)
        {
            MyStack sourceStack = blockRegistryList.get(blockStackIndexList[from]);
            MyStack targetStack = blockRegistryList.get(blockStackIndexList[to]);

            Block fromBlock = new Block(from);
            Block toBlock = new Block(to);

            int indexOfSourceBlock = sourceStack.getBlockIndex(fromBlock);
            fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);
            while(fromBlock != null)
            {
                blockStackIndexList[fromBlock.data] = blockStackIndexList[toBlock.data];
                targetStack.push(fromBlock);
                fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);
            }

        }
    }

    private static void pileOnTo(int from, int to, List<MyStack> blockRegistryList, int[] blockStackIndexList)
    {
        int fromBlockIndex = blockStackIndexList[from];
        int toBlockIndex = blockStackIndexList[to];

        if(fromBlockIndex != toBlockIndex)
        {
            MyStack sourceStack = blockRegistryList.get(blockStackIndexList[from]);
            MyStack targetStack = blockRegistryList.get(blockStackIndexList[to]);

            Block fromBlock = new Block(from);
            Block toBlock = new Block(to);

            returnBlocksToTheirOriginalPosition(toBlock, blockRegistryList, blockStackIndexList);

            int indexOfSourceBlock = sourceStack.getBlockIndex(fromBlock);
            fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);
            while(fromBlock != null)
            {
                blockStackIndexList[fromBlock.data] = blockStackIndexList[toBlock.data];
                targetStack.push(fromBlock);
                fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);
            }

        }

    }

    private static void moveOver(int from, int to, List<MyStack> blockRegistryList, int[] blockStackIndexList)
    {
        int fromBlockIndex = blockStackIndexList[from];
        int toBlockIndex = blockStackIndexList[to];

        if(fromBlockIndex != toBlockIndex)
        {
            MyStack sourceStack = blockRegistryList.get(blockStackIndexList[from]);
            MyStack targetStack = blockRegistryList.get(blockStackIndexList[to]);

            Block fromBlock = new Block(from);
            Block toBlock = new Block(to);

            returnBlocksToTheirOriginalPosition(fromBlock, blockRegistryList, blockStackIndexList);


            int indexOfSourceBlock = sourceStack.getBlockIndex(fromBlock);
            fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);

            blockStackIndexList[fromBlock.data] = blockStackIndexList[toBlock.data];
            targetStack.push(fromBlock);
        }

    }

    private static void moveOnTo(int from, int to, List<MyStack> blockRegistryList, int[] blockStackIndexList)
    {
        int fromBlockIndex = blockStackIndexList[from];
        int toBlockIndex = blockStackIndexList[to];


        if(fromBlockIndex != toBlockIndex)
        {
            MyStack sourceStack = blockRegistryList.get(blockStackIndexList[from]);
            MyStack targetStack = blockRegistryList.get(blockStackIndexList[to]);

            Block fromBlock = new Block(from);
            Block toBlock = new Block(to);

            returnBlocksToTheirOriginalPosition(fromBlock, blockRegistryList, blockStackIndexList);
            returnBlocksToTheirOriginalPosition(toBlock, blockRegistryList, blockStackIndexList);


            int indexOfSourceBlock = sourceStack.getBlockIndex(fromBlock);
            fromBlock = sourceStack.removeAndGetBlock(indexOfSourceBlock);

            blockStackIndexList[fromBlock.data] = blockStackIndexList[toBlock.data];
            targetStack.push(fromBlock);
        }
    }

    private static boolean notInSameStack(Block from, Block to, int [] blockRegistry)
    {
        boolean result = true;

        Block start = from;
        while(start != null)
        {
            if(start.data == to.data)
            {
                return false;
            }

            start = start.prev;
        }

        start = from;
        while(start != null)
        {
            if(start.data == to.data)
            {
                return false;
            }

            start = start.next;
        }

        return result;
    }

    private static Block getStartBlockInStack(Block startBlock)
    {
        while (startBlock.prev != null)
        {
            startBlock = startBlock.prev;
        }

        return startBlock;
    }

    private static Block getEndBlockInStack(Block startBlock)
    {

        while(startBlock.next != null)
        {
          startBlock = startBlock.next;
        }

        return startBlock;
    }

    private static void returnBlocksToTheirOriginalPosition(Block startBlock, List<MyStack> blockRegistryList, int[] blockStackIndexList)
    {
        MyStack blockStack = blockRegistryList.get(blockStackIndexList[startBlock.data]);
        int indexOfBlock = blockStack.getBlockIndex(startBlock);

        if(indexOfBlock >= 0)
        {
            for(int i=blockStack.getSize()-1; i>indexOfBlock; i--)
            {
                Block block = blockStack.removeAndGetBlock(i);
                blockStackIndexList[block.data] = block.data;
                blockRegistryList.get(blockStackIndexList[block.data]).push(block);
            }
        }
    }

    private static void stitchBlocks(Block first, Block second)
    {
        if(first.next != null)
        {
            first.next.prev = null;
        }
        first.next = second;
        if(second.prev != null)
        {
            second.prev.next = null;
        }
        second.prev = first;
    }

    private static class MyStack
    {
        ArrayList <Block> list = new ArrayList<Block>();
        int top = -1;
        HashMap <Block, Integer> blockIndexMap = new HashMap<Block, Integer>();

        public void push(Block block)
        {
            list.add(block);
            blockIndexMap.put(block, list.size()-1);
        }

        public int getBlockIndex(Block block)
        {
            Integer index = blockIndexMap.get(block);
            return  index != null ? index : -1;
        }

        public Block getBlockAtIndex(int index)
        {
            Block result = null;

            if(index >= 0 && index < list.size())
            {
                result = list.get(index);
            }

            return result;
        }

        public ArrayList<Block> getAsList()
        {
            return list;
        }

        public Block removeAndGetBlock(int index)
        {
            Block result = null;

            if(index >= 0 && index < list.size())
            {
                result = list.get(index);
                list.remove(index);
                blockIndexMap.remove(result);
            }

            return result;
        }

        public int getSize()
        {
            return list.size();
        }
    }

}
