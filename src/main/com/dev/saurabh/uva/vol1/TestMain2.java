package main.com.dev.saurabh.uva.vol1;


public class TestMain2 {


    public static void main(String [] rags)
    {
        TestMain2 instance = new TestMain2();
        int [] input = new int[]{100,1,1,1,1,2,3,2,2,2,2,2,2,2,2,3,4};
        int result = instance.arrLeader(input);
        System.out.println(result);
    }
    public int arrLeader ( int[] A ) {
        int result = -1;

        if(A != null && A.length > 0)
        {
            int leaderFrequency = 1;
            int leader = A[0];
            for(int currentIndex = 1; currentIndex < A.length; currentIndex++) {
                if(leader != A[currentIndex])
                {
                    if(leaderFrequency == 0)
                    {
                        leader = A[currentIndex];
                        leaderFrequency++;
                    }
                    else
                    {
                        leaderFrequency--;
                    }
                }
                else
                {
                    leaderFrequency++;
                }
            }
            if(leaderFrequency > 0)
            {
                result = leader;
            }

            leaderFrequency = 0;
            for(int i=0; i<A.length; i++)
            {
                if(A[i] == leader)
                {
                    leaderFrequency++;
                }
            }

            if(leaderFrequency <= (A.length>>1))
            {
              result = -1;
            }

            //System.out.println(leaderFrequency + " "+leader );
        }



        return result;
    }
}
