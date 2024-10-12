package com.engilyin.bestpractices.rest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to load sample JSONs stored as resources and used for passing
 * as parameter and comparison with actual result
 */
public class TestSampleLoader<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> sampleType;

    public TestSampleLoader(Class<T> sampleType) {
	this.sampleType = sampleType;
    }

    public Map<String, T> load(String sampleJsonFilepath) {
	var sampleJsonResource = new ClassPathResource(sampleJsonFilepath);
	try (InputStream is = sampleJsonResource.getInputStream()) {
	    var collectionType = mapper.getTypeFactory().constructParametricType(TestSamples.class, sampleType);

	    TestSamples<T> samples = mapper.readValue(is, collectionType);

	    return samples.sampleCollection();

	} catch (IOException e) {

	    throw new RuntimeException("Unable to load test samples from resource json: " + sampleJsonFilepath, e);
	}
    }

    public record TestSamples<T>(Map<String, T> sampleCollection) {
    }
}
