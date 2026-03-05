/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 添加小费实体
 * <p>
 * 用于麦芽田请求三方服务添加小费，command值为tips，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class TipsEntity {
    
    /**
     * 麦芽田侧运单号
     */
    private String sourceOrderNo;
    
    /**
     * 小费金额，累加方式（分）
     */
    private Integer tips;
    
    // ==================== Getter 和 Setter 方法 ====================
    
    public String getSourceOrderNo() {
        return sourceOrderNo;
    }
    
    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo;
    }
    
    public Integer getTips() {
        return tips;
    }
    
    public void setTips(Integer tips) {
        this.tips = tips;
    }
}