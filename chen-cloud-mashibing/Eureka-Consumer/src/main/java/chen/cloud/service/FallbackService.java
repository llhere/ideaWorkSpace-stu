package chen.cloud.service;

import chen.cloud.Feign.ProviderFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/9/2 8:13 下午
 * @Version 1.0
 **/
@Service
public class FallbackService {

    @Autowired
    ProviderFeign providerFeign;


    @HystrixCommand(defaultFallback = "back")
    public String getProviderHi2() {
            int i = 1/0;
        return providerFeign.getHi();
    }



    public String back(){
        return "back失败！！";
    }

}
