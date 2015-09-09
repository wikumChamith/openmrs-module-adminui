/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.adminui.converter;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.ProviderService;
import org.openmrs.module.providermanagement.Provider;
import org.openmrs.ui.framework.converter.util.ConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("adminuiProviderConverter")
@Order(Ordered.LOWEST_PRECEDENCE)
public class StringToProviderConverter implements Converter<String, Provider> {
	
	@Autowired
	@Qualifier("providerService")
	public ProviderService service;
	
	/**
	 * Treats the string as the integer primary key of the Provider Role
	 */
	@Override
	public Provider convert(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		} else if (ConversionUtil.onlyDigits(id)) {
			return (Provider) service.getProvider(Integer.valueOf(id));
		} else {
			return (Provider) service.getProviderByUuid(id);
		}
	}
}
