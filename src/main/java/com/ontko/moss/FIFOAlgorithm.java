package com.ontko.moss;

import java.util.List;

public class FIFOAlgorithm extends ReplacementAlgorithm {

    private final Names name = Names.FIFO;

    public int getPageToReplace( List<Page> mem, int virtPageNum ) {

		int count = 0;
		int oldestPage = -1;
		int oldestTime = 0;
		int firstPage = -1;
		int map_count = 0;
		boolean mapped = false;

        while (!(mapped) || count != virtPageNum) {
            Page page = mem.get(count);
            if (page.physical != -1) {
                if (firstPage == -1) {
                    firstPage = count;
                }
                if (page.inMemTime > oldestTime) {
                    oldestTime = page.inMemTime;
                    oldestPage = count;
					mapped = true;
				}
			}
			count++;
			if (count == virtPageNum) {
				mapped = true;
			}
		}
		if (oldestPage == -1) {
			oldestPage = firstPage;
		}
        return oldestPage;
    }
}
