package com.ningpai.jdpage;

import java.util.List;

/**
 * 属性信息
 * 
 * @author ggn
 *
 */
public class OutKeys {

    /**
     * 属性名
     */
    private String outKey;
    /**
     * 属性值列表
     */
    private List<OutValues> values;

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey;
    }

    public List<OutValues> getValues() {
        return values;
    }

    public void setValues(List<OutValues> values) {
        this.values = values;
    }

}
