/*
 * Created on 09-08-2023 21:34 by jjedlins
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

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.jackrabbit.webdav.property.ResourceType;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.day.cq.wcm.api.Page;


@Component(service = PageSearchService.class, immediate = true)
@Designate(ocd = PageSearchConfig.class)
public class PageSearchServiceImpl implements PageSearchService {


	private String rootPath;

	@Activate
	public void activate(PageSearchConfig pageSearchConfig) {
		this.rootPath = pageSearchConfig.getRootPath();
	}

	@Override
	public String getRootPath() {

		return rootPath;
	}

	@Override
	public List<PageSearchResultData> executeQuery(String rootPath, String parameterFromUrl, int maximumNumberOfResults, ResourceResolver resourceResolver) {

		String query = "SELECT page.* FROM [cq:Page] AS page "
			+ "INNER JOIN [cq:PageContent] AS jcrcontent ON ISCHILDNODE(jcrcontent, page) "
			+ "WHERE ISDESCENDANTNODE(page ,'%s') AND jcrcontent.[jcr:title] LIKE  '%%%s%%'";

		Iterator<Resource> resources = resourceResolver.findResources(String.format(query, rootPath, parameterFromUrl), Query.JCR_SQL2);

		List<PageSearchResultData> resultList = new ArrayList<>();
		int size = 0;

		while (resources.hasNext() && size < maximumNumberOfResults) {

			Resource next = resources.next();

			String title = getTitle(next);

			String image = getThumbnailPath(next);

			String path = next.getPath();
			String url = path + ".html";

			resultList.add(
				new PageSearchResultData(
					title,
					url,
					image));

			size ++;
		}

		return resultList;
	}

	private String getTitle(Resource resource) {

		return Optional.ofNullable(resource.adaptTo(Page.class))
			.map(Page::getTitle)
			.orElse("");
	}

	private String getThumbnailPath(Resource resource) {

		return Optional.ofNullable(resource.getChild("jcr:content"))
			.map(res -> res.getChild("image"))
			.map(res -> res.getChild("file"))
			.map(Resource::getPath)
			.orElse("");
	}

	public static class PageSearchResultData implements Serializable {

		private static final long serialVersionUID = 1L;

		private final String title;
		private final String path;
		private final String thumbnail;

		public PageSearchResultData(String title, String path, String thumbnail) {

			this.title = title;
			this.path = path;
			this.thumbnail = thumbnail;
		}

		public String getTitle() {

			return title;
		}

		public String getPath() {

			return path;
		}

		public String getThumbnail() {

			return thumbnail;
		}

		@Override
		public String toString() {

			return new ToStringBuilder(this)
				.append("title", title)
				.append("path", path)
				.append("thumbnail", thumbnail)
				.toString();
		}
	}
}
