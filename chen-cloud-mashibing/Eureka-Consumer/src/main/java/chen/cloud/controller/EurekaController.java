package chen.cloud.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Deacription Eurekaeureka Api测试
 * @Author chenpengwei
 * @Date 2020/6/18 11:16 上午
 * @Version 1.0
 **/
@RestController
public class EurekaController {


    @Autowired
    DiscoveryClient springClient;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    //Spring Cloud提供
    @Autowired
    LoadBalancerClient lb;

    @GetMapping(value = "getHi")
    public String getHi(){
        return "Hi";
    }


    @GetMapping(value = "getProviderHi")
    public String getProviderHi(){

        String returnMessage = null;

        //获取所有服务节点名称
        List<String> services = springClient.getServices();
        for (String service : services) {
            System.out.println("url:" + service);
        }

        //获取制定服务节点详细信息
        List<ServiceInstance> provider = springClient.getInstances("eureka-provider");
        for (ServiceInstance serviceInstance : provider) {
            System.out.println("serviceInstance" + serviceInstance);
        }

        //服务不为空调用服务
        if (null != provider.get(0)) {

            ServiceInstance serviceInstance = provider.get(0);

            //服务地址
            String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getHi";

            RestTemplate restTemplate = new RestTemplate();
            returnMessage = restTemplate.getForObject(url, String.class);
        }

        List<InstanceInfo> instancesByVipAddress = eurekaClient.getInstancesByVipAddress("eureka-provider", false);
        for (InstanceInfo byVipAddress : instancesByVipAddress) {
            System.out.println(byVipAddress);
            System.out.println(byVipAddress.getStatus());
        }

        return returnMessage;
    }


    @GetMapping(value = "getProviderHi2")
    public Object getProviderHi2(){

        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance choose = lb.choose("eureka-provider");

        //拼接请求地址
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/getHi";

        //发送请求
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);

        return respStr;
    }

}
