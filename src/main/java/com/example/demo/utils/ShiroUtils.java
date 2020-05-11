package com.example.demo.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtils {
    private static Logger logger = LoggerFactory.getLogger(ShiroUtils.class);

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            logger.info("验证码已失效");
            return null;
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }
}
