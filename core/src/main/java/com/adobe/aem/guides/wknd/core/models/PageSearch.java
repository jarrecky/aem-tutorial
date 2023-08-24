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

package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.services.PageSearchServiceImpl;


public interface PageSearch {

	String getName();
	List<PageSearchServiceImpl.PageSearchResultData> getSearchResults();
}
