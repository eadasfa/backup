package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * 配置DispatcherServlet
 *
 */
public class SpittrWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer
{
	//返回带有@Configuration注解类将会用来配置ContextLoaderListener创建的应用上
	//ContextLoaderListener加载的bean通常是驱动应用后端的中间层和数据层组件
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub

		return new Class<?>[] {RootConfig.class};
	}
	//表明要求DispathcherServlet加载应用上下文时，使用定义在WebConfig配置类中的Bean
	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		// TODO Auto-generated method stub
	
		return new Class<?>[] {WebConfig.class};
	}
	//将一个或多个路径映射到DispatcherServlet上，这里指定的“/”表示他会适应应用的默认
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	//配置StandardServletMultipartResolver的限制
	//MultiPartConfig配置了StandardServletMultipartResolver的Bean
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//上传目录
		registration.setMultipartConfig(
				new MultipartConfigElement(
						"E:\\demo1_upload",	//上传目录
						2097152,4194304,0));//最大限制,大小不超过2MB,请求大小不超过4MB
											//默认值0表示所有上传的文件都会写入磁盘
	}
	  
}
