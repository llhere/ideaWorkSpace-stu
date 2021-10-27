package chen.cloud.feignBack;

import chen.cloud.Feign.ProviderFeign;
import org.springframework.stereotype.Component;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/9/2 7:19 下午
 * @Version 1.0
 **/

@Component
public class FeignBack implements ProviderFeign {


    @Override
    public String getHi() {
        return "失败啦！！！！";
    }

}
