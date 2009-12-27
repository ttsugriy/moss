package com.ontko.moss;

import java.util.List;

public class RoundRobinAlgorithm extends ReplacementAlgorithm {

    private final Names name = Names.RR; 
    private static int rrNextPage = 0;

    public int getPageToReplace ( List<Page> mem, int virtPageNum ) {
        int pageToReplace = -1;
        for (Page page : mem) {
            if (page.physical == rrNextPage) {
                pageToReplace = page.id;
                break;
            }
        }
		rrNextPage = ++rrNextPage % getNumberOfPhysicalPages( mem );
        return pageToReplace;
    }
}
