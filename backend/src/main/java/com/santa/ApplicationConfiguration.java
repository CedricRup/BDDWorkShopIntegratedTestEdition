package com.santa;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

@Configuration
public class ApplicationConfiguration {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ApplicationArguments arguments;

    @Bean
    public FlywayMigrationStrategy EnableFlyWay() {
        return new OnDemandMigrationStrategy(arguments.containsOption("migrate"));
    }

    @Bean
    public FileSystem fileSystem() {
        return FileSystems.getDefault();
    }

    public class OnDemandMigrationStrategy implements FlywayMigrationStrategy {

        private boolean migrate;

        public OnDemandMigrationStrategy(boolean migrate) {
            this.migrate = migrate;
        }

        @Override
        public void migrate(Flyway flyway) {
            if (migrate) {
                flyway.migrate();
            }
        }
    }
}

