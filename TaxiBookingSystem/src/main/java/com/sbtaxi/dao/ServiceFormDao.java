package com.sbtaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbtaxi.model.ServiceForm;
@Repository
public interface ServiceFormDao extends JpaRepository<ServiceForm, Integer> {

}
