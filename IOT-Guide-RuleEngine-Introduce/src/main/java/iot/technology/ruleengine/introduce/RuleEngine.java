package iot.technology.ruleengine.introduce;

import iot.technology.ruleengine.introduce.model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jamesmsw
 * @date 2021/1/26 10:22 上午
 */
public class RuleEngine {

    List<RuleI<Phone, Phone>> rules;

    public RuleEngine() {
        rules = new ArrayList<>();
    }

    public Phone rule(Phone phone) {
        return rules.stream()
                .filter(rule -> rule.matches(phone))
                .map(rule -> rule.process(phone))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Matching rule found"));
    }

    public RuleEngine registerRule(RuleI<Phone, Phone> rule) {
        rules.add(rule);
        return this;
    }
}
