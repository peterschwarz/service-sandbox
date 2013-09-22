package io.bicycle;

import io.bicycle.service.ServiceStarter;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:22 AM
 */
public class HelloServiceStarter extends ServiceStarter {
    public HelloServiceStarter() {
        super(HelloService.class);
    }
}
