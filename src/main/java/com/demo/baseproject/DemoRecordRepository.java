package com.demo.baseproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoRecordRepository extends JpaRepository<DemoRecord, Long> {

    List<DemoRecord> findByNameContainingIgnoreCaseOrderByIdAsc(String name);

    List<DemoRecord> findAllByOrderByIdAsc();
}
