package chen.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//配置手动刷新
@RefreshScope
public class ConfigCenterContorller {


    @Value("${myconfig}")
    String myconfig;

    @GetMapping(value = "getMyConfig")
    public String getHi(){
        return myconfig;
    }

}
