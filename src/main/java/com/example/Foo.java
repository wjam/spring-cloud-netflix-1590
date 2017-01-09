package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public final class Foo {

    @NotNull
    @Size(min = 1)
    private final String item;

    private Foo(final String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "item='" + item + '\'' +
                '}';
    }

    @JsonCreator
    public static Foo valueOf(@JsonProperty("item") String item) {
        return new Foo(item);
    }
}
