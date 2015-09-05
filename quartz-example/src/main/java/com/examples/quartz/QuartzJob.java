package com.examples.quartz;

import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

	Logger log = Logger.getLogger(QuartzJob.class.getCanonicalName());

	public QuartzJob() {}
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		int count = 0;
		
		while (count <= 100) {
			log.info("XXXXXXXXXXXXXXXXXXXXXXXX Job 1 Running....");
			try {
				
				Thread.sleep(900000000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}

	}
}