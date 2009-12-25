package persistence.daos;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class CreateDBTest {

	@Test
	public void test(){
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.drop(true, true);
		schemaExport.create(true, true);
	}
}
