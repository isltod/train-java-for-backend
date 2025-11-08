package kr.co.shorten_url_service.Domain;

// 여기는 아무 딱지가 없지만, 이걸 구현하는 놈들이 딱지 붙어있고 실제로 그것들이 가서 붙는다...
public interface ShortenUrlRepository {

    void saveShortenUrl(ShortenUrl shortenUrl);

    ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);
}
