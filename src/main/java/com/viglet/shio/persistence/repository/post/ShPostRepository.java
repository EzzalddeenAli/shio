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
package com.viglet.shio.persistence.repository.post;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viglet.shio.bean.ShPostTinyBean;
import com.viglet.shio.persistence.model.folder.ShFolder;
import com.viglet.shio.persistence.model.post.ShPost;
import com.viglet.shio.persistence.model.post.ShPostAttr;
import com.viglet.shio.persistence.model.post.type.ShPostType;

/**
 * @author Alexandre Oliveira
 */
@Repository
public interface ShPostRepository extends JpaRepository<ShPost, String>, ShPostRepositoryCustom {

	Set<ShPost> findByShPostTypeAndShPostAttrsIn(ShPostType shPostType, Collection<ShPostAttr> postAttrs);
	
	List<ShPost> findAll();

	List<ShPost> findByShFolder(ShFolder shFolder);
	
	List<ShPost> findByShFolderOrderByShPostType(ShFolder shFolder);

	@Query("select new com.viglet.shio.bean.ShPostTinyBean(p.id, p.title, p.summary, p.position, p.date, p.shPostType.id, p.shPostType.name, p.shPostType.title, p.objectType, p.publishStatus, p.published) from ShPost p where p.shFolder.id = ?1")
	List<ShPostTinyBean> findByShFolderTiny(String shFolderId);
	
	@Query("select new com.viglet.shio.bean.ShPostTinyBean(p.id, p.title, p.summary, p.position, p.date, p.shPostType.id, p.shPostType.name, p.shPostType.title, p.objectType, p.publishStatus, p.published) from ShPost p where p.shFolder.id = ?1 and p.shPostType.id = ?2")
	List<ShPostTinyBean> findByShFolderAndShPostTypeTiny(String shFolderId, String shPostTypeId);
		
	List<ShPost> findByShFolderAndShPostType(ShFolder shFolder, ShPostType shPostType);
	
	List<ShPost> findByShPostType(ShPostType shPostType);

	List<ShPost> findByShFolderAndShPostTypeOrderByPositionAsc(ShFolder shFolder, ShPostType shPostType);

	
	@Query("select p from ShPost p JOIN FETCH p.shPostType JOIN FETCH p.shFolder JOIN FETCH p.shPostAttrs where p.id = ?1")
	Optional<ShPost> findByIdFull(String id);
	
	Optional<ShPost> findById(String id);

	List<ShPost> findByTitle(String title);

	ShPost findByShFolderAndTitle(ShFolder shFolder, String title);

	ShPost findByShFolderAndFurl(ShFolder shFolder, String furl);
	
	boolean existsByShFolderAndTitle(ShFolder shFolder, String title);
	
	boolean existsByShFolderAndFurl(ShFolder shFolder, String furl);

	@SuppressWarnings("unchecked")
	ShPost save(ShPost shPost);

	@Modifying
	@Query("delete from ShPost p where p.id = ?1")
	void delete(String shPostId);
}