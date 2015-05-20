/*
 * *****************************************************************************
 *      Cloud Foundry
 *      Copyright (c) [2009-2015] Pivotal Software, Inc. All Rights Reserved.
 *      This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *      You may not use this product except in compliance with the License.
 *
 *      This product includes a number of subcomponents with
 *      separate copyright notices and license terms. Your use of these
 *      subcomponents is subject to the terms and conditions of the
 *      subcomponent's license, as noted in the LICENSE file.
 * *****************************************************************************
 */
package org.cloudfoundry.identity.uaa.mock;

import org.cloudfoundry.identity.uaa.test.YamlServletProfileInitializerContextInitializer;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class CheckContextStartupMvcMockTests {


    @Test
    public void test1() throws Exception {
        testStartContext();
    }

    @Test
    public void test2() throws Exception {
        testStartContext();
    }

    @Test
    public void test3() throws Exception {
        testStartContext();
    }

    public void testStartContext() throws Exception {
        XmlWebApplicationContext webApplicationContext;
        MockEnvironment mockEnvironment;
        long start = System.currentTimeMillis();
        webApplicationContext = new XmlWebApplicationContext();
        mockEnvironment = new MockEnvironment();
        webApplicationContext.setEnvironment(mockEnvironment);
        webApplicationContext.setServletContext(new MockServletContext());
        new YamlServletProfileInitializerContextInitializer().initializeContext(webApplicationContext, "uaa.yml,login.yml");
        webApplicationContext.setConfigLocation("file:./src/main/webapp/WEB-INF/spring-servlet.xml");
        long refreshStart = System.currentTimeMillis();
        webApplicationContext.refresh();
        long refreshStop = System.currentTimeMillis();
        webApplicationContext.registerShutdownHook();
        FilterChainProxy springSecurityFilterChain = webApplicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(springSecurityFilterChain)
            .build();

        long stop = System.currentTimeMillis();
        System.err.println("Time to refresh context:"+(refreshStop-refreshStart)+" ms.");
        System.err.println("Time to start context:"+(stop-start)+" ms.");
        webApplicationContext.destroy();
    }


}
