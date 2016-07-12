package com.bank.core;

import com.bank.Client;

public class ClientImpl implements Client {
    private final String id;
    private final String name;

    public ClientImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientImpl client = (ClientImpl) o;

        return id.equals(client.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Client "+ name + " with id " + id;
    }
}
