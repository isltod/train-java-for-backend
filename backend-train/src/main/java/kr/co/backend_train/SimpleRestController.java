package kr.co.backend_train;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class SimpleRestController {
    @RequestMapping("/")
    public String Hello() {
        return "Hello <strong>Backend</strong>";
    }

    @RequestMapping("/bye")
    public String bye() {
        return "Bye~";
    }

    @RequestMapping("/article")
    public String createArticle(@RequestParam("title") String title, @RequestParam("content") String content) {
        return String.format("title=%s / content=%s", title, content);
    }

    @RequestMapping("/get-with-no-parameter")
    public String getWithNoPrarameter() {
        return "파라미터가 없는 GET 요청";
    }

    @RequestMapping("/redirectToTargeet")
    public ResponseEntity redirectToTarget() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("targetOfRedirect"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping("/targetOfRedirect")
    public String targetOfRedirect() {
        return "This is Redirect";
    }

    @RequestMapping(value = "/returnString", produces = "text/plain")
    public String returnString() {
        return "<string>문자열</string>을 리턴";
    }

    @RequestMapping("/returnBookmark")
    public Bookmark returnBookmark() {
        return new Bookmark();
    }
}
