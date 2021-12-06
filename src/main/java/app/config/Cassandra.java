package app.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class Cassandra extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.contactpoints}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.keyspacename}")
    private String keySpaceName;

    @Value("${spring.data.cassandra.schemaaction}")
    private String schemaAction;

    @Override
    public String[] getEntityBasePackages() {
        return new String[] { "app.entity" };
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(schemaAction);
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getKeyspaceName() {
        return keySpaceName;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(CreateKeyspaceSpecification
            .createKeyspace(getKeyspaceName())
            .ifNotExists()
            .withSimpleReplication()
        );
    }
}
