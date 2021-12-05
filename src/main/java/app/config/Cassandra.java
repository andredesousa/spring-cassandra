package app.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class Cassandra extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.contactpoints}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.keyspacename}")
    private String keySpace;

    @Value("${spring.data.cassandra.schemaaction}")
    private String schemaAction;

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean bean = new CqlSessionFactoryBean();

        bean.setContactPoints(getContactPoints());
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setKeyspaceDrops(getKeyspaceDrops());
        bean.setKeyspaceName(getKeyspaceName());
        bean.setLocalDatacenter(getLocalDataCenter());
        bean.setPort(getPort());
        bean.setSessionBuilderConfigurer(null);

        return bean;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(schemaAction);
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] { "app.entity" };
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification pandaCoopKeyspace = CreateKeyspaceSpecification.createKeyspace(getKeyspaceName());
        pandaCoopKeyspace.ifNotExists().withNetworkReplication(DataCenterReplication.of(getLocalDataCenter(), 1L));

        return List.of(pandaCoopKeyspace);
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
        return keySpace;
    }
}
