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
package com.viglet.shio.api.object;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonView;
import com.viglet.shio.api.ShJsonView;
import com.viglet.shio.api.folder.ShFolderList;
import com.viglet.shio.api.folder.ShFolderPath;
import com.viglet.shio.bean.ShFolderTinyBean;
import com.viglet.shio.bean.ShPostTinyBean;
import com.viglet.shio.bean.security.ShConsoleSecurityBean;
import com.viglet.shio.bean.security.ShPageSecurityBean;
import com.viglet.shio.bean.security.ShSecurityBean;
import com.viglet.shio.persistence.model.auth.ShGroup;
import com.viglet.shio.persistence.model.auth.ShUser;
import com.viglet.shio.persistence.model.folder.ShFolder;
import com.viglet.shio.persistence.model.object.ShObject;
import com.viglet.shio.persistence.model.post.ShPost;
import com.viglet.shio.persistence.model.post.impl.ShPostImpl;
import com.viglet.shio.persistence.model.post.type.ShPostType;
import com.viglet.shio.persistence.model.site.ShSite;
import com.viglet.shio.persistence.repository.auth.ShUserRepository;
import com.viglet.shio.persistence.repository.folder.ShFolderRepository;
import com.viglet.shio.persistence.repository.object.ShObjectRepository;
import com.viglet.shio.persistence.repository.post.ShPostRepository;
import com.viglet.shio.persistence.repository.post.type.ShPostTypeRepository;
import com.viglet.shio.url.ShURLFormatter;
import com.viglet.shio.utils.ShFolderUtils;
import com.viglet.shio.utils.ShObjectUtils;
import com.viglet.shio.utils.ShPostUtils;
import com.viglet.shio.utils.ShSiteUtils;
import com.viglet.shio.website.cache.component.ShCacheObject;
import com.viglet.shio.website.utils.ShSitesFolderUtils;
import com.viglet.shio.website.utils.ShSitesObjectUtils;
import com.viglet.shio.website.utils.ShSitesPostUtils;
import com.viglet.shio.workflow.ShWorkflow;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Alexandre Oliveira
 */
@RestController
@RequestMapping("/api/v2/object")
@Api(tags = "Object", description = "Object API")
public class ShObjectAPI {
	@Autowired
	private ShFolderRepository shFolderRepository;
	@Autowired
	private ShPostRepository shPostRepository;
	@Autowired
	private ShSiteUtils shSiteUtils;
	@Autowired
	private ShFolderUtils shFolderUtils;
	@Autowired
	private ShSitesFolderUtils shSitesFolderUtils;
	@Autowired
	private ShPostTypeRepository shPostTypeRepository;
	@Autowired
	private ShObjectRepository shObjectRepository;
	@Autowired
	private ShPostUtils shPostUtils;
	@Autowired
	private ShSitesPostUtils shSitesPostUtils;
	@Autowired
	private ShURLFormatter shURLFormatter;
	@Autowired
	private ShCacheObject shCacheObject;
	@Autowired
	private ShSitesObjectUtils shSitesObjectUtils;
	@Autowired
	private ShUserRepository shUserRepository;
	@Autowired
	private ShObjectUtils shObjectUtils;
	@Autowired
	private ShWorkflow shWorkflow;

	@GetMapping
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public List<ShObject> shObjectList() {
		return shObjectRepository.findAll();
	}

	@GetMapping("/{id}/editor")
	public ResponseEntity<String> shObjectEditor(@PathVariable String id, HttpServletResponse response,
			RedirectAttributes attributes) {
		String redirect = null;
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		if (shObject != null) {
			if (shObject instanceof ShSite) {
				redirect = String.format("/content#!/list/%s", shObject.getId());
			} else if (shObject instanceof ShPost) {
				ShPost shPost = (ShPost) shObject;
				redirect = String.format("/content#!/post/type/%s/post/%s", shPost.getShPostType().getId(),
						shPost.getId());
			} else if (shObject instanceof ShFolder) {
				redirect = String.format("/content#!/list/%s", shObject.getId());
			}
			if (redirect != null) {
				HttpHeaders headers = new HttpHeaders();

				headers.add("Location",
						new String(redirect.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

				return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/{id}/preview")
	public RedirectView shObjectPreview(@PathVariable String id, HttpServletResponse response,
			RedirectAttributes attributes) {
		String redirect = null;
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		if (shObject instanceof ShSite) {
			redirect = shSiteUtils.generatePostLink((ShSite) shObject);
		} else if (shObject instanceof ShPost) {
			redirect = shSitesPostUtils.generatePostLink((ShPost) shObject);
		} else if (shObject instanceof ShFolder) {
			redirect = shSitesFolderUtils.generateFolderLink((ShFolder) shObject);
		}
		if (redirect != null) {
			RedirectView redirectView = new RedirectView(
					new String(redirect.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
			redirectView.setHttp10Compatible(false);

			return redirectView;
		} else {
			return null;
		}
	}

	@GetMapping("{id}/clear-cache")
	public ShObject shObjectClearCache(@PathVariable String id, HttpServletResponse response) {
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		shCacheObject.deleteCache(id);
		return shObject;
	}

	@GetMapping("{id}/request-workflow/{publishStatus}")
	public ShObject shObjectRequestWorkflow(@PathVariable String id, @PathVariable String publishStatus,
			Principal principal) {
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		shWorkflow.requestWorkFlow(shObject, principal);
		return shObject;
	}

	@ApiOperation(value = "Show object users and groups")
	@GetMapping("/{id}/security")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShSecurityBean shObjectGroupsGet(@PathVariable String id) {
		ShSecurityBean shSecurityBean = new ShSecurityBean();
		shObjectRepository.findById(id).ifPresent(shObject -> {
			List<ShObject> shObjects = new ArrayList<>();
			shObjects.add(shObject);

			ShConsoleSecurityBean shConsoleSecurityBean = new ShConsoleSecurityBean();
			shConsoleSecurityBean.setShGroups(shObject.getShGroups());
			shConsoleSecurityBean.setShUsers(shObject.getShUsers());

			ShPageSecurityBean shPageSecurityBean = new ShPageSecurityBean();
			shPageSecurityBean.setAllowGuestUser(shObject.isPageAllowGuestUser());
			shPageSecurityBean.setAllowRegisterUser(shObject.isPageAllowRegisterUser());
			shPageSecurityBean.setShGroups(shObject.getShPageGroups());

			shSecurityBean.setConsole(shConsoleSecurityBean);
			shSecurityBean.setPage(shPageSecurityBean);

		});
		return shSecurityBean;

	}

	@ApiOperation(value = "Update object users and groups")
	@PutMapping("/{id}/security")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShSecurityBean shObjectGroupsUpdate(@PathVariable String id, @RequestBody ShSecurityBean shSecurityBean) {
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		if (shObject != null && shSecurityBean != null) {
			if (shSecurityBean.getConsole() != null) {
				shObject.setShGroups(shSecurityBean.getConsole().getShGroups());
				shObject.setShUsers(shSecurityBean.getConsole().getShUsers());
			}

			if (shSecurityBean.getPage() != null) {
				shObject.setPageAllowGuestUser(shSecurityBean.getPage().isAllowGuestUser());
				shObject.setPageAllowRegisterUser(shSecurityBean.getPage().isAllowRegisterUser());
				shObject.setShPageGroups(shSecurityBean.getPage().getShGroups());
			}

			shObjectRepository.saveAndFlush(shObject);
		}
		return shSecurityBean;
	}

	@PutMapping("/moveto/{globallIdDest}")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public List<ShObject> shObjectMoveTo(@PathVariable String globallIdDest, @RequestBody List<String> globalIds) {
		List<ShObject> shObjects = new ArrayList<>();
		ShObject shObjectDest = shObjectRepository.findById(globallIdDest).orElse(null);
		for (String globalId : globalIds) {
			shCacheObject.deleteCache(globalId);
			ShObject shObject = shObjectRepository.findById(globalId).orElse(null);
			if (shObjectDest instanceof ShFolder) {
				ShFolder shFolderDest = (ShFolder) shObjectDest;
				if (shObject instanceof ShPost) {
					ShPost shPost = (ShPost) shObject;
					shPost.setShFolder(shFolderDest);
					shPost.setFurl(shURLFormatter.format(shPost.getTitle()));
					shPostRepository.save(shPost);
					shObjects.add(shPost);
				} else if (shObject instanceof ShFolder) {
					ShFolder shFolder = (ShFolder) shObject;
					shFolder.setParentFolder(shFolderDest);
					shFolder.setRootFolder((byte) 0);
					shFolder.setShSite(null);
					shFolder.setFurl(shURLFormatter.format(shFolder.getName()));
					shFolderRepository.save(shFolder);
					shObjects.add(shFolder);
				}
			} else if (shObjectDest instanceof ShSite && shObject instanceof ShFolder) {
				ShSite shSiteDest = (ShSite) shObjectDest;
				ShFolder shFolder = (ShFolder) shObject;
				shFolder.setParentFolder(null);
				shFolder.setRootFolder((byte) 1);
				shFolder.setShSite(shSiteDest);
				shFolder.setFurl(shURLFormatter.format(shFolder.getName()));
				shFolderRepository.save(shFolder);
				shObjects.add(shFolder);
			}
		}

		shCacheObject.deleteCache(globallIdDest);
		return shObjects;
	}

	@PutMapping("/copyto/{globallIdDest}")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public List<ShObject> shObjectCopyTo(@PathVariable String globallIdDest, @RequestBody List<String> globalIds) {
		List<ShObject> shObjects = new ArrayList<>();
		for (String globalId : globalIds) {
			ShObject shObject = shObjectRepository.findById(globalId).orElse(null);
			ShObject shObjectDest = shObjectRepository.findById(globallIdDest).orElse(null);
			if (shObjectDest instanceof ShFolder) {
				ShFolder shFolderDest = (ShFolder) shObjectDest;
				if (shObject instanceof ShPost) {
					ShPostImpl shPost = (ShPostImpl) shObject;
					shObjects.add(shPostUtils.copy(shPost, shFolderDest));
				} else if (shObject instanceof ShFolder) {
					ShFolder shFolder = (ShFolder) shObject;
					shObjects.add(shFolderUtils.copy(shFolder, shObjectDest));
				}
			} else if (shObjectDest instanceof ShSite && shObject instanceof ShFolder) {
				ShFolder shFolder = (ShFolder) shObject;
				shObjects.add(shFolderUtils.copy(shFolder, shObjectDest));
			}
		}
		shCacheObject.deleteCache(globallIdDest);
		return shObjects;
	}

	@GetMapping("/{id}/list")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ResponseEntity<?> shObjectListItem(@PathVariable String id, final Principal principal) {
		if (shObjectUtils.canAccess(principal, id)) {
			Optional<ShObject> shObjectOptional = shObjectRepository.findById(id);
			if (shObjectOptional.isPresent()) {
				ShUser shUser = null;
				if (principal != null)
					shUser = shUserRepository.findByUsername(principal.getName());
				ShObject shObject = shObjectOptional.get();
				if (shObject instanceof ShFolder) {

					ShFolder shFolder = (ShFolder) shObject;
					String folderPath = shFolderUtils.folderPath(shFolder, true, false);
					List<ShFolder> breadcrumb = shFolderUtils.breadcrumb(shFolder);
					ShSite shSite = breadcrumb.get(0).getShSite();
					ShFolderList shFolderList = new ShFolderList();
					shFolderList.setShFolders(this.allowedFolders(shUser, shObject));
					shFolderList.setShPosts(this.allowedPosts(shUser, shObject));
					shFolderList.setFolderPath(folderPath);
					shFolderList.setBreadcrumb(breadcrumb);
					shFolderList.setShSite(shSite);

					return new ResponseEntity<>(shFolderList, HttpStatus.OK);
				} else if (shObject instanceof ShSite) {
					ShSite shSite = (ShSite) shObject;
					ShFolderList shFolderList = new ShFolderList();
					shFolderList.setShFolders(this.allowedFolders(shUser, shObject));
					shFolderList.setShSite(shSite);
					return new ResponseEntity<>(shFolderList, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}

	private Set<ShFolderTinyBean> allowedFolders(ShUser shUser, ShObject shObject) {

		Set<ShFolderTinyBean> folders = new HashSet<>();
		if (shObject instanceof ShFolder) {
			ShFolder shFolder = (ShFolder) shObject;
			folders = shFolderRepository.findByParentFolderTiny(shFolder);
		} else if (shObject instanceof ShSite) {
			ShSite shSite = (ShSite) shObject;
			folders = shFolderRepository.findByShSiteAndRootFolderTiny(shSite, (byte) 1);
		}

		Set<String> shGroups = new HashSet<>();
		Set<String> shUsers = new HashSet<>();
		if (shUser != null && shUser.getShGroups() != null) {
			boolean fullAccess = false;
			for (ShGroup shGroup : shUser.getShGroups()) {
				if (shGroup.getName().equals("Administrator")) {
					fullAccess = true;
				}
			}
			if (fullAccess) {
				return folders;
			} else {
				Set<ShFolderTinyBean> shFolders = new HashSet<>();

				for (ShGroup shGroup : shUser.getShGroups()) {
					shGroups.add(shGroup.getName());
				}
				shUsers.add(shUser.getUsername());
				for (ShFolderTinyBean folder : folders)
					if (shObjectRepository.countByIdAndShGroupsInOrIdAndShUsersInOrIdAndShGroupsIsNullAndShUsersIsNull(
							folder.getId(), shGroups, folder.getId(), shUsers, folder.getId()) > 0)
						shFolders.add(folder);

				return shFolders;
			}
		} else {
			return folders;
		}

	}

	private List<ShPostTinyBean> allowedPosts(ShUser shUser, ShObject shObject) {

		List<ShPostTinyBean> posts = new ArrayList<>();
		if (shObject instanceof ShFolder) {
			ShFolder shFolder = (ShFolder) shObject;
			posts = shPostRepository.findByShFolderTiny(shFolder.getId());
		}

		Set<String> shGroups = new HashSet<>();
		Set<String> shUsers = new HashSet<>();
		if (shUser != null && shUser.getShGroups() != null) {
			boolean fullAccess = false;
			for (ShGroup shGroup : shUser.getShGroups()) {
				if (shGroup.getName().equals("Administrator")) {
					fullAccess = true;
				}
			}
			if (fullAccess) {
				return posts;
			} else {
				List<ShPostTinyBean> shPosts = new ArrayList<>();
				for (ShGroup shGroup : shUser.getShGroups()) {
					shGroups.add(shGroup.getName());
				}
				shUsers.add(shUser.getUsername());
				for (ShPostTinyBean post : posts)
					if (shObjectRepository.countByIdAndShGroupsInOrIdAndShUsersInOrIdAndShGroupsIsNullAndShUsersIsNull(
							post.getId(), shGroups, post.getId(), shUsers, post.getId()) > 0)
						shPosts.add(post);
				return shPosts;
			}
		} else {
			return posts;
		}

	}

	@GetMapping("/{id}/list/{postTypeName}")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShFolderList shFolderListByPostType(@PathVariable String id, @PathVariable String postTypeName) {
		Optional<ShObject> shObjectOptional = shObjectRepository.findById(id);
		if (shObjectOptional.isPresent()) {
			ShObject shObject = shObjectOptional.get();
			ShPostType shPostType = shPostTypeRepository.findByName(postTypeName);
			if (shObject instanceof ShFolder) {
				ShFolder shFolder = (ShFolder) shObject;
				String folderPath = shFolderUtils.folderPath(shFolder, true, false);
				List<ShFolder> breadcrumb = shFolderUtils.breadcrumb(shFolder);
				ShSite shSite = breadcrumb.get(0).getShSite();
				ShFolderList shFolderList = new ShFolderList();
				shFolderList.setShFolders(shFolderRepository.findByParentFolderTiny(shFolder));
				String postTypeId = null;
				if (shPostType != null) {
					postTypeId = shPostType.getId();
				}
				shFolderList.setShPosts(shPostRepository.findByShFolderAndShPostTypeTiny(shFolder.getId(), postTypeId));
				shFolderList.setFolderPath(folderPath);
				shFolderList.setBreadcrumb(breadcrumb);
				shFolderList.setShSite(shSite);
				return shFolderList;
			} else if (shObject instanceof ShSite) {
				ShSite shSite = (ShSite) shObject;
				Set<ShFolderTinyBean> shFolders = shFolderRepository.findByShSiteAndRootFolderTiny(shSite, (byte) 1);
				ShFolderList shFolderList = new ShFolderList();
				shFolderList.setShFolders(shFolders);
				shFolderList.setShSite(shSite);
				return shFolderList;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@ApiOperation(value = "Object URL")
	@GetMapping("/{id}/url")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public String shObjectURL(@PathVariable String id) {
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		String label = "";
		if (shObject instanceof ShPost) {
			label = ((ShPostImpl) shObject).getTitle();
		} else if (shObject instanceof ShFolder) {
			label = ((ShFolder) shObject).getName();
		} else if (shObject instanceof ShSite) {
			label = ((ShSite) shObject).getName();
		}

		return String.format("{ \"url\" : \"%s\", \"label\" : \"%s\"}", shSitesObjectUtils.generateObjectLinkById(id),
				label);
	}

	@ApiOperation(value = "Object path")
	@GetMapping("/{id}/path")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShFolderPath shObjectPath(@PathVariable String id) {
		ShObject shObject = shObjectRepository.findById(id).orElse(null);
		if (shObject instanceof ShSite) {
			ShSite shSite = (ShSite) shObject;
			if (shSite != null) {
				ShFolderPath shFolderPath = new ShFolderPath();
				shFolderPath.setFolderPath(null);
				shFolderPath.setCurrentFolder(null);
				shFolderPath.setBreadcrumb(null);
				shFolderPath.setShSite(shSite);
				return shFolderPath;
			}
		} else if (shObject instanceof ShFolder) {
			ShFolder shFolder = (ShFolder) shObject;
			if (shFolder != null) {
				ShFolderPath shFolderPath = new ShFolderPath();
				String folderPath = shFolderUtils.folderPath(shFolder, true, false);
				List<ShFolder> breadcrumb = shFolderUtils.breadcrumb(shFolder);
				ShSite shSite = breadcrumb.get(0).getShSite();
				shFolderPath.setFolderPath(folderPath);
				shFolderPath.setCurrentFolder(shFolder);
				shFolderPath.setBreadcrumb(breadcrumb);
				shFolderPath.setShSite(shSite);
				return shFolderPath;
			}
		} else if (shObject instanceof ShPost) {
			ShPostImpl shPost = shPostUtils.loadLazyPost(shObject.getId(), false);
			if (shPost != null) {
				ShFolder shFolder = shPost.getShFolder();
				ShFolderPath shFolderPath = new ShFolderPath();
				String folderPath = shFolderUtils.folderPath(shFolder, true, false);
				List<ShFolder> breadcrumb = shFolderUtils.breadcrumb(shFolder);
				ShSite shSite = breadcrumb.get(0).getShSite();
				shFolderPath.setFolderPath(folderPath);
				shFolderPath.setCurrentFolder(shFolder);
				shFolderPath.setBreadcrumb(breadcrumb);
				shFolderPath.setShSite(shSite);
				shFolderPath.setShPost(shPost);
				return shFolderPath;
			}
		}
		return null;
	}

	@ApiOperation(value = "Sort Object")
	@PutMapping("/sort")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public Map<String, Integer> shObjectSort(@RequestBody Map<String, Integer> objectOrder) {
		boolean first = true;
		String parentFolderId = null;

		for (Entry<String, Integer> objectOrderItem : objectOrder.entrySet()) {
			int shObjectOrder = objectOrderItem.getValue();
			String shObjectId = objectOrderItem.getKey();
			Optional<ShObject> shObjectOptional = shObjectRepository.findById(shObjectId);
			if (shObjectOptional.isPresent()) {
				ShObject shObject = shObjectOptional.get();
				shObject.setPosition(shObjectOrder);
				shObjectRepository.save(shObject);
				if (first) {
					first = false;
					ShFolder shFolder = shFolderUtils.getParentFolder(shObject);
					if (shFolder != null)
						parentFolderId = shFolder.getId();
				}
			}
		}

		if (parentFolderId != null)
			shCacheObject.deleteCache(parentFolderId);

		return objectOrder;

	}
}
