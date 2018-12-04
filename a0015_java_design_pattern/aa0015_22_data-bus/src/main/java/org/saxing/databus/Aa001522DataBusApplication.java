package org.saxing.databus;

import org.saxing.databus.data.MessageData;
import org.saxing.databus.data.StartingData;
import org.saxing.databus.data.StoppingData;
import org.saxing.databus.members.MessageCollectorMember;
import org.saxing.databus.members.StatusMember;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

/**
 * main
 *
 * @author saxing 2018/12/4 11:02
 */
//@SpringBootApplication
public class Aa001522DataBusApplication {

    public static void main(String[] args) {
        final DataBus bus = DataBus.getInstance();
        bus.subscribe(new StatusMember(1));
        bus.subscribe(new StatusMember(2));

        final MessageCollectorMember foo = new MessageCollectorMember("Foo");
        final MessageCollectorMember bar = new MessageCollectorMember("Bar");
        bus.subscribe(foo);
        bus.publish(StartingData.of(LocalDateTime.now()));
        bus.publish(MessageData.of("Only Foo should see this"));
        bus.subscribe(bar);
        bus.publish(MessageData.of("Foo and Bar should see this"));
        bus.unsubscribe(foo);
        bus.publish(MessageData.of("Only Bar should see this"));
        bus.publish(StoppingData.of(LocalDateTime.now()));

    }
}
