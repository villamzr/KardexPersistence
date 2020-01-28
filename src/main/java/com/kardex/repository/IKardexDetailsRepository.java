package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kardex.domain.MKardexDetails;

public interface IKardexDetailsRepository extends JpaRepository<MKardexDetails, Long> {
}
