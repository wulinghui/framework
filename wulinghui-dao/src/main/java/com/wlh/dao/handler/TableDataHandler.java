package com.wlh.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wlh.dao.SqlConfig;
import com.wlh.dao.entity.ColumnSet;
import com.wlh.dao.entity.Record;
import com.wlh.dao.entity.TableData;
import com.wlh.dao.entity.TableDataImp;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.MapListHandler
 */
public class TableDataHandler extends AbstractHandler<ColumnSet<Record>> {
	private final ColumnSetHandler handler;
			
	public TableDataHandler(SqlConfig config) {
		super(config);
		handler = new ColumnSetHandler(config){
			protected Record handleRow(ResultSet rs) throws SQLException {
				return RecordHandler.toRecord(this, rs);
			}
		};
	}
	
	@Override
	public TableData handle(ResultSet rs) throws SQLException {
		return new TableDataImp(handler.handle(rs));
	}

	
}
