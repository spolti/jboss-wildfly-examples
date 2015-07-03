package com.examples.quartz;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@Startup
@Singleton
public class Main {

	Logger log = Logger.getLogger(Main.class.getCanonicalName());

	@PostConstruct
	void init() throws SchedulerException {

		try {
			log.info("Creating the job");
			JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("dummyJobName", "group1").build();

			log.info("Triggering the job");
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("dummyTriggerName", "group1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
					.build();

			// schedule it
			log.info("Schedulling the job");
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {
			log.severe(e.getStackTrace().toString());
		}

	}

}