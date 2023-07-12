import java.lang.annotation.*;
import java.lang.reflect.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}
public class AnnottionMethod {
    @Test(a=2, b=5)
    public void test (int a, int b){
        System.out.println("a= "+ a+" b= "+b);
    }

    public static void main(String[] args) {
        final Class <?> cls = AnnottionMethod.class;

        Method [] methods = cls.getMethods();
        for (Method method : methods){
            if(method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                int a = annotation.a();
                int b = annotation.b();
                try {
                    method.invoke(cls.newInstance(), a, b);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            };
        }
    }
}
