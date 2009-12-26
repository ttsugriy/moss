package com.ontko.moss;

public class ReplacementAlgorithmFactory {

    private static ReplacementAlgorithm current;
    
    public static ReplacementAlgorithm getReplacementAlgorithm () {
        return getReplacementAlgorithm( ReplacementAlgorithm.Names.DEFAULT );
    }

    public static ReplacementAlgorithm getReplacementAlgorithm( ReplacementAlgorithm.Names alg ) {
        if (current != null && current.getName() == alg)
            return current;
        switch (alg) {
            case RR:
                current = new RoundRobinAlgorithm();
                break;
            case LRU:
                current = new LRUAlgorithm();
                break;
            case FIFO:
            default:
                current = new FIFOAlgorithm();
        }
        return current;
    }

}
