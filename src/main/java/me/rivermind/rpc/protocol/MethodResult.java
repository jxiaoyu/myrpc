package me.rivermind.rpc.protocol;

import lombok.Data;

/**
 * @author river
 * @date 2019-04-14
 */
@Data
public class MethodResult {

    private String callId;

    private Object result;

}
