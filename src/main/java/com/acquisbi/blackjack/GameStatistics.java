package com.acquisbi.blackjack;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class GameStatistics extends TreeMap<Integer, Integer>
{

    public GameStatistics()
    {
        super();
    }

    public String showSummary()
    {

        return this.entrySet().parallelStream().map(
                entry -> entry.getKey() + " - " + entry.getValue())
            .collect(Collectors.joining(", "));

    }

}
