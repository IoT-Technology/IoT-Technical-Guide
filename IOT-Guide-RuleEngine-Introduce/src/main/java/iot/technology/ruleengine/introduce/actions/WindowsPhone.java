package iot.technology.ruleengine.introduce.actions;

import iot.technology.ruleengine.introduce.RuleI;
import iot.technology.ruleengine.introduce.model.OSType;
import iot.technology.ruleengine.introduce.model.Phone;

/**
 * @author jamesmsw
 * @date 2021/1/26 10:20 上午
 */
public class WindowsPhone implements RuleI<Phone, Phone> {

    @Override
    public boolean matches(Phone input) {
        return input.getOsType().equals(OSType.WINDOWS);
    }

    @Override
    public Phone process(Phone input) {
        input.setModel("诺基亚 1100");
        return input;
    }
}
