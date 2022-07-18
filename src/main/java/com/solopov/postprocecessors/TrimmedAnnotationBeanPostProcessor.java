package com.solopov.postprocecessors;

import com.solopov.postprocecessors.annotation.Trimmed;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Component
public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var beanType = bean.getClass();

        var wrappedBean = bean;

        if (beanType.getAnnotation(Trimmed.class) != null) {
            var enhancer = new Enhancer();
            enhancer.setSuperclass(beanType);
            enhancer.setCallback(getMethodInterceptor());

            wrappedBean = enhancer.create();
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(wrappedBean, beanName);
    }

    @Nullable
    private static Object trim(Object object) {
        if (object == null) {
            return null;

        } else if (object instanceof String) {
            return ((String) object).trim();
        }

        return object;
    }

    private MethodInterceptor getMethodInterceptor() {
        return TrimmedAnnotationBeanPostProcessor::trimStringArguments;
    }

    @SneakyThrows
    private static Object trimStringArguments(Object object, Method method, Object[] arguments, MethodProxy methodProxy) {
        var trimmedArguments = Arrays.stream(arguments).map(TrimmedAnnotationBeanPostProcessor::trim).toArray();
        return trim(methodProxy.invokeSuper(object, trimmedArguments));
    }

}
