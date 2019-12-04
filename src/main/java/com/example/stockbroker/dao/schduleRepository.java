package com.example.stockbroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface schduleRepository extends JpaRepository<schedule,Integer> {
    schedule findScheduleByStocktableid(Integer stocktableid);
}
