package com.example.demo.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Org;
@Repository
public interface OrgRepository extends CrudRepository<Org,UUID>{

}
