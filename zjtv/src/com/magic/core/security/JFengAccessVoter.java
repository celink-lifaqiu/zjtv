package com.magic.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: YinJianFeng
 * Date: 13-9-14
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public class JFengAccessVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (!(object instanceof FilterInvocation)) return ACCESS_ABSTAIN;
        for (Iterator<ConfigAttribute> iterator = attributes.iterator(); iterator.hasNext(); ) {
            ConfigAttribute configAttribute = iterator.next();
            String needPermission = configAttribute.getAttribute();
            if ("P_COMMON".equals(needPermission)) return ACCESS_GRANTED;

//            if (authentication.getAuthorities().size() == 1){
//                GrantedAuthority grantedAuthority = authentication.getAuthorities().toArray(new GrantedAuthority[]{})[0];
//                if ("P_NONE".equals(grantedAuthority.getAuthority()))
//                    return ACCESS_DENIED;
//            }
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needPermission.equals(grantedAuthority.getAuthority())
                        || "P_ADMIN".equals(grantedAuthority.getAuthority())){
                    return ACCESS_GRANTED;
                }
            }
        }
        return ACCESS_DENIED;
    }
}
