package com.lxl.webtool.config;

import org.springframework.util.AntPathMatcher;

import java.util.Map;

public class PathMatcher extends AntPathMatcher {

	@Override
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		return super.doMatch(pattern.toLowerCase(), path.toLowerCase(),
				fullMatch, uriTemplateVariables);
	}

}
