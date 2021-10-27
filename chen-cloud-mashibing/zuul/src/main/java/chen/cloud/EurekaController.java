package chen.cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Deacription Eurekaeureka Api测试
 * @Author chenpengwei
 * @Date 2020/6/18 11:16 上午
 * @Version 1.0
 **/
@RestController
public class EurekaController {


    @GetMapping(value = "getHi")
    public String getHi(){
        return "providerHiasdasd";
    }



}
