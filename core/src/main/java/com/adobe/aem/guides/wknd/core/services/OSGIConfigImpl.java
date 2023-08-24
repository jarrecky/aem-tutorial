/*
 * Created on 09-08-2023 21:18 by jjedlins
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

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;


@Component(service=OSGIConfig.class, immediate=true)
@Designate(ocd=ServiceConfig.class)
public class OSGIConfigImpl implements OSGIConfig {

	private String path;

	@Modified
	@Activate
	public void activate(ServiceConfig serviceConfig) {
		path=serviceConfig.getPathToSearch();
	}

	@Override
	public String getPath() {
		return path;
	}
}
