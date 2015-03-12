package com.hms.core.mongodb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.googlecode.mjorm.MongoDao;
import com.googlecode.mjorm.MongoDaoImpl;
import com.googlecode.mjorm.XmlDescriptorObjectMapper;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class BaseRootDAO {

	protected static MongoDao mongoDao=null;
	
	private static Mongo mongo = null;
	
	private final String DEFAULT_PROPERTY_FILE="mongo";
	
	private final String DEFAULT_MJORM_FILE="mjorm.xml";
	
	private static List<ServerAddress> serverlist = new ArrayList<ServerAddress>();
	
	
	public BaseRootDAO() {
		// TODO Auto-generated constructor stub
		if (mongoDao == null) {
			// Get mongo.properties
			try {
				// Create MongoOptions 
				MongoOptions mop = new MongoOptions();
				
				ResourceBundle bundle = ResourceBundle.getBundle("mongo");
				Set<String> keys=bundle.keySet();
				
				for (String key : keys) {
					String value=bundle.getString(key);
					System.out.println(value);
					
					if ("servers".equals(key)){
						//Get server list
						String[] serverString = value.split(";");
						for (String serv : serverString) {
							String[] server = serv.split(":");
							if (server.length != 2)
								continue;
							String host = server[0];
							String port = server[1];
							ServerAddress serverAddress = new ServerAddress(host,Integer.valueOf(port));
							serverlist.add(serverAddress);
							
						}
					}
					
					if ("connectionsPerHost".equals(key))
						mop.setConnectionsPerHost(Integer.valueOf(value));
					
					if ("threadsAllowedToBlockForConnectionMultiplier".equals(key))
						mop.setThreadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(value));
					
					if ("connectTimeout".equals(key))
						mop.setConnectTimeout(Integer.valueOf(value));
					
					if ("maxWaitTime".equals(key))
						mop.setMaxWaitTime(Integer.valueOf(value));
					
					if ("autoConnectRetry".equals(key))
						mop.setAutoConnectRetry(Boolean.getBoolean(value));
					
					if ("socketTimeout".equals(key))
						mop.setSocketTimeout(Integer.valueOf(value));
					
					if ("socketKeepAlive".equals(key))
						mop.setSocketKeepAlive(Boolean.getBoolean(value));
					
					if ("autoConnectRetry".equals(key))
						mop.setAutoConnectRetry(Boolean.getBoolean(value));
					
					if ("maxAutoConnectRetryTime".equals(key))
						mop.setMaxAutoConnectRetryTime(Integer.valueOf(value));
				}
				
				// Initial mongo
				mongo = new Mongo(serverlist,mop);
				
				// Get mjorm.xml
				String config_file_path = Thread.currentThread().getContextClassLoader().getResource("mjorm.xml").getFile();
				File mjorm_config_file=new File(config_file_path);
				
				// Create mongoDao
				XmlDescriptorObjectMapper objectMapper = new XmlDescriptorObjectMapper();
				objectMapper.addXmlObjectDescriptor(mjorm_config_file);
				
				mongoDao = new MongoDaoImpl(mongo.getDB(bundle.getString("db")), objectMapper);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				if (mongoDao!=null)
					mongoDao=null;
				
				if (mongo!=null){
					mongo.close();
					mongo=null;
					
				}
			} 

		}

	}
	
	public void closeDb()
	{
		if (mongoDao!=null)
			mongoDao=null;
		
		if (mongo!=null){
			mongo.close();
			mongo=null;
		}
		
		System.gc();
	}
	
	
	public static Mongo getMongo() {
		return mongo;
	}

	public static void setMongo(Mongo mongo) {
		BaseRootDAO.mongo = mongo;
	}

	public String getDEFAULT_PROPERTY_FILE() {
		return DEFAULT_PROPERTY_FILE;
	}

	public String getDEFAULT_MJORM_FILE() {
		return DEFAULT_MJORM_FILE;
	}

	public static MongoDao getMongoDao() {
		return mongoDao;
	}

	public static void setMongoDao(MongoDao mongoDao) {
		BaseRootDAO.mongoDao = mongoDao;
	}


	public static List<ServerAddress> getServerlist() {
		return serverlist;
	}

	public static void setServerlist(List<ServerAddress> serverlist) {
		BaseRootDAO.serverlist = serverlist;
	}

	
	
}
