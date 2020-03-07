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
package com.viglet.shio.onstartup.site;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viglet.shio.exchange.ShImportExchange;
import com.viglet.shio.persistence.repository.site.ShSiteRepository;

/**
 * @author Alexandre Oliveira
 */
@Component
public class ShSiteOnStartup {
	private static final Log logger = LogFactory.getLog(ShSiteOnStartup.class);
	@Autowired
	private ShSiteRepository shSiteRepository;
	@Autowired
	private ShImportExchange shImportExchange;
	private static final String COULD_NOT_CREATE_SAMPLE_SITE = "Could not create sample site";

	public void createDefaultRows() {

		if (shSiteRepository.findAll().isEmpty()) {

			URL sampleSiteRepository = null;
			try {
				sampleSiteRepository = new URL("https://github.com/openshio/sample-site/archive/master.zip");
			} catch (MalformedURLException e) {
				logger.error("sampleSiteRepository MalformedURLException", e);

			}

			File userDir = new File(System.getProperty("user.dir"));
			if (userDir.exists() && userDir.isDirectory()) {
				File tmpDir = new File(
						userDir.getAbsolutePath().concat(File.separator + "store" + File.separator + "tmp"));
				if (!tmpDir.exists()) {
					tmpDir.mkdirs();
				}

				File sampleSiteFile = new File(
						tmpDir.getAbsolutePath().concat(File.separator + "sample-site-" + UUID.randomUUID() + ".zip"));

				try {
					FileUtils.copyURLToFile(sampleSiteRepository, sampleSiteFile);
					shImportExchange.importFromFile(sampleSiteFile, "admin");
				} catch (IllegalStateException e) {
					System.out.println(COULD_NOT_CREATE_SAMPLE_SITE);
					logger.error("createDefaultRows IllegalStateException", e);

				} catch (IOException e) {
					System.out.println(COULD_NOT_CREATE_SAMPLE_SITE);
					logger.error("importFromFile IOException", e);
				}

				FileUtils.deleteQuietly(sampleSiteFile);
			}
		}
	}
}
