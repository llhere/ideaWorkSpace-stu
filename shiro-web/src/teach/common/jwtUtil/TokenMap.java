package teach.common.jwtUtil;

import org.apache.shiro.authc.UsernamePasswordToken;
import teach.code.model.User;

import java.util.HashMap;
import java.util.Map;

public class TokenMap {

    private static Map<String, User> tokenHashMap = new HashMap<>();
    private TokenMap() {

    }

    public static Map<String, User> getInstance() {
        return tokenHashMap;
    }

}
