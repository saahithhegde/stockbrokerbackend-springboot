package com.example.stockbroker.dao;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<user,Integer> {

   List<user> findUsersByEmail(String email);

}
