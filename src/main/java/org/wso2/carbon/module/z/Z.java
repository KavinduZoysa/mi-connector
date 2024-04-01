package org.wso2.carbon.module.z;

import io.ballerina.runtime.internal.scheduling.Scheduler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.wso2.carbon.module.core.SimpleMediator;
import org.wso2.carbon.module.core.SimpleMessageContext;

import java.util.HashMap;

import static org.wso2.carbon.module.z.Constants.FIRST_ARGUMENT;
import static org.wso2.carbon.module.z.Constants.FUNCTION_NAME;
import static org.wso2.carbon.module.z.Constants.MODULE_NAME;
import static org.wso2.carbon.module.z.Constants.ORG_NAME;
import static org.wso2.carbon.module.z.Constants.PAYLOAD;
import static org.wso2.carbon.module.z.Constants.RESULT;
import static org.wso2.carbon.module.z.Constants.SECOND_ARGUMENT;
import static org.wso2.carbon.module.z.Constants.VERSION;
import static io.ballerina.runtime.internal.BalRuntime.balStart;

//public class Z extends SimpleMediator {
//    @Override
//    public void mediate(SimpleMessageContext mc) {
//        mc.setTextPayload("Z");
//    }
//}

public class Z extends SimpleMediator {
    static Scheduler scheduler = new Scheduler(false);
    private String firstArgument = "s2co";
    private String secondArgument = "s1";
    private String functionName = "transform_1";

    public void mediate(SimpleMessageContext context) {
        HashMap<String, Object> properties = new HashMap<String, Object>() {{
            put(PAYLOAD, getPayload(context));
            put(FIRST_ARGUMENT, getFirstArgument());
            put(SECOND_ARGUMENT, getSecondArgument());
            put(FUNCTION_NAME, getFunctionName());
        }};

        balStart(scheduler, properties, ORG_NAME, MODULE_NAME, VERSION);
        System.out.println("SSSSSSSSSSSSS");
        context.setProperty(RESULT, properties.get(RESULT));
        System.out.println("AAAAAAAAAAAA");
//        context.setProperty(RESULT, "dcdcdcdc");
    }

    public void setFirstArgument(String value) {
        firstArgument = value;
    }

    public String getFirstArgument() {
        return firstArgument;
    }

    public void setSecondArgument(String value) {
        secondArgument = value;
    }

    public String getSecondArgument() {
        return secondArgument;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getPayload(SimpleMessageContext context) {
        return "p11";
    }
}
