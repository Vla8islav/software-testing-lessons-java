package com.softwaretestingtraning.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by vla8islav on 19.12.16.
 */
public class TagsTests {
}
interface PositiveTests { /* category marker */ }
interface SeleniumTests { /* category marker */ }
interface NegativeTests { /* category marker */ }
interface LongTests { /* category marker */ }
interface BrokenTests { /* category marker */ }
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface NeedsRepetition {

}
