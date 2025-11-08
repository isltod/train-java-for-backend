package kr.co.mystore.product.management.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public interface ProductRepository {

    // 근데 이 추상 메서드들은 왜 다 범위 한정자가 없는거냐? 인터페이스는 그거 상관 없는건가?
    Product add(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByNameContainig(String name);
    Product update(Product product);
    void delete(Long id);

}
