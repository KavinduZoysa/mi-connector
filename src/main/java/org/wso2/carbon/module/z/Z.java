package org.wso2.carbon.module.z;

//import io.ballerina.runtime.internal.scheduling.Scheduler;
import io.ballerina.runtime.api.Module;
import io.ballerina.runtime.api.Runtime;
import io.ballerina.runtime.api.async.Callback;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BIterator;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.internal.values.BmpStringValue;
import org.wso2.carbon.module.core.SimpleMediator;
import org.wso2.carbon.module.core.SimpleMessageContext;
import io.ballerina.runtime.api.utils.StringUtils;

import java.util.HashMap;

import static org.wso2.carbon.module.z.Constants.FIRST_ARGUMENT;
import static org.wso2.carbon.module.z.Constants.FUNCTION_NAME;
import static org.wso2.carbon.module.z.Constants.MODULE_NAME;
import static org.wso2.carbon.module.z.Constants.ORG_NAME;
import static org.wso2.carbon.module.z.Constants.PAYLOAD;
import static org.wso2.carbon.module.z.Constants.RESULT;
import static org.wso2.carbon.module.z.Constants.SECOND_ARGUMENT;
import static org.wso2.carbon.module.z.Constants.VERSION;
//import static io.ballerina.runtime.internal.BalRuntime.balStart;

public class Z extends SimpleMediator {
//    static Scheduler scheduler = new Scheduler(false);
    private String firstArgument = "s23co";
    private String secondArgument = "s2";
    private String functionName = "transform_1";

    public void mediate(SimpleMessageContext context) {
        HashMap<String, Object> properties = new HashMap<String, Object>() {{
            put(PAYLOAD, getPayload(context));
            put(FIRST_ARGUMENT, getFirstArgument());
            put(SECOND_ARGUMENT, getSecondArgument());
            put(FUNCTION_NAME, getFunctionName());
        }};

        Callback returnCallback = new Callback() {
            public void notifySuccess(Object result) {
                System.out.println("asasasasa");
                System.out.println(result);
                context.setProperty(RESULT, result);
            }

            public void notifyFailure(BError result) {
                System.out.println("fgfgfgfgfg");
                System.out.println(result);
                context.setProperty(RESULT, result);
            }
        };

        System.out.println("hhhhhhhh");
        Module module = new Module(ORG_NAME, MODULE_NAME, "0");
        Runtime rt = Runtime.from(module);
        Object[] args = new Object[2];

        args[0] = StringUtils.fromString(firstArgument);
        args[1] = StringUtils.fromString(secondArgument);
        rt.init();
        rt.start();
        rt.invokeMethodAsync(functionName, returnCallback, args);
        try { // test this
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("rrrrrrr");
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

    private static Object[] buildArguments(Object... arguments) {
        Object[] args = new Object[arguments.length * 2+2];
        for (int i = 0; i < arguments.length; i++) {
            args[i * 2+2] = new BmpStringValue(arguments[i].toString()) ;
            args[i * 2 + 3] = true;
        }

        return args;
    }
}
