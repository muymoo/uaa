/*******************************************************************************
 *     Cloud Foundry
 *     Copyright (c) [2009-2015] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 *******************************************************************************/
package org.cloudfoundry.identity.uaa.config;

import org.springframework.web.context.ConfigurableWebApplicationContext;

public class YamlServletProfileInitializerForTests extends YamlServletProfileInitializer {
    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        super.initialize(applicationContext,
            "${LOGIN_CONFIG_URL},file:${LOGIN_CONFIG_PATH}/login.yml,file:${CLOUD_FOUNDRY_CONFIG_PATH}/login.yml,${UAA_CONFIG_URL},file:${UAA_CONFIG_PATH}/uaa.yml,file:${CLOUD_FOUNDRY_CONFIG_PATH}/uaa.yml",
            "uaa.yml,login.yml",
            null);
    }
}
