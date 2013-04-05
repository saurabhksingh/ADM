package main.com.dev.saurabh.uva.vol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class UVA123SearchingQuickly
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

    private static class IndexedTitle
    {
        String fullTitle;
        String indexingKey;

        public IndexedTitle(String fullTitle, String indexingKey)
        {
            this.fullTitle = fullTitle;
            this.indexingKey = indexingKey;
        }
    }
    public static void main(String [] args) throws Exception
    {
        HashSet <String> ignoreWordsSet = new HashSet<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while(!"::".equals(str = br.readLine()))
        {
            ignoreWordsSet.add(str);
        }

        ArrayList<IndexedTitle> indexedTitles = new ArrayList<IndexedTitle>();

        try
        {
            while(true)
            {
                str = br.readLine();
               // System.out.println(str);
                if(str == null || "".equals(str) || "\n".equals(str)) break;
                String title = str.toLowerCase();
                String [] tokens = title.split(" ");
                addIndexedTitlesToList(tokens, ignoreWordsSet, indexedTitles);

                Collections.sort(indexedTitles, new Comparator<IndexedTitle>() {
                    @Override
                    public int compare(IndexedTitle first, IndexedTitle second) {
                        return first.indexingKey.compareTo(second.indexingKey);
                    }
                });

            }

        }
        catch (Exception exc)
        {
            //ignore it.
        }
        for(IndexedTitle title : indexedTitles)
        {
            System.out.println(title.fullTitle.trim());
        }
    }

    private static void addIndexedTitlesToList(String[] tokens, HashSet<String> ignoreWordsSet, ArrayList<IndexedTitle> indexedTitles)
    {
        String [] prefixes = new String[tokens.length];
        String [] suffixes = new String[tokens.length];

        prefixes[0] = "";
        suffixes[suffixes.length-1] = "";
        StringBuilder prefixBuilder = new StringBuilder("");
        StringBuilder suffixBuilder = new StringBuilder("");

        for(int j=1; j<tokens.length; j++)
        {
            prefixes[j] = prefixBuilder.append(tokens[j-1]).append(" ").toString();
            suffixes[tokens.length-j-1] = suffixBuilder.insert(0,tokens[tokens.length-j]+" ").toString();
        }

        for(int i=0; i<tokens.length; i++)
        {
            String currentToken = tokens[i];
            if(!ignoreWordsSet.contains(currentToken))
            {
                String indexBasis = currentToken.toUpperCase();
                IndexedTitle title = new IndexedTitle(new StringBuilder(prefixes[i]).append(indexBasis).append(" ").append(suffixes[i]).toString(), indexBasis);
                indexedTitles.add(title);
            }
        }
    }
}
