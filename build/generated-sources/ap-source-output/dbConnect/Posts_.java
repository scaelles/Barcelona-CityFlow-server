package dbConnect;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-07T16:43:02")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, Date> date;
    public static volatile SingularAttribute<Posts, Float> long1;
    public static volatile SingularAttribute<Posts, String> caption;
    public static volatile SingularAttribute<Posts, String> typeMedia;
    public static volatile SingularAttribute<Posts, String> imLink;
    public static volatile SingularAttribute<Posts, Float> lat;
    public static volatile SingularAttribute<Posts, Integer> idPost;
    public static volatile SingularAttribute<Posts, String> tags;
    public static volatile SingularAttribute<Posts, Integer> likes;
    public static volatile SingularAttribute<Posts, Integer> idNeighb;

}