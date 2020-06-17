package tools.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tools.cmm.Util;

@Entity(name="ZZ_DATA")
public class DataInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "title")	private String title;
	@Column(name = "url")	private String url;
	@Column(name = "data_type")	private String dataType;
	@Column(name = "source")	private String source;
	@Column(name = "content")	private String content;
	@Column(name = "hit_count")	private Integer hitCount;
	@Temporal(TemporalType.TIMESTAMP) @Column(name = "pub_date")	private Date pubDate;
	@Temporal(TemporalType.TIMESTAMP) @Column(name = "col_date")	private Date colDate;
	@Column(name = "use_yn")	private String useYn;

	/**
	 * toString()
	 * @return String
	 */
    public String toString() {
    	return Util.reflectionToString(this);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHitCount() {
		return hitCount;
	}

	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getColDate() {
		return colDate;
	}

	public void setColDate(Date colDate) {
		this.colDate = colDate;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
    
}
