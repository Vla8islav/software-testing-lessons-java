package com.softwaretestingtraning.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

interface PositiveTests { /* category marker */ }
interface SeleniumTests { /* category marker */ }
interface NegativeTests { /* category marker */ }
interface LongTests { /* category marker */ }
interface BrokenTests { /* category marker */ }
interface UnstableTests { /* category marker */ }

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Unstable {

    int value();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface DataSource {

    enum Type {
        RESOURCE,
        FILE
    }

    String value();
    Type type();

}
