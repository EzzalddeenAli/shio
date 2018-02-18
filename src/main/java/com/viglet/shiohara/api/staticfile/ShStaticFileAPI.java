package com.viglet.shiohara.api.staticfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viglet.shiohara.persistence.model.channel.ShChannel;
import com.viglet.shiohara.persistence.model.post.ShPost;
import com.viglet.shiohara.persistence.model.post.ShPostAttr;
import com.viglet.shiohara.persistence.model.post.type.ShPostType;
import com.viglet.shiohara.persistence.model.post.type.ShPostTypeAttr;
import com.viglet.shiohara.persistence.repository.channel.ShChannelRepository;
import com.viglet.shiohara.persistence.repository.post.ShPostAttrRepository;
import com.viglet.shiohara.persistence.repository.post.ShPostRepository;
import com.viglet.shiohara.persistence.repository.post.type.ShPostTypeAttrRepository;
import com.viglet.shiohara.persistence.repository.post.type.ShPostTypeRepository;
import com.viglet.shiohara.utils.ShStaticFileUtils;

@Component
@Path("/staticfile")
public class ShStaticFileAPI {

	@Autowired
	private ShChannelRepository shChannelRepository;
	@Autowired
	private ShStaticFileUtils shStaticFileUtils;
	@Autowired
	private ShPostTypeRepository shPostTypeRepository;
	@Autowired
	private ShPostRepository shPostRepository;
	@Autowired
	private ShPostTypeAttrRepository shPostTypeAttrRepository;
	@Autowired
	private ShPostAttrRepository shPostAttrRepository;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	@Path("upload")
	public String fileUpload(@DefaultValue("true") @FormDataParam("enabled") boolean enabled,
			@FormDataParam("file") InputStream inputStream, @FormDataParam("channelId") UUID channelId,
			@FormDataParam("createPost") boolean createPost,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @Context UriInfo uriInfo)
			throws URISyntaxException {

		ShChannel shChannel = shChannelRepository.findById(channelId);
		File directoryPath = shStaticFileUtils.dirPath(shChannel);
		String filePath = null;
		if (directoryPath != null) {
			if (!directoryPath.exists()) {
				directoryPath.mkdirs();
			}
			OutputStream outputStream = null;

			try {

				// write the inputStream to a FileOutputStream
				String destFile = directoryPath.getAbsolutePath().concat("/" + fileDetail.getFileName());

				outputStream = new FileOutputStream(new File(destFile));

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				filePath = fileDetail.getFileName();

				if (createPost) {
					// Post File
					ShPostType shPostType = shPostTypeRepository.findByName("PT-FILE");
					ShPost shPost = new ShPost();
					shPost.setDate(new Date());
					shPost.setShPostType(shPostType);
					shPost.setSummary(null);
					shPost.setTitle(filePath);
					shPost.setShChannel(shChannel);

					shPostRepository.save(shPost);

					ShPostTypeAttr shPostTypeAttr = shPostTypeAttrRepository.findByShPostTypeAndName(shPostType,
							"FILE");

					ShPostAttr shPostAttr = new ShPostAttr();
					shPostAttr.setShPost(shPost);
					shPostAttr.setShPostTypeAttr(shPostTypeAttr);
					shPostAttr.setStrValue(shPost.getTitle());
					shPostAttr.setType(1);

					shPostAttrRepository.save(shPostAttr);

				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (outputStream != null) {
					try {
						// outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return "{ \"file\":\"" + filePath + "\"}";
	}

}
