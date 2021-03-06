package io.choerodon.base.infra.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Callable;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import io.choerodon.asgard.saga.consumer.MockHttpServletRequest;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;

/**
 * Creator: ChangpingShi0213@gmail.com
 * Date:  17:24 2019/3/1
 * Description:
 */
public class CustomContextUtil {
    private CustomContextUtil() {
    }

    public static void setUserContext(Long userId) {
        setUserContext("unknown", userId, 1L);
    }

    /**
     * 设置用户上下文，谨慎使用
     *
     * @param loginName 登录名
     * @param userId    用户id
     * @param orgId     组织id
     */
    public static void setUserContext(String loginName, Long userId, Long orgId) {
        try {
            CustomUserDetails customUserDetails = DetailsHelper.getUserDetails() == null ? new CustomUserDetails(loginName, "unknown", Collections.emptyList()) : DetailsHelper.getUserDetails();
            customUserDetails.setUserId(userId);
            customUserDetails.setOrganizationId(orgId);
            customUserDetails.setLanguage("zh_CN");
            customUserDetails.setTimeZone("CCT");
            Authentication user = new UsernamePasswordAuthenticationToken("default", "N/A", Collections.emptyList());
            OAuth2Request request = new OAuth2Request(new HashMap<>(0), "", Collections.emptyList(), true,
                    Collections.emptySet(), Collections.emptySet(), null, null, null);
            OAuth2Authentication authentication = new OAuth2Authentication(request, user);
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = new OAuth2AuthenticationDetails(new MockHttpServletRequest());
            oAuth2AuthenticationDetails.setDecodedDetails(customUserDetails);
            authentication.setDetails(oAuth2AuthenticationDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            throw new CommonException("context.set.error", e);
        }
    }

    /**
     * 设置特定的用户上下文
     *
     * @param context 用户上下文
     */
    public static void setUserContext(UserDetails context) {
        try {
            Authentication user = new UsernamePasswordAuthenticationToken("default", "N/A", Collections.emptyList());
            OAuth2Request request = new OAuth2Request(new HashMap<>(0), "", Collections.emptyList(), true,
                    Collections.emptySet(), Collections.emptySet(), null, null, null);
            OAuth2Authentication authentication = new OAuth2Authentication(request, user);
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = new OAuth2AuthenticationDetails(new MockHttpServletRequest());
            oAuth2AuthenticationDetails.setDecodedDetails(context);
            authentication.setDetails(oAuth2AuthenticationDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new CommonException("context.set.error", e);
        }
    }

}
