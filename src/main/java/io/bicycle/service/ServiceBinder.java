package io.bicycle.service;

import android.os.Binder;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:17 AM
 */
public class ServiceBinder<T> extends Binder {
    private final T service;

    public static <T> ServiceBinder<T> create(T service) {
        return new ServiceBinder<T>(service);
    }


    private ServiceBinder(T service) {
        this.service = service;
    }

    public T getService() {
        return this.service;
    }
}
