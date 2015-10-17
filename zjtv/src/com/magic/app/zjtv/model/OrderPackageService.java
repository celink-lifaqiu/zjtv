package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.OrderPackageServiceEntity;
import com.magic.app.zjtv.entities.PackagesEntity;

public class OrderPackageService extends OrderPackageServiceEntity {

	private Packages packages;

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}


	
}
