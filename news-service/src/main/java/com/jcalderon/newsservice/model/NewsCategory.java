package com.jcalderon.newsservice.model;

import java.util.List;
import java.util.Random;

public enum NewsCategory {
        INTERNATIONAL,
        LOCAL,
        SOCIAL,
        SPORT;

        private static final List<NewsCategory> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static String randomCategory()  {
            return VALUES.get(RANDOM.nextInt(SIZE)).name();
        }
}
