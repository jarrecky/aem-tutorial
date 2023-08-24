/*
 * Created on 17-08-2023 23:39 by jjedlins
 *
 * Copyright (c) 2001-2023 Unity S.A.
 * ul. Strzegomska 2-4, 53-611 Wrocław, Poland
 * Wszelkie prawa zastrzeżone
 *
 * Niniejsze oprogramowanie jest własnością Unity S.A.
 * Wykorzystanie niniejszego oprogramowania jest możliwe tylko na podstawie
 * i w zgodzie z warunkami umowy licencyjnej zawartej z Unity S.A.
 */

package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.ValidationStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.PageSearch;
import com.adobe.aem.guides.wknd.core.services.PageSearchService;
import com.adobe.aem.guides.wknd.core.services.PageSearchServiceImpl;


@Model(
	adaptables = { SlingHttpServletRequest.class},
	adapters = {PageSearch.class},
	resourceType = {PageSearchImpl.RESOURCE_TYPE},
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PageSearchImpl implements PageSearch {

	protected static final String RESOURCE_TYPE = "wknd/components/pagesearch";

	@Self
	private SlingHttpServletRequest request;

	@OSGiService
	private PageSearchService pageSearchService;

	@ValueMapValue
	private String name;

	@ValueMapValue
	private Integer maxNumberOfResults;

	public PageSearchImpl() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<PageSearchServiceImpl.PageSearchResultData> getSearchResults() {

		String query = request.getParameter("q");

		if (StringUtils.isEmpty(query)) {
			return Collections.emptyList();
		}

		return pageSearchService.executeQuery(
			pageSearchService.getRootPath(),
			query,
			maxNumberOfResults,
			request.getResourceResolver());
	}
}
