import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MainReflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter class name:");
        String className = "model.Resume";
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        System.out.println("Created object of: " + clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Fields: ");
        for (Field field : fields) {
            System.out.println("Type: " + field.getName ());
            System.out.println("Type: " + field.getType());
        }
        Class<?> classResume = Class.forName("model.Resume");
        Object r = classResume.getDeclaredConstructor().newInstance();
        System.out.println("drdfg");
        Method method = classResume.getMethod("toString");
        System.out.println(method.invoke(r));
    }
}
