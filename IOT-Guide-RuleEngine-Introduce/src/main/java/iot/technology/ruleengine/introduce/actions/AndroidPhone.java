package iot.technology.ruleengine.introduce.actions;

import iot.technology.ruleengine.introduce.RuleI;
import iot.technology.ruleengine.introduce.model.OSType;
import iot.technology.ruleengine.introduce.model.Phone;

/**
 * @author jamesmsw
 * @date 2021/1/26 10:16 上午
 */
public class AndroidPhone implements RuleI<Phone, Phone> {

    @Override
    public boolean matches(Phone input) {
        return input.getOsType().equals(OSType.ANDROID);
    }

    @Override
    public Phone process(Phone input) {
        input.setModel("华为 Mate40 pro");
        return input;
    }
}
