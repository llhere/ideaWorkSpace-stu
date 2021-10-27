package chen.cloud.controller;

import chen.cloud.Feign.ProviderFeign;
import chen.cloud.service.FallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Deacription Eurekaeureka Api测试
 * @Author chenpengwei
 * @Date 2020/6/18 11:16 上午
 * @Version 1.0
 **/
@RestController
@RequestMapping("provider")
public class ProviderFeignController {


    @Autowired
    ProviderFeign providerFeign;

    @Autowired
    FallbackService fallbackService;

    @RequestMapping("getProviderHi")
    public String getProviderHi(){
        return providerFeign.getHi();
    }

    @RequestMapping("getProviderHi2")
    public String getProviderHi2(){
        return fallbackService.getProviderHi2();
    }


}
