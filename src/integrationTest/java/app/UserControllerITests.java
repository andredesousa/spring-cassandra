package app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("User Resource")
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerITests {

    @Autowired
    transient MockMvc mockMvc;

    @BeforeAll
    static void startCassandraEmbedded() throws Exception { 
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(); 
    }

    @AfterAll
    static void stopCassandraEmbedded() throws Exception {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Test
    @Order(1)
    @DisplayName("/user (GET)")
    void getAllUsers() throws Exception {
        MvcResult response = mockMvc.perform(get("/user")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(2)
    @DisplayName("/user (POST)")
    void addUser() throws Exception {
        String user = "{\"id\":1,\"username\":\"username\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(post("/user").content(user).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(3)
    @DisplayName("/user/{id} (PUT)")
    void updateUser() throws Exception {
        String user = "{\"id\":1,\"username\":\"username\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(put("/user/1").content(user).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(4)
    @DisplayName("/user/{id} (GET)")
    void getUserById() throws Exception {
        MvcResult response = mockMvc.perform(get("/user/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(5)
    @DisplayName("/user/{id} (DELETE)")
    void deleteUser() throws Exception {
        MvcResult response = mockMvc.perform(delete("/user/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }
}
