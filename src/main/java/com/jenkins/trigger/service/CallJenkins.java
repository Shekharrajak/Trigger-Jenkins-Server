package com.jenkins.trigger.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildCause;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.View;

// refere links for more info :
// https://stackoverflow.com/questions/20459375/how-to-post-an-authenticated-jenkins-job-using-java
// https://github.com/Shekharrajak/java-client-api

@RestController
public class CallJenkins {

    private Map<String, Job> jobs;
	@RequestMapping(value = "/getJobs", method = RequestMethod.GET)
	public Object getBuild() throws URISyntaxException, IOException {

		JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8081"), "shekharrajak", "om.");
		 jobs = jenkinsServer.getJobs();
		 return jobs;
	}

    private Map<String, View> views;
	@RequestMapping(value = "/getView", method = RequestMethod.GET)
	public Object getViews() throws URISyntaxException, IOException {

		JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8081"), "shekharrajak", "om.");
		views = jenkinsServer.getViews();
		 return views;
	}
	
    private Job job;
    private JobWithDetails jobWithDetails;
	@RequestMapping(value = "/getJob/{job_name}", method = RequestMethod.GET)
	public Object getJob(@PathVariable("job_name") String job_n) throws URISyntaxException, IOException {


		JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8081"), "shekharrajak", "om.");

//		jenkinsServer.createFolder("First-Folder");
		jobWithDetails = jenkinsServer.getJob(job_n);
		
        BuildWithDetails details = jobWithDetails.getFirstBuild().details();
        List<BuildCause> causes = details.getCauses();
        BuildCause buildCause = causes.get(0);
        
        
//		return jobWithDetails.getBuilds();
        return jobWithDetails.getLastStableBuild();
	}    
	
	@RequestMapping(value = "/createJob/{job_name}", method = RequestMethod.GET)
	public Object createJob(@PathVariable("job_name") String job_n) throws URISyntaxException, IOException {


		JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8081"), "shekharrajak", "om.");
	    public JenkinsRule jenkinsRule = new JenkinsRule();
//		jenkinsServer.createFolder("First-Folder");
		jobWithDetails = jenkinsServer.getJob(job_n);
		
        BuildWithDetails details = jobWithDetails.getFirstBuild().details();
        List<BuildCause> causes = details.getCauses();
        BuildCause buildCause = causes.get(0);
        
        
//		return jobWithDetails.getBuilds();
        return jobWithDetails.getLastStableBuild();
	}    
		
	
	
}
