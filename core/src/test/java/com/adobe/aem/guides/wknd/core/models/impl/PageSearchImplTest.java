/*
 * Created on 24-08-2023 22:39 by jjedlins
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adobe.aem.guides.wknd.core.models.PageSearch;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


@ExtendWith({AemContextExtension.class, MockitoExtension.class })
class PageSearchImplTest {

	private final AemContext ctx = new AemContext();

	@BeforeEach
	public void setUp() {
		ctx.addModelsForClasses(PageSearchImpl.class);
		ctx.load().json("/com/adobe/aem/guides/wknd/core/models/impl/PageSearchImplTest.json", "/content");
	}

	@Test
	public void testGetName() {

		String expected = "Wyszukiwajka";

		ctx.currentResource("/content/pagesearch");
		PageSearch pageSearch = ctx.request().adaptTo(PageSearch.class);

		String actual = pageSearch.getName();

		assertEquals(expected, actual);
	}

}
