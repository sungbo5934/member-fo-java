package com.ssb.member.comm.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;

@Configuration
public class AwsClientConfig {
	
	@Value("${aws.credential.accessKey}")
	private String awsAccessKeyId;
	
	@Value("${aws.credential.secretKey}")
	private String awsSecretAccessKey;
	
	@Value("${aws.endpoint}")
	private String endpoint;
	
	@Value("${aws.region}")
	private String region;

	private AWSCredentials awsCredentials;
	
	private AWSCredentialsProvider awsCredentialsProvider;
	
	private EndpointConfiguration endpointConfiguration;
	
	@PostConstruct
	public void init() {
		awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
		awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		endpointConfiguration = new EndpointConfiguration(endpoint, region);
	}
	
	@Bean
	public AmazonDynamoDB getAmazonDynamoDB() {
		
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withCredentials(awsCredentialsProvider)
				.withEndpointConfiguration(endpointConfiguration).build();
			
	}
}
