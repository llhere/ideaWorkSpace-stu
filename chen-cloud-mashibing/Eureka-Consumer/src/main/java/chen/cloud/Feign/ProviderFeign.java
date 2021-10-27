package chen.cloud.Feign;

import chen.cloud.feignBack.FeignFactoryBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/9/2 7:10 下午
 * @Version 1.0
 **/
@FeignClient(name = "Eureka-Provider", fallbackFactory = FeignFactoryBack.class)
public interface ProviderFeign {

    @GetMapping(value = "getHi")
    public String getHi();

}
