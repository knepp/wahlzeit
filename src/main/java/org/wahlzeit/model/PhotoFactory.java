/*
 * SPDX-FileCopyrightText: 2006-2009 Dirk Riehle <dirk@riehle.org> https://dirkriehle.com
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

/**
 * An Abstract Factory for creating photos and related objects.
 */
public class PhotoFactory {
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		return FlowerPhotoFactory.getInstance();
/*		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			setInstance(new PhotoFactory());
		}
		
		return instance;
*/
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) throws IllegalStateException {
		if (instance != null) {
			FlowerLog.logError("It was attempted to initialize PhotoFactory twice.");
			throw new IllegalStateException("attempt to initialize PhotoFactory twice");
		}
		instance = photoFactory;
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	/**
	 * 
	 */
	protected PhotoFactory() {
		// do nothing
	}

	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new Photo();
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(PhotoId id) {
		return new Photo(id);
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		try {
			return new Photo(rs);
		} catch (SQLException exception) {
			FlowerLog.logError("Photo could not be created due to SQL error.");
			throw exception;
		}
	}
	
	/**
	 * 
	 */
	public PhotoFilter createPhotoFilter() {
		return new PhotoFilter();
	}
	
	/**
	 * 
	 */
	public PhotoTagCollector createPhotoTagCollector() {
		return new PhotoTagCollector();
	}

}
