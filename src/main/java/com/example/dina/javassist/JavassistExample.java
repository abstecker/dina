package com.example.dina.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.FileOutputStream;
import java.io.IOException;

public class JavassistExample {
  public static void main(String[] args) {
    try {
      CtClass ctClass = createClass();
      saveClass(ctClass, "src/main/java/com/example/dina/javassist/generated/GeneratedClass.class");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static CtClass createClass() throws Exception {
    ClassPool pool = ClassPool.getDefault();
    CtClass cc = pool.makeClass("com.example.dina.javassist.generated.GeneratedClass");
    CtMethod method = CtMethod.make("public String toString() { return \"Hello World!\"; }", cc);
    cc.addMethod(method);
    return cc;
  }

  private static void saveClass(CtClass ctClass, String filePath) throws IOException, CannotCompileException {
    byte[] byteCode = ctClass.toBytecode();
    try (FileOutputStream fos = new FileOutputStream(filePath)) {
      fos.write(byteCode);
    }
  }
}