package hnb.team.writenow.EventBus;

import com.squareup.otto.Bus;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class EventBus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private EventBus() {
        // No instances.
    }
}
