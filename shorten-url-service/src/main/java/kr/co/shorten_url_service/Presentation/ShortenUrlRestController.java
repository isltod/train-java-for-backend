package kr.co.shorten_url_service.Presentation;

import jakarta.validation.Valid;
import kr.co.shorten_url_service.Application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortenUrlRestController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlRestController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlResponseDto> createShortenUrl(
            @Valid @RequestBody ShortenUrlRequestDto shortenUrlRequestDto
    ) {
        ShortenUrlResponseDto shortenUrlResponseDto = shortenUrlService.generateShortenUrl(shortenUrlRequestDto);
        return ResponseEntity.ok(shortenUrlResponseDto);
    }

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectShortenUrl(@PathVariable String shortenUrlKey) throws URISyntaxException {
        // 이 뒤로는 데이터만 다루니까, ResponseEntity의 상태는 여기서 다루는게 맞나?
        String originalUrl = shortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        URI redirectUrl = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping(value = "/shorten-url/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(@PathVariable String shortenUrlKey) {
        ShortenUrlInformationDto shortenUrlInformationDto = shortenUrlService.getShortenInformationByShortenUrlKey(
                shortenUrlKey
        );
        return ResponseEntity.ok(shortenUrlInformationDto);
    }

}
