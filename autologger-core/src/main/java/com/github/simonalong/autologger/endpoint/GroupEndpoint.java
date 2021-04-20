package com.github.simonalong.autologger.endpoint;

import com.github.simonalong.autologger.log.FunLoggerBeanWrapper;
import com.github.simonalong.autologger.log.LoggerBeanWrapperRsp;
import com.github.simonalong.autologger.log.LoggerInvoker;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import java.util.Map;
import java.util.Set;

import static com.github.simonalong.autologger.AutoLoggerConstants.GROUP;

/**
 * @author shizi
 * @since 2021-04-16 16:04:20
 */
@Endpoint(id = GROUP)
public class GroupEndpoint {

    /**
     * 获取所有分组列表
     *
     * @param arg0  list
     * @return 分组名列表
     */
    @ReadOperation
    public Set<String> getList(@Selector String arg0) {
        return LoggerInvoker.getGroupSet();
    }

    /**
     * 获取分组下的所有函数
     *
     * @param arg0  fun
     * @param arg1  list
     * @param group 分组
     * @return 函数集合
     */
    @ReadOperation
    public Set<String> getFunList(@Selector String arg0, @Selector String arg1, String group) {
        return LoggerInvoker.getFunSet(group);
    }

    /**
     * 获取所有的分组信息
     *
     * @param arg0  fun
     * @param arg1  info
     * @param arg2  all
     * @param group 分组
     * @return 所有的函数信息
     */
    @ReadOperation
    public Map<String, FunLoggerBeanWrapper> getAllGroupInfoMap(@Selector String arg0, @Selector String arg1, @Selector String arg2, String group) {
        return LoggerInvoker.getLoggerProxyMap().get(group);
    }

    /**
     * 获取日志信息
     *
     * @param arg0     fun
     * @param arg1     info
     * @param arg2     one
     * @param arg3     logger
     * @param group    分组
     * @param funId 函数id
     * @return 日志信息
     */
    @ReadOperation
    public LoggerBeanWrapperRsp getLoggerInfo(@Selector String arg0, @Selector String arg1, @Selector String arg2, @Selector String arg3, String group, String funId) {
        return LoggerInvoker.getLoggerInfo(group, funId);
    }

    /**
     * 更新组的所有日志级别
     *
     * @param group    分组
     * @param logLevel 日志
     * @param enable   激活与否
     * @return 更改结果：0-没有变更，n-变更个数
     */
    @WriteOperation
    public Integer updateGroupAllLogger(String group, String logLevel, Boolean enable) {
        return LoggerInvoker.updateLoggerBeanLog(group, logLevel, enable);
    }

    /**
     * 更新对应函数日志级别
     *
     * @param arg0 fun
     * @param group 分组
     * @param funId 函数id
     * @param logLevel 日志级别
     * @param enable 激活与否
     * @return 更新结果标示：0-没有更新成功，1-更新成功
     */
    @WriteOperation
    public Integer updateFunLogger(@Selector String arg0, String group, String funId, String logLevel, Boolean enable) {
        return LoggerInvoker.updateLoggerBeanLog(group, funId, logLevel, enable);
    }
}
