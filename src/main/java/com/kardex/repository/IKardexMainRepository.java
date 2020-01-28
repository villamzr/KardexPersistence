package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kardex.domain.MKardexMain;

public interface IKardexMainRepository extends JpaRepository<MKardexMain, Long>{
}
