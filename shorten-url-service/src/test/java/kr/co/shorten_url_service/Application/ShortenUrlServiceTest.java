package kr.co.shorten_url_service.Application;

import kr.co.shorten_url_service.Domain.NotFoundShortenUrlException;
import kr.co.shorten_url_service.Presentation.ShortenUrlRequestDto;
import kr.co.shorten_url_service.Presentation.ShortenUrlResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenUrlServiceTest {

    @Autowired
    ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("URL을 단축한 후 단축된 URL 키로 조회하면 원래 URL이 조회되어야 한다.")
    void shortenUrlAddTest() {
        String expectedOriginalUrl = "https://www.hanbit.co.kr/";
        ShortenUrlRequestDto shortenUrlRequestDto = new ShortenUrlRequestDto(expectedOriginalUrl);
        ShortenUrlResponseDto shortenUrlResponseDto = shortenUrlService.generateShortenUrl(shortenUrlRequestDto);
        String shortenUrlKey = shortenUrlResponseDto.getShortenUrlKey();

        String originalUrl = shortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        assertEquals(originalUrl, expectedOriginalUrl);
    }

    @Test
    @DisplayName("존재하지 않는 단축 Url을 조회하면 NotFoundShortenUrlException이 발생한다.")
    void notFoundShortenUrlExceptionTest() {
        String notExistShortenUrlKey = "abcd1234";
        assertThrows(NotFoundShortenUrlException.class, () -> {
            shortenUrlService.getOriginalUrlByShortenUrlKey(notExistShortenUrlKey);
        });
    }
}