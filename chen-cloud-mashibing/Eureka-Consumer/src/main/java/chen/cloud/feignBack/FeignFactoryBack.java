package chen.cloud.feignBack;

import chen.cloud.Feign.ProviderFeign;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/9/2 7:19 下午
 * @Version 1.0
 **/

@Component
public class FeignFactoryBack implements FallbackFactory<ProviderFeign> {


    @Override
    public ProviderFeign create(Throwable cause) {
        return new ProviderFeign() {
            @Override
            public String getHi() {

                System.out.println(cause);
                ToStringBuilder.reflectionToString(cause);
                cause.printStackTrace();
                return "失败!!";
            }
        };
    }

}
