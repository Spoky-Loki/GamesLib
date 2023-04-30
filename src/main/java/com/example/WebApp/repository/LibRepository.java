package com.example.WebApp.repository;

import com.example.WebApp.models.Game;
import com.example.WebApp.models.Lib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface LibRepository extends JpaRepository<Lib, Long> {
    @Query("SELECT u FROM Lib u WHERE u.user_id = ?1")
    ArrayList<Lib> findAllByUserId(long id);
}
