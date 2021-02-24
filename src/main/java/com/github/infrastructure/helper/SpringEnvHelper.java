package com.github.infrastructure.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author EalenXie create on 2020/7/24 10:24
 * 常用Spring环境工具类
 */
public class SpringEnvHelper implements ApplicationContextAware {

    /**
     * 应用名
     */
    private static String appName;
    private static final String[] DEV = {"dev", "default", "develop"};
    private static final String[] SIT = {"sit", "test", "uat"};
    private static final String[] PROD = {"prod", "prd", "pro", "master"};
    private static String[] profiles;
    /**
     * 程序启动模式
     */
    private static Boolean isDebug;
    /**
     * 本机真实Ip
     */
    private static String localhostIp;
    /**
     * 当前应用进程号
     */
    private static Integer pid;


    @Override
    public void setApplicationContext(@Autowired ApplicationContext applicationContext) {
        setAppName(applicationContext);
        setProfiles(applicationContext);
    }

    /**
     * 获取应用名称
     */
    public static String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     */
    public static void setAppName(String name) {
        appName = name;
    }

    /**
     * 判断是否是开发环境
     */
    public static boolean isDev() {
        return isEnv(DEV);
    }

    /**
     * 判断是否是测试环境
     */
    public static boolean isSit() {
        return isEnv(SIT);
    }

    /**
     * 判断是否是生产环境
     */
    public static boolean isProd() {
        return isEnv(PROD);
    }

    /**
     * 程序是否以DEBUG模式运行
     */
    public static boolean isDebugMode() {
        if (isDebug == null) {
            isDebug = false;
            final Pattern debugPattern = Pattern.compile("-Xdebug|jdwp");
            RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
            List<String> inputArguments = mxBean.getInputArguments();
            for (String arg : inputArguments) {
                if (debugPattern.matcher(arg).find()) {
                    isDebug = true;
                    break;
                }
            }
        }
        return isDebug;
    }

    /**
     * 获取当前进程号
     */
    public static int getProcessId() {
        if (pid == null) {
            pid = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        }
        return pid;
    }

    /**
     * 获取本机真实IP
     */
    public static String getLocalhostIp() {
        if (localhostIp == null) {
            try {
                localhostIp = getLocalIpAddr0();
            } catch (IOException e) {
                e.printStackTrace();
                localhostIp = "UNKNOWN";
            }
        }
        return localhostIp;
    }

    /**
     * 判断环境
     */
    public static boolean isEnv(String... envKeys) {
        if (envKeys == null || envKeys.length == 0) {
            return false;
        }
        if (profiles == envKeys) {
            return true;
        }
        for (String profile : profiles) {
            for (String env : envKeys) {
                if (env.equalsIgnoreCase(profile)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置程序profile
     */
    private static void setProfiles(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        profiles = environment.getActiveProfiles();
        if (profiles.length == 0) {
            profiles = environment.getDefaultProfiles();
        }
        if (profiles.length == 0) {
            profiles = DEV;
        }
    }

    /**
     * 设置应用名称
     */
    private static void setAppName(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        String name = environment.getProperty("spring.application.name");
        if (name != null) {
            appName = name;
            return;
        }
        if (applicationContext.getId() != null) {
            appName = applicationContext.getId();
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if ("main".equals(stackTraceElement.getMethodName())) {
                name = stackTraceElement.getFileName();
                break;
            }
        }
        if (name != null) {
            appName = name;
            return;
        }
        appName = applicationContext.getApplicationName();
    }

    /**
     * 获取本机网卡第一个IPv4
     *
     * @return Get the first IPv4 of the native network card
     * @throws IOException Resolving native Ip may throw UnknownHostException
     */
    public static String getLocalIpAddr0() throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> ipAddrEnum = ni.getInetAddresses();
            while (ipAddrEnum.hasMoreElements()) {
                InetAddress addr = ipAddrEnum.nextElement();
                if (!addr.isLoopbackAddress()) {
                    String ip = addr.getHostAddress();
                    if (ip != null && !ip.contains(":")) {
                        return ip;
                    }
                }
            }
        }
        throw new UnknownHostException();
    }

}
