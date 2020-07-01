package com.fox.net;

import java.io.File;

import com.fox.Settings;

public class Update {

	public static byte updateExists(int type, String filename) {
		if(type == 0) {
			File file = new File(Settings.SAVE_DIR + Settings.SAVE_NAME);
			if (!file.exists())
				return 1;

			String localCheck = Checksum.getLocalChecksum();
			String remoteCheck = Checksum.getRemoteChecksum();

			if (remoteCheck == null || localCheck == null)
				return 2;

			if (!remoteCheck.equalsIgnoreCase(localCheck))
				return 3;

			return 0;
		} else if (type == 1){
			System.out.println("Type 1 detected, filename " + filename);
			File file = new File(Settings.CACHE_DIR + filename);
			System.out.println("Checking in " + Settings.CACHE_DIR);
			if (!file.exists())
				return 1;

			String localCheck = Checksum.getLocalChecksum(Settings.CACHE_DIR + filename);
			String remoteCheck = Checksum.getRemoteChecksum(Settings.CACHE_URL + filename);

			if (remoteCheck == null || localCheck == null)
				return 2;

			if (!remoteCheck.equalsIgnoreCase(localCheck))
				return 3;

			return 0;
		}
		return 0;
	}

}
