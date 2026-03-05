/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 批量更新骑手位置请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田批量请求给三方骑手位置信息
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田批量请求三方获取订单的骑手位置
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 批量请求骑手位置 → 三方接收并同步位置信息
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>支持批量处理多个骑手位置更新</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class MultiRiderLocationReq {

    /**
     * 骑手位置列表
     */
    @NotNull(message = "骑手位置列表不能为空")
    @Size(min = 1, message = "骑手位置列表至少包含一个位置信息")
    @Valid
    @JsonProperty("locations")
    private List<RiderLocation> locations;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public MultiRiderLocationReq() {
        this.locations = new ArrayList<>();
    }

    /**
     * 带参构造函数
     * 
     * @param locations 骑手位置列表
     */
    public MultiRiderLocationReq(List<RiderLocation> locations) {
        this.locations = locations != null ? new ArrayList<>(locations) : new ArrayList<>();
    }

    // ==================== Getter 和 Setter 方法 ====================

    public List<RiderLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RiderLocation> locations) {
        this.locations = locations != null ? new ArrayList<>(locations) : new ArrayList<>();
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiRiderLocationReq that = (MultiRiderLocationReq) o;
        return Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations);
    }

    @Override
    public String toString() {
        return "MultiRiderLocationReq{" +
                "locations=" + locations +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 获取骑手位置数量
     * 
     * @return 骑手位置列表的大小
     */
    @JsonIgnore
    public int getLocationCount() {
        return locations != null ? locations.size() : 0;
    }

    /**
     * 检查是否包含骑手位置信息
     * 
     * @return 如果包含骑手位置信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasLocations() {
        return locations != null && !locations.isEmpty();
    }

    /**
     * 检查是否为空的请求
     * 
     * @return 如果骑手位置列表为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isEmpty() {
        return !hasLocations();
    }

    /**
     * 添加单个骑手位置
     * 
     * @param location 骑手位置信息
     */
    public void addLocation(RiderLocation location) {
        if (location != null) {
            if (locations == null) {
                locations = new ArrayList<>();
            }
            locations.add(location);
        }
    }

    /**
     * 添加多个骑手位置
     * 
     * @param locationList 骑手位置列表
     */
    public void addLocations(List<RiderLocation> locationList) {
        if (locationList != null && !locationList.isEmpty()) {
            if (locations == null) {
                locations = new ArrayList<>();
            }
            locations.addAll(locationList);
        }
    }

    /**
     * 清空所有骑手位置
     */
    public void clearLocations() {
        if (locations != null) {
            locations.clear();
        }
    }

    /**
     * 获取不可变的骑手位置列表
     * 
     * @return 不可变的骑手位置列表
     */
    @JsonIgnore
    public List<RiderLocation> getImmutableLocations() {
        if (locations == null || locations.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(locations);
    }

    /**
     * 验证所有骑手位置信息是否有效
     * 
     * @return 如果所有骑手位置信息都有效返回true，否则返回false
     */
    @JsonIgnore
    public boolean isAllLocationsValid() {
        if (!hasLocations()) {
            return false;
        }
        
        for (RiderLocation location : locations) {
            if (location == null || !location.isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取有效的骑手位置列表
     * 
     * @return 有效的骑手位置列表
     */
    @JsonIgnore
    public List<RiderLocation> getValidLocations() {
        List<RiderLocation> validLocations = new ArrayList<>();
        if (hasLocations()) {
            for (RiderLocation location : locations) {
                if (location != null && location.isValid()) {
                    validLocations.add(location);
                }
            }
        }
        return validLocations;
    }

    /**
     * 获取包含位置坐标的骑手信息列表
     * 
     * @return 包含位置坐标的骑手信息列表
     */
    @JsonIgnore
    public List<RiderLocation> getLocationsWithCoordinates() {
        List<RiderLocation> locationsWithCoordinates = new ArrayList<>();
        if (hasLocations()) {
            for (RiderLocation location : locations) {
                if (location != null && location.hasLocation()) {
                    locationsWithCoordinates.add(location);
                }
            }
        }
        return locationsWithCoordinates;
    }

    /**
     * 按订单ID查找骑手位置信息
     * 
     * @param orderId 订单ID
     * @return 匹配的骑手位置信息，如果未找到返回null
     */
    public RiderLocation findByOrderId(String orderId) {
        if (orderId == null || !hasLocations()) {
            return null;
        }
        
        for (RiderLocation location : locations) {
            if (location != null && orderId.equals(location.getOrderId())) {
                return location;
            }
        }
        return null;
    }

    /**
     * 按门店ID查找骑手位置信息列表
     * 
     * @param shopId 门店ID
     * @return 匹配的骑手位置信息列表
     */
    public List<RiderLocation> findByShopId(String shopId) {
        List<RiderLocation> result = new ArrayList<>();
        if (shopId != null && hasLocations()) {
            for (RiderLocation location : locations) {
                if (location != null && shopId.equals(location.getShopId())) {
                    result.add(location);
                }
            }
        }
        return result;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建包含单个骑手位置的请求
     * 
     * @param location 骑手位置信息
     * @return 批量骑手位置请求对象
     */
    public static MultiRiderLocationReq single(RiderLocation location) {
        MultiRiderLocationReq req = new MultiRiderLocationReq();
        if (location != null) {
            req.addLocation(location);
        }
        return req;
    }

    /**
     * 创建包含多个骑手位置的请求
     * 
     * @param locations 骑手位置列表
     * @return 批量骑手位置请求对象
     */
    public static MultiRiderLocationReq of(List<RiderLocation> locations) {
        return new MultiRiderLocationReq(locations);
    }

    /**
     * 创建空的批量骑手位置请求
     * 
     * @return 空的批量骑手位置请求对象
     */
    public static MultiRiderLocationReq empty() {
        return new MultiRiderLocationReq();
    }

    /**
     * 从骑手位置数组创建请求
     * 
     * @param locations 骑手位置数组
     * @return 批量骑手位置请求对象
     */
    public static MultiRiderLocationReq of(RiderLocation... locations) {
        MultiRiderLocationReq req = new MultiRiderLocationReq();
        if (locations != null) {
            for (RiderLocation location : locations) {
                req.addLocation(location);
            }
        }
        return req;
    }

    /**
     * 构建器模式创建请求
     * 
     * @return 批量骑手位置请求构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 批量骑手位置请求构建器
     */
    public static class Builder {
        private final List<RiderLocation> locations = new ArrayList<>();

        /**
         * 添加骑手位置
         * 
         * @param location 骑手位置信息
         * @return 构建器实例
         */
        public Builder addLocation(RiderLocation location) {
            if (location != null) {
                locations.add(location);
            }
            return this;
        }

        /**
         * 添加多个骑手位置
         * 
         * @param locationList 骑手位置列表
         * @return 构建器实例
         */
        public Builder addLocations(List<RiderLocation> locationList) {
            if (locationList != null) {
                locations.addAll(locationList);
            }
            return this;
        }

        /**
         * 添加骑手位置数组
         * 
         * @param locationArray 骑手位置数组
         * @return 构建器实例
         */
        public Builder addLocations(RiderLocation... locationArray) {
            if (locationArray != null) {
                for (RiderLocation location : locationArray) {
                    if (location != null) {
                        locations.add(location);
                    }
                }
            }
            return this;
        }

        /**
         * 构建批量骑手位置请求对象
         * 
         * @return 批量骑手位置请求对象
         */
        public MultiRiderLocationReq build() {
            return new MultiRiderLocationReq(locations);
        }
    }
}