package kr.co.shorten_url_service.Presentation;

import kr.co.shorten_url_service.Domain.ShortenUrl;

public class ShortenUrlResponseDto {

    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
