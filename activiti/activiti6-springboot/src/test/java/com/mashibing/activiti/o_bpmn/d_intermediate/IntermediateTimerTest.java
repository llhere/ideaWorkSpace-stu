package com.mashibing.activiti.o_bpmn.d_intermediate;

import com.mashibing.activiti.ApplicationTests;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * <p>定时器中间事件</p>
 *
 * @author 孙志强
 * @date 2020-07-27 22:33
 */
public class IntermediateTimerTest extends ApplicationTests {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    private final String bpmnNameAndKey = "intermediateTimer";
    @Test
    public void deployment() {
        Deployment deployment = repositoryService.createDeployment().name("请假流程")
                .addClasspathResource("processes/" + bpmnNameAndKey + ".bpmn")
                .addClasspathResource("processes/" + bpmnNameAndKey + ".png")
                .category("HR")
                .deploy();
        System.out.println("部署ID\t"+deployment.getId());
        System.out.println("部署分类\t"+deployment.getCategory());
        System.out.println("部署名称\t"+deployment.getName());
    }
    @Test
    public void start() {
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(bpmnNameAndKey);
        System.out.println("流程实例ID\t"+processInstance.getId());
        System.out.println("流程实例ID\t"+processInstance.getProcessInstanceId());
        System.out.println("流程定义ID\t"+processInstance.getProcessDefinitionId());
    }

    @Test
    public void compleTask() {
        String taskId = "22505";
        taskService.complete(taskId);
    }

    @Test
    public void signal(){
        runtimeService.signalEventReceived("contactChangeSignal");
    }
}
