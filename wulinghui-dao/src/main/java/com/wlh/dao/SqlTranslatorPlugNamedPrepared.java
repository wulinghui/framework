package com.wlh.dao;

import java.io.IOException;
import java.io.Writer;

public class SqlTranslatorPlugNamedPrepared extends CharSequenceTranslator {
	protected SqlConfig config;
	protected Object para;
	
	public SqlTranslatorPlugNamedPrepared(SqlConfig config, Object para) {
		super();
		this.config = config;
		this.para = para;
	}
	public final int translate(CharSequence input, int index, Writer out)
			throws IOException {
		return translate( config , input, index, out ,para);
	}
	protected int translate(SqlConfig config , CharSequence input, int index, Writer out , Object para)
			throws IOException {
		return 
	}
}
