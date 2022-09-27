package com.company;

public class Main {

    public static void main(String[] args) {
        int tests = 4;
        int[] times = new int[tests];
        for (int j = 0; j < tests; j++) {
            int mal = j * 20 - 10;
            if (mal < 0) mal = 0;
            int runs = 0;
            int cumTime = 0;
            int repetitions = 100;
            while(runs<repetitions) {
                final int block_count = 10;
                final int miner_count = 10;
                final int privateMiner_count = 0;
                final int malevolentRatio = mal;
                //final int privateMalevolentRatio = 0;
                final String name = "public";
                //final String privateName = "private";
                Blockchain chain = new Blockchain(block_count, miner_count, privateMiner_count, malevolentRatio, name);
                //Blockchain privateChain = new Blockchain(block_count, privateMiner_count, miner_count, privateMalevolentRatio, privateName);
                chain.start();
                //privateChain.start();
                try {
                    chain.join();
                    //privateChain.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                chain.traverse(chain.getRoot());
                System.out.println(chain);
                //privateChain.traverse(privateChain.getRoot());
                //System.out.println(privateChain);
                if(chain.getTime()<100000){
                    cumTime += chain.getTime();
                    runs++;
                }
                System.out.println(runs + " with " + cumTime / runs + " for " + mal + " %");
            }
            times[j] = cumTime / runs;
        }
        for (int i : times)
            System.out.println("time: " + i);
    }
}