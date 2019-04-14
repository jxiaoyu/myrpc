package me.rivermind.rpc.protocol;

import lombok.Data;

/**
 * @author river
 * @date 2019-04-14
 */
@Data
public class MethodCall {

    private String callId;

    private String className;

    private String methodName;

    private Object[] args;

}
