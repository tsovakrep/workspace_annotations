package framework.classcore.impl;

import java.util.List;

import framework.classcore.PageScanner;
import framework.classcore.template.PageTemplate;

public class PageScannerImpl implements PageScanner {

	@Override
	public List<String> getPageList(String packageName) {
		return new PageTemplate(packageName) {
			@Override
			protected boolean checkAddPage(String pageName) {
				return pageName.endsWith(".jsp");
			}
		}.getFileList();
	}
}
