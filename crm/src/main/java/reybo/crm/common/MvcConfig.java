package reybo.crm.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class.
 * Used to access static files crm.
 * Example such as: scripts, styles, fonts, media
 *
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
            .addResourceHandler("/reybo/web-app/scripts/**")
            .addResourceLocations("classpath:/static/crm/web-app/scripts/");
    registry
            .addResourceHandler("/reybo/web-app/styles/**")
            .addResourceLocations("classpath:/static/crm/web-app/styles/");
    registry
            .addResourceHandler("/reybo/web-app/fonts/**")
            .addResourceLocations("classpath:/static/crm/web-app/fonts/");
    registry
            .addResourceHandler("/reybo/web-app/media/**")
            .addResourceLocations("classpath:/static/crm/web-app/media/");
  }
}