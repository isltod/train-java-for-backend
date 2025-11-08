package kr.co.shorten_url_service.Application;

import kr.co.shorten_url_service.Domain.LackOfShortenUrlKeyException;
import kr.co.shorten_url_service.Domain.NotFoundShortenUrlException;
import kr.co.shorten_url_service.Domain.ShortenUrl;
import kr.co.shorten_url_service.Domain.ShortenUrlRepository;
import kr.co.shorten_url_service.Presentation.ShortenUrlInformationDto;
import kr.co.shorten_url_service.Presentation.ShortenUrlRequestDto;
import kr.co.shorten_url_service.Presentation.ShortenUrlResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    // 인터페이스에는 아무 딱지도 없지만, 그걸 구현하는 놈에 딱지가 있으니 Auto wired 된다...
    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlResponseDto generateShortenUrl(
            ShortenUrlRequestDto shortenUrlRequestDto
    ) {
        /*
         중복 키가 나오면 의도치않게 이전에 저장했던 다른 URL을 덮어써버릴테니 문제다...
         생성 코드가 있는 ShortenUrl 클래스는 저장소에 있는 형제들을 모를테니 저장소를 볼 수 있는 여기..
         또는 저장소에서 문제를 제기해야 하고,
         그 문제가 5번 계속되면 포기한다는 로직은 여기가 맞을 듯...
         */
        String shortenUrlKey = getUniqueShortenUrlKey();
        ShortenUrl shortenUrl = new ShortenUrl(shortenUrlRequestDto.getOriginalUrl(), shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);
        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto(shortenUrl);
        return shortenUrlResponseDto;
    }

    public ShortenUrlInformationDto getShortenInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
        // 저 Map이라는 놈은 키가 없어도 그냥 널을 주고 태연하네...뭔가 모이는 여기서 예외 처리를 하는게 맞을 듯...
        if (shortenUrl == null) throw new NotFoundShortenUrlException();
        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
        if (shortenUrl == null) throw new NotFoundShortenUrlException();
        // 근데 이게 여기 있는게 맞냐? 저장소 관리하는 놈이 꺼낼 때마다 하는게 맞지 않나?
        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenUrl);
        String originalUrl = shortenUrl.getOriginalUrl();
        return originalUrl;
    }

    private String getUniqueShortenUrlKey() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;
        String shortenUrlKey = "";
        while (count++ < MAX_RETRY_COUNT) {
            shortenUrlKey = ShortenUrl.generateShortenUrlKey();
            ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
            if (shortenUrl == null) {
                return shortenUrlKey;
            }
        }
        throw new LackOfShortenUrlKeyException();
    }

}
