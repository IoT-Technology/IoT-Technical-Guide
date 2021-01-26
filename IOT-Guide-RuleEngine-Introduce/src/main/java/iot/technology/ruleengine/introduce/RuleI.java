package iot.technology.ruleengine.introduce;

/**
 * @author jamesmsw
 * @date 2021/1/26 9:51 上午
 */
public interface RuleI<I, O> {

    boolean matches(I input);

    O process(I input);
}
