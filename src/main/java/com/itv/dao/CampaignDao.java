package com.itv.dao;

import com.itv.entity.Campaign;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class CampaignDao {
    private static final String CREATE_CAMPAIGN_TABLE = "CREATE TABLE IF NOT EXISTS campaign ("
            + "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "advertiser_id          INTEGER,"
            + "demographic_profile_id INTEGER,"
            + "spot_length            INTEGER,"
            + "target_tvr             INTEGER,"
            + "FOREIGN KEY(advertiser_id) REFERENCES advertiser(id))";
    private static final String INSERT_CAMPAIGN = "INSERT INTO campaign (advertiser_id, demographic_profile_id, spot_length, target_tvr) "
            + "VALUES (%d, %d, %d, %d)";
    private static final String SELECT_CAMPAIGN = "SELECT * FROM campaign WHERE id IN (%s)";

    private JdbcTemplate jdbcTemplate;

    public CampaignDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(CREATE_CAMPAIGN_TABLE);
    }

    public void insert(int advertiserId, int demographicProfileId, int spotLength, int targetTvr) {
        jdbcTemplate.execute(String.format(INSERT_CAMPAIGN, advertiserId, demographicProfileId, spotLength, targetTvr));
    }

    public List<Campaign> getByIds(List<Integer> ids) {
        return jdbcTemplate.query(String.format(SELECT_CAMPAIGN, ids.stream().map(String::valueOf).collect(Collectors.joining(","))),
                (rs, rowNum) -> new Campaign.Builder()
                        .withId(rs.getInt("id"))
                        .withAdvertiserId(rs.getInt("advertiser_id"))
                        .withDemographicProfile(rs.getInt("demographic_profile_id"))
                        .withSpotLength(rs.getInt("spot_length"))
                        .withTargetTVR(rs.getInt("target_tvr"))
                        .build());
    }
}
