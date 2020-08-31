package com.itv.dao;

import com.itv.entity.DemographicProfile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class DemographicProfileDao {
    private static final String CREATE_DEMOGRAPHIC_PROFILE_TABLE = "CREATE TABLE IF NOT EXISTS demographic_profile ("
            + "id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "gender   VARCHAR(10),"
            + "location VARCHAR(255),"
            + "age      INTEGER)";
    private static final String INSERT_DEMOGRAPHIC_PROFILE = "INSERT INTO demographic_profile (gender, location, age) "
            + "VALUES ('%s', '%s', %d)";
    private static final String SELECT_DEMOGRAPHIC_PROFILE_BY_IDS = "SELECT * FROM demographic_profile WHERE id IN (%s)";

    private JdbcTemplate jdbcTemplate;

    public DemographicProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_DEMOGRAPHIC_PROFILE_TABLE);
    }

    public void insert(String gender, String location, int age) {
        jdbcTemplate.execute(String.format(INSERT_DEMOGRAPHIC_PROFILE, gender, location, age));
    }

    public List<DemographicProfile> getByIds(List<Integer> ids) {
        return jdbcTemplate.query(String.format(SELECT_DEMOGRAPHIC_PROFILE_BY_IDS, ids.stream().map(String::valueOf).collect(Collectors.joining(","))),
                (rs, rowNum) -> new DemographicProfile.Builder()
                        .withId(rs.getInt("id"))
                        .withGender(rs.getString("gender"))
                        .withLocation(rs.getString("location"))
                        .withAge(rs.getInt("age"))
                        .build());
    }
}
