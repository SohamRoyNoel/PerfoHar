package com.proHar.perfoMeasure.main.accessors;

import java.util.ArrayList;
import java.util.List;

public class PerfoPOJO {

	List<String> navHolder = new ArrayList<String>();
	List<String> resHolder = new ArrayList<String>();
	
	public List<String> getNavHolder() {
		return navHolder;
	}
	public void setNavHolder(List<String> navHolder) {
		this.navHolder = navHolder;
	}
	public List<String> getResHolder() {
		return resHolder;
	}
	public void setResHolder(List<String> resHolder) {
		this.resHolder = resHolder;
	}

	public static boolean isFinished() {
		return true;
	}


}
