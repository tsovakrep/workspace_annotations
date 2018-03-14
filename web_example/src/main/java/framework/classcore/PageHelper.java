package framework.classcore;

import java.util.List;

import framework.util.FrameworkConstant;
import framework.webcore.util.InstanceFactory;

public class PageHelper {
	
	private static final PageScanner pageScanner = InstanceFactory.getPageScanner();
	
	public static List<String> getBasePackagePageList() {
		return pageScanner.getPageList(FrameworkConstant.PATH_PAGES);
	}
}