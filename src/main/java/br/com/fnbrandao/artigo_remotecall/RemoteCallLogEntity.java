package br.com.fnbrandao.artigo_remotecall;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class RemoteCallLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMOTECALLLOG_SEQ")
	@SequenceGenerator(sequenceName = "REMOTECALLLOG_SEQ", allocationSize = 1, name = "REMOTECALLLOG_SEQ")
	private Long id;

	@Column
	private int sleep;

	@Column
	private long time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
