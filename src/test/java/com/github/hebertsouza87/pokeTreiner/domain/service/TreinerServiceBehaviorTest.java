package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.PokeTreinerApplication;
import com.github.hebertsouza87.pokeTreiner.confg.TestConfig;
import org.flywaydb.core.Flyway;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TreinerServiceBehaviorTest extends JUnitStories {

    @Autowired
    private Flyway flyway;

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PokeTreinerApplication.class);
        return new SpringStepsFactory(configuration(), context);
    }

    @Override
    public List<String> storyPaths() {
        return List.of("com/github/hebertsouza87/pokeTreiner/domain/service/TreinerService.story");
    }

    @BeforeStory
    public void beforeStory() {
        flyway.clean();
        flyway.migrate();
    }
}