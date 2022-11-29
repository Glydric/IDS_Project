package it.unicam.ids.studenti.ll.model;

import java.util.Map;

/**
 * Una mappa bidirezionale è una mappa che consta in due map e consente di visualizzarla da entrambi i versi, da C a K[] e da K a C[].
 */
public class BidirectionalArrayMap<C, K> {

    Map<C, K[]> map;
    Map<K, C[]> invertedMap;

    /**
     * @param key
     */
    public C[] getValue(K key) {
        // TODO - implement ArrayBidirectionalMap.getValue
        throw new UnsupportedOperationException();
    }

}