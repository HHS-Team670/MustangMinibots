package pi;

import org.python.util.PythonInterpreter;
import org.python.core.JavaImporter;
import org.python.core.PyObject;

public class JythonTester {
  public static void main(String[] args) {
//    try(PythonInterpreter pyInterp = new PythonInterpreter()) {
//      pyInterp.exec("m1 = PiMotor.Motor(MOTOR1,1)");
//      pyInterp.exec("print('imported time')");
//    }
	  PythonInterpreter p = new PythonInterpreter();
	  p.exec("import RPi.GPIO as GPIO");
  }
}