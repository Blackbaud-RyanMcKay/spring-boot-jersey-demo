package com.blackbaud.testsupport

/*
 * Copyright 2014 BancVue, LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.fluttercode.datafactory.impl.DataFactory

public class RandomGenerator {

    public static final List<String> STATES = [
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
            "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
            "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    ]

    private static Random random = new Random()
    private static DataFactory df = new DataFactory()

    static {
        init(getSeed())
    }

    private static long getSeed() {
        return random.nextLong()
    }

    public static void init(long seed) {
        random = new Random(seed)
        df = new DataFactory()
        df.randomize(random.nextInt())
    }

    public static DataFactory getDataFactory() {
        return df
    }

    public static long id() {
        return toId(random.nextLong())
    }

    public static int intId() {
        return toId(random.nextInt())
    }

    private static <T extends Number> T toId(T number) {
        if (number < 0) {
            number *= -1
        } else if (number == 0) {
            number = 1
        }
        return number
    }

    public static int tinyInt() {
        return intBetween(0, 50)
    }

    public static int intBetween(int min, int max) {
        return df.getNumberBetween(min, max)
    }

    public static int anInt() {
        return random.nextInt()
    }

    public static long aLong() {
        return random.nextLong()
    }

    public static long aDouble() {
        return random.nextDouble()
    }

    /**
     * Returns a string of random characters.
     */
    public static String text(int length) {
        // NOTE: we're using getRandomChars instead of getRandomText b/c the random text isn't so random.
        // they basically use a dictionary of words of specific lengths and the number of choices can be
        // very small (e.g. size 10 equates to 2 distinct words)
        return df.getRandomChars(length)
    }

    public static String optionalText(int length) {
        return shouldBeNull() ? null : text(length)
    }

    private static boolean shouldBeNull() {
        return intBetween(0, 100) < 5
    }

    public static String phoneNumber() {
        return df.getNumberText(3) + "-" + df.getNumberText(3) + "-" + df.getNumberText(4)
    }

    public static String optionalPhoneNumber() {
        return shouldBeNull() ? null : phoneNumber()
    }

    public static String state() {
        return item(STATES)
    }

    public static String optionalState() {
        return shouldBeNull() ? null : state()
    }

    public static String address() {
        return df.getAddress()
    }

    public static String optionalAddress() {
        return shouldBeNull() ? null : address()
    }

    public static String email() {
        return df.getEmailAddress()
    }

    public static String optionalEmail() {
        return shouldBeNull() ? null : email()
    }

    public static <T> T item(List<T> items) {
        return df.getItem(items)
    }

    public static <T> T item(T... items) {
        return df.getItem(Arrays.asList(items))
    }

}
