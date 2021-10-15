package iot.technology.thingsboard.ruleengine.service;

import iot.technology.actor.ActorSystem;
import iot.technology.actor.core.ActorRef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author mushuwei
 */
@Service
@Slf4j
public class ActorSystemContext {

    private ActorSystem system;

    private ActorRef appActor;

    private int actorThroughput;

}
