package com.example.demo.Repositories;

import com.example.demo.Models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    List<Skill> findAll();
    //

    Skill findById(int id);
}
