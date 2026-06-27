package com.api.pro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.api.pro.entity.Rol;
import com.api.pro.repository.RolRepository;

@Service
public class RolService
        extends ICRUDImpl<Rol, Integer> {

    @Autowired
    private RolRepository repo;

    @Override
    JpaRepository<Rol, Integer> getRepository() {
        return repo;
    }
}