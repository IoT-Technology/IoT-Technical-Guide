package iot.technology.custom.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author james mu
 * @date 2020/8/10 15:08
 */
@Data
@NoArgsConstructor
public class Session {
    private String clientId;

    public Session(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return clientId;
    }
}
