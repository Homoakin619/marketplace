package dev.alphacodez.marketplace.utilities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public final class IdGenerator {
    private static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String digits = "1234567890";
    private static final String lowerAlphabets = alphabets.toLowerCase();
    private static final String alphanumeric = alphabets + digits + lowerAlphabets;

    public static String generateId(int n) {
        StringBuilder id = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (alphanumeric.length() * Math.random());
            id.append(alphanumeric.charAt(index));
        }
        return id.toString();
    }

    public static String generateId() {
        StringBuilder id = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int index = (int) (alphanumeric.length() * Math.random());
            id.append(alphanumeric.charAt(index));
        }
        return id.toString();
    }

}
