package domain.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserProfits {

    private Map<User, Profit> userProfits;

    public UserProfits(Map<User, Profit> userProfits) {
        this.userProfits = new HashMap<>(userProfits);
    }

    public Profit getUserProfit(User user) {
        return userProfits.get(user);
    }

    public Map<User, Profit> getUserProfits() {
        return Collections.unmodifiableMap(userProfits);
    }
}
