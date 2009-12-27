package com.ontko.moss;

import java.util.List;

public abstract class ReplacementAlgorithm {

    public enum Names { FIFO, LRU, RR, DEFAULT };

    private final Names name = Names.DEFAULT;

    public Names getName() {
        return name;
    }

    public abstract int getPageToReplace (List<Page> pages, int virtPageNum);

    protected int getNumberOfPhysicalPages(List<Page> mem ) {
        int count = 0;
        for (Page page : mem)
            if (page.physical != -1)
                count++;
        return count;
    }
}
