package kr.co.mystore.product.management;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    @Profile("prod")
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            // 위에 args가 정확히 뭐지? 아무튼 여기서 정의되는 내용을 넘겨준다는 얘긴가?
            Connection connection = dataSource.getConnection();
        };
    }

}
