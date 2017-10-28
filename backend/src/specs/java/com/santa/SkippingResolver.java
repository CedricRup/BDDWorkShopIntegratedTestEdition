package com.santa;

import org.flywaydb.core.api.resolver.MigrationResolver;
import org.flywaydb.core.api.resolver.ResolvedMigration;
import org.flywaydb.core.internal.resolver.sql.SqlMigrationResolver;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SkippingResolver implements MigrationResolver {
    private SqlMigrationResolver resolver;
    private Predicate<ResolvedMigration> filter;

    public SkippingResolver(SqlMigrationResolver resolver, Predicate<ResolvedMigration> filter) {
        this.resolver = resolver;
        this.filter = filter;
    }

    @Override
    public Collection<ResolvedMigration> resolveMigrations() {
        return resolver.resolveMigrations().stream().filter(filter).collect(Collectors.toList());
    }
}
