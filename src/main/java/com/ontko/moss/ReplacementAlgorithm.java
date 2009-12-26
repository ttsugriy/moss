package com.ontko.moss;

import java.util.List;

public abstract class ReplacementAlgorithm {

    public enum Names { FIFO, LRU, RR, DEFAULT };

    private final Names name = Names.DEFAULT;

    public Names getName() {
        return name;
    }

    public abstract int getPageToReplace (List<Page> pages, int virtPageNum);
}
