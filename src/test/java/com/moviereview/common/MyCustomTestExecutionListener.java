package com.moviereview.common;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class MyCustomTestExecutionListener implements TestExecutionListener {
  @Override
  public void beforeTestMethod(TestContext testContext){
    MongoTemplate mongoTemplate = testContext.getApplicationContext().getBean(MongoTemplate.class);
    mongoTemplate.getDb().drop();
    mongoTemplate.createCollection("movie");
  }
}
