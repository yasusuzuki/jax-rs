package jaxrs_test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ibm.websphere.jaxrs20.multipart.IAttachment;
import com.ibm.websphere.jaxrs20.multipart.IMultipartBody;

/**
 * 簡単なREST API集
 * JAX-RSはファイルアップロードをサポートしておらず、Apache CXFの実装依存の
 * API(アノーテーション)を利用している　IMultipartBody
 * @author yasu
 *
 */

/* JAX-RSのリソースクラスのデフォルトスコープは@RequestScopedに近いが、
 * 明示的にCDIの@RequestScopedを指定しないCDI管理下になってくれない
 * @Injectが機能するためには、CDI管理下にする必要がある。
*/
@RequestScoped  
@Path("/gas")
public class GASSimulator {
	@Inject
	private TaskRepository task;

    @GET
    @Path("/loadMySpreadSheet") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadMySpreadSheet() {
    	System.out.println(LocalDateTime.now() + "loadMySpreadSheet is called ");
    	return Response.ok(task.getTask()).build();
    }
    
    @POST
    @Path("/updateMySpreadSheet")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response updateMySpreadSheet(@FormParam("input") String input) {
    	task.getTask().addComment(input);
    	System.out.println(LocalDateTime.now() + " updateMySpreadSheet is called " + input);
        return Response.ok().build();
    }
    
    

    /**
     * 
     * FORMを使ってクライアントがファイルアップロードしたときに、そのファイルをサーバアプリのルートフォルダに
     * 保存する機能。クライアント実装はclient/test_fileupload.htmlを参照。
     * 
     * @param bodies
     * @param ctx
     * @return
     * @throws Exception
     */
    @POST
    @Path("/upload")     
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(IMultipartBody bodies, @Context ServletContext ctx) throws Exception {
    	// multipart/form_dataの場合、複数の添付ファイルが存在する想定
    	int attachCount = 0;
    	for ( IAttachment attach : bodies.getAllAttachments()){
            if (attach == null) {
                continue;
            }
            System.out.println("[" + attachCount + "]th attach " + attach.getContentId());
            MultivaluedMap<String, String> map = attach.getHeaders();
            String contentDisposition = map.getFirst("Content-Disposition");
            if ( contentDisposition == null ){ continue; }

            String fileName = "default_out_file";
            System.out.println("Content-Disposition : " + contentDisposition);
            String[] contentDispositions = contentDisposition.split(";");
            //HTTPリクエストヘッダから送信元のファイル名を割り出す
            for (String tempName : contentDispositions) {
            	//送信元のファイル名は、filename="abc.png" のような文字列ではいってくる
            	if( tempName.indexOf("filename") == -1){ continue; }
                String[] names = tempName.split("=");
                if( names == null || names.length <= 1 ){ continue; } 
                //ファイル名の両脇にあるダブルクォートを除去
                fileName = names[1].trim().replaceAll("\"", "");
            }
            
            //FORMで送信されたバイト列をファイルに書き出す
    		InputStream ins = attach.getDataHandler().getInputStream();
            BufferedInputStream br = new BufferedInputStream(ins);
            String ls = File.separator;
            
            
            //ファイル名は送信元と同じ。出力先フォルダはデプロイしたアプリのルート
            File ofile = new File(ctx.getRealPath("/") + ls + fileName);
            System.out.println("Writing file to : " + ofile.getAbsolutePath());
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(ofile));
            int len;
            byte buf[]=new byte[256];
            while ((len = br.read(buf)) != -1){  
            	bw.write(buf,0,len);  
            }  
            bw.flush();
            bw.close();
            br.close();
              

            System.out.println("Complete file upload at server side");
    	}
    	return Response.ok().build();
    }
}  

