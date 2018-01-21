package com.utils;

import com.beans.SearchSpec;

/**
 * �ṩ����������������URL�ķ���
 *
 */
public class SearchUtilities {

	private static SearchSpec[] commonSpecs = 
		{new SearchSpec("Baidu", "https://www.baidu.com/s?wd="),
		 new SearchSpec("YaHoo", "https://search.yahoo.com/search?p="),
		 new SearchSpec("Bing", "https://www.bing.com/search?q="),
		 new SearchSpec("sogou", "https://www.sogou.com/web?query=")};
	
	public static SearchSpec[] getCommonSpecs()
	{
		return commonSpecs;
	}
	//�����û�������ֶκ������������ֵõ�ʵ�ʵ�URL
	public static String makeURL(String searchEngineName, String searchString)
	{
		SearchSpec[] searchSpecs = getCommonSpecs();
		String searchURL = null;
		for(int i=0; i<searchSpecs.length; i++)
		{
			SearchSpec spec = searchSpecs[i];
			if(spec.getName().equalsIgnoreCase(searchEngineName))
			{
				searchURL = spec.makeURL(searchString);
				break;
			}
		}
		return searchURL;
	}
}