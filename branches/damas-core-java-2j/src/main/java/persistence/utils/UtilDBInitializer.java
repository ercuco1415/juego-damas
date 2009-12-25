package persistence.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class UtilDBInitializer {

	public static void initDropCreate(){
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.drop(true, true);
		schemaExport.create(true, true);
	}
	
}
