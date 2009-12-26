package com.ontko.moss;

import java.util.List;

public class LRUAlgorithm extends ReplacementAlgorithm {

    private final Names name = Names.LRU;

    public int getPageToReplace( List<Page> mem, int virtPageNum ) {

        int pageToReplace = -1;
		int usedLongAgo = -1;
		for (int i = 0; i < mem.size(); i++) {
			Page tempPage = mem.get(i);
			if (tempPage.lastTouchTime > usedLongAgo) {
				usedLongAgo = tempPage.lastTouchTime;
				pageToReplace = i;
			}
		}
        return pageToReplace;
    }
}
