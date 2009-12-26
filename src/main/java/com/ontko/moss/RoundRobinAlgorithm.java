package com.ontko.moss;

import java.util.List;

public class RoundRobinAlgorithm extends ReplacementAlgorithm {

    private final Names name = Names.RR; 
    public static int rrNextPage = 0;

    public int getPageToReplace ( List<Page> mem, int virtPageNum ) {
        int pageToReplace = -1;
		for (int i = 0; i < mem.size(); i++) {
			Page tempPage = mem.get(i);
			if (tempPage.physical == rrNextPage) {
				pageToReplace = i;
				break;
			}
		}
		rrNextPage = (rrNextPage + 1) % (virtPageNum / 2);
        return pageToReplace;
    }
}
