package com.itv.dao;

import com.itv.entity.PYORequest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PYORequestDao {
    private static final String CREATE_PYO_REQUEST_TABLE = "CREATE TABLE IF NOT EXISTS pyo ("
            + "id           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "break_id     INTEGER,"
            + "campaign_id  INTEGER,"
            + "FOREIGN KEY(break_id) REFERENCES break(id),"
            + "FOREIGN KEY(campaign_id) REFERENCES campaign(id))";

    private static final String INSERT_PYO_REQUEST = "INSERT INTO pyo (break_id, campaign_id) VALUES (%d, %d)";
    private static final String SELECT_PYO_REQUEST = "SELECT * FROM pyo WHERE id = %d";
    private static final String SELECT_PYO_REQUEST_BY_BREAK_IDS = "SELECT * FROM pyo WHERE break_id IN (%s)";
    private static final String DELETE_PYO_REQUEST_BY_ID = "DELETE FROM pyo WHERE id = %d";

    private JdbcTemplate jdbcTemplate;

    public PYORequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_PYO_REQUEST_TABLE);
    }

    public void insert(int breakId, int campaignId) {
        jdbcTemplate.execute(String.format(INSERT_PYO_REQUEST, breakId, campaignId));
    }

    public List<PYORequest> getById(int id) {
        return jdbcTemplate.query(String.format(SELECT_PYO_REQUEST, id), (rs, rowNum) -> map(rs));
    }

    public List<PYORequest> getByBreakIds(List<Integer> breakIds) {
        return jdbcTemplate.query(String.format(SELECT_PYO_REQUEST_BY_BREAK_IDS, breakIds.stream().map(String::valueOf).collect(Collectors.joining(","))), (rs, rowNum) -> map(rs));
    }

    public void delete(int id) {
        jdbcTemplate.execute(String.format(DELETE_PYO_REQUEST_BY_ID, id));
    }

    private PYORequest map(ResultSet rs) throws SQLException {
        return new PYORequest.Builder()
                .withId(rs.getInt("id"))
                .withBreakId(rs.getInt("break_id"))
                .withCampaignId(rs.getInt("campaign_id"))
                .build();
    }
}
