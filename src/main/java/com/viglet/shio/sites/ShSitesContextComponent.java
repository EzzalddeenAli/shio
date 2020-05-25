/*
 * Copyright (C) 2016-2020 the original author or authors. 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.viglet.shio.sites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;
import com.viglet.shio.persistence.model.folder.ShFolder;
import com.viglet.shio.persistence.model.object.ShObject;
import com.viglet.shio.persistence.model.post.ShPost;
import com.viglet.shio.persistence.model.post.ShPostAttr;
import com.viglet.shio.persistence.model.site.ShSite;
import com.viglet.shio.persistence.repository.folder.ShFolderRepository;
import com.viglet.shio.persistence.repository.post.ShPostRepository;
import com.viglet.shio.post.type.ShSystemPostType;
import com.viglet.shio.post.type.ShSystemPostTypeAttr;
import com.viglet.shio.sites.cache.component.ShCacheJavascript;
import com.viglet.shio.sites.cache.component.ShCachePageLayout;
import com.viglet.shio.sites.cache.component.ShCacheRegion;
import com.viglet.shio.sites.component.ShSitesPageLayout;
import com.viglet.shio.sites.component.ShSitesPageLayoutUtils;
import com.viglet.shio.sites.nashorn.ShNashornEngineProcess;
import com.viglet.shio.sites.utils.ShSitesFolderUtils;
import com.viglet.shio.sites.utils.ShSitesPostUtils;
import com.viglet.shio.utils.ShFolderUtils;
import com.viglet.shio.utils.ShPostUtils;

/**
 * @author Alexandre Oliveira
 */
@Component
public class ShSitesContextComponent {
	static final Logger logger = LogManager.getLogger(ShSitesContextComponent.class);
	@Autowired
	private ShPostRepository shPostRepository;
	@Autowired
	private ShFolderRepository shFolderRepository;
	@Autowired
	private ShFolderUtils shFolderUtils;
	@Autowired
	private ShSitesFolderUtils shSitesFolderUtils;
	@Autowired
	private ShPostUtils shPostUtils;
	@Autowired
	private ShSitesPostUtils shSitesPostUtils;
	@Autowired
	private ShCacheRegion shCacheRegion;
	@Autowired
	private ShCachePageLayout shCachePageLayout;
	@Autowired
	private ShCacheJavascript shCacheJavascript;
	@Autowired
	private ShNashornEngineProcess shNashornEngineProcess;
	@Autowired
	private ShSitesPageLayoutUtils shSitesPageLayoutUtils;
	@Resource
	private ApplicationContext context;

	public String folderPathFactory(ArrayList<String> contentPath) {
		String folderPath = "/";
		if (contentPath.size() >= 1) {
			ArrayList<String> folderPathArray = contentPath;

			// Remove PostName
			folderPathArray.remove(folderPathArray.size() - 1);

			for (String path : folderPathArray) {
				folderPath = folderPath + path + "/";
			}

		}
		return folderPath;
	}

	public ArrayList<String> contentPathFactory(String url) {
		int contextPathPosition = 5;
		String[] contexts = url.split("/");
		ArrayList<String> contentPath = new ArrayList<String>();

		for (int i = contextPathPosition; i < contexts.length; i++) {
			contentPath.add(contexts[i]);
		}
		return contentPath;
	}

	public ShPost shPostAlias(ShPost shPostItem) {
		if (shPostItem.getShPostType().getName().equals(ShSystemPostType.ALIAS)) {
			Set<ShPostAttr> shPostAttrs = shPostItem.getShPostAttrs();
			for (ShPostAttr shPostAttr : shPostAttrs) {
				if (shPostAttr.getShPostTypeAttr().getName().equals(ShSystemPostTypeAttr.CONTENT)) {
					shPostItem = shPostRepository.findById(shPostAttr.getStrValue()).orElse(null);
				}
			}
		}
		return shPostItem;
	}

	public String objectNameFactory(ArrayList<String> contentPath) {
		String objectName = null;
		int lastPosition = contentPath.size() - 1;
		if (contentPath.size() >= 1) {
			objectName = contentPath.get(lastPosition);
		}
		return objectName;
	}

	public ShObject shObjectItemFactory(ShSite shSite, ShFolder shFolder, String objectName) {
		ShObject shObjectItem = null;

		// If shPostItem is not null, so is a Post, otherwise is a Folder
		if (objectName != null) {
			ShFolder shParentFolder = shFolder;
			shObjectItem = shPostRepository.findByShFolderAndFurl(shParentFolder, objectName);
			if (shObjectItem == null) {
				shObjectItem = shPostRepository.findByShFolderAndTitle(shParentFolder, objectName);
			}
		}

		if (shObjectItem != null) {
			shObjectItem = this.shPostAlias((ShPost) shObjectItem);
		} else {
			String folderPathCurrent = shFolderUtils.folderPath(shFolder, true, false);
			if (objectName != null) {
				folderPathCurrent = folderPathCurrent + objectName + "/";
			}
			ShFolder shFolderItem = shFolderUtils.folderFromPath(shSite, folderPathCurrent);
			if (shFolderItem != null) {
				ShPost shFolderIndex = shPostRepository.findByShFolderAndFurl(shFolderItem, "index");

				if (shFolderIndex != null) {
					shObjectItem = shFolderIndex;
				} else {
					shObjectItem = shFolderItem;
				}
			}
		}
		return shObjectItem;
	}

	public Map<String, Object> shThemeFactory(String postThemeId) {
		ShPost shTheme = shPostRepository.findById(postThemeId).orElse(null);

		Map<String, ShPostAttr> shThemeMap = shSitesPostUtils.postToMap(shTheme);

		Map<String, Object> shThemeAttrs = new HashMap<>();
		shThemeAttrs.put("javascript", shThemeMap.get(ShSystemPostTypeAttr.JAVASCRIPT).getStrValue());
		shThemeAttrs.put("css", shThemeMap.get(ShSystemPostTypeAttr.CSS).getStrValue());

		return shThemeAttrs;
	}

	public List<Map<String, Object>> shPostItemsFactory(ShFolder shFolderItem) {
		List<Map<String, Object>> shPostItems = new ArrayList<>();
		List<ShPost> shPosts = shPostRepository.findByShFolder(shFolderItem);

		for (ShPost shPost : shPosts) {
			if (!shPost.getShPostType().getName().equals(ShSystemPostType.FOLDER_INDEX)) {
				Map<String, Object> shPostItemAttrs = shSitesPostUtils.toSystemMap(shPost);
				shPostItems.add(shPostItemAttrs);
			}
		}

		return shPostItems;
	}

	public List<Map<String, Object>> shChildFolderItemsFactory(ShFolder shFolderItem) {
		List<Map<String, Object>> shChildFolderItems = new ArrayList<>();
		Set<ShFolder> shFolders = shFolderRepository.findByParentFolder(shFolderItem);

		for (ShFolder shChildFolder : shFolders) {
			shChildFolderItems.add(shSitesFolderUtils.toSystemMap(shChildFolder));
		}
		return shChildFolderItems;
	}

	public ShFolder shFolderItemFactory(ShPost shPostItem) {
		ShFolder shFolderItem = null;
		if (shPostItem != null && shPostItem.getShPostType().getName().equals(ShSystemPostType.FOLDER_INDEX)) {
			shFolderItem = shPostItem.getShFolder();
		}

		return shFolderItem;
	}

	public Map<String, ShPostAttr> shFolderPageLayoutMapFactory(ShObject shObjectItem, ShSite shSite, String format) {

		return shSitesPostUtils.postToMap(shSitesPageLayoutUtils.pageLayoutFromShObject(shObjectItem, shSite, format));
	}

	public String shPageLayoutFactory(ShSitesPageLayout shSitesPageLayout, HttpServletRequest request, ShSite shSite,
			String mimeType) throws Exception {

		return shCachePageLayout.cache(shSitesPageLayout, request, shSite, context, mimeType);
	}

	public Document shRegionFactory(ShSitesPageLayout shSitesPageLayout, String regionResult, ShSite shSite,
			String mimeType, HttpServletRequest request) throws Exception {
		StringBuilder shObjectJS = shCacheJavascript.shObjectJSFactory();
		Document doc = null;
		if (shSitesPageLayout.getPageCacheKey().endsWith(".json") || mimeType.equals("json") || mimeType.equals("xml"))
			doc = Jsoup.parse(regionResult, "", Parser.xmlParser());
		else
			doc = Jsoup.parse(regionResult);

		for (Element element : doc.getElementsByAttribute("sh-region")) {
			String cachedRegion = null;
			String regionName = element.attr("sh-region");
			if (shCacheRegion.isCached(regionName, shSite.getId()))
				cachedRegion = shCacheRegion.templateScopeCache(regionName, shSitesPageLayout, shSite, shObjectJS,
						mimeType, request);
			else
				cachedRegion = this.regionProcess(regionName, shSitesPageLayout, shSite, shObjectJS, mimeType, request);

			if (cachedRegion != null)
				element.html(cachedRegion).unwrap();
			else {
				element.html("<div> Region Error </div>").unwrap();
				throw new Exception("Region Error Exception");
			}
		}
		return doc;
	}

	public String regionProcess(String regionName, ShSitesPageLayout shSitesPageLayout, ShSite shSite,
			StringBuilder shObjectJS, String mimeType, HttpServletRequest request) {
		ShPost shRegion = getRegion(regionName, shSite.getId());
		if (shRegion != null) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			Map<String, ShPostAttr> shRegionPostMap = shSitesPostUtils.postToMap(shRegion);

			String shRegionJS = shRegionPostMap.get(ShSystemPostTypeAttr.JAVASCRIPT).getStrValue();

			String shRegionHTML = shRegionPostMap.get(ShSystemPostTypeAttr.HTML).getStrValue();

			Object regionResultChild = shNashornEngineProcess.render(regionName, shRegionJS, shRegionHTML, request,
					shSitesPageLayout.getShContent());
			try {

				String regionHTML = this
						.shRegionFactory(shSitesPageLayout, regionResultChild.toString(), shSite, mimeType, request)
						.html();

				stopwatch.stop();

				long timeProcess = stopwatch.elapsed(TimeUnit.MILLISECONDS);

				Comment comment = new Comment(String.format(" sh-region: %s, id: %s, processed in: %s ms ", regionName,
						shRegion.getId(), String.valueOf(timeProcess)));
				return String.format("%s%s", comment.toString(), regionHTML);
			} catch (Throwable err) {

				return null;
			}
		}
		return null;

	}

	public ShPost getRegion(String regionName, String siteId) {
		List<ShPost> shRegionPosts = shPostRepository.findByTitle(regionName);
		ShPost shRegion = null;
		if (shRegionPosts != null) {
			for (ShPost shRegionPost : shRegionPosts) {
				if (shPostUtils.getSite(shRegionPost).getId().equals(siteId))
					shRegion = shRegionPost;
			}

		}
		return shRegion;
	}
}
