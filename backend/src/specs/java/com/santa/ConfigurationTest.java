package com.santa;


import com.santa.time.Clock;
import com.santa.utils.CukeClock;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.internal.dbsupport.DbSupport;
import org.flywaydb.core.internal.dbsupport.DbSupportFactory;
import org.flywaydb.core.internal.resolver.sql.SqlMigrationResolver;
import org.flywaydb.core.internal.util.Location;
import org.flywaydb.core.internal.util.Locations;
import org.flywaydb.core.internal.util.PlaceholderReplacer;
import org.flywaydb.core.internal.util.jdbc.JdbcUtils;
import org.flywaydb.core.internal.util.scanner.Scanner;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.util.List;

@Configuration
@ComponentScan("com.santa")
public class ConfigurationTest implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private PortConfiguration port = new PortConfiguration();
    private CukeClock clock = new CukeClock();

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent) {
        port.setPort(embeddedServletContainerInitializedEvent.getEmbeddedServletContainer().getPort());
    }

    @Bean
    public PortConfiguration portConfiguration() {
        return port;
    }

    @Bean
    public FlywayMigrationStrategy EnableFlyWay() {
        return new ConfigurationTest.TestStrategy();
    }

    @Bean
    public Clock clock() {
        return clock;
    }

    public class TestStrategy implements FlywayMigrationStrategy {

        @Override
        public void migrate(Flyway flyway) {
            changerLesResolvers(flyway);

            flyway.clean();
            flyway.migrate();
        }

        private void changerLesResolvers(Flyway flyway) {
            SqlMigrationResolver resolver = getResolverParDefaut(flyway);

            List<String> versionASkipper = java.util.Arrays.asList(
            );

            SkippingResolver test = new SkippingResolver(resolver, resolvedMigration -> {
                MigrationVersion version = resolvedMigration.getVersion();
                return version == null || !versionASkipper.contains(version.getVersion());
            });

            flyway.setSkipDefaultResolvers(true);
            flyway.setResolvers(test);
        }

        private SqlMigrationResolver getResolverParDefaut(Flyway flyway) {
            Connection connection = JdbcUtils.openConnection(flyway.getDataSource());
            DbSupport dbSupport = DbSupportFactory.createDbSupport(connection, false);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Scanner scanner = new Scanner(classLoader);
            Location location = new Locations(flyway.getLocations()).getLocations().get(0);
            PlaceholderReplacer placeholderReplacer = new PlaceholderReplacer(flyway.getPlaceholders(), flyway.getPlaceholderPrefix(), flyway.getPlaceholderSuffix());
            return new SqlMigrationResolver(dbSupport, scanner, location, placeholderReplacer, flyway);
        }
    }
}
