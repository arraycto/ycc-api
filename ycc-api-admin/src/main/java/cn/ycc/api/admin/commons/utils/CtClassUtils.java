package cn.ycc.api.admin.commons.utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 19:59
 */
public abstract class CtClassUtils {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    public static String[] getMethodParameterNames(Class<?> type, String methodName, Class<?>[] paramTypes) {
        String[] paramTypesString = Arrays.stream(paramTypes).map(Class::getName).toArray(l -> new String[l]);
        return getMethodParameterNames(type.getName(), methodName, paramTypesString);
    }

    public static String[] getMethodParameterNames(Method method) {
        return getMethodParameterNames(method.getDeclaringClass(), method.getName(), method.getParameterTypes());
    }

    public static String[] getMethodParameterNames(String className, String methodName, String... paramTypes) {
        try {
            List<CtClass> ctParamTypes = new ArrayList<>();
            for (String paramType : paramTypes) {
                ctParamTypes.add(CLASS_POOL.get(paramType));
            }
            CtClass ctClass = CLASS_POOL.get(className);
            CtMethod method = ctClass.getDeclaredMethod(methodName, ctParamTypes.toArray(new CtClass[0]));

            CodeAttribute codeAttribute = method.getMethodInfo().getCodeAttribute();
            LocalVariableAttribute attribute = (LocalVariableAttribute)codeAttribute.getAttribute(LocalVariableAttribute.tag);

            int pos = Modifier.isStatic(method.getModifiers()) ? 0 : 1;

            String[] names = new String[method.getParameterTypes().length];
            for (int i = 0; i < names.length; i++) {
                names[i]=attribute.variableName(i+pos);
            }
            return names;
        } catch (Exception e) {
            throw new RuntimeException("获取方法名称出现异常",e);
        }
    }

}
