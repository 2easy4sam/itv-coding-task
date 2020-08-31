package com.itv.dao;

import com.itv.entity.Rating;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class RatingDao {
    private static final String CREATE_RATING_TABLE = "CREATE TABLE IF NOT EXISTS rating ("
            + "id                       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "break_id                 INTEGER,"
            + "demographic_profile_id   INTEGER,"
            + "tvr                      INTEGER,"
            + "FOREIGN KEY(demographic_profile_id) REFERENCES demographic_profile(id),"
            + "FOREIGN KEY(break_id) REFERENCES break(id))";
    private static final String INSERT_RATING = "INSERT INTO rating (break_id, demographic_profile_id, tvr) VALUES (%d, %d, %d)";
    private static final String SELECT_RATING_BY_BREAK_ID = "SELECT * FROM rating WHERE break_id IN (%s)";

    private JdbcTemplate jdbcTemplate;

    public RatingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_RATING_TABLE);
    }

    public void insert(int breakId, int demographicProfileId, int tvr) {
        jdbcTemplate.execute(String.format(INSERT_RATING, breakId, demographicProfileId, tvr));
    }

    public List<Rating> getByBreakIds(List<Integer> breakIds) {
        return jdbcTemplate.query(String.format(SELECT_RATING_BY_BREAK_ID, breakIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                (rs, rowNum) -> new Rating.Builder()
                        .withId(rs.getInt("id"))
                        .withBreakId(rs.getInt("break_id"))
                        .withDemographicProfile(rs.getInt("demographic_profile_id"))
                        .withTvr(rs.getInt("tvr"))
                        .build());
    }
}
