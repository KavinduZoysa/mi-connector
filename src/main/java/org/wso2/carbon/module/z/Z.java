package org.wso2.carbon.module.z;

import org.wso2.carbon.module.core.SimpleMediator;
import org.wso2.carbon.module.core.SimpleMessageContext;

public class Z extends SimpleMediator {
    @Override
    public void mediate(SimpleMessageContext mc) {
        mc.setTextPayload("Z");
    }
}
