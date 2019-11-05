package io.choerodon.base.infra.dto;

import io.choerodon.base.api.validator.Check;
import io.choerodon.base.api.validator.Insert;
import io.choerodon.mybatis.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * FD_ROUTE_MEMBER_RULE DTO
 *
 * @author pengyuhua
 * @date 2019/10/25
 */
@Table(name = "fd_route_member_rule")
public class RouteMemberRuleDTO extends BaseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "用户ID/必填")
    @NotNull(message = "error.route.member.rule.user.id.can.not.be.null", groups = {Insert.class, Check.class})
    private Long userId;
    @ApiModelProperty(value = "路由编码/必填")
    private String routeRuleCode;

    @Transient
    private String userName;

    @Transient
    private String imageUrl;

    @Transient
    private String email;

    public Long getId() {
        return id;
    }

    public RouteMemberRuleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RouteMemberRuleDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getRouteRuleCode() {
        return routeRuleCode;
    }

    public RouteMemberRuleDTO setRouteRuleCode(String routeRuleCode) {
        this.routeRuleCode = routeRuleCode;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public RouteMemberRuleDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RouteMemberRuleDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RouteMemberRuleDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
