package app.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("Cassandra")
@ExtendWith(MockitoExtension.class)
public class CassandraTests {

    @InjectMocks
    transient Cassandra solrConfig;

    @BeforeEach
    void beforeEach() {
        ReflectionTestUtils.setField(solrConfig, "contactPoints", "localhost");
        ReflectionTestUtils.setField(solrConfig, "port", 9042);
        ReflectionTestUtils.setField(solrConfig, "keySpaceName", "spring");
        ReflectionTestUtils.setField(solrConfig, "schemaAction", "CREATE_IF_NOT_EXISTS");
    }

    @Test
    @DisplayName("#getEntityBasePackages the entity package")
    void getEntityBasePackages() {
        assertThat(solrConfig.getEntityBasePackages()).isEqualTo(new String[] { "app.entity" });
    }

    @Test
    @DisplayName("#getSchemaAction returns the schema action")
    void getSchemaAction() {
        assertThat(solrConfig.getSchemaAction()).isEqualTo(SchemaAction.CREATE_IF_NOT_EXISTS);
    }

    @Test
    @DisplayName("#getContactPoints returns the server address")
    void getContactPoints() {
        assertThat(solrConfig.getContactPoints()).isEqualTo("localhost");
    }

    @Test
    @DisplayName("#getPort returns the port number")
    void getPort() {
        assertThat(solrConfig.getPort()).isEqualTo(9042);
    }

    @Test
    @DisplayName("#getKeyspaceName returns the key space name")
    void getKeyspaceName() {
        assertThat(solrConfig.getKeyspaceName()).isEqualTo("spring");
    }

    @Test
    @DisplayName("#getKeyspaceCreations returns a list of CreateKeyspaceSpecification")
    void getKeyspaceCreations() {
        assertThat(solrConfig.getKeyspaceCreations()).isInstanceOf(List.class);
    }
}
