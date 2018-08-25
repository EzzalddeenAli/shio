package com.viglet.shiohara.widget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.google.gson.Gson;
import com.viglet.shiohara.persistence.model.object.ShObject;
import com.viglet.shiohara.persistence.model.post.type.ShPostTypeAttr;

@Component
public class ShReCaptchaWidget implements ShWidgetImplementation {
	@Autowired
	private SpringTemplateEngine templateEngine;

	public String render(ShPostTypeAttr shPostTypeAttr, ShObject shObject) {
		String widgetSettings = shPostTypeAttr.getWidgetSettings();
		JSONObject settings = new JSONObject(widgetSettings);
		String siteKey = settings.getString("siteKey");
		final Context ctx = new Context();
		ctx.setVariable("shPostTypeAttr", shPostTypeAttr);
		ctx.setVariable("siteKey", siteKey);
		return templateEngine.process("widget/recaptcha/recaptcha-widget", ctx);
	}

	@Override
	public boolean validateForm(HttpServletRequest request, ShPostTypeAttr shPostTypeAttr) {
		String recap = request.getParameter("g-recaptcha-response");
		String widgetSettings = shPostTypeAttr.getWidgetSettings();
		JSONObject settings = new JSONObject(widgetSettings);
		String secretKey = settings.getString("secretKey");

		try {
			String urlGoogle = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";

			// Send get request to Google reCaptcha server with secret key
			String urlFormatada = String.format(urlGoogle, secretKey, recap,
					(request.getRemoteAddr() != null ? request.getRemoteAddr() : "0.0.0.0"));
			URL url = new URL(urlFormatada);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			// Convert response into Object
			CaptchaResponse capRes = new Gson().fromJson(outputString, CaptchaResponse.class);

			// Verify whether the input from Human or Robot
			if (capRes.isSuccess()) {
				// Input by Human
				return true;
			} else {
				// Input by Robot
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private class CaptchaResponse {
		private boolean success;
		private String[] errorCodes;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String[] getErrorCodes() {
			return errorCodes;
		}

		public void setErrorCodes(String[] errorCodes) {
			this.errorCodes = errorCodes;
		}

	}
}