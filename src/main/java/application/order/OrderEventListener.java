package application.order;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderEventListener extends AbstractMongoEventListener<Order> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Order> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setDate(LocalDateTime.now());
        }
        super.onBeforeSave(event);
    }
}
