package kr.co.mystore.product.management.infrastructure;

import kr.co.mystore.product.management.domain.EntityNotFountException;
import kr.co.mystore.product.management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class DatabaseProductRepository implements kr.co.mystore.product.management.domain.ProductRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 근데 이 의존성 주입이라는 것이 정확히 하는 일이 뭐냐?
    @Autowired
    public DatabaseProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Product add(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // namedParameter는 아이디를 받아오는 것이 아니라 그냥 ? 대신 이름을 사용할 수 있도록 해준다...그래도 이게 어디냐...
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate.update(
                "INSERT INTO products (name, price, amount) VALUES (:name, :price, :amount)",
                namedParameter, keyHolder
        );
        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);
        return product;
    }

    public Product findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        Product product = null;
        try {
            product = namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, name, price, amount FROM products WHERE id=:id",
                    namedParameter, new BeanPropertyRowMapper<>(Product.class)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFountException("Product를 찾지 못했습니다.");
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT id, name, price, amount FROM products",
                new BeanPropertyRowMapper<>(Product.class)
        );
        return products;
    }

    public List<Product> findByNameContainig(String name) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%" + name + "%");
        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT id, name, price, amount FROM products WHERE name LIKE :name",
                namedParameter, new BeanPropertyRowMapper<>(Product.class)
        );
        return products;
    }

    public Product update(Product product) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate.update(
                "UPDATE products SET name=:name, price=:price, amount=:amount WHERE id=:id",
                namedParameter
        );
        return product;
    }

    public void delete(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(
                "DELETE FROM products WHERE id=:id",
                namedParameter
        );
    }

}
