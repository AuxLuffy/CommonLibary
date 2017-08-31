package com.lenovo.service.basicpubliclibrary.config;

/**
 * @description: 配置文件
 * @author: 袁东华
 * @time: created at 2017/5/18 上午11:22
 */
public class Config {
    //是否输出日志
    private static boolean debug=true;

    public static boolean isDebug() {
        return debug;
    }

    /**
     * 是否输出日志
     * @param debug
     */
    public static void setDebug(boolean debug) {
        Config.debug = debug;
    }
}
