package vtu.hackathon.srrs.futureeducation.dto;

import java.io.Serializable;

/**
 * Created by sathish on 17/10/15.
 */
public class StreamDTO implements Serializable {

    private String topic;
    private String heading;
    private String content;
    private String image;
    private String page;


    public String getTopic(){ return topic; }
    public String getHeading(){ return heading; }
    public String getContent(){ return content; }
    public String getImage(){ return image; }
    public String getPage(){ return page; }


    public void setTopic(String topic){ this.topic = topic;}
    public void setHeading(String heading){ this.heading = heading;}
    public void setContent(String content){ this.content = content;}
    public void setImage(String image){ this.image = image;}
    public void setPage(String page){ this.page = page;}



}
