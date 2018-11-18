/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.util;

/**
 * 货品规格
 * @author jiping 
 * @since 2015年8月21日 下午7:37:54 
 * @version 0.0.1
 */
public class StockWarnSpec {
    
     //货品的id
        private Long id;
    //货品规格
        private String specname;
        /*货品值**/
        private String specvalue;
        
        
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getSpecname() {
            return specname;
        }
        public void setSpecname(String specname) {
            this.specname = specname;
        }
        public String getSpecvalue() {
            return specvalue;
        }
        public void setSpecvalue(String specvalue) {
            this.specvalue = specvalue;
        }

}
