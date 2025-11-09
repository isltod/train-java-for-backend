package kr.co.shorten_url_service.Presentation;

import kr.co.shorten_url_service.Application.ShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShortenUrlRestController.class)
class ShortenUrlRestControllerTest {

    @MockitoBean
    private ShortenUrlService shortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야 한다.")
    void redirectTest() throws Exception {
        String expectedUrl = "https://www.hanbit.co.kr";

        when(shortenUrlService.getOriginalUrlByShortenUrlKey(any()))
                .thenReturn(expectedUrl);

        mockMvc.perform(MockMvcRequestBuilders.get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedUrl));
    }
}