package com.itv.dao;

import com.itv.entity.Advertiser;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiserDao {
    private static final String CREATE_ADVERTISER_TABLE = "CREATE TABLE IF NOT EXISTS advertiser ("
            + "id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "name VARCHAR(255))";
    private static final String INSERT_ADVERTISER = "INSERT INTO advertiser (name) VALUES ('%s')";
    private static final String SELECT_ADVERTISER_IDS = "SELECT * FROM advertiser WHERE id IN (%s)";


    private JdbcTemplate jdbcTemplate;

    public AdvertiserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_ADVERTISER_TABLE);
    }

    public void insert(String name) {
        jdbcTemplate.execute(String.format(INSERT_ADVERTISER, name));
    }

    public List<Advertiser> getByIds(List<Integer> ids) {
        return jdbcTemplate.query(String.format(SELECT_ADVERTISER_IDS, ids.stream().map(String::valueOf).collect(Collectors.joining(","))),
                (rs, rowNum) -> new Advertiser
                        .Builder()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .build());
    }
}
