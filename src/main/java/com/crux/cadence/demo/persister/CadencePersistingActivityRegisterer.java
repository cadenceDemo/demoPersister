package com.crux.cadence.demo.persister;

import com.crux.cadence.demo.persister.activity.SaveWeatherActivityImpl;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CadencePersistingActivityRegisterer implements ApplicationListener<ContextRefreshedEvent> {
    @NonNull
    private String taskList;
    @NonNull
    WorkflowClient workflowClient;
    @NonNull
    SaveWeatherActivityImpl saveWeatherActivity;

    public CadencePersistingActivityRegisterer(@Value("${app.cadence.tasklist}") String taskList,
                                               WorkflowClient workflowClient,
                                               SaveWeatherActivityImpl saveWeatherActivity) {
        this.taskList = taskList;
        this.workflowClient = workflowClient;
        this.saveWeatherActivity = saveWeatherActivity;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(taskList);
        worker.registerActivitiesImplementations(saveWeatherActivity);
        factory.start();
    }
}