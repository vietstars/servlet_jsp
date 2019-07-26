package validates;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface InputValidator {
   public String name();
   public int min();
   public int max();
   public String msg();
}