/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 反馈Vo
 *
 * */
public class FreeBackVo {
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    @Length(max = 250, min = 10)
    private String feedbackcontent;

    @NotNull
    private String feedbackname;

    @NotNull
    private String feedbacktype;

    public String getFeedbacktype() {
        return feedbacktype;
    }

    public void setFeedbacktype(String feedbacktype) {
        this.feedbacktype = feedbacktype;
    }

    public String getFeedbackcontent() {
        return feedbackcontent;
    }

    public void setFeedbackcontent(String feedbackcontent) {
        this.feedbackcontent = feedbackcontent;
    }

    public String getFeedbackname() {
        return feedbackname;
    }

    public void setFeedbackname(String feedbackname) {
        this.feedbackname = feedbackname;
    }

}
