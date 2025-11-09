package kr.co.shorten_url_service.Application;

import kr.co.shorten_url_service.Domain.LackOfShortenUrlKeyException;
import kr.co.shorten_url_service.Domain.ShortenUrl;
import kr.co.shorten_url_service.Domain.ShortenUrlRepository;
import kr.co.shorten_url_service.Presentation.ShortenUrlRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortenUrlServiceUnitTest {

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException 예외가 발생해야 한다.")
    void throwLackOfShortenUrlKeyExceptionTest() {
        // 이건 어차피 의미있는 새 단축 URL을 만들게 아니니까 대충 null로
        ShortenUrlRequestDto shortenUrlRequestDto = new ShortenUrlRequestDto(null);
        /*
         일단 generate한 후 findShortenUrlByShortenUrlKey 메서드로 중복인지 확인한다...
         따라서 여기서 아무거나 있다고 return 하면 중복
         그냥 null을 반환하면 오히려 중복 없음이 되서 넘어간다...
         */
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any()))
                .thenReturn(new ShortenUrl(null, null));
        Assertions.assertThrows(LackOfShortenUrlKeyException.class, () -> {
            shortenUrlService.generateShortenUrl(shortenUrlRequestDto);
        });
    }
}
