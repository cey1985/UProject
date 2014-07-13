/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.creditcard;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;

/**
 * Story class of CreditCardValidator object embedded in JUnit.
 * @author Cyber
 */
public class CreditCardValidatorStory extends JUnitStory
{
	private static final int DEFAULT_THREADS_COUNT_FOR_JBEHAVE = 2;
	private final CrossReference xRef = new CrossReference();
	
	public CreditCardValidatorStory()
	{
		configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
		.doIgnoreFailureInView(false).useThreads(DEFAULT_THREADS_COUNT_FOR_JBEHAVE);
	}
	
	@Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "false");
        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters();
        // factory to allow parameter conversion and loading from external
        // resources (used by StoryParser too)
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters);
        // add custom converters
        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ExamplesTableConverter(examplesTableFactory));
 
        return new MostUsefulConfiguration()
                .useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats().withPathResolver(new ResolveToPackagedName())
                                .withViewResources(viewResources)
                                .withFailureTrace(true).withFailureTraceCompression(true).withCrossReference(xRef))
                .useParameterConverters(parameterConverters)
                .useStepMonitor(xRef.getStepMonitor());
    }
 
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new CreditCardValidatorCheckSteps());
    }
}
