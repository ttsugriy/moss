package com.ontko.moss;

import java.util.List;

public class FIFOAlgorithm extends ReplacementAlgorithm {

    private final Names name = Names.FIFO;

    public int getPageToReplace( List<Page> mem, int virtPageNum ) {

        int oldestPage = -1;
        int oldestTime = -1;

        for (int i = 0; i < mem.size(); i++) {
            Page page = mem.get(i);
            if (page.physical != -1 && page.inMemTime > oldestTime) {
                oldestPage = i;
                oldestTime = page.inMemTime;
            }
        }

        return oldestPage;
    }
}
