package com.lohika.morning.lambda.architecture.spark.driver.service.speed;

import com.lohika.morning.lambda.architecture.spark.driver.configuration.SparkContextConfiguration;
import com.lohika.morning.lambda.architecture.spark.driver.configuration.SparkContextTestConfiguration;
import com.lohika.morning.lambda.architecture.spark.driver.context.AnalyticsSparkContext;
import java.net.URL;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SparkContextConfiguration.class, SparkContextTestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseStreamingTest {

    @Autowired
    private AnalyticsSparkContext analyticsSparkContext;

    protected AnalyticsSparkContext getAnalyticsSparkContext() {
        return analyticsSparkContext;
    }

    public String getPathForNewDataStream() {
        URL resource = this.getClass().getResource("/new-data-stream");

        return resource.getPath();
    }

    public String getPathToBatchView() {
        URL resource = this.getClass().getResource("/batch-views/batch-view.parquet");

        return resource.getPath();
    }

    @After
    public void tearDown() {
        analyticsSparkContext.getJavaStreamingContext().stop();
        analyticsSparkContext.getJavaSparkContext().stop();
    }

}
