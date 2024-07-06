package com.moviereview.common.annotation;

import com.moviereview.common.MyCustomTestExecutionListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest(
    properties = "de.flapdoodle.mongodb.embedded.version=5.0.5",
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@AutoConfigureDataMongo
@TestExecutionListeners({
    MyCustomTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class
})
public abstract class AcceptanceTest {
  @LocalServerPort
  int port;

  @BeforeEach
  public void environmentSetUp() {
    RestAssured.port = port;
  }
}
