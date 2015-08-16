package com.sia.tutorial.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.sia.tutorial.config.RootConfig.WebPackage;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = { "com.sia.tutorial" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class RootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com.sia.tutorial\\.web"));
		}
	}

}
