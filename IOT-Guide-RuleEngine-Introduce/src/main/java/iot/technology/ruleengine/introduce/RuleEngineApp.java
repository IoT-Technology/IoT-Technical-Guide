package iot.technology.ruleengine.introduce;

import iot.technology.ruleengine.introduce.actions.AndroidPhone;
import iot.technology.ruleengine.introduce.actions.IPhone;
import iot.technology.ruleengine.introduce.actions.WindowsPhone;
import iot.technology.ruleengine.introduce.model.OSType;
import iot.technology.ruleengine.introduce.model.Phone;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jamesmsw
 * @date 2021/1/26 10:27 上午
 */
@Slf4j
public class RuleEngineApp {

    public static void main(String[] args) {
        RuleEngine ruleEngine = new RuleEngine();
        ruleEngine
                .registerRule(new IPhone())
                .registerRule(new AndroidPhone())
                .registerRule(new WindowsPhone());
        Phone androidPhone = new Phone(OSType.ANDROID);
        Phone phone = ruleEngine.rule(androidPhone);

        log.info("Output Phone: " + phone);
    }
}
