package org.example.projekt3_gruppe_5.repositories;

import java.util.List;

import org.example.projekt3_gruppe_5.models.Damage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Repository håndterer direkte kommunikation med databasen via SQL queries.
@Repository
public class DamageRepository {
    //Søren

    private final JdbcTemplate jdbcTemplate;

    public DamageRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    public List<Damage> findAll() {     // Henter alle skader fra databasen.
        String sql = "select * from damage";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Damage.class));
    }

    public Damage findById(int id) {    // Finder en specifik skade baseret på ID.
        String sql = "select * from damage where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Damage.class), id);
    }

    public void insert(Damage damage) {
        System.out.print("damage:" + damage.getDamageId() + damage.getCarId());

        //  På hjemmesiden vælger man hvilken bil skaden skal tildels og ikke hvilken lease,
        //men i DB'en er den gemt efter lease, så vi skal finde den rigtige lease baseret på carId før vi kan indsætte det
        //  Dette gør vi med en subquery som finder alle lease's en bil har haft og sorterer dem med nyeste først og stopper listen efter 1 element
        //  På den måde får vi et og kun et leaseId, som forhåbentligt er det brugeren mente skaden skete under :)
        //  Søren
        String sql =
                    "insert into damage (lease_id, description, price, status) values " +
                    "((SELECT lease_id FROM lease WHERE car_id = ? ORDER BY lease_id DESC LIMIT 1" +
                    "), ?, ?, ?)";


        // Prepared statement bruges automatisk af JdbcTemplate for at beskytte mod SQL injection.
        jdbcTemplate.update(sql,
                damage.getCarId(),
                damage.getDescription(),
                damage.getPrice(),
                damage.getStatus()
        );
    }
    
    // Sletter skade fra databasen ud fra damageId.
    public void delete(Damage damage) {
        String sql = "delete from damage where id = ?";
        jdbcTemplate.update(sql, damage.getDamageId());
    }
}
