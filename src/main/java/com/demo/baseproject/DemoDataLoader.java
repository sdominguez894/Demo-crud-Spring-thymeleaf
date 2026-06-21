package com.demo.baseproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final DemoRecordRepository repository;

    public DemoDataLoader(DemoRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }

        List.of(
                record("Alpha Services", true, LocalDate.now().minusDays(14)),
                record("Bravo Logistics", true, LocalDate.now().minusDays(11)),
                record("Central Finance", false, LocalDate.now().minusDays(9)),
                record("Delta Support", true, LocalDate.now().minusDays(7)),
                record("Echo Operations", false, LocalDate.now().minusDays(5)),
                record("Forward Labs", true, LocalDate.now().minusDays(3)),
                record("Global Systems", true, LocalDate.now().minusDays(1)),
                record("Harbor Analytics", true, LocalDate.now().minusDays(25)),
                record("Iberia Manufacturing", false, LocalDate.now().minusDays(24)),
                record("Juniper Retail", true, LocalDate.now().minusDays(23)),
                record("Keystone Medical", true, LocalDate.now().minusDays(22)),
                record("Lumen Energy", false, LocalDate.now().minusDays(21)),
                record("Meridian Foods", true, LocalDate.now().minusDays(20)),
                record("Northstar Telecom", true, LocalDate.now().minusDays(19)),
                record("Oakline Construction", false, LocalDate.now().minusDays(18)),
                record("Pioneer Studio", true, LocalDate.now().minusDays(17)),
                record("Quartz Consulting", true, LocalDate.now().minusDays(16)),
                record("Riverstone Insurance", false, LocalDate.now().minusDays(15)),
                record("Summit Education", true, LocalDate.now().minusDays(13)),
                record("Terra Imports", true, LocalDate.now().minusDays(12)),
                record("Union Warehousing", false, LocalDate.now().minusDays(10)),
                record("Vertex Security", true, LocalDate.now().minusDays(8)),
                record("Westbridge Media", true, LocalDate.now().minusDays(6)),
                record("Xeno Research", false, LocalDate.now().minusDays(4)),
                record("Yellowstone Travel", true, LocalDate.now().minusDays(2))
        ).forEach(repository::save);
    }

    private DemoRecord record(String name, boolean active, LocalDate creationDate) {
        DemoRecord record = new DemoRecord();
        record.setName(name);
        record.setActive(active);
        record.setCreationDate(creationDate);
        return record;
    }
}
