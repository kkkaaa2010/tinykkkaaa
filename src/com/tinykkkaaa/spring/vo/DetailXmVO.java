package com.tinykkkaaa.spring.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DetailXmVO implements RowMapper<DetailXmVO> {
	private String xmdm;
	private String xmmc;
	private String accounttype_dm;
	private String detailtype_dm;
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getAccounttype_dm() {
		return accounttype_dm;
	}
	public void setAccounttype_dm(String accounttype_dm) {
		this.accounttype_dm = accounttype_dm;
	}
	public String getDetailtype_dm() {
		return detailtype_dm;
	}
	public void setDetailtype_dm(String detailtype_dm) {
		this.detailtype_dm = detailtype_dm;
	}
	@Override
	public DetailXmVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetailXmVO vo = new DetailXmVO();
		vo.setXmdm(rs.getString("xmdm"));
		vo.setXmmc(rs.getString("xmmc"));
		vo.setDetailtype_dm(rs.getString("detailtype_dm"));
		vo.setAccounttype_dm(rs.getString("accounttype_dm"));
		return vo;
	}
}
