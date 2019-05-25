package com.wlh.jpa;

import static com.wlh.jpa.JpaHelper.getHELPER;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.wlh.jpa.entity.ManyOnManyEntity;
import com.wlh.jpa.entity.ORMEntity;
import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;

/**
 * @author wulinghui
 * 建表之前先删除。
 */
public class CreateTablePolicy extends UpdateTablePolicy {
	private static ILogger logger = LogMSG.getLogger();
	public CreateTablePolicy(DataSource ds) {
		super(ds);
	}

	@Override
	public void createTable(Class<?> class1) {
		ORMEntity ormEntity = new ORMEntity(class1);
		executeDropTable(getHELPER().getTableName(class1) );
		for (ManyOnManyEntity element : ormEntity.getAllManyOnMany()) {
			executeDropTable( element.getManyOnManyTableName() );
		}
		super.createTable(class1);
	}

	protected void executeDropTable(String tableName) {
		try {
			String sql = "DROP TABLE " + tableName;
			queryRunner.update(sql);
//			logger.debug("drop success : " +sql);
		} catch (SQLException e) {
			// in 说明不存在。
			logger.debug(e);
		}
	}
	
}
