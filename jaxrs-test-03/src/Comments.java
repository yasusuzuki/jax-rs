import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

/**
 * GASではGoolge Spreadsheetに保存されるもの。
 *　本来ならば、データベースなどに永続化されるべきものだが、
 * スタブ目的なので、そこまでおおがかりにする必要はない。
 * なので、ApplicationScopedでデータの永続化を表現している。
 */
@ApplicationScoped
public class Comments {
	private List<String> comments = new ArrayList<String>();
	
	public Comments(){
		System.out.println("Comments init()");
		comments.add("こめんと1");
	}
	
	public void add(String comment){
		comments.add(comment);
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for ( String comment : comments ){
			buff.append(comment);
		}
		return buff.toString();
	}
	
	

}
