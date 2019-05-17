package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * ����DispatcherServlet
 *
 */
public class SpittrWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer
{
	//���ش���@Configurationע���ཫ����������ContextLoaderListener������Ӧ����
	//ContextLoaderListener���ص�beanͨ��������Ӧ�ú�˵��м������ݲ����
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub

		return new Class<?>[] {RootConfig.class};
	}
	//����Ҫ��DispathcherServlet����Ӧ��������ʱ��ʹ�ö�����WebConfig�������е�Bean
	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		// TODO Auto-generated method stub
	
		return new Class<?>[] {WebConfig.class};
	}
	//��һ������·��ӳ�䵽DispatcherServlet�ϣ�����ָ���ġ�/����ʾ������ӦӦ�õ�Ĭ��
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	//����StandardServletMultipartResolver������
	//MultiPartConfig������StandardServletMultipartResolver��Bean
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//�ϴ�Ŀ¼
		registration.setMultipartConfig(
				new MultipartConfigElement(
						"E:\\demo1_upload",	//�ϴ�Ŀ¼
						2097152,4194304,0));//�������,��С������2MB,�����С������4MB
											//Ĭ��ֵ0��ʾ�����ϴ����ļ�����д�����
	}
	  
}
