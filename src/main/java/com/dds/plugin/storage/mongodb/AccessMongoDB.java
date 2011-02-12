/**
 * 
 */
package com.dds.plugin.storage.mongodb;

import java.net.UnknownHostException;


/**
 * To test the functionality of MongoDB.class
 * 
 * @author ravid
 * 
 */
public class AccessMongoDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoDB mdb;
		try {
			mdb = new MongoDB();

			mdb.createConnection();

			mdb.put("key1", "value1");
			mdb.put("key2", "value2");
			mdb.put("key1", "value3");

			if (mdb.contains("key1"))
				System.out.println("Present!");
			else
				System.out.println("Not present!");
			// System.out.println(mdb.get("key2"));
			// mdb.showAll();
			// mdb.delete("key1");
			// mdb.deleteAll();
			mdb.closeConnection();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}