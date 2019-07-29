package validates;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InputValidatiorHandle {
	
	public Map<String, String> check(Object ob) {
		Map<String, String> error = new HashMap<String, String>();
		Field[] fields = ob.getClass().getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(InputValidator.class)) {
				InputValidator myAnn = field.getAnnotation(InputValidator.class);
				String name = myAnn.name();
				int min  = myAnn.min();
				int max  = myAnn.max();
				String msg  = myAnn.msg();
				try {
					if( field.get(ob).toString().length()<min || field.get(ob).toString().length()>max ) {
						error.put(name,msg);
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return error;
	}

}
