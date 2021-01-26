package iot.technology.ruleengine.introduce.actions;

import iot.technology.ruleengine.introduce.RuleI;
import iot.technology.ruleengine.introduce.model.OSType;
import iot.technology.ruleengine.introduce.model.Phone;

/**
 * @author jamesmsw
 * @date 2021/1/26 10:02 上午
 */
public class IPhone implements RuleI<Phone, Phone> {

    @Override
    public boolean matches(Phone input) {
        return input.getOsType().equals(OSType.IOS);
    }

    @Override
    public Phone process(Phone input) {
        input.setModel("iphone 12");
        return input;
    }
}
