/*
 * Created on 09-08-2023 22:10 by jjedlins
 *
 * Copyright (c) 2001-2023 Unity S.A.
 * ul. Strzegomska 2-4, 53-611 Wrocław, Poland
 * Wszelkie prawa zastrzeżone
 *
 * Niniejsze oprogramowanie jest własnością Unity S.A.
 * Wykorzystanie niniejszego oprogramowania jest możliwe tylko na podstawie
 * i w zgodzie z warunkami umowy licencyjnej zawartej z Unity S.A.
 */

package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition(name="PageSearchConfig",
	description = "Configuration for PageSearchService")
public @interface PageSearchConfig {

	@AttributeDefinition(
		name = "Search Path",
		description = "Resource path for search query execution",
		type = AttributeType.STRING)
	String getRootPath() default "/content/wknd";
}
