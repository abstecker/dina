package com.example.dina.byte_buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;

public class ByteBuddyExample {
  public static void main(String[] args) {
    // Step 1: Generate the class
    DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
        .subclass(Object.class)
        .name("GeneratedClass")
        .method(ElementMatchers.named("toString"))
        .intercept(FixedValue.value("Hello World!"))
        .make();

    // Step 2: Save the generated class to a file
    try {
      dynamicType.saveIn(new File("src/main/java/com/example/dina/byte_buddy/generated"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}