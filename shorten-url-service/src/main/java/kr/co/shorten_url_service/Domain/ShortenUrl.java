package kr.co.shorten_url_service.Domain;

import java.util.Random;

public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

    public static String generateShortenUrlKey() {
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();
        int chosenIdx = -1;
        for (int i = 0; i < 8; i++) {
            chosenIdx = random.nextInt(0, base56Characters.length());
            char choseChar = base56Characters.charAt(chosenIdx);
            shortenUrlKey.append(choseChar);
        }
        return shortenUrlKey.toString();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }
}
