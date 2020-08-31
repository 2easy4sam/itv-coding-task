package com.itv.dao;

import com.itv.entity.Break;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class BreakDao {
    private static final String CREATE_BREAK_TABLE = "CREATE TABLE IF NOT EXISTS break ("
            + "id               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "datetime         INTEGER,"
            + "duration_in_sec  INTEGER)";
    private static final String INSERT_BREAK = "INSERT INTO break (datetime, duration_in_sec) VALUES (%d, %d)";
    private static final String SELECT_BREAK = "SELECT * FROM break WHERE id = %d";
    private static final String SELECT_BREAK_IN_TIME_RANGE = "SELECT * FROM break WHERE datetime >= %d AND datetime <= %d";

    private JdbcTemplate jdbcTemplate;

    public BreakDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_BREAK_TABLE);
    }

    public void insert(long datetime, int durationInSec) {
        jdbcTemplate.execute(String.format(INSERT_BREAK, datetime, durationInSec));
    }

    public List<Break> getById(int id) {
        return jdbcTemplate.query(String.format(SELECT_BREAK, id), (rs, rowNum) -> map(rs));
    }

    public List<Break> getInRange(long start, long end) {
        return jdbcTemplate.query(String.format(SELECT_BREAK_IN_TIME_RANGE, start, end), (rs, rowNum) -> map(rs));
    }

    private Break map(ResultSet rs) throws SQLException {
        return new Break.Builder()
                .withId(rs.getInt("id"))
                .withDateTime(toLocalDateTime(rs.getLong("datetime")))
                .withDurationInSec(rs.getInt("duration_in_sec"))
                .build();
    }

    private LocalDateTime toLocalDateTime(Long epoch) {
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
