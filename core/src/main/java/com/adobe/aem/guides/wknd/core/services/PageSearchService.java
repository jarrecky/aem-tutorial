/*
 * Created on 09-08-2023 21:33 by jjedlins
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

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;


public interface PageSearchService {

	String getRootPath();

	List<PageSearchServiceImpl.PageSearchResultData> executeQuery(String rootPath, String parameterFromUrl, int maximumNumberOfResults, ResourceResolver resourceResolver);
}
