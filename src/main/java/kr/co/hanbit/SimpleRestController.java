package kr.co.hanbit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
