package example;

import java.io.Serializable;
import java.util.Date;

public class EscalationCallOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private boolean skipUserOnAvialibility;
	private Date createdDate;
	private Date lastUpdatedDate;
//	private Tenant tenant;
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSkipUserOnAvialibility(boolean skipUserOnAvialibility) {
		this.skipUserOnAvialibility = skipUserOnAvialibility;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastUpdatedDate(Date updatedDate) {
		this.lastUpdatedDate = updatedDate;
	}

//	public void setTenant(Tenant tenant) {
//		this.tenant = tenant;
//	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isSkipUserOnAvialibility() {
		return skipUserOnAvialibility;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

//	@Override
//	public Tenant getTenant() {
//		return tenant;
//	}

}
