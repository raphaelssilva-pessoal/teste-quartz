package quartz.test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

public class Start {
	public static void main(String [] arg){
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job = newJob(Job1.class)
			      .withIdentity("job1", "group1")
			      .build();

			  // Trigger the job to run now, and then repeat every 40 seconds
			  Trigger trigger = newTrigger()
			      .withIdentity("trigger1", "group1")
			      .startNow()
			      .withSchedule(simpleSchedule()
			              .withIntervalInSeconds(40)
			              .repeatForever())
			      .build();

			  // Tell quartz to schedule the job using our trigger
			  scheduler.scheduleJob(job, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}	
	}
}
