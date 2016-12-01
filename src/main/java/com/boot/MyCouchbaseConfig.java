package com.boot;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableCouchbaseRepositories(basePackages={"com.boot"})
public class MyCouchbaseConfig extends AbstractCouchbaseConfiguration {
	
	//How to integrate the property files here (see spring boot pluaralsight course)
	public static final List<String> NODE_LIST = Arrays.asList("localhost");
	    public static final String BUCKET_NAME = "default";
	    public static final String BUCKET_PASSWORD = "";

	    @Override
	    protected List<String> getBootstrapHosts() {
	        return NODE_LIST;
	    }

	    @Override
	    protected String getBucketName() {
	        return BUCKET_NAME;
	    }

	    @Override
	    protected String getBucketPassword() {
	        return BUCKET_PASSWORD;
	    }

	    @Override
	    protected Consistency getDefaultConsistency() {
	        return Consistency.READ_YOUR_OWN_WRITES;
	    }
	    
	    @Override
	    public String typeKey() {
	    	return "dataType"; //field name becomes "dataType"
	        //return MappingCouchbaseConverter.TYPEKEY_SYNCGATEWAY_COMPATIBLE;//field name becomes "javaClass" 
	    }
	    
	    @Bean
	    public LocalValidatorFactoryBean validator() {
	        return new LocalValidatorFactoryBean();
	    }

	    @Bean
	    public ValidatingCouchbaseEventListener validationEventListener() {
	        return new ValidatingCouchbaseEventListener(validator());
	    }
	    
}
