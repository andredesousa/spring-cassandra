package app.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("Solr")
@ExtendWith(MockitoExtension.class)
public class CassandraTests {

    @InjectMocks
    transient Cassandra solrConfig;

    @BeforeEach
    void beforeEach() {
        ReflectionTestUtils.setField(solrConfig, "contactPoints", "localhost");
        ReflectionTestUtils.setField(solrConfig, "port", 9042);
        ReflectionTestUtils.setField(solrConfig, "keySpace", "spring");
        ReflectionTestUtils.setField(solrConfig, "schemaAction", "CREATE_IF_NOT_EXISTS");
    }

    @Test
    @DisplayName("#cassandraSession returns a CqlSessionFactoryBean instance")
    void cassandraSession() {
        assertThat(solrConfig.cassandraSession()).isInstanceOf(CqlSessionFactoryBean.class);
    }
}
