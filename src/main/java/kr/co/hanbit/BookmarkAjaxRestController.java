package kr.co.hanbit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookmarkAjaxRestController {
    private List<Bookmark> bookmarks = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST, path = "/bookmark")
    public String registerBookmark(@RequestBody Bookmark bookmark) {
        //스프링 프레임워크의 Jackson이라는 컨버터가 Json을 String으로 만든 내용을 Bookmark 클래스로 바꿔준다.
        //이때 클래스 필드와 Json 속성 이름이 같아야 자동 매칭된다...
        bookmarks.add(bookmark);
        return "registered";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookmarks")
    public List<Bookmark> getBookmarks() {
        //이번에는 반대로 List를 Json Array로, Bookmark 클래스를 Json 객체로 만들고, 그걸 다시 String으로 바꿔준다...
        return bookmarks;
    }
}
