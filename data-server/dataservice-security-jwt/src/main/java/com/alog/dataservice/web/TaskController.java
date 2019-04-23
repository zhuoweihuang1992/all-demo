package com.alog.dataservice.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String newTasks(){
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject("http://localhost:8769/from", String.class);
        if(data != null && data != ""){
            String[] dataRole = data.split(",");
            if(dataRole.length > 0){
                boolean flag = Arrays.asList(dataRole).contains("local-dev");
                if(flag){
                    String serviceBdata = restTemplate.getForObject("http://localhost:8799/getAllUser", String.class);
                    String serviceAdata = restTemplate.getForObject("http://localhost:8769/from", String.class);
                    //System.out.println("返回两个服务的数据:"+serviceBdata+"||"+serviceAdata);
                    return "返回两个服务的数据:"+serviceBdata+"||"+serviceAdata;
                }
                return "没有该数据接口请求权限！";
            }else{
                System.out.println("进入else,请求http://localhost:8769/from");
                return restTemplate.getForObject("http://localhost:8769/from",String.class);
            }
        }
        return "请求失败！";
        //return "创建了一个新任务";
    }

    @PutMapping("/{taskId}")
    //@PreAuthorize("hasRole('ADMIN') AND hasRole('USER')")

    public String updateTasks(@PathVariable("taskId") Integer id){
        return "更新了一下id为："+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId") Integer id){
        return "删除了id为："+id+"的任务";
    }
}
