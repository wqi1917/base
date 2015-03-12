/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message.down;

import javax.ws.rs.core.MultivaluedMap;

import com.hms.message.BaseSender;
import com.hms.message.resource.ResourceFactory;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 账户管理
 * 
 * @author wangq
 * @version $Id: AccountManageSender.java, v 0.1 2014-11-19 下午5:24:18 wangq Exp $
 */
public class AccountManageSender extends BaseSender {

    private static String openfire_user_url = "plugins/openservice/user";
    private final String  type;
    private final String  userinfos;

    public AccountManageSender(String type, String userinfos) {
        super(ResourceFactory.OPENFRE_SYNC, ResourceFactory.OPENFRE);
        this.type = type;
        this.userinfos = userinfos;
    }

    /**
     * 
     * @see com.hms.message.BaseSender#sendMessage(com.sun.jersey.api.client.WebResource)
     */
    @Override
    protected String sendMessage(WebResource wr) {
        System.out.println("In AccountManageSender function, userinfos info:" + userinfos);
        MultivaluedMap<String, String> form = new MultivaluedMapImpl();
        form.add("type", type);
        form.add("userinfos", userinfos);

        String response = wr.path(openfire_user_url).post(String.class, form);

        System.out.print(response);
        return response;
    }

}
