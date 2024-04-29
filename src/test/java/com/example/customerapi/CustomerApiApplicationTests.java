package com.example.customerapi;

import com.example.customerapi.testcontainer.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = TestContainersConfig.class)
class CustomerApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
