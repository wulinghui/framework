package com.wlh.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.ArrayUtils;

import com.wlh.dao.SqlConfig;
import com.wlh.dao.entity.ColumnSet;
import com.wlh.dao.entity.ColumnSetImp;
import com.wlh.dao.entity.Value;
import com.wlh.dao.entity.ValueImp;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.ColumnListHandler
 */
public class ColumnSetHandler<T> extends AbstractHandler<ColumnSet<T>> {

	public ColumnSetHandler(SqlConfig config) {
		super(config);
	}

	@Override
	public ColumnSet<T> handle(ResultSet rs) throws SQLException {
		ColumnSet<T> rows = new ColumnSetImp(new ArrayList<T>());
		int i = 0;
		Integer columnMax = this.getColumnMax();
		int[] rowIndexs = getRowIndexs();
        int rsIndex = 0;
        while(rs.next()){
        	if( rowIndexs != null)	
        		if(ArrayUtils.contains(rowIndexs, rsIndex++)) continue;
        	
			if (i == columnMax)
				break;
			rows.add(this.handleRow(rs));
			i++;
		}
		return rows;
	}

	protected T handleRow(ResultSet rs) throws SQLException {
		return (T) ValueHandler.toValue(this, rs);
	}

}
